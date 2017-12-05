import Util from './util';
import I18n from './jf.i18n';
const Security={
    /**
     * 显示登录组件
     */
    showLogin(vue){
        vue.$router.replace({name:'login'});
    },
    /**
     * 显示主页面
     */
    showMainPage(vue){
        let currentRouter=vue.$route.path;
        if(currentRouter=='/login'){  
            vue.$router.replace({name:'index'});
        }
    },
    /**
     * 检查登录状态
     */
    checkLoginStatus(vue){
        var _self=this;
        var token=sessionStorage.token;
        if(token){
            Util.get('servlet/current',null,function(workspace){
                vue.$store.state.workspace=workspace;
                I18n.findServerI18n(workspace);
                _self.showMainPage(vue);
                _self.loadMenus(vue);
            },function(){
                _self.showLogin(vue);
            });
        }else{
            _self.showLogin(vue);
        }
    },
    /**
     * 加载主菜单
     */
    loadMenus(vue){
        Util.get('services/jf/config/siteMap/find/list',{},function(result){
            vue.$store.state.currentMenus=result;
        });
    },
    login(vue,data){
        var _self=this;
        vue.loginLoading=true;
        Util.post('auth/login',data,function(result){
            sessionStorage.setItem('token',result.token);
            _self.checkLoginStatus(vue);
            vue.loginLoading=false;
        },function(error){
            vue.loginMsg=error.message;
            vue.loginLoading=false;
        });
    },
    logout(vue){
        var _self=this;
        sessionStorage.removeItem('token');
        _self.showLogin(vue);
    }
};
export default Security;