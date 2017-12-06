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
                <jf-form :form='form'/>
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
                        ]}
                    ],
                    data:{
                        name:'name1'
                    },
                    rules:{
                        name:[
                            { required: true, message: vm.$t('common.notNull'), trigger: 'blur' }
                        ]
                    }
                }
            }
        }
    };
</script>