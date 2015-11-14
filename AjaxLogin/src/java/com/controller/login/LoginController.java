
/*
    whatsapp: 1-809-978-5552
    email: yoloprogramo22@gmail.com
    kakaotalk: JoanVasquez
*/

package com.controller.login;

import com.google.gson.Gson;
import com.models.login.LoginModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login.co"})
public class LoginController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HttpSession httpSession = request.getSession(false);
        LoginModel login;
        Map<String, String> errors = new HashMap<>();
        Map<String, String> success = new HashMap<>();
        InternetAddress internetAddress;
        String datas;
        Gson gson = new Gson();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String action = request.getParameter("action");
        System.out.println(action);
        
        try (PrintWriter out = response.getWriter()) {
            
            if (!action.isEmpty() && action.equals("login")) {
                if (email == null || email.trim().isEmpty()) {
                    errors.put("emailError", "You must specify an email");
                } else if (email.length() > 30) {
                    errors.put("emailError", "Email must be up to 30 chars");
                } else {
                    try {
                        internetAddress = new InternetAddress(email);
                        internetAddress.validate();
                    } catch (AddressException ex) {
                        errors.put("emailError", "You must specify a real email please" + ex.getMessage());
                    }
                }
                
                if (pass == null || pass.trim().isEmpty()) {
                    errors.put("passError", "You must specify a password");
                } else if (pass.length() > 30) {
                    errors.put("passError", "Password must be up to 30 chars");
                }
                
                if (errors.isEmpty()) {
                    
                    if (httpSession.getAttribute("user") != null) {
                        success.put("success", "true");
                        datas = gson.toJson(success);
                        out.println(datas);
                    } else {
                        
                        if (email.equals("admin@gmail.com") && pass.equals("1234")) {
                            login = new LoginModel();
                            login.setEmail(email);
                            httpSession.setAttribute("user", login);
                            success.put("success", "true");
                            datas = gson.toJson(success);
                            out.println(datas);
                        } else {
                            errors.put("loginError", "Wrong email or password");
                            datas = gson.toJson(errors);
                            out.println(datas);
                        }
                        
                    }
                    
                } else {
                    datas = gson.toJson(errors);
                    out.println(datas);
                }
            } else if (!action.isEmpty() && action.equals("logout")) {
                
                if (httpSession != null) {
                    httpSession.invalidate();
                    success.put("logout", "true");
                    datas = gson.toJson(success);
                    out.println(datas);
                }
                
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
