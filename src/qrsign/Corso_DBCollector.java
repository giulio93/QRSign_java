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

/**
 *
 * @author root
 */
class Corso_DBCollector {
    
    private Connection  con = null;
    private Statement  st = null;
    private ResultSet rs = null;
      
    private String url = "jdbc:mysql://127.0.0.1:3306/University";
    private String user="root";
    private String password = "admin";
    
    private String docente;
    private String pwd;
    private String nome_corso;
    private int ID;
    
    private boolean VALIDATE_CORSO = true;
    private boolean NOT_VALIDATE_CORSO = false;
    private boolean DB_CONNECTION_PROBLEM = false;
    
    Corso_DBCollector()
    {
                
    }
    
    public void setDocente(String doc)
    {
        docente = doc;
    }
    
    public String getDocente()
    {
        String doc;
        doc = docente;
        return doc;
    }
    
    
    public String getNomeCorso()
    {
        String nc;
        nc = nome_corso;
        return nc;
    }
    
    
    public void setPwd(String password)
    {
        pwd = password;
    }
    
    public String getPwd()
    {
        String password;
        password = pwd;
        return password;
    }
    
    public int getId()
    {
        int id;
        id = ID;
        return id;
    }
    
    public boolean ValidateCorso()
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

               rs = st.executeQuery("SELECT * FROM CORSI");

           } catch (SQLException ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

           }
           try {

                       while(rs.next())
                       {
                           if(docente.equals(rs.getString("DOCENTE")) && pwd.equals(rs.getString("PASSWORD")))
                           {
                               nome_corso = rs.getString("NOME_CORSO");
                               ID = rs.getInt("ID");
                               return VALIDATE_CORSO;
                           }
                               
                       }
                       return NOT_VALIDATE_CORSO;

                      

           } catch (Exception ex) {

               Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
           }
        return DB_CONNECTION_PROBLEM;
           

    }
}
