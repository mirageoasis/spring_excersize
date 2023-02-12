package test.demo.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.demo.domain.FreeboardPost;
import test.demo.service.FreeboardPostService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FreeboardPostController {

    private final FreeboardPostService freeboardPostService;

    @GetMapping("/board")
    public void freeBoardMain(){
        // 아직은 인증 제외
        freeboardPostService.findAll();
    }

    @PostMapping("/board/write")
    public void freeBoardWrite()
    {

    }

}
