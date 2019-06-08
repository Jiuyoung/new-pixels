package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Mapper.ArticleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ArticleService
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public Article findById(Integer id){
        if(id!=null){
            Article article=articleMapper.findById(id);
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
