package FormDataBase;

import carcare.MyConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormCustomer extends JFrame {
    Connection con = MyConnect.getConnection();

    JPanel pFormCust = new JPanel();

    JTextField txtCustNum, txtCustName, txtCustAddr, txtCustPhone, txtCustMail;
    JTextField txtSearchCust;
    JTable tbCust;
    JButton cmdSaveCust, cmdUpdateCust, cmdDeleteCust, cmdClear;
    DefaultTableModel tbModel;

    public FormCustomer() {
        if(con != null) {
            System.out.println("DataBase Connected");
        } else {
            System.out.println("DataBase Not Connect");
        }

        setTitle("การจัดการข้อมูลลูกค้า (CUSTOMER)");
        setSize(950,380);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pSearch = new JPanel();
        pSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
        pSearch.setBorder(BorderFactory.createTitledBorder("ค้นหา"));
        txtSearchCust = new JTextField(15);
        txtSearchCust.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                showData();
            }
        });
        pSearch.add(txtSearchCust);

        JPanel pForm = new JPanel();
        pForm.setLayout(new GridLayout(10,1,10,5));
        JLabel lbCustNum = new JLabel("เลขที่ลูกค้า (CUST_NUM)");
        pForm.add(lbCustNum);
        txtCustNum = new JTextField();
        pForm.add(txtCustNum);
        JLabel lbCustName = new JLabel("ชื่อลูกค้า (CUST_NAME)");
        pForm.add(lbCustName);
        txtCustName = new JTextField();
        pForm.add(txtCustName);
        JLabel lbCustAddr = new JLabel("ที่อยู่ลูกค้า (CUST_ADDR)");
        pForm.add(lbCustAddr);
        txtCustAddr = new JTextField();
        pForm.add(txtCustAddr);
        JLabel lbCustPhone = new JLabel("โทรศัพท์ลูกค้า (CUST_PHONE)");
        pForm.add(lbCustPhone);
        txtCustPhone = new JTextField();
        pForm.add(txtCustPhone);
        JLabel lbCustMail = new JLabel("อีเมลลูกค้า (CUST_MAIL)");
        pForm.add(lbCustMail);
        txtCustMail = new JTextField();
        pForm.add(txtCustMail);

        JPanel pWest = new JPanel();
        pWest.setPreferredSize(new Dimension(200,400));
        pWest.add(pSearch, BorderLayout.NORTH);
        pWest.add(pForm, BorderLayout.CENTER);

        JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cmdSaveCust = new JButton("เพิ่มข้อมูล");
        cmdSaveCust.addActionListener(e -> insert());
        pCenter.add(cmdSaveCust);
        cmdUpdateCust = new JButton("แก้ไข");
        cmdUpdateCust.addActionListener(e -> update());
        pCenter.add(cmdUpdateCust);
        cmdDeleteCust = new JButton("ลบ");
        cmdDeleteCust.addActionListener(e -> delete());
        pCenter.add(cmdDeleteCust);
        cmdClear = new JButton("ยกเลิก");
        cmdClear.addActionListener(e -> clear());
        pCenter.add(cmdClear);

        JPanel pTable = new JPanel();
        tbCust = new JTable();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(670,257));
        String[] header = {"เลขที่ลูกค้า", "ชื่อลูกค้า", "ที่อยู่ลูกค้า", "หมายเลขโทรศัพท์ลูกค้า", "อีเมลลูกค้า"};
        Object[][] data = {
                {null,null,null,null,null,},
                {null,null,null,null,null,},
                {null,null,null,null,null,},
                {null,null,null,null,null,},
                {null,null,null,null,null,}};
        tbModel = new DefaultTableModel(data, header) {
            public boolean isCellEditable(int row, int columns) {
                return false;
            }
        };
        tbCust.setModel(tbModel);
        tbCust.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tbCust.getSelectedRow();
                cmdSaveCust.setEnabled(false);
                txtCustNum.setEditable(false);
                txtCustNum.setText(tbCust.getValueAt(index, 0).toString());
                txtCustName.setText(tbCust.getValueAt(index, 1).toString());
                txtCustAddr.setText(tbCust.getValueAt(index, 2).toString());
                txtCustPhone.setText(tbCust.getValueAt(index, 3).toString());
                txtCustMail.setText(tbCust.getValueAt(index, 4).toString());
            }
        });
        jScrollPane.setViewportView(tbCust);
        pTable.add(jScrollPane);

        JPanel pEast = new JPanel();
        pEast.setPreferredSize(new Dimension(680,400));
        pEast.add(pCenter, BorderLayout.NORTH);
        pEast.add(pTable, BorderLayout.SOUTH);

        pFormCust.add(pWest, BorderLayout.WEST);
        pFormCust.add(pEast, BorderLayout.EAST);
        add(pFormCust);
        showData();
        setVisible(true);
    }

    public void showData() {
        try {
            int totalRow = tbCust.getRowCount() - 1;
            while (totalRow > 1) {
                tbModel.removeRow(totalRow);
                totalRow--;
            }
            String search = txtSearchCust.getText().trim();
            String sql = " SELECT * FROM CUSTOMER " +
                    "WHERE CUST_NUM LIKE '%" + search + "%'" +
                    "OR CUST_NAME LIKE '%" + search + "%'" +
                    "OR CUST_ADDR LIKE '%" + search + "%'" +
                    "OR CUST_PHONE LIKE '%" + search + "%'" +
                    "OR CUST_MAIL LIKE '%" + search + "%'";
            ResultSet rs = con.createStatement().executeQuery(sql);
            int row = 0;
            while (rs.next()) {
                tbModel.addRow(new Object[0]);
                tbModel.setValueAt(rs.getString("CUST_NUM"), row,0);
                tbModel.setValueAt(rs.getString("CUST_NAME"), row,1);
                tbModel.setValueAt(rs.getString("CUST_ADDR"), row,2);
                tbModel.setValueAt(rs.getString("CUST_PHONE"), row,3);
                tbModel.setValueAt(rs.getString("CUST_MAIL"), row,4);
                row++;
            }
            tbCust.setModel(tbModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insert() {
        try {
            if ((txtCustNum.getText().trim() == "") || (txtCustNum.getText().trim().length() != 4)) {
                JOptionPane.showMessageDialog(this, "กรุณากรอกเลขที่ลูกค้า 4 หลัก",
                        "ผลการบันทึกรายการ", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, txtCustNum.getText().trim());
            pre.setString(2, txtCustName.getText().trim());
            pre.setString(3, txtCustAddr.getText().trim());
            pre.setString(4, txtCustPhone.getText().trim());
            pre.setString(5, txtCustMail.getText().trim());
            if (pre.executeUpdate() != -1) {
                JOptionPane.showMessageDialog(this, "บันทึกข้อมูลลูกค้าเรียบร้อยแล้ว",
                        "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
                showData();
                txtCustNum.setText("");
                txtCustName.setText("");
                txtCustAddr.setText("");
                txtCustPhone.setText("");
                txtCustMail.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
        try {
            if (tbCust.getSelectedRow() == -1) return;
            String sql = " UPDATE CUSTOMER SET " + " CUST_NAME=?, " +
                    " CUST_ADDR=?, " + " CUST_PHONE=?, " +
                    " CUST_MAIL=? " + " WHERE CUST_NUM=? ";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, txtCustName.getText().trim());
            pre.setString(2, txtCustAddr.getText().trim());
            pre.setString(3, txtCustPhone.getText().trim());
            pre.setString(4, txtCustMail.getText().trim());
            pre.setString(5, txtCustNum.getText().trim());
            if (pre.executeUpdate() != -1) {
                JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลลูกค้าเรียบร้อยแล้ว",
                        "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
                showData();
                txtCustNum.setText("");
                txtCustName.setText("");
                txtCustAddr.setText("");
                txtCustPhone.setText("");
                txtCustMail.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void delete() {
        try {
            if (tbCust.getSelectedRow() == -1) return;
            String sql = " DELETE FROM CUSTOMER WHERE CUST_NUM=? ";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, txtCustNum.getText().trim());
            if (pre.executeUpdate() != -1) {
                JOptionPane.showMessageDialog(this, "ลบข้อมูลลูกค้าเรียบร้อยแล้ว",
                        "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
                showData();
                txtCustNum.setText("");
                txtCustName.setText("");
                txtCustAddr.setText("");
                txtCustPhone.setText("");
                txtCustMail.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void clear() {
        cmdSaveCust.setEnabled(true);
        txtCustNum.setEditable(true);
        showData();
        txtCustNum.setText("");
        txtCustName.setText("");
        txtCustAddr.setText("");
        txtCustPhone.setText("");
        txtCustMail.setText("");
    }
}
