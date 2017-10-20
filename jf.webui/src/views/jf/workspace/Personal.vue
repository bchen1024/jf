<template>
    <Tabs>
        <Tab-pane :label="$t('workspace.personalInfo')">

        </Tab-pane>
        <Tab-pane :label="$t('workspace.updateFace')"></Tab-pane>
        <Tab-pane :label="$t('workspace.updatePassword')"></Tab-pane>
        <Tab-pane :label="$t('workspace.updateEmail')"></Tab-pane>
        <Tab-pane :label="$t('workspace.updatePhone')"></Tab-pane>
        <Tab-pane :label="$t('workspace.setting')"></Tab-pane>
    </Tabs>
</template>
<script>
    import Util from '../../../libs/util';
    export default {
        data(){
            let vue=this;
            return {
                
            }
        },
        methods:{
            
        },
        created(){
            
        }
    };
</script>