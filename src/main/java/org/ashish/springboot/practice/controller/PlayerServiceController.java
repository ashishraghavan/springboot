package org.ashish.springboot.practice.controller;

import org.ashish.springboot.practice.exception.BootException;
import org.ashish.springboot.practice.model.Player;
import org.ashish.springboot.practice.serviceimpl.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/players")
@SuppressWarnings({"unchecked"})
public class PlayerServiceController extends BaseController {
    @Autowired
    private PlayerService playerService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path="/test")
    @ResponseBody
    public ResponseEntity<String> testPlayerController() {
        return new ResponseEntity<>("API works!",HttpStatus.OK);
    }

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

    @ResponseBody
    @PostMapping(path = "/create",produces = "application/json",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Player> createPlayer(@RequestParam(name = "first_name") String firstName,
                                               @RequestParam(name = "last_name") String lastName,
                                               @RequestParam(name = "country") String country) {
        return new ResponseEntity<>(playerService.createPlayer(firstName,lastName,country),HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping(path = "/create/json",produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> createPlayer(@RequestBody String playerJson) {
        Player createdPlayer = playerService.createPlayer(playerJson);
        if(createdPlayer == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdPlayer,HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlayerById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(playerService.getPlayerById(Long.valueOf(id)), HttpStatus.OK);
        } catch (Exception e) {
            serviceLogger.error("There was an error getting player by id with exception "+e.getLocalizedMessage());
            return new ResponseEntity(new BootException(e,HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
        }
    }
}
