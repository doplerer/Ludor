package com.doplerer.ludor.controller;

import com.doplerer.ludor.dao.GameDAO;
import com.doplerer.ludor.model.Game;
import com.doplerer.ludor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/")
public class StatsController {

    private final GameService gameService;
    private GameDAO gameDAO;

    @Autowired
    public StatsController(GameService gameService, GameDAO gameDAO) {
        this.gameService = gameService;
        this.gameDAO = gameDAO;
    }

    @GetMapping("/stats")
    public String stats() {
        return "stats";
    }

    @GetMapping("/api/stats")
    @ResponseBody
    public Map<String, Game> statsApi() {
        return gameDAO.getGames();
    }


}