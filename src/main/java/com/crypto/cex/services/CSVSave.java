package com.crypto.cex.services;

import com.crypto.cex.repositories.CryptoRepo;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class CSVSave {
    private CryptoRepo cryptoRepo;

    public CSVSave(CryptoRepo cryptoRepo) {
        this.cryptoRepo = cryptoRepo;
    }

    public void mainCSV() throws IOException {
        List<String[]> csvData = createCsvDataSimple();

        try (CSVWriter writer = new CSVWriter(new FileWriter("E:\\test.csv"))) {
            writer.writeAll(csvData);
        }
    }

    private  List<String[]> createCsvDataSimple() {
        String[] header = {"Cryptocurrency Name", "Min Price", "Max Price"};

        String[] record1 = {"BTC", String.valueOf(cryptoRepo.getMinPriceOnlyNumber("BTC")),
                                   String.valueOf(cryptoRepo.getMaxPriceOnlyNumber("BTC"))};

        String[] record2 = {"ETH", String.valueOf(cryptoRepo.getMinPriceOnlyNumber("ETH")),
                                   String.valueOf(cryptoRepo.getMaxPriceOnlyNumber("ETH"))};

        String[] record3 = {"XRP", String.valueOf(cryptoRepo.getMinPriceOnlyNumber("XRP")),
                                   String.valueOf(cryptoRepo.getMaxPriceOnlyNumber("XRP"))};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);
        list.add(record3);

        return list;
    }
}
