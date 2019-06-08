package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.ArticleMapper;
import edu.xidian.pixels.Mapper.UserMapper;
import edu.xidian.pixels.VO.AuthorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ArticleService
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public Article findById(Integer id){
        if(id!=null){
            Article article=articleMapper.findById(id);
            User user=userMapper.findById(article.getAuthor());
            AuthorVO authorVO=AuthorVO.trans(user);
            //tags查询

            if (article!=null)
                return article;
        }
        return null;
    }

    public Article findByAuthor(Integer author){
        if(author!=null){
            Article article=articleMapper.findByAuthor(author);
            if(article!=null)
                return article;
        }
        return null;
    }

    public boolean insert(Article article){
        if(article!=null) {
            if (articleMapper.insert(article) > 0)
                return true;
        }
        return false;
    }

    public Article update(Article article){
        if(articleMapper.insert(article)==1){
            return articleMapper.findById(article.getId());
        }
        else
            return null;
    }
}
