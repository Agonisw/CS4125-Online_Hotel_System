<template>
    <div>
        <div>
            <div class="flicker-example" data-block-text="false">
                <el-carousel height="460px" style="background-color: white" >
                    <el-carousel-item style="background-color: white" v-for="(item,index) in imageList" :key="index">
                        <img style="height:460px;width: 100%;cursor: pointer" :src="item.imageUrl"/>
                    </el-carousel-item>
                </el-carousel>
            </div>
            <All></All>

        </div>
    </div>
</template>

<script>
    import { getProductList } from '../../api/cake';
    import All from '../../components/page/All'
    export default{
        data(){
            return{
                // imageList:[
                //     {id:1,imageUrl:require('../../assets/images/banner_02.jpg')},
                //     {id:2,imageUrl:require('../../assets/images/banner3.jpg')},
                //     {id:3,imageUrl:require('../../assets/images/banner05.png')},
                // ],
                imageList:[
                    {id:1,imageUrl:require('../../assets/img/2.png')},
                    {id:2,imageUrl:require('../../assets/img/3.png')},
                    {id:3,imageUrl:require('../../assets/img/6.png')},
                    {id:4,imageUrl:require('../../assets/img/15.png')},
                ],
                list1:[],
                loveFirstData:{},
                loveSecondData:{},
                loveThirdData:{},
                loveFourData:{},
                loveFiveData:{},
            }
        },

        created() {
            this.getListOne();
        },

        components:{
            All
        },

        methods:{
            toDetail(id){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/detail/'+id)
            },
            getListOne(){
                getProductList({pageSize:4,recommendType:'1'}).then(res =>{
                    this.list1 = res.data.list;
                });
                getProductList({pageSize:5,recommendType:'2'}).then(res =>{
                    this.loveFirstData = res.firstData;
                    this.loveSecondData = res.secondData;
                    this.loveThirdData = res.thirdData;
                    this.loveFourData = res.fourData;
                    this.loveFiveData = res.fiveData;
                });
            }
        },
        mounted() {

        }
    }
</script>

<style scoped>

    #web li:nth-child(2){
        margin:0 20px;
    }


    #web li:nth-child(4){
        margin-left:20px;;
    }

    #web .brand{
        margin-left: 30px;
        width: 200px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor: pointer;
    }

    a, a:visited {
        color: #E54028;
        text-decoration: none;
    }
    a:hover {
        color: #c22d18;
        cursor: pointer;
    }
    *{
        margin: 0;
        padding: 0;
    }
    body{
        height: 6000px;
    }
    .lift-nav{
        position: fixed;
        top: 100px;
        left: 30px;
        display: none;
    }
    .lift-nav li{
        width: 30px;
        height: 30px;
        text-align: center;
        line-height: 30px;
        color: #fff;
        padding: 10px 10px;
        margin-bottom: 10px;
        background: skyblue;
        cursor: pointer;
    }

    .lift-nav li.current{
        background:#fb0000;
    }

    /*.t1,.t2,.t3,.t4,.t5,.t6{
        width: 800px;
        height: 400px;
        text-align: center;
        line-height: 400px;
        background: skyblue;
        margin: 100px auto;
        font-size: 30px;
        color: #fff;
    }
    .t1{
        margin-top: 200px;
    }
    .t2{
        background: pink;
    }
    .t3{
        background: tomato;
    }
    .t4{
        background: grey;
    }
    .t5{
        background: yellow;
    }
    .t6{
        background: seagreen;
    }*/


</style>
<style scoped src="../../assets/css/index.css" />
<!--<style scoped src="../../assets/css/flickerplate.css" />-->
