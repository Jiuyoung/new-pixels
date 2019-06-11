package edu.xidian.pixels.VO;

import java.io.Serializable;
import java.util.Date;

import edu.xidian.pixels.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentVO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int articleId;
    private int id;
    private String message;
    private Date time;
    private AuthorVO author;

    public static CommentVO trans(Comment comment, AuthorVO author) {
        CommentVO vo = CommentVO.builder().articleId(comment.getArticleId()).author(author).id(comment.getId())
                    .message(comment.getMessage()).time(comment.getTime()).build();
        return vo;
    }
}