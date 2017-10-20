let util = {
    /**
     * 加载表格数据
     */
    loadGrid:function(vue,grid,successCallback,failCallback){
        //构造查询参数
        let gridOps=vue[grid];
        let params=Object.assign({},JSON.parse(JSON.stringify(gridOps.queryParams)));

        //构造分页参数
        if(gridOps.page){
            params.curPage=gridOps.page.curPage || 1;
            params.pageSize=gridOps.page.pageSize || 10;
        }

        //指定默认的加载信息
        gridOps.noDataMessage=vue.$t('common.loading');
        const loading=vue.$Message.loading({
            content: vue.$t('common.loading'),
            duration: 0
        });

        //清空表格数据
        gridOps.result=[];
        //请求服务查询数据
        util.get(gridOps.pageUrl,params,function(result){
            if(successCallback){//自定义的成功回调函数
                successCallback.call(this,result);
            }else{//默认的成功执行过程
                //指定查询结果
                gridOps.result=result.result;
                //指定分页信息
                gridOps.page=result.page;
                //如果没有数据，提示没数据
                if(!result.result || result.result.length==0){
                    gridOps.noDataMessage=vue.$t('common.noData');
                }
                setTimeout(loading,1);
            }
        },function(error){
            setTimeout(loading,1);
            if(failCallback){//自定义的错误回调
                failCallback.call(this,error);
            }else{//默认的错误回调
                gridOps.noDataMessage=vue.$t('common.loadError')+error.message;
            }
        });
    }
};
export default util;