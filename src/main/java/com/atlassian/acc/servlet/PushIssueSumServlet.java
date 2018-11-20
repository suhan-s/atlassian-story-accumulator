package com.atlassian.acc.servlet;

import com.atlassian.acc.services.StorySummarizer;
import com.atlassian.acc.util.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class PushIssueSumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameterMap().containsKey("name") ? request.getParameter("name") : null;
        String query = request.getParameterMap().containsKey("query") ? request.getParameter("query") : null;
        Map<String,String> responseMap = new HashMap<>();
        if (Util.isStringSet(name) && Util.isStringSet(query)) {
            try{
                StorySummarizer.pushTotalStoryPoints(name, query);
                responseMap.put("status", "success");
            }catch (Exception e){
                responseMap.put("status", "failed");
                responseMap.put("errorMsg", "Error while getting and pushing data");
            }
        } else {
            responseMap.put("status", "failed");
            responseMap.put("errorMsg", "Missing parameter");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(responseMap));
    }
}
