package com.maomao.stream;

import com.maomao.domain.User;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * {@link User} 消息 stream 接口定义
 *
 * @author senmao.li
 * @since 2018/11/22 15:08
 */
public interface UserMessage {
    String INPUT = "user-message";

    @Input(INPUT)
    SubscribableChannel input();
}
