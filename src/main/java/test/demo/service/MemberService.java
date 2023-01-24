package test.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.demo.domain.Member;
import test.demo.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        // 중복 확인
        //duplicateUsername(member.getUsername());
        memberRepository.save(member);
        return member.getId();
    }

    //중복되는 아이디 찾기

    public void duplicateUsername(String name){
        if (!memberRepository.existsByUsername(name)){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


}
