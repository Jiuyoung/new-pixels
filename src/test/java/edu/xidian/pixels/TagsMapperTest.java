package edu.xidian.pixels;

import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Mapper.TagsMapper;
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
public class TagsMapperTest {

    @Autowired
    private TagsMapper tagsMapper;

    @Test
    public void insertTest(){
        Tags tags=new Tags();
        tags.setMessage("111");
        tags.setName("111");
        tagsMapper.insert(tags);
        Assert.assertEquals(1,tagsMapper.insert(tags));
    }

    @Test
    public void deleteTest(){
        Tags tags=new Tags();
        tags.setMessage("111");
        tags.setName("111");
        tagsMapper.insert(tags);
        Assert.assertEquals(1,tagsMapper.insert(tags));
        Assert.assertEquals(tags,tagsMapper.findById(tags.getId()));
    }
}
