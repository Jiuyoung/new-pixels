package edu.xidian.pixels.Entity;

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
public class Article {

    private int id;
    private String title;
    private int author;
    private Date publishTime;
    private int tag;
    private String content;
    private int stars;
    private int comments;
}