package kr.co.wave.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppInfo {

    private String appName;

    private String appSubName;

    private String appVersion;

}