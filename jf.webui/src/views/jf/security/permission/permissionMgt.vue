<template>
    <Row>
        <Col span="6">
        </Col>
        <Col span="18">
        </Col>
    </Row>
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
                        {title: vue.$t('group.code'),key: 'groupCode',width:120},
                        {title: vue.$t('group.name'),key: 'groupName',width:250},
                        {title: vue.$t('common.applyStatus'),key: 'applyStatus',align: 'center',width:120,render:(h,params)=>{
                            return Util.statusRender(h,params.row.applyStatus,params.row);
                        }},
                       
                        {title: vue.$t('common.owner'),key: 'groupOwnerName',width:150},
                        {title: vue.$t('common.desc'),key: 'groupDesc',width:400},
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
                                            this.showModal('edit',params.row.groupId);
                                        }}
                                    }, this.$t('common.edit')),
                                    h('Button', {
                                        props: {type: 'error',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                            vue.grid.deleteModal=true;
                                            this.deleteKey=params.row.groupId;
                                        }}
                                    }, this.$t('common.delete')),
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        style: {marginRight: '5px'},
                                        on: {click: () => {
                                           
                                        }}
                                    }, this.$t('group.users')),
                                    h('Button', {
                                        props: {type: 'primary',size: 'small'},
                                        on: {click: () => {
                                           
                                        }}
                                    }, this.$t('group.roles'))
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
                        applyStatus: 'N'
                    },
                    ruleValidate: {
                        groupCode: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        groupName: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        applyStatus: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'change' }
                        ],
                        groupOwner: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'change' }
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
                Util.loadGrid(vue,"grid","services/jf/security/group/find/page");
            },
            /**
             * 显示添加&编辑Modal
             */
            showModal:function(type,groupId){
                let vue=this;
                Util.showAddOrEditModal(vue,'grid','formMain',type,'services/jf/security/group/find/single',{groupId:groupId});
            },
            /**
             * 删除数据
             */
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/security/group/delete',{groupId:vue.deleteKey},function(){
                    vue.loadTable();
                });
            },
            /**
             * 保存记录
             */
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/security/group/create',
                    'services/jf/security/group/update','groupId',function(){
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