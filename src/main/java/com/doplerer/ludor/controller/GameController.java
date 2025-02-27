package com.doplerer.ludor.controller;

import com.doplerer.ludor.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // API del juego

    // API para unirse a una partida
    @PostMapping("/game/join")
    @ResponseBody
    public void unirseAPartida(@RequestParam String partyCode, @RequestParam String playerName) {
        //gameService.joinGame(partyCode, playerName);
    }

}