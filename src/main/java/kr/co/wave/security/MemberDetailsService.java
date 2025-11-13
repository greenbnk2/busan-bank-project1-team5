package kr.co.wave.security;

import kr.co.wave.entity.member.Member;
import kr.co.wave.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername username: {}", username);

        // 사용자가 입력한 아이디로 사용자 조회, 비밀번호에 대한 검증은 이전 컴포넌트인 AuthenticationProvider에서 수행
        Optional<Member> optUser = memberRepository.findById(username);

        if(optUser.isPresent()) {

            Member member = optUser.get();
            log.info("loadUserByUsername User: {}", member);

            // 인증 객체 생성1
            MemberDetails memberDetails = MemberDetails.builder()
                    .member(member)
                    .build();

            // 반환되는 인증 객체가 Security Context Holder에 Authentication으로 저장
            return memberDetails;
        }
        return null;
    }
}