package com.maomao.listener;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/22 16:17
 */
public class BeforeConfigFileApplicationListener implements SmartApplicationListener, Ordered {
    @Override
    public int getOrder() {
        // 这里减一就比 ConfigFileApplicationListener 优先级更高
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            Environment environment = preparedEvent.getEnvironment();

            // getOrder + 1 就可以读到 (------------environment.getProperty("name") : application.properties.name.maomao)
            // getOrder - 1 就可以读不到 (------------environment.getProperty("name") : null)
            System.out.println("------------environment.getProperty(\"name\") : "
                    + environment.getProperty("name"));
        }
        if (event instanceof ApplicationPreparedEvent) {
        }
    }
}
