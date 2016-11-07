package com.qbin.crawlers.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：Json 转换
 * author qiaobin   2016/9/6 13:56.
 */
@Log4j
public class Json {

    /**
      * 功能描述：将对象转为JSON字符串
      * @author qiaobin
      * @date 2016/9/6  14:53
      * @param object
      */
    public static String toJsonString (Object object) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
      * 功能描述：将JSON转为List<Object>
      * @author qiaobin
      * @date 2016/9/8  17:58
      * @param jsonString
      * @param clazz
      */
    public static List toObjectList(String jsonString, Class<?> clazz) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List lst = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            lst = mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw e;
        }
        return lst;
    }

    /**
     * 功能描述：JSON转Object
     * @author qiaobin
     * @date 2016/9/6  14:53
     * @param clazz
     * @param jsonString
     */
    public static Object toObject (String jsonString, Class<?> clazz) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(jsonString, clazz);
        return obj;
    }
}
