<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;height:30px;">
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('log.queryTip')" style="width: 360px;float:right">
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
    export default {
        data(){
            let vue=this;
            return {
                grid:{
                    //列配置信息
                    columns:[
                        {title: vue.$t('common.operateTime'),key: 'logTime',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.logTime);
                        }},
                        {title: vue.$t('log.logMethod'),key: 'logMethod',width:150,ellipsis:true},
                        {title: vue.$t('log.logAccount'),key: 'logAccount',width:150},
                        {title: vue.$t('log.logIp'),key: 'logIp',width:120},
                        {title: vue.$t('log.logHost'),key: 'logHost',width:150},
                        {title: vue.$t('common.cost'),key: 'logCast',width:120,render:(h,params)=>{
                            return Util.castRender(params.row.logCast);
                        }},
                        {title: vue.$t('log.logMessage'),key: 'logMessage',width:150,ellipsis:true},
                        {title: vue.$t('log.logUrl'),key: 'logUrl',width:120,ellipsis:true},
                        {title: vue.$t('log.logType'),key: 'logType',width:120},
                        {title: vue.$t('log.logClient'),key: 'logClient',width:120},
                        {title: vue.$t('log.logClass'),key: 'logClass',width:200,ellipsis:true}
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
                Util.loadGrid(vue,'grid',"services/jf/monitor/auditLog/find/page");
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
             * 上传成功函数
             */
            uploadSuccess:function(){
                this.loadTable();
            }
        },
        created(){
            this.loadTable();
        }
    };
</script>