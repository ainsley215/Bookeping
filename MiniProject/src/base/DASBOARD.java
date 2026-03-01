/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package base;

import CONNECTION.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.jfree.chart.JFreeChart;


/**
 *
 * @author ASUS
 */
public class DASBOARD extends javax.swing.JFrame {

    private JFreeChart BarChart;

    /**
     * Creates new form DASBOARD
     */
    public DASBOARD() {
        initComponents();
         lbl_nama.setText(Login.nama_pengguna);
         BarChart();
         tanggal();
         PendapatanHarian();
         PendapatanBulanan();
         
         
    }
    
    
    public void BarChart() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
//            String sql = "SELECT MONTH(tanggal) AS bulan, YEAR(tanggal) AS tahun FROM transaksi_jual "
//                    + "WHERE DATE(tanggal) = '" + tgl + "'";
            String sql = "SELECT DATE_FORMAT(NOW(), '%c') AS Bulan, DATE_FORMAT(NOW(), '%Y') AS Tahun;";
            Connection conn = (Connection) CONNECTION.Conec.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                new CONNECTION.BarChartPendapatan().showBarChart(panel_chart, "PENDAPATAN BULAN INI", 
                        rs.getInt("bulan"), rs.getInt("tahun"));
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }
    

  
    public void PendapatanBulanan() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();

        try {
            String sql = "SELECT SUM(total_harga) AS total FROM tf_jual WHERE month(tanggal_tf_jual) = "
                    + "'" + tgl.substring(5, 7) + "' ";
                        System.out.println("SQl Pendapatan Bulanan: "+sql);

            Connection conn = CONNECTION.Conec.GetConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_bulan.setText(Util.convertToRupiah(0));
                } else {
                    this.lbl_bulan.setText(Util.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }
    public void PendapatanHarian() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(total_harga) AS total FROM tf_jual WHERE DATE(tanggal_tf_jual) = '" + tgl + "'";
            Connection conn = CONNECTION.Conec.GetConnection();
                        System.out.println("SQl Pendapatan harian : "+sql);

            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_hari.setText(Util.convertToRupiah(0));
                } else {
                    this.lbl_hari.setText(Util.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }
    public void tanggal(){
    long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        
        Calendar c = java.util.Calendar.getInstance();
        int tanggal = c.get(Calendar.DAY_OF_MONTH),
            bulan = c.get(Calendar.MONTH),
            tahun = c.get(Calendar.YEAR);
        
        String txt = getNamaHari(c.get(Calendar.DAY_OF_WEEK)) + ", " +
                tanggal + " " + getNamaBulan(bulan) + " "  + tahun;
        
        this.lbl_tanggal.setText(txt);
    }
     private Calendar kalender = Calendar.getInstance();
    
    public String getNamaHari(int dayOfWeek){
        switch(kalender.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY: return "Minggu";
            case Calendar.MONDAY: return "Senin";
            case Calendar.TUESDAY: return "Selasa";
            case Calendar.WEDNESDAY: return "Rabu";
            case Calendar.THURSDAY: return "Kamis";
            case Calendar.FRIDAY: return "Jumat";
            case Calendar.SATURDAY: return "Sabtu";
            default: return "null";
        }
    }
    public String getNamaBulan(int bulan){
        switch(bulan){
            case Calendar.JANUARY: return "Januari";
            case Calendar.FEBRUARY: return "Februari";
            case Calendar.MARCH: return "Maret";
            case Calendar.APRIL: return "April";
            case Calendar.MAY: return "Mei";
            case Calendar.JUNE: return "Juni";
            case Calendar.JULY: return "Juli";
            case Calendar.AUGUST: return "Agustus";
            case Calendar.SEPTEMBER: return "September";
            case Calendar.OCTOBER: return "Oktober";
            case Calendar.NOVEMBER: return "November";
            case Calendar.DECEMBER: return "Desember";
            default: return "null";
        }
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produk = new javax.swing.JButton();
        supplier = new javax.swing.JButton();
        report = new javax.swing.JButton();
        setting = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        lbl_tanggal = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_bulan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl_hari = new javax.swing.JLabel();
        panel_chart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        produk.setBackground(new java.awt.Color(0,0,0,1)
        );
        produk.setBorder(null);
        produk.setContentAreaFilled(false);
        produk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produkActionPerformed(evt);
            }
        });
        getContentPane().add(produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 120, 30));

        supplier.setBackground(new java.awt.Color(0,0,0,1)
        );
        supplier.setBorder(null);
        supplier.setBorderPainted(false);
        supplier.setContentAreaFilled(false);
        supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierActionPerformed(evt);
            }
        });
        getContentPane().add(supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 120, 30));

        report.setBackground(new java.awt.Color(0,0,0,1)
        );
        report.setBorder(null);
        report.setContentAreaFilled(false);
        report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionPerformed(evt);
            }
        });
        getContentPane().add(report, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 310, 110, 30));

        setting.setBackground(new java.awt.Color(0,0,0,1)
        );
        setting.setBorder(null);
        setting.setContentAreaFilled(false);
        setting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingActionPerformed(evt);
            }
        });
        getContentPane().add(setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 150, 30));

        logout.setBackground(new java.awt.Color(0,0,0,1)
        );
        logout.setBorder(null);
        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 120, 30));

        lbl_tanggal.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        getContentPane().add(lbl_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 290, 30));

        lbl_nama.setFont(new java.awt.Font("Nirmala UI", 1, 34)); // NOI18N
        lbl_nama.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbl_namaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 410, 60));

        jPanel3.setBackground(new java.awt.Color(91, 91, 91));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_bulan.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbl_bulan.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 670, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 300, 50));

        jPanel1.setBackground(new java.awt.Color(217, 217, 217));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 20, 60));

        jPanel2.setBackground(new java.awt.Color(91, 91, 91));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_hari.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbl_hari.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 300, 50));

        panel_chart.setBackground(new java.awt.Color(0,0,0,1)
        );
        panel_chart.setBorder(null);
        panel_chart.setOpaque(false);
        panel_chart.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                panel_chartAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        panel_chart.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 820, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TAMPILAN/DASHBOARDNEW.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        setSize(new java.awt.Dimension(1091, 807));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produkActionPerformed
        // TODO add your handling code here:
        PRODUK PRODUK =new PRODUK();
      PRODUK.setVisible(true); 
      this.dispose();
    }//GEN-LAST:event_produkActionPerformed

    private void settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingActionPerformed
        // TODO add your handling code here:
        new SettingAwal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingActionPerformed

    private void supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierActionPerformed
        // TODO add your handling code hetre:
        SUPPLIER SUPPLIER =new SUPPLIER();
        SUPPLIER.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_supplierActionPerformed

    private void reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionPerformed
        // TODO add your handling code here:
        REPORT REPORT =new REPORT();
        REPORT.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reportActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        new Login().setVisible(true);
        this.dispose();
        } else {
        // Tindakan yang diambil jika pengguna mengklik tombol "Tidak" atau menutup dialog
        // Di sini, Anda dapat menambahkan tindakan tambahan atau tidak melakukan apa-apa
    }

        
    }//GEN-LAST:event_logoutActionPerformed

    private void panel_chartAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_panel_chartAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_chartAncestorAdded

    private void lbl_namaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbl_namaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_namaAncestorAdded

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
            java.util.logging.Logger.getLogger(DASBOARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DASBOARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DASBOARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DASBOARD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DASBOARD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_bulan;
    private javax.swing.JLabel lbl_hari;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JButton logout;
    private javax.swing.JPanel panel_chart;
    private javax.swing.JButton produk;
    private javax.swing.JButton report;
    private javax.swing.JButton setting;
    private javax.swing.JButton supplier;
    // End of variables declaration//GEN-END:variables
}
