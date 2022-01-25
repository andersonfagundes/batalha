package br.edu.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.edu.infnet.repository.BatalhaRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BatalhaService {

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    BatalhaRepository batalhaRepository;

    public String getFirstPlayer(Integer agilityHero, Integer agilityMonster) {

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

    public void saveBattle(Integer idHero, Integer idMonster, String firstPlayer){

        Date dateNow = new Date();
        String data = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        String hour = new SimpleDateFormat("HH:mm:ss").format(dateNow);

        String dateTimeNow = data+" "+hour;

    	batalhaRepository.saveBattle(idHero, idMonster, firstPlayer, dateTimeNow);


    }

    public String getFirstPlayerFallback(){
        return "heroi";
    }
    
    public String getBattle(int idHero, int idMonster, String firstPlayer) {
    	//Buscar os dados dos herois e monstros nos microservicos
    	//Guerreiro
    	int pVHero = 12;
    	int forceHero = 4;
    	int defenseHero = 3;
    	int agilityHero = 3;

    	//Morto-Vivo
    	int pVMonster = 25;
    	int forceMonster = 4;
    	int defenseMonster = 0;
    	int agilityMonster = 1;

        saveAttack(idHero, idMonster, pVHero, pVMonster);

    	do {

    		int resultHero, resultMonster;
    		
    		if(firstPlayer == "hero") {
    			if(pVHero > 0) {
	    			//Ataque do heroi e defesa do monstro
	    			resultHero = getAttack(agilityHero, forceHero);
	    			resultMonster = getDefense(agilityMonster, defenseMonster);
	    			
	    			//Calculo do dano do monstro
	    			if(resultHero > resultMonster) {
	    				pVMonster = getDemage(2, 4, pVMonster);
                        saveAttack(idHero, idMonster, pVHero, pVMonster);
	    			}
    			}
    			
    			if(pVMonster > 0) {
    				//Ataque do monstro e defesa do heroi
        			resultMonster = getAttack(agilityMonster, forceMonster);
        			resultHero = getDefense(agilityHero, defenseHero);
        			
        			//Calculo do dano do heroi
        			if(resultMonster > resultHero) {
        				pVHero = getDemage(2, 4, pVHero);
                        saveAttack(idHero, idMonster, pVHero, pVMonster);
        			}
    			}
    		}
    		else {
    			if(pVMonster > 0) {
    				//Ataque do monstro e defesa do heroi
        			resultMonster = getAttack(agilityMonster, forceMonster);
        			resultHero = getDefense(agilityHero, defenseHero);
        			
        			//Calculo do dano do heroi
        			if(resultMonster > resultHero) {
        				pVHero = getDemage(2, 4, pVHero);
                        saveAttack(idHero, idMonster, pVHero, pVMonster);
        			}
    			}
    			if(pVHero > 0) {
	    			//Ataque do heroi e defesa do monstro
	    			resultHero = getAttack(agilityHero, forceHero);
	    			resultMonster = getDefense(agilityMonster, defenseMonster);
	    			
	    			//Calculo do dano do monstro
	    			if(resultHero > resultMonster) {
	    				pVMonster = getDemage(2, 4, pVMonster);
                        saveAttack(idHero, idMonster, pVHero, pVMonster);
	    			}
    			}
    		}
    	} while((pVHero > 0) && (pVMonster > 0));
    	
    	if(pVHero > 0) {
    		return "hero";
    	}
    	
    	return "monster";
    }
    
    public Integer getAttack(int agility, int force) {
        int resultRollDices = getResultRollDices(10,1);
    	return  resultRollDices + agility + force;
    }
    
    public Integer getDefense(int agility, int defense) {
    	int resultRollDices = getResultRollDices(10,1);
        return resultRollDices + agility + defense;
    }
    
    public Integer getDemage(int amount, int type, int pDV) {
    	int resultRollDices = getResultRollDices(type,amount);
        return pDV - resultRollDices;
    }

    public void saveAttack(Integer idHero, Integer idMonster, Integer pdvHero, Integer pdvMonster){

        Date dateNow = new Date();
        String data = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        String hour = new SimpleDateFormat("HH:mm:ss").format(dateNow);

        String dateTimeNow = data+" "+hour;

        int idBattle = batalhaRepository.lastIdStart().intValue();

        batalhaRepository.saveAttack(idHero, idMonster, pdvHero, pdvMonster, idBattle, dateTimeNow);
    }

}
