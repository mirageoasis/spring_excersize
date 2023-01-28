package test.demo.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.demo.domain.Post;
import test.demo.repository.PostRepository;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    EntityManager em;


    @Test
    public void 익명_글생성() throws Exception{
        //given
        Post post = new Post();

        post.setTitle("test1");
        post.setContent("mike test mike test");

        //when
        Long postId = postService.createPost(post);

        //then
        em.flush();

        assertEquals(post, postRepository.findById(postId));

    }


}