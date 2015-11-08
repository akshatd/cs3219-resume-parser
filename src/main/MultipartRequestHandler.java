package main;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class MultipartRequestHandler {
	 
    public static List<FileMeta> uploadByJavaServletAPI(HttpServletRequest request) throws IOException, ServletException{
    	
    	System.out.println(request.toString());
        List<FileMeta> files = new LinkedList<FileMeta>();
 
        // 1. Get all parts
        Collection<Part> parts = request.getParts();
        
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = "";
        
        if (request.getParameter("uploader").equals("applicant")) {
        	savePath = appPath + File.separator + "uploaded_cv";
        }
        else {
        	savePath = appPath + File.separator + "uploaded_job";
        }
        
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
        	System.out.println("created the folder" + savePath);
            fileSaveDir.mkdir();
        }
 
        // 3. Go over each part
        FileMeta temp = null;
        for(Part part:parts){   
        	System.out.println("inside parts");
            // 3.1 if part is multiparts "file"
            if(part.getContentType() != null){
 
                // 3.2 Create a new FileMeta object
                temp = new FileMeta();
                temp.setFileName(getFilename(part));
                temp.setFileSize(part.getSize()/1024 +" Kb");
                temp.setFileType(part.getContentType());
                temp.setContent(part.getInputStream());
 
                String fileName = getFilename(part);
                System.out.println(fileName);
                
                String filePath = savePath + File.separator + fileName;
                part.write(filePath);
                
                if (request.getParameter("uploader").equals("applicant")) {
                	Controller.uploadCV(filePath);
                }
                else {
                	Controller.uploadJob(filePath);
                }
                
                // 3.3 Add created FileMeta object to List<FileMeta> files
                files.add(temp);
 
            }
        }
        return files;
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
 