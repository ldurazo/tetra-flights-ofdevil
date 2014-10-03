package com.controllers;

import com.models.SearchRequestModel;
import com.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/*
 * TODO very important: i have to refactor everything i put here, first make it work dude, then clean your mess up.
 */


@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //Dummy link to perform a search with this API
    //http://localhost:8080/mvn-webapp-flights/search?origin=SFO&destination=MEX&passengers=1&date=2014-12-19
    @RequestMapping(value="/search", produces="application/json")
    public @ResponseBody ResponseEntity<String> search(
                         @RequestParam(value="origin",                    required=true)                       String origin,
                         @RequestParam(value="destination",               required=true)                       String destination,
                         @RequestParam(value="passengers",                required=true)                       String passengers,
                         @RequestParam(value="date",                      required=true)                       String date,
                         @RequestParam(value="solutions",                 required=false, defaultValue = "10") String solutions) {

        //Logic to parse the parameters as json
        SearchRequestModel search = new SearchRequestModel(origin, destination, date, passengers, solutions);
        String jsonStringForRequest = searchService.getJsonStringForRequest(search);
        String jsonResponse = searchService.getFlightsAsJsonString(jsonStringForRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        Date currentTime = new Date(System.currentTimeMillis());
        responseHeaders.set("Date", currentTime.toString());
        return new ResponseEntity<String>(jsonResponse, responseHeaders, HttpStatus.OK);
    }
}