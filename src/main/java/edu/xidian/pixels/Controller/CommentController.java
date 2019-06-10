package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.Comment;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.CommentService;
import edu.xidian.pixels.VO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("")
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
    public ResponseObject findByArticle(@RequestParam(name = "id") Integer id,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "5") int pageSize){
        ResponseObject o;
        List<Comment> comments=commentService.findByArticle(id, pageNum, pageSize);
        if(comments!=null && !comments.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",comments);
        }
        else
            o=ResponseObject.getFailResponse("评论不存在");
        return o;
    }

    @GetMapping("/user")
    public ResponseObject findByUser(@RequestParam(name = "id") Integer id,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "5") int pageSize){
        ResponseObject o;
        List<Comment> comments=commentService.findByUser(id, pageNum, pageSize);
        if(comments!=null && !comments.isEmpty()){
            o=ResponseObject.getSuccessResponse();
            o.putValue("data",comments);
        }
        else
            o=ResponseObject.getFailResponse("评论不存在");
        return o;
    }

    @UserLoginToken
    @PostMapping("")
    public ResponseObject insert( @CurrentUser User user, @RequestBody Comment comment){
        ResponseObject o;
        if(comment!=null) {
            comment.setUserId(user.getId());
            if(commentService.insert(comment)) {
                o=ResponseObject.getSuccessResponse("评论成功！");
                o.putValue("data", comment);
            }
            else
                o=ResponseObject.getFailResponse("评论失败！");
        }
        else
            o=ResponseObject.getFailResponse("评论出错");
        return o;
    }

    @UserLoginToken
    @DeleteMapping("")
    public ResponseObject delete(@RequestParam(name = "id") Integer id){
        ResponseObject o;
        if(id!=null){
            if(commentService.delete(id))
                o=ResponseObject.getSuccessResponse("删除成功");
            else
                o=ResponseObject.getFailResponse("删除失败");
        }
        else
            o=ResponseObject.getFailResponse("删除失败");
        return o;
    }
}
