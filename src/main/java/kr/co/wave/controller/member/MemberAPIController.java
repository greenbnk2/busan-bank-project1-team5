package kr.co.wave.controller.member;

import kr.co.wave.dto.MemberDTO;
import kr.co.wave.jwt.JwtProvider;
import kr.co.wave.security.MemberDetails;
import kr.co.wave.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/member/api/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberDTO memberDTO) {
        // 시큐리티 인증처리
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(memberDTO.getMemId(), memberDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken); // 실제 DB 조회 수행
        log.info("authentication : " + authentication);

        // 시큐리티 인증된 사용자 객체 가져오기
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        log.info("Login User: {}", memberDetails);

        // 토큰 생성
        String accessToken = jwtProvider.createToken(memberDetails.getMember(), 1);
        String refreshToken = jwtProvider.createToken(memberDetails.getMember(), 7);
        log.info("accessToken Token: {}", accessToken);

        // 클라이언트 토큰 전송
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("accessToken", accessToken);
        resultMap.put("refreshToken", refreshToken);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/member/api/list")
    public ResponseEntity<List<MemberDTO>> list() {

        List<MemberDTO> memberDTOList = memberService.getMemberAll();

        return ResponseEntity.ok(memberDTOList);
    }

    @PostMapping("/member/api/register")
    public ResponseEntity<MemberDTO> memberRegister(@RequestBody MemberDTO memberDTO) {
        System.out.println(memberDTO);
        MemberDTO savedMember = memberService.signup(memberDTO);

        return ResponseEntity.ok(savedMember);
    }
}
