<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;">
            <!--添加-->
            <Button type="primary" icon="plus" @click="showModal('add')">{{$t('common.add')}}</Button>
            <!--清除缓存-->
            <Button type="primary" icon="refresh" @click="clearCache">{{$t('common.clearCache')}}</Button>
            <!--导入导出操作-->
            <Dropdown>
                <Button type="primary">
                    {{$t('common.importOrExport')}}
                    <Icon type="arrow-down-b"></Icon>
                </Button>
                <Dropdown-menu slot="list">
                    <Dropdown-item :disabled="exportSelection">{{$t('common.exportSelectedItems')}}</Dropdown-item>
                    <Dropdown-item>{{$t('common.exportByCondition')}}</Dropdown-item>
                    <Dropdown-item>{{$t('common.import')}}</Dropdown-item>
                    <Dropdown-item>{{$t('common.importIgnoreExists')}}</Dropdown-item>
                    <Dropdown-item>{{$t('common.downloadImportTemplate')}}</Dropdown-item>
                </Dropdown-menu>
            </Dropdown>
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('i18n.queryTip')" style="width: 360px;float:right">
                <Button slot="append" icon="ios-search"  @click="loadTable()"></Button>
            </Input>
        </div>
        <!--Table列表-->
        <Table height="430" border stripe size="small" 
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage"
            @on-selection-change="selectionChange">
        </Table>
        <!--分页-->
        <div style="margin: 5px;overflow: hidden">
            <div style="float:right">
                <Page @on-change="onChange"  :total="grid.page.total" 
                    :page-size="grid.page.pageSize" :current="grid.page.curPage" 
                    @on-page-size-change="onPageSizeChange"
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
        <Modal v-model="grid.addOrEditModal" width="700" 
            :title="$t('common.addEdit')"
        >
            <Form ref="formMain" :model="grid.formValidate" :rules="grid.ruleValidate" :label-width="110">
                <Form-item :label="$t('i18n.key')" prop="i18nKey">
                    <Input v-model="grid.formValidate.i18nKey"></Input>
                </Form-item>
                <Form-item :label="$t('i18n.valueCN')" prop="i18nValueCN">
                    <Input v-model="grid.formValidate.i18nValueCN"></Input>
                </Form-item>
                <Form-item :label="$t('i18n.valueEN')" prop="i18nValueEN">
                    <Input v-model="grid.formValidate.i18nValueEN"></Input>
                </Form-item>
                <Form-item :label="$t('common.desc')" prop="i18nDesc">
                    <Input v-model="grid.formValidate.i18nDesc" type="textarea"></Input>
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
                        {title: vue.$t('i18n.key'),key: 'i18nKey',width:200,ellipsis:true},
                        {title: vue.$t('i18n.valueCN'),key: 'i18nValueCN',width:200,ellipsis:true},
                        {title: vue.$t('i18n.valueEN'),key: 'i18nValueEN',width:200,ellipsis:true},
                        {title: vue.$t('common.cache'),key: 'cache',align: 'center',width:80,render:(h,params)=>{
                            return Util.statusRender(h,params.row.cache,params.row);
                        }},
                        {title: vue.$t('common.desc'),key: 'i18nDesc',ellipsis:true},
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
                                            this.showModal('edit',params.row.i18nId);
                                        }}
                                    }, this.$t('common.edit')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'
                                        },
                                        on: {click: () => {
                                            vue.grid.deleteModal=true;
                                            this.deleteKey=params.row.i18nId;
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
                    //是否显示删除确认框
                    deleteModal:false,
                    //是否正在删除中
                    deleteLoading:false,
                    //是否显示添加编辑框
                    addOrEditModal:false,
                    //是否正在保存中
                    saveLoading:false,
                    formValidate: {},
                    ruleValidate: {
                        i18nKey: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        i18nValueCN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        i18nValueEN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ]
                    }
                },
                //导出选中项是否禁用
                exportSelection:true
            }
        },
        methods:{
            /**
             * 加载数据
             */
            loadTable:function(curPage=1){
                let vue=this;
                vue.grid.page.curPage=curPage;
                Util.loadGrid(vue,'grid',"services/jf/config/i18n/find/page");
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,i18nId){
                let vue=this;
                Util.showAddOrEditModal(vue,'grid','formMain',type,'services/jf/config/i18n/find/single',{i18nId:i18nId});
            },
            /**
             * 删除记录
             */
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/config/i18n/delete',{i18nId:vue.deleteKey},function(){
                    vue.loadTable();
                });
            },
            /**
             * 保存数据
             */
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/config/i18n/create',
                    'services/jf/config/i18n/update','i18nId',function(){
                        vue.loadTable();
                    }
                );
            },
            /**
             * 清除缓存
             */
            clearCache:function(){
                let vue=this;
                Util.post("services/jf/config/i18n/clearCache",null,function(){
                    vue.$Message.success(vue.$t('common.clearCacheSuccess'));
                    vue.loadTable();
                });
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
            },
            /**
             * 当选中项发生变化是否判断是否启用导出选中项
             */
            selectionChange:function(selection){
                if(selection && selection.length>0){
                    this.exportSelection=false;
                }else{
                    this.exportSelection=true;
                }
            }
        },
        created(){
            this.loadTable();
        }
    };
</script>