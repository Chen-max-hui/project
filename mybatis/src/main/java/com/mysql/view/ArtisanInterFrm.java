package com.mysql.view;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class ArtisanInterFrm extends JInternalFrame {
    public ArtisanInterFrm() {
        //getContentPane().setBackground(Color.RED);
        setIconifiable(true);
        setClosable(true);
        setTitle("关于系统");
        setBounds(100, 100, 450, 300);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(ArtisanInterFrm.class.getResource("/images/artisan.png")));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(98)
                                .addComponent(lblNewLabel)
                                .addContainerGap(126, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(48)
                                .addComponent(lblNewLabel)
                                .addContainerGap(149, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
    }
}
