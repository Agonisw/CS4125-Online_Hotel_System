package com.xxx.common.utils;


public enum ResultCode {
    SUCCESS(1,"operate successfully"),

    ERROR(500," server error"),

    SERVER_ERROR_RETURN_TRANSACTION(501,"Server exception, rollback transaction"),

    PLEASE_LOGIN_FIRST(401,"please log in first"),

    PERMISSION_DENIED_ERROR(403,"insufficient privileges"),

    USER_NAME_OR_PASSWORD_ERROR(405,"The user name or password is incorrect"),

    MONEY_NOT_ENOUGH(406,"not sufficient funds"),

    DUPLICATE_USER_NAME(407,"not sufficient funds"),


    LOGIN_SUCCESS(1001,"login successfully"),

    SAVE_ERROR(1001,"Failed to save data"),

    UPDATE_ERROR(1002,"Failed to update data"),

    DELETE_ERROR(1003,"Failed to delete data"),

    USER_IS_EXIST(1004,"The user already exists. Please change the account"),

    UPDATE_PASSWORD_ERROR(1005,"Password update failed"),

    MENU_DELETE_ERROR(1006,"There are submenus under the current menu. Please delete the submenus first"),

    SERVER_FILE_PATH_EMPTY(1007,"The storage file path cannot be found on the current server"),

    UPLOAD_IMAGE_ERROR(1008,"Upload Fail"),

    IMPORT_EXCEL_ERROR(1009,"导入Excel失败"),

    MATETIAL_ID_IS_EMPTY(1010,"物料id集合不得为空"),

    NOT_DELETE_MY(1011,"Unable to delete their own account information"),

    ROLE_HAS_USER(1012,"The current role has user information and cannot be deleted"),


    ENTITY_CHECK_EXCEPTION(2000,"实体校验异常"),

    RUN_EXCEPTION(2001,"run运行异常"),

    ASSERT_EXCEPTION(2002,"Assert异常----"),


    DEPT_REPEAT_ERROR(2000,"组织名称重复"),

    DEPT_HAS_CHILDREN(2001,"当前组织下还有子组织,请先删除子组织"),

    DEPT_HAS_DEVICE(2002,"当前组织下还有设备,请先删除子设备"),

    DEVICEID_REPEAT(2003,"设备id重复,请重新添加"),

    DEVICEID_NOT_EMPTY(2004,"设备id不得为空"),

    ELECTRONIC_NAME_EMPTY(2005,"围栏名称不得为空"),

    ELECTRONIC_NAME_REPEAT(2006,"围栏名称重复"),

    ELECTRONIC_ID_REPEAT(2007,"电子围栏id不得为空"),



    SAVE_FILE_CHUNK_ERROR(2010,"存储分片文件失败"),

    ALL_FILE_CHUNK_UPLOAD_COMPLETE(2011,"完整文件已存在，直接跳过上传，实现秒传"),

    PART_FILE_EXIST(2012,"部分文件块已存在，继续上传剩余文件块，实现断点续传"),

    FILE_NOT_UPLOAD(2013,"暂未上传"),

    GB_ID_REPEAT(2014,"当前国标平台Id已添加,无需重复添加"),

    GB_PLATE_HAS_CHANNEL(2015,"当前国标平台下有通道设备,请先删除通道设备"),

    CHOOSE_DEVICEID(2016,"请选择设备"),

    LOG_ID_EMPTY(2017,"日志id列表不得为空"),

    LOG_TYPE_EMPTY(2018,"日志类型不得为空"),

    FILE_ID_EMPTY(2019,"文件id不得为空"),

    FILE_UPLOAD_FAILED(2019,"文件上传失败"),

    TASK_DISTRIBUTE_FAILED(2020,"派发接口失败"),

    TASK_ID_EMPTY(2021,"任务id不得为空"),

    TASK_STATUS_EMPTY(2022,"任务状态不得为空"),

    PROJECT_VERSION_EMPTY(2023,"当前项目无版本"),

    DEVICE_NOT_EMPTY(2024,"设备不得为空"),

    STORAGE_SPACE_NOT_ENOUGH(2025,"存储已关闭"),

    /**
     * 身份认证异常
     */
    AUTH_NOT_CORRECT(3000,"登录失败,账号或者密码错误"),


    LOGIN_NOT_EMPTY(3003,"登陆人不得为空"),

    CHOOSE_EXECUTE_PERSON(3004,"请选择执行人"),

    ADMIN_FORBIDDEN_DELETE(3005,"超级管理员用户无法删除"),

    USER_HAS_FORBIDDEN(3006,"当前用户已经被封禁,无法登录"),

    USERNAME_IS_EMPTY(3007,"用户名不存在"),

    GB_DEVICE_IS_EMPTY(3008,"当前国标设备在数据库中不存在,请联系相关人员添加"),

    IMAGE_ID_IS_EMPTY(3009,"底图id不得为空"),

    PUSH_DEVICE_NOT_EMPTY(3010,"推送设备不得为空"),

    AUTHOR_DEVICE_COUNT(3011,"设备已达授权最高路数"),

    CERTIFICATE_VERIFY_FAILED(3022,"您的证书无效，请核查服务器是否取得授权或重新申请证书"),

    /**
     * 查询相关
     */

    CROSS_MONTH_FORBIDDEN(4000,"不允许跨月查询"),

    START_TIME_EMPTY(4001,"开始时间不得为空"),

    END_TIME_EMPTY(4002,"结束时间不得为空"),

    DELETE_MONTH_FORBIDDEN(4003,"不允许跨月删除"),


    /**
     * 统计相关
     */
    IS_ACTIVE_EMPTY(5000,"活跃度参数不得为空"),

    IS_TIME_EMPTY(5001,"近期时间参数不得为空"),

    USE_FREQUENCYQUENCY_EQUIPMENT_COUNT_IS_EMPTY(5002,"recentDay参数不得为空"),

    CURRENT_MONTH_IS_EMPTY(5003,"currentMonth参数不得为空");




    private int code;

    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg =  msg;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
}

