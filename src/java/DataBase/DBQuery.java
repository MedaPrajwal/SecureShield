/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBQuery {

    Connection con = null;
    ResultSet rs = null;
    Statement st = null;

    public String loginInfo(String email, String password) throws ClassNotFoundException, SQLException {

        String utype = "";
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select * from login where email='" + email + "' and pass='" + password + "'";
        System.out.println("value of email and password: " + email + "   " + password);
        System.out.println("q = " + q);
        rs = st.executeQuery(q);

        if (rs.next()) {

            utype = rs.getString("utype");//vuvu
        }//jhvhjvh

        return utype;
    }



 
     public int add_user(String aadhar, String first_name, String middle_name, String last_name, String email, String mobile,String dob, String pan, String address,String pass,String squs,String answer) throws ClassNotFoundException, SQLException {
        int i = 0;
        String q = "insert into user_details values('" + aadhar + "','" + first_name + "','" + middle_name + "','" + last_name + "','" + email + "','" + mobile + "', '"+dob+"', '" + pan + "','" + address + "','" + squs + "','" + answer + "')";
         System.out.println(q);
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        String utype = "User";
        String q1 = "insert into login values('" + email + "','" + pass + "','" + utype + "')";
        i = st.executeUpdate(q1);
        return i;
    }
 
     
     
    public String get_squs(String email) throws ClassNotFoundException, SQLException {

        String squs = "",answer="";
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q = "select squs from user_details where email='" + email + "' ";
 
        System.out.println("q = " + q);
        rs = st.executeQuery(q);

        if (rs.next()) {

            squs = rs.getString("squs");
            answer = rs.getString("answer");
        }//jhvhjvh

        return squs+"@@"+answer;
    }

     
     
     
     
     
     
     
     
     
     
     
 public int add_message(String sender, String receiver, String msg) throws ClassNotFoundException, SQLException {
        int i = 0;
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String tdate = simpleDateFormat.format(new Date());
        System.out.println(tdate);

        String  pattern1 = "hh-mm-ss";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

        String tdate1 = simpleDateFormat1.format(new Date());
        tdate1=tdate1.replace(":", "-");
        System.out.println(tdate1);
        String q = "insert into message_details set sender='" + sender + "',receiver='" + receiver + "',message='" + msg + "',mdate='" + tdate + "',mtime='" + tdate1 + "'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
       
        return i;
    }
     public ArrayList get_review_details(){
    ArrayList al=new ArrayList();
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select * from review_details";
        rs=st.executeQuery(q);
        while(rs.next())
        {
        al.add(rs.getInt("review_id")+"#"+rs.getString("review_num")+"#"+rs.getString("rdate")+"#"+rs.getString("from_time")+"#"+rs.getString("to_time")+"#"+rs.getString("guides")+"#"+rs.getString("c_course")+"#"+rs.getString("sem")+"#"+rs.getString("status"));
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return al;
    }
    public int addStudent(String srn, String fName, String mName, String lName, String email, String mobile, String pass, String course, String sem,String dob) throws ClassNotFoundException, SQLException {
        int i = 0;
        String q = "insert into student_Details values('" + srn + "','" + fName + "','" + mName + "','" + lName + "','" + email + "','" + mobile + "','" + course + "','" + sem + "','" + dob + "')";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        String utype = "student";
        String q1 = "insert into login values('" + srn + "','" + pass + "','" + utype + "')";
        i = st.executeUpdate(q1);
        return i;
    }

    public ArrayList get_guide_details(){
    ArrayList al=new ArrayList();
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select * from guide_details";
        rs=st.executeQuery(q);
        while(rs.next())
        {
        al.add(rs.getString("emp_id")+"#"+rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname")+"#"+rs.getString("email")+"#"+rs.getString("mobile")+"#"+rs.getString("course")+"#"+rs.getString("qualification")+"#"+rs.getString("specialization"));
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return al;
    }
     public int get_guide_count(){
   int c=0;
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select count(*) from guide_details";
        rs=st.executeQuery(q);
        while(rs.next())
        {
        c=rs.getInt(1);
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return c;
    }
     
          public int get_student_count(){
   int c=0;
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select count(*) from student_details";
        rs=st.executeQuery(q);
        while(rs.next())
        {
        c=rs.getInt(1);
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return c;
    }
      public int get_accepted_guide_count(String empid){
   int c=0;
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select count(*) from proposal_requests where guide='"+empid+"'" ;
        rs=st.executeQuery(q);
        while(rs.next())
        {
        c=rs.getInt(1);
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return c;
    }
     
     public int add_file_details(String srn,String review_id,String category,String fname) throws ClassNotFoundException, SQLException{
        String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                        String tdate = simpleDateFormat.format(new Date());
                        System.out.println(tdate);
                        
                        String  pattern1 = "hh-mm-ss";
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

                        String tdate1 = simpleDateFormat1.format(new Date());
                        tdate1=tdate1.replace(":", "-");
                        System.out.println(tdate1);
        String qry = "insert into file_details set srn='"+srn+"',review_id='"+review_id+"',category='" + category + "',fname='"+fname+"',sdate='"+tdate+"',stime='"+tdate1+"'";
        
        con = DBConnection.getConnection();
        Statement stat = con.createStatement();
        
        return stat.executeUpdate(qry);
    }
     
     
      public int add_proposal(String srn, String title, String domain, String description, String guide1, String guide2, String guide) throws ClassNotFoundException, SQLException {
        int i = 0;
          String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                        String tdate = simpleDateFormat.format(new Date());
                        System.out.println(tdate);
                        
                        String  pattern1 = "hh-mm-ss";
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

                        String tdate1 = simpleDateFormat1.format(new Date());
                        tdate1=tdate1.replace(":", "-");
                        System.out.println(tdate1);
                        
        String q = "insert into proposal_requests set srn='" + srn + "',title='" + title + "',domain='" + domain + "',description='" + description + "',preference1='" + guide1 + "',preference2='" + guide2 + "',guide='" + guide + "',rdate='" + tdate + "',rtime='" + tdate1 + "',status='allocated'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        
        return i;
    }
    public int allot_marks(String review_id, String srn, String marks, String user) throws ClassNotFoundException, SQLException {
        int i = 0;
        String q1="delete from marks_details where review_id='" + review_id + "' and srn='" + srn + "'";
        String q = "insert into marks_details set review_id='" + review_id + "',srn='" + srn + "',marks='" + marks + "',alloted_by='" + user + "'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q1);
        i = st.executeUpdate(q);
        
        return i;
    }  
      public int update_proposal(String rid, String guide) throws ClassNotFoundException, SQLException {
        int i = 0;
         
                        
        String q = "update  proposal_requests set guide='" + guide + "' where rid='" + rid + "'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        
        return i;
    }
      public int send_request(String review_id, String srn,String empid) throws ClassNotFoundException, SQLException {
        int i = 0;
         
                        
        String q = "insert into  request_details set review_id='" + review_id + "',srn='" + srn + "',empid='"+empid+"',status='pending'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        
        return i;
    }
     public int update_request(String request_id, String status) throws ClassNotFoundException, SQLException {
        int i = 0;
         
                        
        String q = "update request_details set status='" + status + "' where rid='"+request_id+"'";
        con = DBConnection.getConnection();
        st = con.createStatement();
        i = st.executeUpdate(q);
        
        return i;
    } 
      public int update_review_details(String review_id, String srn,String empid) throws ClassNotFoundException, SQLException {
        int i = 0;
        String oempis="",osrns="";
        con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select * from review_details where review_id='" + review_id + "'";            
        System.out.println(q);
        rs=st.executeQuery(q);
        if(rs.next())
        {
        oempis=rs.getString("guides");
        osrns=rs.getString("srns");
        }
          System.out.println("oempis="+oempis);
          System.out.println("osrns="+osrns);
        if(!oempis.equals("") ||oempis==null )
        {
        oempis=oempis+","+empid;
        }
        else{
        oempis=empid;
        }
        if(!osrns.equals("") || osrns==null)
        {
        osrns=osrns+","+srn;
        }
        else{
        osrns=srn;
        }
        
        String q1 = "update  review_details set srns='" + osrns + "',guides='"+oempis+"' where review_id='" + review_id + "'";
        
        System.out.println(q1);
        
        i = st.executeUpdate(q1);
        
        return i;
    }
      
      
      public ArrayList get_guide_details(String g1,String g2){
    ArrayList al=new ArrayList();
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select * from guide_details where emp_id <> '"+g1+"' and emp_id <> '"+g2+"'";
        System.out.println(q);
        rs=st.executeQuery(q);
        while(rs.next())
        {
        al.add(rs.getString("emp_id")+"#"+rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname")+"#"+rs.getString("email")+"#"+rs.getString("mobile")+"#"+rs.getString("course")+"#"+rs.getString("qualification")+"#"+rs.getString("specialization"));
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return al;
    }
      
      
       public ArrayList get_student_details(String srn){
    ArrayList al=new ArrayList();
    try{
    con = DBConnection.getConnection();
        st = con.createStatement();
        String q="select * from student_details where srn ='"+srn+"'";
        System.out.println(q);
        rs=st.executeQuery(q);
        if(rs.next())
        {
        al.add(rs.getString("fname"));
        al.add(rs.getString("mname"));
        al.add(rs.getString("lname"));

        al.add(rs.getString("email"));
        al.add(rs.getString("mobile"));
        al.add(rs.getString("course"));
        al.add(rs.getString("sem"));
        al.add(rs.getString("dob"));
        }
    }catch(Exception e)
    {
    e.printStackTrace();
    }
    return al;
    }
}
