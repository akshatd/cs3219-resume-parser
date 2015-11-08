package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.FileMeta;

@MultipartConfig(fileSizeThreshold=-1,    // 10 MB 
				maxFileSize=-1,          // 50 MB
				maxRequestSize=-1) 

public class FileUpload extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7269929250136699541L;
	
	private static List<FileMeta> files = new LinkedList<FileMeta>();
	
	/***************************************************
     * URL: /upload
     * doPost(): upload the files and other parameters
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
 
        // 1. Upload File Using Java Servlet API
        files.addAll(MultipartRequestHandler.uploadByJavaServletAPI(request));            
 
        // 1. Upload File Using Apache FileUpload
//        files.addAll(MultipartRequestHandler.uploadByApacheFileUpload(request));
 
        // Remove some files
        while(files.size() > 20)
        {
            files.remove(0);
        }
 
        // 2. Set response type to json
        response.setContentType("application/json");
 
        // 3. Convert List<FileMeta> into JSON format
        ObjectMapper mapper = new ObjectMapper();
 
        // 4. Send result to client
        mapper.writeValue(response.getOutputStream(), files);
 
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("YOUVE REACHED LEL");
	}
}
