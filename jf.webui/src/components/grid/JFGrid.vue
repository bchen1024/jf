<template>
    <div>
        <!--Table列表-->
        <Table border stripe size="small"
            :columns="grid.columns" :data="grid.result" 
            :no-data-text="grid.noDataMessage" :loading="grid.loading">
        </Table>
        <!--分页-->
        <template v-if='grid.showPager'>
            <Page @on-change="onChange"
                @on-page-size-change="onPageSizeChange"
                :total="grid.page.total" 
                :page-size="grid.page.pageSize" 
                :current="grid.page.curPage" 
                size="small"
                placement="top" show-elevator show-sizer show-total
                class="table-pager">
            </Page>
        </template>
    </div>
</template>
<script>
    import {JFFetch} from '../../libs/jf.js';
    export default {
        name:'JFGrid',
        props:['gridOptions'],
        data (){
            return {
                grid:Object.assign({
                    serach:{},
                    result: [],
                    columns: [],
                    queryParams:{},
                    noDataMessage:'',
                    showPager:true,
                    page:{
                        pageSize:10,
                        total:0,
                        curPage:1
                    }
                },this.gridOptions)
            }
        },
        methods:{
            load(params,reload){
                let grid=this.grid;
                let search=grid.search;
                if(search.url){
                    let param={};
                    if(reload){
                        param=params;
                    }else{
                        param=Object.assign({},JSON.parse(JSON.stringify(grid.queryParams)),params);
                    }
                    if(grid.showPager){
                        let page=grid.page;
                        Object.assign(param,{
                            curPage:page.curPage,
                            pageSize:page.pageSize
                        });
                    }
                    grid.loading=true;
                    grid.result=[];
                    JFFetch.doRequest(search.method,search.url,param,function(result){
                        grid.loading=false;
                        grid.result=result.result;
                        grid.page=result.page;
                    },function(error){
                        grid.loading=false;
                    });
                }
            },
            onChange(page){

            },
            onPageSizeChange(page){

            }
        },
        created(){
            this.load();
        }
    }
</script>
