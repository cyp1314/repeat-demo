package com.example.repeatdemo.advice;

import com.example.repeatdemo.util.RSAUtil;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

//@Component
@ControllerAdvice
public class RequestBodyDecrypt implements RequestBodyAdvice {
//    @Reference
//    private EquipmentService equipmentService;
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;  // 必须为true才会执行beforeBodyRead和afterBodyRead方法
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        // 从请求头中获取equipmentNo，getFirst()方法根据请求头的名称获取值
        String equipmentNo = httpInputMessage.getHeaders().getFirst("equipmentNo");
        String privateKey = null;
//        List<EquipmentDTO> mapList = equipmentService.getByEquipmentNo(equipmentNo);
//        if (mapList.size() > 0) {
//            EquipmentDTO equipmentDTO = mapList.get(0);
//            privateKey = equipmentDTO.getProdPriKey();
//        }

        // 提取数据
        InputStream is = httpInputMessage.getBody(); // 从HTTPInputMessage中获取请求体，得到字节输入流
        byte[] data = new byte[is.available()];
        is.read(data);
        String dataStr = new String(data, StandardCharsets.UTF_8);
//        JSONObject json = JSONObject.parseObject(dataStr);
//        String decrypt = null;
//        try {
//            decrypt = RSAUtil.decryptByPrivateKey(json.getString("applyData"), privateKey); // 私钥解密后的请求体
//        } catch (Exception e) {
//            throw new RuntimeException("数据错误");
//        }
        // 将解密后的请求体封装到HttpInputMessage中返回
//        return new DecodedHttpInputMessage(httpInputMessage.getHeaders(),new ByteArrayInputStream(decrypt.getBytes(StandardCharsets.UTF_8)));

        return new DecodedHttpInputMessage(httpInputMessage.getHeaders(),new ByteArrayInputStream(dataStr.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type,Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    static class DecodedHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

        public DecodedHttpInputMessage(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}