package Servlets;

import beens.Product;
import connectionDB.ProductDB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            getServletContext()
                    .getRequestDispatcher("/create.jsp")
                    .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        Product product = new Product(name,price);
        ProductDB.insert(product);
        response.sendRedirect(request.getContextPath()+ "/index.jsp");
    }
}
