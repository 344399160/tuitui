package com.qbin.crawlers.common.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述：TODO
 * author qiaobin   2016/9/30 13:41.
 */
public class PicDownloader {

    public static void download(String urlString, String filename,String savePath) {
        OutputStream os = null;
        InputStream is = null;
                // 构造URL
        try {
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5*1000);
            // 输入流
            is = con.getInputStream();

            // 1K的数据缓冲
            byte[] bs = new byte[1024*50];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf=new File(savePath);
            if(!sf.exists()){
                sf.mkdirs();
            }
            os = new FileOutputStream(sf.getPath()+"\\"+filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
        } catch (Exception e) {
            System.out.println("");
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
            }
        }
    }
}
