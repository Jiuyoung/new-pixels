package edu.xidian.pixels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.UserMapper;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    public User select(Integer id) {
        return userMapper.findById(id);
    }
}