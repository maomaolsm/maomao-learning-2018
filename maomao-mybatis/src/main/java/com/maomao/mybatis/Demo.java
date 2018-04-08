package com.maomao.mybatis;

import org.apache.ibatis.session.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by maomao on 2018/4/6.
 */
public class Demo {

    public static void main(String[] args) throws FileNotFoundException {
        TestMapper testMapper = getSqlSession().getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test.getName());
        System.out.println(new File(".").getAbsolutePath());
    }

    private static SqlSession getSqlSession() throws FileNotFoundException {
        InputStream configFile = new FileInputStream("maomao-mybatis\\src\\main\\java\\com\\maomao\\mybatis\\mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        return sqlSessionFactory.openSession();
    }


}
