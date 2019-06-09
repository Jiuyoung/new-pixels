package edu.xidian.pixels.Entity;

import java.io.Serializable;
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
public class Star implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int articleId;
    private int userId;
    private Date starTime;
}