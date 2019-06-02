package edu.xidian.pixels.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.xidian.pixels.Entity.User;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE ID = #{id}")
    User findById(@Param("id") int id);
}