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
public class laporan_pengeluaran_hari extends javax.swing.JFrame {

  
    public laporan_pengeluaran_hari() {
        initComponents();
         tanggal();
    }
    public void tanggal(){
    long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
        String tgl = date.toString();
//        txt_tgl.setText(tgl);
        
        java.util.Calendar c = java.util.Calendar.getInstance();
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
    
    public void HeaderColumn() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabel_laporan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tabel_laporan.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tabel_laporan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tabel_laporan.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Verdana", Font.BOLD, 12));

        TableColumn column;
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(0);
        column.setPreferredWidth(120);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(1);
        column.setPreferredWidth(110);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(2);
        column.setPreferredWidth(150);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(3);
        column.setPreferredWidth(180);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(4);
        column.setPreferredWidth(92);
        tabel_laporan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabel_laporan.getColumnModel().getColumn(5);
        column.setPreferredWidth(120);
    }
    
    public void total() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(txt_tgl.getDate()));
        
        try {
            String sql = "SELECT SUM(total_harga) as total FROM tf_beli WHERE tanggal_tf_beli = '"+ tgl +"'";
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

        btn_hari = new javax.swing.JButton();
        btn_bulan = new javax.swing.JButton();
        btn_minggu = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        btn_export = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_laporan = new javax.swing.JTable();
        logout = new javax.swing.JButton();
        total = new javax.swing.JLabel();
        txt_total = new javax.swing.JLabel();
        lbl_tgl = new javax.swing.JLabel();
        txt_tgl = new com.toedter.calendar.JDateChooser();
        btn_export1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

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
        getContentPane().add(btn_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 180, 80));

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
        getContentPane().add(btn_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 180, 80));

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
        getContentPane().add(btn_minggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 180, 80));

        btn_cari.setOpaque(false);
        btn_cari.setContentAreaFilled(false);
        btn_cari.setBackground(new java.awt.Color(0,0,0,1));
        btn_cari.setBorderPainted(false);
        btn_cari.setContentAreaFilled(false);
        btn_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 100, 40, 40));

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
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 20, 110, 30));

        btn_export.setOpaque(false);
        btn_export.setContentAreaFilled(false);
        btn_export.setBackground(new java.awt.Color(0,0,0,1));
        btn_export.setBorderPainted(false);
        btn_export.setContentAreaFilled(false);
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });
        getContentPane().add(btn_export, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 680, 140, 50));

        tabel_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_tf_jual", "Id_pengguna", "Id_supplier", "Id_produk", "nama_produk", "jumlah", "total_harga", "tanggal_tf_beli"
            }
        ));
        jScrollPane1.setViewportView(tabel_laporan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 920, -1));

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
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 150, 30));

        total.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 25)); // NOI18N
        total.setForeground(new java.awt.Color(102, 102, 102));
        total.setText("TOTAL :");
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 690, 120, 40));

        txt_total.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 25)); // NOI18N
        txt_total.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 680, 150, 50));

        lbl_tgl.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        getContentPane().add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 300, 50));
        getContentPane().add(txt_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, -1, -1));

        btn_export1.setBackground(new java.awt.Color(0,0,0,1)
        );
        btn_export1.setBorder(null);
        btn_export1.setContentAreaFilled(false);
        btn_export1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_export1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_export1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 680, 150, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TAMPILAN/HARI.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        setSize(new java.awt.Dimension(1091, 803));
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

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(txt_tgl.getDate()));

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("id_tf_beli");
        tbl.addColumn("id_pengguna");
        tbl.addColumn("id_supplier");
        tbl.addColumn("id_produk");
        tbl.addColumn("nama_produk");
        tbl.addColumn("jumlah");
        tbl.addColumn("total_harga");
        tbl.addColumn("tanggal_tf_beli");
        tabel_laporan.setModel(tbl);
        try {
            String sql = "select * from tf_beli WHERE tanggal_tf_beli = '"+ tgl +"'";
            Connection conn = (Connection) CONNECTION.Conec.GetConnection();  
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_tf_beli"),
                    res.getString("id_pengguna"),
                    res.getString("id_supplier"),
                    res.getString("id_produk"),
                    res.getString("nama_produk"),
                    res.getString("jumlah"),
                    Util.convertToRupiah(Integer.parseInt(res.getString("total_harga"))),
                    res.getString("tanggal_tf_beli"),
                });
                tabel_laporan.setModel(tbl);
                HeaderColumn();
                total();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
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

    private void btn_export1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export1ActionPerformed
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
    }//GEN-LAST:event_btn_export1ActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        new REPORT().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(laporan_pengeluaran_hari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_hari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_hari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan_pengeluaran_hari.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan_pengeluaran_hari().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bulan;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_export1;
    private javax.swing.JButton btn_hari;
    private javax.swing.JButton btn_minggu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JLabel lbl_tgl;
    private javax.swing.JButton logout;
    private javax.swing.JTable tabel_laporan;
    private javax.swing.JLabel total;
    private com.toedter.calendar.JDateChooser txt_tgl;
    private javax.swing.JLabel txt_total;
    // End of variables declaration//GEN-END:variables
}
