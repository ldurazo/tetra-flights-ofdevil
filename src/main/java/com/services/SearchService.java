package com.services;

import com.models.SearchRequestQpx;
import com.models.TripOption;
import com.utils.GlobalObjectMapper;
import com.qpxutils.QpxRestOperator;
import com.qpxutils.JsonRequestParser;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private GlobalObjectMapper objectMapper ;
    private JsonRequestParser jsonRequestParser;
    private QpxRestOperator qpxRestOperator;

    @Autowired
    public SearchService(GlobalObjectMapper objectMapper, QpxRestOperator qpxRestOperator, JsonRequestParser jsonRequestParser) {
        this.objectMapper = objectMapper;
        this.qpxRestOperator = qpxRestOperator;
        this.jsonRequestParser = jsonRequestParser;
    }

    public String getJsonStringForRequest(SearchRequestQpx searchRequestQpx){
        return jsonRequestParser.createJsonStringSearchRequest(searchRequestQpx);
    }

    public String getFlightsAsJsonString(String jsonStringForRequest){
        String qpxResponse = qpxRestOperator.getFlightsFromQpxAsJsonString(jsonStringForRequest);
        try {
            JsonNode node = objectMapper.readTree(qpxResponse);
            node = node.get("trips").get("tripOption");
            List<TripOption> list = objectMapper.readValue(node, objectMapper.getTypeFactory().constructCollectionType(List.class, TripOption.class));
            return objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qpxResponse;
    }
}
