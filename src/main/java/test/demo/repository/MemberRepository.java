package test.demo.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.demo.domain.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원가입
    // save 함수 존재

    public List<Member> findByUsername(String username);

    boolean existsByUsername(String username);

    //아이디 변경
    //public Member updateUsername(Long id){return em.find()}


    // 비밀번호 변경


    //계정 삭제 -> 계정 존재하는지 flag 만 바꿈


}
