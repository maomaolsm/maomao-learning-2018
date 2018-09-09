package com.maomao.springaop.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 只是对 application 中的 expression 的封装
 *
 * Created by maomao on 2018/9/8.
 */
public class MaoAopConfig {

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

    public class MaoAspect {
        private Object aspect;
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
