package edu.xidian.pixels.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserAccount
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

    private String account;
    private String password;
}