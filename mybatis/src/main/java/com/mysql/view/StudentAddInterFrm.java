package com.mysql.view;

import com.mysql.mapper.SchoolClassMapper;
import com.mysql.mapper.StudentMapper;
import com.mysql.pojo.SchoolClass;
import com.mysql.util.DbUtil;
import com.mysql.util.StringUtil;
import org.apache.ibatis.session.SqlSession;
import com.mysql.pojo.Student;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
public class StudentAddInterFrm extends JInternalFrame {
    private JTextField nameTxt;
    private JTextField snTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField deptTxt;
    private JComboBox schoolClassJcb;
    private JTextArea addressTxt;
    private JRadioButton manJrb;
    private JRadioButton femaleJrb;

//    private DbUtil dbUtil = new DbUtil();
//    private SchoolClassDao schoolClassDao = new SchoolClassDao();
//    private StudentDao studentDao = new StudentDao();

    /**
     * Create the frame.
     */
    public StudentAddInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("添加学生信息");
        setBounds(100, 100, 450, 467);

        JLabel label = new JLabel("学生姓名");
        nameTxt = new JTextField();
        nameTxt.setColumns(10);

        JLabel label_1 = new JLabel("学生学号");
        snTxt = new JTextField();
        snTxt.setColumns(10);


        JLabel label_2 = new JLabel("学生性别");
        manJrb = new JRadioButton("\u7537");
        buttonGroup.add(manJrb);
        manJrb.setSelected(true);


        femaleJrb = new JRadioButton("\u5973");
        buttonGroup.add(femaleJrb);
        JLabel label_3 = new JLabel("所在院系");


        deptTxt = new JTextField();
        deptTxt.setColumns(10);
        JLabel label_4 = new JLabel("家庭住址");

        addressTxt = new JTextArea();

        JLabel label_5 = new JLabel("所在班级");

        schoolClassJcb = new JComboBox();

        JButton button = new JButton("\u6DFB\u52A0");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    bookAddActionPerformed(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button.setIcon(new ImageIcon(StudentAddInterFrm.class.getResource("/images/add.png")));

        JButton button_1 = new JButton("\u91CD\u7F6E");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });
        button_1.setIcon(new ImageIcon(StudentAddInterFrm.class.getResource("/images/reset.png")));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(42).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(button).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(button_1).addGap(232)).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label_5).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(label_4).addComponent(label_2).addComponent(label)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addGroup(groupLayout.createSequentialGroup().addComponent(manJrb).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(femaleJrb)).addComponent(schoolClassJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(35).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(groupLayout.createSequentialGroup().addComponent(label_1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(snTxt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)).addGroup(groupLayout.createSequentialGroup().addComponent(label_3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(deptTxt)))).addComponent(addressTxt)).addContainerGap(44, Short.MAX_VALUE))))));
        Object Alignment;
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGap(42).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label).addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(label_1).addComponent(snTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(29).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_2).addComponent(manJrb).addComponent(femaleJrb).addComponent(label_3).addComponent(deptTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(33).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label_5).addComponent(schoolClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(30).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label_4).addComponent(addressTxt, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(button).addComponent(button_1)).addGap(42)));
        getContentPane().setLayout(groupLayout);

        // 设置文本域边框
        addressTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));


        try {
            fillBookType();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重置事件处理
     *
     * @param e
     */
    private void resetValueActionPerformed(ActionEvent e) {
        this.resetValue();
    }


    private void bookAddActionPerformed(ActionEvent evt) throws IOException {
        String name = this.nameTxt.getText();
        String sn = this.snTxt.getText();
        String dept = this.deptTxt.getText();
        String address = this.addressTxt.getText();
        if (StringUtil.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "学生姓名不能为空！");
            return;
        }

        if (StringUtil.isEmpty(sn)) {
            JOptionPane.showMessageDialog(null, "学生学号不能为空！");
            return;
        }

        if (StringUtil.isEmpty(dept)) {
            JOptionPane.showMessageDialog(null, "学生学院不能为空！");
            return;
        }
        if (StringUtil.isEmpty(address)) {
            JOptionPane.showMessageDialog(null, "学生住址不能为空！");
            return;
        }

        String sex = "";
        if (manJrb.isSelected()) {
            sex = "男";
        } else if (femaleJrb.isSelected()) {
            sex = "女";
        }

        SchoolClass schoolClass = (SchoolClass) schoolClassJcb.getSelectedItem();
        Student student = new Student(null, name, sn,sex, dept, null, address);
        SqlSession sqlSession = DbUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int addNum = mapper.insertStu(student);
        sqlSession.commit();
        try {
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "学生添加成功！");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "学生添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生添加失败！");
        }
    }

    /**
     * 重置表单
     */
    private void resetValue() {
        this.nameTxt.setText("");
        this.snTxt.setText("");
        this.deptTxt.setText("");
        this.manJrb.setSelected(true);
        this.addressTxt.setText("");
        if (this.schoolClassJcb.getItemCount() > 0) {
            this.schoolClassJcb.setSelectedIndex(0);
        }
    }

    /**
     * 初始化学生类别下拉框
     */

    private void fillBookType() throws IOException {
        SqlSession sqlSession = DbUtil.getSqlSession();
        SchoolClassMapper mapper = sqlSession.getMapper(SchoolClassMapper.class);
        List<SchoolClass> Classes = mapper.selectAll();
        Classes.forEach(clazz -> {
            this.schoolClassJcb.addItem(clazz);
        });
    }
}
