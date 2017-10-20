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
            <Input v-model="grid.queryParams.propertyKey" :placeholder="$t('property.queryTip')" style="width: 360px;float:right">
                <Button slot="append" icon="ios-search"  @click="loadTable(1)"></Button>
            </Input>
        </div>
        <!--Table列表-->
        <Table height="400" border stripe size="small" 
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage"
            @on-selection-change="selectionChange">
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
                        <Form-item :label="$t('property.key')" prop="propertyKey">
                            <Input v-model="grid.formValidate.propertyKey"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('property.type')" prop="propertyType">
                            <Select v-model="grid.formValidate.propertyType">
                               <Option v-for="item in grid.propertyTypes" :value="item.itemNo" :key="item.itemId">{{item.itemName}}</Option>
                            </Select>
                        </Form-item>
                    </Col>
                </Row>
                
                <Form-item :label="$t('property.value')" prop="propertyValue">
                    <Input v-model="grid.formValidate.propertyValue"></Input>
                </Form-item>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('common.status')" prop="enableFlag">
                            <Radio-group v-model="grid.formValidate.enableFlag">
                                <Radio label="Y">{{$t('common.valid')}}</Radio>
                                <Radio label="N">{{$t('common.inValid')}}</Radio>
                            </Radio-group>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('common.publish')" prop="propertyPublish">
                            <Radio-group v-model="grid.formValidate.propertyPublish">
                                <Radio label="Y">{{$t('common.yes')}}</Radio>
                                <Radio label="N">{{$t('common.no')}}</Radio>
                            </Radio-group>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('common.attr1')" prop="propertyAttr1">
                            <Input v-model="grid.formValidate.propertyAttr1" ></Input>
                        </Form-item>
                     </Col>
                     <Col span="12">
                        <Form-item :label="$t('common.attr2')" prop="propertyAttr2">
                            <Input v-model="grid.formValidate.propertyAttr2" ></Input>
                        </Form-item>
                     </Col>
                </Row>
                <Form-item :label="$t('common.attr3')" prop="propertyAttr3">
                    <Input v-model="grid.formValidate.propertyAttr3" ></Input>
                </Form-item>
                <Form-item :label="$t('common.desc')" prop="propertyDesc">
                    <Input v-model="grid.formValidate.propertyDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
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
                        {title: vue.$t('property.key'),key: 'propertyKey',width:200},
                        {title: vue.$t('property.value'),key: 'propertyValue',width:300,ellipsis:true},
                        {title: vue.$t('property.type'),key: 'propertyType',width:150},
                        {title: vue.$t('common.status'),key: 'enableFlag',align: 'center',width:80,render:(h,params)=>{
                            return Util.statusRender(h,params.row.enableFlag,params.row);
                        }},
                        {title: vue.$t('common.publish'),key: 'propertyPublish',align: 'center',width:80,render:(h,params)=>{
                            return Util.statusRender(h,params.row.propertyPublish,params.row);
                        }},
                        {title: vue.$t('common.cache'),key: 'cache',align: 'center',width:100,render:(h,params)=>{
                            return Util.statusRender(h,params.row.cache,params.row);
                        }},
                        {title: vue.$t('common.desc'),key: 'propertyDesc',width:400,ellipsis:true},
                        {title: vue.$t('common.attr1'),key: 'propertyAttr1',width:150},
                        {title: vue.$t('common.attr2'),key: 'propertyAttr2',width:150},
                        {title: vue.$t('common.attr3'),key: 'propertyAttr3',width:150},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }},
                        {title: vue.$t('common.operate'),key: 'action', width: 140, align: 'center',fixed: 'right',render: (h, params) => {
                                return h('div', [
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            this.showModal('edit',params.row.propertyId);
                                        }}
                                    }, this.$t('common.edit')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'
                                        },
                                        on: {click: () => {
                                            vue.grid.deleteModal=true;
                                            this.deleteKey=params.row.propertyId;
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
                    formValidate: {
                        enableFlag: 'Y',
                        propertyPublish: 'N'
                    },
                    ruleValidate: {
                        propertyKey: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        propertyValue: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        propertyType: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'change' }
                        ]
                    },
                    propertyTypes:[]
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
                Util.loadGrid(vue,"grid","services/jf/config/property/find/page");
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,propertyId){
                let vue=this;
                Util.showAddOrEditModal(vue,'grid','formMain',type,'services/jf/config/property/find/single',{propertyId:propertyId});
            },
            /**
             * 删除数据
             */
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/config/property/delete',{propertyId:vue.deleteKey},function(){
                    vue.loadTable();
                });
            },
            /**
             * 保存记录
             */
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/config/property/create',
                    'services/jf/config/property/update','propertyId',function(){
                        vue.loadTable();
                    }
                );
            },
            /**
             * 清除缓存
             */
            clearCache:function(){
                let vue=this;
                Util.post("services/jf/config/property/clearCache",null,function(){
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
            },
            loadPropertys:function(){
                let vue=this;
                Util.get("services/jf/config/lookupItem/find/lookupItems/property_type",null,function(result){
                    vue.grid.propertyTypes=result;
                })
            }
        },
        created(){
            this.loadTable();
            this.loadPropertys();
        }
    };
</script>