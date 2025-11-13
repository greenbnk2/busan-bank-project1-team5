package kr.co.wave.repository.member;

import kr.co.wave.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
