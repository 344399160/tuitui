package com.qbin.crawlers.common.util;

import org.springframework.util.ClassUtils;

/**
 * 描述：系统工具
 * author qiaobin   2016/9/22 16:00.
 */
public class ApplicationUtil {

    /**
      * 功能描述：获取文件根目录
      * @author qiaobin
      * @date 2016/9/22  16:01
      * @param
      */
    public static String getRootPath() {
        String root = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return root;
    }
}
