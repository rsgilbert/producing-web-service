package com.gilboot.soapproducer.producingwebservice;



import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Dummy country repository with hard-coded data.
 */
@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    /**
     * Initializes the dummy data that we shall use in our web service.
     */
    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(80_000_000);
        countries.put(spain.getName(), spain);

        Country uganda = new Country();
        uganda.setName("Uganda");
        uganda.setCapital("Kampala");
        uganda.setCurrency(Currency.UGX);
        uganda.setPopulation(40_000_000);
        countries.put(uganda.getName(), spain);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.POUND);
        uk.setPopulation(140_000_000);
        countries.put(uk.getName(), spain);

    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
}
