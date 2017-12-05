<template>
    <div>
        <router-view></router-view>
    </div>
</template>
<script>
    import {Security} from './libs/jf';
    export default {
        created () {
            Security.checkLoginStatus(this);
        }
    };
</script>