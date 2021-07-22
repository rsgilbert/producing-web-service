package com.gilboot.soapproducer.producingwebservice;

public class Country {

    private long population;
    private String name;
    private String capital;
    private Enum<Currency> currency;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setCurrency(Enum<Currency> currency) {
        this.currency = currency;
    }

    public Enum<Currency> getCurrency() {
        return currency;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getPopulation() {
        return population;
    }
}
