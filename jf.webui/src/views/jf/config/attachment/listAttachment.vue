<template>
    <div>
        <!--操作按钮行，搜索框-->
        <div style="margin-bottom:6px;">
            <div style="display:inline-block;">
                <Upload :action="uploadUrl"
                    :show-upload-list="false"
                    :on-success="uploadSuccess"
                >
                    <Button type="ghost" icon="upload">{{$t('common.upload')}}</Button>
                </Upload>
             </div>
            <Input v-model="grid.queryParams.queryParams" :placeholder="$t('attachment.queryTip')" style="width: 360px;float:right">
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
    import Config from '../../../../config/config';
    export default {
        data(){
            let vue=this;
            return {
                grid:{
                    //列配置信息
                    columns:[
                        {type: 'index',width: 60,align: 'center'},
                        {title: vue.$t('common.fileName'),key: 'attachmentName',render:(h,params)=>{
                            return h('a', {
                                attrs: {
                                    href:Config.serviceUrl+params.row.downloadUrl,
                                    target:'_blank',
                                    title:params.row.attachmentName
                                }
                            },params.row.attachmentName);
                        }},
                        {title: vue.$t('common.fileSize'),key: 'attachmentSize',width:150,align:'right',render:(h,params)=>{
                            return Util.fileSizeRender(params.row.attachmentSize,params.row);
                        }},
                        {title: vue.$t('common.exists'),key: 'attachmentExists',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.attachmentExists,params.row);
                        }},
                        {title: vue.$t('attachment.type'),key: 'attachmentType',width:150},
                        {title: vue.$t('common.uploader'),key: 'createUserAccount',width:150},
                        {title: vue.$t('common.uploadDate'),key: 'creationDate',width:160,render:(h,params)=>{
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
                },
                uploadUrl:Config.uploadUrl+"?uploadSupport=attachment"
            }
        },
        methods:{
            /**
             * 加载数据字典数据
             */
            loadTable:function(curPage=1){
                let vue=this;
                vue.grid.page.curPage=curPage;
                Util.loadGrid(vue,'grid',"services/jf/config/attachment/find/page");
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