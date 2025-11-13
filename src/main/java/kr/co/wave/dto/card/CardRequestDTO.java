package kr.co.wave.dto.card;

import lombok.Data;

import java.util.List;

@Data
public class CardRequestDTO {
    // 카드 등록 요청 받는 객체
    private String name;
    private String engName;
    private String type;
    private Boolean isCompany;
    private String description;

    private List<String> category;
    private List<String> benefitType;
    private List<Integer> value;
    private List<String> unit;
    private List<Integer> limit;
    private List<String> benefitDescription;

    private List<String> annualFeeName;

}
