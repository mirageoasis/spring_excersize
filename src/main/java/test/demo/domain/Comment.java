package test.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

public class Comment {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    //최상단 댓글 id
    private Long ancestorId;

    //직속 부모 댓글 id
    private Long parentId;

    //내용
    @Lob
    private String content;

    //익명여부
    private boolean isAnon;

    //삭제 여부
    private boolean isDeleted;

    // 좋아요 개수
    private int likeCnt;

    //최초 작성시간
    private LocalDateTime firstWrittenDate;

    //최종 수정시간
    private LocalDateTime lastModifiedDate;

    //수정 여부
    private boolean isModified;
}
