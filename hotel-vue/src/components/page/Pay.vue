<template>
    <div id="headCon">
        <ul>
            <a @click="toIndex()"><li></li></a>
            <p style="cursor: pointer">Pay</p>
            <ol>
                <li>
                    <div class="my"><img src="images/shopCar_08.png"></div>
                    <p class="red">My Car</p>
                </li>
                <li>
                    <div class="order"><img src="images/pay_03.jpg"></div>
                    <p>Confirm Order</p>
                </li>
                <li class="pay">
                    <div class="pay"><img src="images/pay_05.jpg"></div>
                    <p>Pay</p>
                </li>
                <li class="bingo">
                    <div class="bingo"><img src="images/shopCar_05.png"></div>
                    <p>Complete</p>
                </li>
            </ol>
        </ul>
        <div id="contentCon">
            <el-table :data="$store.state.carList" style="width: 100%">
                <el-table-column prop="date" label="product" align="center" width="200">
                    <template slot-scope="scope">
                        <div style="display: inline-flex">
                            <img style="height: 66px;width: 90px" :src="scope.row.imageUrl" />
                            <div style="margin-left: 15px;margin-top: 20px;width: 40px">{{scope.row.name}}</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="day" label="day" align="center" width="200"></el-table-column>
                <el-table-column prop="realName" label="realName" align="center" width="200"></el-table-column>
                <el-table-column prop="count" label="count" align="center" width="200"></el-table-column>
                <el-table-column prop="totalPrice" label="totalPrice" align="center" width="200"></el-table-column>
                <el-table-column prop="createTime" label="createTime" align="center" width="200"></el-table-column>
            </el-table>

            <div style="display: inline-flex;flex-direction: column">
                <ol>
                    <p>cope with???</p>
                    <span>{{$store.state.totalPrice}}</span>
                </ol>
                <ol>
                    <p>service charge???</p>
                    <span>10</span>
                </ol>
                <ol>
                    <p>expenses of taxation???</p>
                    <span>20</span>
                </ol>
                <ol>
                    <p>totalPrice???</p>
                    <span>{{$store.state.totalPrice + 30}}</span>
                </ol>
            </div>
            <el-button style="height: 30px;width: 60px;position:fixed;right: 150px;top:300px;" type="primary" @click="toPayment">Pay</el-button>
        </div>

    </div>

</template>

<script>
    import { saveAndUpdateOrderInfo, sendPay } from '../../api/cake';
    import vueQr from 'vue-qr'
    import axios from 'axios';
    export default {
        name: 'Pay',
        data(){
            return{
                orderNum:null,
                qr:null,
                websock: null,
            }
        },

        components:{
            vueQr
        },
        created() {
            this.initWebSocket();
        },
        destroyed() {
            this.websock.close() //????????????????????????websocket??????
        },
        mounted() {
            // this.toPayment();
            console.log(axios.defaults.baseURL)
        },
        methods:{
            toPayment(){
                saveAndUpdateOrderInfo ({discountId:sessionStorage.getItem('discountId'),all:this.$store.state.totalPrice+30,carList:this.$store.state.carList,orderNum:this.$store.state.orderNum,userId:this.$store.state.userId}).then(res =>{
                    if (res.code === 1){
                        this.$message.success('????????????');
                    }else {
                        console.log(res);
                        this.$message.warning(res.data.msg)
                    }
                });

                let _this = this;
                // ?????????????????????????????????
                // axios({
                //     method: 'POST',
                //     url: "http://127.0.0.1:9000/common/sandboxPay",
                //     params:{id:id,price:price,name:name,productId:productId,userId:sessionStorage.getItem('userId'),address:address}
                // }).then(res => {
                //     console.log(res)
                //     this.qr = res.data;
                //     const that = this;
                //     console.log('ddd')
                //     if ("WebSocket" in window) {
                //         // ???????????? web socket
                //         console.log("hahh");
                //         // ????????????????????????????????????
                //         var ws = new WebSocket("ws://localhost:9000/bindingRecord");
                //         ws.onopen = function() {
                //             // Web Socket ????????????????????? send() ??????????????????
                //             ws.send("data");
                //             // alert("???????????????...");
                //         };
                //         ws.onmessage = function(evt) {
                //             var received_msg = evt.data;
                //             // ???????????????????????????
                //             // alert("???????????????..." + evt.data);
                //             if (Boolean(evt.data)) {
                //                 _this.paySucc = true;
                //                 setTimeout(() => {
                //                     that.getData();
                //                     _this.dialogVisible = false;
                //                 }, 3 * 1000);
                //             }
                //
                //             ws.close();
                //         };
                //         ws.onclose = function() {
                //
                //         };
                //     } else {
                //         // ?????????????????? WebSocket
                //         alert("???????????????????????? WebSocket!");
                //     }
                // });
                // this.dialogVisible = true;
            },



            toIndex(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/index')
            },

            initWebSocket(){ //?????????weosocket
                const wsuri = "ws://localhost:9001/bindingRecord";
                this.websock = new WebSocket(wsuri);
                this.websock.onmessage = this.websocketonmessage;
                this.websock.onopen = this.websocketonopen;
                this.websock.onerror = this.websocketonerror;
                this.websock.onclose = this.websocketclose;
            },
            websocketonopen(){ //????????????????????????send??????????????????
                let actions = {"test":"12345"};
                this.websocketsend(JSON.stringify(actions));
            },
            websocketonerror(){//????????????????????????
                this.initWebSocket();
            },
            websocketonmessage(e){ //????????????
                const data = JSON.parse(e.data);
                console.log(data);
                let message ='';
                let i = 1;
                data.forEach(item =>{
                   message = message+ "<div>Your No."+i+" room number is:"+item.number+"---wifiPassword is:"+item.wifi+"</div><br>"
                    i++;
                });
                message = message+ "<div>Tip: Please keep it safe</div>";
                this.open2(message);
            },
            websocketsend(Data){//????????????
                this.websock.send(Data);
            },
            websocketclose(e){  //??????
                console.log('????????????',e);
            },

            open2(data) {
                this.$notify.closeAll();
                this.$notify({
                    title: '',
                    dangerouslyUseHTMLString: true,
                    message: `<div>${data}</div>`,
                    position: 'bottom-right',
                    type: 'warning',
                    customClass: 'ss',
                    duration: 0
                });
            }

            // toPay(){
            //     saveAndUpdateOrderInfo(
            //         {
            //             totalPrice
            //         }
            //     ).then(res =>{
            //
            //     })
            // }


    }
    };
</script>

<style scoped>

</style>
<style scoped src="../../assets/css/pay.css" />
