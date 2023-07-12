package com.mysql.view;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.mapper.SchoolClassMapper;
import com.mysql.mapper.StudentMapper;
import com.mysql.pojo.Student;
import com.mysql.pojo.SchoolClass;
import com.mysql.util.DbUtil;
import com.mysql.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

public class StudentManageInterFrm extends JInternalFrame {
    private JTable studentTable;
    private JTextField s_nameTxt;
    private JTextField s_snTxt;
    private JComboBox s_schoolClassJcb;
    private JRadioButton manJrb ;
    private JRadioButton femaleJrb ;
    private JTextArea addressTxt;
    private JComboBox schoolClassJcb ;

    private DbUtil dbUtil=new DbUtil();
    private JTextField idTxt;
    private JTextField nameTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField deptTxt;
    private JTextField snTxt;
    public StudentManageInterFrm() throws IOException {
        setClosable(true);
        setIconifiable(true);
        setTitle("学生信息管理主界面");
        setBounds(100, 100, 677, 487);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scrollPane)))
                                .addContainerGap(66, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(28)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JLabel lblNewLabel = new JLabel("\u7F16\u53F7\uFF1A");

        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("学生姓名");

        nameTxt = new JTextField();
        nameTxt.setColumns(10);

        JLabel label_3 = new JLabel("学生性别");

        manJrb = new JRadioButton("\u7537");
        buttonGroup.add(manJrb);
        manJrb.setSelected(true);

        femaleJrb = new JRadioButton("\u5973");
        buttonGroup.add(femaleJrb);

        JLabel label_4 = new JLabel("所在学院");

        deptTxt = new JTextField();
        deptTxt.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("学生学号");

        snTxt = new JTextField();
        snTxt.setColumns(10);

        JLabel label_5 = new JLabel("所属班级");

        schoolClassJcb = new JComboBox();

        JLabel label_6 = new JLabel("家庭住址");

        addressTxt = new JTextArea();

        JButton button_1 = new JButton("\u4FEE\u6539");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    studentUpdateActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_1.setIcon(new ImageIcon(StudentManageInterFrm.class.getResource("/images/modify.png")));

        JButton button_2 = new JButton("\u5220\u9664");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    studentDeleteActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_2.setIcon(new ImageIcon(StudentManageInterFrm.class.getResource("/images/delete.png")));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(19)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(button_1)
                                                .addGap(18)
                                                .addComponent(button_2)
                                                .addGap(386))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(label_6)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(addressTxt))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(label_4)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(deptTxt))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(lblNewLabel)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(26)
                                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(lblNewLabel_1)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(lblNewLabel_2)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(snTxt)))
                                                                .addGap(26)
                                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(label_3)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(manJrb)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(femaleJrb))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(label_5)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(schoolClassJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                                .addContainerGap(86, Short.MAX_VALUE))))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(21)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_3)
                                        .addComponent(manJrb)
                                        .addComponent(femaleJrb))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_4)
                                        .addComponent(deptTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_2)
                                        .addComponent(snTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_5)
                                        .addComponent(schoolClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_6)
                                        .addComponent(addressTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(button_1)
                                        .addComponent(button_2)))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel label = new JLabel("学生姓名");

        s_nameTxt = new JTextField();
        s_nameTxt.setColumns(10);

        JLabel label_1 = new JLabel("学生编号");

        s_snTxt = new JTextField();
        s_snTxt.setColumns(10);

        JLabel label_2 = new JLabel("所属班级");

        s_schoolClassJcb = new JComboBox();

        JButton button = new JButton("\u67E5\u8BE2");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    bookSearchActionPerformed(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button.setIcon(new ImageIcon(StudentManageInterFrm.class.getResource("/images/search.png")));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(19)
                                .addComponent(label)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(s_nameTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(label_1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(s_snTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(label_2)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(s_schoolClassJcb, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(button)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(s_nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1)
                                        .addComponent(s_snTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_2)
                                        .addComponent(s_schoolClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addGap(16))
        );
        panel.setLayout(gl_panel);

        studentTable = new JTable();
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent met) {
                studentTableMousePressed(met);
            }
        });
        scrollPane.setViewportView(studentTable);
        studentTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u7F16\u53F7", "学生姓名", "学生学号", "学生性别", "所属院系", "家庭住址", "所属班级"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        studentTable.getColumnModel().getColumn(5).setPreferredWidth(119);
        getContentPane().setLayout(groupLayout);

        // 设置文本域边框
        addressTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));


        this.fillBookType("search");
        this.fillBookType("modify");
        this.fillTable(new Student());
    }

    /**
     * 学生信息删除事件处理
     * @param evt
     */
    private void studentDeleteActionPerformed(ActionEvent evt) throws IOException {
        String id=idTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if(n==0){
            SqlSession sqlSession = DbUtil.getSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            int deleteNum = mapper.deleteById(Integer.parseInt(id));
            try{
                if(deleteNum==1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new Student());
                }else{
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "删除失败");
            }
        }
    }

    /**
     * 学生信息修改事件处理
     * @param evt
     */
    private void studentUpdateActionPerformed(ActionEvent evt) throws IOException {
        String id=this.idTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }

        String sname=this.nameTxt.getText();
        String sn=this.snTxt.getText();
        String dept=this.deptTxt.getText();
        String address=this.addressTxt.getText();

        if(StringUtil.isEmpty(sname)){
            JOptionPane.showMessageDialog(null, "学生信息名称不能为空！");
            return;
        }

        if(StringUtil.isEmpty(sn)){
            JOptionPane.showMessageDialog(null, "学生信息作者不能为空！");
            return;
        }

        if(StringUtil.isEmpty(dept)){
            JOptionPane.showMessageDialog(null, "学生信息价格不能为空！");
            return;
        }

        String sex="";
        if(manJrb.isSelected()){
            sex="男";
        }else if(femaleJrb.isSelected()){
            sex="女";
        }

        SchoolClass schoolClassTmp=(SchoolClass) schoolClassJcb.getSelectedItem();
        int scId=schoolClassTmp.getId();

        Student student=new Student(Integer.parseInt(id),  sname, sn, sex, dept,  scId,  address);
        SqlSession sqlSession = DbUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int addNum = mapper.updateById(student.getName(), student.getSn(), student.getSex(), student.getDept(),  student.getClassId(),student.getAddress(),student.getId());
        Connection con=null;
        try{
            if(addNum==1){
                JOptionPane.showMessageDialog(null, "学生信息修改成功！");
                resetValue();
                this.fillTable(new Student());
            }else{
                JOptionPane.showMessageDialog(null, "学生信息修改失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生信息修改失败！");
        }
    }

    /**
     * 重置表单
     */
    private void resetValue(){
        this.idTxt.setText("");
        this.nameTxt.setText("");
        this.snTxt.setText("");
        this.deptTxt.setText("");
        this.manJrb.setSelected(true);
        this.addressTxt.setText("");
        if(this.schoolClassJcb.getItemCount()>0){
            this.schoolClassJcb.setSelectedIndex(0);
        }
    }

    /**
     * 表格行点击事件处理
     * @param met
     */
    private void studentTableMousePressed(MouseEvent met) {
        int row=this.studentTable.getSelectedRow();
        this.idTxt.setText((String)studentTable.getValueAt(row, 0));
        this.nameTxt.setText((String)studentTable.getValueAt(row, 1));
        this.snTxt.setText((String)studentTable.getValueAt(row, 2));
        String sex=(String)studentTable.getValueAt(row, 3);
        if("男".equals(sex)){
            this.manJrb.setSelected(true);
        }else if("女".equals(sex)){
            this.femaleJrb.setSelected(true);
        }
        this.deptTxt.setText(studentTable.getValueAt(row, 4)+"");
        this.addressTxt.setText((String)studentTable.getValueAt(row, 5));
        String className=(String)this.studentTable.getValueAt(row, 6);
        int n=this.schoolClassJcb.getItemCount();
        for(int i=0;i<n;i++){
            SchoolClass item=(SchoolClass)this.schoolClassJcb.getItemAt(i);
            if(item.getClassName().equals(className)){
                this.schoolClassJcb.setSelectedIndex(i);
            }
        }
    }

    /**
     * 学生信息查询事件处理
     * @param
     */
    private void bookSearchActionPerformed(ActionEvent evt) throws IOException {
        String name=this.s_nameTxt.getText();
        String sn=this.s_snTxt.getText();
        SchoolClass schoolClass=(SchoolClass) this.s_schoolClassJcb.getSelectedItem();
        int id=schoolClass.getId();
        Student student=new Student(null,name,sn,null,null,id,null);
        this.fillTable(student);
    }


    private void fillBookType(String type) throws IOException {
        Connection con=null;
        SchoolClass schoolClass=null;
        SqlSession sqlSession = DbUtil.getSqlSession();
        SchoolClassMapper mapper = sqlSession.getMapper(SchoolClassMapper.class);
        List<SchoolClass> classes = mapper.selectAll();
        try{
            if("search".equals(type)){
                schoolClass=new SchoolClass();
                schoolClass.setClassName("请选择...");
                schoolClass.setId(-1);
                this.s_schoolClassJcb.addItem(schoolClass);
            }
            classes.forEach(clazz->{
                if("search".equals(type)){
                    this.s_schoolClassJcb.addItem(clazz);
                }else if("modify".equals(type)){
                    this.schoolClassJcb.addItem(clazz);
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void fillTable(Student student) throws IOException {
        SqlSession sqlSession = DbUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById1(14);
        DefaultTableModel dtm=(DefaultTableModel) studentTable.getModel();
        dtm.setRowCount(0); // 设置成0行
        try{
            students.forEach(student1 -> {
                Vector v=new Vector();
                v.add(student1.getId());
                v.add(student1.getName());
                v.add(student1.getSn());
                v.add(student1.getSex());
                v.add(student1.getDept());
                v.add(student1.getAddress());
                v.add(student1.getClass().getName());
                dtm.addRow(v);
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
