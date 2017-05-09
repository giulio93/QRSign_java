/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qrsign;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author root
 */
public class QrSearch extends javax.swing.JFrame implements Runnable, ThreadFactory{

    private Webcam webcam = null; 
   // private WebcamPanel panel = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    public  boolean stopRequested = false;
    //private JTextArea textarea = null;
    private DefaultListModel ls = new DefaultListModel();
    private DefaultListModel db_list = new DefaultListModel();
    
    private Studente_DBSigner st = new Studente_DBSigner();
    private Corso_DBCollector corso = null;
   
    public QrSearch(Corso_DBCollector corso1) {
        initComponents();
        
                
                corso = corso1;
                jList1.setModel(ls);
                
                Dimension size = WebcamResolution.QVGA.getSize();
                webcam = Webcam.getWebcams().get(0);
                webcam.setViewSize(size);

                //panel = new WebcamPanel(webcam);
                //panel.setPreferredSize(size);
                
                jPanel1.setLayout(new java.awt.BorderLayout());
                jPanel1.add(new WebcamPanel(webcam));
                jPanel1.setPreferredSize(size);
                jPanel1.revalidate();
                //jPanel1.repaint();
                
                
                /*textarea = new JTextArea();
                textarea.setEditable(false);
                textarea.setPreferredSize(size);*/
                
               

                //add(jPanel1);
                //add(textarea);
                //add(jButton1);
  
                
                
                Dimension size_Jframe = new Dimension(400,400);
                this.setMinimumSize(size_Jframe);

                pack();
                setVisible(true);

                executor.execute(this);
                
    }

    private QrSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public DefaultListModel get_list_presence()
    {
        DefaultListModel mls;
        mls=ls;
        return mls;
    }

    @Override
        public void run() {

                while(!stopRequested)
                {
                        try {
                                Thread.sleep(100);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }

                       
                        BufferedImage image = null;
                        Result result = null;

                        if (webcam.isOpen()) {

                                if ((image = webcam.getImage()) == null) {
                                        continue;
                                }

                                LuminanceSource source = new BufferedImageLuminanceSource(image);
                                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                                try {
                                        result = new MultiFormatReader().decode(bitmap);
                                } catch (NotFoundException e) {
                                        // fall thru, it means there is no QR code in image
                                }
                        }

                        if (result != null) {
                            
                            
                            
                            if(ls.isEmpty())
                            {
                               
                                st.setMatricola(result.getText());
                                
                                if(st.ValidateMatricola())
                                {
                                    jLabel1.setText("You are signed in");
                                    ls.addElement(st.getMatricola());
                                    db_list.addElement(st.getID());
                                }
                                else  jLabel1.setText("You are NOT signed in");
    
                            }
                            else if(!result.getText().contains(ls.lastElement().toString()))
                            {
                               
                               st.setMatricola(result.getText());
                               
                               if(st.ValidateMatricola())
                                {
                                    jLabel1.setText("You are signed in");
                                    ls.addElement(st.getMatricola());
                                    db_list.addElement(st.getID());
                                    
                                }
                               else jLabel1.setText("You are NOT signed in");
                            }
   
                        }
                       
                }
               
                
        }
    
         @Override
        public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "example-runner");
                t.setDaemon(true);
                return t;
        }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Close Sign Session");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel1.setText("Say to you if you are signed in");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            
       
        
        
       stop();
        
        for(int i=0;i<db_list.getSize();i++)
        {
            DataManager datamanager;
            datamanager = new DataManager(corso.getId(),Integer.parseInt(db_list.getElementAt(i).toString()));
            datamanager.SignPresence();
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void stop()
    {
        stopRequested = true;
        webcam.close();
        webcam = null;
        Webcam.resetDriver();
        this.dispose();
    }
    
    
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QrSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QrSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QrSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QrSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QrSearch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
