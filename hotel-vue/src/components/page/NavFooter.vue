<template>
    <footer class="footer">



        <div class="footer-links container3">

            <dl class="col-links">

                <dt>About Us</dt>

                <dd><a>contact us</a></dd>

                <dd><a>business cooperation</a></dd>

                <dd><a>talent recruitment</a></dd>

            </dl>

            <dl class="col-links">

                <dt>means of exchange</dt>

                <dd><a>Melon and fruit trade</a></dd>

                <dd><a>Vegetable recycling</a></dd>

                <!--<dd><a href="https://www.suhuishou.com/about/7.html">合作商家回收</a></dd>-->

            </dl>

            <dl class="col-links">

                <dt>help center</dt>

                <dd><a>standard question</a></dd>

                <dd><a>mailing address</a></dd>

                <dd><a>Recycling Notice</a></dd>

            </dl>

            <dl class="col-links">

                <dt>service center</dt>

                <dd><a href="https://www.suhuishou.com/about/10.html">Quality Inspection Description</a></dd>

                <dd><a href="https://www.suhuishou.com/about/19.html">Clause of service</a></dd>

            </dl>

            <dl class="col-links">

                <dt>Website Updates</dt>

                <dd><a>standard question</a></dd>

                <dd><a>Recall Information</a></dd>

                <dd><a>Mobile Encyclopedia</a></dd>

                <dd><a>site navigation</a></dd>

            </dl>

            <dl class="col-contact">

                <dt><a href="javascript:;" class="baiduline" @click="toChat()">online customer</a><p>Monday to Sunday <br>From 9 a.m. to 21 a.m</p></dt>

                <dd>Mailing address: Tianjin Chengjian University, Zhangjiawo Town, Xiqing District, Tianjin</dd>

                <dd>13788888888</dd>

                <dd>To: Xiao Zhang (received)</dd>

            </dl>

        </div>


        <div class="copyright">

<!--            <div class="container3">-->

<!--                <p>深圳市南山区 高新科技园 大冲国际中心17A1（室）<a href="https://beian.miit.gov.cn" rel="nofollow">粤ICP备16015090号</a><a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44030502004776" rel="nofollow">粤公网安备 44030502004776 号</a><a href="https://beian.miit.gov.cn/#/Integrated/index" rel="nofollow">增值电信业务经营许可证 粤B2-20211546</a></p>-->


<!--            </div>-->
<!--            <div class="container3"><p style="text-align: left;">© 2005-2021 深圳协腾网络电子科技有限公司 版权所有，并保留所有权利。 </p></div>-->

        </div>

    </footer>
</template>

<script>
    export default {
        name: 'NavFooter',
        data(){
            return{

            }
        },
        methods:{
            toChat(){
                console.log('hh')
                sessionStorage.setItem("userStatus","1");
                this.$router.push('/chat')
            }
        }
    };
</script>

<style scoped>
    .footer {
        width: 100%;
        background-color: #333;
    }
    footer{
        display: block;
    }
    *, :after, :before {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }
    .baiduline a {
        display: inline-block;
        color: #666;
        position: fixed;
        right: 20px;
        box-shadow: #ccc 0 0 5px;
        background: url(../../assets/img/kefu2.png) -10px -10px #fff no-repeat;
        background-size: 100px;
        width: 80px;
        height: 80px;
        top: 80%;
    }
    .baiduline .desc {
        width: 90px;
        height: 60px;
        position: absolute;
        left: -106px;
        top: 20px;
        border: 1px solid #e0e0e0;
        padding: 5px;
        background-color: #fff;
        display: none;
    }
    .baiduline .desc p {
        font-size: 12px;
        margin: 5px 0 0 5px;
    }
    p {
        margin: 0;
        padding: 0;
    }
    .footer .footer-top.container3 {
        padding-left: 0;
        padding-right: 0;
    }
    .container3 {
        width: 1226px;
        margin-right: auto;
        margin-left: auto;
    }
    .footer .footer-top ul {
        display: flex;
        flex-wrap: wrap;
        margin: 0;
        padding: 20px 0;
        border-bottom: solid #666 1px;
    }
    ul {
        display: block;
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
    }
    .footer .footer-top ul li {
        width: 20%;
        text-align: center;
        position: relative;
    }
    li {
        list-style: none;
    }
    .footer .footer-top ul li a {
        font-size: 16px;
        line-height: 40px;
        padding-left: 0px;
    }
    .footer a {
        color: #efefef;
    }
    a {
        color: #666;
        text-decoration: none;
        background-color: transparent;
        -webkit-text-decoration-skip: objects;
    }

    .footer-links {
        display: flex;
        flex-wrap: wrap;
        padding: 30px 0;
        border-bottom: solid #666 1px;
    }
    .container3 {
        width: 1226px;
        margin-right: auto;
        margin-left: auto;
    }

    .footer-links dl {
        width: 14%;
    }
    address, dl, ol, ul {
        margin: 0;
        padding: 0;
    }
    dl {
        display: block;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
    }
    .footer-links dl dt {
        font-size: 16px;
        font-weight: normal;
        margin-bottom: 15px;
        color: #fff;
    }
    dt {
        display: block;
    }
    .footer-links dl dd a {
        font-size: 12px;
    }
    .footer-links .col-contact {
        width: 27%;
        border-left: solid #cdcdcd 1px;
        padding-left: 3%;
    }.footer-links .col-contact dt {
         display: flex;
     }
    .footer-links dl dt {
        font-size: 16px;
        font-weight: normal;
        margin-bottom: 15px;
        color: #fff;
    }
    .footer-links .col-contact dt a {
        display: inline-block;
        border-radius: 50px;
        padding-left: 40px;
        color: #fff;
        line-height: 45px;
        width: 150px;
        height: 45px;
        text-align: center;
        font-size: 14px;
        background: #e84947 url(../../assets/img/kefu.png) 20px center no-repeat;
        background-size: 36px;
    }
    .footer-links .col-contact dt p {
        font-size: 14px;
        margin-left: 30px;
    }

    p {
        margin: 0;
        padding: 0;
    }
    .footer-links .col-contact dd {
        color: #ddd;
    }
    .footer-links dl dd {
        margin: 0;
    }
    .footer .flink.container3 {
        padding-left: 0;
        padding-right: 0;
    }
    .footer .flink {
        padding: 10px;
        font-size: 12px;
        color: #ddd;
    }
    .footer .flink a {
        margin-left: 10px;
        line-height: 30px;
    }
    .copyright {
        padding: 15px 0;
        background: #282828;
    }
    .copyright .container3 {
        display: flex;
        padding: 0;
    }
    .copyright p {
        line-height: 30px;
        color: #ddd;
    }
    .copyright a {
        color: #ddd;
        margin-left: 10px;
    }



</style>
