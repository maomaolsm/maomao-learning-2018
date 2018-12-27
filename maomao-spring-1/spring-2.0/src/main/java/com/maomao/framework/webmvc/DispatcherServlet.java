package com.maomao.framework.webmvc;

import com.maomao.framework.context.MaoApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet 只是作为一个 mvc 的启动入口
 * <p>
 * Created by maomao on 2018/12/27.
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {

        MaoApplicationContext maoApplicationContext = new MaoApplicationContext();

    }
}
