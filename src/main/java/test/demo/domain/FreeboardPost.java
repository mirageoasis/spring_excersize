package test.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class FreeboardPost {

    @Id @GeneratedValue
    @Column(name="freeboard_post_id")
    private Long id;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    @Lob
    @Column(nullable = false)
    private String content;

    //작성자
    //

    //댓글 collection
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<FreeboardComment> commentList = new ArrayList<>();

    //사진
    // collections로 표기

    //익명여부
    @Column(nullable = false)
    private Boolean isAnon = true;

    //삭제여부
    @Column(nullable = false)
    private Boolean isDeleted = false;

    // 좋아요 개수
    private Integer likeCnt = 0;

    //최초 작성시간
    @CreatedDate
    private LocalDateTime createdDate;

    //최종 수정시간
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    //수정 여부
    private boolean isModified;

    //여기에 글을 수정하는 함수를 넣을지 service 단에 넣을지 성일이한테 질문

    //==연관관계 메서드==//
    public void addComment(FreeboardComment comment){
        commentList.add(comment);
    }

    // 수정
    // 나중에 다른 class 넣을 예정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
