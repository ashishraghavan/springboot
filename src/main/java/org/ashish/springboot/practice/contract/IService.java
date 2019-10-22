package org.ashish.springboot.practice.contract;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import okhttp3.OkHttpClient;

import java.util.HashMap;

@SuppressWarnings("unused")
public interface IService {
    OkHttpClient client = new OkHttpClient();
    /*Logger serviceLogger =
    Set<Character> escapeMapKeys = ImmutableSet.<Character>builder().add(Character.MIN_VALUE,'$','&','@','/','?','+').build();
    Map<Character,String> escapeMap = ImmutableMap.<Character,String>builder()
            .put(Character.MIN_VALUE,"%20")
            .put('$',"%24")
            .put('&',"%26")
            .put('@',"%40")
            .put('/',"%2F")
            .put('?',"%3F")
            .put('+',"%2B")
            .build();*/

    TypeFactory typeFactory = TypeFactory.defaultInstance();
    MapType typeMapStringObject = typeFactory.constructMapType(HashMap.class,String.class,Object.class);
    ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    //format %s with the last name
    //TODO ; This name must be properly encoded.
    String playerLastNameUrl = "https://api-nba-v1.p.rapidapi.com/players/lastName/%s";
    String playerNamesByCountryUrl = "https://api-nba-v1.p.rapidapi.com/players/country/%s";
    String X_RAPID_API_HEADER_HOST_KEY = "x-rapidapi-host";
    String X_RAPID_API_HEADER_HOST_VALUE = "api-nba-v1.p.rapidapi.com";
    String X_RAPID_API_HEADER_KEY_KEY = "x-rapidapi-key";
    String X_RAPID_API_HEADER_KEY_VALUE = "9336a21155msh0a673a5db1a5c1ap1ef2e6jsn72a61142678b";
}
