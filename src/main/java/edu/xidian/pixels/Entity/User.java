package edu.xidian.pixels.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private String account;
    private String password;
    private String message;
    private Date registTime;
    private String phone;
    private int starsNum;
    private int articleNum;
}