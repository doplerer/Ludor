package com.doplerer.ludor.service;

import com.doplerer.ludor.dao.GameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  GameService {
    private final GameDAO gameDAO;

    @Autowired
    public GameService(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }



}
