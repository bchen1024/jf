
import Config from '../config/config.js';
import Vue from 'vue';
export default{
    doFetch(url,params,options,successCallback,failCallback){
        let defaultOp={
            method:'GET',
            headers:{
                'Accept':'application/json',
                'Content-type':'application/json',
                'currentLanguage':localStorage.getItem('currentLanguage')||'zh_CN',
                'Authorization':sessionStorage.token
            }
        };
        let baseUrl=Config.serviceUrl;
        let realUrl=baseUrl+url;
        let op=Object.assign({},defaultOp,options);
        if(op.method=='GET'){
            if(params){
                let realParams=[];
                for(var key in params){
                    realParams.push(key+'='+params[key]);
                }
                if(realParams.length>0){
                    realUrl+='?'+realParams.join('&');
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
    },
    doGet(url,params,successCallback,failCallback,options){
        this.doFetch(url,params,Object.assign({},{'method':'GET'},options),successCallback,failCallback);
    },
    doPost(url,params,successCallback,failCallback,options){
        this.doFetch(url,params,Object.assign({},{'method':'POST'},options),successCallback,failCallback);
    },
    doPut(url,params,successCallback,failCallback,options){
        this.doFetch(url,params,Object.assign({},{'method':'PUT'},options),successCallback,failCallback);
    },
    doDelete(url,params,successCallback,failCallback,options){
        this.doFetch(url,params,Object.assign({},{'method':'DELETE'},options),successCallback,failCallback);
    },
    doRequest(method='GET',url,params,successCallback,failCallback,options){
        this.doFetch(url,params,Object.assign({},{'method':method.toUpperCase()},options),successCallback,failCallback);
    }
};