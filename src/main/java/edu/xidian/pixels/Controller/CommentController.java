package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Entity.Comment;
import edu.xidian.pixels.Service.CommentService;
import edu.xidian.pixels.VO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/id")
    public ResponseObject findById(@RequestParam(name="id") Integer id){
        ResponseObject o;
        Comment comment=commentService.findById(id);
        if(comment!=null){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",comment);
        }
        else
            o=ResponseObject.getFailResponse("评论不存在");
        return o;
    }

    @GetMapping("/article")
    public ResponseObject findByArticle(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        List<Comment> comments=commentService.findByArticle(id);
        if(comments!=null && !comments.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",comments);
        }
        else
            o=ResponseObject.getFailResponse("评论不存在");
        return o;
    }

    @GetMapping("/user")
    public ResponseObject findByUser(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        List<Comment> comments=commentService.findByUser(id);
        if(comments!=null && !comments.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",comments);
        }
        else
            o=ResponseObject.getFailResponse("评论不存在");
        return o;
    }

    @GetMapping("/insert")
    public ResponseObject insert(@RequestBody Comment comment){
        ResponseObject o;
        if(comment!=null){
            if(commentService.insert(comment))
                o=ResponseObject.getSuccessResponse("插入成功");
            else
                o=ResponseObject.getFailResponse("插入失败");
        }
        else
            o=ResponseObject.getFailResponse("评论为null");
        return o;
    }

    @GetMapping("/delete")
    public ResponseObject delete(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        if(id!=null){
            if(commentService.delete(id))
                o=ResponseObject.getSuccessResponse("删除成功");
            else
                o=ResponseObject.getFailResponse("删除失败");
        }
        else
            o=ResponseObject.getFailResponse("评论id为null");
        return o;
    }
}
