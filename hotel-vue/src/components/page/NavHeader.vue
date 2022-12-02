<template>

    <div id="headCon" style="height: 140px">
        <div  v-if="this.$route.path !=='/register'">
            <p style="margin-left: 160px">Eisen Hotel your home~</p>
            <ol>
                <li><a @click="toLogin()" v-if="$store.state.userId == null">please login</a></li>
                <li><a @click="toRegister()" v-if="$store.state.userId  == null">register</a></li>
                <li  v-if="$store.state.userId">
                    <a>{{user.realName}}</a>
                </li>
                <a  v-if="$store.state.userId" @click="toOrder()">My Order</a>
                <a  v-if="$store.state.userId" @click="toCar()" class="shoppingCar">
                    <i></i>
                    <span>Car</span>
                </a>
                <a v-if="$store.state.userId" @click="toLogOut">LogOut</a>
                <div class="user-avator" v-if="$store.state.userId"> <img :src="user.imageUrl" /></div>
            </ol>
        </div>
            <ul  v-if="this.$route.path !=='/register'">
            <a @click="toIndex()"><li></li></a>
            <ol>
                <li><a @click="toIndex()">Home</a></li>
<!--                <li><a @click="toWestern()">西式甜品</a></li>-->
<!--                <li><a @click="toSweetVideo()">甜品视频</a></li>-->
                <li><a @click="toNews">LatestNews</a></li>
<!--                <li><a @click="toAll">所有甜品</a></li>-->
<!--                <li class="search">-->
<!--                    <input value="提拉米苏"><button></button>-->
<!--                </li>-->
            </ol>
        </ul>
            <ol  v-if="this.$route.path !=='/register'" ></ol>




<!--           注册头部-->
            <ul v-else>
                <a @click="toIndex"><li></li></a>
                <p style="margin-left: 130px;margin-top: 13px;font-size: 30px">Please Register</p>
            </ul>
        </div>

</template>

<script>


    import { getUserInfoBySecurityContextHolder, getUserList } from '../../api/cake';

    export default {
        name: 'NavHeader',
        data(){
            return{
                user:{}
            }
        },
        created() {
            if (this.$store.state.userId){
                 this.getData();
                }
        },

        methods:{

            getData(){
                getUserInfoBySecurityContextHolder().then(res =>{
                    this.user = res.data;
                })
            },

            toIndex(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/index')
            },

            toWestern(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/western')
            },
            toLogin(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/login')
            },
            toRegister(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/register')
            },
            toSweetVideo(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/sweet-video')
            },
            toAll(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/all')
            },
            toOrder(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/real-order')
            },
            toCar(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/car')
            },
            toNews(){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/news')
            },
            toLogOut(){
                this.$router.push('/index');
                sessionStorage.clear();
                window.location.reload();
            },
        }
    };
</script>
<style scoped>
    .user-avator {
        /*margin-left: 20px;*/
        padding-left: 20px;
        position: absolute;
        left: 40px;
        cursor: pointer;
    }
    .user-avator img {
        display: block;
        width: 70px;
        height: 70px;
        border-radius: 50%;
    }
    a, a:visited {
        color: #E54028;
        text-decoration: none;
    }
    a:hover {
        color: #c22d18;
        cursor: pointer;
    }
    *{
        margin: 0;
        padding: 0;
    }
    body{
        height: 6000px;
    }
    .lift-nav{
        position: fixed;
        top: 100px;
        left: 30px;
        display: none;
    }
    .lift-nav li{
        width: 30px;
        height: 30px;
        text-align: center;
        line-height: 30px;
        color: #fff;
        padding: 10px 10px;
        margin-bottom: 10px;
        background: skyblue;
        cursor: pointer;
    }

    .lift-nav li.current{
        background:#fb0000;
    }

    /*.t1,.t2,.t3,.t4,.t5,.t6{
        width: 800px;
        height: 400px;
        text-align: center;
        line-height: 400px;
        background: skyblue;
        margin: 100px auto;
        font-size: 30px;
        color: #fff;
    }
    .t1{
        margin-top: 200px;
    }
    .t2{
        background: pink;
    }
    .t3{
        background: tomato;
    }
    .t4{
        background: grey;
    }
    .t5{
        background: yellow;
    }
    .t6{
        background: seagreen;
    }*/


</style>
<style scoped src="../../assets/css/index.css" />
