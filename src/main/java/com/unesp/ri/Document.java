package com.unesp.ri;
import java.util.Map;
import java.util.ArrayList;

public class Document {
    public Document(String raw, String id) {
        this.raw = raw;
        this.id = id;
        this.calculateIndex();
    }

    public String raw;
    public Map<String, Integer> termFrequencyMap;
    public String id;

    public Map<String, Integer> getTermFrequencyMap() {
        return this.termFrequencyMap;
    }

    // apply the indexer to the document and loads the terms and their frequency into a map 
    public void calculateIndex(){
        try {
            Indexer idx = new Indexer();
            ArrayList<String> terms = idx.getTerms(this.raw);
            this.termFrequencyMap = idx.getTermsFrequency(terms);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } 
    }

    // returns the term frequency of a term in a document
    public Float calculateTF(String term){
        Integer tf = this.getTermFrequencyMap().get(term);
        return 1 + ((float)(Math.log(tf) / Math.log(2)));
    }
}