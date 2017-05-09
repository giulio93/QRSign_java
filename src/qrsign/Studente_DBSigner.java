/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qrsign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



class Studente_DBSigner {
    
    private String Matricola;
    private int ID_STUDENTE;
    private boolean VALIDATE_MATRICOLA = true;
    private boolean NOT_VALIDATE_MATRICOLA = false;
    private boolean DB_CONNECTION_PROBLEM = false;
    
    private Connection  con = null;
    private Statement  st = null;
    private ResultSet rs = null;
      
    private String url = "jdbc:mysql://127.0.0.1:3306/University";
    private String user="root";
    private String password = "admin";

    Studente_DBSigner() {
         
    }
    
    public void setMatricola(String mat )
    {
        Matricola = mat;
    }        
    
    public String getMatricola()
    {
        String mat;
        mat = Matricola;
        return mat;
    } 
    
    public int getID()
    {
        int id;
        id = ID_STUDENTE;
        return id;
    
    }
    
    public boolean ValidateMatricola()
    {
        
        try {

               Class.forName("com.mysql.jdbc.Driver");//load driver

           } catch (ClassNotFoundException ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
           }



        try{
               con = DriverManager.getConnection(url,user,password);

           } catch (Exception ex) {

               ex.printStackTrace();
           }

           try {

               st = con.createStatement();

           } catch (SQLException ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

           }

           try {

               rs = st.executeQuery("SELECT * FROM STUDENTI");

           } catch (SQLException ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

           }
           try {

                       while(rs.next())
                       {
                           System.out.println(rs.getString("MATRICOLA"));

                           if(Matricola.contains(rs.getString("MATRICOLA")))
                           {
                               Matricola = rs.getString("MATRICOLA");
                               ID_STUDENTE = rs.getInt("ID");
                               return VALIDATE_MATRICOLA;
                           }
                       }

                       return NOT_VALIDATE_MATRICOLA;

           } catch (Exception ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
           }
           return DB_CONNECTION_PROBLEM;

    }
    
}
