package com.tracker.java.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.tracker.java.DAOImp.userInfoDAOImp;
import com.tracker.java.model.userInfo;
import com.tracker.java.util.SettingMenu;

public class userDetails extends JDialog {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private JButton btn_save, btn_reset;
	private JTextField txt_Name, txt_Age;
	private JComboBox<String> cmb_gender;
	private String array_gender[] = { "Male", "Female" };
	Font textsize = new Font("Arial", Font.BOLD, 14);
	userInfoDAOImp userinfo = new userInfoDAOImp();
	String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	SettingMenu setting = new SettingMenu();

	public userDetails() {
		this.createDalog();
	}

	private void createDalog() {
		try {
			this.setSize(400, 230);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Name: ");
			JLabel lbl_Age = new JLabel("Age: ");
			JLabel lbl_Gender = new JLabel("Gender: ");
			txt_Name = new JTextField();
			txt_Age = new JTextField();
			cmb_gender = new JComboBox<String>();
			btn_save = new JButton("Save");
			btn_reset = new JButton("Cancel");
			for (int i = 0; i < array_gender.length; i++) {
				cmb_gender.addItem(array_gender[i]);
			}
			lbl_Name.setBounds(50, 20, 180, 30);
			lbl_Age.setBounds(50, 60, 180, 30);
			lbl_Gender.setBounds(50, 100, 180, 30);
			txt_Name.setBounds(130, 20, 210, 30);
			txt_Age.setBounds(130, 60, 210, 30);
			cmb_gender.setBounds(130, 100, 210, 30);
			btn_save.setBounds(130, 150, 100, 30);
			btn_reset.setBounds(240, 150, 100, 30);
			lbl_Name.setFont(textsize);
			lbl_Age.setFont(textsize);
			lbl_Gender.setFont(textsize);
			txt_Name.setFont(textsize);
			txt_Age.setFont(textsize);
			cmb_gender.setFont(textsize);
			btn_save.setFont(textsize);
			btn_reset.setFont(textsize);
			btn_save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String userName = txt_Name.getText().toString();
					String userAge = txt_Age.getText().toString();
					String userGender = cmb_gender.getSelectedItem().toString();
					if (userName.equals("") || userAge.equals("")) {
						JOptionPane.showMessageDialog(null, "Field Must Not Empty!");
					} else {
						try {
							if (userinfo.checkUser(userName) == true) {
								JOptionPane.showMessageDialog(null, "Sorry userName is Exist");
							} else {
								userinfo.addUser(
										new userInfo(0, userName, userGender, Integer.parseInt(userAge), current_date));
								txt_Name.setText("");
								txt_Age.setText("");
								JOptionPane.showMessageDialog(null, "Enroll Now!!!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				}

			});
			btn_reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}

			});
			this.add(lbl_Name);
			this.add(lbl_Age);
			this.add(lbl_Gender);
			this.add(txt_Name);
			this.add(txt_Age);
			this.add(cmb_gender);
			this.add(btn_save);
			this.add(btn_reset);
			this.getContentPane().setBackground(Color.decode("#0D620F"));
			setting.Numvalidator1(txt_Name);
			setting.Numvalidator(txt_Age);
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

}