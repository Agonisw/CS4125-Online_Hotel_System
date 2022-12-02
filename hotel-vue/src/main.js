import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import store from './store'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import './assets/css/icon.css';
import './components/common/directives';
import 'babel-polyfill';
// import VideoPlayer from 'vue-video-player'
// require('vue-video-player/node_modules/video.js/dist/video-js.css')
// require('vue-video-player/src/custom-theme.css')
// Vue.use(VideoPlayer)
axios.defaults.baseURL = window.server_ip.BASE_URL;
Vue.config.productionTip = false;
Vue.use(ElementUI, {
    size: 'small'
});

router.afterEach((to,from,next) => {
    document.body.scrollTop =0;
    document.documentElement.scrollTop = 0;
});


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
