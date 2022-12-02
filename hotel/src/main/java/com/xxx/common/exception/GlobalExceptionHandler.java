package com.xxx.common.exception;//package com.example.security.common.exception;
//import com.example.security.utils.Result;
//import com.example.security.utils.ResultCode;
//import com.example.security.utils.ResultUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * 全局处理异常
// */
//@Slf4j
//@RestControllerAdvice //进行统一异常处理
//public class GlobalExceptionHandler {
//
//    //实体类检验异常
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result handler(MethodArgumentNotValidException e){
//
//        BindingResult bindingResult = e.getBindingResult();
//        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
//
//        log.error("实体校验异常-------",objectError.getDefaultMessage());
////        return ResultUtil.error(-1,e.getMessage());
//        return ResultUtil.error(ResultCode.ENTITY_CHECK_EXCEPTION,e.getMessage());
//    }
//
//
//    //运行异常
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = RuntimeException.class)
//    public Result handler(RuntimeException e){
//        log.error("run运行异常-------",e);
//        return ResultUtil.error(ResultCode.RUN_EXCEPTION,e.getMessage());
//    }
//
//    //形参异常
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public Result handler(IllegalArgumentException e){
//        log.error("Assert异常-------",e.getMessage());
//        return ResultUtil.error(ResultCode.ASSERT_EXCEPTION,e.getMessage());
//    }
//}
