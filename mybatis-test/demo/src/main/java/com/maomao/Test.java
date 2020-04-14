package com.maomao;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/mytest?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Blog blog = sqlSession.selectOne("com.maomao.BlogMapper.selectBlog", 101);
        System.out.println(blog.getName());

        int a = sqlSession.update("com.maomao.BlogMapper.update", "1");
        System.out.println(a);
//        sqlSession.commit();

        Blog blog1 = sqlSession.selectOne("com.maomao.BlogMapper.selectBlog", 101);
        System.out.println(blog1.getName());

        sqlSession.close();
    }
}
