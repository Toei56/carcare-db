package carcare;

import javax.swing.*;
import java.awt.*;

public class MenuReport extends JFrame {
    MyFont font = new MyFont();
    JPanel pReport = new JPanel(new GridLayout(4,1));
    JButton btAddReCust, btAddReFin, btAddReServ, btClose;

    public MenuReport() {
        setTitle("การจัดการรายงาน : ธุรกิจดูแลรักษารถยนต์");
        setSize(500,225);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        btAddReCust = new JButton("รายงานข้อมูลลูกค้า");
        btAddReCust.setFont(font.fTimesRoman);
        p1.add(btAddReCust);
        pReport.add(p1);

        JPanel p2 = new JPanel();
        btAddReFin = new JButton("รายงานข้อมูลการเงิน");
        btAddReFin.setFont(font.fTimesRoman);
        p2.add(btAddReFin);
        pReport.add(p2);

        JPanel p3 = new JPanel();
        btAddReServ = new JButton("รายงานข้อมูลการใช้บริการ");
        btAddReServ.setFont(font.fTimesRoman);
        p3.add(btAddReServ);
        pReport.add(p3);

        JPanel p4 = new JPanel();
        btClose = new JButton("ปิดหน้านี้");
        btClose.setFont(font.fTimesRoman);
        btClose.addActionListener(e -> dispose());
        p4.add(btClose);
        pReport.add(p4);

        add(pReport);
        setVisible(true);
    }
}
