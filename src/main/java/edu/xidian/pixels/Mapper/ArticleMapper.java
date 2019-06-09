package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ArticleMapper
 */
@Mapper
public interface ArticleMapper {

    @Select("select * from article where id=#{id}")
    Article findById(int id);

    @Select("select * from article where author=#{author}")
    Article findByAuthor(int author);

    @Insert("insert into article(title,author,publish_time,tag,content,stars,comments)" +
            " values(#{title},#{author},#{publishTime},#{tag},#{content},#{stars}," +
            "#{comments})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Article article);

    @Update("update article set title=#{title},tag=#{tag},content=#{content},stars=#{stars}" +
            ",comments=#{comments} where id=#{id}")
    int update(Article article);

    @Update("update article set stars=#{stars} where id=#{id}")
    int editStars(Article article);

    //挑选点赞数大于某值的文章
    @Select("select * from article where stars>#{stars}")
    List<Article> recommend(int stars);
}