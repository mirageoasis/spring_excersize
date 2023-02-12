package test.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.FreeboardComment;
import test.demo.domain.FreeboardPost;
import test.demo.repository.FreeboardPostRepository;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeboardPostService {

    private final FreeboardPostRepository freeboardRepository;
    private final FreeboardComment freeboardComment;

    // C
    @Transactional
    public Long createPost(FreeboardPost post) {



        freeboardRepository.save(post);
        return post.getId();
    }

    // R
    //모든 post 보여주기 -> 페지네이션 구현 안함
    public List<FreeboardPost> findAll(){
        // 글이면 삭제된 글은 정보자체를 보내지 않는다.
        return freeboardRepository.findAll().stream()
                .filter(post -> post.getIsDeleted().equals(FALSE))
                .toList();
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
    public Long updateContent(Long postId){
        FreeboardPost postToUpdate = freeboardRepository.findById(postId).orElseThrow(
                ()-> new IllegalStateException("해당 글은 존재하지 않습니다.")
        );
        String title = "title";
        String content = "content";

        postToUpdate.update(title, content);

        return postId;
    }

    // D
    @Transactional
    public Long deletePost(Long postId){
        FreeboardPost postToDelete = freeboardRepository.findById(postId)
                                                        .orElseThrow(
                                                                ()->new IllegalStateException("해당 글 번호는 존재하지 않음")
                                                        );

        postToDelete.setIsDeleted(TRUE);

        return postId;
    }

}
