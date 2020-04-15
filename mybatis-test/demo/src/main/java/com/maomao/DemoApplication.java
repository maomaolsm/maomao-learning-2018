package com.maomao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DemoApplication {

    public static void main(String[] args) throws IOException {

        String resource = "org/mybatis/example/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Blog blog = (Blog) sqlSession.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);

        sqlSession.close();

    }

//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new ClassPathResource("mybatis.xml").getInputStream();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserDao mapper = sqlSession.getMapper(UserDao.class);
//        System.out.println(mapper.get(1L)); System.out.println("-------------------");
//        System.out.println(mapper.get(1L));
//    }
}
