package com.maomao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/11/28 14:46
 */
@RestController
public class StatusController {

    @GetMapping("/status")
    public String status() {
        return "OK";
    }
}
