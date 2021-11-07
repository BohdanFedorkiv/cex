package com.crypto.cex.controllers;

import com.crypto.cex.services.CSVSave;
import com.crypto.cex.services.JsonSave;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.time.LocalDateTime;
import static com.crypto.cex.services.JsonReader.readJsonFromUrl;

@Controller
@AllArgsConstructor
public class HomeController {
    private final JsonSave jsonSave;
    private final CSVSave csvSave;

    @GetMapping("/home")
    public String home(Model model) throws JSONException, IOException {
        model.addAttribute("btc_usd", readJsonFromUrl("https://cex.io/api/last_price/BTC/USD").get("lprice"));
        model.addAttribute("eth_usd", readJsonFromUrl("https://cex.io/api/last_price/ETH/USD").get("lprice"));
        model.addAttribute("xrp_usd", readJsonFromUrl("https://cex.io/api/last_price/XRP/USD").get("lprice"));
        model.addAttribute("time", LocalDateTime.now());
        return "home";
    }

    @GetMapping("/cryptocurrencies/minprice/{curr1}")
    public String getMinPrice(Model model, @PathVariable("curr1") String curr1){
        model.addAttribute("minPriceRecord", jsonSave.getMinPrice(curr1));
        return "minPricePage";
    }

    @GetMapping("/cryptocurrencies/maxprice/{curr1}")
    public String getMaxPrice(Model model, @PathVariable("curr1") String curr1) throws Exception {
        model.addAttribute("maxPriceRecord", jsonSave.getMaxPrice(curr1));
        return "maxPricePage";
    }

    @RequestMapping("/cryptocurrencies")
    public String getSomeModels(Model model, @RequestParam(value = "name", required = true) String name,
                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        model.addAttribute("models", jsonSave.getSomeModels(name, page, size));
        return "someModels";
    }

    @GetMapping("/cryptocurrencies/csv")
    public String createCSV() throws IOException {
        csvSave.mainCSV();
        return "CSVPage";
    }
}