package edu.xidian.pixels;

import edu.xidian.pixels.Entity.Star;
import edu.xidian.pixels.Mapper.StarMapper;
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
public class StarMapperTest {

    @Autowired
    private StarMapper starMapper;

    private Star star;

    public StarMapperTest(){
        star=new Star();
        star.setUserId(4);
        star.setArticleId(2);
    }

    @Test
    public void selectTest(){
        Assert.assertEquals(1,starMapper.insert(star));
        Assert.assertEquals(this.star,starMapper.select(star.getId()));
    }

    @Test
    public void insertTest(){
        Assert.assertEquals(1,starMapper.insert(star));
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,starMapper.insert(star));
        Assert.assertEquals(1,starMapper.delete(star.getId()));
    }
}
