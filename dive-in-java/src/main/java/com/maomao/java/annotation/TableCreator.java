package com.maomao.java.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/20 17:03
 */
class TableCreator {

    static String createTableSql(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        DBTable dbTable = clazz.getAnnotation(DBTable.class);

        // 如果没有表注解，则直接返回
        if (dbTable == null) {
            System.out.println(className + " is not DBTable annotation. ");
            return null;
        }

        String tableName = dbTable.name();
        // 如果 name 为空，则用 class name
        if (tableName.length() < 1) {
            tableName = clazz.getName();
        }

        List<String> columnList = new ArrayList<String>();

        // 通过 class 类的 API 获取所有成员字段
        for (Field field : clazz.getDeclaredFields()) {
            String columnName;

            // 获取字段上的所有注解
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1) {
                continue;
            }

            // SQLInteger 类型的处理
            if (annotations[0] instanceof SQLInteger) {
                SQLInteger sqlInteger = (SQLInteger) annotations[0];
                if (sqlInteger.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sqlInteger.name();
                }
                columnList.add(columnName + " INT"
                        + getConstraints(sqlInteger.constraint()));
            }

            // SQLString 类型的处理
            if (annotations[0] instanceof SQLString) {
                SQLString sqlString = (SQLString) annotations[0];
                if (sqlString.name().length() < 1) {
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sqlString.name();
                }
                columnList.add(columnName + " VARCHAR("
                        + sqlString.value()
                        + ")"
                        + getConstraints(sqlString.constraint()));
            }
        }

        // 建表
        StringBuilder createCommand = new StringBuilder();
        createCommand.append("CREATE TABLE")
                .append(tableName)
                .append("(");
        for (String column : columnList) {
            createCommand.append("\n")
                    .append(column)
                    .append(",");
        }

        return createCommand
                .substring(0, createCommand.length() - 1)
                + ");";
    }

    /**
     * 获取字段的其他约束
     *
     * @param constraints
     * @return string
     */
    private static String getConstraints(Constraints constraints) {
        String string = "";
        if (!constraints.allowNull()) {
            string += " NOT NULL";
        }
        if (!constraints.primaryKey()) {
            string += " PRIMARY KEY";
        }
        if (!constraints.unique()) {
            string += " UNIQUE";
        }
        return string;
    }
}
