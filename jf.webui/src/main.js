import Vue from 'vue';
import iView from 'iview';
import VueRouter from 'vue-router';
import Vuex from 'vuex';
import VueI18n from 'vue-i18n';
import App from './app.vue';
import Routers from './routers';
import {I18n} from './libs/jf';
import 'iview/dist/styles/iview.css';

Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(VueI18n);
Vue.use(iView);
// 路由配置
const router = new VueRouter({
    mode: 'history',
    routes:Routers
});

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    next();
});

router.afterEach(() => {
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});

//初始化国际化
const currentLanguage=I18n.getLanguage();
I18n.initI18n(currentLanguage,{});

/**
 * 状态管理
 */
const store = new Vuex.Store({
    state: {
        //登录状态
        loginStatus:false,
        checkLoginng:true,
        workspace:{},
        currentMenus:[],
        breadcrumbs:[]
    }
});

new Vue({
    el: '#app',
    router: router,
    store:store,
    render: h => h(App)
});
