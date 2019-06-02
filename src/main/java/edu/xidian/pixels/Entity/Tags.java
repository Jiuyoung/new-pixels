package edu.xidian.pixels.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tags
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    private int id;
    private String name;
    private String message;
}