<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;">
            <!--添加-->
            <Button type="primary" icon="plus" @click="showModal('add')">{{$t('common.add')}}</Button>
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('user.queryTip')" style="width: 360px;float:right">
                <Button slot="append" icon="ios-search"  @click="loadTable(1)"></Button>
            </Input>
        </div>
        <!--Table列表-->
        <Table height="400" border stripe size="small" 
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage">
        </Table>
        <!--分页-->
        <div style="margin: 5px;overflow: hidden">
            <div style="float:right">
                <Page @on-change="onChange"
                    @on-page-size-change="onPageSizeChange"
                    :total="grid.page.total" 
                    :page-size="grid.page.pageSize" 
                    :current="grid.page.curPage" 
                    size="small"
                    placement="top" show-elevator show-sizer show-total>
                </Page>
            </div>
        </div>
        <!--删除确认Modal-->
        <Modal v-model="grid.deleteModal" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>{{$t('common.deleteConfirm')}}</span>
            </p>
            <div style="text-align:center">
                <p>{{$t('common.deleteConfirmMsg')}}</p>
            </div>
            <div slot="footer">
                <Button type="error" size="large" long :loading="grid.deleteLoading" @click="deleteRecord">{{$t('common.delete')}}</Button>
            </div>
        </Modal>
        <!--添加编辑Modal-->
        <Modal v-model="grid.addOrEditModal" width="760" 
            :title="$t('common.addEdit')"
        >
            <Form ref="formMain" :model="grid.formValidate" :rules="grid.ruleValidate" :label-width="110">
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('user.account')" prop="userAccount">
                            <Input v-model="grid.formValidate.userAccount"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('user.name')" prop="userName">
                           <Input v-model="grid.formValidate.userName"></Input>
                        </Form-item>
                    </Col>
                </Row>
               <Row>
                    <Col span="12">
                        <Form-item :label="$t('user.email')" prop="userEmail">
                            <Input v-model="grid.formValidate.userEmail"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('user.phone')" prop="userPhone">
                           <Input v-model="grid.formValidate.userPhone"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Form-item :label="$t('common.desc')" prop="userDesc">
                    <Input v-model="grid.formValidate.userDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
                </Form-item>
            </Form>
            <div slot="footer">
                <Button type="text"  @click="grid.addOrEditModal=false">{{$t('common.cancel')}}</Button>
                <Button type="primary" :loading="grid.saveLoading" @click="saveRecord">{{$t('common.save')}}</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import Util from '../../../../libs/util';
    export default {
        data(){
            let vue=this;
            return {
                grid:{
                    //列配置信息
                    columns:[
                        {type: 'selection',width: 60,align: 'center',fixed: 'left'},
                    {title: vue.$t('user.account'),key: 'userAccount',width:150},
                    {title: vue.$t('user.name'),key: 'userName',width:200},
                    {title: vue.$t('user.email'),key: 'userEmail',width:200},
                    {title: vue.$t('user.phone'),key: 'userPhone',width:150},
                    {title: vue.$t('common.status'),key: 'enableFlag',align: 'center',width:80,render:(h,params)=>{
                        return Util.statusRender(h,params.row.enableFlag,params.row);
                    }},
                    {title: vue.$t('user.timeout'),key: 'userTimeout',width:150,align:'right'},
                    {title: vue.$t('user.passwordError'),key: 'passwordError',width:150,align:'right'},
                    {title: vue.$t('user.displayName'),key: 'displayName',width:200},
                    {title: vue.$t('common.desc'),key: 'userDesc',width:200},
                    {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                    {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                        return Util.toDateStr(params.row.lastUpdationDate);
                    }},
                        {title: vue.$t('common.operate'),key: 'action', width:240, align: 'center',fixed: 'right',render: (h, params) => {
                                return h('div', [
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            this.showModal('edit',params.row.userId);
                                        }}
                                    }, this.$t('common.edit')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            vue.grid.deleteModal=true;
                                            this.deleteKey=params.row.userId;
                                        }}
                                    }, this.$t('common.delete')),
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                           
                                        }}
                                    }, this.$t('group.roles')),
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        on: {click: () => {
                                           
                                        }}
                                    }, this.$t('role.groups'))
                                ]);
                            }
                        }
                    ],
                    //列表数据
                    result:[],
                    //分页信息
                    page:{
                        total:0,
                        curPage:1,
                        pageSize:10
                    },
                    //查询参数
                    queryParams:{},
                    //是否显示删除确认框
                    deleteModal:false,
                    //是否正在删除中
                    deleteLoading:false,
                    //是否显示添加编辑框
                    addOrEditModal:false,
                    //是否正在保存中
                    saveLoading:false,
                    formValidate: {
                        
                    },
                    ruleValidate: {
                        userAccount: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        userName: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ]
                    }
                }
            }
        },
        methods:{
            /**
             * 加载数据
             */
            loadTable:function(curPage=1){
                let vue=this;
                vue.grid.page.curPage=curPage;
                Util.loadGrid(vue,"grid","services/jf/security/user/find/page");
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,userId){
                let vue=this;
                Util.showAddOrEditModal(vue,'grid','formMain',type,'services/jf/security/user/find/single',{userId:userId});
            },
            /**
             * 删除数据
             */
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/security/user/delete',{userId:vue.deleteKey},function(){
                    vue.loadTable();
                });
            },
            /**
             * 保存记录
             */
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/security/user/create',
                    'services/jf/security/user/update','userId',function(){
                        vue.loadTable();
                    }
                );
            },
            /**
             * 翻页
             */
            onChange:function(curPage){
                this.loadTable(curPage);
            },
            /**
             * 每页大小切换
             */
            onPageSizeChange:function(pageSize){
                let vue=this;
                vue.grid.page.pageSize=pageSize;
                vue.loadTable();
            }
        },
        created(){
            this.loadTable();
        }
    };
</script>