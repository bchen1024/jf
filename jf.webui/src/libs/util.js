
import Config from '../config/config.js';
import Vue from 'vue';
import zhLocale from 'iview/src/locale/lang/zh-CN';
import enLocale from 'iview/src/locale/lang/en-US';
let util = {

};
util.title = function(title) {
    title = title ? title + ' - JF平台' : 'JF平台';
    window.document.title = title;
};

/**
 * 状态渲染
 */
util.statusRender=function(h,value,data){
    let icon= value=='Y'?'checkmark':'close';
    let color=value=='Y'?'green':'red';
    if(value=='T'){
        icon="information";
        color="#ff9900";
    }
    return h('div', [
        h('Icon', {
            props: {type:icon,size: 16,color:color}
        })
    ]);
};
/**
 * 状态渲染
 */
util.fileSizeRender=function(value,data){
    if(value<1024){
        return value+" B";
    }else if(value>=1024 && value<1024*1024){
        return (value/1024).toFixed(2)+" KB";
    }else if(value>=1024*1024 && value<1024*1024*1024){
        return (value/1024/1024).toFixed(2)+" M";
    }else if(value>=1024*1024*1024 && value<1024*1024*1024*1024){
        return (value/1024/1024/1024).toFixed(2)+" GB";
    }else if(value>=1024*1024*1024*1024 && value<1024*1024*1024*1024*1024){
        return (value/1024/1024/1024/1024).toFixed(2)+" TB";
    }
};

/**
 * 日期转换函数
 */
util.toDateStr=function(value, format){
    if (!value) {
        return null;
    }
    if(!format){
        format="datetime";
    }
    if(typeof value =='number'){
        value=new Date(value);
    }
    var year = value.getFullYear();// 当前年份，yyyy

    var month = value.getMonth() + 1;// 当前月份 0-11
    if (month < 10) {
        month = "0" + month;
    }

    var date = value.getDate();// 当前日期 1-31
    if (date < 10) {
        date = "0" + date;
    }
    // 小时
    var hours = value.getHours();
    if (hours < 10) {
        hours = "0" + hours;
    }
    // 分钟
    var min = value.getMinutes();
    if (min < 10) {
        min = "0" + min;
    }
    // 秒
    var s = value.getSeconds();
    if (s < 10) {
        s = "0" + s;
    }

    var result = year + "-" + month + "-" + date;
    if (format == "date") {
        return result;
    } else if (format == "datetime") {
        return result + " " + hours + ":" + min + ":" + s;
    } else {
        try {
            return format.replace("yyyy", year).replace("MM", month)
                    .replace("dd", date).replace("hh", hours).replace("mm",
                            min).replace("ss", s);
        } catch (e) {
            throw new Error(e);
        }
    }
};

/**
 * 长文本渲染
 */
util.longtext=function(h,value,data,placement='bottom-start'){
    return h('Tooltip', {
        props:{
            content:value,
            placement:placement,
            transfer:true
        }
    },value);
}

/**
 * 下载链接渲染
 */
util.downloadRender=function(h,value,params){
    return h('a', {
        attrs: {
            href:Config.downloadUrl+(params?"?"+params:""),
            target:'_blank',
            title:value
        }
    },value);
}

util.castRender=function(value){
    if(value<1000){//1s以内
        return value+" ms";
    }else if(value>=1000 && value < 1000*60){//一分钟以内
        return Math.floor(value/1000)+"s "+(value%1000)+"ms";
    }
}

util.fetch=function(url,params,options,successCallback,failCallback){
    let defaultOp={
        method:"GET",
        headers:{
            "Accept":"application/json",
            "Content-type":"application/json",
            "currentLanguage":localStorage.getItem("currentLanguage")||'zh_CN',
            'Authorization':sessionStorage.token
        }
    };
    let baseUrl=Config.serviceUrl;
    let realUrl=baseUrl+url;
    let op=Object.assign({},defaultOp,options);
    if(op.method=="GET"){
        if(params){
            let realParams=[];
            for(var key in params){
                realParams.push(key+"="+params[key]);
            }
            if(realParams.length){
                realUrl+="?"+realParams.join("&");
            }
        }
    }else{
        if(params){
            op.body=JSON.stringify(params);
        }
    }
    fetch(realUrl,op).then(function(resp){
        if(resp.status==204){
            return {};
        }
        return resp.json();
    }).then(function(result){
        if(result.httpCode){
            if(failCallback){
                failCallback.call(this,result);
            }else{
                Vue.$Modal.error({
                    title: 'Error',
                    content: result.message
                });
            }
        }else{
            successCallback && successCallback.call(this,result);
        }
    }).catch(function(error){
        failCallback && failCallback.call(this,error);
    });
};
util.get=function(url,params,successCallback,failCallback,options){
    util.fetch(url,params,Object.assign({},{'method':'GET'},options),successCallback,failCallback);
};
util.post=function(url,params,successCallback,failCallback,options){
    util.fetch(url,params,Object.assign({},{'method':'POST'},options),successCallback,failCallback,Object.assign({},{'method':'POST'},options));
}
util.delete=function(url,params,successCallback,failCallback,options){
    util.fetch(url,params,Object.assign({},{'method':'DELETE'},options),successCallback,failCallback,Object.assign({},{'method':'DELETE'},options));
}
util.put=function(url,params,successCallback,failCallback,options){
    util.fetch(url,params,Object.assign({},{'method':'PUT'},options),successCallback,failCallback,Object.assign({},{'method':'PUT'},options));
}

/**
 * 加载表格
 */
util.loadGrid=function(vue,grid,url,successCallback,failCallback){
    //构造查询参数
    let params=Object.assign({},JSON.parse(JSON.stringify(vue[grid].queryParams)),
        {curPage:vue[grid].page.curPage,pageSize:vue[grid].page.pageSize});
    //指定默认加载中
    vue[grid].loading=true;
    //清空表格数据
    vue[grid].result=[];
    //请求服务查询数据
    util.get(url,params,function(result){
        vue[grid].loading=false;
        if(successCallback){//自定义的成功回调函数
            successCallback.call(this,result);
        }else{//默认的成功执行过程
            //指定查询结果
            vue[grid].result=result.result;
            //指定分页信息
            vue[grid].page=result.page;
            //如果没有数据，提示没数据
            if(!result.result || result.result.length==0){
                vue[grid].noDataMessage=vue.$t('common.noData');
            }
        }
    },function(error){
        vue[grid].loading=false;
        if(failCallback){//自定义的错误回调
            failCallback.call(this,error);
        }else{//默认的错误回调
            vue[grid].noDataMessage=vue.$t('common.loadError')+error.message;
        }
    });
};

util.showAddOrEditModal=function(vue,grid,formId,type,url,params,successCallback){
    vue.$refs[formId].resetFields();
    vue[grid].addOrEditModal=true;
    vue[grid].editType=type;
    if(type=='edit'){
        util.get(url,params,function(result){
            vue[grid].formValidate=result;
            successCallback && successCallback.call(this,result);
        },function(error){
            vue.$Message.error(vue.$t('common.loadError')+error.message);
        });
    }else{
        successCallback && successCallback.call(this);
    }
};

/**
 * 删除数据
 */
util.deleteGrid=function(vue,grid,url,params,successCallback,failCallback){
    vue[grid].deleteLoading = true;
    util.delete(url,params,function(){
        vue[grid].deleteLoading = false;
        vue[grid].deleteModal=false;
        vue.$Message.success(vue.$t('common.deleteSuccessful'));
        successCallback && successCallback.call(this);
    },function(error){
        vue[grid].deleteLoading = false;
        vue.$Message.error(vue.$t('common.deleteError')+error.message);
    });
};

util.saveGrid=function(vue,grid,formId,createUrl,updateUrl,keyId,successCallback){
    //校验有效性
    vue.$refs[formId].validate((valid) => {
        if (valid) {
            let data=JSON.parse(JSON.stringify(vue[grid].formValidate));
            let url=createUrl;
            let fetchMethod=util.post;
            if(data[keyId] && vue[grid].editType=="edit"){
                url=updateUrl;
                fetchMethod=util.put;
            }else{
                data[keyId]=null;
            }
            vue[grid].saveLoading=true;
            fetchMethod(url,data,function(){
                vue[grid].addOrEditModal=false;
                vue[grid].saveLoading=false;
                vue.$Message.success(vue.$t('common.saveSuccessful'));
                successCallback && successCallback.call(this);
            },function(error){
                vue[grid].saveLoading=false;
                vue.$Message.error(vue.$t('common.saveError')+error.message);
            });
        }
    });
};

util.loadTable=function(vue,url,curPage=1,successCallback,failCallback){
    let params=Object.assign({},JSON.parse(JSON.stringify(vue.queryParams)),
        {curPage:curPage,pageSize:vue.page.pageSize});
    vue.noDataMessage=vue.$t('common.loading');
    const loading=vue.$Message.loading({
        content: vue.$t('common.loading'),
        duration: 0
    });
    vue.result=[];
    util.get(url,params,function(result){
        if(successCallback){
            successCallback.call(this,result);
        }else{
            vue.result=result.result;
            vue.page=result.page;
            if(!result.result || result.result.length==0){
                vue.noDataMessage=vue.$t('common.noData');
            }
            setTimeout(loading,1);
        }
    },function(error){
        if(failCallback){
            failCallback.call(this,error);
        }else{
            setTimeout(loading,1);
            vue.noDataMessage=vue.$t('common.loadError')+error.message;
        }
    });
};
util.saveRecord=function(vue,formId,createUrl,updateUrl,keyId){
    //校验有效性
    vue.$refs[formId].validate((valid) => {
        if (valid) {
            let data=JSON.parse(JSON.stringify(vue.formValidate));
            let url=createUrl;
            let fetchMethod=util.post;
            if(data[keyId] && vue.editType=="edit"){
                url=updateUrl;
                fetchMethod=util.put;
            }else{
                data[keyId]=null;
            }
            vue.saveLoading=true;
            fetchMethod(url,data,function(){
                vue.addOrEditModal=false;
                vue.saveLoading=false;
                vue.$Message.success(vue.$t('common.saveSuccessful'));
                vue.loadTable(1);
            },function(error){
                vue.saveLoading=false;
                vue.$Message.error(vue.$t('common.saveError')+error.message);
                
            });
        }
    });
};

export default util;