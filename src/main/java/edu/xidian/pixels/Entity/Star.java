package edu.xidian.pixels.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Star
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Star {

    private int id;
    private int articleId;
    private int userId;
    private Date starTime;
}