import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        items:JSON.parse(localStorage.getItem('items') || '[]'),
        userId:null,
        carList:[],
        totalPrice:0,
        orderNum:null,
        token:'',
        menuList2:[],
        menuList:[],
        permList:[],
        editableTabsValue:null,
        editableTabs: [{
            title: '首页',
            name: 'Index',
        }],
        hasRoutes: false,
    },
    mutations:{
        setItems(state,data) {
            state.items = data;
            localStorage.setItem('items',JSON.stringify(data))
        },

        saveUserInfo(state,val){
            state.userId = val;
        },
        saveCarList(state,val){
            state.carList = val;
        },
        saveTotalPrice(state,val){
            state.totalPrice = val;
        },
        saveOrderNum(state,val){
            state.orderNum = val;
        },
        saveToken: (state ,token) => {
            state.token = token;
            sessionStorage.setItem('token',token)
        },
        // setMenuList2(state, menus) {
        //     state.menuList2 = menus
        //     sessionStorage.setItem('menuList2',JSON.stringify(menus))
        // },
        setMenuList(state, menus) {
            state.menuList = menus
            sessionStorage.setItem('menuList',JSON.stringify(menus))
        },

        setPermList(state, perms) {
            state.permList = perms;
            sessionStorage.setItem('permList',perms)
        },
        changeRouteStatus(state, hasRoutes) {
            state.hasRoutes = hasRoutes;
            // sessionStorage.setItem('hasRoutes',hasRoutes)
        },

        addTab(state,tab) {

            let index =state.editableTabs.findIndex(e => e.name === tab.name)

            if (index === -1) {
                state.editableTabs.push({
                    title: tab.title,
                    name: tab.name,
                });
            }
            state.editableTabsValue = tab.name;

        },

    },
    actions:{

    },
    getters:{

    },
    plugins: [
        createPersistedState({
            storage: window.sessionStorage
        })
    ],
})
