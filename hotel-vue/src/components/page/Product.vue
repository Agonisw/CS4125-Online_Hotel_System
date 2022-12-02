<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>Room information
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.name" placeholder="Please input roomName" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="getProductInfo">search</el-button>
                <el-button type="primary" icon="el-icon-refresh" @click="reset">reset</el-button>
            </div>
            <div class="handle-box" style="display: inline-flex;">
                <el-button style="margin-bottom: 10px"
                           type="primary"
                           icon="el-icon-plus"
                           class="handle-del mr10"
                           @click="addUserInfo"
                >add</el-button>
            </div>

            <el-table
                    :data="tableData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
            >
                <el-table-column type="index" label="index" align="center"></el-table-column>
                <el-table-column prop="name" label="name" align="center"></el-table-column>
                <el-table-column prop="brand" label="brand" align="center"></el-table-column>
                <el-table-column prop="number" label="number" align="center"></el-table-column>
                <el-table-column prop="releaseArea" label="releaseArea" align="center"></el-table-column>
                <el-table-column prop="launchTime" label="launchTime" width="180" align="center"></el-table-column>
                <el-table-column prop="price" label="price" align="center"></el-table-column>
                <el-table-column prop="wifi" label="wifiPassword" align="center"></el-table-column>
                <el-table-column prop="count" label="saleCount" align="center"></el-table-column>
<!--                <el-table-column prop="buyCount" label="销量" align="center"></el-table-column>-->
                <el-table-column prop="sortName" label="sort" align="center">
                    <template slot-scope="scope">
                        <span v-if="scope.row.sort === '1'">PresidentialSuite</span>
                        <span v-if="scope.row.sort === '2'">LightLuxuryHotel</span>
                        <span v-if="scope.row.sort === '3'">HomeStayHotel</span>
                        <span v-if="scope.row.sort === '4'">YouthHostel</span>
                    </template>
                </el-table-column>
                <el-table-column prop="introduction" label="introduction" align="center" width="200" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <span :title="scope.row.introduction" class="ellipsis" style="height: 40px">{{scope.row.introduction}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="imageUrl" label="image" align="center" >
                    <template slot-scope="scope">
                        <el-image :src="scope.row.imageUrl" style="width: 70px;height: 50px" :preview-src-list="[scope.row.imageUrl]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="originImageUrl" label="Introduction Picture" align="center" >
                    <template slot-scope="scope">
                        <el-image :src="scope.row.originImageUrl" style="width: 70px;height: 50px" :preview-src-list="[scope.row.originImageUrl]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="showImageUrlOne" label="presentPictureOne" align="center" >
                    <template slot-scope="scope">
                        <el-image :src="scope.row.showImageUrlOne" style="width: 70px;height: 50px" :preview-src-list="[scope.row.showImageUrlOne]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="showImageUrlTwo" label="presentPictureTwo" align="center" >
                    <template slot-scope="scope">
                        <el-image :src="scope.row.showImageUrlTwo" style="width: 70px;height: 50px" :preview-src-list="[scope.row.showImageUrlTwo]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="operate" width="350" align="center">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                icon="el-icon-edit"
                                @click="editProductInfo(scope.row)"
                        >update</el-button>
                        <el-button
                                type="primary"
                                icon="el-icon-edit"
                                @click="editProductInfo2(scope.row)"
                        >spec</el-button>
                        <el-button
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteProductInfo(scope.row.id)"
                        >delete</el-button>
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
        <!-- 添加弹出框 -->
        <el-dialog title="Room Info" :modal="false"  :visible.sync="addVisible" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" :rules="rules" label-width="80px"  :model="form" >
                <el-form-item label="name" prpp="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="brand" prpp="name">
                    <el-input v-model="form.brand"></el-input>
                </el-form-item>
                <el-form-item label="number" prpp="name">
                    <el-input v-model="form.number"></el-input>
                </el-form-item>
                <el-form-item label="releaseArea" prop="releaseArea">
                    <el-input v-model="form.releaseArea"></el-input>
                </el-form-item>
                <el-form-item label="price" prop="price">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
                <el-form-item label="wifiPassword" prop="wifi">
                    <el-input v-model="form.wifi"></el-input>
                </el-form-item>
                <el-form-item label="sort" prop="sort">
                    <el-radio v-model="form.sort" label="1">PresidentialSuite</el-radio>
                    <el-radio v-model="form.sort" label="2">LightLuxuryHotel</el-radio>
                    <el-radio v-model="form.sort" label="3">HomeStayHotel</el-radio>
                    <el-radio v-model="form.sort" label="4">YouthHostel</el-radio>
                </el-form-item>
                <el-form-item label="launchTime" prop="launchTime">
                    <el-date-picker
                            v-model="form.launchTime"
                            type="datetime"
                            placeholder="choose launchTime"
                            value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="image" prop="imageUrl">
                    <single-upload v-model="form.imageUrl"></single-upload>
                </el-form-item>
                <el-form-item label="Introduction Picture">
                    <single-upload v-model="form.originImageUrl"></single-upload>
                </el-form-item>
                <el-form-item label="presentPictureOne">
                    <single-upload v-model="form.showImageUrlOne"></single-upload>
                </el-form-item>
                <el-form-item label="presentPictureTwo">
                    <single-upload v-model="form.showImageUrlTwo"></single-upload>
                </el-form-item>
                <el-form-item label="introduction" prop="introduction">
                    <textarea cols="50" rows="10" v-model="form.introduction"></textarea>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="clearAddForm">cancel</el-button>
                <el-button type="primary" @click="saveAndUpdateProductInfo()">confirm</el-button>
            </span>
        </el-dialog>

        <el-dialog title="spec info" :modal="false"  :visible.sync="addVisible2" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm"  label-width="80px"  :model="spec" >
                <el-form-item label="room spec" prpp="name">
                    <el-input v-model="spec.specName"></el-input>
                </el-form-item>
                <el-form-item label="room price" prpp="name">
                    <el-input v-model="spec.price"></el-input>
                </el-form-item>
            </el-form>
            <span>room spec</span>
            <div style="margin-left: 100px">

                <ul>
                    <li style="margin-top: 15px" v-for="(item,index) in specData">{{item.specName}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>¥</span>{{item.price}}</li>
                </ul>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="clearAddForm">cancel</el-button>
                <el-button type="primary" @click="saveSpecInfo">confirm</el-button>
            </span>
        </el-dialog>


    </div>
</template>

<script>

    import SingleUpload from '../../components/common/singleUpload'
    import {
        delProductInfo,
        getProductList,
        getSortInfo, getSpecList,
        saveAndUpdateProductInfo, saveAndUpdateSpecInfo,
        saveAndUpdateUserInfo, saveProductInfo, updateProductInfo
    } from '../../api/cake';


    export default {
        name: 'user',

        data() {
            return {
                query: {
                    pageNum:1,
                    pageSize:10,
                    type:'1',
                },
                specData:[],
                spec:{
                    specName:null,
                    price:null,
                },
                options:[],
                tableData:[],
                addVisible: false,
                addVisible2: false,
                form:{
                    imageUrl:'',
                },
                pageTotal: 0,
                rules: {
                    name:[{ required: true, message: 'Please input room name', trigger: 'blur' }],
                    releaseArea: [{ required: true, message: 'Please enter the location', trigger: 'blur' }],
                    price: [{ required: true, message: 'Please enter the price', trigger: 'blur' }],
                    wifi: [{ required: true, message: 'Please enter the wifi password', trigger: 'blur' }],
                    count: [{ required: true, message: 'Please input inventory', trigger: 'blur' }],
                    sort: [{ required: true, message: 'Please select a type', trigger: 'blur' }],
                    launchTime: [{ required: true, message: 'Please select the listing time', trigger: 'blur' }],
                    imageUrl: [{ required: true, message: 'Please upload pictures', trigger: 'blur' }],
                    introduction: [{ required: true, message: 'Please enter introduction', trigger: 'blur' }],


                },

            };
        },
        created() {
            this.getData();
        },
        components:{SingleUpload},
        methods: {
            // 获取所有数组(支持分页和关键字查询)
            getData() {
                getProductList(this.query).then(res => {
                    if (res.code === 1){
                        this.tableData = res.data.list;
                        this.pageTotal = res.data.total;
                    }else {
                        console.log('异常');
                    }

                });
            },


            //单个删除
            deleteProductInfo(id){
                this.$confirm('Are you sure you want to delete the selected one？', 'hint', {
                    type: 'warning'
                }).then(action => {
                    if (action === 'confirm') {
                        delProductInfo(id).then(res=>{
                            if (res.code === 1){
                                this.$message.success('successfully delete');
                                this.getData();
                            }else {
                                this.$message.error('fail to delete');
                            }

                        })
                    }
                }).catch(() => {});

            },

            editProductInfo2(row){
                this.spec.productId = row.id;
                getSpecList(this.spec).then(res =>{
                    this.specData = res.data.list;
                });
                this.addVisible2 = true;
            },

            saveSpecInfo(){
                saveAndUpdateSpecInfo(this.spec).then(res =>{
                    if (res.code === 1){
                        getSpecList(this.spec).then(res =>{
                            this.specData = res.data.list;
                        });
                        this.spec.specName = null;
                        this.spec.price = null;
                        this.$message.success('operate successfully');
                    }
                })
            },

            //编辑
            editProductInfo(row){
                this.form = JSON.parse(JSON.stringify(row));
                this.addVisible = true;
            },
            //重置
            reset(){
                this.query = {};
                this.getData();
            },

            getProductInfo(){
                this.getData();
            },

            //添加用户信息
            addUserInfo(){
                this.addVisible = true;
                this.form = {};
            },

            saveAndUpdateProductInfo(){
                this.$refs.addForm.validate(valid => {
                    if (valid) {
                        if (this.form.recommendType === '0'){
                            this.form.recommendType = null;
                        }
                        if (this.form.id == null){
                            saveProductInfo(this.form).then(res =>{
                                if (res.code === 1){
                                    this.getData();
                                    this.addVisible = false;
                                    this.$message.success('operate successfully');
                                }else {
                                    this.$message.error('operate fail');
                                }
                            })
                        }else {
                            updateProductInfo(this.form).then(res =>{
                                if (res.code === 1){
                                    this.getData();
                                    this.addVisible = false;
                                    this.$message.success('operate successfully');
                                }else {
                                    this.$message.error('operate fail');
                                }
                            })
                        }

                    } else {
                        return false;
                    }
                });

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
    li{
        list-style: none;
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
