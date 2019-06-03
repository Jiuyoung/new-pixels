package edu.xidian.pixels.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import edu.xidian.pixels.Entity.User;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE ID = #{id}")
    User findById(int id);

    @Select("SELECT * FROM user WHERE ACCOUNT = #{account}")
    User findByAccount(String account);

    @Insert("INSERT INTO user(name,account,password,message,regist_time,phone,stars_num,article_num)" + 
            "values(#{name},#{account},#{password},#{message},#{registTime},#{phone},#{starsNum},#{articleNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
}