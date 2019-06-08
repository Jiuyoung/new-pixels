package edu.xidian.pixels.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tags
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    private int id;
    private String name;
    private String message;
}