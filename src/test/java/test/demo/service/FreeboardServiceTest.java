package test.demo.service;

import jakarta.persistence.EntityManager;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.FreeboardPost;
import test.demo.dto.FreeboardCommentCreateDto;
import test.demo.repository.FreeboardCommentRepository;
import test.demo.repository.FreeboardPostRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
class FreeboardServiceTest {

    @Autowired
    FreeboardPostService postService;
    @Autowired
    FreeboardCommentService freeboardCommentService;

    @Autowired
    FreeboardPostRepository freeboardPostRepository;
    @Autowired
    FreeboardCommentRepository freeboardCommentRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    public void clear(){
        freeboardCommentRepository.deleteAll();
        freeboardPostRepository.deleteAll();
    }

    // C
    @Test
    public void 익명_글생성() throws Exception{
        //given
        FreeboardPost post = new FreeboardPost();

        post.setTitle("test1");
        post.setContent("mike test mike test");


        //when
        Long postId = postService.createPost(post);

        //then
        em.flush();

        assertEquals(post, freeboardPostRepository.findById(postId).get());

    }

    // R
    @Test
    public void 글_조회() throws Exception{
        //given

        //when
    
        //then
    
    }
    
    //U
    @Test
    public void 제목_수정() throws Exception{
        //given

        // 글 생성
        FreeboardPost post = new FreeboardPost();

        post.setTitle("test1");
        post.setContent("mike test mike test");

        Long postId = postService.createPost(post);

        em.flush();
        // 글 생성 완료
        //when

        FreeboardPost fixPost = freeboardPostRepository.findById(postId).get();
        String fixedTitle = "fixed1";

        fixPost.setTitle(fixedTitle);
        em.flush();
        //then

        assertEquals(freeboardPostRepository.findById(postId).get().getTitle(), fixedTitle);
    }
    
    @Test
    public void 글내용_수정() throws Exception{
        //given

        // 글 생성
        FreeboardPost post = new FreeboardPost();

        post.setTitle("test1");
        post.setContent("mike test mike test");

        Long postId = postService.createPost(post);

        em.flush();
        // 글 생성 완료
        //when

        FreeboardPost fixPost = freeboardPostRepository.findById(postId).get();
        String fixedContent = "mike chekc already done!";

        fixPost.setContent(fixedContent);
        em.flush();
        //then
        assertEquals(fixPost.getId(), postId);
        assertEquals(freeboardPostRepository.findById(postId).get().getContent(), fixedContent);
    
    }
    
    @Test
    public void 댓글_조회() throws Exception{
        FreeboardPost post = new FreeboardPost();
        post.setTitle("test1");
        post.setContent("mike test mike test");


        // 글 추가
        postService.createPost(post);
        //freeboardPostService.createPost(post2);

        // 거기에 댓글 추가

        FreeboardCommentCreateDto dto1 = new FreeboardCommentCreateDto();
        String dto1Content = "dto1";

        FreeboardCommentCreateDto dto2 = new FreeboardCommentCreateDto();
        String dto2Content = "dto2";

        FreeboardCommentCreateDto dto3 = new FreeboardCommentCreateDto();
        String dto3Content = "dto3";

        // 대댓글은 아직
        //dto1.setAncestorId();

        // 댓글 부모와 댓글 내용 설정, 부모 설정은 나중에
        dto1.setPost(post);
        dto1.setContent(dto1Content);

        dto2.setPost(post);
        dto2.setContent(dto2Content);

        dto3.setPost(post);
        dto2.setContent(dto3Content);


        // 글 추가
        Long commentId1 =  freeboardCommentService.createNormalComment(dto1);
        Long commentId2 =  freeboardCommentService.createNormalComment(dto2);
        Long commentId3 =  freeboardCommentService.createNormalComment(dto2);

        //
        em.flush();

        // 글 id, 댓글 내용, 비교하기
        //System.out.println(commentId1);
        assertEquals(3, post.getCommentList().size());

        System.out.println("자신 id 비교");
        //assertEquals(, );
    
    }

    @Test
    public void 전체_조회() throws Exception{
        FreeboardPost post1 = new FreeboardPost();
        post1.setTitle("test1");
        post1.setContent("mike test mike test");

        FreeboardPost post2 = new FreeboardPost();
        post2.setTitle("test1");
        post2.setContent("mike test mike test");


        // 글 추가
        postService.createPost(post1);
        postService.createPost(post2);
        //freeboardPostService.createPost(post2);

        // 거기에 댓글 추가

        FreeboardCommentCreateDto dto1 = new FreeboardCommentCreateDto();
        String dto1Content = "dto1";
        FreeboardCommentCreateDto dto2 = new FreeboardCommentCreateDto();
        String dto2Content = "dto2";
        FreeboardCommentCreateDto dto3 = new FreeboardCommentCreateDto();
        String dto3Content = "dto3";
        dto1.setPost(post1);
        dto1.setContent(dto1Content);
        dto2.setPost(post1);
        dto2.setContent(dto2Content);
        dto3.setPost(post1);
        dto3.setContent(dto3Content);

        // 글 추가
        Long commentId1 =  freeboardCommentService.createNormalComment(dto1);
        Long commentId2 =  freeboardCommentService.createNormalComment(dto2);
        Long commentId3 =  freeboardCommentService.createNormalComment(dto3);

        FreeboardCommentCreateDto dto4 = new FreeboardCommentCreateDto();
        String dto4Content = "dto4";
        FreeboardCommentCreateDto dto5 = new FreeboardCommentCreateDto();
        String dto5Content = "dto5";
        FreeboardCommentCreateDto dto6 = new FreeboardCommentCreateDto();
        String dto6Content = "dto6";
        dto4.setPost(post2);
        dto4.setContent(dto4Content);
        dto5.setPost(post2);
        dto5.setContent(dto5Content);
        dto6.setPost(post2);
        dto6.setContent(dto6Content);


        // 글 추가
        Long commentId4 =  freeboardCommentService.createNormalComment(dto4);
        Long commentId5 =  freeboardCommentService.createNormalComment(dto5);
        Long commentId6 =  freeboardCommentService.createNormalComment(dto6);

        //
        em.flush();

        // 글 id, 댓글 내용, 비교하기
        //System.out.println(commentId1);
        System.out.println("쿼리 6개 나오면 ㅅㄱ");

        // 쿼리를 보기
        freeboardPostRepository.findAll()
                .stream()
                .forEach(x -> System.out.println(x.getId()));
            ;
        System.out.println("쿼리 6개 나오면 ㅅㄱ");

    }

}