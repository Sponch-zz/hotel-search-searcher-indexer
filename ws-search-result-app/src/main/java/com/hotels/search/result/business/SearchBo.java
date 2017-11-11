package com.hotels.search.result.business;

import com.hotels.search.result.model.SearchResult;

/**
 * Created by cesponc on 6/13/16.
 */
public interface SearchBo {

    SearchResult searchByQuery(String query, String city);
}
