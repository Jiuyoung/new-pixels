package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where id=#{id}")
    Comment select(int id);

    @Select("select * from comment where article_id=#{articleId}")
    List<Comment> selectByArticle(int articleId);

    @Select("select * from comment where user_id=#{userId}")
    List<Comment> selectByUser(int userId);

    @Insert("insert into comment(article_id,user_id,message,time) " +
            "values(#{articleId},#{userId},#{text},#{time})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Comment comment);

    @Delete("delete from comment where id=#{id}")
    int delete(int id);
}
