<template>
    <div class="comment" :id="`comment${comment.id}`">
        <div class="comment-head">
            <div class="user-avatar"><img :src="comment.imageName" alt=""></div>
            <div class="head-right">
                    <div style="display: flex;align-items: center;justify-content: space-between;">
                        <div>
                            <span class="from-user user-name" style="color: black">{{comment.realName}}</span>
                            <span class="to-user" ><span style="margin: 0 5px;">|</span><span class="user-name">来自{{comment.address}}的网友</span></span>
                        </div>
                        <div style="font-size: 13px;">
<!--                            <span style="color: #9c9c9c;margin-right: 20px;">{{comment.createTime}}</span>-->
                            <span  v-if="this.$store.state.user.id !==comment.userId" @click="openComment(comment.id)" style="cursor: pointer;color: #67c23a">回复</span>
                            <span v-else style="margin-right: 10px;color: red;cursor: pointer;position: absolute" @click="delComment(comment.id)">删除</span>
                        </div>
                    </div>
            </div>
        </div>
        <div class="comment-body">
            <div class="content-text">
                <p>{{comment.content}}</p>
            </div>
<!--            <div v-if="showCommentEditor" style="display: inline-flex;position: absolute;left: 400px">-->
<!--                <el-input v-model="form.content" style="width: 400px" ></el-input>-->
<!--                <el-button type="success" @click="replyComment()">评论</el-button>-->
<!--            </div>-->
            <slot></slot>
        </div>
        <el-dialog title="回复" :modal="false"  :visible.sync="addVisible" width="43%" :before-close="clearAddForm">
            <el-form label-position="left"  ref="addForm" label-width="80px"  :model="form" >
                <el-form-item label="内容">
                    <el-input v-model="form.content"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="clearAddForm">取 消</el-button>
                <el-button type="primary" @click="replyComment()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import commentMessageEditor from 'comment-message-editor'
    import {deleteCommentInfo, saveAndUpdateCommentInfo} from "../../api/cake";
    import utilMethod from '../../utils/utilMethod';
    export default {
        name: "comment",
        props: {
          comment: {
              type: Object
          }
        },
        data(){
          return{
              showCommentEditor: false,
              form:{},
              addVisible:false,
          }
        },

        computed:{
            userId(){
                return sessionStorage.getItem("userId");
            },
        },

        watch:{
            showCommentEditor(value) {
                if (value) {
                    document.body.addEventListener('click', this.close)
                } else {
                    document.body.removeEventListener('click', this.close)
                }
            }
        },
        components: {
            commentMessageEditor
        },
        methods: {
            clearAddForm(){
                this.addVisible = false
            },
            openComment(id){
                console.log('回复');
                if (!this.$store.state.user.id){
                    this.$message.warning('请登录');
                    return false;
                }
                this.addVisible = true;
                console.log(this.showCommentEditor);
                this.form.pid = id;
            },

            replyComment(){
                this.form.userId = this.$store.state.user.id;
                this.form.productId = this.$route.params.id;
                saveAndUpdateCommentInfo(this.form).then(res =>{
                    if (res.code === 1){
                        this.$message.success('评论成功');
                        this.form.content = null;
                        this.addVisible = false;
                        utilMethod.$emit('success', "1");
                    }
                })
            },


            delComment(id){
                this.$confirm('确定要删除评论的吗','提示',{
                    type:'warning',
                }).then(action =>{
                    if (action === 'confirm'){
                        deleteCommentInfo(id).then(res =>{
                            if (res.code ===1){
                                this.form.comment = null;
                                this.$message.success('删除成功')
                                utilMethod.$emit('success','1')
                            }else{
                            }
                        })
                    }
                })
            },


            close(){
                this.showCommentEditor = false
            }
        }
    }
</script>

<style scoped>
    .comment{
        margin: 20px 100px;
    }
    .comment-head{
        display: flex;
    }
    .head-right{
        flex: 1;
    }

    .user-name{
        color: #8fd0cc;
    }
    .comment-body{
        padding-left: 80px;
    }
    .content-text{
        /*padding-bottom: 30px;*/
        margin-bottom: 30px;
        font-size: 14px;
        color: #9c9c9c;
        line-height: 1.3rem;
    }
    .user-avatar{
        width: 50px;
        height: 50px;
        margin-right: 30px;

    }
    img{
        width: 100%;
        height: 100%;
        border-radius: 100%;
    }
    @media (max-width: 600px){
        .comment-body{
            padding-left: 15px;

        }
        .content-text{
            margin-top: 10px;
        }
        .user-avatar{
            margin-right: 10px;
        }
    }
</style>
