<style scoped>
    
</style>
<template>
    <div>
        <Row>
            <Col span="6">
                <Card style="width:100%">
                    <p slot="title">
                        待办
                    </p>
                    <a href="#" slot="extra" >
                        <Icon type="ios-loop-strong"></Icon>
                        刷新
                    </a>
                </Card>
            </Col>
            <Col span="18">
                <Card style="width:100%;height:500px;">
                    <p slot="title">
                        待办
                    </p>
                    <a href="#" slot="extra" >
                        <Icon type="ios-loop-strong"></Icon>
                        刷新
                    </a>
                    <JFForm :form='form'/>
                    <JFGrid :gridOptions='grid'/>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    export default {
        data(){
            let vm=this;
            return {
                form:{
                    labelWidth:100,
                    items:[
                        {prop:'name',label:'Name'},
                        {prop:'value',label:'Value'},
                        {prop:'select',label:'Select',type:'select',options:[
                            {value:1,text:'Options1'},
                            {value:2,text:'Options2'}
                        ]},
                        {prop:'radio',label:'Radio',type:'radio',options:[
                            {value:'Y',text:'有效'},
                            {value:'N',text:'无效'}
                        ]}
                    ],
                    data:{
                        name:'name1',radio:'Y'
                    },
                    rules:{
                        name:[
                            { required: true, message: vm.$t('common.notNull'), trigger: 'blur' }
                        ]
                    }
                },
                grid:{
                    columns:[
                        {key:'attachmentName',title:'ID'},
                        {key:'attachmentType',title:'Name'},
                        {key:'createUserAccount',title:'Age'},
                        {key:'attachmentId',title:'Sex'}
                    ],
                    search:{
                        url:'services/jf/config/attachment/find/page'
                    }
                }
            }
        }
    };
</script>