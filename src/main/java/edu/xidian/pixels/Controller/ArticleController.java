package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Service.ArticleService;
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

    @GetMapping("/")
    public ResponseObject findById(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        Article article=articleService.findById(id);
        if(article!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",article);
        }
        else {
            o=ResponseObject.getFailResponse("文章不存在");
        }
        return o;
    }

    @GetMapping("/")
    public ResponseObject findByAuthor(@RequestParam(name="author") Integer author){
        ResponseObject o;
        Article article=articleService.findByAuthor(author);
        if(article!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",article);
        }
        else
            o=ResponseObject.getFailResponse("文章不存在");
        return o;
    }
}