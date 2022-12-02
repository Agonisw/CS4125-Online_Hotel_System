<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
        >
            <template v-for="item in menuList">
                <template v-if="item.children">
<!--                    一级菜单-->
                    <el-submenu :index="item.path" :key="item.path">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.children">
                            <el-submenu
                                v-if="subItem.children.length > 0"
                                :index="subItem.path"
                                :key="subItem.path"
                            >
                                <template slot="title" @click="clickTitle(subItem.title)"><i :class="subItem.icon"></i>{{ subItem.title }}</template>
<!--                                二级菜单-->
<!--                                <el-menu-item-->
<!--                                    v-for="(threeItem,i) in subItem.children"-->
<!--                                    :key="i"-->
<!--                                    :index="threeItem.index"-->
<!--                                ><i :class="threeItem.icon"></i>{{ threeItem.title }}</el-menu-item>-->
                            </el-submenu>
                            <el-menu-item
                                v-else
                                @click="clickTitle(subItem.path)"
                                :index="subItem.path"
                                :key="subItem.path"
                            ><i :class="subItem.icon"></i>{{ subItem.title }}</el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.path" :key="item.path">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
import bus from '../common/bus';
import router from '../../router';

export default {
    data() {
        return {
            collapse: false,
            items:[],
            itemList: [
                    {
                        "id":4,
                        "pid":1,
                        "icon":"el-icon-s-order",
                        "index":"3",
                        "title":"unified management",
                        "subs":[
                            {
                                "id":9,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"user",
                                "title":"user management",
                                "subs":null
                            },
                            {
                                "id":10,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"product",
                                "title":"Room information",
                                "subs":null
                            },
                            {
                                "id":11,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"order",
                                "title":"order management",
                                "subs":null
                            },
                            {
                                "id":10,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"notice",
                                "title":"Information management",
                                "subs":null
                            },
                            {
                                "id":10,
                                "pid":4,
                                "icon":"el-icon-plus",
                                "index":"statistical",
                                "title":"statistical management",
                                "subs":null
                            },
                        ]
                    },
                  ],
        };
    },
    computed: {
        onRoutes() {
            console.log('哈哈哈'+this.$route.path.replace('/', ''));
            return '/'+this.$route.path.replace('/', '');
        },
        menuList(){
            return JSON.parse(JSON.stringify(this.$store.state.menuList))
        }
    },
    created() {
        // 通过 Event Bus 进行组件间通信，来折叠侧边栏
        bus.$on('collapse', msg => {
            this.collapse = msg;
            bus.$emit('collapse-content', msg);
        });
        //初始化menuList
        if ("1" === sessionStorage.getItem('userType')){
            this.items = this.itemList;
        }else {
            this.items = this.itemList2;
        }
    },
    methods:{
        clickTitle(title){
            console.log(router.options.routes);
            console.log(title);
            this.$router.push(title)
        }
    }
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>
