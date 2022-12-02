<template>
    <div id="headCon">
        <ul>
            <a <li></li></a>
            <p>My Car</p>
            <ol>
                <li>
                    <div class="my"><img src="images/shopCar_08.png"></div>
                    <p class="red">My Car</p>
                </li>
                <li>
                    <div class="order"><img src="images/shopCar_03.png"></div>
                    <p>Confirm Order</p>
                </li>
                <li class="pay">
                    <div class="pay"><img src="images/shopCar_11.png"></div>
                    <p>Pay</p>
                </li>
                <li class="bingo">
                    <div class="bingo"><img src="images/shopCar_05.png"></div>
                    <p>Complete</p>
                </li>
            </ol>
        </ul>
        <div id="contentCon">
            <ul>
                <li>
                    <p>My Product</p>
                    <span></span>
                </li>
                <li @click="toMyDiscount">
                    <p>My DisCount</p>
                    <span></span>
                </li>
            </ul>
            <section class="cartMain">
                <el-table :data="list" style="width: 100%" @selection-change="handleSelect">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column prop="date" label="product" align="center" width="180">
                        <template slot-scope="scope">
                            <div style="display: inline-flex">
                            <img style="height: 66px;width: 90px" :src="scope.row.imageUrl" />
                                <div style="margin-left: 15px;margin-top: 20px;width: 40px">{{scope.row.name}}</div>
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="startTime" label="startTime" align="center" width="180"></el-table-column>
                    <el-table-column prop="endTime" label="endTime" align="center" width="180"></el-table-column>
                    <el-table-column prop="day" label="day" align="center"></el-table-column>
                    <el-table-column prop="realName" label="realName" align="center" ></el-table-column>
                    <el-table-column prop="count" label="count" align="center"></el-table-column>
                    <el-table-column prop="totalPrice" label="totalPrice" align="center">
                        <template slot-scope="scope">
                            <span>{{scope.row.totalPrice * discountRate}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" label="createTime" align="center" width="180"></el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button
                                    style="width: 100px;height: 30px;background-color: #ff8000;color: white"
                                    @click="delCar(scope.row.id)">remove product</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="query.pageNum"
                            :page-size="query.pageSize"
                            :total="pageTotal"
                            @current-change="handlePageChange"
                    ></el-pagination>
                </div>
                <!--底部-->
                <div class="bar-wrapper">
                    <div class="bar-right">
                        <div class="piece">Selected goods<strong class="piece_num">{{carList.length}}</strong>count</div>
                        <div class="totalMoney">totalPrice: <strong class="total_text">{{totalPrice}}</strong></div>
                        <div class="totalMoney">discountRate: <strong class="total_text">{{rate * 100 }}%</strong></div>
                        <div class="totalMoney">discountPrice: <strong class="total_text">{{totalPrice * discountRate}}</strong></div>
<!--                        <div class="calBtn"><a>结算</a></div>-->
                        <el-button :disabled="carList.length === 0" type="success" style="width: 160px;height: 46px" @click="closeAccount">closeAccount</el-button>
                    </div>
                </div>
            </section>
        </div>
        <el-dialog title="discount" :modal="false"  :visible.sync="addVisible" width="33%">
            <el-select v-model="discountId" placeholder="please choose">
                <el-option
                        v-for="item in data"
                        :key="item.id"
                        :label="item.discountRate * 100 +'%折扣'"
                        :value="item.id">
                </el-option>
            </el-select>
        </el-dialog>
    </div>
</template>
<script>

    // import vueQr from 'vue-qr'
    import { delCarInfo, delUserInfo, getCarList, getDisCountRate, getMyDiscount } from '../../api/cake';
    export default {
       data(){
           return{
               list:[],
               query:{
                   pageNum:1,
                   pageSize:10,
               },
               pageTotal:0,
               carList:[],
               totalPrice:0,
               addVisible:false,
               data:[],
               discountId:null,
               discountRate:1,
               rate:1,
           }
       },

        watch:{
           discountId:function(newVal,oldVal) {
               for (let i=0;i<this.data.length;i++){
                   if (this.data[i].id === newVal){
                       this.discountRate = this.data[i].discountRate
                   }
               }
           }
        },

        created() {
            this.getData();
        },

        methods:{
           getData(){
               this.query.userId = this.$store.state.userId;
               getCarList(this.query).then(res =>{
                   this.list = res.data.list;
                   this.pageTotal = res.data.total;
                   getDisCountRate(this.$store.state.userId).then(res =>{
                       console.log(res);
                       let weekDay = this.getWeekDay();
                       if (weekDay === 2){
                           this.rate = 0.75
                       }else {
                           this.rate = res.data;
                       }

                   })
               })

           },

            getWeekDay(){
                return  new Date().getDay()
            },

            handlePageChange(val) {
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },
            handleSelect(val){
                this.carList = [];
                this.totalPrice = 0;
                val.forEach((item) => {
                    this.carList.push(item);
                    this.totalPrice = (this.totalPrice+item.totalPrice);
                });
                this.totalPrice = this.totalPrice * this.rate
            },

            toMyDiscount(){
               getMyDiscount({userId:this.$store.state.userId}).then(res =>{
                   this.data = res.data.list;
               });
               this.addVisible = true;
            },

            closeAccount(){
                console.log( this.totalPrice);
                for (let i=0;i<this.carList.length;i++){
                    this.carList[i].totalPrice = this.carList[i].totalPrice * this.discountRate * this.rate ;
                    this.$store.commit('saveCarList',this.carList);
                }

                this.$store.commit('saveTotalPrice',this.totalPrice * this.discountRate);
                this.$store.commit('saveOrderNum',this.createOrderNum());
                sessionStorage.setItem('discountId',this.discountId)
                this.$router.push('/pay')
            },

            createOrderNum() {
                let orderNum = '';
                for (let i = 0; i < 6; i++) {
                    orderNum += Math.floor(Math.random() * 10);
                }
                orderNum = new Date().getTime() + orderNum;  //时间戳，用来生成订单号。
                console.log(orderNum);
                this.orderNum = orderNum;
                return orderNum;
            },
            delCar(id){
                this.$confirm('Are you sure you want to remove this item from your cart？', 'hint', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                            delCarInfo(id).then(res=>{
                                if (res.code === 1){
                                    this.$message.success('remove success');
                                    this.getData();
                                }else {
                                    this.$message.error('remove fail');
                                }

                            })
                    }
                }).catch(() => {});

            },

        }

    };
</script>
<style scoped>
    /deep/ .el-pagination.is-background .el-pager li:not(.disabled).active {
        background-color: #ff9800 !important;
    }
</style>
<style scoped src="../../assets/css/shoppingCar.css" />
<style scoped src="../../assets/css/reset.css" />
<style scoped src="../../assets/css/carts.css" />
