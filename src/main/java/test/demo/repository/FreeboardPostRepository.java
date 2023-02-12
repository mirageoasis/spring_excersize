package test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.domain.FreeboardPost;

import java.util.List;

@Repository
public interface FreeboardPostRepository extends JpaRepository<FreeboardPost, Long> {
        // 포스트 추가

    // 포스트 삭제-> 그냥 번호만 바꿔 놓는다

    public List<FreeboardPost> findByContentContaining(String content);
    public List<FreeboardPost> findByTitleContaining(String title);

    public List<FreeboardPost> findAll();
}
