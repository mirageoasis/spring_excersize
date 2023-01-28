package test.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.Post;
import test.demo.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // C
    @Transactional
    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    // R
    public Post findPost(Long postId){
        return postRepository.findById(postId).get();
    }

    //제목으로 찾기
    public List<Post> findByTitle(String keyword){
        return postRepository.findByTitleContaining(keyword);
    }

    //글 내용으로 찾기
    public List<Post> findByContent(String keyword){
        return postRepository.findByContentContaining(keyword);
    }

    // U
    // 제목 update
    public Long updateContent(Post post){
        Post postToUpdate = findPost(post.getId());
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setModified(true);
        postToUpdate.setLastModifiedDate(post.getLastModifiedDate());

        return post.getId();
    }

    // 글 내용 update
    public Long updateTitle(Post post){
        Post postToUpdate = findPost(post.getId());
        postToUpdate.setContent(post.getContent());
        postToUpdate.setModified(true);
        postToUpdate.setLastModifiedDate(post.getLastModifiedDate());

        return post.getId();
    }




    // D
    public Long deletePost(Long postId){
        Post postToDelete = findPost(postId);
        postToDelete.setDeleted(true);

        return postId;
    }

}
