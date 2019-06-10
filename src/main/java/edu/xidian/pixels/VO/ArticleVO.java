package edu.xidian.pixels.VO;

import java.io.Serializable;
import java.util.Date;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ArticleVO
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int articleId;
    private AuthorVO author;
    private String title;
    private Date publishTime;
    private String tag;
    private String summary;
    private String content;
    private int stars;
    private int comments;

    public static ArticleVO trans(Article article, AuthorVO authorVO, Tags tags){
        // ArticleVO articleVO=new ArticleVO();
        // articleVO.setArticleId(article.getId());
        // articleVO.setAuthor(authorVO);
        // articleVO.setTitle(article.getTitle());
        // articleVO.setPublishTime(article.getPublishTime());
        // articleVO.setTag(tags.getName());
        // articleVO.setSummary(article.getSummary());
        // articleVO.setContent(article.getContent());
        // articleVO.setStars(article.getStars());
        // articleVO.setComments(article.getComments());
        return ArticleVO.builder().articleId(article.getId()).author(authorVO).title(article.getTitle())
                        .publishTime(article.getPublishTime()).tag(tags.getName()).summary(article.getSummary())
                        .content(article.getContent()).stars(article.getStars()).comments(article.getComments())
                        .build();
        // return articleVO;
    }
}