package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Star;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.ArticleMapper;
import edu.xidian.pixels.Mapper.StarMapper;
import edu.xidian.pixels.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarService {


    @Autowired
    private StarMapper starMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Star> findByArticle(Integer articleId){
        if(articleId!=null){
            List<Star> stars=starMapper.findByArticle(articleId);
            if(stars!=null)
                return stars;
        }
        return null;
    }

    public List<Star> findByUser(Integer userId){
        if(userId!=null){
            List<Star> stars=starMapper.findByUser(userId);
            if(stars!=null)
                return stars;
        }
        return null;
    }

    public Star findByArticleAndUser(Integer articleId,Integer userId){
        if(articleId!=null && userId!=null){
            Star star=starMapper.findByArticleAndUser(articleId,userId);
            if(star!=null)
                return star;
        }
        return null;
    }

    public boolean insert(Star star){
        if(star!=null){
            Article article=articleMapper.findById(star.getArticleId());
            if(article!=null){
                User user=userMapper.findById(star.getUserId());
                if(user!=null){
                    if(starMapper.insert(star)==1)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean delete(Integer id){
        if(id!=null) {
            Star star=starMapper.select(id);
            if (star != null) {
                Article article = articleMapper.findById(star.getArticleId());
                if (article != null) {
                    User user = userMapper.findById(star.getUserId());
                    if (user != null) {
                        if (starMapper.delete(id) == 1)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
