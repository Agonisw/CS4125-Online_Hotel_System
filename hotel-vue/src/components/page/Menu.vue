<template>
    <diV>

        <el-form :inline="true"  class="demo-form-inline">
           <el-form-item>
                <el-button type="primary" @click="addMenu()">新增</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addTopMenu()">新增顶级菜单</el-button>
            </el-form-item>
        </el-form>

        <el-table
                :data="tableData"
                style="width: 100%;margin-bottom: 20px;"
                row-key="id"
                border
                stripe
                default-expand-all
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

            <el-table-column prop="name" label="名称" sortable width="180"></el-table-column>
            <el-table-column prop="perms" label="编码权限" sortable width="180"></el-table-column>
            <el-table-column prop="icon" label="图标"></el-table-column>
            <el-table-column prop="type" label="类型">
                <template slot-scope="scope">
                    <el-tag size="small" v-if="scope.row.type === 0">目录</el-tag>
                    <el-tag size="small" v-else-if="scope.row.type === 1" type="success">菜单</el-tag>
                    <el-tag size="small" v-else-if="scope.row.type === 2" type="info">按钮</el-tag>
                </template>
            </el-table-column>

            <el-table-column prop="path" label="菜单URL"></el-table-column>
            <el-table-column prop="component" label="菜单组件"></el-table-column>
            <el-table-column prop="statu" label="状态">
                <template slot-scope="scope">
                    <el-tag type="success" size="small" v-if="scope.row.statu ===1">正常</el-tag>
                    <el-tag type="danger" size="small" v-if="scope.row.statu ===0">禁用</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="button"
                    width="200"
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="text" :disabled="scope.row.type === 2" @click="editMenu2(scope.row)">添加下级</el-button>
                    <el-button type="text" @click="editMenu(scope.row)">编辑</el-button>
                    <el-divider direction="vertical"></el-divider>
                    <el-popconfirm title="这是一段内容确定删除吗？" @confirm="delMenu(scope.row.id)">
                        <el-button slot="reference" type="text">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <!--新增对话框-->
        <el-dialog
                title="提示"
                :visible.sync="addVisible"
                width="600px"
                :before-close="handleClose">

            <el-form :model="form" :rules="formRules" ref="form" label-width="100px" class="demo-form">

                <el-form-item label="上级菜单" prop="parentId">
                    <el-select v-model="form.parentId" placeholder="请选择上级菜单">

                        <template v-for="item in tableData">
                            <el-option :label="item.name" :value="item.id"></el-option>

                            <template v-for="child in item.children">
                                <el-option :label="child.name" :value="child.id">
                                    <span>{{"--" + child.name}}</span>
                                </el-option>
                            </template>
                        </template>

                    </el-select>
                </el-form-item>


                <el-form-item label="菜单名称" prop="name" label-width="100px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="权限编码" prop="perms" label-width="100px">
                    <el-input v-model="form.perms" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="图标" prop="icon" label-width="100px">
                    <el-input v-model="form.icon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="菜单URL" prop="path" label-width="100px">
                    <el-input v-model="form.path" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="菜单组件" prop="component" label-width="100px">
                    <el-input v-model="form.component" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="类型" prop="type" label-width="100px">
                    <el-radio-group v-model="form.type">
                        <el-radio :label=0>目录</el-radio>
                        <el-radio :label=1>菜单</el-radio>
                        <el-radio :label=2>按钮</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="状态" prop="statu" label-width="100px">
                    <el-radio-group v-model="form.statu">
                        <el-radio :label=0>禁用</el-radio>
                        <el-radio :label=1>正常</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="排序号" prop="orderNum" label-width="100px">
                    <el-input-number v-model="form.orderNum" :min="1" label="排序号">1</el-input-number>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="saveMenuInfo('form')">立即创建</el-button>
                    <el-button @click="resetForm('form')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog
                title="提示"
                :visible.sync="addVisible2"
                width="600px"
                :before-close="handleClose">

            <el-form :model="form" :rules="formRules" ref="form" label-width="100px" class="demo-form">

                <el-form-item label="菜单名称" prop="name" label-width="100px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="权限编码" prop="perms" label-width="100px">
                    <el-input v-model="form.perms" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="图标" prop="icon" label-width="100px">
                    <el-input v-model="form.icon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="菜单URL" prop="path" label-width="100px">
                    <el-input v-model="form.path" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="菜单组件" prop="component" label-width="100px">
                    <el-input v-model="form.component" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="类型" prop="type" label-width="100px">
                    <el-radio-group v-model="form.type">
                        <el-radio :label=0>目录</el-radio>
                        <el-radio :label=1>菜单</el-radio>
                        <el-radio :label=2>按钮</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="状态" prop="statu" label-width="100px">
                    <el-radio-group v-model="form.statu">
                        <el-radio :label=0>禁用</el-radio>
                        <el-radio :label=1>正常</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="排序号" prop="orderNum" label-width="100px">
                    <el-input-number v-model="form.orderNum" :min="1" label="排序号">1</el-input-number>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="saveTopMenuInfo2('form')">立即创建</el-button>
                    <el-button @click="resetForm('form')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </diV>
</template>

<script>
    import { delMenuInfo, getMenuList, saveMenuInfo, updateMenuInfo } from '../../api/cake';

    export default {
        name: "Menus",
        data() {
        return {
            form:{},
            addVisible: false,
            addVisible2: false,
            formRules: {
                parentId: [
                    {required: true, message: '请选择上级菜单', trigger: 'blur'}
                ],
                name: [
                    {required: true, message: '请输入名称', trigger: 'blur'}
                ],
                perms: [
                    {required: true, message: '请输入权限编码', trigger: 'blur'}
                ],
                type: [
                    {required: true, message: '请选择状态', trigger: 'blur'}
                ],
                orderNum: [
                    {required: true, message: '请填入排序号', trigger: 'blur'}
                ],
                statu: [
                    {required: true, message: '请选择状态', trigger: 'blur'}
                ]
            },
            tableData: [],
        }
        },
        created() {
          this.getData()
        },
        methods: {
            getData(){
              getMenuList().then(res =>{
                  this.tableData = res.data
              })
            },
            saveMenuInfo(form){
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        if (this.form.id == null){
                            saveMenuInfo(this.form).then(res =>{
                                this.$message.success('操作成功');
                                this.getData();
                                this.addVisible =false
                            })
                        }else {
                            updateMenuInfo(this.form).then(res =>{
                                this.$message({
                                    showClose: true,
                                    message: '恭喜你，操作成功',
                                    type: 'success',
                                    onClose:() =>{
                                        this.getData()
                                    }
                                });
                                this.addVisible =false
                            })
                        }
                        // this.$axios.post('/sys/menu/' + (this.form.id ? 'update':'save'),this.form)
                        //     .then(res =>{
                        //         this.$message({
                        //             showClose: true,
                        //             message: '恭喜你，操作成功',
                        //             type: 'success',
                        //             onClose:() =>{
                        //                 this.getData()
                        //             }
                        //         });
                        //         this.addVisible =false
                        // })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            editMenu(row) {
                console.log(row);
                this.form = JSON.parse(JSON.stringify(row));
                this.addVisible = true
            },

            editMenu2(row){
                this.form = {};
                this.form.parentId = row.id;
                this.addVisible2 = true;
            },

            handleClose() {
               this.resetForm('form');
               this.addVisible = false;
               this.addVisible2 = false;
            },
            resetForm(form) {
                this.$refs[form].resetFields();
                this.addVisible=false
                this.form={}
            },
            delMenu(id){
                delMenuInfo(id).then(res =>{
                    if (res.code === 1){
                        this.$message.success('删除成功');
                        this.getData();
                    }else {
                        this.$message.error(res.msg)
                    }
                })
            },

            addTopMenu(){
                this.form = {};
                this.form.type = 0;
                this.form.statu = 1;
                this.form.parentId = 0;
                this.addVisible2 = true;
            },

            addMenu(){
                this.form = {};
                this.addVisible = true;
            },

            saveTopMenuInfo2(form){
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        if (this.form.id == null){
                            saveMenuInfo(this.form).then(res =>{
                                this.$message({
                                    showClose: true,
                                    message: '恭喜你，操作成功',
                                    type: 'success',
                                });
                                this.getData()
                                this.addVisible2 =false
                            })
                        }else {
                            updateMenuInfo(this.form).then(res =>{
                                this.$message({
                                    showClose: true,
                                    message: '恭喜你，操作成功',
                                    type: 'success',
                                });
                                this.getData()
                                this.addVisible2 =false
                            })
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>
