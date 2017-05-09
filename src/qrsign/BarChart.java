/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qrsign;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author root
 */
public class BarChart {

    private Connection con = null;
    private Statement st = null;
    private Statement st2 = null;
    private ResultSet rs = null;

    private String url = "jdbc:mysql://127.0.0.1:3306/University";
    private String user = "root";
    private String password = "admin";

    private DefaultListModel date_ls = new DefaultListModel();
    private DefaultListModel num_ls = new DefaultListModel();
    private int ID_corso;

    public BarChart(int id) {
        
        ID_corso = id;

    }

    public ChartPanel create() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i=0;i<date_ls.getSize();i++)
        {
            dataset.setValue(Integer.parseInt(num_ls.getElementAt(i).toString()), "Presence", date_ls.getElementAt(i).toString());
        }


        JFreeChart chart = ChartFactory.createBarChart("Comparison between Lesson and Presence ",
                "Lesson", "Presence", dataset, PlotOrientation.VERTICAL,
                false, true, false);

        final ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(700, 470));

        try {

            ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);

        } catch (IOException e) {

            System.err.println("Problem occurred creating chart.");

        }

        return chartPanel;
    }

    public void Presenze_DBChart() {
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
            st2 = con.createStatement();

        } catch (SQLException ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

        }

        try {

            rs = st.executeQuery("SELECT DISTINCT(DATA) FROM PRESENZE WHERE ID_CORSO = "+ID_corso+"");

        } catch (SQLException ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            while (rs.next()) {
                date_ls.addElement(rs.getString("DATA"));
                System.out.println(date_ls.lastElement().toString());
            }

        } catch (Exception ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            for(int i=0;i<date_ls.getSize();i++)
            {
                int val =0;
                rs = st2.executeQuery("SELECT COUNT(DATA) FROM PRESENZE WHERE ID_CORSO="+ID_corso+" && DATA='"+date_ls.getElementAt(i).toString()+"'");
                if(rs.next())
                {
                  val =  ((Number) rs.getObject(1)).intValue();
                  num_ls.addElement(val);
                }
                System.out.println(val);
               
                
            }
             

        } catch (SQLException ex) {

            ex.printStackTrace();
            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
            

        }
        try {

        } catch (Exception ex) {

            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
