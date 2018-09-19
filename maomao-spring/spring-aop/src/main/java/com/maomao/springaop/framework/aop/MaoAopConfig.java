package com.maomao.springaop.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 只是对 application 中的 expression 的封装
 * 目标代理对象的一个方法要增强
 * 由有自己实现的业务逻辑去增强
 * 配置文件的目的：告诉 spring，哪些类的哪些方法需要增强，增强的内容是什么
 * 对配置文件中所体现的内容进行封装
 * <p>
 * Created by maomao on 2018/9/8.
 */
public class MaoAopConfig {

    // 以目标对象的 method 作为 key，需要增强的代码内容作为 value
    private Map<Method, MaoAspect> points = new HashMap<Method, MaoAspect>();

    public void put(Method target, Object aspect, Method[] points) {
        this.points.put(target, new MaoAspect(aspect, points));
    }

    public MaoAspect get(Method method) {
        return this.points.get(method);
    }

    public boolean contains(Method method) {
        return this.points.containsKey(method);
    }

    // 对增强代码的封装
    public class MaoAspect {

        // 待会将 LogAspect 这个对象赋值给他
        private Object aspect;

        // 会将 LogAspect 的 before 方法和 after 方法赋值进来
        private Method[] points;

        public MaoAspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public void setAspect(Object aspect) {
            this.aspect = aspect;
        }

        public Method[] getPoints() {
            return points;
        }

        public void setPoints(Method[] points) {
            this.points = points;
        }
    }
}
