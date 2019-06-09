package edu.xidian.pixels.VO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    @Email(message = "邮箱格式不正确")
    @NotNull(message = "账号不能为空")
    private String account;
    @Size(min = 9, max = 20, message = "密码长度不合要求")
    private String password;
}