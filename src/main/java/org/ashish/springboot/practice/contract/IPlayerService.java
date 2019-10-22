package org.ashish.springboot.practice.contract;

import okhttp3.Request;
import org.ashish.springboot.practice.model.Player;

import java.util.List;

public interface IPlayerService extends IService {
    List<Player> getPlayersByLastName(String lastName) throws Exception;
    List<Player> getPlayerByCountry(String country) throws Exception;
    Request.Builder requestBuilder = new Request.Builder()
            .get()
            .addHeader(X_RAPID_API_HEADER_HOST_KEY, X_RAPID_API_HEADER_HOST_VALUE)
            .addHeader(X_RAPID_API_HEADER_KEY_KEY, X_RAPID_API_HEADER_KEY_VALUE);
}
