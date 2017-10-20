<template>
    <Tabs>
        <Tab-pane :label="$t('excel.importTemplate')"> 
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <Button type="primary" icon="plus" @click="showModal('add')">{{$t('common.add')}}</Button>  
                <Input v-model="importTemplate.queryParams.queryParams" :placeholder="$t('excel.queryTemplateTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadImportTemplate(1)"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table border stripe size="small" 
                :columns="importTemplate.columns" :data="importTemplate.result" 
                :no-data-text="importTemplate.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onChange"
                        @on-page-size-change="onPageSizeChange"
                        :total="importTemplate.page.total" 
                        :page-size="importTemplate.page.pageSize" 
                        :current="importTemplate.page.curPage" 
                        size="small"
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
            <!--添加编辑Modal-->
        <Modal v-model="importTemplate.addOrEditModal" width="760" 
            :title="$t('common.addEdit')"
        >
            <Form ref="formMain" :model="importTemplate.formValidate" :rules="importTemplate.ruleValidate" :label-width="110">
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('excel.templateCode')" prop="excelTemplateCode">
                            <Input v-model="importTemplate.formValidate.excelTemplateCode"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('excel.templateName')" prop="excelTemplateName">
                           <Input v-model="importTemplate.formValidate.excelTemplateName"></Input>
                        </Form-item>
                    </Col>
                </Row>
               <Row>
                    <Col span="12">
                        <Form-item :label="$t('excel.sendEmail')" prop="sendEmail">
                            <Switch size="small" value='Y' true-value='Y' false-value='N'></Switch>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('excel.allowPartSuccess')" prop="allowPartSuccess">
                           <Switch size="small" v-model="importTemplate.formValidate.allowPartSuccess" true-value='Y' false-value='N'></Switch>
                        </Form-item>
                    </Col>
                </Row>
                <Form-item :label="$t('common.desc')" prop="excelTemplateDesc">
                    <Input v-model="importTemplate.formValidate.excelTemplateDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
                </Form-item>
            </Form>
            <div slot="footer">
                <Button type="text"  @click="importTemplate.addOrEditModal=false">{{$t('common.cancel')}}</Button>
                <Button type="primary" :loading="importTemplate.saveLoading" @click="saveRecord">{{$t('common.save')}}</Button>
            </div>
        </Modal>
        </Tab-pane>
        <Tab-pane :label="$t('excel.exportTemplate')">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <Button type="primary" icon="plus" >{{$t('common.add')}}</Button> 
                <Input v-model="exportTemplate.queryParams.queryParams" :placeholder="$t('excel.queryTemplateTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadExportTemplate(1)"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table border stripe size="small" 
                :columns="exportTemplate.columns" :data="exportTemplate.result" 
                :no-data-text="exportTemplate.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onChange"
                        @on-page-size-change="onPageSizeChange"
                        :total="exportTemplate.page.total" 
                        :page-size="exportTemplate.page.pageSize" 
                        :current="exportTemplate.page.curPage" 
                        size="small"
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
    </Tabs>
</template>
<script>
    import Util from '../../../../libs/util';
    export default {
        data(){
            let vue=this;
            return {
                importTemplate:{
                    //列配置信息
                    columns:[
                        {title: vue.$t('excel.templateCode'),key: 'excelTemplateCode',width:150,ellipsis:true,render:(h,params)=>{
                            return h('a', {
                                on: {click: () => {
                                    //this.loadLookupItem(params.row.codeNo);
                                }}
                            },params.row.excelTemplateCode);
                        }},
                        {title: vue.$t('excel.templateName'),key: 'excelTemplateName',width:180},
                        {title: vue.$t('common.desc'),key: 'excelTemplateDesc'},
                        {title: vue.$t('excel.sendEmail'),key: 'sendEmail',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.sendEmail,params.row);
                        }},
                        {title: vue.$t('excel.allowPartSuccess'),key: 'allowPartSuccess',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.allowPartSuccess,params.row);
                        }},
                        {title: vue.$t('excel.asynFlag'),key: 'asynFlag',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.asynFlag,params.row);
                        }},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }},
                        {title: vue.$t('common.operate'),key: 'action', width: 150, align: 'center',fixed: 'right',render: (h, params) => {
                                return h('div', [
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            this.showModal('edit',params.row.excelTemplateId);
                                        }}
                                    }, this.$t('common.update')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'
                                        },
                                        on: {click: () => {
                                            vue.importTemplate.deleteModal=true;
                                            this.deleteKey=params.row.codeId;
                                        }}
                                    }, this.$t('common.delete'))
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
                    //是否显示删除确认框
                    deleteModal:false,
                    //是否正在删除中
                    deleteLoading:false,
                    //是否显示添加编辑框
                    addOrEditModal:false,
                    //是否正在保存中
                    saveLoading:false,
                    //查询参数
                    queryParams:{},
                    formValidate:{}
                },
                exportTemplate:{
                    //列配置信息
                    columns:[
                        {title: vue.$t('excel.templateCode'),key: 'excelTemplateCode',width:150,ellipsis:true,render:(h,params)=>{
                            return h('a', {
                                on: {click: () => {
                                    //this.loadLookupItem(params.row.codeNo);
                                }}
                            },params.row.excelTemplateCode);
                        }},
                        {title: vue.$t('excel.templateName'),key: 'excelTemplateName',width:180},
                        {title: vue.$t('common.desc'),key: 'excelTemplateDesc'},
                        {title: vue.$t('excel.sendEmail'),key: 'sendEmail',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.sendEmail,params.row);
                        }},
                        {title: vue.$t('excel.compressFlag'),key: 'compressFlag',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.compressFlag,params.row);
                        }},
                        {title: vue.$t('excel.asynFlag'),key: 'asynFlag',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.asynFlag,params.row);
                        }},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }},
                        {title: vue.$t('common.operate'),key: 'action', width: 150, align: 'center',fixed: 'right',render: (h, params) => {
                                return h('div', [
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            this.showModal('edit',params.row.codeId);
                                        }}
                                    }, this.$t('common.update')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'
                                        },
                                        on: {click: () => {
                                            vue.importTemplate.deleteModal=true;
                                            this.deleteKey=params.row.codeId;
                                        }}
                                    }, this.$t('common.delete'))
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
                    formValidate:{}
                }
            }
        },
        methods:{
            /**
             * 加载数据
             */
            loadImportTemplate:function(curPage=1){
                let vue=this;
                vue.importTemplate.page.curPage=curPage;
                Util.loadGrid(vue,"importTemplate","services/jf/config/excel/template/import/find/page");
            },
            /**
             * 加载数据
             */
            loadExportTemplate:function(curPage=1){
                let vue=this;
                vue.exportTemplate.page.curPage=curPage;
                Util.loadGrid(vue,"exportTemplate","services/jf/config/excel/template/export/find/page");
            },
            /**
             * 翻页
             */
            onChange:function(curPage){
                this.loadImportTemplate(curPage);
            },
            /**
             * 每页大小切换
             */
            onPageSizeChange:function(pageSize){
                let vue=this;
                vue.importTemplate.page.pageSize=pageSize;
                vue.loadImportTemplate();
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,excelTemplateId){
                let vue=this;
                Util.showAddOrEditModal(vue,'importTemplate','formMain',type,'services/jf/config/excel/template/import/find/single',{excelTemplateId:excelTemplateId});
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
        },
        created(){
            this.loadImportTemplate();
            this.loadExportTemplate();
        }
    };
</script>