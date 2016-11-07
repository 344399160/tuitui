package com.qbin.crawlers.common.schedule.job;

import com.qbin.crawlers.common.cache.service.RedisTemplate;
import com.qbin.crawlers.common.globalconst.WeiXinConst;
import com.qbin.crawlers.common.util.Json;
import com.qbin.crawlers.common.util.Utils;
import com.qbin.crawlers.common.schedule.util.QuartsUtil;
import com.qbin.crawlers.weixin.model.WeiXinInfo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：微信登录用户同步定时
 * author qiaobin   2016/10/18 17:53.
 */
public class WeixinSyncSchedule implements Job {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String wxUserId = jobExecutionContext.getJobDetail().getKey().getName();
        WeiXinInfo weiXinInfo = null;
        try {
            weiXinInfo = (WeiXinInfo) Json.toObject(redisTemplate.get(wxUserId).toString(), WeiXinInfo.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String url = WeiXinConst.url_synccheck.replace("{0}", weiXinInfo.getWxsid());
        url = url.replace("{1}", weiXinInfo.getWxuin());
        url = url.replace("{2}", weiXinInfo.getDeviceid());
        url = url.replace("{3}", weiXinInfo.getSyncKeyStr());
        url = url.replaceAll("[|]", "%7C");
        long t = System.currentTimeMillis();
        url = url.replace("{t}", ""+t);
        url = url.replace("{t}", ""+t);
        String res = Utils.get(url);
        System.out.println("sync back="+res);
        Pattern pat = Pattern.compile("window.synccheck=\\{retcode:\"([0-9]+)\",selector:\"([0-9]+)\"}");
        Matcher mat = pat.matcher(res);
        if(mat.find()){
            int retcode = Integer.valueOf(mat.group(1));
            if(retcode != 0){
                QuartsUtil.removeJob(weiXinInfo.getName());
            }
        }
        System.out.println("heart beating");
    }
}
