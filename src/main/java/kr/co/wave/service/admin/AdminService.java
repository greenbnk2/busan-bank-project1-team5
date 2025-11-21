package kr.co.wave.service.admin;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.MemberDTO;
import kr.co.wave.dto.admin.AdminDTO;
import kr.co.wave.dto.approval.CardApprovalDTO;
import kr.co.wave.entity.admin.Admin;
import kr.co.wave.entity.approval.CardApproval;
import kr.co.wave.entity.card.Card;
import kr.co.wave.entity.member.Member;
import kr.co.wave.repository.admin.AdminRepository;
import kr.co.wave.repository.approval.CardApprovalRepository;
import kr.co.wave.repository.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체

    @Transactional
    public Page<AdminDTO> getAdminAllBySearch(String searchType, String keyword, int page, int size) {
        // 검색어/타입 공백 처리
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);

        Page<AdminDTO> adminDTOList = adminRepository.findAdminAllBySearch(st, kw, pageable);

        return adminDTOList;
    }

    // 회원가입
    public void signup(AdminDTO adminDTO) {
        adminRepository.save(modelMapper.map(adminDTO, Admin.class));
    }

    // ID로 회원 찾기
    public AdminDTO getAdminById(String adminId) {
        Optional<Admin> optAdmin = adminRepository.findById(adminId);
        return optAdmin.map(a -> modelMapper.map(a, AdminDTO.class)).orElse(null);
    }

}
