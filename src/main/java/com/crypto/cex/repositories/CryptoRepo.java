package com.crypto.cex.repositories;

import com.crypto.cex.models.CryptoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface CryptoRepo extends CrudRepository<CryptoModel, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM crypto_models WHERE curr1 = :curr AND lprice = (SELECT MIN(lprice) FROM crypto_models WHERE curr1 = :curr) LIMIT 1")
    CryptoModel getMinPrice(String curr);

    @Query(nativeQuery = true,
            value = "SELECT * FROM crypto_models WHERE curr1 = :curr AND lprice = (SELECT MAX(lprice) FROM crypto_models WHERE curr1 = :curr) LIMIT 1")
    CryptoModel getMaxPrice(String curr);

    @Query(nativeQuery = true,
            value = "select * from crypto_models where curr1 = :name order by lprice ASC OFFSET :page ROWS FETCH NEXT :size ROWS ONLY")
    List<CryptoModel> getSomeModels(String name, Integer page, Integer size);

    @Query(nativeQuery = true,
    value = "select min(lprice) from crypto_models where curr1 = :name")
    Double getMinPriceOnlyNumber(String name);

    @Query(nativeQuery = true,
            value = "select max(lprice) from crypto_models where curr1 = :name")
    Double getMaxPriceOnlyNumber(String name);
}
