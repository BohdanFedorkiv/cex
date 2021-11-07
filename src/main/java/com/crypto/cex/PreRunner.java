package com.crypto.cex;

import com.crypto.cex.services.JsonSave;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@AllArgsConstructor
public class PreRunner implements ApplicationRunner {
    private JsonSave jsonSave;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            while (true) {
                jsonSave.save("BTC");
                jsonSave.save("ETH");
                jsonSave.save("XRP");
                Thread.sleep(10000);
            }
        } catch (InterruptedException | JSONException ex) {}
    }
}