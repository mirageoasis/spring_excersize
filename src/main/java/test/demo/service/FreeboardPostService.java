package test.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.FreeboardComment;
import test.demo.domain.FreeboardPost;
import test.demo.repository.FreeboardPostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeboardPostService {

    private final FreeboardPostRepository freeboardRepository;

    // C
    @Transactional
    public Long createPost(FreeboardPost post) {
        freeboardRepository.save(post);
        return post.getId();
    }

    // R
    //모든 post 보여주기
    public List<FreeboardPost> findAll(){ return freeboardRepository.findAll(); }

    //아이디로 찾기
    public FreeboardPost findPost(Long postId){
        return freeboardRepository.findById(postId).get();
    }

    //제목으로 찾기
    public List<FreeboardPost> findByTitle(String keyword){
        return freeboardRepository.findByTitleContaining(keyword);
    }

    //글 내용으로 찾기
    public List<FreeboardPost> findByContent(String keyword){
        return freeboardRepository.findByContentContaining(keyword);
    }


    // U
    // 글 그냥 수정 update
    @Transactional
    public Long updateContent(FreeboardPost post){
        FreeboardPost postToUpdate = findPost(post.getId());
        postToUpdate.update(post);

        return post.getId();
    }

    // D
    @Transactional
    public Long deletePost(Long postId){
        FreeboardPost postToDelete = findPost(postId);
        postToDelete.setDeleted(true);

        return postId;
    }

}
