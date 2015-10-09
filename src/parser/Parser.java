package parser;

import java.io.*;


import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class CVParser {
	// location where the index will be stored.
    private static final String INDEX_DIR = "src/main/resources/";
    private static final String FILE_NAME = "src/resources/SuranjanaSengupta_Resume.pdf";
    private static final int DEFAULT_RESULT_SIZE = 100;

    public static void main(String[] args) throws IOException, ParseException {

        File pdfFile = new File(FILE_NAME);
        IndexItem pdfIndexItem = index(pdfFile);

        // creating an instance of the indexer class and indexing the items
        Indexer indexer = new Indexer(INDEX_DIR);
        indexer.index(pdfIndexItem);
        indexer.close();

        // creating an instance of the Searcher class to the query the index
        Searcher searcher = new Searcher(INDEX_DIR);
        
        int result = searcher.findByContent("Suranjana", DEFAULT_RESULT_SIZE);
        print(result);
    }
    
    //Extract text from PDF document
    public static IndexItem index(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
    }

   //Print the results
    private static void print(int result) {
     if(result==1)
        System.out.println("The document contains the search keyword");
     else
     System.out.println("The document does not contain the search keyword");

    }
    
    

}
