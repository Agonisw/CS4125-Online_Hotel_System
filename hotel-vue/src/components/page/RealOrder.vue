<template>
    <div id="contentCon">
        <div class="left">
            <div :style="{ 'background-image': 'url(' + form.imageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></div>

            <p>{{form.realName}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥{{form.money}}</p>

            <a @click="toRe" style="cursor: pointer">recharge</a>
            <span></span>
            <ul style="cursor: pointer">
                <li @click="type = 2"><a>Personal Information</a></li>
                <li @click="type = 1"><a>My Order</a></li>
            </ul>
        </div>
        <div class="right" v-if="type === 1">
        <ul>
            <li class="all">All Order</li>
        </ul>
        <!--            </span>-->
        <el-table :data="orderList" style="width: 100%">
            <el-table-column prop="date" label="product" align="center" width="180">
                <template slot-scope="scope">
                    <div style="display: inline-flex">
                        <img style="height: 66px;width: 90px" :src="scope.row.imageUrl" />
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="realName" label="realName" align="center"></el-table-column>
            <el-table-column prop="count" label="count" align="center" ></el-table-column>
            <el-table-column prop="startTime" label="startTime" align="center" width="140"></el-table-column>
            <el-table-column prop="endTime" label="endTime" align="center" width="140"></el-table-column>
            <el-table-column prop="totalPrice" label="totalPrice" align="center"></el-table-column>
            <el-table-column prop="createTime" label="createTime" align="center" width="140"></el-table-column>
            <el-table-column prop="isDelivery" label="isDelivery" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.isDelivery ==='1'">account paid</span>
                </template>
            </el-table-column>
            <el-table-column label="operate" width="200">
                <template slot-scope="scope">
                    <el-button
                            style="width: 70px;height: 30px;background-color: #ff8000;color: white"
                            @click="editOrder(scope.row)">update</el-button>
                    <el-button
                            style="width: 70px;height: 30px;background-color: #ff8000;color: white"
                            @click="returnOrderInfo(scope.row.id)">returnHotel</el-button>
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
    </div>

        <div class="right" v-else>
            <el-card class="box-card" style="margin-top: -10px">
                <div  class="text item">
                    <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form" >
                        <el-form-item label="userName">
                            <el-input v-model="form.username"></el-input>
                        </el-form-item>
                        <el-form-item label="realName">
                            <el-input v-model="form.realName"></el-input>
                        </el-form-item>
                        <el-form-item label="phone">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item label="address">
                            <el-input v-model="form.address"></el-input>
                        </el-form-item>
                        <el-form-item label="image">
                            <single-upload v-model="form.imageUrl"></single-upload>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                <el-button type="primary" style="height: 40px;width: 80px" @click="saveAndUpdateUserInfo">confirm</el-button>
            </span>
                </div>
            </el-card>
        </div>
        <el-dialog title="rechargeInfo" :modal="false"  :visible.sync="addVisible" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form2" >
                <el-form-item label="rechargeMoney">
                    <el-input v-model="form2.money"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button style="height: 30px;width: 60px" @click="clearAddForm">cancel</el-button>
                <el-button type="primary" style="height: 30px;width: 60px" @click="saveAndUpdateUserInfo2">confirm</el-button>
            </span>
        </el-dialog>
        <el-dialog title="updateTime" :modal="false"  :visible.sync="addVisible2" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form2" >
                <el-form-item label="startTime">
                    <el-date-picker
                            v-model="form3.startTime"
                            type="date"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择开始日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="endTime">
                    <el-date-picker
                            v-model="form3.endTime"
                            type="date"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择结束日期">
                    </el-date-picker>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button style="height: 30px;width: 60px" @click="clearAddForm">cancel</el-button>
                <el-button type="primary" style="height: 30px;width: 60px" @click="saveAndUpdateOrderInfo">confirm</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {
        delCarInfo,
        delOrderInfo,
        getOrderList, getUserInfoBySecurityContextHolder,
        getUserList,
        reMoney, returnHotel, saveAndUpdateOrderInfo,
        saveAndUpdateUserInfo, saveAndUpdateUserInfo2
    } from '../../api/cake';
    import singleUpload from '../common/singleUpload';

    export default {
        name: 'RealOrder',
        components:{
            singleUpload
        },
        data(){
            return{
                orderList:[],
                form:{},
                form2:{},
                form3:{},
                query:{
                    pageNum:1,
                    pageSize:10,
                },
                pageTotal:0,
                type:2,
                addVisible:false,
                addVisible2:false,
            }
        },
        methods:{
            getOrderList(){
                getOrderList({userId:this.$store.state.userId,pageSize:100}).then(res =>{
                    this.orderList = res.data.list;
                    this.pageTotal = res.data.total;
                });
                getUserInfoBySecurityContextHolder().then(res =>{
                    this.form = res.data;
                })
            },

            toRe(){
                this.form2 = {};
                this.addVisible = true;
            },

            clearAddForm(){
                this.addVisible = false;
            },

            toLogin(){
                sessionStorage.clear();
                this.$router.push('/login')
            },

            editOrder(row){
                this.form3 = JSON.parse(JSON.stringify(row));
                this.addVisible2 = true;
            },

            saveAndUpdateOrderInfo(){
                saveAndUpdateOrderInfo(this.form3).then(res =>{
                    if (res.code === 1){
                        this.addVisible2 = false;
                        this.$message.success('modify successfully');
                        getOrderList({userId:this.$store.state.userId,pageSize:100}).then(res =>{
                            this.orderList = res.data.list;
                            this.pageTotal = res.data.total;
                        });
                    }else {
                        this.$message.warning('modify fail')
                    }
                })
            },

            delOrderInfo(id){
                this.$confirm('Are you sure you want to remove this order？', 'hint', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        delOrderInfo(id).then(res=>{
                            if (res.code === 1){
                                this.$message.success('remove success');
                                this.getOrderList();
                            }else {
                                this.$message.error('remove fail');
                            }

                        })
                    }
                }).catch(() => {});

            },

            returnOrderInfo(id){
                this.$confirm('Are you sure you want to check out? It might cost you some money？', 'hint', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        returnHotel(id).then(res=>{
                            if (res.code === 1){
                                this.$message.success('return hotel success');
                                this.getOrderList();
                            }else {
                                this.$message.error('return hotel fail');
                            }

                        })
                    }
                }).catch(() => {});

            },



            handlePageChange(val) {
                this.$set(this.query, 'pageNum', val);
                this.getOrderList();
            },

            saveAndUpdateUserInfo(){
                saveAndUpdateUserInfo2(this.form).then(res =>{
                    if (res.code === 1){
                        getUserInfoBySecurityContextHolder().then(res =>{
                            this.form = res.data;
                        })
                        this.$message.success('operate successfully');
                    }else {
                        this.$message.error('operate fail');
                    }
                })

            },

            saveAndUpdateUserInfo2(){
                this.form2.userId = this.$store.state.userId
                reMoney(this.form2).then(res =>{
                    if (res.code === 1){
                        getUserInfoBySecurityContextHolder().then(res =>{
                            this.form = res.data;
                        })
                        this.addVisible = false;
                        this.$message.success('operate successfully');

                    }else {
                        this.$message.error('operate fail');

                    }
                })

            },
        },


        created() {
            this.getOrderList()
        }
    };
</script>

<style scoped>

</style>
<style scoped src="../../assets/css/order.css" />
