//package servlets.resources;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * Created by gur on 5/24/2016.
// */
//@WebServlet( urlPatterns = {"/resources/*"} )
//public class ResourceDownloadServlet  extends HttpServlet{
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String query = request.getQueryString();
//        String path = query.substring(query.indexOf("resources"));
//        String fileType = path.substring(path.lastIndexOf(".") + 1);
//        String fileName = path.substring(path.lastIndexOf("/") + 1);
//
//        if (true){
//            response.getWriter().println(path);
//        }
//
//
//        // Find this file id in database to get file name, and file type
//
//        // You must tell the browser the file type you are going to send
//        // for example application/pdf, text/plain, text/html, image/jpg
//        response.setContentType("image/"+fileType);
//
//        // Make sure to show the download dialog
//        response.setHeader("Content-disposition","attachment; filename=" + fileName);
//
//        File file = new File(fileName);
//
//        // This should send the file to browser
//        OutputStream out = response.getOutputStream();
//        FileInputStream in = new FileInputStream(file);
//        byte[] buffer = new byte[4096];
//        int length;
//        while ((length = in.read(buffer)) > 0){
//            out.write(buffer, 0, length);
//        }
//        in.close();
//        out.flush();
//
//        response.setStatus(200);
//    }
//}