package com.crypto.cex.services;

import com.crypto.cex.exceptions.ExceptionClass;
import com.crypto.cex.models.CryptoModel;
import com.crypto.cex.repositories.CryptoRepo;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import static com.crypto.cex.services.JsonReader.readJsonFromUrl;
import java.util.*;

@Service
public class JsonSave {
    private CryptoRepo cryptoRepo;

    public JsonSave(CryptoRepo cryptoRepo){
        this.cryptoRepo = cryptoRepo;
    }

    public void save(String currency) throws JSONException, IOException {
        CryptoModel cryptoModel = new CryptoModel();
        cryptoModel.setCurr1((String) readJsonFromUrl(String.format("https://cex.io/api/last_price/%s/USD", currency)).get("curr1"));
        cryptoModel.setCurr2((String) readJsonFromUrl(String.format("https://cex.io/api/last_price/%s/USD", currency)).get("curr2"));
        cryptoModel.setLprice(Double.valueOf((String) readJsonFromUrl(String.format("https://cex.io/api/last_price/%s/USD", currency)).get("lprice")));
        cryptoModel.setTimes(LocalDateTime.now());
        cryptoRepo.save(cryptoModel);
    }

    public CryptoModel getMinPrice(String curr1){
        return cryptoRepo.getMinPrice(curr1);
    }

    public CryptoModel getMaxPrice(String curr1) throws Exception {
        if(!curr1.equals("XRP") && !curr1.equals("BTC") && !curr1.equals("ETH")){
            throw new ExceptionClass("bad variable of curr");
        }
        return cryptoRepo.getMaxPrice(curr1);
    }

    public List<CryptoModel> getSomeModels(String name, Integer page, Integer size){
        return cryptoRepo.getSomeModels(name, page, size);
    }
}