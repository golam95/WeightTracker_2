package com.tracker.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import com.tracker.java.DAOImp.DBConnection;
import com.tracker.java.DAOImp.userInfoDAOImp;

public class MainFrame extends JFrame {
	JButton enroll, signup, exit;
	Border thickBorder = new LineBorder(Color.decode("#44619D"), 10);
	Font textsize = new Font("Arial", Font.BOLD, 14);
	JLabel lbl_image = new JLabel(new ImageIcon("images/font.png"));
	userInfoDAOImp userinfo = new userInfoDAOImp();

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public static final Dimension PREFERREDSIZE = new Dimension(1000, 600);
	static JFrame frame = new MainFrame();
	private static String get_gender = null;

	public MainFrame() {
		super("Weight Tracker");
		this.create_layout();

	}

	public static void createAndShowGUI() {
		try {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setMinimumSize(PREFERREDSIZE);
			frame.setPreferredSize(PREFERREDSIZE);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createGui" + ex.toString());
		}

	}

	public static void disposeall() {
		frame.setVisible(false);
	}

	public void create_layout() {
		try {
			lbl_image.setBackground(Color.red);
			lbl_image.setBounds(10, 10, 973, 552);
			enroll = new JButton("Enroll Now");
			signup = new JButton("Enter Detail");
			exit = new JButton("Exit");
			enroll.setBackground(Color.decode("#0D620F"));
			enroll.setForeground(Color.white);
			signup.setBackground(Color.decode("#0D620F"));
			exit.setBackground(Color.decode("#0D620F"));
			signup.setForeground(Color.white);
			exit.setForeground(Color.white);
			enroll.setFont(textsize);
			signup.setFont(textsize);
			exit.setFont(textsize);
			enroll.setBounds(260, 355, 150, 53);
			signup.setBounds(430, 355, 150, 53);
			exit.setBounds(610, 355, 150, 53);
			this.add(exit);
			this.add(enroll);
			this.add(signup);
			this.add(lbl_image);
			enroll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String input = JOptionPane.showInputDialog(null, "Please Enter Your Name");

						if (!input.equals("")) {
							if (userinfo.checkUser(input) == true) {
								Connection con = null;
								try {
									con = DBConnection.getConnecttion();
								} catch (Exception e) {

								}
								try {
									Statement stmt = con.createStatement();
									String query = "select * from user where user_name ='" + input + "'";
									ResultSet rs = stmt.executeQuery(query);
									while (rs.next()) {
										get_gender = rs.getString(3).trim();
									}
									rs = null;
									con.close();
								}

								catch (Exception ex) {
									ex.printStackTrace();
								}

								JOptionPane.showMessageDialog(null, "Welcome " + input);
								dispose();
								TrackerDashboard dashboard = new TrackerDashboard(input, get_gender);
							} else {
								JOptionPane.showMessageDialog(null, "Sorry Not Match " + input);

							}

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Field Must Not Empty");
					}

				}

			});
			signup.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					@SuppressWarnings("unused")
					userDetails userdetails = new userDetails();
				}
			});
			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					@SuppressWarnings("unused")
					int i = JOptionPane.showConfirmDialog(null, "Do you want to Exit?");
					if (i == 0) {
						dispose();
					}
				}
			});

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createLayout" + ex.toString());
		}
	}

}
