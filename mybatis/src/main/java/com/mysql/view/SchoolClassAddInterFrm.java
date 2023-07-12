package com.mysql.view;

import com.mysql.mapper.SchoolClassMapper;
import com.mysql.pojo.SchoolClass;
import com.mysql.util.DbUtil;
import com.mysql.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
public class SchoolClassAddInterFrm extends JInternalFrame {
    private JTextField classNameTxt;
    private JTextArea classDescTxt;

    public SchoolClassAddInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("添加班级信息界面");
        setBounds(100, 100, 450, 300);

        JLabel lblNewLabel = new JLabel("班级信息名称");

        JLabel lblNewLabel_1 = new JLabel("班级信息描述");

        classNameTxt = new JTextField();
        classNameTxt.setColumns(10);

        classDescTxt = new JTextArea();
        classDescTxt.setLineWrap(true);
        classDescTxt.setWrapStyleWord(true);
        JButton btnNewButton = new JButton("\u6DFB\u52A0");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    bookTypeAddActionPerformed(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNewButton.setIcon(new ImageIcon(SchoolClassAddInterFrm.class.getResource("/images/add.png")));

        JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(SchoolClassAddInterFrm.class.getResource("/images/reset.png")));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(86)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(classNameTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel_1)
                                                        .addComponent(btnNewButton))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnNewButton_1)
                                                        .addComponent(classDescTxt))))
                                .addContainerGap(69, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(56)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(classNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(29)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(classDescTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnNewButton_1))
                                .addGap(28))
        );
        getContentPane().setLayout(groupLayout);

        // 设置文本域边框
        classDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));

    }

    private void bookTypeAddActionPerformed(ActionEvent evt) throws IOException {
        String className=this.classNameTxt.getText();
        String classDesc=this.classDescTxt.getText();
        if(StringUtil.isEmpty(className)){
            JOptionPane.showMessageDialog(null, "班级信息名称不能为空！");
            return;
        }
        SchoolClass schoolClass=new SchoolClass(null,className,classDesc);
        SqlSession sqlSession = DbUtil.getSqlSession();
        SchoolClassMapper mapper = sqlSession.getMapper(SchoolClassMapper.class);
        int n = mapper.insertDemo(schoolClass);
        sqlSession.commit();
        try{
            if(n==1){
                JOptionPane.showMessageDialog(null, "班级信息添加成功！");
                resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "班级信息添加失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "班级信息添加失败！");
        }
    }


    private void resetValueActionPerformed(ActionEvent evt) {
        this.resetValue();
    }

    private void resetValue(){
        this.classNameTxt.setText("");
        this.classDescTxt.setText("");
    }

}
