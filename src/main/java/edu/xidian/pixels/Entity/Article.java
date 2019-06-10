package edu.xidian.pixels.Entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Article
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private int author;
    private Date publishTime;
    private int tag;
    private String summary;
    private String content;
    private int stars;
    private int comments;//评论数
}