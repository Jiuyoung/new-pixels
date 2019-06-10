package edu.xidian.pixels;

import edu.xidian.pixels.Entity.Comment;
import edu.xidian.pixels.Mapper.CommentMapper;
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
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    private Comment comment;

    public CommentMapperTest(){
        comment=new Comment();
        comment.setUserId(4);
        comment.setArticleId(2);
    }

    @Test
    public void insertTest(){
        Assert.assertEquals(1,commentMapper.insert(comment));
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1,commentMapper.insert(comment));
        Assert.assertEquals(1,commentMapper.delete(comment.getId()));
    }

    @Test
    public void selectTest(){
        Assert.assertEquals(1,commentMapper.insert(comment));
        Assert.assertEquals(comment,commentMapper.select(comment.getId()));
    }
}
