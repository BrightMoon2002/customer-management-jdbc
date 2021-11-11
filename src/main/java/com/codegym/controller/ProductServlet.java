package com.codegym.controller;

import com.codegym.dao.ProductDAO;
import com.codegym.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            case "search":
                showSearchProductByProducer(req, resp);
                break;
            case "searchP":
                showSearchForm(req, resp);
                break;
            case "searchD":
                showSearchProductByDescription(req, resp);
                break;
            case "sortByName":
                showListByName(req, resp);
                break;
            default:
                listProduct(req, resp);
                break;
        }

    }

    private void showListByName(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        productList = productDAO.sortByName();
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchProductByDescription(HttpServletRequest req, HttpServletResponse resp) {
        String description = req.getParameter("searchD");
        List<Product> productList;
        productList = productDAO.selectProductByDescription(description);
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchForm(HttpServletRequest req, HttpServletResponse resp) {
        String producer = req.getParameter("producer");
        req.setAttribute("producer", producer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/searchByProducer.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchProductByProducer(HttpServletRequest req, HttpServletResponse resp) {
        String producer = req.getParameter("searchP");
        List<Product> productList;
        productList = productDAO.selectProductByProducer(producer);
        req.setAttribute("list", productList);
        RequestDispatcher dispatcher;
        if (productList == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
        } else {
           dispatcher = req.getRequestDispatcher("product/list.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
       RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void listProduct (HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList = productDAO.selectAllProduct();
        req.setAttribute("list", productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "searchP":
                showSearchProductByProducer(req, resp);
                break;
            default:
                listProduct(req,resp);
                break;
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id =  Integer.parseInt(req.getParameter("id"));
        Product productDelete = productDAO.selectProduct(id);
        req.setAttribute("productDelete", productDelete);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/delete.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productDelete = productDAO.selectProduct(id);
        RequestDispatcher dispatcher;
        if (productDelete == null) {
            dispatcher = req.getRequestDispatcher("product/error-404.jsp");
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            productDAO.deleteProduct(id);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productEdit = productDAO.selectProduct(id);
        req.setAttribute("productEdit", productEdit);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String producer = req.getParameter("producer");
        double price = Double.parseDouble(req.getParameter("price"));
        Product productEdit = productDAO.selectProduct(id);
        RequestDispatcher dispatcher;
        if (productEdit == null) {
           dispatcher= req.getRequestDispatcher("product/error-404.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            productEdit = new Product(id, name, description, producer, price);
            productDAO.updateProduct(productEdit);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }


    private void createNewProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String producer = req.getParameter("producer");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(name, description, producer, price);
        try {
            productDAO.insertProduct(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
