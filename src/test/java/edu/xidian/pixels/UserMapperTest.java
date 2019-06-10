package edu.xidian.pixels;

import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private User user;

    public UserMapperTest(){
        this.user=new User();
        user.setAccount("245411916@qq.com");
        user.setName("lzq");
        user.setPassword("245411916li");
        user.setPhone("13709215209");
    }

    @Test
    public void insertTest(){
        Assert.assertEquals(1,userMapper.insert(user));
    }

    @Test
    public void updateTest(){
        Assert.assertEquals(1,userMapper.insert(user));
        Assert.assertEquals(1,userMapper.update(user));
    }

    @Test
    public void editStarsTest(){
        Assert.assertEquals(1,userMapper.insert(user));
        Assert.assertEquals(1, userMapper.editStarsNum(user));
    }

    @Test
    public void editArticleTest(){
        Assert.assertEquals(1,userMapper.insert(user));
        Assert.assertEquals(1,userMapper.editArticleNum(user));
    }
}
