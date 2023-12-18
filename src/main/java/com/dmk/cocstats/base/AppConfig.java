package com.dmk.cocstats.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // yml에 작성해 놓은 데이터를 관리하는 클래스
    public static String COC_API_TOKEN;

    @Value("${coc.api-token}")
    private void setApiToken(String apiToken) {
        COC_API_TOKEN = apiToken;
    }
}
