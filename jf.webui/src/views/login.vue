<template>
    <Card class='login-card'>
        <p slot="title">
            {{$t('common.loginTitle')}}
        </p>
        <Form ref="formInline" :model="formInline" :rules="ruleInline">
            <div class='login-error-panel'>
                <label class='login-error-msg'>{{loginMsg}}</label>
            </div>
            <FormItem prop="userAccount">
                <Input type="text" v-model="formInline.userAccount" :placeholder="$t('common.loginAccount')">
                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
            </FormItem>
            <FormItem prop="password">
                <Input type="password" v-model="formInline.password" :placeholder="$t('common.loginPassword')">
                    <Icon type="ios-locked-outline" slot="prepend"></Icon>
                </Input>
            </FormItem>
            <div class='login-forget-panel'>
                <a class='login-forget-pwd' href='javascript:void(0)'>{{$t('common.forgetPassword')}}</a>
            </div>
            <FormItem class='login-item'>
                <Button long class='login-btn' :loading='loginLoading' type="primary" @click="handleSubmit('formInline')">{{$t('common.login')}}</Button>
            </FormItem>
        </Form>
    </Card>
</template>
<script>
    import {Security} from '../libs/jf';
    export default {
        data(){
            let vue=this;
            return {
                loginMsg:null,
                loginLoading:false,
                formInline: {
                    userAccount: '',
                    password: ''
                },
                ruleInline: {
                    userAccount: [
                        { required: true, message: vue.$t('common.accountNotNull')}
                    ],
                    password: [
                        { required: true, message: vue.$t('common.passwordNotNull')}
                    ]
                }
            }
        },
        methods:{
            handleSubmit(name) {
                var vue=this;
                vue.loginMsg=null;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        Security.login(vue,vue.formInline);
                    }
                })
            }
        }
    };
</script>
<style scoped>
    .login-card{
        width:400px;
        margin:100px auto;
    }
    .login-error-msg{
        color:red;
    }
    .login-btn{
        float:right;
    }
    .login-error-panel{
        height:13px;
        line-height: 3px;
        text-align: center;
    }
    .login-forget-panel{
        height:26px;
        line-height: 6px;
    }
    .login-forget-pwd{
        float:right;
    }
    .login-item{
        margin-bottom: 6px;
    }
</style>