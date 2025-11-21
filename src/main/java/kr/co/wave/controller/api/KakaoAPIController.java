package kr.co.wave.controller.api;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@RestController
public class KakaoAPIController {

    @GetMapping(value = "/kakao/maps/sdk.js", produces = "application/javascript")
    public ResponseEntity<byte[]> getKakaoSdk() throws IOException {
        System.out.println("Kakao SDK Proxy 요청 들어옴");
        URL url = new URL("https://dapi.kakao.com/v2/maps/sdk.js?appkey=60a0c24e69381d7f07081e23083b3600");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(url.openStream(), baos);

        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
                .body(baos.toByteArray());
    }
}