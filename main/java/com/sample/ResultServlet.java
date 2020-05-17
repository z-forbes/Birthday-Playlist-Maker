package com.sample;

import program.GetUserApproval;
import program.run.Go;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "resultservlet",
        urlPatterns = "/result"
)
public class ResultServlet extends HttpServlet {

    private static String date;
    private static String playlistID = null;
    private static String code;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        playlistID = null;
        date = req.getParameter("Date");
        String uri = GetUserApproval.mkURI();
        req.setAttribute("uri", uri);
        RequestDispatcher view = req.getRequestDispatcher("clickUri.jsp");
        view.forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullQuery = "";
        fullQuery += req.getQueryString();

        if (!fullQuery.contains("code")) {
            RequestDispatcher view = req.getRequestDispatcher("gohome.jsp");
            view.forward(req, resp);
            return;
        }

        if (playlistID == null) {
            code = fullQuery.substring(fullQuery.indexOf("=") + 1);
            playlistID = Go.go(date, code);
        }

        req.setAttribute("playlistID", playlistID);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);
    }

}