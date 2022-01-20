package br.edu.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class BatalhaService {

    @Autowired
    RestTemplate restTemplate;

    public String start(Integer agilityHero, Integer agilityMonster) {

        int type = 10;
        int amount = 1;
        int resultDice = 0;
        int resultHero = 0;
        int resultMonster = 0;
        boolean hasResult = false;

        do {
            resultDice = getResultRollDices(type, amount);
            resultHero = resultDice + agilityHero;

            resultDice = getResultRollDices(type, amount);
            resultMonster = resultDice + agilityMonster;

            if ((resultHero > resultMonster) || (resultMonster > resultHero)) hasResult = true;

        } while (!hasResult);

        if(resultHero > resultMonster) {
            return "hero";
        }
        return "monster";
    }

    public Integer getResultRollDices(Integer type, Integer amount) {

        //RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://DICE-API/api/rolldices?type="+type+"&amount="+amount, String.class);

        Gson gson = new Gson();

        String resultDices = gson.toJson(forObject);
        int result;

        if(resultDices.substring(13,14).equals("}")) {
            result = Integer.parseInt(resultDices.substring(12,13));
        } else{
            result = Integer.parseInt(resultDices.substring(12,14));
        }

        return result;
    }

}
