package kr.co.wave.controller.member;

import kr.co.wave.dto.MemberDTO;
import kr.co.wave.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 화면으로 이동
    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    // 로그인 요청 받기
    @PostMapping("/member/login")
    public String login(MemberDTO memberDTO) {
        
        // 입력받은 데이터(매개변수 MemberDTO memberDTO를 통해 가져옴)로 기존 회원 찾아오기
        MemberDTO member = memberService.getMemberById(memberDTO.getMemId());

        // 회원이 존재하고
        if(member != null){
            // 비밀번호가 맞다면
            if(memberDTO.getPassword().equals(member.getPassword())) {
                return "redirect:/"; // 메인 화면으로 이동
            }
        }
        return "redirect:/member/login"; // 둘 중 하나라도 맞지 않다면 로그인 화면으로 이동
    }

    // 회원가입 화면으로 이동
    @GetMapping("/member/signup")
    public String signup() {
        return "member/signup";
    }

    // 회원가입 요청 받기
    @PostMapping("/member/signup")
    public String signup(MemberDTO memberDTO) {
        memberService.signup(memberDTO);
        return "member/signup";
    }
}
