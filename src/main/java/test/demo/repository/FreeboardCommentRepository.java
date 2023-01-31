package test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.demo.domain.FreeboardComment;

import java.util.List;

public interface FreeboardCommentRepository extends JpaRepository <FreeboardComment, Long>{

    //public List<FreeboardComment> findBy

}
