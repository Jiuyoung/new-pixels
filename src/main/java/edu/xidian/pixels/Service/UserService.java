package edu.xidian.pixels.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.UserMapper;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @CachePut(value = "redisCache",
            key = "'redis_user_' + #user.getAccount()")
    public User update(User user) {
        if(userMapper.update(user) == 1) {
            return userMapper.findById(user.getId());
        }
        else {
            return null;
        }
    }
    
    @Transactional
    @CachePut(value = "redisCache", 
            key="'redis_user_' + #user.getAccount()")
    public User insert(User user) {
        if(userMapper.findByAccount(user.getAccount()) != null) {
            return null;
        }
        if(userMapper.insert(user) > 0) {
            return user;
        }
        else {
            return null;
        }
    }

    @Nullable
    public User login(String account, String password) {
        User user = userMapper.findByAccount(account);
        if(null != user && user.getPassword().equals(password)) {
            return user;
        }
        else {
            return null;
        }
    }

    @Nullable
    @Transactional
    @Cacheable(value = "redisCache",
            unless = "#result == null",
            key = "'redis_user_' + #account")
    public User findByAccount(String account) {
        if(StringUtils.isNotEmpty(account) && StringUtils.isNotBlank(account)) {
            User user = userMapper.findByAccount(account);
            if(null != user) {
                return user;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public boolean editStarsNum(User user){
        if(user!=null){
            if(userMapper.editStarsNum(user)==1)
                return true;
        }
        return false;
    }

    public boolean editArticleNum(User user){
        if(user!=null){
            if(userMapper.editArticleNum(user)==1)
                return true;
        }
        return false;
    }

    public User findById(Integer id){
        if(id!=null){
            return userMapper.findById(id);
        }
        return null;
    }
}