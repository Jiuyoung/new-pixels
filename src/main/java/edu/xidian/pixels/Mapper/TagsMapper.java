package edu.xidian.pixels.Mapper;

import edu.xidian.pixels.Entity.Tags;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagsMapper {

    @Select("select * from tags where id=#{id}")
    Tags findById(int id);

    @Select("select * from tags")
    List<Tags> getAll();

    @Insert("insert into tags(name,message) values(#{name},#{message})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Tags tags);
}
