package test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.demo.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
        // 포스트 추가

        // 포스트 삭제-> 그냥 번호만 바꿔 놓는다.

    public List<Post> findByContentContaining(String content);
    public List<Post> findByTitleContaining(String title);

}
