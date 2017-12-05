<template>
    <Menu class="layout-menu" mode="horizontal" theme="primary" active-name="1">
        <div class="layout-logo">
            <div class="layout-logo-text">{{$t('common.appName')}}</div>
        </div>
        <div v-for="menu in $store.state.currentMenus" :key="menu.siteMapCode">
            <Submenu v-if="menu.children" :key="menu.siteMapCode" :name="menu.siteMapId">
                <template slot="title">
                    <Icon v-if="menu.siteMapIcon" :type="menu.siteMapIcon"></Icon>
                    {{menu.siteMapName}}
                </template>
                <div v-for="chrid in menu.children" :key="chrid.siteMapCode">	
                    <template v-if="chrid.children">
                        <MenuGroup :title="chrid.siteMapName">
                            <Menu-item v-for="submenu in chrid.children"  :name="submenu.siteMapId" :key="submenu.siteMapCode">
                                <template v-if="submenu.siteMapUrl">
                                    <router-link :to="submenu.siteMapUrl" tag="li">{{submenu.siteMapName}}</router-link>
                                </template>
                                <template v-if="!submenu.siteMapUrl">
                                    {{submenu.siteMapName}}
                                </template>
                            </Menu-item>
                        </MenuGroup>
                    </template>
                    <template v-else>
                        <Menu-item  :name="chrid.siteMapId" :key="chrid.siteMapCode">
                            <template v-if="chrid.siteMapUrl">
                                <router-link :to="chrid.siteMapUrl" tag="li">{{chrid.siteMapName}}</router-link>
                            </template>
                            <template v-if="!chrid.siteMapUrl">
                                {{chrid.siteMapName}}
                            </template>
                        </Menu-item>
                    </template>
                </div>
            </Submenu>
            <Menu-item v-else :name="menu.siteMapId" :key="menu.siteMapCode">
                <Icon v-if="menu.siteMapIcon" :type="menu.siteMapIcon"></Icon>
                <template v-if="menu.siteMapUrl">
                    <router-link :to="menu.siteMapUrl" style="color:#fff;">{{menu.siteMapName}}</router-link>
                </template>
                <template v-if="!menu.siteMapUrl">
                    {{menu.siteMapName}}
                </template>
            </Menu-item>
        </div>
        
        <div class="layout-ceiling-main">
            <Dropdown v-if="$store.state.workspace.global.switchLanguage!='N'" @on-click="changeLanguage">
                <a href="javascript:void(0)">
                    {{$t('common.'+($store.state.workspace.global.currentLanguage || 'zh_CN'))}}
                    <Icon type="arrow-down-b"></Icon>
                </a>
                <Dropdown-menu slot="list">
                    <Dropdown-item name="zh_CN">{{$t('common.zh_CN')}}</Dropdown-item>
                    <Dropdown-item name="en_US">{{$t('common.en_US')}}</Dropdown-item>
                </Dropdown-menu>
            </Dropdown>
            <Dropdown @on-click="personalClick">
                <a href="javascript:void(0)">
                    {{$store.state.workspace.user.displayName}}
                    <Icon type="arrow-down-b"></Icon>
                </a>
                <Dropdown-menu slot="list">
                    <Dropdown-item name="personal">{{$t('common.personal')}}</Dropdown-item>
                    <Dropdown-item name="logout">{{$t('common.logout')}}</Dropdown-item>
                </Dropdown-menu>
            </Dropdown>
        </div>
    </Menu>
</template>
<script>
    import {Security,I18n} from '../libs/jf';
    export default {
        methods: {
            /**
             * 切换语言
             */
            changeLanguage:function(name){
                I18n.changeLanguage(name);  
            },
            personalClick:function(name){
                let vue=this;
                if(name=='personal'){
                    //vue.$router.push({path:'/jf/workspace/Personal'});
                }else if(name=='logout'){
                    Security.logout(vue);
                }
            }
        }
    };
</script>
<style scoped>
    .layout-logo{
        height: 30px;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
        margin-right: 36px;
    }
    .layout-logo-text{
        width: 100%;
        height: 100%;
        line-height: 30px;
        text-align: center;
        color: #ffffff;
        font-size: 20px;
        font-weight: bold;
    }
    .layout-copy{
        text-align: center;
        padding: 10px 0 20px;
        color: #9ea7b4;
    }
    .layout-ceiling-main{
        float: right;
        margin-right: 30px;
    }
    .layout-ceiling-main a{
        color: #ffffff;
        margin-left:10px;
    }
</style>