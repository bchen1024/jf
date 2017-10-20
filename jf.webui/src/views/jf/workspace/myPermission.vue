<template>
    <Tabs>
        <Tab-pane :label="$t('workspace.myRoles')">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;height:36px;">
                <!--同步数据-->
                <Button type="primary">{{$t('common.delete')}}</Button>
                <Input v-model="role.queryParams.queryParams" :placeholder="$t('role.queryTip')" style="width: 300px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadRole(1)"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table border stripe size="small" 
                :columns="role.columns" :data="role.result" 
                :no-data-text="role.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onRoleChange"  :total="role.page.total" size="small"
                        :page-size="role.page.pageSize" :current="role.page.curPage" 
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
        <Tab-pane :label="$t('workspace.myGroups')">
            <!--操作按钮行，搜索框-->
            <div style="margin-bottom:6px;height:36px;">
                <!--同步数据-->
                <Button type="primary">{{$t('common.delete')}}</Button>
                <Input v-model="group.queryParams.queryParams" :placeholder="$t('group.queryTip')" style="width: 300px;float:right">
                    <Button slot="append" icon="ios-search"  @click="loadGroup(1)"></Button>
                </Input>
            </div>
            <!--Table列表-->
            <Table border stripe size="small" 
                :columns="group.columns" :data="group.result" 
                :no-data-text="group.noDataMessage">
            </Table>
            <!--分页-->
            <div style="margin: 5px;overflow: hidden">
                <div style="float:right">
                    <Page @on-change="onGroupChange"  :total="group.page.total" size="small"
                        :page-size="group.page.pageSize" :current="group.page.curPage" 
                        placement="top" show-elevator show-sizer show-total>
                    </Page>
                </div>
            </div>
        </Tab-pane>
        <Tab-pane :label="$t('workspace.myPermission')">
        </Tab-pane>
    </Tabs>
</template>
<script>
    import Util from '../../../libs/util';
    export default {
        data(){
            let vue=this;
            return {
                role:{
                    //列配置信息
                    columns:[
                        {type: 'selection',width: 60,align: 'center',fixed: 'left'},
                        {title: vue.$t('common.status'),key: 'status',width:100},
                        {title: vue.$t('role.code'),key: 'roleCode',width:150,ellipsis:true},
                        {title: vue.$t('role.name'),key: 'roleName',width:200,ellipsis:true},
                        {title: vue.$t('common.desc'),key: 'roleDesc',ellipsis:true},
                        {title: vue.$t('common.beginDate'),key: 'beginDate',width:120,render:(h,params)=>{
                            return Util.toDateStr(params.row.beginDate,'date');
                        }},
                        {title: vue.$t('common.endDate'),key: 'endDate',width:120,render:(h,params)=>{
                            return Util.toDateStr(params.row.endDate,'date');
                        }},
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
                    noDataMessage:vue.$t('common.noData'),
                    //查询参数
                    queryParams:{
                        relationType:'user-role'
                    },
                },
                group:{
                    //列配置信息
                    columns:[
                        {type: 'selection',width: 60,align: 'center',fixed: 'left'},
                        {title: vue.$t('common.status'),key: 'status',width:100},
                        {title: vue.$t('group.code'),key: 'roleCode',width:150,ellipsis:true},
                        {title: vue.$t('group.name'),key: 'roleName',width:200,ellipsis:true},
                        {title: vue.$t('common.desc'),key: 'roleDesc',ellipsis:true},
                        {title: vue.$t('common.beginDate'),key: 'beginDate',width:120,render:(h,params)=>{
                            return Util.toDateStr(params.row.beginDate,'date');
                        }},
                        {title: vue.$t('common.endDate'),key: 'endDate',width:120,render:(h,params)=>{
                            return Util.toDateStr(params.row.endDate,'date');
                        }},
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
                    noDataMessage:vue.$t('common.noData'),
                    //查询参数
                    queryParams:{
                        relationType:'group-user'
                    },
                }
            }
        },
        methods:{
            /**
             * 加载数据
             */
            loadRole:function(curPage=1){
                let vue=this;
                vue.role.page.curPage=curPage;
                Util.loadGrid(vue,"role","services/jf/security/user/myRoles/page");
            },
            loadGroup:function(curPage=1){
                let vue=this;
                vue.group.page.curPage=curPage;
                Util.loadGrid(vue,"group","services/jf/security/user/myGroups/page");
            },
            onRoleChange:function(curPage){
                this.loadRole(curPage);
            },
            onGroupChange:function(curPage){
                this.loadGroup(curPage);
            }
        },
        created(){
            this.loadRole();
            this.loadGroup();
        }
    };
</script>