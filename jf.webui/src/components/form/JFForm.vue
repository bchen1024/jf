<template>
    <Form :model="form.data || {}" :rules="form.rules || {}" :label-width='form.labelWidth || 0'>
        <Form-item v-for='item in form.items' :key='item.prop' :label="item.label" :prop="item.prop">
            <!--Select下拉框组件-->
            <template v-if='item.type=="select"'>
                <Select v-model="form.data[item.prop]">
                    <Option v-for='option in item.options' :key='option.value' :value='option.value'>
                        {{option.text}}
                    </Option>
                </Select>
            </template>
            <!--Radio组件-->
            <template v-else-if='item.type=="radio"'>
                <RadioGroup v-model="form.data[item.prop]">
                    <Radio v-for='option in item.options' :key='option.value' :label='option.value'>
                        {{option.text}}
                    </Radio>
                </RadioGroup>
            </template>
            <!--Input组件-->
            <template v-else>
                <Input v-model="form.data[item.prop]"></Input>
            </template>
        </Form-item>
    </Form>
</template>
<script>
    export default {
        name:'JFForm',
        props:{
            form:{
                type:Object,
                default:{}
            }
        }
    }
</script>

