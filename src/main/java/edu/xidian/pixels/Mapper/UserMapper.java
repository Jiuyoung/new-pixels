package edu.xidian.pixels.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import edu.xidian.pixels.Entity.User;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(int id);

    @Select("select * from user where account = #{account}")
    User findByAccount(String account);

    @Insert("insert into user(name,account,password,message,regist_time,phone,stars_num,article_num)" + 
            "values(#{name},#{account},#{password},#{message},#{registTime},#{phone},#{starsNum},#{articleNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("update user set password=#{password}, message=#{message}, phone=#{phone} where id=#{id}")
    int update(User user);
}