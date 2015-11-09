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

import java.util.ArrayList;

import common.CV;
import common.Job;
import common.Ranking;

@MultipartConfig(fileSizeThreshold=-1,    // 10 MB 
				maxFileSize=-1,          // 50 MB
				maxRequestSize=-1) 

public class RankRequestHandler extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7269929250136699541L;
	
	/***************************************************
     * URL: /upload
     * doPost(): upload the files and other parameters
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Ranking> ranks = Controller.getRanking(Integer.parseInt(req.getParameter("jobId")));
		
	       // 2. Set response type to json
        resp.setContentType("application/json");
 
        // 3. Convert List<FileMeta> into JSON format
        ObjectMapper mapper = new ObjectMapper();
 
        // 4. Send result to client
        mapper.writeValue(resp.getOutputStream(), ranks);
		
	}
}
