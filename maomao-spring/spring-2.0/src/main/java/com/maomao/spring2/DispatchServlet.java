package com.maomao.spring2;

import com.maomao.spring2.context.MaoApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet 只是作为一个 MVC 的启动入口
 * <p>
 * Created by maomao on 2018/8/19.
 */
public class DispatchServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        MaoApplicationContext context = new MaoApplicationContext(config.getInitParameter(LOCATION));

    }
}
