package test.demo.api;



import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.demo.domain.Member;
import test.demo.repository.MemberRepository;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class Login {

    private MemberRepository memberRepository;

    // 회원가입
    //@PostMapping("/user/checkpasswd")
    //public ResponseCheckPassword checkPasswd(@RequestBody RquestCheckPassword reqeustCheckPassword){
        //return
//        return
//        }


    @PostMapping("/user/join")
    public void newMember(@RequestBody ResquestMember resquestMember){

        // 비번 2개 일치하는지 확인
        if (!Objects.equals(resquestMember.getPassword(), resquestMember.getCheckPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        Member member = new Member();
        member.setUsername(resquestMember.getName());
        //memberRepository.save(member);
    }

    //로그인
    @PostMapping("/user/login")
    public void entry(@RequestBody ResquestMember resquestMember){

    }



    @Data
    static class ResquestMember {
        String name;
        String password;
        String checkPassword;
    }


    @Data
    static class RquestCheckPassword {
        String password;
        String checkPassword;
    }

    @Data
    static class ResponseCheckPassword {
        boolean isVerified;
    }


}
