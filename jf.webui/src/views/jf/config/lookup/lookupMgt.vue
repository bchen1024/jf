<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;">
            <Button type="primary" icon="plus" @click="showModal('add')">{{$t('common.add')}}</Button>
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
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('lookup.queryTip')" style="width: 360px;float:right">
                <Button slot="append" icon="ios-search"  @click="loadTable()"></Button>
            </Input>
        </div>
        <!--Table列表-->
        <Table border stripe size="small" 
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage">
        </Table>
        <!--分页-->
        <div style="margin: 5px;overflow: hidden">
            <div style="float:right">
                <Page @on-change="onChange"  
                    :total="grid.page.total" 
                    :page-size="grid.page.pageSize" :current="grid.page.curPage" 
                    size="small" :page-size-opts="grid.page.pageSizeOpts"
                    placement="top" show-elevator show-sizer show-total>
                </Page>
            </div>
        </div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;">
            <Button type="primary" icon="plus" :disabled="!codeNo" @click="showLookupItemModal('add')">{{$t('common.add')}}</Button>
            <Input v-model="itemGrid.queryParams.queryParams"  :placeholder="$t('lookup.itemQueryTip')" style="width: 360px;float:right">
                <Button slot="append" icon="ios-search" ></Button>
            </Input>
        </div>
        <!--Table列表-->
        <Table border stripe size="small" 
            :columns="itemGrid.columns" :data="itemGrid.result" 
            :no-data-text="itemGrid.noDataMessage">
        </Table>
        <!--分页-->
        <div style="margin: 5px;overflow: hidden">
            <div style="float:right">
                <Page @on-change="itemOnChange"  :total="itemGrid.page.total" 
                    :page-size="itemGrid.page.pageSize" :current="itemGrid.page.curPage" 
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
        <!--删除确认Modal-->
        <Modal v-model="itemGrid.deleteModal" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>{{$t('common.deleteConfirm')}}</span>
            </p>
            <div style="text-align:center">
                <p>{{$t('common.deleteConfirmMsg')}}</p>
            </div>
            <div slot="footer">
                <Button type="error" size="large" long :loading="itemGrid.deleteLoading" @click="deleteItemRecord">{{$t('common.delete')}}</Button>
            </div>
        </Modal>
        <!--添加编辑Modal-->
        <Modal v-model="grid.addOrEditModal" width="760" 
            :title="$t('common.addEdit')"
        >
            <Form ref="formMain" :model="grid.formValidate" :rules="grid.ruleValidate"  :label-width="110">
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('lookup.codeNo')" prop="codeNo">
                            <Input v-model="grid.formValidate.codeNo"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('lookup.codeName')" prop="codeName">
                            <Input v-model="grid.formValidate.codeName"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('lookup.codeType')" prop="codeType">
                            <Select v-model="grid.formValidate.codeType">
                                <Option v-for="item in grid.lookupTypes" :value="item.itemNo" :key="item.itemId">{{item.itemName}}</Option>
                            </Select>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('lookup.codeParent')" prop="codeParent">
                            <Select v-model="grid.formValidate.codeParent">
                            </Select>
                        </Form-item>
                    </Col>
                </Row>
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
                        <Form-item :label="$t('common.publish')" prop="codePublish">
                            <Radio-group v-model="grid.formValidate.codePublish">
                                <Radio label="Y">{{$t('common.yes')}}</Radio>
                                <Radio label="N">{{$t('common.no')}}</Radio>
                            </Radio-group>
                        </Form-item>
                    </Col>
                </Row>
                <Form-item :label="$t('common.desc')" prop="codeDesc">
                    <Input v-model="grid.formValidate.codeDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
                </Form-item>
            </Form>
            <div slot="footer">
                <Button type="text"  @click="grid.addOrEditModal=false">{{$t('common.cancel')}}</Button>
                <Button type="primary" :loading="grid.saveLoading" @click="saveRecord">{{$t('common.save')}}</Button>
            </div>
        </Modal>
        <!--添加编辑Modal-->
        <Modal v-model="itemGrid.addOrEditModal" width="960" 
            :title="$t('common.addEdit')"
        >
            <Form ref="lookupItemForm" :model="itemGrid.formValidate" :rules="itemGrid.ruleValidate"  :label-width="110">
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('lookup.codeNo')" prop="codeNo">
                            <Input v-model="itemGrid.formValidate.codeNo" :disabled='true'></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('lookup.itemNo')" prop="itemNo">
                            <Input v-model="itemGrid.formValidate.itemNo"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('lookup.itemNameCN')" prop="itemNameCN">
                            <Input v-model="itemGrid.formValidate.itemNameCN"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('lookup.itemNameEN')" prop="itemNameEN">
                            <Input v-model="itemGrid.formValidate.itemNameEN"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('common.status')" prop="enableFlag">
                            <Radio-group v-model="itemGrid.formValidate.enableFlag">
                                <Radio label="Y">{{$t('common.valid')}}</Radio>
                                <Radio label="N">{{$t('common.inValid')}}</Radio>
                            </Radio-group>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('common.sort')" prop="itemSort">
                            <Input-number :max="100000" :min="10" :step="10" v-model="itemGrid.formValidate.itemSort"></Input-number>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('lookup.itemParent')" prop="itemParent">
                            <Input v-model="itemGrid.formValidate.itemParent"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('common.attr1')" prop="itemAttr1">
                            <Input v-model="itemGrid.formValidate.itemAttr1"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item :label="$t('common.attr2')" prop="itemAttr2">
                            <Input v-model="itemGrid.formValidate.itemAttr2"></Input>
                        </Form-item>
                    </Col>
                    <Col span="12">
                        <Form-item :label="$t('common.attr1')" prop="itemAttr3">
                            <Input v-model="itemGrid.formValidate.itemAttr3"></Input>
                        </Form-item>
                    </Col>
                </Row>
                <Form-item :label="$t('common.desc')" prop="itemDesc">
                    <Input v-model="itemGrid.formValidate.itemDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"></Input>
                </Form-item>
            </Form>
            <div slot="footer">
                <Button type="text"  @click="itemGrid.addOrEditModal=false">{{$t('common.cancel')}}</Button>
                <Button type="primary" :loading="itemGrid.saveLoading" @click="saveLookupItem">{{$t('common.save')}}</Button>
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
                        {title: vue.$t('lookup.codeNo'),key: 'codeNo',fixed: 'left',width:200,ellipsis:true,render:(h,params)=>{
                            return h('a', {
                                on: {click: () => {
                                    this.loadLookupItem(params.row.codeNo);
                                }}
                            },params.row.codeNo);
                        }},
                        {title: vue.$t('lookup.codeName'),key: 'codeName',width:200,ellipsis:true},
                        {title: vue.$t('lookup.codeType'),key: 'codeType',width:120,ellipsis:true},
                        {title: vue.$t('lookup.codeParent'),key: 'codeParent',width:150,ellipsis:true},
                        {title: vue.$t('common.publish'),key: 'codePublish',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.codePublish,params.row);
                        }},
                        {title: vue.$t('common.status'),key: 'enableFlag',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.enableFlag,params.row);
                        }},
                        {title: vue.$t('common.desc'),key: 'codeDesc',width:200,ellipsis:true},
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
                                            vue.grid.deleteModal=true;
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
                        pageSize:5,
                        pageSizeOpts:[5,10,20,30,40]
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
                    formValidate: {
                        enableFlag: 'Y',
                        codePublish:'Y'
                    },
                    ruleValidate: {
                        codeNo: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        codeName: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        codeType: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'change' }
                        ]
                    },
                    lookupTypes:[]
                },
                exportSelection:true,
                codeNo:null,
                itemGrid:{
                    //列表数据
                    result:[],
                    //分页信息
                    page:{
                        total:0,
                        curPage:1,
                        pageSize:10
                    },
                    queryParams:{},
                    columns:[
                        {title: vue.$t('lookup.codeNo'),key: 'codeNo',fixed: 'left',width:200,ellipsis:true},
                        {title: vue.$t('lookup.itemNo'),key: 'itemNo',fixed: 'left',width:150,ellipsis:true},
                        {title: vue.$t('lookup.itemNameCN'),key: 'itemNameCN',width:150,ellipsis:true},
                        {title: vue.$t('lookup.itemNameEN'),key: 'itemNameEN',width:150,ellipsis:true},
                        {title: vue.$t('lookup.itemParent'),key: 'itemParent',width:150,ellipsis:true},
                        {title: vue.$t('common.sort'),key: 'itemSort',width:100},
                        {title: vue.$t('common.status'),key: 'enableFlag',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.enableFlag,params.row);
                        }},
                        {title: vue.$t('common.attr1'),key: 'itemAttr1',width:100},
                        {title: vue.$t('common.attr2'),key: 'itemAttr2',width:100},
                        {title: vue.$t('common.attr3'),key: 'itemAttr3',width:100},
                        {title: vue.$t('common.desc'),key: 'itemDesc',width:200,ellipsis:true},
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
                                            this.showLookupItemModal('edit',params.row.itemId);
                                        }}
                                    }, this.$t('common.update')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'
                                        },
                                        on: {click: () => {
                                            vue.itemGrid.deleteModal=true;
                                            this.itemDeleteKey=params.row.itemId;
                                        }}
                                    }, this.$t('common.delete'))
                                ]);
                            }
                        }
                    ],
                    //是否显示删除确认框
                    deleteModal:false,
                    //是否正在删除中
                    deleteLoading:false,
                    //是否显示添加编辑框
                    addOrEditModal:false,
                    //是否正在保存中
                    saveLoading:false,
                    formValidate: {
                        itemSort:10,
                        enableFlag:'Y'
                    },
                    ruleValidate: {
                        codeNo: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        itemNo: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        itemNameCN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        itemNameEN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        enableFlag: [
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
                Util.loadGrid(vue,'grid',"services/jf/config/lookup/find/page");
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,codeId){
                let vue=this;
                Util.showAddOrEditModal(vue,'grid','formMain',type,'services/jf/config/lookup/find/single',{codeId:codeId});
            },
            /**
            *翻页
            */
            onChange:function(curPage){
                this.loadTable(curPage);
            },
            /**
             * 删除数据
             */
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/config/lookup/delete',{codeId:vue.deleteKey},function(){
                    vue.loadTable();
                });
            },
            
            /**
             * 保存记录
             */
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/config/lookup/create',
                    'services/jf/config/lookup/update','codeId',function(){
                        vue.loadTable();
                    }
                );
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
            itemOnChange:function(curPage){

            },
            loadLookupItem:function(codeNo){
                let vue=this;
                vue.codeNo=codeNo;
                vue.itemGrid.page.curPage=1;
                vue.itemGrid.queryParams.codeNo=codeNo;
                Util.loadGrid(vue,'itemGrid',"services/jf/config/lookupItem/find/page");
            },
            showLookupItemModal:function(type,itemId){
                let vue=this;
                Util.showAddOrEditModal(vue,'itemGrid','lookupItemForm',type,
                    'services/jf/config/lookupItem/find/single',{itemId:itemId},function(){
                        vue.itemGrid.formValidate.codeNo=vue.codeNo;
                    });
            },
            saveLookupItem:function(){
                let vue=this;
                Util.saveGrid(vue,'itemGrid','lookupItemForm',
                    'services/jf/config/lookupItem/create',
                    'services/jf/config/lookupItem/update','itemId',function(){
                        vue.loadLookupItem(vue.codeNo);
                    }
                );
            },
            deleteItemRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'itemGrid','services/jf/config/lookupItem/delete',{itemId:vue.itemDeleteKey},function(){
                    vue.loadLookupItem(vue.codeNo);
                });
            },
            loadLookupType:function(){
                let vue=this;
                Util.get("services/jf/config/lookupItem/find/lookupItems/lookup_type",null,function(result){
                    vue.grid.lookupTypes=result;
                })
            }
        },
        created(){
            this.loadTable();
            this.loadLookupType();
        }
    };
</script>