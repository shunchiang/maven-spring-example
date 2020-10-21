package com.shundev.countries.controllers;

import com.shundev.countries.models.Country;
import com.shundev.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepo;

    private List<Country> findCountries(List<Country> myList, CheckCountry tester){
        List <Country> tempList = new ArrayList<>();

        for(Country e:myList){
            if(tester.test(e)){
                tempList.add(e);
            }
        }
        return tempList;
    }
    //http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries(){
        List<Country> allCountries = new ArrayList<>();
        countryRepo.findAll().iterator().forEachRemaining(allCountries::add);
        return new ResponseEntity<>(allCountries, HttpStatus.OK);
    }

    //http://localhost:2019/names/start/u

    @GetMapping(value="names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> listAllByLetter(@PathVariable char letter){
        List<Country> myList = new ArrayList<>();
        countryRepo.findAll().iterator().forEachRemaining(myList::add);
        List<Country> rtnList = findCountries(myList, e->e.getName().charAt(0) == letter);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //http://localhost:2019/population/total

    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> displayTotalPop(){
        List<Country> popList = new ArrayList<>();
        countryRepo.findAll().iterator().forEachRemaining(popList::add);
        long total = 0;

        for(Country e: popList){
            total+=e.getPopulation();
        }
        String res = "the total population is: " + total;

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //http://localhost:2019/population/min

    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getMin(){
        List<Country> popList = new ArrayList<>();
        countryRepo.findAll().iterator().forEachRemaining(popList::add);

        popList.sort((v1,v2)-> (int)v2.getPopulation()-(int)v1.getPopulation());



        return new ResponseEntity<>(popList.get(popList.size()-1), HttpStatus.OK);
    }

    //http://localhost:2019/population/max

    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getMax(){
        List<Country> popList = new ArrayList<>();
        countryRepo.findAll().iterator().forEachRemaining(popList::add);

        popList.sort((v1,v2)-> (int)v2.getPopulation()-(int)v1.getPopulation());

        return new ResponseEntity<>(popList.get(0), HttpStatus.OK);
    }
}
