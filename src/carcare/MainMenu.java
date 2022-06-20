package carcare;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    MyFont font = new MyFont();
    JButton btMenuForm, btReport, btClose;

    public MainMenu() {
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(4,0));
        JLabel blLogo = new JLabel("บริษัท STOU CARCARE จำกัด", SwingConstants.CENTER);
        blLogo.setFont(new Font("TimesRoman", Font.ITALIC | Font.BOLD, 30));
        c.add(blLogo);

        JPanel p1 = new JPanel();
        btMenuForm = new JButton("ระบบจัดการข้อมูลพื้นฐาน");
        btMenuForm.setFont(font.fTimesRoman);
        btMenuForm.addActionListener(e -> new MenuForm());
        btReport = new JButton("ระบบรายงาน");
        btReport.addActionListener(e -> new MenuReport());
        btReport.setFont(font.fTimesRoman);
        p1.add(btMenuForm);
        p1.add(btReport);
        c.add(p1);

        JPanel p2 = new JPanel();
        btClose = new JButton("ออกจากโปรแกรม");
        btClose.setFont(font.fTimesRoman);
        btClose.addActionListener(e -> System.exit(0));
        p2.add(btClose);
        c.add(p2);
    }

    public static void main(String[] args) {
        MainMenu frame = new MainMenu();
        frame.setSize(650,200);
        frame.setTitle("ระบบธุรกิจดูแลรักษารถยนต์");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
