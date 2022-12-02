import Vue from 'vue';
import Router from 'vue-router';
import store from '../store';
import { getNavList } from '../api/cake';

Vue.use(Router);


const router = new Router({
    routes: [
        // {
        //     path: '/',
        //     redirect: '/Home'
        // },
        {
            path: '/Home',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '' },
            children: [

                // {
                //     path: '/user',
                //     component: () => import(/* webpackChunkName: "icon" */ '../components/page/User'),
                //     meta: { title: 'user management' }
                // },
                //
                // {
                //     path: '/product',
                //     component: () => import(/* webpackChunkName: "icon" */ '../components/page/Product'),
                //     meta: { title: 'Room information' }
                // },
                //
                // {
                //     path: '/order',
                //     component: () => import(/* webpackChunkName: "icon" */ '../components/page/Order'),
                //     meta: { title: 'order management' }
                // },

                // {
                //     path: '/404',
                //     component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                //     meta: { title: '404' }
                // },
                // {
                //     path: '/403',
                //     component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                //     meta: { title: '403' }
                // },
                // {
                //     path: '/notice',
                //     component: () => import(/* webpackChunkName: "icon" */ '../components/page/News2'),
                //     meta: { title: 'Information management' }
                // },
                // {
                //     path: '/statistical',
                //     component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Statistical'),
                //     meta: { title: 'statistical management' }
                // },
            ]
        },


        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/page/Content'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/index',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Index'),
                    meta: { title: 'Home' }
                },
                {
                    path: '/register',
                    component: () => import(/* webpackChunkName: "login" */ '../components/page/Register'),
                    meta: { title: '注册' }
                },
                {
                    path: '/real-order',
                    component: () => import(/* webpackChunkName: "login" */ '../components/page/RealOrder'),
                    meta: { title: '订单信息' }
                },
                {
                    path: '/news',
                    component: () => import(/* webpackChunkName: "login" */ '../components/page/News'),
                    meta: { title: '资讯' }
                },
                {
                    path: '/detail/:id',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Detail'),
                    meta: { title: '详情页' }
                },
                {
                    path: '/all',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/All'),
                    meta: { title: '所有' }
                },
                ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '/pay',
            component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Pay'),
            meta: { title: '支付' }
        },

        {
            path: '/car',
            component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Car'),
            meta: { title: '购物车' }
        },
        //如果这里后面路由有问题，把这个404注释掉
        // {
        //     path: '*',
        //     redirect: '/404'
        // }
    ]
});

// router.beforeEach((to,from,next) =>{
//     if (to.path === '/login') {
//         next()
//     }else{
//         const token = sessionStorage.getItem('Authorization');
//         if (!token){
//             next('/login')
//         }else{
//             next();
//         }
//     }
// });

// 获取原型对象上的push函数
const originalPush = Router.prototype.push
// 修改原型对象中的push方法
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
let flag = false;
router.beforeEach((to, from, next) => {
    let token = sessionStorage.getItem("Authorization");
    if (to.path === '/login' ||  to.path ==='/register') {
        next()

    } else if (!token) {
        next({path: '/login'})


    } else if(token && !flag && router.options.routes[0].children.length === 0){
        // alert('请求了鲁豫')
        getNavList().then(res =>{

            //拿到menulist
            store.commit("setMenuList",res.data.nav);
            //拿到用户权限
            store.commit("setPermList",res.data.authoritys);

            //动态绑定路由
            //获取路由
            let newRoutes =router.options.routes;
            res.data.nav.forEach(menu =>{
                if (menu.children){
                    menu.children.forEach(e =>{
                        //转成路由
                        let route =menuToRoute(e);
                        // console.log(route);
                        //把路由添加到路由管理中
                        if (route){
                            newRoutes[0].children.push(route)
                        }
                    })
                }
            })

            // newRoutes[0].children.push({
            //     path: '*',
            //     redirect: '/404',
            //     hidden: true
            // });
            router.addRoutes(newRoutes)
            flag = true;
            // store.commit("changeRouteStatus",hasRoute)
            // store.commit("changeRouteStatus",hasRoute)
        })

    }

    next()
})

//导航转成路由
const menuToRoute= (menu) =>{
    // console.log(menu);
    if (!menu.component){
        return null
    }
    return {
        name: menu.title,
        path: menu.path,
        //路由额外值的赋值
        meta: {
            icon: menu.icon,
            title: menu.title,
        },
        component: () => import('../components/page/' + menu.component + '.vue'),
    }

}



export default router
