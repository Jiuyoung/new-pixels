package edu.xidian.pixels.Controller;

import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Service.TagsService;
import edu.xidian.pixels.VO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/get")
    public ResponseObject get(@RequestParam(name = "id", required = false, defaultValue = "null") Integer id) {
        ResponseObject o;
        if (id == null) {
            List<Tags> tagsList = tagsService.getAll();
            if (tagsList != null && !tagsList.isEmpty()) {
                o = ResponseObject.getSuccessResponse();
                o.putValue("data", tagsList);
            } else
                o = ResponseObject.getFailResponse("tags不存在");
        } else {
            Tags tags = tagsService.findById(id);
            if (tags != null) {
                o = ResponseObject.getSuccessResponse();
                o.putValue("data", tags);
            } else
                o = ResponseObject.getFailResponse("tags不存在");
        }
        return o;
    }

    @GetMapping("/insert")
    public ResponseObject insert(@RequestBody Tags tags) {
        ResponseObject o;
        if (tags != null) {
            if (tagsService.insert(tags)) {
                o = ResponseObject.getSuccessResponse("插入成功");
            } else
                o = ResponseObject.getFailResponse("插入失败");
        } else
            o = ResponseObject.getFailResponse("tags为null");
        return o;
    }
}
