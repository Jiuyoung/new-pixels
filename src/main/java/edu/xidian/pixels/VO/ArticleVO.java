package edu.xidian.pixels.VO;

import java.util.Date;

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
}