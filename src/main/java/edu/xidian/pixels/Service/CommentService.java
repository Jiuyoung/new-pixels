package edu.xidian.pixels.Service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Comment;
import edu.xidian.pixels.Mapper.ArticleMapper;
import edu.xidian.pixels.Mapper.CommentMapper;
import edu.xidian.pixels.Mapper.UserMapper;
import edu.xidian.pixels.VO.AuthorVO;
import edu.xidian.pixels.VO.CommentVO;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    private final ArticleMapper articleMapper;

    private final UserMapper userMapper;

    public CommentService(CommentMapper commentMapper, ArticleMapper articleMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.articleMapper = articleMapper;
        this.userMapper = userMapper;
    }

    @Cacheable(value = "redisCache", unless = "#result == null", key = "'redis_comment_' + #id", sync = true)
    public CommentVO findById(Integer id) {
        if (id != null) {
            Comment comment = commentMapper.select(id);
            if (comment != null) {
                return CommentVO.trans(comment, AuthorVO.trans(userMapper.findById(comment.getUserId())));
            }
        }
        return null;
    }

    public List<CommentVO> findByArticle(Integer articleId, Integer pageNum, Integer pageSize) {
        if (articleId != null) {
            PageHelper.startPage(pageNum, pageSize);
            List<Comment> comments = commentMapper.selectByArticle(articleId);
            List<CommentVO> cVOs = new ArrayList<>();
            if (comments != null) {
                for (Comment comment : comments) {
                    cVOs.add(CommentVO.trans(comment, AuthorVO.trans(userMapper.findById(comment.getUserId()))));
                }
                return cVOs;
            }
        }
        return null;
    }

    public List<CommentVO> findByUser(Integer userId, Integer pageNum, Integer pageSize) {
        if (userId != null) {
            PageHelper.startPage(pageNum, pageSize);
            List<CommentVO> cVOs = new ArrayList<>();
            List<Comment> comments = commentMapper.selectByUser(userId);
            for (Comment comment : comments) {
                cVOs.add(CommentVO.trans(comment, AuthorVO.trans(userMapper.findById(comment.getUserId()))));
            }
        }
        return null;
    }

    @CachePut(value = "redisCache", key = "'redis_comment_' + #comment.getId()")
    public boolean insert(Comment comment) {
        if (comment != null) {
            Article article = articleMapper.findById(comment.getArticleId());
            if (article != null) {
                article.setComments(article.getComments() + 1);
                if (commentMapper.insert(comment) > 0) {
                    articleMapper.update(article);
                    return true;
                }
            }
        }
        return false;
    }

    @CacheEvict(value = "redisCache", key = "'redis_comment_' + #id", beforeInvocation = true)
    public boolean delete(Integer id) {
        if (id != null) {
            Comment comment = commentMapper.select(id);
            if (comment != null) {
                Article article = articleMapper.findById(comment.getArticleId());
                if (article != null) {
                    article.setComments(article.getComments() - 1);
                    if (commentMapper.delete(id) == 1) {
                        articleMapper.update(article);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
