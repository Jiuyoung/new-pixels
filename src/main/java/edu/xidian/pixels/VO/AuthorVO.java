package edu.xidian.pixels.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthorVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVO {

    private int id;
    private String name;
    private int starsNum;
    private int articleNum;
}