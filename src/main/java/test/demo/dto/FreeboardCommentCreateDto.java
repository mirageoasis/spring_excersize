package test.demo.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import test.demo.domain.FreeboardComment;
import test.demo.domain.FreeboardPost;

@Getter @Setter
public class FreeboardCommentCreateDto {

    //private Long parentId;
    //private Long ancestorId;

    private FreeboardComment parentComment;

    private String content;

    private FreeboardPost post;

    private boolean isAnon = true;

    private boolean isDeleted = false;

    private int likeCnt = 0;

}
