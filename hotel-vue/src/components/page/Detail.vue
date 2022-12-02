<template>
    <div id="contentCon">
        <ul>
            <li>{{info.name}}</li>
        </ul>
        <div id="show">
            <div>
                <ul style="cursor: pointer">
                    <li :style="{ 'background-image': 'url(' + info.imageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></li>
                    <li :style="{ 'background-image': 'url(' + info.originImageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }" class="pic02"></li>
                    <li :style="{ 'background-image': 'url(' + info.showImageUrlOne + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }" class="pic03"></li>
                    <li :style="{ 'background-image': 'url(' + info.showImageUrlTwo + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }" class="pic04"></li>
                </ul>
                <ol :style="{ 'background-image': 'url(' + info.imageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></ol>

            </div>
            <div class="right">
                <p class="info" :title="info.brand+'~'+info.name">【self-support】{{info.brand}}~ {{info.name}}</p>
                <div>
                    <ul>
                        <span>¥</span>
                        <h2>{{price}}</h2>
                        <p>（notice of lowering price）</p>
                    </ul>
                    <ol>
                        <a >$15 or more is on a first-come</a>
                    </ol>
                    <li>
                        <p>service</p>
                        <div>
                            <span>.</span>
                            <p>Over 88 yuan, shipping is free</p>
                            <span>.</span>
<!--                            <p>不支持无理由货</p>-->
<!--                            <span>.</span>-->
<!--                            <p>48小时极速退款</p>-->
                        </div>
                    </li>
                </div>
                <ul>
                    <li class="check01">
                        <p>spec</p>
                        <span class="spec" :class="color(index)" @click="changeColor(index)" v-for="(item,index) in specData">{{item.specName}}</span>
                    </li>
                    <el-date-picker
                            v-model="startTime"
                            type="date"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="choose start time">
                    </el-date-picker>
                    <el-date-picker
                            v-model="endTime"
                            type="date"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="choose end time">
                    </el-date-picker>
                    <ol style="width: 200px">
                        <p>Num</p>
                        <a style="cursor: pointer" @click="sub">-</a><input  v-model="count"><a @click="add"  style="cursor: pointer" >+</a>
                    </ol>
<!--                    <a style="cursor: pointer" @click="toBuy" class="buy">立刻购买</a>-->
                    <a style="cursor: pointer"  @click="toCar" class="buy">Add Car</a>
                    <a href="#" class="love"></a>
                </ul>
            </div>
        </div>
        <div id="origin">
            <ul>
                <div>
                    <p>Origin</p>
                    <span></span>
                </div>
                <p>products introduction</p>
                <span></span>
            </ul>
            <ol>
                <li :style="{ 'background-image': 'url(' + info.originImageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></li>
                <div>
<!--                    <p>一个意大利士兵即将开赴战场，可是家里已经什么也没有了，爱<br>-->
<!--                        他的妻子为了给他准备干粮，把家里所有能吃的饼干、面包全做<br>-->
<!--                        进了一个糕点里，意为带我走，那个糕点就叫提拉米苏。每当这<br>-->
<!--                        个士兵在战场上吃到提拉米苏就会想起他的家，想起家中的爱人<br>-->
<!--                        。在意大利文里，提拉米苏有 “ 带我走 ” 的含义，带走的不只<br>是美味，还有爱和幸福</p>-->
                    <p>{{info.introduction}}</p>
                </div>
            </ol>
        </div>
        <div id="show" style="margin-bottom: 20px">
            <ul>
                <div>
                    <p>Show</p>
                    <span></span>
                </div>
                <p>product show</p>
                <span></span>
            </ul>
            <ol>
                <!--                <li></li>-->
                <div>
                    <li :style="{ 'background-image': 'url(' + info.showImageUrlOne + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></li>
                    <span :style="{ 'background-image': 'url(' + info.showImageUrlTwo + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></span>
                </div>
            </ol>
        </div>
    </div>
</template>

<script>
    import {
        deleteCommentInfo,
        deleteProductInfo, getAddressInfo, getCommentForAdmin,
        getProductInfo, getProductList, getSpecList,
        saveAndUpdateCarInfo,
        saveAndUpdateCommentInfo
    } from '../../api/cake';
    import mqtt from 'mqtt'
    export default {
        data(){
            return{
                info:{},
                specData:[],
                realColor:0,
                price:null,
                count:1,
                totalPrice:null,
                startTime:null,
                endTime:null,
                time:1,
            }
        },
        created() {
            this.getData(this.$route.params.id)
        },

        computed:{
          // price(){
          //
          // }
        },
        watch:{
            'realColor':function(newVal,oldVal) {
                this.price = this.specData[newVal].price * this.time;
            },
            endTime:function(newVal,oldValue) {
                console.log();
                this.time = this.getDifferTime(this.startTime,this.endTime);
                this.price = this.price * this.time;
            }
        },
        methods:{
            getData(id){
                getProductList({id:id}).then(res =>{
                    this.info = res.data.list[0];
                });
                getSpecList({productId:id}).then(res =>{
                    this.specData = res.data.list;
                    this.price = res.data.list[0].price;
                })
            },

            getDifferTime(startDate, endDate) {
                let startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
                let endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
                let dates = Math.abs((startTime - endTime)) / (1000 * 60 * 60 * 24);
                return dates;
            },

            changeColor: function(index){
                this.count = 1;
                this.realColor = index;
            },
            color: function(index){
                if(this.realColor === index){
                    return 'active';
                }
            },
            add(){
                if (this.count >4){
                    this.$message.warning('You can only place five orders at most');
                    return false;
                }
                this.count++;
                this.price =  this.specData[this.realColor].price * this.count * this.time;
            },
            sub(){
                if (this.count <2){
                    this.$message.warning('At least one item can be placed');
                    return false;
                }
                this.count--;
                this.price =  this.specData[this.realColor].price * this.count  * this.time;
            },
            toBuy(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/pay')
            },
            toCar(){
                if (this.$store.state.userId == null){
                    this.$message.warning('please log in');
                    return false;
                }
                saveAndUpdateCarInfo({userId:this.$store.state.userId,productId:this.info.id,totalPrice:this.price,count:this.count,specId:this.realColor,startTime:this.startTime,endTime:this.endTime,day:this.time}).then(res =>{
                    if (res.code === 1){
                        this.$message.success('Add to cart successfully');
                    }else {
                        this.$message.success(res.msg);
                    }
                })
            }
        }
    };
</script>
<style scoped>
    .active {
        background:#ff8000;
        color: whitesmoke !important;
    }
    #contentCon>#show>.right>ul>li>span{
        width:82px;
        height:32px;
        border:1px solid #d2d2d2;
        line-height:32px;
        text-align:center;
        color:#4c4c4c;
        font-size:14px;
        float:left;
        cursor: pointer;
        margin-left:20px;}

    #contentCon>#show>.right>ul>li>span:nth-child(5){
        margin-left: 48px;margin-top: 10px}
    #contentCon>#show>.right>ul>li>span:nth-child(6){
        margin-top: 10px}

    .info{
        width: 440px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor: pointer;
    }

    .spec{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor: pointer;
        font-size: 3px!important;
    }
</style>
<style scoped src="../../assets/css/xiangqing.css" />
