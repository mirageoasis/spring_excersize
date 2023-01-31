package test.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.FreeboardComment;
import test.demo.domain.FreeboardPost;
import test.demo.dto.FreeboardCommentCreateDto;
import test.demo.repository.FreeboardCommentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeboardCommentService {

    private final FreeboardPostService freeboardPostService;
    private final FreeboardCommentRepository freeboardCommentRepository;

    //C
    // 일반 댓글 달기
    @Transactional
    // 나중에 dto 만들어서 대처하기
    public Long createNormalComment(FreeboardCommentCreateDto commentDto){
        // 해당 글번호 설정
        FreeboardPost post = commentDto.getPost();

        // dto comment
        FreeboardComment comment = new FreeboardComment();

        // 댓글에 속성 자동추가
        comment.insertDto(commentDto);

        // 글에 추가
        comment.toPost(post);

        freeboardCommentRepository.save(comment);

        return comment.getId();
    }

    // 대댓글 달기

    //R


    //U


    //D

}
