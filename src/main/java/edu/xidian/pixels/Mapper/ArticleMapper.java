package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleMapper
 */
@Repository
@Mapper
public interface ArticleMapper {

    @Select("select * from article where id=#{id}")
    Article findById(int id);

    @Select("select * from article where author=#{author}")
    List<Article> findByAuthor(int author);

    @Insert("insert into article(title,author,publish_time,tag,summary,content,stars,comments)" +
            " values(#{title},#{author},#{publishTime},#{tag},#{summary},#{content},#{stars}," +
            "#{comments})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Article article);

    @Update("update article set title=#{title},tag=#{tag},content=#{content},stars=#{stars}" +
            ",comments=#{comments} where id=#{id}")
    int update(Article article);

    //只修改点赞数
    @Update("update article set stars=#{stars} where id=#{id}")
    int editStars(Article article);

    //按照点赞数和评论数进行推荐
    @Select("select * from article order by stars desc, comments desc")
    List<Article> recommend();
}