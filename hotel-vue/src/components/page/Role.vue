<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"/>角色管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.roleName" placeholder="请输入角色名称" class="handle-input mr10"/>
                <el-button type="primary" icon="el-icon-search" @click="getData">搜索</el-button>
                <el-button type="primary" icon="el-icon-search" @click="reset">重置</el-button>
            </div>
            <div class="handle-box">
                <el-button style="float:left"
                           type="primary"
                           icon="el-icon-success"
                           class="handle-del mr10"
                           @click="addRoleInfo"
                >添加</el-button>
            </div>

            <el-table
                    :data="tableData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
            >
                <el-table-column type="selection" width="55" align="center"/>
                <el-table-column type="index" label="序号" width="55" align="center"/>
                <el-table-column prop="name" label="角色名称" align="center"/>
                <el-table-column prop="code" label="唯一编码" show-overflow-tooltip/>
                <el-table-column prop="remark" label="描述" show-overflow-tooltip/>
                <el-table-column prop="statu" label="状态">
                    <template slot-scope="scope">
                        <el-tag type="success" size="small" v-if="scope.row.statu ===1">正常</el-tag>
                        <el-tag type="danger" size="small" v-if="scope.row.statu ===0">禁用</el-tag>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="button"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button type="text" @click="distributionAuth(scope.row.id)">分配权限</el-button>
                        <el-divider direction="vertical"/>
                        <el-button type="text" @click="editRoleInfo(scope.row)">编辑</el-button>
                        <el-divider direction="vertical"/>
                        <el-popconfirm title="这是一段内容确定删除吗？" @confirm="delRoleInfo(scope.row.id)">
                            <el-button slot="reference" type="text">删除</el-button>
                        </el-popconfirm>
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
                />
            </div>
        </div>
        <!-- 添加弹出框 -->
        <el-dialog
                title="分配权限"
                :visible.sync="addVisible"
                width="600px">
            <el-form :model="form">

                <el-tree
                        :data="perTreeData"
                        show-checkbox
                        node-key="id"
                        :check-strictly="this.form.roleId !=null"
                        ref="permTree"
                        @check="menuChange"
                        :default-expand-all=true
                        :props="defaultProps">
                </el-tree>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="permDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveRoleMenu('permForm')">确 定</el-button>
                </span>

        </el-dialog>

        <el-dialog title="角色信息" :modal="false"  :visible.sync="addVisible2" width="33%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form" >
                <el-form-item label="角色名称" prop="name" label-width="100px">
                    <el-input v-model="form.name" autocomplete="off"/>
                </el-form-item>

                <el-form-item label="唯一编码" prop="code" label-width="100px">
                    <el-input v-model="form.code" autocomplete="off"/>
                </el-form-item>

                <el-form-item label="描述" prop="remark" label-width="100px">
                    <el-input v-model="form.remark" autocomplete="off"/>
                </el-form-item>

                <el-form-item label="状态" prop="statu" label-width="100px">
                    <el-radio-group v-model="form.statu">
                        <el-radio :label=0>禁用</el-radio>
                        <el-radio :label=1>正常</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="saveAndUpdateRoleInfo">确 定</el-button>
            </span>
        </el-dialog>


    </div>
</template>

<script>


    import {
        delRoleInfo,
        getMenuList,
        getRoleList,
        getRoleMenuByRoleId,
        getRoleMenuByUserId,
        saveAndUpdateRoleInfo,
        saveRoleInfo,
        saveRoleMenu,
        saveRolePerm, updateRoleInfo
    } from '../../api/cake';
    export default {
        name: 'role',
        data() {
            return {
                perTreeData:{},
                query: {
                    pageNum : 1,
                    pageSize : 10
                },
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                tableData: [],
                multipleSelection: [],
                addVisible: false,
                addVisible2: false,
                pageTotal: 0,
                defKeys:[],
                systemMenuTree:[],
                form: {},
                rules: {
                    roleName: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    accessType: [
                        { required: true, message: '请选择权限名称', trigger: 'blur' },
                    ],
                    remark: [
                        { required: true, message: '请输入角色描述信息', trigger: 'blur' },
                    ],
                }

            };
        },
        created() {
            this.getData();
        },
        methods: {

            menuChange (val) {
                console.log(val)
                // // 节点已被选中
                // if (this.menuids.indexOf(val.id) !== -1) {
                //     this.menuids = []
                // } else {
                //     this.menuids = [1, 4, 42, 43] // 实际情况不能这样直接添加
                // }
                // this.$refs.menu.setCheckedKeys(this.menuids) //setCheckedKeys：通过 keys 设置目前勾选的节点，使用此方法必须设置 node-key 属性
            },



            // 获取所有数组(支持分页和关键字查询)
            getData() {
                getRoleList(this.query).then(res => {
                    console.log(res);
                    this.tableData = res.data.list;
                    this.pageTotal = res.data.total;
                });

                getMenuList().then(res => {
                    this.perTreeData = res.data
                })
            },

            /**
             * 回显权限信息
             * @param id
             */
            distributionAuth(id) {
                this.addVisible = true;
                this.form = {};
                this.form.roleId = id;
                getRoleMenuByRoleId(id).then(res => {
                    this.$refs.permTree.setCheckedKeys(res.data);
                    this.permForm = res.data
                })
            },

            saveRoleMenu(){
                this.form.menuIdList = [
                    ...this.$refs.permTree.
                    getHalfCheckedKeys(),
                    ...this.$refs.permTree.
                    getCheckedKeys()
              ];
              saveRoleMenu(this.form).then(res =>{
                  this.$message.success('成功');
                  this.addVisible = false;
              })
            },

            //添加角色信息
            addRoleInfo(){
                this.form = {};
                this.addVisible2 = true;
            },

            editRoleInfo(row){
                this.form = JSON.parse(JSON.stringify(row));
                this.addVisible2 = true;
            },

            saveAndUpdateRoleInfo(){
                if (this.form.id == null){
                    saveRoleInfo(this.form).then(res =>{
                        this.$message.success('添加成功');
                        getRoleList(this.query).then(res => {
                            this.tableData = res.data.list;
                            this.pageTotal = res.data.total;
                        });
                        this.addVisible2 = false;
                    })
                }else {
                    updateRoleInfo(this.form).then(res =>{
                        this.$message.success('添加成功');
                        getRoleList(this.query).then(res => {
                            this.tableData = res.data.list;
                            this.pageTotal = res.data.total;
                        });
                        this.addVisible2 = false;
                    })
                }

            },

            clearAddForm(){
                this.addVisible = false;
                this.addVisible2 = false;
            },


            closeDialog(){
                this.addVisible = false;
                this.$refs.systemMenuTree.setCheckedKeys([]);
                this.defKeys = [];
                this.form = {};
            },

            //重置
            reset(){
                this.query = {};
                this.getData();
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },

            //单个删除
            delRoleInfo(id){
               this.$confirm('确定要删除这个角色信息吗?','提示',{
                   type:'warning'
               }).then(action =>{
                   if (action === 'confirm'){
                       delRoleInfo(id).then(res =>{
                           if (res.code === 1){
                               this.$message.success('删除成功!');
                               this.getData();
                           }else{
                               this.$message.error(res.data.msg);
                           }
                       })
                   }
               })
            },

        }
    };
</script>

<style scoped>
    .handle-box {
        margin-top: 20px;
        margin-bottom: 10px;
    }
    /deep/ .el-upload--text{
        width: 98px !important;
        height: 34px !important;
        display: inline-grid;
        float: left;
        margin-right: 10px;
        border: 1px solid #DCDFE6;
        border-radius: 3px;
    }
    .el-upload--text .inline-block {
        display: inline-block;
        border: 0px dashed #d9d9d9
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table {
        width: 100%;
        font-size: 14px;
    }
    .red {
        color: #ff0000;
    }
    .mr10 {
        margin-right: 10px;
    }
    .crop-demo{
        display: inline-block;;
        align-items: flex-end;
        margin-right: 10px;
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
