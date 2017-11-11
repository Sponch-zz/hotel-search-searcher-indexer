package com.hotels.search.result.services;

import com.hotels.search.result.business.SearchBo;
import com.hotels.search.result.model.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.status;

@Component
@Path("/search")
@Produces("application/json")
@Api(value = "/search", produces = "application/json", consumes = "application/json")
public class SearchService {

    @Autowired
    private SearchBo searchBo;

    @GET
    @Path("/")
    @ApiOperation(value = "Search hotels by query", notes = "Returns a list of hotels")
    public Response search(@QueryParam("query") String query, @QueryParam("city") String city) {

        SearchResult searchResult = this.searchBo.searchByQuery(query, city);

        return status(200).entity(searchResult).build();

    }

}
