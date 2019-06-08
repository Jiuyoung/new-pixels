package edu.xidian.pixels.VO;

import java.util.Date;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ArticleVO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    private int articleId;
    private AuthorVO author;
    private String title;
    private Date publishTime;
    private String tag;
    private String content;
    private int stars;
    private int comments;

    public static ArticleVO trans(Article article, AuthorVO authorVO, Tags tags){
        ArticleVO articleVO=new ArticleVO();
        articleVO.setArticleId(article.getId());
        articleVO.setAuthor(authorVO);
        articleVO.setTitle(article.getTitle());
        articleVO.setPublishTime(article.getPublishTime());
        articleVO.setTag(tags.getName());
        articleVO.setContent(article.getContent());
        articleVO.setStars(article.getStars());
        articleVO.setComments(article.getComments());
        return articleVO;
    }
}