<template>
    <div>
        <!--Table列表-->
        <Table border stripe size="small" 
            :columns="columns" :data="data" 
            :loading="loading">
        </Table>
        <!--分页-->
        <div :v-if="showPager" style="margin: 5px;overflow: hidden">
            <div style="float:right">
                <Page @on-change="onChange"
                    @on-page-size-change="onPageSizeChange"
                    :total="total" 
                    :page-size="pageSize" 
                    :current="curPage" 
                    size="small"
                    placement="top" show-elevator show-sizer show-total>
                </Page>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name:'jf-grid',
        props:{
            refs:String,
            data:{
                type:Array,
                default:[]
            },
            columns:{
                type:Array,
                default:[]
            },
            loading:{
                type:Boolean,
                default:false
            },
            showPager:{
                type:Boolean,
                default:true
            },
            total:{
                type:int,
                default:0
            },
            pageSize:{
                type:int,
                default:10
            },
            curPage:{
                type:int,
                default:1
            },
            onChange:{
                type:Function,
                default (){
                    return ()=>{};
                }
            },
            onPageSizeChange:{
                type:Function,
                default (){
                    return ()=>{};
                }
            }
        },
        data (){
            return {
                columns:[],
                data:[],
                loading:false,
                showPager:true,
                total:0,
                pageSize:10,
                curPage:1
            }
        }
    }
</script>

