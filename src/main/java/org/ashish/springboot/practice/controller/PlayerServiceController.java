package org.ashish.springboot.practice.controller;

import org.ashish.springboot.practice.model.Player;
import org.ashish.springboot.practice.serviceimpl.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/players")
@SuppressWarnings({"unchecked"})
public class PlayerServiceController extends BaseController {
    @Autowired
    private PlayerService playerService;


    @SuppressWarnings("unchecked")
    @GetMapping(produces = "application/json",path = "/lastname/{playerName}")
    @ResponseBody
    public ResponseEntity getPlayersByLastName(@PathVariable String playerName) {
        //TODO Make sure playername is not null etc etc.
        try {
            List<Player> players = playerService.getPlayersByLastName(playerName);
            return new ResponseEntity(players,HttpStatus.OK);
        } catch (Exception e) {
            serviceLogger.error("Error while fetching list of players with given last name with exception "+e.getLocalizedMessage());
            //TODO : Change the getCause -> getMessage()
            return new ResponseEntity("Bad request while fetching players by last name with message "+e.getCause().getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping(path = "/country/{countryName}",produces = "application/json")
    public ResponseEntity getPlayersByCountry(@PathVariable String countryName) {
        //TODO Make sure country name is properly escaped from space and reserved characters.
        try {
            List<Player> players = playerService.getPlayerByCountry(countryName);
            return new ResponseEntity(players,HttpStatus.OK);
        } catch (Exception e) {
            serviceLogger.error("Error while fetching list of players with given country with exception "+e.getLocalizedMessage());
            //TODO : Change the getCause -> getMessage()
            return new ResponseEntity("Bad request while fetching players by country with message "+e.getCause().getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
