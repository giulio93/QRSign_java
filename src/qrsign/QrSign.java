/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qrsign;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.sql.*;
import qrsign.WebcamQRCodeExample;

/**
 *
 * @author root
 */
public class QrSign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
       /* QRCodeWriter w = new QRCodeWriter();
        BitMatrix m = null;
                
        try {
            
           m = w.encode("159994", BarcodeFormat.QR_CODE, 256, 256);
           
        } catch (WriterException ex) {
            
            ex.printStackTrace();
        }
        
        File f = new File("test.png");
        try {
            
            MatrixToImageWriter.writeToFile(m,"PNG", f);
            System.out.println(f.getAbsolutePath());
            
        } catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
      Webcam webcam = null;
      webcam = Webcam.getWebcams().get(0);
      if(webcam.open())
      {
        webcam.getWebcamListeners();
        BufferedImage img = null;
        img = webcam.getImage();
          try {
              ImageIO.write(img, "png", new File("test2.png"));
          } catch (IOException ex) {
              Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      else System.out.println("Problema");
      */
      
      
      
      /*  try {
            Class.forName("com.mysql.jdbc.Driver");//load driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      Connection  con = null;
      Statement  st = null;
      ResultSet rs = null;
      
      String url = "jdbc:mysql://127.0.0.1:3306/University";
      String user="root";
      String password = "admin";
      
     try{
            con = DriverManager.getConnection(url,user,password);
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            rs = st.executeQuery("SELECT * FROM STUDENTI");
        } catch (SQLException ex) {
            
            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            
                while (rs.next()) {
                    
                    System.out.println(rs.getString("MATRICOLA"));
                    if(rs.getString("MATRICOLA").equals("15994"))
                        System.out.println("Gooood");
                }
                
                
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(QrSign.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
      
       // WebcamQRCodeExample a = new WebcamQRCodeExample();
        //a.run();
   
        
    }
    
        
    
}
