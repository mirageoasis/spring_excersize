package test.demo.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.FreeboardComment;
import test.demo.domain.FreeboardPost;
import test.demo.dto.FreeboardCommentCreateDto;
import test.demo.repository.FreeboardCommentRepository;
import test.demo.repository.FreeboardPostRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class FreeboardCommentServiceTest {

    @Autowired
    FreeboardPostRepository freeboardPostRepository;
    @Autowired
    FreeboardPostService freeboardPostService;
    @Autowired
    FreeboardCommentService freeboardCommentService;
    @Autowired
    EntityManager entityManager;
    @Autowired
    FreeboardCommentRepository freeboardCommentRepository;

    @Test
    void 일반댓글_추가() {
        // 글 추가
        FreeboardPost post = new FreeboardPost();
        post.setTitle("test1");
        post.setContent("mike test mike test");


        // 글 추가
        freeboardPostService.createPost(post);
        //freeboardPostService.createPost(post2);

        // 거기에 댓글 추가

        FreeboardCommentCreateDto dto1 = new FreeboardCommentCreateDto();
        String dto1Content = "like your saying";

        FreeboardCommentCreateDto dto2 = new FreeboardCommentCreateDto();
        String dto2Content = "like your saying";
        // 대댓글은 아직
        //dto1.setAncestorId();

        // 댓글 부모와 댓글 내용 설정, 부모 설정은 나중에
        dto1.setPost(post);
        dto1.setContent(dto1Content);

        dto2.setPost(post);
        dto2.setContent(dto2Content);

        // 글 추가
        Long commentId1 =  freeboardCommentService.createNormalComment(dto1);
        Long commentId2 =  freeboardCommentService.createNormalComment(dto2);



        //
        entityManager.flush();

        // 글 id, 댓글 내용, 비교하기
        //System.out.println(commentId1);
        System.out.println("원글 id 비교");
        assertEquals(post.getId(), freeboardCommentRepository.findById(commentId1).get().getPost().getId());

        System.out.println("자신 id 비교");
        //assertEquals(, );


    }

    @Test
    public void 대댓글_추가() throws Exception{
        FreeboardPost post = new FreeboardPost();
        post.setTitle("test1");
        post.setContent("mike test mike test");


        // 글 추가
        freeboardPostService.createPost(post);

        // 거기에 댓글 추가
        FreeboardCommentCreateDto dto1 = new FreeboardCommentCreateDto();
        String dto1Content = "like your saying";

        FreeboardCommentCreateDto dto2 = new FreeboardCommentCreateDto();
        String dto2Content = "like your saying";
        // 대댓글은 아직
        //dto1.setAncestorId();

        // 댓글 부모와 댓글 내용 설정, 부모 설정은 나중에
        dto1.setPost(post);
        dto1.setContent(dto1Content);

        dto2.setPost(post);
        dto2.setContent(dto2Content);

        // 글 추가
        Long commentId1 =  freeboardCommentService.createNormalComment(dto1);
        Long commentId2 =  freeboardCommentService.createNormalComment(dto2);
        Long commentId3 =  freeboardCommentService.createNormalComment(dto2);

        // 댓글 추가
        freeboardCommentService.createNormalReComment(commentId2, commentId1);
        freeboardCommentService.createNormalReComment(commentId3, commentId1);

        //
        entityManager.flush();

        // 글 id, 댓글 내용, 비교하기
        //System.out.println(commentId1);
        System.out.println("test");
        assertEquals(2, freeboardCommentRepository.findById(commentId1).get().getCommentList().size());
        System.out.println("test");
    
    }

}