package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Star;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StarMapper {

    @Select("select * from star where article_id=#{articleId} and user_id=#{userId}")
    Star findByUserAndArticle(int userId, int articleId);

    @Select("select * from star where id=#{id}")
    Star select(int id);

    @Select("select * from star where article_id=#{articleId}")
    List<Star> findByArticle(int articleId);

    @Select("select * from star where user_id=#{userId}")
    List<Star> findByUser(int userId);

    @Select("select * from star where article_id=#{articleId} and " +
            "user_id=#{userId}")
    Star findByArticleAndUser(int articleId,int userId);

    @Insert("insert into star(article_id,user_id,star_time)" +
            "values(#{articleId},#{userId},#{starTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Star star);

    @Delete("delete from star where id=#{id}")
    int delete(int id);
}
