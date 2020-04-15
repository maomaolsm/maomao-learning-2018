package com.maomao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BlogMapper {
    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(int id);

    @Update("UPDATE blog SET name = 'li' WHERE id = 101 ")
    int update(int id);
}
