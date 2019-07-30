package com.maomao.bean;

import lombok.*;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/25 20:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
}
