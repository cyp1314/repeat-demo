//package com.example.repeatdemo.advice;
//
//import com.alibaba.fastjson.JSON;
//import com.example.repeatdemo.vo.Result;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//import sun.security.provider.MD5;
//
//import javax.annotation.PostConstruct;
//
//@ControllerAdvice
//public class MyResponseBodyAdvice implements ResponseBodyAdvice<Result> {
//
//    /**
//     * 加密串一
//     */
//    private static String md5_keyone;
//    /**
//     * 加密串二
//     */
//    private static String md5_keytwo;
//
//    @PostConstruct
//    public void init() throws Exception {
//        md5_keyone = Utils.PT.getProps("md5_keyone");
//        md5_keytwo = Utils.PT.getProps("md5_keytwo");
//    }
//
//    /**
//     * 判断支持的类型
//     *
//     * @param returnType
//     * @param converterType
//     * @return
//     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter,
//     *      java.lang.Class)
//     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return returnType.getMethod().getReturnType().isAssignableFrom(Result.class);
//    }
//
//    /**
//     * 对于结果进行加密
//     *
//     * @param body
//     * @param returnType
//     * @param selectedContentType
//     * @param selectedConverterType
//     * @param request
//     * @param response
//     * @return
//     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object,
//     *      org.springframework.core.MethodParameter,
//     *      org.springframework.http.MediaType, java.lang.Class,
//     *      org.springframework.http.server.ServerHttpRequest,
//     *      org.springframework.http.server.ServerHttpResponse)
//     */
//    @Override
//    public Result beforeBodyWrite(Result body, MethodParameter returnType, org.springframework.http.MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        String jsonString = JSON.toJSONString(body.getData());
//        System.out.println(jsonString);
//        // 第一次加密
//        String data_encode_one = MD5.md(md5_keyone + jsonString);
//        // 第二次加密
//        String data_encode_two = MD5.md5(data_encode_one + md5_keytwo);
//        body.setToken(data_encode_two);
//        return body;
//    }
//
//}