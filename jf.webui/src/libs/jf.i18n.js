import Vue from 'vue';
import zhLocale from 'iview/src/locale/lang/zh-CN';
import enLocale from 'iview/src/locale/lang/en-US';
import locale   from '../config/locale';
import util from './util';
export default {
    /**
     * 获取当前语言
     */
    getLanguage(){
        let currentLanguage=localStorage.getItem('currentLanguage') || 'zh_CN';
        return currentLanguage;
    },
    /**
     * 根据语言获取客户端本地的国际化配置
     * @param {*} language 
     */
    getLocaleI18n(language){
        if(language=='zh_CN'){
            return Object.assign({},zhLocale,locale.zh_CN);
        }
        return Object.assign({},enLocale,locale.en_US);
    },
    /**
     * 解析国际化数据
     */
    parseI18n(i18nDatas){
        var i18ns={zh_CN:{},en_US:{}};
        for(var i=0;i<i18nDatas.length;i++){
            var i18nData=i18nDatas[i];
            var i18nKeys=i18nData.i18nKey.split('.');
            var i18nCN=i18ns.zh_CN,i18nEN=i18ns.en_US;
            for(var j=0;j<i18nKeys.length;j++){
                if(j==i18nKeys.length-1){
                    i18nCN[i18nKeys[j]]=i18nData.i18nCN;
                    i18nEN[i18nKeys[j]]=i18nData.i18nEN;
                }else{
                    i18nCN=i18nCN[i18nKeys[j]]=i18nCN[i18nKeys[j]]||{};
                    i18nEN=i18nEN[i18nKeys[j]]=i18nEN[i18nKeys[j]]||{};
                }
            }
        }
        return i18ns;
    },
    /**
     * 获取服务器配置的国际化
     * @param {*} workspace 
     */
    findServerI18n(workspace){
        var _self=this;
        //设置当前语言
        let currentLanguage=workspace.global.currentLanguage||'zh_CN';
        localStorage.setItem('currentLanguage',currentLanguage);
        var i18nVersion=localStorage.getItem('i18nVersion');
        var serviceI18nVersion=workspace.global.i18nVersion;
        if(workspace.application.environment=='dev' 
            || serviceI18nVersion>i18nVersion 
            || !i18nVersion){
            util.get('services/jf/config/i18n/find/i18ns',null,function(result){
                localStorage.setItem('i18nStorage',JSON.stringify(result));
                if(serviceI18nVersion){
                    localStorage.setItem('i18nVersion',serviceI18nVersion);
                }
                _self.initI18n(currentLanguage,result); 
            });
        }
    },
    /**
     * 初始化国际化配置
     * @param {*} currentLanguage 
     * @param {*} i18nDatas 
     */
    initI18n (currentLanguage,i18nDatas){
        Vue.config.lang = currentLanguage;
        Vue.locale('zh_CN',Object.assign(
            this.getLocaleI18n('zh_CN'), 
            this.parseI18n(i18nDatas)['zh_CN']));
        Vue.locale('en_US', Object.assign(
            this.getLocaleI18n('en_US'), 
            this.parseI18n(i18nDatas)['en_US'])
        );
    },
    /**
     * 改变语言
     * @param {*} currentLanguage 
     */
    changeLanguage(currentLanguage){
        util.post('services/jf/config/personalized/save/global',{'currentLanguage':currentLanguage},
        function(){
            window.location.reload();
        });
    }
};