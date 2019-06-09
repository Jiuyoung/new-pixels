package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Comment;
import edu.xidian.pixels.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public Comment findById(Integer id){
        if(id!=null){
            Comment comment=commentMapper.select(id);
            if(comment!=null)
                return comment;
        }
        return null;
    }

    public List<Comment> findByArticle(Integer articleId){
        if(articleId!=null){
            List<Comment> comments=commentMapper.selectByArticle(articleId);
            if(comments!=null)
                return comments;
        }
        return null;
    }

    public List<Comment> findByUser(Integer userId){
        if(userId!=null){
            List<Comment> comments=commentMapper.selectByUser(userId);
            if(comments!=null)
                return comments;
        }
        return null;
    }

    public boolean insert(Comment comment){
        if(comment!=null){
            if(commentMapper.insert(comment)>0)
                return true;
        }
        return false;
    }

    public boolean delete(Integer id){
        if(id!=null){
            if(commentMapper.delete(id)==1)
                return true;
        }
        return false;
    }
}
