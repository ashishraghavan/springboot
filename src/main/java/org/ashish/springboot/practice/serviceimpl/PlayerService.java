package org.ashish.springboot.practice.serviceimpl;

import com.google.common.collect.Lists;
import okhttp3.Response;
import org.ashish.springboot.practice.contract.IPlayerService;
import org.ashish.springboot.practice.model.Player;
import org.ashish.springboot.practice.repository.NbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private NbaRepository nbaRepository;

    /**
     *
     * @param lastName The last name of the player for which we need the details
     * @return A {@link List} of {@link Player} if there exists, or empty list
     */
    @Override
    public List<Player> getPlayersByLastName(String lastName) throws Exception {
        //TODO: encode or replace all reserved characters with their %hexadecimal symbol, for example " " with %40
        String url = String.format(playerLastNameUrl, lastName);
        return respond(url);
    }

    @Override
    public List<Player> getPlayerByCountry(String country) throws Exception {
        //TODO : again, encode country for spaces and other reserved characters.
        String url = String.format(playerNamesByCountryUrl, country);
        return respond(url);
    }

    @Override
    public Player createPlayer(String firstName, String lastName, String country) {
        return nbaRepository.save(new Player(firstName,lastName,country));
    }

    @Override
    public Player getPlayerById(Long id) throws Exception {
        Optional<Player> optionalPlayer = nbaRepository.findById(id);
        if(!optionalPlayer.isPresent()) {
            serviceLogger.error("There was an error getting the player with id "+id);
            //TODO An exception should be thrown or the appropriate response object should be returned instead of returning null.
            return null;
        }
        return optionalPlayer.get();
    }

    public Player createPlayer(String jsonPlayer) {
        Player player = null;
        try {
            player = mapper.readValue(jsonPlayer,Player.class);
            //persist in DB.
            player = nbaRepository.save(player);
        } catch (Exception e) {
            serviceLogger.error("There was an error reading the JSON value with exception "+e.getLocalizedMessage());
        }
        return player;
    }

    @SuppressWarnings("unchecked")
    private List<Player> respond(String url) throws Exception {
        List<Player> playerList = Lists.newArrayList();
        Response response = client.newCall(requestBuilder.url(url).build()).execute();
        if(!response.isSuccessful()) {
            throw new Exception(response.code() + " "+"THere was an error!"+response.message());
        }
        if(response.body() == null) {
            return playerList;
        }
        Map<String,Object> responseBody = mapper.readValue(response.body().string(),typeMapStringObject);
        List<Map<String,Object>> playerListMap = ((List<Map<String,Object>>)((Map<String,Object>)responseBody.get("api")).get("players"));
        for(Map<String,Object> player : playerListMap) {
            Player convertedPlayer = mapper.convertValue(player,Player.class);
            playerList.add(convertedPlayer);
        }
        return playerList;
    }


}

