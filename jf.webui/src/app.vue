<style scoped>
    .layout{
        margin: 0;
        padding: 0;
    }
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
    .layout-breadcrumb{
        padding: 10px 15px;
        background: #f5f7f9;
    }
    .layout-content{
        margin: 5px;
        overflow: hidden;
        background: #fff;;
        border-radius: 4px;
    }
    .layout-content-main{
        padding: 10px;
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
<template>
    <div class="layout">
        <Menu class="layout-menu" mode="horizontal" theme="primary" active-name="1">
            <div class="layout-logo">
                <div class="layout-logo-text">JF集成平台</div>
            </div>
            <div v-for="menu in menus" :key="menu.siteMapCode">
                <Submenu v-if="menu.children" :key="menu.siteMapCode" :name="menu.siteMapId">
                    <template slot="title">
                        <Icon v-if="menu.siteMapIcon" :type="menu.siteMapIcon"></Icon>
                        {{menu.siteMapName}}
                    </template>
                    <div v-for="chrid in menu.children" :key="chrid.siteMapCode">	
                        <Menu-item :name="chrid.siteMapId" :key="chrid.siteMapCode">
                            <template v-if="chrid.siteMapUrl">
                                <router-link :to="chrid.siteMapUrl" tag="li">{{chrid.siteMapName}}</router-link>
                            </template>
                            <template v-if="!chrid.siteMapUrl">
                                {{chrid.siteMapName}}
                            </template>
                        </Menu-item>
                    </div>
                </Submenu>
                <Menu-item v-if="menu.children==null" :name="menu.siteMapId" :key="menu.siteMapCode">
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
                <Dropdown v-if="switchLanguage!='N'" @on-click="changeLanguage">
                    <a href="javascript:void(0)">
                        {{currentLanguage}}
                        <Icon type="arrow-down-b"></Icon>
                    </a>
                    <Dropdown-menu slot="list">
                        <Dropdown-item name="zh_CN">{{$t('common.zh_CN')}}</Dropdown-item>
                        <Dropdown-item name="en_US">{{$t('common.en_US')}}</Dropdown-item>
                    </Dropdown-menu>
                </Dropdown>
                <Dropdown @on-click="personalClick">
                    <a href="javascript:void(0)">
                        陈斌
                        <Icon type="arrow-down-b"></Icon>
                    </a>
                    <Dropdown-menu slot="list">
                        <Dropdown-item name="personal">个人中心</Dropdown-item>
                        <Dropdown-item name="logout">注销</Dropdown-item>
                    </Dropdown-menu>
                </Dropdown>
            </div>
        </Menu>
        <!--导航面包屑-->
        <div class="layout-breadcrumb">
            <Breadcrumb>
                <Breadcrumb-item href="/">{{$t("common.home")}}</Breadcrumb-item>
            </Breadcrumb>
        </div>
        <!--路由内容区-->
        <div class="layout-content">
            <router-view></router-view>
        </div>
    </div>
</template>
<script>
    import Vue from 'vue';
    import Util from './libs/util';
    export default {
        data() {
            let vue=this;
            return {
                menus:[],
                switchLanguage:vue.$workspace.global.switchLanguage,
                currentLanguage:vue.$t('common.'+vue.$workspace.global.currentLanguage)
            }
        },
        methods: {
            loadMenu:function(){
                let vue=this;
                Util.get("services/jf/config/siteMap/find/list",{},function(result){
                    vue.menus=result;
                });
            },
            changeLanguage:function(name){
                Util.post("services/jf/config/personalized/save/global",{"currentLanguage":name},function(){
                    window.location.reload();
                });
                
            },
            personalClick:function(name){
                let vue=this;
                if(name=='personal'){
                    vue.$router.push({path:'/jf/workspace/Personal'});
                }
            }
        },
        created(){
            this.loadMenu();
        }
    };
</script>