<template>
    <div class="layout">
        <JFMenu/>
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
    import Login from './login.vue';
    import JFMenu from './menu.vue';
    export default {
        components: {
            Login,
            JFMenu
        }
    };
</script>
<style scoped>
    .layout{
        margin: 0;
        padding: 0;
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
</style>