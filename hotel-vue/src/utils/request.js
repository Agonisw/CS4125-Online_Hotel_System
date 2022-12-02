import axios from 'axios';
import Element from 'element-ui'
const service = axios.create({
    //本地
   // baseURL: 'http://39.99.158.36:15465/api',
    baseURL: 'http://127.0.0.1:9001/security',
    //47服务器
    // baseURL: 'http://47.103.39.109:15465/api',
    // baseURL:'http://47.111.21.35:8082/api',
    timeout: 5000,
    headers: {
        'Content-Type' : "application/json; charset=utf-8"
    }
});
/*进行拦截*/
service.interceptors.request.use(config=>{
    //请求头  代表身份的请求头
    config.headers['Authorization'] =sessionStorage.getItem('Authorization')
    return config
})

/*返回的结果*/
service.interceptors.response.use(response=>{
        let res=response.data;
        if (res.code === 1){
            return response.data
        }else if (res.code === 1000){
            //登录接口返回
            // const jwt=response.headers['authorization'];
            // Element.Message.success("登录接口返回======"+jwt)
            return  response
        }else if(res.code === 403){
            Element.Message.success(res.msg);
            return false;
        } else {
            return  response
        }
        // else {
        //     //异常的话 弹出异常信息
        //     Element.Message.error(!res.msg? "系统异常" : res.msg)
        //     //拒绝的原因 Promise.reject()方法返回一个带有拒绝原因的Promise对象。
        //     return Promise.reject(response.data.msg)
        // // }
        // /* 200 正常 500 异常 401 权限不够 404 找不到*/
    },error => {
    // 任何超出2xx范围的状态码都会触发此函数，这里主要用于处理响应错误
    const { status } = error.response
    console.log(status);
        // if (error.response.data){
        //     error.message =error.response.data.msg
        // }
        // if (error.response.status === 401){
        //     router.push("/login")
        // }
    if (status === 403){
        Element.Message.warning('暂无权限');
    }
        return Promise.reject(error)
    }
)

export default service;
