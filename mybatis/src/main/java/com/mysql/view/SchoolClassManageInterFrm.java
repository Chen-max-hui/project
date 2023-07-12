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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import com.mysql.pojo.SchoolClass;
import com.mysql.util.DbUtil;
import com.mysql.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

public class SchoolClassManageInterFrm extends JInternalFrame {
    private JTable schoolClassTable;
    private JTextArea classDescTxt ;

    private DbUtil dbUtil=new DbUtil();
    private JTextField s_classNameTxt;
    private JTextField idTxt;
    private JTextField classNameTxt;



    /**
     * Create the frame.
     */
    public SchoolClassManageInterFrm() throws IOException {
        setClosable(true);
        setIconifiable(true);
        setTitle("班级信息管理界面");
        setBounds(100, 100, 507, 481);

        JScrollPane scrollPane = new JScrollPane();

        JLabel label = new JLabel("班级信息名称");

        s_classNameTxt = new JTextField();
        s_classNameTxt.setColumns(10);

        JButton button = new JButton("\u67E5\u8BE2");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    schoolClassSearchActionPerformed(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button.setIcon(new ImageIcon(SchoolClassManageInterFrm.class.getResource("/images/search.png")));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(42)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addComponent(label)
                                                .addGap(18)
                                                .addComponent(s_classNameTxt, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(button))
                                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                                .addGap(48))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(33)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(s_classNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addGap(39)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        JLabel lblNewLabel = new JLabel("\u7F16\u53F7\uFF1A");

        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);

        JLabel label_1 = new JLabel("班级信息名称");

        classNameTxt = new JTextField();
        classNameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u63CF\u8FF0\uFF1A");

        classDescTxt = new JTextArea();
        classDescTxt.setLineWrap(true);
        classDescTxt.setWrapStyleWord(true);
        JButton btnNewButton = new JButton("\u4FEE\u6539");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    schoolClassUpdateActionEvent(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNewButton.setIcon(new ImageIcon(SchoolClassManageInterFrm.class.getResource("/images/modify.png")));

        JButton btnNewButton_1 = new JButton("\u5220\u9664");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    schoolClassDeleteActionEvent(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(SchoolClassManageInterFrm.class.getResource("/images/delete.png")));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(31)
                                                .addComponent(label_1)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(classNameTxt, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(lblNewLabel_1)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(classDescTxt))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(btnNewButton)
                                                .addGap(26)
                                                .addComponent(btnNewButton_1)))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1)
                                        .addComponent(classNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(classDescTxt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnNewButton_1))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        schoolClassTable = new JTable();
        schoolClassTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                schoolClassTableMousePressed(e);
            }
        });
        schoolClassTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u7F16\u53F7", "班级信息名称", "班级信息描述"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        schoolClassTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        schoolClassTable.getColumnModel().getColumn(2).setPreferredWidth(123);
        scrollPane.setViewportView(schoolClassTable);
        getContentPane().setLayout(groupLayout);

        this.fillTable(new SchoolClass());

        // 设置文本域边框
        classDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
    }

    /**
     * 班级信息删除事件处理
     * @param
     */
    private void schoolClassDeleteActionEvent(ActionEvent evt) throws IOException {
        String id=idTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if(n==0){
            Connection con=null;
            SqlSession sqlSession = DbUtil.getSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            boolean flag = mapper.isExistById(Integer.parseInt(id));
            try{
                if(flag){
                    JOptionPane.showMessageDialog(null, "当前班级信息下有图书，不能删除");
                    return;
                }
                int deleteNum=mapper.deleteById(Integer.parseInt(id));
                if(deleteNum==1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new SchoolClass());
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
     * 班级信息修改事件处理
     * @param evt
     */
    private void schoolClassUpdateActionEvent(ActionEvent evt) throws IOException {
        String id=idTxt.getText();
        String className=classNameTxt.getText();
        String classDesc=classDescTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        if(StringUtil.isEmpty(className)){
            JOptionPane.showMessageDialog(null, "班级信息名称不能为空");
            return;
        }
        SqlSession sqlSession = DbUtil.getSqlSession();
        SchoolClassMapper mapper = sqlSession.getMapper(SchoolClassMapper.class);
        int modifyNum = mapper.updateInfo(Integer.parseInt(id), className, classDesc);
        try{
            if(modifyNum==1){
                JOptionPane.showMessageDialog(null, "修改成功");
                this.resetValue();
                this.fillTable(new SchoolClass());
            }else{
                JOptionPane.showMessageDialog(null, "修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    /**
     * 表格行点击事件处理
     * @param
     */
    private void schoolClassTableMousePressed(MouseEvent evt) {
        int row=schoolClassTable.getSelectedRow();
        idTxt.setText((String)schoolClassTable.getValueAt(row, 0));
        classNameTxt.setText((String)schoolClassTable.getValueAt(row, 1));
        classDescTxt.setText((String)schoolClassTable.getValueAt(row, 2));
    }

    /**
     * 班级信息搜索事件处理
     * @param evt
     */
    private void schoolClassSearchActionPerformed(ActionEvent evt) throws IOException {
        String s_className=this.s_classNameTxt.getText();
        SchoolClass schoolClass=new SchoolClass();
        schoolClass.setClassName(s_className);
        this.fillTable(schoolClass);
    }

    /**
     * 初始化表格
     * @param schoolClass
     */
    private void fillTable(SchoolClass schoolClass) throws IOException {
        DefaultTableModel dtm=(DefaultTableModel) schoolClassTable.getModel();
        dtm.setRowCount(0); // 设置成0行
        Connection con=null;
        SqlSession sqlSession = DbUtil.getSqlSession();
        SchoolClassMapper mapper = sqlSession.getMapper(SchoolClassMapper.class);
        List<SchoolClass> classes = mapper.selectAll();
        classes.forEach(clazz->{
            Vector v=new Vector();
            v.add(clazz.getId());
            v.add(clazz.getClassName());
            v.add(clazz.getClassDesc());
            dtm.addRow(v);
        });
    }

    /**
     * 重置表单
     */
    private void resetValue(){
        this.idTxt.setText("");
        this.classNameTxt.setText("");
        this.classDescTxt.setText("");
    }
}
