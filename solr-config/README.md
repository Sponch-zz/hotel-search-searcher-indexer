# Solr Configuration

This component contains the configuration of Solr (schema.xml, solrconfig.xml, synonyms, stopwords, etc).
To index movies, is necessary initially download the last version of Solr. In this project I've used the 
Solr 6.0.1.

***
## Requirements

Before indexing the movies, it's necessary to download the Solr

1. [Solr](http://www.apache.org/dyn/closer.lua/lucene/solr/6.0.1)

## Procediments

Follow these steps to get started:

1. Clone this project in your home:
    
        cd ~/
        git clone git@bitbucket.org:csponchiado/search-hotels.git


2. Enter inside the directory where the solr was installed:

        cd /path/to/solr-6.0.1/
                
3. Run the Solr

        bin/solr start -s  ~/search-hotels/solr-config/home
        
4. Access this URL to check if the core was created correctly

        http://localhost:8983/solr/#/hotels
        

