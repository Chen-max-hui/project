package com.mysql.view;

import com.mysql.mapper.UserMapper;
import com.mysql.pojo.User;
import com.mysql.util.DbUtil;
import com.mysql.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LogOnFrm extends JFrame {
    private JPanel contentPane;
    private JTextField userNameTxt;
    private JPasswordField passwordTxt;

    public LogOnFrm() {
        Font font = new Font("Dialog", Font.PLAIN, 12);
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font);
            }
        }

        setResizable(false);
        setTitle("学生信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 343);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("学生信息管理系统");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 23));
        lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/logo.png")));

        JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        lblNewLabel_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/userName.png")));

        JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
        lblNewLabel_2.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));

        userNameTxt = new JTextField();
        userNameTxt.setColumns(10);

        passwordTxt = new JPasswordField();

        JButton btnNewButton = new JButton("\u767B\u5F55");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    loginActionPerformed(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNewButton.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));

        JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/reset.png")));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGap(111).addComponent(lblNewLabel)).addGroup(gl_contentPane.createSequentialGroup().addGap(101).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblNewLabel_1).addComponent(lblNewLabel_2).addComponent(btnNewButton)).addGap(32).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(btnNewButton_1).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(passwordTxt).addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))).addContainerGap(111, Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addGap(30).addComponent(lblNewLabel).addGap(26).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_1).addGap(29).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(36).addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnNewButton).addComponent(btnNewButton_1)).addContainerGap(60, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);

        // 设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }



    private void loginActionPerformed(ActionEvent evt) throws IOException {
        String userName=this.userNameTxt.getText();
        String password=new String(this.passwordTxt.getPassword());
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }

        SqlSession sqlSession = DbUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser(userName, password);
        try {
            if(user!=null){
                dispose();
                new MainFrm().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void resetValueActionPerformed(ActionEvent evt) {
        this.userNameTxt.setText("");
        this.passwordTxt.setText("");
    }
}
