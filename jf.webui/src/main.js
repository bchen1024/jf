import Vue from 'vue';
import iView from 'iview';
import VueRouter from 'vue-router';
import Vuex from 'vuex';
import Cookies from 'js-cookie';
import env from './config/env';
import Util from './libs/util';
import App from './app.vue';
import 'iview/dist/styles/iview.css';

import VueI18n from 'vue-i18n';
import zhLocale from 'iview/src/locale/lang/zh-CN';
import enLocale from 'iview/src/locale/lang/en-US';

Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(VueI18n);
Vue.use(iView);

//获取当前用户信息
Vue.prototype.$workspace={};
var currentRequest=new Promise(function(resolve){
    Util.get("servlet/current",null,function(result){
        Object.assign(Vue.prototype.$workspace,result);
        //设置当前语言
        let currentLanguage=result.global.currentLanguage||'zh_CN';
        Vue.config.lang = currentLanguage;
        localStorage.setItem("currentLanguage",currentLanguage);
        i18nRequest();
        resolve(result);
    });
    
});

//获取国际化配置信息
var i18nDatas=[];
function i18nRequest(){
    var i18nVersion=localStorage.getItem("i18nVersion");
    var workspace=Vue.prototype.$workspace;
    if(workspace.application.environment=="dev" || workspace.global.i18nVersion>i18nVersion || !i18nVersion){
        Util.get("services/jf/config/i18n/find/i18ns",null,function(result){
            if(result && result.length>0){
                i18nDatas=result;
            }
            localStorage.setItem("i18nStorage",JSON.stringify(result));
            localStorage.setItem("i18nVersion",workspace.global.i18nVersion);
            bindI18n(); 
        });
    }else{
        var i18nStorage=localStorage.getItem("i18nStorage");
        if(i18nStorage){
            i18nDatas=JSON.parse(i18nStorage);
            bindI18n(); 
        }
    } 
}
//构建I18n配置信息
function bindI18n(){
    var i18ns={zh_CN:{},en_US:{}};
    for(var i=0;i<i18nDatas.length;i++){
        var i18nData=i18nDatas[i]
        var i18nKeys=i18nData.i18nKey.split(".");
        var i18nCN=i18ns.zh_CN,i18nEN=i18ns.en_US;
        for(var j=0;j<i18nKeys.length;j++){
            if(j==i18nKeys.length-1){
                i18nCN[i18nKeys[j]]=i18nData.i18nValueCN;
                i18nEN[i18nKeys[j]]=i18nData.i18nValueEN;
            }else{
                i18nCN=i18nCN[i18nKeys[j]]=i18nCN[i18nKeys[j]]||{};
                i18nEN=i18nEN[i18nKeys[j]]=i18nEN[i18nKeys[j]]||{};
            }
        }
    }
    Vue.locale('zh_CN', Object.assign(zhLocale, i18ns['zh_CN']));
    Vue.locale('en_US', Object.assign(enLocale, i18ns['en_US']));
}

//路由设置
const Routers = [
    {path: '/',meta: {title: '首页'},component: (resolve) => require(['./views/index.vue'], resolve)}
];
var routerRequest=new Promise(function(resolve){
    Util.get("services/jf/config/siteMap/find/routers",null,function(result){
        if(result && result.length>0){
            result.forEach(function(element) {
                if(element.siteMapUrl){
                    Routers.push(
                        {
                            path:element.siteMapUrl,
                            meta:{title:element.siteMapName},
                            component:(resolve)=>require(['./views'+element.siteMapUrl+'.vue'], resolve)
                        }
                    );
                } 
            }, this);
        }
        resolve(Routers);
    });
});

Promise.all([currentRequest,routerRequest]).then(function(values){
    // 路由配置
    const RouterConfig = {
        mode: 'history',
        routes: values[1]
    };
    const router = new VueRouter(RouterConfig);

    router.beforeEach((to, from, next) => {
        iView.LoadingBar.start();
        Util.title(to.meta.title);
        next();
    });

    router.afterEach(() => {
        iView.LoadingBar.finish();
        window.scrollTo(0, 0);
    });

    new Vue({
        el: '#app',
        router: router,
        render: h => h(App)
    });
});
