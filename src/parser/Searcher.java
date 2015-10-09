package parser;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

public class Searcher {
 
    private IndexSearcher searcher;
    private QueryParser contentQueryParser;

    public Searcher(String indexDir) throws IOException {
        // open the index directory to search
    	searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(indexDir).toPath())));
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // defining the query parser to search items by content field.
        contentQueryParser = new QueryParser(IndexItem.CONTENT, analyzer);
    }

    
    /**
      * This method is used to find the indexed items by the content.
      * @param queryString - the query string to search for
      */
    public int findByContent(String queryString, int numOfResults) throws ParseException, IOException {
        // create query from the incoming query string.
        Query query = contentQueryParser.parse(queryString);
         // execute the query and get the results
        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
        
        if(queryResults.length>0)
         return 1;
        else 
         return 0;
        
    }
}
