/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package base;

import CONNECTION.Util;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author ASUS
 */
public class laporan_pengeluaran_bulan extends javax.swing.JFrame {

    /**
     * Creates new form laporan_pengeluaran_bulan
     */
    public laporan_pengeluaran_bulan() {
        initComponents();
        tanggal();
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
        
        this.lbl_tgl.setText(txt);
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
    
    public void total() {
        try {
            String sql = "SELECT SUM(total_harga) AS total FROM tf_beli WHERE year(tanggal_tf_beli) ='"+ year.getYear() +"'";
            Connection conn = (Connection) CONNECTION.Conec.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if( rs.getString("total") == null){
                    this.txt_total.setText(Util.convertToRupiah(0));
                } else {
                   this.txt_total.setText(Util.convertToRupiah(Integer.parseInt(rs.getString("total")))); 
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }
    
    public void HeaderColumn() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabel_laporan.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tabel_laporan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tabel_laporan.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Verdana", Font.BOLD, 12));

        TableColumn column;
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(0);
        column.setPreferredWidth(165);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(1);
        column.setPreferredWidth(168);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(2);
        column.setPreferredWidth(270);
    }
    
    private String getBulan(int bulan){
        switch(bulan-1){
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
    public void RubahKeExcel(javax.swing.JTable table, File file) {
        try {
            DefaultTableModel Model_Data = (DefaultTableModel) table.getModel();
            FileWriter ObjWriter = new FileWriter(file);
            for (int i = 0; i < Model_Data.getColumnCount(); i++) {
                ObjWriter.write(Model_Data.getColumnName(i) + "\t");
            }

            ObjWriter.write("\n");

            for (int i = 0; i < Model_Data.getRowCount(); i++) {
                for (int j = 0; j < Model_Data.getColumnCount(); j++) {
                    ObjWriter.write(Model_Data.getValueAt(i, j).toString() + "\t");
                }
                ObjWriter.write("\n");
            }

            ObjWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btn_hari = new javax.swing.JButton();
        btn_bulan = new javax.swing.JButton();
        btn_minggu = new javax.swing.JButton();
        year = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_laporan = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        txt_total = new javax.swing.JLabel();
        btn_export = new javax.swing.JButton();
        lbl_tgl = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_hari.setBackground(new java.awt.Color(0,0,0,1)
        );
        btn_hari.setBorder(null);
        btn_hari.setContentAreaFilled(false);
        btn_hari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 180, 80));

        btn_bulan.setBackground(new java.awt.Color(0,0,0,1)
        );
        btn_bulan.setBorder(null);
        btn_bulan.setContentAreaFilled(false);
        btn_bulan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bulanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 180, 80));

        btn_minggu.setBackground(new java.awt.Color(0,0,0,1)
        );
        btn_minggu.setBorder(null);
        btn_minggu.setContentAreaFilled(false);
        btn_minggu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_minggu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mingguActionPerformed(evt);
            }
        });
        getContentPane().add(btn_minggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 180, 80));
        getContentPane().add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 150, 110, 30));

        jButton1.setBackground(new java.awt.Color(0,0,0,1));
        jButton1.setText("jButton1");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 140, 40, 40));

        tabel_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tahun", "Bulan", "Pendapatan per Bulan"
            }
        ));
        jScrollPane1.setViewportView(tabel_laporan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 620, 400));

        total.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 25)); // NOI18N
        total.setForeground(new java.awt.Color(102, 102, 102));
        total.setText("TOTAL :");
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 680, 120, 40));

        txt_total.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 25)); // NOI18N
        txt_total.setForeground(new java.awt.Color(102, 102, 102));
        txt_total.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txt_totalAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 680, 150, 50));

        btn_export.setBackground(new java.awt.Color(0,0,0,1)
        );
        btn_export.setBorder(null);
        btn_export.setContentAreaFilled(false);
        btn_export.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });
        getContentPane().add(btn_export, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 670, 150, 60));

        lbl_tgl.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbl_tgl.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 310, 50));

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
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 150, 40));

        kembali.setBackground(new java.awt.Color(0,0,0,1)
        );
        kembali.setBorder(null);
        kembali.setContentAreaFilled(false);
        kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TAMPILAN/Bulan.png"))); // NOI18N
        jLabel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        setSize(new java.awt.Dimension(1091, 804));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hariActionPerformed
        // TODO add your handling code here:
        new laporan_pengeluaran_hari().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_hariActionPerformed

    private void btn_bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bulanActionPerformed
        // TODO add your handling code here:
        new laporan_pengeluaran_bulan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_bulanActionPerformed

    private void btn_mingguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mingguActionPerformed
        new laporan_pengeluaran_minggu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_mingguActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Tahun");
        tbl.addColumn("Bulan");
        tbl.addColumn("Total Perbulan");
        tabel_laporan.setModel(tbl);
        try {
            String sql = "SELECT SUM(total_harga) AS totalPerbulan ,  MONTH(tanggal_tf_beli) AS Bulan_ke , YEAR(tanggal_tf_beli) AS Tahun FROM tf_beli "
                    + "WHERE MONTH(tanggal_tf_beli) >=1 AND YEAR(tanggal_tf_beli) = '"+ year.getYear() +"' GROUP BY MONTH(tanggal_tf_beli)";
            Connection conn = (Connection) CONNECTION.Conec.GetConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("Tahun"),
                    this.getBulan(res.getInt("Bulan_ke")),
                    Util.convertToRupiah(Integer.parseInt(res.getString("totalPerbulan")))
                });
                tabel_laporan.setModel(tbl);
                HeaderColumn();
                total();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser path = new JFileChooser();
        path.showOpenDialog(this);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            File f = path.getSelectedFile();
            String location = f.getAbsolutePath();
            String filename = location + "_" + date + ".xls";
            JOptionPane.showMessageDialog(null, filename);
            File fp = new File(filename);

            RubahKeExcel(tabel_laporan, fp);

            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI EXPORT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_exportActionPerformed

    private void jLabel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1AncestorAdded

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

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        REPORT REPORT=new REPORT();
        REPORT.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void txt_totalAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txt_totalAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalAncestorAdded

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
            java.util.logging.Logger.getLogger(laporan_pengeluaran_bulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_bulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_bulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_bulan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan_pengeluaran_bulan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bulan;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_hari;
    private javax.swing.JButton btn_minggu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JLabel lbl_tgl;
    private javax.swing.JButton logout;
    private javax.swing.JTable tabel_laporan;
    private javax.swing.JLabel total;
    private javax.swing.JLabel txt_total;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables

}
