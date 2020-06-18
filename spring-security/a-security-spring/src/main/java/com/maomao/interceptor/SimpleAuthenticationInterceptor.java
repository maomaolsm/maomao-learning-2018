package com.maomao.interceptor;

import com.maomao.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验url是否在用户的权限内
        // 取出用户信息
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null) {
            writeContent(response, "请登录");
        }
        UserDto userDto = (UserDto) object;
        // 请求的url
        String url = request.getRequestURI();

        if(userDto.getAuthorities().contains("p1")&& url.contains("/r/r1")){
            return true;
        }
        if(userDto.getAuthorities().contains("p2")&& url.contains("/r/r2")){
            return true;
        }
        writeContent(response,"没权限");
        return false;
    }

    // 响应信息给客户端
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
        response.resetBuffer();
    }
}
