<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 用户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input class="handle-input mr10" v-model="query.realName" placeholder="请输入真实姓名"></el-input>
                <el-button type="primary" icon="el-icon-search" style="margin-left: 20px" @click="getUserInfo">查询</el-button>
                <el-button type="primary" icon="el-icon-refresh" @click="reset">重置</el-button>
            </div>
            <div class="handle-box" style="display: inline-flex;">
                <el-button style="margin-bottom: 10px"
                           type="primary"
                           icon="el-icon-plus"
                           class="handle-del mr10"
                           @click="addUserInfo"
                >新增</el-button>
            </div>

            <el-table
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    border
                    stripe
            >

                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column label="头像" width="50">
                    <template slot-scope="scope">
                        <el-avatar size="small" :src="scope.row.imageUrl"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column prop="username" label="用户名" width="120"></el-table-column>
                <el-table-column prop="code" label="角色名称">
                    <template slot-scope="scope">
                        <el-tag size="small" type="info" v-for="(item,index) in scope.row.roleList" :key="index">{{item}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="realName" label="姓名"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="phone" label="电话"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column prop="userType" label="用户类型">
                    <template slot-scope="scope">
                        <span v-if="scope.row.userType === '1'">管理用户</span>
                        <span v-if="scope.row.userType === '2'">前台用户</span>
                    </template>
                </el-table-column>
                <el-table-column prop="icon" width="260px" label="操作">
                    <template slot-scope="scope">
                        <el-button type="text" @click="getUserRoleInfo(scope.row.id)">分配角色</el-button>
                        <el-divider direction="vertical"></el-divider>

                        <el-button type="text" @click="editUserInfo(scope.row)">编辑</el-button>
                        <el-divider direction="vertical"></el-divider>
                        <el-button type="text" @click="editPassword(scope.row.id)">更新密码</el-button>
                        <el-divider direction="vertical"></el-divider>
                         <el-button type="text" @click="delUserInfo(scope.row.id)">删除</el-button>


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
        <el-dialog title="用户信息" :modal="false"  :visible.sync="addVisible" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form" >
                <el-form-item label="用户名" prop="username" label-width="100px">
                    <el-input v-model="form.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" v-if="form.id == null" prop="username" label-width="100px">
                    <el-input v-model="form.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱"  prop="email" label-width="100px">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名"  prop="email" label-width="100px">
                    <el-input v-model="form.realName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址"  prop="email" label-width="100px">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话"  prop="email" label-width="100px">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="头像"  prop="email" label-width="100px">
                    <single-upload v-model="form.imageUrl"></single-upload>
                </el-form-item>
                <el-form-item label="用户类型"  label-width="100px">
                        <el-radio label="1" v-model="form.userType">管理用户</el-radio>
                        <el-radio label="2" v-model="form.userType">前台用户</el-radio>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="clearAddForm">取 消</el-button>
                <el-button type="primary" @click="saveAndUpdateUserInfo">确 定</el-button>
            </span>
        </el-dialog>


        <el-dialog title="分配角色" :visible.sync="addVisible2" width="600px">

            <el-form :model="form">
                <el-tree
                        :data="roleTreeData"
                        show-checkbox
                        ref="roleTree"
                        :check-strictly=checkStrictly
                        node-key="id"
                        :default-expand-all=true
                        :props="defaultProps">
                </el-tree>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="roleDialogFormVisible=false">取 消</el-button>
                <el-button type="primary" @click="saveUserRoleInfo('roleForm')">确 定</el-button>
            </div>
        </el-dialog>


        <el-dialog title="更新密码" :modal="false"  :visible.sync="addVisible3" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form" >
                <el-form-item label="新密码" prop="username" label-width="100px">
                    <el-input v-model="form.password" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="clearAddForm">取 消</el-button>
                <el-button type="primary" @click="updatePassword">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>

    import {
        delUserInfo,
        getRoleList,
        getUserList,
        getUserRoleInfoByUserId,
        saveUserInfo,
        saveUserRoleInfo, updatePassword, updateUserInfo
    } from '../../api/cake';
    import SingleUpload from '../common/singleUpload3';

    export default {
        name: 'User',
        components: { SingleUpload },
        data() {
            return {
                query: {
                    pageNum:1,
                    pageSize:10,
                    realName:null,
                },
                tableData:[],
                addVisible: false,
                addVisible2:false,
                addVisible3:false,
                form:{},
                pageTotal: 0,
                options:[],
                options2:[],
                roleDialogFormVisible: false,
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                roleForm: {},
                roleTreeData:  [],
                treeCheckedKeys: [],
                checkStrictly: true

            };
        },
        created() {
            this.getData();
        },
        methods: {
            // 获取所有数组(支持分页和关键字查询)
            getData() {
                getUserList(this.query).then(res => {
                    if (res.code === 1){
                        this.tableData = res.data.list;
                        this.pageTotal = res.data.total;
                    }else {
                        console.log('异常');
                    }
                });

                getRoleList().then(res =>{
                    this.roleTreeData = res.data.list
                })
            },

            /**
             * 获取用户的角色信息
             * @param id
             */
            getUserRoleInfo(id){
                this.form = {};
                this.addVisible2 = true;
                this.form.userId = id;
                getUserRoleInfoByUserId(id).then(res =>{
                    const roleIds = res.data;
                    this.$refs.roleTree.setCheckedKeys(roleIds);
                })
            },

            /**
             * 保存用户的角色信息，即给用户分配角色
             * @param id
             */
            saveUserRoleInfo(){
                // 获取选中的角色id
                this.form.roleIdList = this.$refs.roleTree.getCheckedKeys();
                saveUserRoleInfo(this.form).then(res =>{
                    if (res.code === 1){
                        this.$message.success('授权成功');
                        this.getData();
                        this.addVisible2 = false;
                    }
                })
            },

            //编辑
            editUserInfo(row){
                this.form = JSON.parse(JSON.stringify(row));
                this.addVisible = true;
            },

            /**
             * 删除用户信息
             * @param id
             */
            delUserInfo(id){
                delUserInfo(id).then(res =>{
                    if (res.code === 1){
                        this.$message.success('删除成功');
                        this.getData();
                    }
                })
            },

            editPassword(id){
                this.form = {};
                this.form.id = id;
                this.addVisible3 = true;

            },

            /**
             * 更新密码
             */
            updatePassword(){
                updatePassword(this.form).then(res =>{
                    if (res.code === 1){
                        this.$message.success('更新成功');
                        this.addVisible3 = false;
                        this.getData();
                    }else {
                        this.$message.error('更新失败')
                    }
                })
            },





            roleHandle (id) {
                this.roleDialogFormVisible = true;
                getUserInfoById(id).then(res =>{
                    this.roleForm = res.data
                    console.log(this.roleForm)
                    let roleIds = [];
                    res.data.sysRole.forEach(row => {
                        roleIds.push(row.id)
                    });
                    this.$refs.roleTree.setCheckedKeys(roleIds)
                });
            },


            submitRoleHandle(formName) {
                const roleIds = this.$refs.roleTree.getCheckedKeys();

                console.log(roleIds);
                saveUserRoleInfo(this.roleForm.id,roleIds).then(res =>{
                    this.$message.success('恭喜你，操作成功');
                    this.getData();
                    this.roleDialogFormVisible = false
                });
            },




            //重置
            reset(){
                this.query.realName= null;
                this.getData();
            },

            getUserInfo(){
                this.getData();
            },

            //添加用户信息
            addUserInfo(){
                this.form = {};
                this.addVisible = true;
            },

            saveAndUpdateUserInfo(){
                if (this.form.id == null){
                    saveUserInfo(this.form).then(res =>{
                        if (res.code === 1){
                            this.getData();
                            this.$message.success('操作成功');
                            this.addVisible = false;
                        }else {
                            this.$message.error('操作失败')
                        }
                    })
                }else {
                    updateUserInfo(this.form).then(res =>{
                        if (res.code === 1){
                            this.getData();
                            this.$message.success('操作成功');
                            this.addVisible = false;
                        }else {
                            this.$message.error('操作失败')
                        }
                    })
                }


            },


            clearAddForm(){
                this.addVisible = false;
                this.addVisible2 = false;
                this.addVisible3 = false;
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
