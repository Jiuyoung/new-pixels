package edu.xidian.pixels.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private int articleId;
    private int userId;
    private String text;
    private Date time;
}