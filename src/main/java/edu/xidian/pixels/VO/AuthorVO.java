package edu.xidian.pixels.VO;

import java.io.Serializable;

import edu.xidian.pixels.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthorVO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVO implements Serializable {

    private static final long serialVersionUID = 1L;
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