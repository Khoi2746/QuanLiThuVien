/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.fpoly.ui;

import com.fpoly.ui.MainForm;
import com.fpoly.ui.QuenMatKhauJDialog;
import com.fpoly.utils.XAuth;
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author X1 Carbon
 */
public class LoginForm extends javax.swing.JFrame {

    private final Color HOVER_BUTTON_COLOR = new Color(210, 180, 140); // Màu HOVER: NÂU NHẠT
    private final Color DEFAULT_BUTTON_COLOR = new Color(255, 255, 255); // Màu Mặc định: TRẮNG

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);

        jLabel6.setText("<html>Quên Mật Khẩu?</html>");
        jLabel6.setForeground(java.awt.Color.WHITE);
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        applyHoverEffect(btnLogin, DEFAULT_BUTTON_COLOR, HOVER_BUTTON_COLOR);
        applyHoverEffect(btnEnd, DEFAULT_BUTTON_COLOR, HOVER_BUTTON_COLOR);
//================================ Hiệu ứng Effect========================\\
        // 1. HOVER EFFECT CHO LABEL QUÊN MẬT KHẨU
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                openQuenMatKhauForm();
            }

            // THÊM HOVER VÀO ĐÂY
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                jLabel6.setForeground(HOVER_BUTTON_COLOR); // Nâu nhạt
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                jLabel6.setForeground(java.awt.Color.WHITE); // Trở về Trắng
            }
        });

        // 2. FOCUS EFFECT CHO TXTUSER
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txtUser.setBackground(HOVER_BUTTON_COLOR);
                txtUser.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txtUser.setBackground(Color.WHITE);
                txtUser.setForeground(Color.BLACK);
            }
        });

        // 3. FOCUS EFFECT CHO TXTPASSWORD
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txtPassword.setBackground(HOVER_BUTTON_COLOR);
                txtPassword.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txtPassword.setBackground(Color.WHITE);
                txtPassword.setForeground(Color.BLACK);
            }
        });
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                openQuenMatKhauForm();
            }
        });
//=================================================================================================\\

//======================= Xử Lý Giao Diện txt Mật Khẩu===============================\\
        txtPassword.setText("Mật Khẩu");
        txtPassword.setForeground(new Color(0, 0, 0)); // Màu chữ đen
        txtPassword.setEchoChar((char) 0); // Hiển thị chữ rõ ràng (không phải '*')

        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(txtPassword.getPassword()).equals("Mật Khẩu")) {
                    txtPassword.setText("");
                    txtPassword.setEchoChar('•'); // Bắt đầu che mật khẩu
                    txtPassword.setForeground(Color.BLACK); // Đổi chữ sang đen
                }

                // (Hiệu ứng đổi màu focus)
                txtPassword.setBackground(HOVER_BUTTON_COLOR);
                txtPassword.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (new String(txtPassword.getPassword()).isEmpty()) {
                    txtPassword.setText("Mật Khẩu");
                    txtPassword.setEchoChar((char) 0); // Hiển thị chữ rõ ràng
                    txtPassword.setForeground(new Color(150, 150, 150)); // Đổi chữ sang xám
                } else {
                    txtPassword.setEchoChar('•'); // Vẫn là mật khẩu
                    txtPassword.setForeground(Color.BLACK);
                }

                // (Hiệu ứng đổi màu mặc định)
                txtPassword.setBackground(Color.WHITE);
            }
        });
//======================= Xử Lý Giao Diện txt Tên Đăng Nhập===============================\\
        txtUser.setText("Nhập Tên Đăng Nhập");
        txtUser.setForeground(new Color(150, 150, 150)); // Màu chữ xám cho placeholder
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                // Nếu nội dung hiện tại là placeholder, hãy xóa nó đi
                if (txtUser.getText().equals("Nhập Tên Đăng Nhập")) {
                    txtUser.setText("");
                    txtUser.setForeground(Color.BLACK); // Đổi chữ sang màu đen khi nhập
                }

                // (Giữ lại hiệu ứng đổi màu nâu nhạt khi focus)
                txtUser.setBackground(HOVER_BUTTON_COLOR);
                txtUser.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                // Nếu người dùng không nhập gì, đặt lại placeholder
                if (txtUser.getText().isEmpty()) {
                    txtUser.setText("Nhập Tên Đăng Nhập");
                    txtUser.setForeground(new Color(150, 150, 150)); // Đổi chữ sang màu xám
                }

                // (Giữ lại hiệu ứng đổi màu nền mặc định khi mất focus)
                txtUser.setBackground(Color.WHITE);
                // Lưu ý: Đổi foreground về BLACK nếu nội dung là dữ liệu, 
                // hoặc giữ GRAY nếu nội dung là placeholder
                if (!txtUser.getText().equals("Nhập Tên Đăng Nhập")) {
                    txtUser.setForeground(Color.BLACK);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnEnd = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mật Khẩu:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        txtUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 470, 40));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 470, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên Đăng Nhập:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        btnEnd.setBackground(new java.awt.Color(255, 255, 255));
        btnEnd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnd.setForeground(new java.awt.Color(0, 0, 0));
        btnEnd.setText("Kết Thúc");
        btnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 160, 50));

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setText("Đăng Nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 160, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Quên Mật Khẩu? ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Gemini_Generated_Image_n80j7xn80j7xn80j-removebg-preview.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 160, 150));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trang Đăng Nhập");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 410, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/banner.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        this.login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndActionPerformed
        // TODO add your handling code here:
        this.exit();
    }//GEN-LAST:event_btnEndActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnd;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

// ========================== XỬ LÝ CHỨC NĂNG CÁC NÚT==========================//
    //=====Nút Kết Thúc=====//
    public void exit() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thoát?",
                "Xác nhận thoát",
                javax.swing.JOptionPane.YES_NO_OPTION); 
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void openQuenMatKhauForm() {
        // Mở form Quên mật khẩu
        new QuenMatKhauJDialog(this, true).setVisible(true);
    }

    //=====Nút Đăng Nhập =====//
    public void login() {
        String username = txtUser.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Kiểm tra nhập liệu
        if (username.isEmpty() || password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!");
            return;
        }

        boolean success = XAuth.login(username, password);

        if (success) {

            // ---- Lấy tên Role ----
            String roleName = "";
            int role = XAuth.currentUser.getRoleID();
            switch (role) {
                case 1:
                    roleName = "Admin";
                    break;
                case 2:
                    roleName = "Thủ Thư";
                    break;
                case 3:
                    roleName = "Member";
                    break;
                default:
                    roleName = "Không xác định";
            }

            // ---- Thông báo đăng nhập ----
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Đăng nhập thành công! Chào " + XAuth.currentUser.getFullName() + " (" + roleName + ")"
            );

            //  Dùng duy nhất 1 form chính
            MainForm main = new MainForm(XAuth.currentUser);
            main.setVisible(true);
            this.dispose(); // Đóng LoginForm

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!");
        }
    }

    private void applyHoverEffect(JButton button, Color defaultColor, Color hoverColor) {

        button.setForeground(Color.BLACK);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
                button.setForeground(Color.BLACK);
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
                button.setForeground(Color.BLACK);
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
