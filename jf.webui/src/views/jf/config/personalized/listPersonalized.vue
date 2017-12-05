<template>
    <div>
        <!--操作按钮行，搜索框-->
        <Row class="row-operater">
            <Col span="6" offset="18">
                <Input v-model="grid.queryParams.queryParams" 
                       icon="ios-search" 
                       autofocus
                       @on-enter="loadTable()" 
                       @on-click="loadTable()" 
                       :placeholder="$t('personalized.queryTip')">
                </Input>
            </Col>
        </Row>
        <!--Table列表-->
        <Table border stripe size="small" 
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage" :loading="grid.loading">
        </Table>
        <!--分页-->
        <Page @on-change="onChange"
            @on-page-size-change="onPageSizeChange"
            :total="grid.page.total" 
            :page-size="grid.page.pageSize" 
            :current="grid.page.curPage" 
            size="small"
            placement="top" show-elevator show-sizer show-total
            class="table-pager">
        </Page>
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