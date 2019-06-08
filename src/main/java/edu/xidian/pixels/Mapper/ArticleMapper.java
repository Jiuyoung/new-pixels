package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Article;
import org.apache.ibatis.annotations.*;

/**
 * ArticleMapper
 */
@Mapper
public interface ArticleMapper {

    @Select("select * from article where id=#{id}")
    Article findById(int id);

    @Select("select * from article where author=#{author}")
    Article findByAuthor(int author);

    @Insert("insert into article(id,title,author,publish_time,tag,content,stars,comments)" +
            " values(#{id},#{title},#{author},#{publishTime},#{tag},#{content},#{stars}," +
            "#{comments})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Article article);

    @Update("update article set title=#{title},tag=#{tag},context=#{context},stars=#{stars}" +
            ",comments=#{comments} where id=#{id}")
    int update(Article article);
}