package edu.xidian.pixels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.TokenService;
import edu.xidian.pixels.Service.UserService;
import edu.xidian.pixels.VO.ResponseObject;
import edu.xidian.pixels.VO.UserAccount;

/**
 * UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @UserLoginToken
    @GetMapping("/info")
    public ResponseObject find(@CurrentUser User u) {
        ResponseObject o;
        if (u != null) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", u);
        } else {
            o = ResponseObject.getFailResponse("用户未登录");
        }
        return o;
    }

    @UserLoginToken
    @PostMapping("/info")
    public ResponseObject edit(@RequestBody User user) {
        ResponseObject o;
        User u = userService.update(user);
        if (null != u) {
            o = ResponseObject.getSuccessResponse("修改成功");
            o.putValue("data", u);
        } else {
            o = ResponseObject.getFailResponse("修改失败");
        }
        return o;
    }

    @PostMapping("/regist")
    public ResponseObject insert(@RequestBody @Validated User user) {
        ResponseObject o;
        User u = userService.insert(user);
        if (u != null) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", u);
        } else {
            o = ResponseObject.getFailResponse("新建用户失败");
        }
        return o;
    }

    @PostMapping("")
    public ResponseObject login(@RequestBody @Validated UserAccount account) {
        ResponseObject o;
        User user = userService.login(account.getAccount(), account.getPassword());
        if (null != user) {
            o = ResponseObject.getSuccessResponse("登录成功");
            o.putValue("token", tokenService.getToken(user));
            o.putValue("data", user);
        } else {
            o = ResponseObject.getFailResponse("账户不存在或者密码错误");
        }
        return o;
    }

    @GetMapping("/find")
    public User findUser(@RequestParam(name = "account") String account) {
        return userService.findByAccount(account);
    }
}