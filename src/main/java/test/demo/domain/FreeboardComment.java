package test.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import test.demo.dto.FreeboardCommentCreateDto;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class FreeboardComment {
    @Id
    @GeneratedValue
    @Column(name="freeboard_comment_id")
    private Long id;

    // 속해있는 글
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "freeboard_post_id")
    private FreeboardPost post;

    //부모 id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private FreeboardComment parentComment;

    // 대댓글 모음
    @OneToMany(mappedBy = "parentComment")
    private List<FreeboardComment> commentList;

    //내용
    @Lob
    @Column(nullable = false)
    private String content;


    //익명여부
    private boolean isAnon;

    //삭제 여부
    private boolean isDeleted;

    // 좋아요 개수
    private int likeCnt;

    //최초 작성시간
    @CreatedDate
    private LocalDateTime createdDate;

    //최종 수정시간
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    //수정 여부
    private boolean isModified;

    //== 연관관계 편의 메서드 ==//

    //글에 댓글 달릴 때
    public void toPost(FreeboardPost post){
        // 어짜피 service 단에 추가되는거 아님?
        this.post = post;
        post.addComment(this);
    }

    // 자식 추가
    public void addComment(FreeboardComment childComment){
        this.commentList.add(childComment);
    }

    public void addParent(FreeboardComment parentComment){
        this.parentComment = parentComment;
        parentComment.addComment(this);
    }

    //부모 추가

    // == 편의 메서드 == //

    // dto 거래
    public void insertDto(FreeboardCommentCreateDto commentDto) {
        //this.ancestorId = commentDto.getAncestorId();
        this.parentComment = commentDto.getParentComment();
        this.content = commentDto.getContent();
        this.likeCnt = commentDto.getLikeCnt();
        this.post = commentDto.getPost();
    }


}
