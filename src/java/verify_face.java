/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.info;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
public class verify_face extends HttpServlet {

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
             HttpSession session = request.getSession();
            RequestDispatcher rd = null;
            String email=session.getAttribute("userid").toString();
            System.out.println("verify "+email);
            File file = new File(info.py_path+"task1.txt"); 
            FileOutputStream fout=new FileOutputStream(file);
            fout.write("capture".getBytes());
            fout.close();
            
            
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                Logger.getLogger(verify_face.class.getName()).log(Level.SEVERE, null, ex);
            }
            File file1 = new File(info.py_path+"output.txt"); 
  
                    BufferedReader br = new BufferedReader(new FileReader(file1)); 
  
                   String st="",data1=""; 
                   while ((st = br.readLine()) != null) 
                   {
                   System.out.println(st);
                   data1=st;
                   }
                   Random r=new Random();
                   int i=r.nextInt(20);
                   String task="",msg="";
                   
                   if(i<=5)
                   {
                   task="Blynk Left Eye 3 Times";
                   msg="Click to Verify by Blynk Left Eye 3 Times";
                   }
                   else 
                   if(i<=10)
                   {
                   task="Blynk Right Eye 3 Times";
                   msg="Click to Verify by Blynk Right Eye 3 Times";
                   }
                   else 
                   if(i<=15)
                   {
                   task="Blynk Both the  Eyes 3 Times";
                   msg="Click to Verify by Blynk Both Eyes 3 Times";
                   }
                   else{
                   task="Open Mouth";
                   msg="Click to Verify by Opening Mouth";
                    
                   
                   
                   
                   }
            if(data1.equalsIgnoreCase(email))
            {
            session.setAttribute("task", task);
            session.setAttribute("msg", msg);
              rd = request.getRequestDispatcher("face_process.jsp");
                rd.forward(request, response);
            }
            else{
                session.setAttribute("logins", "Sorry: Face Recognition Failed!!!");
             rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            
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
