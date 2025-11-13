package kr.co.wave.service;

import kr.co.wave.dto.MemberDTO;
import kr.co.wave.entity.member.Member;
import kr.co.wave.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    // 회원가입
    public MemberDTO signup(MemberDTO memberDTO) {
        memberRepository.save(modelMapper.map(memberDTO, Member.class));
        return memberDTO;
    }

    // ID로 회원 찾기
    public MemberDTO getMemberById(String memId) {
        Optional<Member> optMember = memberRepository.findById(memId);
        return optMember.map(member -> modelMapper.map(member, MemberDTO.class)).orElse(null);
    }

    // 모든 회원 가져오기
    public List<MemberDTO> getMemberAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : memberList) {
            memberDTOList.add(modelMapper.map(member, MemberDTO.class));
        }
        return memberDTOList;
    }

}
