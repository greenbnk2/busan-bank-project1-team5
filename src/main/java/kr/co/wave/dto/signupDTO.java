package kr.co.wave.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class signupDTO {
    private String memId;
    private String password;
    private String name;
    private String rrn;
    private String email;
    private String address;
    private String de_address;
}
