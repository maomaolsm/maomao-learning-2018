package com.maomao.springaop.framework.context;

/**
 * Created by maomao on 2018/9/6.
 */
public abstract class MaoAbstractApplicationContext {

    // �ṩ��������д
    protected void onRefresh()  {
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();
}
