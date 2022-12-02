<template>
    <div>
        <div class="blogs-box whitebg">

            <div class="news-title">

                <h2>News List</h2>

            </div>

            <div class="blogs-list">

                <ul>

                    <li v-for="(item,index) in data">

                        <h2><a>{{item.title}}</a></h2>

                        <i><a><img style="width: 240px;height: 140px" :src="item.imageUrl"></a></i>



                        <p>{{item.news}}</p>

                        <span><a href="/">发布时间</a><a href="/">{{item.createTime}}</a></span> </li>

                </ul>

            </div>


        </div>
    </div>
</template>

<script>
    import { getNewsList } from '../../api/cake';

    export default {
        name: 'News',
        data(){
            return{
                data:[]
            }
        },
        created() {
            this.getData()
        },
        methods:{
            getData(){
                getNewsList({pageSize:100}).then(res =>{
                    this.data = res.data.list;
                })
            }
        }
    };
</script>

<!--<style scoped>-->

<!--</style>-->
<style src="../../assets/css/base.css" scoped></style>
