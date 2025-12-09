package MasterMainRun;

import com.fpoly.ui.WelcomeForm_1;

/**
 * Lớp khởi chạy chính của ứng dụng.
 */
public class MainRun {
    
    /**
     * Phương thức main, điểm khởi đầu của chương trình.
     */
    public static void main(String[] args) {
        // Đảm bảo Form được tạo trên Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Khởi tạo và hiển thị WelcomeForm (SplashScreen) trước
                // Truyền null và true để nó là modal (tập trung) và không có frame cha
                WelcomeForm_1 welcome = new WelcomeForm_1();
                welcome.setVisible(true);
                
                // Chương trình sẽ tạm dừng ở đây cho đến khi WelcomeForm đóng (dispose())
                // Sau khi WelcomeForm đóng, nó sẽ tự động mở LoginForm trong event của Timer.
            }
        });
    }
}