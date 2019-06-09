package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Mapper.ArticleMapper;
import edu.xidian.pixels.Mapper.TagsMapper;
import edu.xidian.pixels.Mapper.UserMapper;
import edu.xidian.pixels.VO.ArticleVO;
import edu.xidian.pixels.VO.AuthorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ArticleService
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @Transactional
    @Cacheable(value = "redisCache",
            unless = "#result == null",
            key = "'redis_article_' + #id")
    public ArticleVO findById(Integer id){
        if(id!=null){
            Article article=articleMapper.findById(id);
            if(article!=null) {
                User user = userMapper.findById(article.getAuthor());
                if(user!=null) {
                    AuthorVO authorVO = AuthorVO.trans(user);
                    Tags tags = tagsMapper.findById(article.getTag());
                    if (tags != null) {
                        ArticleVO articleVO = ArticleVO.trans(article, authorVO, tags);
                        return articleVO;
                    }
                }
            }
        }
        return null;
    }

    @Transactional
    public List<ArticleVO> findByAuthor(Integer author){
        if(author!=null){
            List<ArticleVO> voList=new ArrayList<>();
            List<Article> list=articleMapper.findByAuthor(author);
            if(list!=null && !list.isEmpty()){
                for (Article article:list){
                    User user=userMapper.findById(author);
                    AuthorVO authorVO=AuthorVO.trans(user);
                    Tags tags=tagsMapper.findById(article.getTag());
                    if(tags!=null){
                        voList.add(ArticleVO.trans(article,authorVO,tags));
                    }
                }
                return voList;
            }
        }
        return null;
    }

    /**
     * 返回文章，非VO
     * @param id
     * @return
     */
    public Article getArticle(Integer id){
        if(id!=null){
            Article article=articleMapper.findById(id);
            if(article!=null)
                return article;
        }
        return null;
    }

    @CachePut(value = "redisCache",
            key = "'redis_article_' + #article.getId()")
    public boolean insert(Article article){
        if(article!=null) {
            if (articleMapper.insert(article) > 0)
                return true;
        }
        return false;
    }

    /**
     * 不修改id,author,publishTime,
     * @param article
     * @return
     */
    @CachePut(value = "redisCache",
            key = "'redis_article_' + #article.getId()")
    public ArticleVO update(Article article){
        if(articleMapper.insert(article)==1){
            return this.findById(article.getId());
        }
        else
            return null;
    }

    /**
     * 只修改点赞
     * @param article
     * @return
     */
    public ArticleVO editStars(Article article){
        if(articleMapper.editStars(article)==1){
            return this.findById(article.getId());
        }
        return null;
    }
}
