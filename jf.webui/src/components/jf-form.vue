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
            <template v-else>
                <Input v-model="form.data[item.prop]"></Input>
            </template>
        </Form-item>
    </Form>
</template>
<script>
    export default {
        name:'jf-form',
        props:{
            form:{
                type:Object,
                default:{}
            }
        }
    }
</script>

