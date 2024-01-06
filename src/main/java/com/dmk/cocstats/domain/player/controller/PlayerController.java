package com.dmk.cocstats.domain.player.controller;

import com.dmk.cocstats.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public String player(@RequestParam String tag, Model model) {
        model.addAttribute("player", playerService.searchPlayer(tag));
        return "players/player";
    }

}
