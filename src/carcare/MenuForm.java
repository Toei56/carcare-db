package carcare;

import FormDataBase.FormCustomer;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame {
    MyFont font = new MyFont();
    JPanel pForm = new JPanel(new GridLayout(6,1));
    JButton btAddCust, btAddInv, btAddServ, btAddEmp, btAddInvSer, btClose;

    MenuForm() {
        setTitle("ระบบจัดการข้อมูลพื้นฐาน : ธุรกิจดูแลรักษารถยนต์");
        setSize(550,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        btAddCust = new JButton("ข้อมูลลูกค้า (CUSTOMER)");
        btAddCust.addActionListener(e -> new FormCustomer());
        btAddCust.setFont(font.fTimesRoman);
        p1.add(btAddCust);
        pForm.add(p1);

        JPanel p2 = new JPanel();
        btAddInv = new JButton("ข้อมูลใบแจ้งหนี้ (INVOICE)");
        btAddInv.setFont(font.fTimesRoman);
        p2.add(btAddInv);
        pForm.add(p2);

        JPanel p3 = new JPanel();
        btAddServ = new JButton("ข้อมูลบริการ (SERVICE)");
        btAddServ.setFont(font.fTimesRoman);
        p3.add(btAddServ);
        pForm.add(p3);

        JPanel p4 = new JPanel();
        btAddEmp = new JButton("ข้อมูลพนักงาน (EMPLOYEE)");
        btAddEmp.setFont(font.fTimesRoman);
        p4.add(btAddEmp);
        pForm.add(p4);

        JPanel p5 = new JPanel();
        btAddInvSer = new JButton("ข้อมูลใบแจ้งหนี้ระบุการบริการ (INV_SERV)");
        btAddInvSer.setFont(font.fTimesRoman);
        p5.add(btAddInvSer);
        pForm.add(p5);

        JPanel p6 = new JPanel();
        btClose = new JButton("ปิดหน้านี้");
        btClose.addActionListener(e -> dispose());
        btClose.setFont(font.fTimesRoman);
        p6.add(btClose);
        pForm.add(p6);

        add(pForm);
        setVisible(true);
    }
}
