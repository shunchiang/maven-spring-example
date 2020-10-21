package com.shundev.countries.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//entity turns our POJO into our spring bean table
@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long countryid;
    // all columns in a table needs to be all lowercase
    private String name;
    private long population;
    private long landmasskm2;
    private int medianage;

    public Country( String name, long population, long landmasskm2, int medianrange) {
        this.name = name;
        this.population = population;
        this.landmasskm2 = landmasskm2;
        this.medianage = medianrange;
    }

    public Country() {
        //jpa requires a default constructor
    }

    public long getCountryid() {
        return countryid;
    }

    public void setCountryid(long countryid){
        this.countryid = countryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getLandmasskm2() {
        return landmasskm2;
    }

    public void setLandmasskm2(long landmasskm2) {
        this.landmasskm2 = landmasskm2;
    }

    public int getMedianrange() {
        return medianage;
    }

    public void setMedianrange(int medianrange) {
        this.medianage = medianrange;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryid=" + countryid +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", landmasskm2=" + landmasskm2 +
                ", medianrange=" + medianage +
                '}';
    }
}
