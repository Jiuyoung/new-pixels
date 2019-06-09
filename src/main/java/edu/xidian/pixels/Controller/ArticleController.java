package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Service.ArticleService;
import edu.xidian.pixels.VO.ArticleVO;
import edu.xidian.pixels.VO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ArticleController
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/id")
    public ResponseObject findById(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        ArticleVO articleVO=articleService.findById(id);
        if(articleVO!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",articleVO);
        }
        else {
            o=ResponseObject.getFailResponse("文章不存在");
        }
        return o;
    }

    @GetMapping("/author")
    public ResponseObject findByAuthor(@RequestParam(name = "author") Integer author){
        ResponseObject o;
        ArticleVO articleVO=articleService.findByAuthor(author);
        if(articleVO!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",articleVO);
        }
        else
            o=ResponseObject.getFailResponse("文章不存在");
        return o;
    }

    @GetMapping("/stars")
    public ResponseObject upStars(@RequestParam(name = "id") Integer id,
                                  @RequestParam(name = "up",defaultValue = "true") Boolean up){
        int temp=up?1:-1;
        ResponseObject o;
        Article article=articleService.getArticle(id);
        if(article!=null){
            article.setId(article.getId()+temp);
            ArticleVO articleVO=articleService.editStars(article);
            if(articleVO!=null){
                o=ResponseObject.getSuccessResponse();
                o.putValue("data",articleVO);
            }
            else
                o=ResponseObject.getFailResponse("点赞+1失败");
        }
        else
            o=ResponseObject.getFailResponse("文章不存在");
        return o;
    }
}