package edu.xidian.pixels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.UserService;

/**
 * UserController
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/find")
    public User find(@RequestParam(value="id", required=true)Integer id) {
        if(null != id) {
            return userService.select(id);
        }
        else {
            return new User();
        }
    }
    
}