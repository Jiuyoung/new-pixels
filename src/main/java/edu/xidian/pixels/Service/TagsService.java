package edu.xidian.pixels.Service;

import edu.xidian.pixels.Entity.Tags;
import edu.xidian.pixels.Mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    public Tags findById(Integer id){
        if(id!=null) {
            return tagsMapper.findById(id);
        }
        return null;
    }

    public List<Tags> getAll(){
        return tagsMapper.getAll();
    }

    public boolean insert(Tags tags){
        if(tags!=null){
            if(tagsMapper.insert(tags)>0)
                return true;
        }
        return false;
    }
}
