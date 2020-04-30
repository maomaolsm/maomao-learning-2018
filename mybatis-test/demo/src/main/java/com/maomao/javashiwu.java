package com.maomao;

import java.sql.*;

public class javashiwu {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = (Connection) DriverManager.getConnection(url, user, password);

        Statement stmt = (Statement) conn.createStatement();

        try {


            conn.setAutoCommit(false);  //将自动提交设置为false

            stmt.execute("UPDATE blog SET name = 'li' WHERE id = 101"); //执行修改操作

            stmt.execute("SELECT * FROM blog WHERE id = 101");  //执行查询操作

            conn.commit();      //当两个操作成功后手动提交

        } catch (Exception e) {

            conn.rollback();    //一旦其中一个操作出错都将回滚，使两个操作都不成功

            e.printStackTrace();

        }
    }
}
