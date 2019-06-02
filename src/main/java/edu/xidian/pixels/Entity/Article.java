package edu.xidian.pixels.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Article
 */
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