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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
class DataManager {

    private Connection con = null;
    private Statement st = null;
    private Statement str = null;
    private ResultSet rs = null;

    private String url = "jdbc:mysql://127.0.0.1:3306/University";
    private String user = "root";
    private String password = "admin";
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private String dates = dateFormat.format(date);

    private int ID_corso;
    private int ID_Studente;

    DataManager() {

    }

    DataManager(int ID, int id_stud) {

        ID_corso = ID;
        ID_Studente = id_stud;

    }

    public void SignPresence() {
        try {

            Class.forName("com.mysql.jdbc.Driver");//load driver

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        try {

            st = con.createStatement();

        } catch (SQLException ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

        }

        try {

            rs = st.executeQuery("SELECT * FROM PRESENZE");

        } catch (SQLException ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {

            if (rs.next()) {

                System.out.println("Pieno");
                
                if (ID_corso != rs.getInt("ID_CORSO") || ID_Studente != rs.getInt("ID_STUDENTE") || !dates.equals(rs.getString("DATA"))) {
                    //INSERT INTO prova2  (data) VALUES ('05-05-22');
                    //INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)  
                    //VALUES (value1, value2, value3,...valueN);
                    System.out.println("Non esiste");
                    
                    try {
                        str = con.createStatement();

                    } catch (SQLException ex) {

                        Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

                    }

                    try {

                        str.executeUpdate("INSERT INTO PRESENZE  (ID,ID_CORSO,ID_STUDENTE,DATA) VALUES (0," + ID_corso + "," + ID_Studente + ",'" + dates.toString() + "')");

                    } catch (SQLException ex) {

                        Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
                else  System.out.println("Esiste");

            } else {
                System.out.println("è vuoto");
                //INSERT INTO prova2  (data) VALUES ('05-05-22');
                try {
                    str = con.createStatement();

                } catch (SQLException ex) {

                    Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

                }

                try {

                    str.executeUpdate("INSERT INTO PRESENZE  (ID,ID_CORSO,ID_STUDENTE,DATA) VALUES (1," + ID_corso + "," + ID_Studente + ",'" + dates.toString() + "')");

                } catch (SQLException ex) {

                    Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

        } catch (Exception ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
