package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    public Tags findById(Integer id){
        return tagsMapper.findById(id);
    }
}
