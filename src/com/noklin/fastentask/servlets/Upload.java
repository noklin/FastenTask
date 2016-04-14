package com.noklin.fastentask.servlets;

import com.noklin.fastentask.data.beans.DataManagerBean;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;

@WebServlet(name =  "Upload"  , urlPatterns = "/upload")
@MultipartConfig
public class Upload extends HttpServlet {

    @EJB
    private DataManagerBean cdDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = "Загрузка успешно завершена";
        Part filePart = request.getPart("file");
        try(InputStream fileContent = filePart.getInputStream()){
            cdDao.addOrUpdateDcFromInputStream(fileContent);
        }catch (IOException | ParserConfigurationException | SAXException ex) {
            System.out.println("Exception: " + ex.getMessage());
            result = "Ошибка: Не верный формат данных.";
        }
        request.setAttribute("result" , result);
        doGet(request , response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("upload.jsp").forward(request, response);
    }
}
