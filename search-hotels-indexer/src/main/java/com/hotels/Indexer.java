package com.hotels;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class Indexer
{
    public static void main( String[] args )
    {
        if(args.length != 2){
            System.out.println("Erro");
        }
        String urlString = args[0];
        String fileName = args[1];

        SolrClient solr = new HttpSolrClient(urlString);
        System.out.println("\n\n\nIndexing Files...\n\n\n");
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(t -> {
                System.out.println(t);
                String[] itens = t.split(",");
                if(itens.length == 6 && !itens[0].equalsIgnoreCase("ID")){
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", itens[0]);
                    document.addField("name", itens[1]);
                    document.addField("city", itens[2]);
                    document.addField("country", itens[3]);
                    document.addField("stars", itens[4]);
                    document.addField("bookings", itens[5]);
                    document.addField("type", "hotel");
                    try {
                        UpdateResponse response = solr.add(document);
                    }catch(Exception e){
                        System.err.println("Error to index document");
                        e.printStackTrace();
                    }
                }
            });
            solr.commit();
            System.out.println("\n\n\nDocuments indexed with success. \n\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
