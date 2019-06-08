package edu.xidian.pixels.Entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    @Email(message = "邮箱格式不正确")
    @NotNull(message = "账号不能为空")
    private String account;
    @Size(min = 10, max = 20, message = "密码长度不合要求")
    private String password;
    private String message;
    private Date registTime;
    @Pattern(
        regexp = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35678]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|66\\d{2})\\d{6}$",
        message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    private int starsNum;
    private int articleNum;
}