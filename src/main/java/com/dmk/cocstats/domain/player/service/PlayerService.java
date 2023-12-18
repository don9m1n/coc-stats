package com.dmk.cocstats.domain.player.service;

import com.dmk.cocstats.base.AppConfig;
import com.dmk.cocstats.domain.player.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final WebClient webClient;

    public Player searchPlayer(String tag) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players/{tag}")
                        .build(tag)
                )
                .header(HttpHeaders.AUTHORIZATION, AppConfig.COC_API_TOKEN)
                .retrieve()
                .bodyToMono(Player.class)
                // TODO: add exception handler
                .block();
    }
}
