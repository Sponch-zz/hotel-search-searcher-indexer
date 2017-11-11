# Search of Hotels #

This project is divided in 3 components:

[solr-config](https://bitbucket.org/csponchiado/search-hotels/src/HEAD/solr-config/?at=master) - Configuration of Solr

[search-hotels-indexer](https://bitbucket.org/csponchiado/search-hotels/src/HEAD/search-hotels-indexer/?at=master) - Indexer of hotels

[ws-search-result-app](https://bitbucket.org/csponchiado/search-hotels/src/HEAD/ws-search-result-app/?at=master) - Service REST and HTML page

## Procediments ##

1. Clone this project in your home:
    
        cd ~/
        git clone git@github.com:Sponch/hotel-search-searcher-indexer.git
 

2. Install Solr

         http://www.apache.org/dyn/closer.lua/lucene/solr/6.0.1


3. Enter inside the directory where the solr was installed:

        cd /path/to/solr-6.0.1/
 
               
4. Run the Solr

        bin/solr start -f -s  ~/search-hotels/solr-config/home
 
       
5. Access this URL to check if the core was created correctly

        http://localhost:8983/solr/#/hotels
        

6. Enter inside the search-hotels-indexer

        cd ~/search-hotels/search-hotels-indexer
        

7. Run the following command

        mvn clean compile exec:java -Dexec.mainClass="com.hotels.Indexer" -Dexec.args="http://localhost:8983/solr/hotels ../hotels.csv"
        

8. Access the solr to check if the hotels were indexed

        http://localhost:8983/solr/hotels/select?indent=on&q=*:*&wt=json
    
    
9. Access the component ws-search-result-app

        cd ~/search-hotels/ws-search-result-app
      
  
10. Run the app

        mvn clean install jetty:run
      
  
11. Access the REST API

        http://localhost:8000/ws/search/?query=hotel
  
      
12. To filter by country, use the city parameter

        http://localhost:8000/ws/search/?query=hotel&city=Beijing
  
      
13. To access the Web Page, use this URL:

        http://localhost:8000/
       
 
## Web Interface ##
        
![Screen Shot 2016-06-15 at 6.51.35 PM.png](https://bitbucket.org/repo/MxABao/images/546457735-Screen%20Shot%202016-06-15%20at%206.51.35%20PM.png)

## Relevance ##

For adjust of the relevance, I've used the following strategies:

1. Use of **BM25SimilarityFactory**

      * The frequency of terms is not so important in a search for hotels. All the documents indexed are hotels, but there are some hotels that have "hotel" in the name. The frequency of this documents is higher than the others. I changed the value of *k1*, that is used to control or normalize the term frequency, to eliminate the importance of frequency terms.
      * the score for term frequency in a document will be higher in the TF-IDF model than that in the BM25 model
      * Adjust of parameter *b* that is used to control to what degree the document length normalizes term frequency values
      
            <similarity class="solr.BM25SimilarityFactory">
              <float name="k1">2</float>
              <float name="b">0.5</float>
            </similarity>
      
2. Use of **omitNorms="true"** in the schema.xml

      * This configuration is to omit the norms associated with this field. If 3 documents have the term hotel inside the field, the lenght of the field not change the score of the results.
      
3. Boost Function (**bf**)

      * I have used the following function as a boost formula: **log(bookings) * star**
      * The function of the log is to minimize the value of bookings. If I have one old hotel with thousands of bookings, and one new and excellent hotel (5 stars) that jave only one year and not so much bookings, this hotel will have a good relevance too. 
      * I think that the relevance is a combination of stars and bookings, that need to be adjusted according the strategies of the company
      
4. Use of **EDismax**

      * With the use of the bq field, each field has different weight. The weight of the field name is higher the fields city and country
      
                ngram_name^2
                ngram_city^1
                ngram_country^1
        
                name_s^2
                city_s
                country_s

                name_exact^2
                city_exact
                country_exact# hotel-search-searcher-indexer

