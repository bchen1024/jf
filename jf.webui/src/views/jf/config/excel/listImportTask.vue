<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;height:30px;">
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('excel.queryTaskTip')" style="width: 360px;float:right">
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
    </div>
</template>
<script>
    import Util from '../../../../libs/util';
    import Config from '../../../../config/config';
    export default {
        data(){
            let vue=this;
            return {
                grid:{
                    //列配置信息
                    columns:[
                        {title: vue.$t('common.fileName'),key: 'fileName',width:300,ellipsis:true,render:(h,params)=>{
                            return Util.downloadRender(h,params.row.fileName,'dlType=excel&taskId='+params.row.taskId);
                        }},
                        {title: vue.$t('common.taskName'),key: 'taskName',width:150},
                        {title: vue.$t('common.taskStatus'),key: 'taskStatusName',width:120},
                        {title: vue.$t('common.fileSize'),key: 'fileSize',align: 'right',width:120,render:(h,params)=>{
                            return Util.fileSizeRender(params.row.fileSize,params.row);
                        }},
                        {title: vue.$t('common.fileRows'),key: 'fileRows',align: 'right',width:100},
                        {title: vue.$t('common.beginDate'),key: 'taskStartTime',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.taskStartTime);
                        }},
                        {title: vue.$t('common.endDate'),key: 'taskEndTime',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.taskEndTime);
                        }},
                        {title: vue.$t('common.cost'),key: 'taskCost',width:100,render:(h,params)=>{
                            return Util.castRender(params.row.taskCost);
                        }},                        
                        {title: vue.$t('common.createdBy'),key: 'createUserAccount',width:150},
                        {title: vue.$t('common.creationDate'),key: 'creationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.creationDate);
                        }}
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
                    queryParams:{}
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
                Util.loadGrid(vue,"grid","services/jf/config/excel/task/import/find/page");
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