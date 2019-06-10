package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Entity.Star;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.ArticleService;
import edu.xidian.pixels.Service.StarService;
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

    @Autowired
    private ArticleService articleService;

    @Autowired
    private StarService starService;

    @GetMapping("/id")
    public ResponseObject findById(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        ArticleVO articleVO=articleService.findById(id);
        if(articleVO!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",articleVO);
        }
        else {
            o=ResponseObject.getFailResponse("文章不存在！");
        }
        return o;
    }

    @UserLoginToken
    @GetMapping("/author")
    public ResponseObject findByAuthor(@CurrentUser User user,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        ResponseObject o;
        List<ArticleVO> articleVOList=articleService.findByAuthor(user.getId(), pageNum, pageSize);
        if(articleVOList!=null && !articleVOList.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",articleVOList);
        }
        else
            o=ResponseObject.getFailResponse("文章不存在！");
        return o;
    }

    @UserLoginToken
    @PostMapping("/stars")
    public ResponseObject upStars(@CurrentUser User user,
                                  @RequestBody Star star,
                                  @RequestParam(name = "up",defaultValue = "true") Boolean up){
        int temp=up?1:-1;
        ResponseObject o;
        if(user.getId()!=star.getUserId()){
            o=ResponseObject.getFailResponse("用户不对应");
        }
        else {
            Article article=articleService.getArticle(star.getArticleId());
            if(article==null){
                o=ResponseObject.getFailResponse("文章不存在");
            }
            else {
                article.setStars(article.getStars()+temp);
                ArticleVO articleVO=articleService.editStars(article);
                if(up?starService.insert(star):starService.delete(star.getId())) {
                    o=ResponseObject.getSuccessResponse();
                    o.putValue("data", articleVO);
                }
                else
                    o=ResponseObject.getFailResponse("更新点赞失败");
            }
        }
        return o;
    }

    @UserLoginToken
    @PostMapping("")
    public ResponseObject insert(@CurrentUser User user, @RequestBody Article article) {
        ResponseObject o;
        if(article != null){
            article.setAuthor(user.getId());
            if(articleService.insert(article)) {
                o = ResponseObject.getSuccessResponse("新建文章成功！");
                o.putValue("data", articleService.findById(article.getId()));
            }
            else
                o = ResponseObject.getFailResponse("新建文章失败！");
        }
        else
            o = ResponseObject.getFailResponse("新建文章失败！");
        return o;
    }
}