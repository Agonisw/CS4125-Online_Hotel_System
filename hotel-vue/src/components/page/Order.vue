<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> order management
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input class="handle-input mr10" v-model="query.realName" placeholder="Please enter the purchaser"></el-input>
                <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="getOrderInfo">search</el-button>
                <el-button type="primary" icon="el-icon-refresh" @click="reset">reset</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
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
                                @click="delOrderInfo(scope.row.id)">delete</el-button>
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


    </div>
</template>

<script>

    import { deleteOrderInfo, delOrderInfo, getOrderInfo, getOrderList, saveAndUpdateOrderInfo } from '../../api/cake';
    import SingleUpload from '../common/singleUpload';

    export default {
        name: 'Order',
        components: { SingleUpload },
        data() {
            return {
                active:"2",
                query: {
                    pageNum:1,
                    pageSize:10,
                    realName:null,
                },
                tableData:[],
                addVisible: false,
                addVisible2:false,
                form:{},
                pageTotal: 0,

            };
        },
        created() {
            this.getData();
        },
        methods: {
            // 获取所有数组(支持分页和关键字查询)
            getData() {
                getOrderList(this.query).then(res => {
                    if (res.code === 1){
                        this.tableData = res.data.list;
                        this.pageTotal = res.data.total;
                    }else {
                        console.log('异常');
                    }

                });
            },

            getOrderInfo(){
                this.getData()
            },


            saveAndUpdateOrderInfo(){
                saveAndUpdateOrderInfo(this.form).then(res =>{
                    this.$message.success('operate successfully');
                    this.addVisible = true;
                    this.getData();
                })
            },

            delOrderInfo(id){
                this.$confirm('Are you sure you want to remove this order？', 'hint', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        delOrderInfo(id).then(res=>{
                            if (res.code === 1){
                                this.$message.success('remove success');                                this.getData();
                            }else {
                                this.$message.error('remove fail');                            }

                        })
                    }
                }).catch(() => {});

            },

            //编辑
            editOrderInfo(row){
                    this.form = JSON.parse(JSON.stringify(row));
                    this.addVisible = true;
            },

            lookStep(row){
                this.active = row.step;
                this.addVisible2 = true;
            },

            //重置
            reset(){
                this.query.realName= null;
                this.getData();
            },

            clearAddForm(){
                this.addVisible = false;
                this.addVisible2 = false;
            },

            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },


        }
    };
</script>

<style scoped>
    .handle-box {
        margin-top: 20px;
        margin-bottom: 10px;
    }
    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table {
        width: 100%;
        font-size: 14px;
    }
    .crop-demo-btn{
        position: relative;
        width: 98px;
        height: 32px;
        line-height: 32px;
        padding: 0 15px;
        background-color: #409eff;
        color: #fff;
        font-size: 12px;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .crop-input{
        position: absolute;
        width: 98px;
        height: 32px;
        left: 0;
        top: 0;
        opacity: 0;
        cursor: pointer;
    }
</style>
