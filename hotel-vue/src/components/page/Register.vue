<template>
    <div id="contentCon">
        <form style="height: 800px">
            <ul>
                <p>userName：</p>
                <input type="text" v-model="form.username" style="margin-left:45px" placeholder="please input userName">
            </ul>
            <ul class="box02">
                <p>password：</p>
                <input type="password" style="margin-left:50px" v-model="form.password" placeholder="please input password">
            </ul>
            <ul class="box02">
                <p>confirmPassword：</p>
                <input type="password"  v-model="form.repatePassword" placeholder="please confirm password">
            </ul>
            <ul class="box02">
                <p>realName：</p>
                <input type="text"  style="margin-left:50px" v-model="form.realName" placeholder="please input realName">
            </ul>
            <ul class="box02">
                <p>your-phone：</p>
                <input type="text" style="margin-left:34px" v-model="form.phone" placeholder="please input phone">
            </ul>
            <ul class="box02">
                <p>your-email：</p>
                <input type="text" style="margin-left:40px"  v-model="form.email" placeholder="please input email">
            </ul>
            <ul class="box02">
                <p>your-address：</p>
                <input type="text"  style="margin-left:24px"   v-model="form.address" placeholder="please input address">
            </ul>
            <ul class="box02">
                <p>image：</p>
                <single-upload style="margin-left:144px"  v-model="form.imageUrl"></single-upload>
            </ul>
<!--            <li>-->
<!--                <input type="radio">-->
<!--                <p>我已阅读并同意</p>-->
<!--                <a href="#">《甜爱服务协议》</a>-->
<!--            </li>-->
            <a @click="register">Register</a>
        </form>
    </div>
</template>

<script>



import SingleUpload from '../common/singleUpload3';
import { registerUser, saveAndUpdateUserInfo } from '../../api/cake';
export default {
    components: { SingleUpload },
    data(){
      return{
          form:{},
      }
    },
    methods:{
        register(){
            if (this.form.password !== this.form.repatePassword){
                this.$message.warning('两次输入密码不一致');
                return false;
            }
            this.form.userType = 2;
            registerUser(this.form).then(res =>{
                if (res.code === 1){
                    this.$message.success('注册成功,即将跳转登录界面');
                    setTimeout(() =>{
                        sessionStorage.setItem('userStatus','ok');
                        this.$router.push('/login')
                    },2000)
                }else {
                    this.$message.warning(res.msg)
                }
            })
        }
    }

};
</script>

<style scoped src="../../assets/css/register.css" />
