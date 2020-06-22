package com.maomao.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt() {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println("------------------" + hashpw);

        System.out.println(
                BCrypt.checkpw("123", "$2a$10$YECvSkxruSkpS113J7aQtO94MP56Lc4/SZGe2oriwDCVjNTAcHs8q")
        );
    }

}
