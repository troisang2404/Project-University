
import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame {

    public MainClass(){
        initMainClass();
    }
    private void initMainClass(){
        Board b = new Board();
        add(b);
        b.setLayout(null);
        setSize(1000, 800);
        setResizable(false);
        setTitle("TANKER v0.7");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainClass ml = new MainClass();
            ml.setVisible(true);
        });
    }

}
