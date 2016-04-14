package com.noklin.fastentask.servlets;

import com.noklin.fastentask.data.beans.DataManagerBean;

import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Download" , urlPatterns = "/download")
public class Download extends HttpServlet {

    @EJB
    private DataManagerBean cdDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        if(fileName != null){
            try(OutputStream out = response.getOutputStream()){
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=\"catalog.xml\"";
                response.setHeader(headerKey, headerValue);
                cdDao.writeCdsToStream(out);
            }catch (Throwable ex){
                System.out.println("downloadProblems: " + ex.getMessage());
            }
        }else{
            request.getRequestDispatcher("download.jsp").forward(request, response);
        }
    }
}
