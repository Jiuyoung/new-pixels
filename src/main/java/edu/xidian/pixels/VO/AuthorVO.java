package edu.xidian.pixels.VO;

import edu.xidian.pixels.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthorVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVO {

    private int id;
    private String name;
    private int starsNum;
    private int articleNum;

    public static AuthorVO trans(User user){
        AuthorVO authorVO=new AuthorVO();
        authorVO.setId(user.getId());
        authorVO.setName(user.getName());
        authorVO.setArticleNum(user.getArticleNum());
        authorVO.setStarsNum(user.getStarsNum());
        return authorVO;
    }
}