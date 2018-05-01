package com.maomao.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by maomao on 2018/5/1.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> beanNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("����dopost");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //��ʼ��ʼ���Ľ���


        //��λ
        doLoadConfig(config.getInitParameter("contentConfigLocation"));


        //����
        doScanner();


        //ע��
        doRegistry();


        //�Զ�����ע��
        doAutowired();


        //�����springMVC��������һ��HandleMapping


        //��@RequestMapping�����õ�url��methodͨ������ȥ����
        //�Ա��ڴ����������û������url�Ժ��ܹ��ҵ�����ִ�е�Methodͨ������ȥ����
        initHandleMapping();
    }

    private void initHandleMapping() {

    }

    private void doRegistry() {

    }

    private void doAutowired() {

    }

    private void doScanner() {

    }

    private void doLoadConfig(String location) {
        //��spring����ͨ��Readerȥ���ҺͶ�λ��
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);

        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
