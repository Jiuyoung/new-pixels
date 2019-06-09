package edu.xidian.pixels.Entity;

import java.io.Serializable;

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
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String message;
}