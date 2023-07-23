/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataBase.DBQuery;
import Logic.info;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class verify_face_features extends HttpServlet {

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
            String task=session.getAttribute("task").toString();
            System.out.println("verify "+email);
            System.out.println("task "+task);
           // task="Open Mouth";
            if(task.contains("Mouth"))
            {
            File file = new File(info.py_path+"task_mouth.txt"); 
            FileOutputStream fout=new FileOutputStream(file);
            fout.write("run".getBytes());
            fout.close();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                Logger.getLogger(verify_face.class.getName()).log(Level.SEVERE, null, ex);
            }
             File file1 = new File(info.py_path+"mouth_result.txt"); 
  
                    BufferedReader br = new BufferedReader(new FileReader(file1)); 
  
                   String st="",data1=""; 
                   while ((st = br.readLine()) != null) 
                   {
                   System.out.println(st);
                   data1=st;
                   }
                     FileOutputStream fout1=new FileOutputStream(file1);
                    fout1.write("".getBytes());
                    fout1.close();
                   if(data1.equalsIgnoreCase("open"))
                    {
                    session.setAttribute("task", task);
                    session.setAttribute("msg", "Mouth "+data1+" Verification Process Cleared");
                      rd = request.getRequestDispatcher("user_home.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        session.setAttribute("logins", "Sorry: Mouth Open Verification Failed!!!");
                     rd = request.getRequestDispatcher("verify_security_answer.jsp");
                        rd.forward(request, response);

                    }
            
            
            
            
            }
            else{
             File file = new File(info.py_path+"task_eye.txt"); 
            FileOutputStream fout=new FileOutputStream(file);
            fout.write("run".getBytes());
            fout.close();
            try {
                Thread.sleep(40000);
            } catch (InterruptedException ex) {
                Logger.getLogger(verify_face.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    File file1 = new File(info.py_path+"eye_result.txt"); 
  
                    BufferedReader br = new BufferedReader(new FileReader(file1)); 
  
                   String st="",data1=""; 
                   while ((st = br.readLine()) != null) 
                   {
                   System.out.println(st);
                   data1=st;
                   }
                   String a[]=data1.split("#");
                   int left=Integer.parseInt(a[0]);
                   int right=Integer.parseInt(a[1]);
                   int both=Integer.parseInt(a[2]);
                   if(task.contains("Left") && left>=3)
                    {
                        
                    session.setAttribute("task", task);
                    session.setAttribute("msg", "Left Eye Blynk Detected  "+left+" Time");
                      rd = request.getRequestDispatcher("user_home.jsp");
                        rd.forward(request, response);
                    }
                   else if(task.contains("Right") && right>=3)
                    {
                        
                    session.setAttribute("task", task);
                    session.setAttribute("msg", "Right Eye Blynk Detected  "+right+" Time");
                      rd = request.getRequestDispatcher("user_home.jsp");
                        rd.forward(request, response);
                    }
                   else if(task.contains("Both") && both>=3)
                    {
                        
                    session.setAttribute("task", task);
                    session.setAttribute("msg", "Both Eyes Blynk Detected  "+both+" Time");
                      rd = request.getRequestDispatcher("user_home.jsp");
                        rd.forward(request, response);
                    }
                    else{
                       DBQuery db=new DBQuery();
                 try {
                     String det=db.get_squs(email);
                     String d[]=det.split("@@");
                        session.setAttribute("msg", "Sorry:Eye Blynk Verification Failed!!!");
                        session.setAttribute("squs", d[0]);
                        session.setAttribute("answer", d[1]);
                     rd = request.getRequestDispatcher("verify_security_answer.jsp");
                        rd.forward(request, response);
                 } catch (ClassNotFoundException ex) {
                     Logger.getLogger(verify_face_features.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(verify_face_features.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     

                    }
            
            
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
