package edu.xidian.pixels.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private int articleId;
    private int userId;
    private String message;
    private Date time;
}