package com.noklin.fastentask.servlets;

import com.noklin.fastentask.data.beans.DataManagerBean;
import com.noklin.fastentask.data.entities.CdEntity;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShowList" , urlPatterns = "/list")
public class ShowList extends HttpServlet {

    @EJB
    private DataManagerBean cdDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cdTitle = request.getParameter("find");
        if(cdTitle == null){
            List<CdEntity> list = cdDao.getCdList();
            request.setAttribute("data" , list);
        }else{
            CdEntity found = cdDao.findCd(cdTitle);
            if(found != null)
                request.setAttribute("data" , found);
        }
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}

