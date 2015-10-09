package parser;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.io.File;
import java.io.IOException;

public class Indexer {
 private IndexWriter writer;

    public Indexer(String indexDir) throws IOException {
        // create the index
        if(writer == null) {
        writer = new IndexWriter(FSDirectory.open(
                new File(indexDir).toPath()), new IndexWriterConfig(new StandardAnalyzer()));
        }
    }

    /** 
      * This method will add the items into index
      */
    public void index(IndexItem indexItem) throws IOException {

        // deleting the item, if already exists
        writer.deleteDocuments(new Term(IndexItem.ID, indexItem.getId().toString()));

        Document doc = new Document();
        
//        doc.add(new Field(IndexItem.ID, indexItem.getId().toString(), TextField.TYPE_STORED));
//        doc.add(new Field(IndexItem.TITLE, indexItem.getId().toString(), TextField.TYPE_STORED));
//        doc.add(new Field(IndexItem.CONTENT, indexItem.getId().toString(), TextField.TYPE_STORED));
//        
        doc.add(new Field(IndexItem.ID, indexItem.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field(IndexItem.TITLE, indexItem.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field(IndexItem.CONTENT, indexItem.getContent(), Field.Store.YES, Field.Index.ANALYZED));

        // add the document to the index
        writer.addDocument(doc);
    }

    /**
      * Closing the index
      */
    public void close() throws IOException {
        writer.close();
    }
}
