package edu.xidian.pixels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.TokenService;
import edu.xidian.pixels.Service.UserService;
import edu.xidian.pixels.VO.UserAccount;
import edu.xidian.pixels.util.ResponseObject;

/**
 * UserController
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @UserLoginToken
    @RequestMapping("/find")
    public ResponseObject find(@RequestParam(value="id", required=true)Integer id, @CurrentUser User u) {
        ResponseObject o;
        if(null != id) {
            User user = userService.select(id);
            if(null != user) {
                o = ResponseObject.getSuccessResponse();
                o.putValue("data", user);
                o.putValue("CurrentUser", u);
            }
            else {
                o = ResponseObject.getFailResponse("未查找到用户");
            }
        }
        else {
            o = ResponseObject.getFailResponse("参数错误");
        }
        return o;
    }

    @RequestMapping("/logup")
    public ResponseObject insert(@RequestBody User user) {
        ResponseObject o;
        if(null != user && userService.insert(user)) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", user);
        }
        else {
            o = ResponseObject.getFailResponse("新建用户失败");
        }
        return o;
    }

    @RequestMapping("/login")
    public ResponseObject login(@RequestBody UserAccount account) {
        ResponseObject o;
        if(null != account) {
            User user = userService.login(account.getAccount(), account.getPassword());
            if(null != user) {
                o = ResponseObject.getSuccessResponse("登录成功");
                o.putValue("token", tokenService.getToken(user));
                o.putValue("data", user);
            }
            else {
                o = ResponseObject.getFailResponse("账户不存在或者密码错误");
            }
        } 
        else {
            o = ResponseObject.getFailResponse();
        }
        return o;
    }
    
}