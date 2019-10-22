package org.ashish.springboot.practice.serviceimpl;

import com.google.common.collect.Lists;
import okhttp3.Response;
import org.ashish.springboot.practice.contract.IPlayerService;
import org.ashish.springboot.practice.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerService implements IPlayerService {

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

