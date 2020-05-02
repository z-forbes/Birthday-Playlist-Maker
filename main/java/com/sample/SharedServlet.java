package com.sample;

import program.GetImageURLForShare;
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
        name = "sharedservlet",
        urlPatterns = "/shared"
)
public class SharedServlet extends HttpServlet {

    public static String shareURL;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullQuery = "";
        fullQuery += req.getQueryString();

        if (!fullQuery.contains("id")) {
            RequestDispatcher view = req.getRequestDispatcher("gohome.jsp");
            view.forward(req, resp);
            return;
        }


        String playlistID = fullQuery.substring(fullQuery.indexOf("=") + 1);
        String examplePLUri = "76Vro891V9PIJLV6FrRxck";
        if (playlistID.length() > examplePLUri.length()) {
            playlistID.substring(0, examplePLUri.length());
        }

        req.setAttribute("shareURL", shareURL);
        req.setAttribute("playlistID", playlistID);
        RequestDispatcher view = req.getRequestDispatcher("shared.jsp");
        view.forward(req, resp);
    }

}