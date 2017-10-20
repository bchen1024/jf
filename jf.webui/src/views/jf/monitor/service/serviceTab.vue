<template>
    <Tabs>
        <Tab-pane :label="$t('service.rest')" icon="cloud">           
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <!--同步数据-->
                <Button type="primary" icon="plus" @click="syncService('rest')">{{$t('common.syncData')}}</Button>
                <Input v-model="rest.queryParams.queryParams" :placeholder="$t('service.queryTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadTable(1,'rest')"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table height="400" border stripe size="small" 
                :columns="rest.columns" :data="rest.result" 
                :no-data-text="rest.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onChange"  :total="rest.page.total"  size="small"
                        :page-size="rest.page.pageSize" :current="rest.page.curPage" 
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
        <Tab-pane :label="$t('common.download')" icon="android-download">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <!--同步数据-->
                <Button type="primary" icon="plus" @click="syncService('download')">{{$t('common.syncData')}}</Button>
                <Input v-model="download.queryParams.queryParams"  :placeholder="$t('service.queryBeanTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadTable(1,'download')"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table height="350" border stripe size="small" 
                :columns="download.columns" :data="download.result" 
                :no-data-text="download.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onDownloadOnChange"  :total="download.page.total"  size="small"
                        :page-size="download.page.pageSize" :current="download.page.curPage" 
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
        <Tab-pane :label="$t('common.upload')" icon="android-upload">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <!--同步数据-->
                <Button type="primary" icon="plus" @click="syncService('upload')">{{$t('common.syncData')}}</Button>
                <Input v-model="upload.queryParams.queryParams"  :placeholder="$t('service.queryBeanTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadTable(1,'upload')"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table height="350" border stripe size="small" 
                :columns="upload.columns" :data="upload.result" 
                :no-data-text="upload.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onUploadOnChange"  :total="upload.page.total"  size="small"
                        :page-size="upload.page.pageSize" :current="upload.page.curPage" 
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
        <Tab-pane :label="$t('common.import')" icon="social-tux">标签三的内容</Tab-pane>
        <Tab-pane :label="$t('common.export')" icon="social-tux">标签三的内容</Tab-pane>
        <Tab-pane :label="$t('service.cache')" icon="social-tux">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;">
                <!--同步数据-->
                <Button type="primary" icon="plus" @click="syncService('cache')">{{$t('common.syncData')}}</Button>
                <Input v-model="cache.queryParams.queryParams"  :placeholder="$t('service.queryBeanTip')" style="width: 360px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadTable(1,'cache')"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table height="350" border stripe size="small" 
                :columns="cache.columns" :data="cache.result" 
                :no-data-text="cache.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onCacheOnChange"  :total="cache.page.total"  size="small"
                        :page-size="cache.page.pageSize" :current="cache.page.curPage" 
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
                rest:{
                    //列配置信息
                    columns:[
                        {type: 'index',width: 60,align: 'center'},
                        {title: vue.$t('service.beanName'),key: 'beanName',width:150,ellipsis:true},
                        {title: vue.$t('service.methodName'),key: 'methodName',width:200,ellipsis:true},
                        {title: vue.$t('service.restMethod'),key: 'restMethod',width:120,align:'center',render:(h,params)=>{
                            let method=params.row.restMethod;
                            if(method){
                                let color="";
                                if(method=="GET"){
                                    color="green";
                                }else if(method=="PUT"){
                                    color="blue";
                                }else if(method=="POST"){
                                    color="yellow";
                                }else if(method=="DELETE"){
                                    color="red";
                                }
                                return h('Tag', {
                                    props: {
                                        color: color
                                    }
                                }, method);
                            }
                            return "";
                        }},
                        {title: vue.$t('service.restUrl'),key: 'restUrl',width:300,ellipsis:true},
                        {title: vue.$t('service.methodPermission'),key: 'methodPermission',width:200,ellipsis:true},
                        {title: vue.$t('service.methodAudit'),key: 'methodAudit',width:100,align:'center',render:(h,params)=>{
                            return Util.statusRender(h,params.row.methodAudit,params.row);
                        }},
                        {title: vue.$t('service.methodDesc'),key: 'methodDesc',width:200,ellipsis:true},
                        {title: vue.$t('service.restProduces'),key: 'restProduces',width:150},
                        {title: vue.$t('service.className'),key: 'className',width:250,ellipsis:true},
                        {title: vue.$t('service.methodReturn'),key: 'methodReturn',width:250,ellipsis:true},
                        {title: vue.$t('service.methodArgs'),key: 'methodArgs',width:250,ellipsis:true},
                        {title: vue.$t('service.methodException'),key: 'methodException',width:250,ellipsis:true},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
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
                    queryParams:{
                        serviceType:'rest'
                    }
                },
                cache:{
                    //列表数据
                    result:[],
                    //分页信息
                    page:{
                        total:0,
                        curPage:1,
                        pageSize:10
                    },
                    //查询参数
                    queryParams:{
                        serviceType:'cache'
                    },
                    columns:[
                        {type: 'index',width: 60,align: 'center'},
                        {title: vue.$t('service.beanName'),key: 'beanName',width:200,ellipsis:true},
                        {title: vue.$t('service.className'),key: 'className',ellipsis:true},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }}
                    ]
                },
                download:{
                    //列表数据
                    result:[],
                    //分页信息
                    page:{
                        total:0,
                        curPage:1,
                        pageSize:10
                    },
                    //查询参数
                    queryParams:{
                        serviceType:'download'
                    },
                    columns:[
                        {type: 'index',width: 60,align: 'center'},
                        {title: vue.$t('service.beanName'),key: 'beanName',width:200,ellipsis:true},
                        {title: vue.$t('service.className'),key: 'className',ellipsis:true},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }}
                    ]
                },
                upload:{
                    //列表数据
                    result:[],
                    //分页信息
                    page:{
                        total:0,
                        curPage:1,
                        pageSize:10
                    },
                    //查询参数
                    queryParams:{
                        serviceType:'upload'
                    },
                    columns:[
                        {type: 'index',width: 60,align: 'center'},
                        {title: vue.$t('service.beanName'),key: 'beanName',width:200,ellipsis:true},
                        {title: vue.$t('service.className'),key: 'className',ellipsis:true},
                        {title: vue.$t('service.uploadFolder'),key: 'methodName',ellipsis:true},
                        {title: vue.$t('service.totalFileNumber'),key: 'methodReturn',width:120},
                        {title: vue.$t('service.singleFileSize'),key: 'methodArgs',width:120,align:'right',render:(h,params)=>{
                            return Util.fileSizeRender(params.row.methodArgs,params.row);
                        }},
                        {title: vue.$t('common.lastUpdatedBy'),key: 'updateUserAccount',width:150},
                        {title: vue.$t('common.lastUpdationDate'),key: 'lastUpdationDate',width:160,render:(h,params)=>{
                            return Util.toDateStr(params.row.lastUpdationDate);
                        }}
                    ]
                }
            }
        },
        methods:{
            /**
             * 加载数据字典数据
             */
            loadTable:function(curPage=1,serviceType){
                let vue=this;
                vue[serviceType].page.curPage=curPage;
                Util.loadGrid(vue,serviceType,"services/jf/monitor/service/find/page");
            },
            syncService:function(serviceType){
                let vue=this;
                Util.post("services/jf/monitor/service/sync",{serviceType:serviceType},function(){
                    vue.$Message.success(vue.$t('common.syncSuccess'));
                    vue.loadTable(1,serviceType);
                });
            },
            onChange:function(curPage){
                this.loadTable(curPage,'rest');
            },
            onCacheOnChange:function(curPage){
                this.loadTable(curPage,'cache');
            },
            onDownloadOnChange:function(curPage){
                this.loadTable(curPage,'download');
            },
            onUploadOnChange:function(curPage){
                this.loadTable(curPage,'upload');
            }
        },
        created(){
            this.loadTable(1,'rest');
            this.loadTable(1,'cache');
            this.loadTable(1,'download');
            this.loadTable(1,'upload');
        }
    };
</script>