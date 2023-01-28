package test.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name="freeboard_id")
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

    //사진
    // collections로 표기

    //익명여부
    private boolean isAnon = true;

    //삭제여부
    private boolean isDeleted = false;

    // 좋아요 개수
    private int likeCnt = 0;

    //최초 작성시간
    private LocalDateTime firstWrittenDate;

    //최종 수정시간
    private LocalDateTime lastModifiedDate;

    //수정 여부
    private boolean isModified;
}
