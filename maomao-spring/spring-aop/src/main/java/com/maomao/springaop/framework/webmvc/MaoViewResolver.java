package com.maomao.springaop.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设计这个类的主要目的是：
 * 1、将一个静态文件变为一个动态文件
 * 2、根据用户传送参数不同，产生不同的结果
 * 最终输出字符串，交给 response 输出
 * <p>
 * Created by maomao on 2018/8/28.
 */
public class MaoViewResolver {

    private String viewName;

    private File templateFile;

    public MaoViewResolver(String viewName, File templateFile) {
        this.viewName = viewName;
        this.templateFile = templateFile;
    }

    public String viewResolver(MaoModelAndView mv) throws Exception {
        StringBuffer sb = new StringBuffer();

        RandomAccessFile ra = new RandomAccessFile(this.templateFile, "r");

        String line = null;
        while (null != (line = ra.readLine())) {
            Matcher m = matcher(line);
            while (m.find()) {
                for (int i = 1; i < m.groupCount(); i++) {

                    // 要把 ￥{} 中间的这个字符串给取出来
                    String paramName = m.group();
                    Object paramValue = mv.getModel().get(paramName);
                    if (null == paramValue) {
                        continue;
                    }
                    line = line.replaceAll("￥\\{" + paramValue + "\\}", paramValue.toString());
                }
            }
            sb.append(line);
        }

        return sb.toString();
    }

    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("￥\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
