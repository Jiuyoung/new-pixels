package edu.xidian.pixels.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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

	public User findUserById(String userId) {
		return userMapper.findById(Integer.valueOf(userId));
    }
    
    public boolean insert(User user) {
        if(userMapper.findByAccount(user.getAccount()) != null) {
            return false;
        }
        if(userMapper.insert(user) > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Nullable
    public User login(String account, String password) {
        if(StringUtils.isNoneEmpty(account, password) && StringUtils.isNoneBlank(account, password)) {
            User user = userMapper.findByAccount(account);
            if(null != user && user.getPassword().equals(password)) {
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
}