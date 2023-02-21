package com.example.postgresql__ee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "ServletPostgres", value = "/products")
public class ServletPostgres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Connection productConnection = null;
            String url = "jdbc:postgresql://localhost:5432/Products";
            String userName = "Kiryl";
            String password = "12344321";
           try {
               Class.forName("com.postgresql.jdbc.Driver");
               productConnection = DriverManager
                       .getConnection(url,userName,password);

           }catch (ClassNotFoundException e){
               throw  new RuntimeException();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
