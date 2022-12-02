
import request from '../utils/request';
import da from 'element-ui/src/locale/lang/da';

export const Login = query =>{
    return request({
        url:'/login',
        method:'post',
        params:query,
    })
};


export const getUserList = query =>{
    return request({
        url:'/user/getUserInfo',
        method:'get',
        params:query,
    })
};



export const saveAndUpdateUserInfo = data =>{
    return request({
        url:'/user/saveAndUpdateUserInfo',
        method:'post',
        data
    })
};

export const saveAndUpdateUserInfo2 = data =>{
    return request({
        url:'/user/updateUserInfo2',
        method:'put',
        data
    })
};

export const registerUser = data =>{
    return request({
        url:'/user/register',
        method:'post',
        data
    })
};



export const reMoney = data =>{
    return request({
        url:'/user/re',
        method:'post',
        params:data
    })
};

export const delUserInfo = id =>{
    return request({
        url:'/user/delUserInfo',
        method:'delete',
        params:{id:id},
    })
};

export const getProductList = query =>{
    return request({
        url:'/product/getProductInfo',
        method:'get',
        params:query,
    })
};



export const saveProductInfo = data =>{
    return request({
        url:'/product/saveProductInfo',
        method:'post',
        data
    })
};

export const updateProductInfo = data =>{
    return request({
        url:'/product/updateProductInfo',
        method:'put',
        data
    })
};

export const delProductInfo = id =>{
    return request({
        url:'/product/delProductInfo',
        method:'delete',
        params:{id:id},
    })
};



export const getSpecList = query =>{
    return request({
        url:'/spec/getSpecInfo',
        method:'get',
        params:query,
    })
};



export const saveAndUpdateSpecInfo = data =>{
    return request({
        url:'/spec/saveAndUpdateSpecInfo',
        method:'post',
        data
    })
};

export const delSpecInfo = id =>{
    return request({
        url:'/spec/delSpecInfo',
        method:'delete',
        params:{id:id},
    })
};

export const getCarList = query =>{
    return request({
        url:'/car/getCarInfo',
        method:'get',
        params:query,
    })
};
export const getMyDiscount = query =>{
    return request({
        url:'/discount/getDiscountInfo',
        method:'get',
        params:query,
    })
};



export const saveAndUpdateCarInfo = data =>{
    return request({
        url:'/car/saveAndUpdateCarInfo',
        method:'post',
        data
    })
};

export const delCarInfo = id =>{
    return request({
        url:'/car/delCarInfo',
        method:'delete',
        params:{id:id},
    })
};

export const sendPay = data =>{
    return request({
        url:'/sandboxPay',
        method:'post',
        data
    })
};



export const getOrderList = query =>{
    return request({
        url:'/order/getOrderInfo',
        method:'get',
        params:query,
    })
};



export const saveAndUpdateOrderInfo = data =>{
    return request({
        url:'/order/saveAndUpdateOrderInfo',
        method:'post',
        data
    })
};

export const delOrderInfo = id =>{
    return request({
        url:'/order/delOrderInfo',
        method:'delete',
        params:{id:id},
    })
};

export const returnHotel = id =>{
    return request({
        url:'/order/returnHotel',
        method:'post',
        params:{id:id},
    })
};

export const getVideoList = query =>{
    return request({
        url:'/video/getVideoInfo',
        method:'get',
        params:query,
    })
};



export const saveAndUpdateVideoInfo = data =>{
    return request({
        url:'/video/saveAndUpdateVideoInfo',
        method:'post',
        data
    })
};

export const delVideoInfo = id =>{
    return request({
        url:'/video/delVideoInfo',
        method:'delete',
        params:{id:id},
    })
};
export const getCommentInfo= query => {
    return request({
        url: '/comment/getCommentInfo',
        method: 'get',
        params: query
    });
};
export const deleteCommentInfo =id =>{
    return request({
        url:'/comment/delCommentInfo',
        method:'delete',
        params:{id:id},
    })
};

export const saveAndUpdateCommentInfo =data =>{
    return request({
        url:'/comment/saveAndUpdateCommentInfo',
        method:'post',
        params:data,
    })
};
export const getNewsList = query =>{
    return request({
        url:'/news/getNewsInfo',
        method:'get',
        params:query,
    })
};



export const saveNewsInfo = data =>{
    return request({
        url:'/news/saveNewsInfo',
        method:'post',
        data
    })
};


export const updateNewsInfo = data =>{
    return request({
        url:'/news/updateNewsInfo',
        method:'put',
        data
    })
};
export const delNewsInfo = id =>{
    return request({
        url:'/news/delNewsInfo',
        method:'delete',
        params:{id:id},
    })
};
export const getEchartData = query =>{
    return request({
        url:'/product/getDataForEchart',
        method:'get',
        params:query,
    })
};

export const getDisCountRate = userId =>{
    return request({
        url:'/order/getDiscountRate',
        method:'get',
        params:{userId:userId},
    })
};


export const getNavList = query =>{
    return request({
        url:'/menu/nav',
        method:'get',
        params:query,
        headers:{
            Authorization: sessionStorage.getItem("Authorization")
        }
    })
};


export const getMenuList = query =>{
    return request({
        url:'/menu/getMenuList',
        method:'get',
        params:query,
    })
};

export const saveMenuInfo = data =>{
    return request({
        url:'/menu/saveMenuInfo',
        method:'post',
        data,
    })
};

export const updateMenuInfo = data =>{
    return request({
        url:'/menu/updateMenuInfo',
        method:'put',
        data,
    })
};

export const delMenuInfo = id =>{
    return request({
        url:'/menu/delMenuInfo',
        method:'delete',
        params:{id:id}
    })
};


export const saveRoleMenu = data =>{
    return request({
        url:'/roleMenu/saveRoleMenu',
        method:'post',
        data,
    })
};



export const getRoleMenuByRoleId = id =>{
    return request({
        url:'/roleMenu/getRoleMenuByRoleId',
        method:'get',
        params:{roleId:id},
    })
};

export const saveRoleInfo = data =>{
    return request({
        url:'/role/saveRoleInfo',
        method:'post',
        data,
    })
};

export const updateRoleInfo = data =>{
    return request({
        url:'/role/updateRoleInfo',
        method:'put',
        data,
    })
};

export const delRoleInfo = id =>{
    return request({
        url:'/role/delRoleInfo',
        method:'delete',
        params:{id:id},
    })
};



export const  getUserRoleInfoByUserId = id =>{
    return request({
        url:'/userRole/getUserRoleInfoByUserId',
        method:'get',
        params:{userId:id}
    })
};
export const getRoleList = query =>{
    return request({
        url:'/role/getRoleInfo',
        method:'get',
        params:query,
    })
};


export const saveUserInfo = query =>{
    return request({
        url:'/user/saveUserInfo',
        method:'post',
        data:query,
    })
};

export const getUserInfoBySecurityContextHolder = query =>{
    return request({
        url:'/user/getUserInfoBySecurityContextHolder',
        method:'get',
        params:query,
    })
};


export const updateUserInfo = query =>{
    return request({
        url:'/user/updateUserInfo',
        method:'put',
        data:query,
    })
};

export const updatePassword = query =>{
    return request({
        url:'/user/updatePassword',
        method:'put',
        data:query,
    })
};

export const saveUserRoleInfo = data =>{
    return request({
        url:'/userRole/saveUserRoleInfo',
        method:'post',
        data
    })
};
