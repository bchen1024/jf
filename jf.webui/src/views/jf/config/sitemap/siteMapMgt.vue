<template>
    <div>
        <Menu class="layout-menu" mode="horizontal" theme="primary" active-name="1" @on-select="onSelect">
            <div v-for="menu in menus" :key="menu.siteMapCode">
                <Submenu v-if="menu.children" :key="menu.siteMapCode" :name="menu.siteMapId">
                    <template slot="title">
                        <Icon v-if="menu.siteMapIcon" :type="menu.siteMapIcon"></Icon>
                        {{menu.siteMapName}}
                    </template>
                    <div v-for="chrid in menu.children" :key="chrid.siteMapCode">	
                        <Menu-item :name="chrid.siteMapId" :key="chrid.siteMapCode">
                            <template v-if="chrid.siteMapUrl">
                                {{chrid.siteMapName}}
                            </template>
                            <template v-if="!chrid.siteMapUrl">
                                {{chrid.siteMapName}}
                            </template>
                        </Menu-item>
                    </div>
                </Submenu>
                <Menu-item v-if="menu.children==null" :name="menu.siteMapId" :key="menu.siteMapCode">
                    <Icon v-if="menu.siteMapIcon" :type="menu.siteMapIcon"></Icon>
                    <template v-if="menu.siteMapUrl">
                        {{menu.siteMapName}}
                    </template>
                    <template v-if="!menu.siteMapUrl">
                        {{menu.siteMapName}}
                    </template>
                </Menu-item>
            </div>
        </Menu>
        <Form ref="formMain" :model="grid.formValidate" :rules="grid.ruleValidate" :label-width="110" style="margin-top:16px;">
            <Row>
                <Col span="8">
                    <Form-item :label="$t('siteMap.code')" prop="siteMapCode">
                        <Input v-model="grid.formValidate.siteMapCode"></Input>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('siteMap.nameCN')" prop="siteMapNameCN">
                        <Input v-model="grid.formValidate.siteMapNameCN"></Input>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('siteMap.nameEN')" prop="siteMapNameEN">
                        <Input v-model="grid.formValidate.siteMapNameEN"></Input>
                    </Form-item>
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Form-item :label="$t('siteMap.parent')" prop="parentId">
                        <Select v-model="grid.formValidate.parentId" >
                            <Option v-for="parent in parents" :key="parent.siteMapId" :value="parent.siteMapId">{{parent.siteMapName}}</Option>
                        </Select>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('siteMap.icon')" prop="siteMapIcon">
                        <Input v-model="grid.formValidate.siteMapIcon"></Input>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('siteMap.showType')" prop="showType">
                        <Select v-model="grid.formValidate.showType" @on-change="onChange">
                            <Option value="1">{{$t('siteMap.allUser')}}</Option>
                            <Option value="2">{{$t('siteMap.visibleSubMenu')}}</Option>
                            <Option value="3">{{$t('siteMap.visiblePermission')}}</Option>
                        </Select>
                    </Form-item>
                </Col>
            </Row>
            <Row>
                <Col span="8">
                    <Form-item :label="$t('siteMap.permissionCode')" prop="permissionCode">
                        <Input :disabled="showType!=3" v-model="grid.formValidate.permissionCode"></Input>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('common.sort')" prop="siteMapSort">
                        <Input-number :max="100000" :min="10" :step="10" v-model="grid.formValidate.siteMapSort"></Input-number>
                    </Form-item>
                </Col>
                <Col span="8">
                    <Form-item :label="$t('common.status')" prop="enableFlag">
                        <Radio-group v-model="grid.formValidate.enableFlag">
                            <Radio label="Y">{{$t('common.valid')}}</Radio>
                            <Radio label="N">{{$t('common.inValid')}}</Radio>
                        </Radio-group>
                    </Form-item>
                </Col>
            </Row>
            <Form-item :label="$t('siteMap.url')">
                <Input v-model="grid.formValidate.siteMapUrl"></Input>
            </Form-item>
            <Form-item :label="$t('common.desc')">
                <Input v-model="grid.formValidate.siteMapDesc" type="textarea"></Input>
            </Form-item>
            <Form-item>
                <Button type="primary" :loading="grid.saveLoading" @click="saveRecord">{{$t('common.save')}}</Button>
                <Button type="primary" :disabled="grid.editType=='add'" @click="addRecord">{{$t('common.add')}}</Button>
                <Button type="primary" :disabled="!grid.formValidate.siteMapId" :loading="grid.deleteLoading" @click="deleteRecord">{{$t('common.delete')}}</Button>
            </Form-item>
        </Form>
    </div>
</template>
<script>
    import Util from '../../../../libs/util';
    export default {
        data(){
            let vue=this;
            return {
                menus:[],
                parents:[],
                showType:null,
                grid:{
                    //是否正在保存中
                    saveLoading:false,
                    //是否正在删除中
                    deleteLoading:false,
                    formValidate:{
                        enableFlag:'Y',
                        siteMapSort:10
                    },
                    ruleValidate:{
                        siteMapCode: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        siteMapNameCN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        siteMapNameEN: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        enableFlag: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'blur' }
                        ],
                        showType: [
                            { required: true, message: vue.$t('common.notNull'), trigger: 'change' }
                        ]
                    }
                }
            };
        },
        methods:{
            loadTable:function(){
                let vue=this;
                Util.get("services/jf/config/siteMap/find/list",{},function(result){
                    vue.menus=result;
                });
                Util.get("services/jf/config/siteMap/find/parents",{},function(result){
                    vue.parents=result;
                });
                vue.addRecord();
            },
            saveRecord:function(){
                let vue=this;
                Util.saveGrid(vue,'grid','formMain',
                    'services/jf/config/siteMap/create',
                    'services/jf/config/siteMap/update','siteMapId',function(){
                        vue.loadTable();
                    }
                );
            },
            addRecord:function(){
                let vue=this;
                vue.grid.formValidate={
                    enableFlag:'Y',
                    siteMapSort:10,
                    showType:'10'
                };
                vue.grid.editType='add';
            },
            deleteRecord:function(){
                let vue=this;
                Util.deleteGrid(vue,'grid','services/jf/config/siteMap/delete',{siteMapId:vue.grid.formValidate.siteMapId},function(){
                    vue.loadTable();
                });
            },
            onSelect:function(siteMapId){
                let vue=this;
                Util.get("services/jf/config/siteMap/find/single",{siteMapId:siteMapId},function(result){
                    vue.grid.formValidate=result;
                    vue.showType=result.showType;
                    vue.grid.editType='edit';
                });
            },
            onChange:function(value){
                let vue=this;
                vue.showType=value;
            }
        },
        created(){
            this.loadTable();
        }
    }
</script>

