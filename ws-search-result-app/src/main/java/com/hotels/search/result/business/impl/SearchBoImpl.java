package com.hotels.search.result.business.impl;

import com.hotels.search.result.business.SearchBo;
import com.hotels.search.result.model.City;
import com.hotels.search.result.model.Hotel;
import com.hotels.search.result.model.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by cesponc on 6/13/16.
 */
@Component("searchBo")
public class SearchBoImpl implements SearchBo{

    @Autowired
    private SolrClient server;

    public SearchResult searchByQuery(String query, String city) {


        SolrQuery solrQuery = new SolrQuery(query);
        if(city != null && !StringUtils.isEmpty(city)){
            solrQuery.addFilterQuery("city:\""+city.replaceAll("\"", "") + "\"");
            solrQuery.set("f.cities.facet.sort", "index");
        }

        SearchResult result = new SearchResult();

        try {
            QueryResponse queryResponse = server.query(solrQuery);
            result.setNumFound(queryResponse.getResults().getNumFound());
            List<Hotel> hotels = queryResponse.getBeans(Hotel.class);
            if(hotels.size() > 0){
                result.setHotels(hotels);
            }
            FacetField cities = queryResponse.getFacetField("city");
            if(cities.getValues().size() > 0){
                cities.getValues().forEach(c -> result.addCities(new City(c.getName(), c.getCount())));
            }
            return result;


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
