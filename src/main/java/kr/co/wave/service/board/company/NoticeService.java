package kr.co.wave.service.board.company;

import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.entity.board.company.Notice;
import kr.co.wave.repository.board.company.NoticeRepository;
import kr.co.wave.service.board.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final DTOConverter converter;
    private final ModelMapper modelMapper;

    public List<NoticeDTO> getNoticeAll() {
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        List<Notice> noticeList = noticeRepository.findAll();

        for (Notice companyNotice : noticeList) {
            noticeDTOList.add(modelMapper.map(companyNotice, NoticeDTO.class));
        }

        return noticeDTOList;
    }

    public NoticeDTO getNoticeById(int companyNoticeId) {
        return modelMapper.map(noticeRepository.findById(companyNoticeId).get(), NoticeDTO.class);
    }

    public void saveNotice(NoticeDTO companyNoticeDTO) {
        noticeRepository.save(modelMapper.map(companyNoticeDTO, Notice.class));
    }

    public Page<NoticeDTO> getNoticeAllBySearch(String searchType, String keyword, int page, int size, String sortBy, String direction){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();

        if(sortBy == null || sortBy.isEmpty()){
            sortBy = "created_at";
        }

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> result = noticeRepository.findNoticeBySearch(st, kw, pageable);

        return converter.convert(result, row -> new NoticeDTO(
                (int) (row[0]),           // noticeId
                (String) row[1],                    // title
                converter.clobToString(row[2]),     // content
                (String) row[3],                    // writer
                converter.toLocalDateTime(row[4])   // createdAt
        ));
    }

    // 최신글 5개 찾아오기
    public List<NoticeDTO> getNoticeRecentlyFive(){
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        List<Notice> noticeList = noticeRepository.findTop5ByOrderByCreatedAtDesc();

        for (Notice notice : noticeList) {
            noticeDTOList.add(modelMapper.map(notice, NoticeDTO.class));
        }

        return noticeDTOList;
    }

}
