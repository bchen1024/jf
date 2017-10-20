<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;height:36px;">
            <Input v-model="grid.queryParams.queryKey"  :placeholder="$t('personalized.queryTip')" style="width: 360px;float:right">
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
                        {title: vue.$t('personalized.key'),key: 'personalizedKey',width:250},
                        {title: vue.$t('personalized.content'),key: 'personalizedContent'},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'createUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'creationDate',width:160,render:(h,params)=>{
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
             * 加载数据字典数据
             */
            loadTable:function(curPage=1){
                let vue=this;
                vue.grid.page.curPage=curPage;
                Util.loadGrid(vue,'grid',"services/jf/config/personalized/find/page");
            },
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
        },
        created(){
            this.loadTable();
        }
    };
</script>