/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataBase.DBQuery;
import Logic.MailSend;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
public class userRegistration extends HttpServlet {

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
             String paramname = null;

        String fname = "", mname = "", lname = "", email = "", mobile = "", aadhar = "", pan = "",  pass = "", address = "",squs="",answer="";
        String day="",month="",year="",dob="";
        
        fname=request.getParameter("fname");
        mname=request.getParameter("mname");
        lname=request.getParameter("lname");
        email=request.getParameter("email");
        mobile=request.getParameter("mobile");
        aadhar=request.getParameter("aadhar");
        pan=request.getParameter("pan");
        address=request.getParameter("address");
        dob=request.getParameter("dob");
        squs=request.getParameter("squs");
        answer=request.getParameter("answer");
        pass=request.getParameter("pass");
//        Random r=new Random();
//        for(int i=0;i<4;i++)
//        {
//        pass+=r.nextInt(10);
//        
//        }
        String pattern = "dd-MM-yyyy";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

  String tdate = simpleDateFormat.format(new Date());
  System.out.println(tdate);
  String t[]=tdate.split("-");
        String d[]=dob.split("-");
        
        
        int tyear=Integer.parseInt(t[2]);
        int byear=Integer.parseInt(d[0]);
        int diff=tyear-byear;
            System.out.println("diff "+diff);
            if(diff>=18)
            {
            
                
                 RequestDispatcher view = null;
        try {



            DBQuery db = new DBQuery();

        
   int j=db.add_user(aadhar, fname, lname, lname, email, mobile,dob, pan, address, pass,squs,answer);
         //   int j1 = db.addStudent(srn, fname, mname, lname, email, mobile, pass, course, sem,dob);
            System.out.println("****" + j);
            if (j == 1) {
                
                 MailSend ms=new MailSend();
            String msg="Dear "+fname+" "+mname+" "+lname+", your login details are, UserId "+aadhar+" and password "+pass+"\nPlease visit http://192.168.0.137:8084/Secured_Web_portal/";
            String sub="Secured_Web_portal- Login Details";
            ms.emailUtility(email, sub, msg);
                
                
                HttpSession session = request.getSession();
                session.setAttribute("msg", "Registration successful..");
                session.setAttribute("user",email);
                RequestDispatcher rd = request.getRequestDispatcher("capture_face.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
                
                
            }
            else{
            HttpSession session = request.getSession();
                session.setAttribute("msg", "Age should be above 18");
                RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
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
