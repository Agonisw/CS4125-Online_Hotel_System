<template>
    <div id="contentCon">

        <div class="French" style="padding-bottom: 20px;margin-top: 20px">
            <div>
                <span class="lx">Type</span>
                <el-radio-group v-model="sort" @change="changeType">
                    <el-radio :label="0" style="margin-left: 20px">All</el-radio>
                    <el-radio :label="1" style="margin-left: 20px">PresidentialSuite</el-radio>
                    <el-radio :label="2" style="margin-left: 20px">LightLuxuryHotel</el-radio>
                    <el-radio :label="3" style="margin-left: 20px">HomeStayHotel</el-radio>
                    <el-radio :label="4" style="margin-left: 20px">YouthHostel</el-radio>
                </el-radio-group>
            </div>
            <ol>
                <li class="su" v-for="(item,index) in frenchList" style="cursor: pointer" @click="toDetail(item.id)">
                    <img style="display: block;width: 285px;height: 400px" :src="item.imageUrl">
                    <!--                        <a :style="{ 'background-image': 'url(' + imageUrl + ')','background-repeat':'no-repeat','background-size':'cover','background-size': '100% 100%' }"></a>-->
                    <p>{{item.name}}</p>
                    <div>
                        <a class="shoucang"></a>
                        <a class="car"></a>
                        <a class="btn">Purchase</a>
                    </div>
                </li>
            </ol>
        </div>
    </div>
</template>

<script>
    import { getProductList } from '../../api/cake';

    export default {
        name: 'All',
        data(){
            return{
                frenchList:[],
                query:{
                    pageSize:200,
                },
                sort:0,
            }
        },
        created() {
            this.getFrenchList();
        },
        methods: {
            getFrenchList() {
                /**
                 * 法式
                 */
                getProductList(this.query).then(res => {
                    this.frenchList = res.data.list;
                });
            },

            changeType:function(val){
                if (this.sort === 0){
                   this.query.sort = null;
                }else {
                    this.query.sort = this.sort;
                }
                this.getFrenchList();
            },

            toDetail(id){
                sessionStorage.setItem('userStatus','isOk');
                this.$router.push('/detail/'+id)
            },
        }
    };
</script>

<style scoped src="../../assets/css/western.css" />
<style scoped>
    .French li:nth-child(4n-2){
        margin: 0 20px;
    }

    .French li:nth-child(4n-1){
        margin-right:20px;
    }
    .French li:nth-child(n+5){
        margin-top:20px;
    }
    .French .lx{
        list-style: none;
        font-size: 14px;
        text-decoration: none;
        font-family: "\5FAE\8F6F\96C5\9ED1", "\5B8B\4F53", "\9ED1\4F53",serif;
        font-weight: normal;

    }
</style>
