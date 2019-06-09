package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Entity.Article;
import edu.xidian.pixels.Service.ArticleService;
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
    public ResponseObject findByAuthor(@RequestParam(name = "id") Integer author){
        ResponseObject o;
        List<ArticleVO> articleVOList=articleService.findByAuthor(author);
        if(articleVOList!=null && !articleVOList.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",articleVOList);
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
            article.setStars(article.getStars()+temp);
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

    @GetMapping("/insert")
    public ResponseObject insert(@RequestBody Article article){
        ResponseObject o;
        if(article!=null){
            if(articleService.insert(article))
                o=ResponseObject.getSuccessResponse("插入成功");
            else
                o=ResponseObject.getFailResponse("插入失败");
        }
        else
            o=ResponseObject.getFailResponse("文章为null");
        return o;
    }
}