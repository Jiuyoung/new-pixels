package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Star;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.ArticleService;
import edu.xidian.pixels.Service.StarService;
import edu.xidian.pixels.Service.UserService;
import edu.xidian.pixels.VO.ArticleVO;
import edu.xidian.pixels.VO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArticleController
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    private final StarService starService;

    private final UserService userService;

    public ArticleController(ArticleService articleService, StarService starService, UserService userService) {
        this.articleService = articleService;
        this.starService = starService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseObject recommend(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        ResponseObject o;
        List<ArticleVO> articleVOList = articleService.recommend(pageNum, pageSize);
        if (articleVOList != null && !articleVOList.isEmpty()) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", articleVOList);
        } else
            o = ResponseObject.getFailResponse("文章不存在！");
        return o;
    }

    @GetMapping("/id")
    public ResponseObject findById(@RequestParam(name = "id") Integer id) {
        ResponseObject o;
        ArticleVO articleVO = articleService.findById(id);
        if (articleVO != null) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", articleVO);
        } else {
            o = ResponseObject.getFailResponse("文章不存在！");
        }
        return o;
    }

    @UserLoginToken
    @GetMapping("/author")
    public ResponseObject findByAuthor(@CurrentUser User user,
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        ResponseObject o;
        List<ArticleVO> articleVOList = articleService.findByAuthor(user.getId(), pageNum, pageSize);
        if (articleVOList != null && !articleVOList.isEmpty()) {
            o = ResponseObject.getSuccessResponse();
            o.putValue("data", articleVOList);
        } else
            o = ResponseObject.getFailResponse("文章不存在！");
        return o;
    }

    @UserLoginToken
    @PostMapping("/stars")
    public ResponseObject upStars(@CurrentUser User user, @RequestBody Star star,
            @RequestParam(name = "up", defaultValue = "true") Boolean up) {
        ResponseObject o;
        Article article = articleService.getArticle(star.getArticleId());
        if (article == null) {
            o = ResponseObject.getFailResponse("文章不存在");
        } else {
            star.setUserId(user.getId());
            if (!starService.notExist(user.getId(), star.getArticleId())) {
                up = false;
            }
            int temp = up ? 1 : -1;
            article.setStars(article.getStars() + temp);
            ArticleVO articleVO = articleService.editStars(article);
            if (up ? starService.insert(star) : starService.delete(star.getId())) {
                // 修改作者的点赞总数
                User author = userService.findById(article.getAuthor());
                author.setStarsNum(up ? author.getStarsNum() + 1 : author.getStarsNum() - 1);
                if (userService.editStarsNum(author)) {
                    articleVO.getAuthor().setStarsNum(author.getStarsNum());
                    o = ResponseObject.getSuccessResponse();
                    o.putValue("data", articleVO);
                } else
                    o = ResponseObject.getFailResponse("更新作者点赞失败");
            } else
                o = ResponseObject.getFailResponse("更新点赞失败");
        }
        return o;
    }

    @UserLoginToken
    @PostMapping("")
    public ResponseObject insert(@CurrentUser User user, @RequestBody Article article) {
        ResponseObject o;
        if (article != null) {
            article.setAuthor(user.getId());
            if (articleService.insert(article)) {
                user.setArticleNum(user.getArticleNum() + 1);
                if (userService.editArticleNum(user)) {
                    o = ResponseObject.getSuccessResponse("新建文章成功！");
                    o.putValue("data", articleService.findById(article.getId()));
                } else
                    o = ResponseObject.getFailResponse("更新文章总数失败");
            } else
                o = ResponseObject.getFailResponse("新建文章失败！");
        } else
            o = ResponseObject.getFailResponse("新建文章失败！");
        return o;
    }
}