package com.maomao.imoocdiveinspringboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/14 11:52
 */
@Profile("Java7")
@Service
public class Java7CalculateService implements CalculateService {
    @Override
    public Integer sum(Integer... values) {

        System.out.println("Java 7 for 循环实现");

        int sum = 0;
        for (int i = 0; i <= values.length; i++) {
            sum += i;
        }
        return sum;

    }
}
