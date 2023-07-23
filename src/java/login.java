/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataBase.DBQuery;
import Logic.MailSend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sumit
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             try {
            HttpSession session = request.getSession();
            RequestDispatcher rd = null;

            String email = request.getParameter("email");// 
            String password = request.getParameter("password");// 



            DBQuery dbq = new DBQuery();
         
           String utype = dbq.loginInfo(email, password);

            if (utype.equalsIgnoreCase("admin")) {

                session.setAttribute("login", "Wecome:: " + email);
                session.setAttribute("utype", "admin");
                session.setAttribute("userid", email);
                System.out.println("userid = " + email);

                rd = request.getRequestDispatcher("admin_home.jsp");
                rd.forward(request, response);

            }
            else if (utype.equalsIgnoreCase("User")) {

                session.setAttribute("login", "Wecome:: " + email);
                session.setAttribute("utype", "User");
                session.setAttribute("userid", email);
                System.out.println("userid = " + email);
                String otp="";
                Random r=new Random();
                for(int i=0;i<4;i++)
                {
                otp+=r.nextInt(10);

                }
                session.setAttribute("otp", otp);
                System.out.println("otp = " + otp);
                MailSend ms=new MailSend();
            String msg="Dear User , your OTP for login is "+otp;
            String sub="Secured_Web_portal- Login OTP Details";
            ms.emailUtility(email, sub, msg);
                rd = request.getRequestDispatcher("verify_otp.jsp");
                rd.forward(request, response);

            }
            
            else {

                session.setAttribute("logins", "Sorry: Your Login Failed!!!");
                rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
