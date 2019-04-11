package com.tracker.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.tracker.java.DAOImp.DBConnection;
import com.tracker.java.DAOImp.exerciseDAOImp;
import com.tracker.java.DAOImp.foodInfoDAOImp;
import com.tracker.java.DAOImp.userInfoDAOImp;
import com.tracker.java.DAOImp.waistDAOImp;
import com.tracker.java.DAOImp.weightDAOImp;
import com.tracker.java.model.exerciseInfo;
import com.tracker.java.model.foodInfo;
import com.tracker.java.model.userInfo;
import com.tracker.java.model.waistInfo;
import com.tracker.java.model.weightInfo;
import com.tracker.java.util.SettingMenu;

public class TrackerDashboard extends JFrame
		implements MouseListener, ActionListener, ListSelectionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	userInfoDAOImp userinfo = new userInfoDAOImp();
	foodInfoDAOImp foodinfo = new foodInfoDAOImp();
	weightDAOImp weigtdao = new weightDAOImp();
	waistDAOImp waistdio = new waistDAOImp();
	exerciseDAOImp exercisedio = new exerciseDAOImp();
	// For menu Item
	JPanel pan_option = new JPanel();
	JMenuBar menubar = new JMenuBar();
	JMenu menuFile = new JMenu("File");
	JMenu menuHelp = new JMenu("Help");
	private JMenuItem item_addhelp = new JMenuItem();
	private JMenuItem item_addInsruction = new JMenuItem();
	private JMenuItem item_exit = new JMenuItem();
	SettingMenu settings = new SettingMenu();
	// For menu Item
	private JPanel pan_left, pan_top, pan_middle;
	private JPanel pan_1, pan_2, pan_3;
	private JPanel pan_maincontainer = new JPanel();
	// for user panel
	private JPanel pan_Usercontainer = new JPanel();
	private JPanel pan_usertable = new JPanel();
	private JButton btn_deleteuserdetails = new JButton("Delete");
	private JLabel lbl_pleasesearch = new JLabel("Search");
	private JTextField txt_pleasesearch = new JTextField();
	private JTable tbl_userDetails = new JTable();
	private JScrollPane pane_userDetails = new JScrollPane();
	JButton btn_back = new JButton("Back");
	JTextField txt_userid = new JTextField();
	Border thickBorder_user = new LineBorder(Color.ORANGE, 8);
	Border thickBorder_table = new LineBorder(Color.white, 8);
	// for user panel
	// for to see the daily activity
	private JPanel pan_dailychange = new JPanel();
	private JList<String> list_showdailyactivity = new JList<String>();
	JButton btn_backdailychange = new JButton("Back");
	JLabel lbl_Intake = new JLabel("Intake(cal)");
	JLabel lbl_Burn = new JLabel("Burn(cal)");
	JLabel lbl_Difference = new JLabel("Difference(cal)");
	JLabel lbl_date = new JLabel("Date");
	// for to seee the daily activity
	String current_date = new SimpleDateFormat("hh:mm:ss a").format(new Date());
	private JButton btn_1 = new JButton();
	private JButton btn_2 = new JButton("Activity");
	private JButton btn_3 = new JButton("User Details");
	private JButton btn_4 = new JButton("Weight");
	private JLabel lbl_title = new JLabel("Weight Tracker");
	Font textsize = new Font("Arial", Font.BOLD, 26);
	Font textsize1 = new Font("Arial", Font.BOLD, 13);
	Font textsize2 = new Font("Arial", Font.BOLD, 23);
	Font textsize3 = new Font("Arial", Font.BOLD, 30);
	Font textsize5 = new Font("Arial", Font.BOLD, 17);
	Font textsize_small = new Font("Arial", Font.BOLD, 14);
	private JLabel lbl_retrivename = new JLabel();
	private JLabel lbl_retriveimage = new JLabel(new ImageIcon("images/option.png"));
	private static String str_title_no1 = "STATISTICS";
	private static String str_title_no2 = "Activity Details";
	private static String str_title_no3 = "USER DETAILS";
	private static String str_title_no4 = "WEIGHT CHANGE";
	private JLabel lbl_statices = new JLabel();
	Border thickBorder = new LineBorder(Color.decode("#1A3A2D"), 6);
	// logout and edit profile

	JLabel lbl_logout = new JLabel("logout");
	JLabel lbl_editprofile = new JLabel("Profile");

	private JDialog frm_profile;
	private JTextField txt_Id, txt_name, txt_age, txt_gender;
	private JButton btn_userInfoupdate, btn_userInfocancel;
	JComboBox<String> selectday = new JComboBox<String>();
	JScrollPane scroll_panel_list1 = new JScrollPane();
	JScrollPane scroll_panel_list2 = new JScrollPane();
	JList<String> list_selectdayview = new JList<String>();
	String food_1 = "Food Id", food_2 = "Food(gram)", food_3 = "Date", food_4 = "UserName", food_5 = "Food(cal)",
			food_6 = "Food Name";
	String ex_1 = "Exercise Id", ex_2 = "Exercise Name", ex_3 = "Exercise(Cal)", ex_4 = "Date", ex_5 = "UserName";
	String waist_1 = "Waist Id", waist_2 = "Waist Morning", waist_3 = "Waist Evening", waist_4 = "Waist Average",
			waist_5 = "Date", waist_6 = "UserName";
	String wei_1 = "Weight Id", wei_2 = "Weight Morning", wei_3 = "Weight Evening", wei_4 = "Weight Average",
			wei_5 = "Date", wei_6 = "UserName";
	JLabel lbl_userview_1 = new JLabel(food_1);
	JLabel lbl_userview_2 = new JLabel(food_6);
	JLabel lbl_userview_3 = new JLabel(food_2);
	JLabel lbl_userview_4 = new JLabel(food_3);
	JLabel lbl_userview_5 = new JLabel(food_4);
	JLabel lbl_userview_6 = new JLabel(food_5);

	JLabel lbl_selectday = new JLabel("Please Select");
	JTextField txt_userview_1 = new JTextField();
	JTextField txt_userview_2 = new JTextField();
	JTextField txt_userview_3 = new JTextField();
	JTextField txt_userview_4 = new JTextField();
	JTextField txt_userview_5 = new JTextField();
	JTextField txt_userview_6 = new JTextField();
	JButton btn_userview_update = new JButton("Update");
	JButton btn_userview_delete = new JButton("Delete");
	JButton btn_userview_close = new JButton("Close");
	private String[] str_array = { "Food", "Exercise", "Weight", "Waist" };
	private static String getuserName = null;
	private static String getuserGender = null;
	// retrive the counter date

	JLabel txt_getday = new JLabel();
	JButton btn_showreport = new JButton("Report");

	JButton btn_exit = new JButton("Exit");

	JButton lbl_titlepanelbutton = new JButton();
	JButton lbl_titlepanelbutton_1 = new JButton();
	JButton lbl_titlepanelbutton_2 = new JButton();

	// update button
	JButton btn_weightview_update = new JButton("Update");
	JButton btn_weightview_delete = new JButton("Delete");
	JButton btn_waistview_update = new JButton("Update");
	JButton btn_waistview_delete = new JButton("Delete");
	JButton btn_exerciseview_update = new JButton("Update");
	JButton btn_exerciseview_delete = new JButton("Delete");

	// update button

	// logout and edit profile
	public TrackerDashboard(String userName, String userGender) {
		getuserName = userName;
		getuserGender = userGender;
		this.createDashboard();
	}

	public void createDashboard() {
		try {
			this.setSize(1364, 716);
			this.setLayout(null);
			this.setResizable(false);
			this.setJMenuBar(CreateJMenuBar());
			lbl_title.setBounds(20, 10, 200, 40);
			lbl_title.setForeground(Color.WHITE);
			lbl_title.setFont(textsize);
			Border thickBorder = new LineBorder(Color.decode("#394B58"), 12);
			btn_1.setBounds(40, 170, 125, 105);
			btn_2.setBounds(40, 290, 125, 105);
			btn_3.setBounds(40, 410, 125, 105);
			btn_4.setBounds(40, 530, 125, 105);

			btn_1.setBorder(thickBorder);
			btn_2.setBorder(thickBorder);
			btn_3.setBorder(thickBorder);
			btn_4.setBorder(thickBorder);
			// 250800
			btn_1.setBackground(Color.decode("#1A3A2D"));
			btn_2.setBackground(Color.decode("#1A3A2D"));
			btn_3.setBackground(Color.decode("#1A3A2D"));
			btn_4.setBackground(Color.decode("#1A3A2D"));
			btn_4.setForeground(Color.white);

			btn_weightview_update.setBackground(Color.decode("#1A3A2D"));
			btn_weightview_delete.setBackground(Color.decode("#1A3A2D"));
			btn_waistview_update.setBackground(Color.decode("#1A3A2D"));
			btn_waistview_delete.setBackground(Color.decode("#1A3A2D"));
			btn_exerciseview_update.setBackground(Color.decode("#1A3A2D"));
			btn_exerciseview_delete.setBackground(Color.decode("#1A3A2D"));
			btn_back.setBackground(Color.decode("#1A3A2D"));
			btn_backdailychange.setBackground(Color.decode("#1A3A2D"));
			btn_weightview_update.setForeground(Color.white);
			btn_weightview_delete.setForeground(Color.white);
			btn_waistview_update.setForeground(Color.white);
			btn_waistview_delete.setForeground(Color.white);
			btn_exerciseview_update.setForeground(Color.white);
			btn_exerciseview_delete.setForeground(Color.white);
			btn_back.setForeground(Color.white);
			btn_backdailychange.setForeground(Color.white);
			btn_userview_update.setBackground(Color.decode("#1A3A2D"));
			btn_userview_delete.setBackground(Color.decode("#1A3A2D"));
			btn_userview_close.setBackground(Color.decode("#1A3A2D"));
			btn_userview_update.setForeground(Color.white);
			btn_userview_delete.setForeground(Color.white);
			btn_userview_close.setForeground(Color.white);
			btn_deleteuserdetails.setBackground(Color.decode("#1A3A2D"));
			btn_deleteuserdetails.setForeground(Color.white);
			btn_showreport.setBounds(630, 520, 150, 105);
			btn_showreport.setFont(textsize1);
			btn_exit.setFont(textsize1);
			btn_showreport.setForeground(Color.white);
			btn_exit.setForeground(Color.white);
			btn_exit.setBounds(850, 520, 150, 105);
			btn_showreport.setBackground(Color.decode("#1A3A2D"));
			btn_exit.setBackground(Color.decode("#1A3A2D"));
			btn_showreport.setForeground(Color.WHITE);
			btn_exit.setForeground(Color.WHITE);
			btn_showreport.setBorder(thickBorder);
			btn_exit.setBorder(thickBorder);
			Action action = new Action();
			btn_showreport.addActionListener(this);
			btn_exit.addActionListener(action);
			pan_left = new JPanel();
			pan_left.setLayout(null);
			pan_left.setBounds(0, 0, 250, 800);
			pan_left.setBackground(Color.decode("#1A3A2D"));
			pan_top = new JPanel();
			pan_top.setLayout(null);
			pan_top.setBounds(0, 0, 1364, 70);
			pan_top.setBackground(Color.decode("#1A3A2D"));
			pan_middle = new JPanel();
			pan_middle.setLayout(null);
			pan_middle.setBounds(250, 70, 1364, 70);
			pan_middle.setBackground(Color.decode("#0D620F"));
			lbl_statices.setBounds(454, 15, 350, 30);
			lbl_statices.setForeground(Color.white);
			lbl_statices.setFont(textsize3);
			lbl_statices.setText(str_title_no1);
			pan_middle.add(lbl_statices);
			lbl_retrivename.setBounds(1170, 15, 100, 30);
			lbl_retrivename.setFont(textsize1);
			lbl_retrivename.setForeground(Color.WHITE);
			// retrive the usernmae hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
			lbl_retrivename.setText(getuserName);
			// retrive the usernmae hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh

			JLabel txt_dayname = new JLabel("day");
			JLabel txt_day = new JLabel();
			txt_day.setText(current_date);
			fill_totaldaycount(lbl_retrivename.getText());

			txt_getday.setBounds(1040, 15, 50, 30);
			txt_dayname.setBounds(1055, 15, 30, 30);
			txt_day.setBounds(715, 15, 300, 30);

			txt_getday.setForeground(Color.RED);
			txt_dayname.setForeground(Color.RED);
			txt_day.setForeground(Color.ORANGE);

			txt_getday.setFont(textsize5);
			txt_dayname.setFont(textsize5);
			txt_day.setFont(textsize);
			pan_top.add(txt_day);
			pan_top.add(txt_getday);
			pan_top.add(txt_dayname);

			lbl_retriveimage.setBounds(1205, 15, 80, 30);
			lbl_retriveimage.addMouseListener(this);
			// Option menu
			pan_option.setLayout(null);
			lbl_editprofile.setBounds(14, 5, 70, 20);
			lbl_logout.setBounds(14, 30, 70, 20);
			lbl_editprofile.setForeground(Color.decode("#488FCF"));
			lbl_logout.setFont(textsize1);
			lbl_editprofile.setFont(textsize1);
			lbl_editprofile.addMouseListener(this);
			lbl_logout.addMouseListener(this);

			pan_option.add(lbl_editprofile);
			pan_option.add(lbl_logout);
			pan_option.setBounds(1260, 15, 80, 60);
			pan_option.setBackground(Color.WHITE);
			pan_option.setVisible(false);
			pan_top.add(pan_option);

			// OPtion menu
			pan_top.add(lbl_retriveimage);
			pan_top.add(lbl_retrivename);
			// adding the border
			btn_1.setBorder(thickBorder);
			btn_2.setBorder(thickBorder);
			btn_3.setBorder(thickBorder);
			btn_4.setBorder(thickBorder);

			// adding the border

			// properties the left button size

			btn_1.setText("Add");

			btn_1.setFont(textsize1);

			btn_1.setForeground(Color.white);
			btn_2.setFont(textsize1);
			btn_3.setFont(textsize1);
			btn_4.setFont(textsize1);

			btn_2.setForeground(Color.white);
			btn_3.setForeground(Color.white);
			btn_1.addActionListener(this);
			btn_2.addActionListener(this);
			btn_3.addActionListener(this);
			btn_4.addActionListener(this);

			// properties the left button size
			pan_left.add(btn_1);
			pan_left.add(btn_2);
			pan_left.add(btn_3);
			pan_left.add(btn_4);
			pan_left.add(lbl_title);
			pan_1 = new JPanel();
			pan_1.setBounds(370, 200, 230, 250);
			pan_1.setBackground(Color.decode("#0D620F"));
			pan_1.setLayout(null);
			pan_2 = new JPanel();
			pan_2.setBounds(680, 200, 230, 250);
			pan_2.setBackground(Color.decode("#0D620F"));
			pan_2.setLayout(null);
			pan_3 = new JPanel();
			pan_3.setBounds(990, 200, 230, 250);
			pan_3.setBackground(Color.decode("#0D620F"));
			pan_3.setLayout(null);
			if (getuserName.equals("Tracker")) {
				btn_1.setEnabled(false);
				btn_2.setEnabled(false);
				btn_3.setEnabled(true);
				btn_4.setEnabled(false);
				btn_showreport.setEnabled(false);
				btn_exit.setEnabled(true);
				txt_dayname.setVisible(false);
				txt_getday.setVisible(false);
			} else {
				btn_1.setEnabled(true);
				btn_2.setEnabled(true);
				btn_3.setEnabled(false);
				btn_4.setEnabled(true);
				btn_showreport.setEnabled(true);
				btn_exit.setEnabled(true);
				txt_dayname.setVisible(true);
				txt_getday.setVisible(true);
			}

			JLabel lbl_titlepanel = new JLabel("Total User");
			JLabel lbl_titlepanelimg = new JLabel(new ImageIcon("images/run.png"));
			lbl_titlepanel.setBounds(50, 10, 180, 40);
			lbl_titlepanelimg.setBounds(50, 50, 100, 100);
			lbl_titlepanelbutton.setBounds(0, 200, 230, 50);
			lbl_titlepanel.setFont(textsize2);
			lbl_titlepanelbutton.setFont(textsize2);
			lbl_titlepanel.setForeground(Color.white);
			lbl_titlepanelbutton.setForeground(Color.black);
			lbl_titlepanelbutton.setBackground(Color.ORANGE);

			pan_1.add(lbl_titlepanel);
			pan_1.add(lbl_titlepanelimg);
			pan_1.add(lbl_titlepanelbutton);
			lbl_titlepanelbutton.setBorder(thickBorder_user);
			lbl_titlepanelbutton_1.setBorder(thickBorder_user);
			lbl_titlepanelbutton_2.setBorder(thickBorder_user);
			fill_totaluser();

			// maincontainer_false();
			JLabel lbl_titlepanel_1 = new JLabel("Total Male");
			JLabel lbl_titlepanelimg_1 = new JLabel(new ImageIcon("images/clock.png"));

			lbl_titlepanel_1.setBounds(50, 10, 180, 40);
			lbl_titlepanelimg_1.setBounds(50, 50, 100, 100);
			lbl_titlepanelbutton_1.setBounds(0, 200, 230, 50);
			lbl_titlepanel_1.setFont(textsize2);

			lbl_titlepanelbutton_1.setFont(textsize2);
			lbl_titlepanel_1.setForeground(Color.white);
			lbl_titlepanelbutton_1.setForeground(Color.black);
			lbl_titlepanelbutton_1.setBackground(Color.ORANGE);

			pan_2.add(lbl_titlepanel_1);
			pan_2.add(lbl_titlepanelimg_1);
			pan_2.add(lbl_titlepanelbutton_1);
			fill_totalman();

			JLabel lbl_titlepanel_2 = new JLabel("Total Female");
			JLabel lbl_titlepanelimg_2 = new JLabel(new ImageIcon("images/weight.png"));

			lbl_titlepanel_2.setBounds(50, 10, 180, 40);
			lbl_titlepanelimg_2.setBounds(50, 50, 100, 100);
			lbl_titlepanelbutton_2.setBounds(0, 200, 230, 50);
			lbl_titlepanel_2.setFont(textsize2);
			lbl_titlepanelbutton_2.setFont(textsize2);
			lbl_titlepanel_2.setForeground(Color.white);
			lbl_titlepanelbutton_2.setForeground(Color.black);
			lbl_titlepanelbutton_2.setBackground(Color.ORANGE);

			pan_3.add(lbl_titlepanel_2);
			pan_3.add(lbl_titlepanelimg_2);
			pan_3.add(lbl_titlepanelbutton_2);
			fill_totalwoman();

			// label all the information

			// mini panel add

			// view user details that i can declare hereffffffffffffffffffffff

			pan_maincontainer.setLayout(null);
			// pan_maincontainer.setBackground(Color.decode("#1A3A2D"));
			pan_maincontainer.setBounds(258, 147, 1093, 511);

			// user pan container

			pan_Usercontainer.setLayout(null);
			// pan_Usercontainer.setBackground(Color.gray);
			pan_Usercontainer.setBounds(258, 147, 1093, 511);

			lbl_pleasesearch.setBounds(290, 100, 100, 30);
			txt_pleasesearch.setBounds(390, 100, 250, 30);
			txt_pleasesearch.addKeyListener(this);
			btn_deleteuserdetails.setBounds(650, 100, 80, 30);
			btn_back.setBounds(20, 20, 80, 30);
			btn_back.addActionListener(this);
			btn_deleteuserdetails.addActionListener(this);
			btn_back.setFont(textsize1);
			txt_userid.setBounds(50, 100, 80, 30);
			txt_userid.setFont(textsize1);
			lbl_pleasesearch.setFont(textsize1);
			txt_pleasesearch.setFont(textsize1);
			btn_deleteuserdetails.setFont(textsize1);

			tbl_userDetails.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
					new String[] { "Id", "Name", "Gender", "Age", "Date" }));
			tbl_userDetails.addMouseListener(action);
			tbl_userDetails.setFont(textsize1);
			pane_userDetails.setViewportView(tbl_userDetails);

			pan_usertable.setLayout(new GridLayout(0, 1));
			pan_usertable.setBounds(120, 200, 870, 270);
			pan_usertable.setBorder(thickBorder_table);

			pan_Usercontainer.add(lbl_pleasesearch);
			pan_Usercontainer.add(txt_pleasesearch);
			pan_Usercontainer.add(btn_deleteuserdetails);
			pan_Usercontainer.add(btn_back);
			pan_Usercontainer.add(txt_userid);
			pan_Usercontainer.add(pan_usertable);
			pan_usertable.add(pane_userDetails);
			pan_Usercontainer.setVisible(false);
			maincontainer_false();

			pan_dailychange.setLayout(null);
			pan_dailychange.setBounds(258, 147, 1093, 511);

			btn_backdailychange.setBounds(20, 20, 80, 30);
			btn_backdailychange.addActionListener(this);
			btn_backdailychange.setFont(textsize1);
			//
			scroll_panel_list2.setBounds(170, 150, 750, 300);

			lbl_Intake.setBounds(170, 110, 200, 30);
			lbl_Burn.setBounds(390, 110, 200, 30);
			lbl_Difference.setBounds(595, 110, 200, 30);
			lbl_date.setBounds(800, 110, 200, 30);
			lbl_Intake.setFont(textsize5);
			lbl_Burn.setFont(textsize5);
			lbl_Difference.setFont(textsize5);
			lbl_date.setFont(textsize5);
			list_showdailyactivity.setFont(textsize1);
			scroll_panel_list2.setViewportView(list_showdailyactivity);
			//
			pan_dailychange.add(btn_backdailychange);
			pan_dailychange.add(scroll_panel_list2);
			pan_dailychange.add(lbl_Intake);
			pan_dailychange.add(lbl_Burn);
			pan_dailychange.add(lbl_Difference);
			pan_dailychange.add(lbl_date);

			pan_dailychange.setVisible(false);

			// daily activity change

			btn_userview_close.addActionListener(this);

			//
			// //
			lbl_userview_1.setForeground(Color.black);
			lbl_userview_2.setForeground(Color.black);
			lbl_userview_3.setForeground(Color.black);
			lbl_userview_4.setForeground(Color.black);
			lbl_userview_5.setForeground(Color.black);
			lbl_userview_6.setForeground(Color.black);
			//
			// //
			//
			lbl_selectday.setForeground(Color.WHITE);
			//
			list_selectdayview.setFont(textsize_small);
			list_selectdayview.addListSelectionListener(this);
			// //
			//
			// //
			//
			// // view user details that i can declare here
			//
			lbl_selectday.setBounds(300, 20, 150, 30);
			lbl_selectday.setFont(textsize_small);
			selectday.setBounds(430, 20, 350, 30);
			selectday.setFont(textsize1);
			selectday.addActionListener(this);
			scroll_panel_list1.setViewportView(list_selectdayview);
			scroll_panel_list1.setBounds(100, 120, 300, 350);

			for (int i = 0; i < str_array.length; i++) {
				selectday.addItem(str_array[i]);
			}
			btn_userview_update.setBounds(700, 437, 80, 30);
			btn_userview_delete.setBounds(800, 437, 80, 30);
			btn_weightview_update.setBounds(700, 437, 80, 30);
			btn_weightview_delete.setBounds(800, 437, 80, 30);
			btn_waistview_update.setBounds(700, 437, 80, 30);
			btn_waistview_delete.setBounds(800, 437, 80, 30);
			btn_exerciseview_update.setBounds(700, 437, 80, 30);
			btn_exerciseview_delete.setBounds(800, 437, 80, 30);
			btn_userview_close.setBounds(900, 437, 80, 30);
			btn_userview_update.setFont(textsize_small);
			btn_userview_delete.setFont(textsize_small);
			btn_userview_close.setFont(textsize_small);
			btn_weightview_update.setFont(textsize_small);
			btn_weightview_delete.setFont(textsize_small);
			btn_waistview_update.setFont(textsize_small);
			btn_waistview_delete.setFont(textsize_small);
			btn_exerciseview_update.setFont(textsize_small);
			btn_exerciseview_delete.setFont(textsize_small);
			// // Label
			lbl_userview_1.setBounds(560, 120, 150, 30);
			lbl_userview_2.setBounds(560, 170, 150, 30);
			lbl_userview_3.setBounds(560, 220, 150, 30);
			lbl_userview_4.setBounds(560, 270, 150, 30);
			lbl_userview_5.setBounds(560, 320, 150, 30);
			lbl_userview_6.setBounds(560, 370, 150, 30);
			//
			txt_userview_1.setBounds(700, 120, 280, 30);
			txt_userview_2.setBounds(700, 170, 280, 30);
			txt_userview_3.setBounds(700, 220, 280, 30);
			txt_userview_4.setBounds(700, 270, 280, 30);
			txt_userview_5.setBounds(700, 320, 280, 30);
			txt_userview_6.setBounds(700, 370, 280, 30);
			txt_userview_1.setEditable(false);
			txt_userview_4.setEditable(false);
			txt_userview_5.setEditable(false);
			// // Label
			lbl_userview_1.setFont(textsize1);
			lbl_userview_2.setFont(textsize1);
			lbl_userview_3.setFont(textsize1);
			lbl_userview_4.setFont(textsize1);
			lbl_userview_5.setFont(textsize1);
			lbl_userview_6.setFont(textsize1);
			txt_userview_1.setFont(textsize1);
			txt_userview_2.setFont(textsize1);
			txt_userview_3.setFont(textsize1);
			txt_userview_4.setFont(textsize1);
			txt_userview_5.setFont(textsize1);
			txt_userview_6.setFont(textsize1);
			//
			txt_userid.setVisible(false);
			//
			pan_maincontainer.add(lbl_selectday);
			pan_maincontainer.add(selectday);
			pan_maincontainer.add(scroll_panel_list1);
			pan_maincontainer.add(lbl_userview_1);
			pan_maincontainer.add(lbl_userview_2);
			pan_maincontainer.add(lbl_userview_3);
			pan_maincontainer.add(lbl_userview_4);
			pan_maincontainer.add(lbl_userview_5);
			pan_maincontainer.add(lbl_userview_6);
			pan_maincontainer.add(txt_userview_1);
			pan_maincontainer.add(txt_userview_2);
			pan_maincontainer.add(txt_userview_3);
			pan_maincontainer.add(txt_userview_4);
			pan_maincontainer.add(txt_userview_5);
			pan_maincontainer.add(txt_userview_6);

			btn_weightview_update.addActionListener(action);
			btn_weightview_delete.addActionListener(action);
			btn_waistview_update.addActionListener(action);
			btn_waistview_delete.addActionListener(action);
			btn_exerciseview_update.addActionListener(action);
			btn_exerciseview_delete.addActionListener(action);
			btn_userview_update.addActionListener(action);
			btn_userview_delete.addActionListener(action);
			btn_weightview_update.setVisible(false);
			btn_weightview_delete.setVisible(false);
			btn_waistview_update.setVisible(false);
			btn_waistview_delete.setVisible(false);
			btn_exerciseview_update.setVisible(false);
			btn_exerciseview_delete.setVisible(false);
			// All button ActionListener
			pan_maincontainer.add(btn_weightview_update);
			pan_maincontainer.add(btn_weightview_delete);
			pan_maincontainer.add(btn_waistview_update);
			pan_maincontainer.add(btn_waistview_delete);
			pan_maincontainer.add(btn_exerciseview_update);
			pan_maincontainer.add(btn_exerciseview_delete);
			pan_maincontainer.add(btn_userview_update);
			pan_maincontainer.add(btn_userview_delete);
			pan_maincontainer.add(btn_userview_close);
			this.add(pan_1);
			this.add(pan_2);
			this.add(pan_3);
			this.add(btn_showreport);
			this.add(btn_exit);
			this.add(pan_left);
			this.add(pan_top);
			this.add(pan_middle);
			this.add(pan_maincontainer);
			this.add(pan_Usercontainer);
			this.add(pan_dailychange);
			Show_userInfo();
			fill_dailyActivity(getuserName);
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showInternalMessageDialog(null, "Error createDashbord" + ex.toString());

		}
	}

	protected JMenuBar CreateJMenuBar() {
		try {
			menuFile.addSeparator();
			menuFile.add(settings.setJMenuItem(item_exit, "Exit", "images/Exit.png"));
			item_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
			item_exit.setMnemonic((int) 'F');
			item_exit.addActionListener(this);
			menuHelp.add(settings.setJMenuItem(item_addhelp, "About", "images/about.png"));
			item_addhelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
			item_addhelp.addActionListener(this);
			menuHelp.addSeparator();
			menuHelp.add(settings.setJMenuItem(item_addInsruction, "Instruction", "images/help.png"));
			item_addInsruction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
			item_addInsruction.addActionListener(this);
			menuHelp.setMnemonic((int) 'H');
			menubar.add(settings.setJMenu(menuFile));
			menubar.add(settings.setJMenu(menuHelp));
		} catch (Exception ex2) {
			JOptionPane.showMessageDialog(null, "Error the Manubar" + ex2.toString());

		}
		return menubar;

	}

	protected void editInformation() {
		try {
			frm_profile = new JDialog();
			frm_profile.setTitle("Edit Information");
			frm_profile.setSize(500, 250);
			frm_profile.setLocationRelativeTo(null);
			frm_profile.setResizable(false);
			frm_profile.setLayout(null);
			JLabel lbl_Id = new JLabel("Id");
			JLabel lbl_name = new JLabel("Name");
			JLabel lbl_age = new JLabel("Age");
			JLabel lbl_gender = new JLabel("Gender");
			lbl_Id.setForeground(Color.WHITE);
			lbl_name.setForeground(Color.WHITE);
			lbl_age.setForeground(Color.WHITE);
			lbl_gender.setForeground(Color.WHITE);
			txt_Id = new JTextField();
			txt_name = new JTextField();
			txt_age = new JTextField();
			txt_gender = new JTextField();
			btn_userInfoupdate = new JButton("Update");
			btn_userInfocancel = new JButton("Cancel");
			btn_userInfoupdate.setBackground(Color.decode("#1A3A2D"));
			btn_userInfocancel.setBackground(Color.decode("#1A3A2D"));
			btn_userInfoupdate.addActionListener(this);
			btn_userInfocancel.addActionListener(this);
			txt_Id.setEditable(false);
			txt_name.setEditable(false);
			lbl_Id.setBounds(20, 20, 100, 30);
			lbl_name.setBounds(20, 70, 100, 30);
			lbl_age.setBounds(20, 120, 100, 30);
			lbl_gender.setBounds(20, 170, 100, 30);
			txt_Id.setBounds(120, 20, 200, 30);
			txt_name.setBounds(120, 70, 200, 30);
			txt_age.setBounds(120, 120, 200, 30);
			txt_gender.setBounds(120, 170, 200, 30);
			btn_userInfoupdate.setBounds(360, 20, 110, 30);
			btn_userInfocancel.setBounds(360, 70, 110, 30);
			txt_Id.setFont(textsize_small);
			txt_name.setFont(textsize_small);
			txt_age.setFont(textsize_small);
			txt_gender.setFont(textsize_small);
			lbl_Id.setFont(textsize_small);
			lbl_name.setFont(textsize_small);
			lbl_age.setFont(textsize_small);
			lbl_gender.setFont(textsize_small);
			btn_userInfoupdate.setFont(textsize_small);
			btn_userInfocancel.setFont(textsize_small);
			frm_profile.add(lbl_Id);
			frm_profile.add(txt_Id);
			frm_profile.add(lbl_name);
			frm_profile.add(txt_name);
			frm_profile.add(lbl_age);
			frm_profile.add(txt_age);
			frm_profile.add(lbl_gender);
			frm_profile.add(txt_gender);
			frm_profile.add(btn_userInfoupdate);
			frm_profile.add(btn_userInfocancel);
			frm_profile.getContentPane().setBackground(Color.decode("#0D620F"));
			frm_profile.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error editInformation" + ex.toString());

		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source.equals(lbl_logout)) {
			this.setVisible(false);
			dispose();
			MainFrame.createAndShowGUI();
			pan_option.setVisible(false);
		} else if (source.equals(lbl_editprofile)) {
			editInformation();
			pan_option.setVisible(false);
			String getInfo = lbl_retrivename.getText().toString();
			fill_form(getInfo);
		} else if (source.equals(lbl_retriveimage)) {
			pan_option.setVisible(true);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent object) {
		Object source = object.getSource();
		if (source.equals(btn_1)) {
			userMealDetails details = new userMealDetails(getuserName, getuserGender, this);
		} else if (source.equals(btn_2)) {
			lbl_statices.setText(str_title_no2);
			setfalse();
			pan_maincontainer.setVisible(true);
			pan_Usercontainer.setVisible(false);
			pan_dailychange.setVisible(false);

		} else if (source.equals(btn_3)) {
			lbl_statices.setText(str_title_no3);
			setfalse();
			pan_Usercontainer.setVisible(true);
			pan_maincontainer.setVisible(false);
			pan_dailychange.setVisible(false);
		} else if (source.equals(btn_4)) {
			lbl_statices.setText(str_title_no4);
			setfalse();
			pan_dailychange.setVisible(true);
			pan_Usercontainer.setVisible(false);
			pan_maincontainer.setVisible(false);
			fill_dailyActivity(getuserName);
		} else if (source.equals(btn_backdailychange)) {
			lbl_statices.setText(str_title_no1);
			addtitle_activity1();
			clear_allupdate();
			set_true();
			pan_dailychange.setVisible(false);
		} else if (source.equals(btn_userview_close)) {
			lbl_statices.setText(str_title_no1);
			addtitle_activity1();
			set_true();
			pan_maincontainer.setVisible(false);
		} else if (source.equals(btn_userInfoupdate)) {
			String id = txt_Id.getText();
			String name = txt_name.getText().toString();
			String age = txt_age.getText();
			String gender = txt_gender.getText().toString();
			if (id.equals("") || name.equals("") || age.equals("") || gender.equals("")) {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String day = (dateFormat.format(date));
				try {
					userinfo.updateUser(new userInfo(Integer.parseInt(id), name, gender, Integer.parseInt(age), day));
					JOptionPane.showMessageDialog(null, "Update Succefully!!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} else if (source.equals(btn_userInfocancel)) {
			frm_profile.setVisible(false);
		} else if (source.equals(item_addhelp)) {
			help dlg = new help(this);
		} else if (source.equals(selectday)) {
			if (selectday.getSelectedItem().equals("Food") == true) {
				active_buttonFood();
				addtitle_activity1();
				clear_allupdate();
				String getid = lbl_retrivename.getText();
				String query = "SELECT * FROM food WHERE username='" + getid + "'";
				fill_listFood(query);
			} else if (selectday.getSelectedItem().equals("Exercise") == true) {
				addtitle_activity2();
				active_buttonExercise();
				clear_allupdate();
				String getid = lbl_retrivename.getText();
				String query = "SELECT * FROM exercise WHERE exercise_username='" + getid + "'";
				fill_listFood(query);
			} else if (selectday.getSelectedItem().equals("Weight") == true) {
				addtitle_activity4();
				active_buttonWeight();
				clear_allupdate();
				String getid = lbl_retrivename.getText();
				String query = "SELECT * FROM weight WHERE username='" + getid + "'";
				fill_listphysical(query);
			} else if (selectday.getSelectedItem().equals("Waist") == true) {
				addtitle_activity3();
				active_buttonWaist();
				clear_allupdate();
				String getid = lbl_retrivename.getText();
				String query = "SELECT * FROM waist WHERE username='" + getid + "'";
				fill_listphysical(query);
			}
		} else if (source.equals(btn_back)) {
			lbl_statices.setText(str_title_no1);
			addtitle_activity1();
			clear_allupdate();
			set_true();
			pan_Usercontainer.setVisible(false);
			fill_totalwoman();
			fill_totalman();
		} else if (source.equals(btn_deleteuserdetails)) {
			String user_id = txt_userid.getText();
			if (user_id.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Select Row!!!" + JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					userinfo.delete_User2(user_id);
					userinfo.delete_all_1(user_id);
					userinfo.delete_all_2(user_id);
					userinfo.delete_all_3(user_id);
					userinfo.delete_all_4(user_id);
					userinfo.delete_all_5(user_id);
					userinfo.delete_all_6(user_id);
					userinfo.delete_all_7(user_id);
					DefaultTableModel model = (DefaultTableModel) tbl_userDetails.getModel();
					model.setRowCount(0);
					Show_userInfo();
					txt_userid.setText("");
					JOptionPane.showMessageDialog(null, "Remove Succefully!!!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} else if (source.equals(btn_showreport)) {
			String getusername = lbl_retrivename.getText();
			trackerReport report = new trackerReport(getusername, this);
		} else if (source.equals(item_addInsruction)) {
			String str = "At First You Must Need To Signup The Following Name ==== (Tracker)";
			JOptionPane.showMessageDialog(null, str);
		} else if (source.equals(item_exit)) {
			dispose();
		}

	}

	public void setfalse() {
		pan_1.setVisible(false);
		pan_2.setVisible(false);
		pan_3.setVisible(false);
		btn_showreport.setVisible(false);
		btn_exit.setVisible(false);

	}

	public void set_true() {
		pan_1.setVisible(true);
		pan_2.setVisible(true);
		pan_3.setVisible(true);
		btn_showreport.setVisible(true);
		btn_exit.setVisible(true);

	}

	public void maincontainer_true() {
		pan_maincontainer.setVisible(true);
	}

	public void maincontainer_false() {
		pan_maincontainer.setVisible(false);
	}

	@SuppressWarnings("resource")
	public void fill_form(String name) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();

		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM user WHERE user_name='" + name + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String userId = rs.getString(1).trim();
				String userName = rs.getString(2).trim();
				String userGender = rs.getString(3).trim();
				String userAge = rs.getString(4).trim();
				txt_Id.setText(userId);
				txt_name.setText(userName);
				txt_age.setText(userAge);
				txt_gender.setText(userGender);
				rs = null;

			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	// CRUD exercise waist weight food======================================

	public void fill_listFood(String str) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = str;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString(4).trim();
				m.addElement(date);
			}
			list_selectdayview.setModel(m);
			rs.close();
			con.close();
		}

		catch (Exception ex) {

		}

	}

	public void fill_listphysical(String str) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = str;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString(5).trim();
				m.addElement(date);
			}
			list_selectdayview.setModel(m);
			con.close();
		}

		catch (Exception ex) {

		}

	}

	// ========================== CRUD ===========================

	// view daily activity for ther individual user

	public void fill_dailyActivity(String username) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM calorie_burn WHERE calorie_username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String str_1 = rs.getString(2).trim();
				String str_2 = rs.getString(3).trim();
				String str_3 = rs.getString(4).trim();
				String str_4 = rs.getString(5).trim();
				m.addElement("(" + str_1 + ")----------------------------------------------(" + str_2
						+ ")----------------------------------------------(" + str_3
						+ ")--------------------------------------------(" + str_4 + ")");
			}
			list_showdailyactivity.setModel(m);
			con.close();
		}

		catch (Exception ex) {

		}

	}

	// view daily activity for the individual user

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Food") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from food where food_date='" + goahead1 + "' And username='"
					+ lbl_retrivename.getText() + "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Weight") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from weight where weight_date='" + goahead1 + "' And username='"
					+ lbl_retrivename.getText() + "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Waist") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from waist where waist_date='" + goahead1 + "' And username='"
					+ lbl_retrivename.getText() + "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Exercise") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from exercise where exercise_date='" + goahead1 + "' And exercise_username='"
					+ lbl_retrivename.getText() + "'";
			fill_listExercise(query);
		}

	}

	public void fill_Food(String getquery) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = getquery;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id_1 = rs.getString(1).trim();
				String id_2 = rs.getString(2).trim();
				String id_3 = rs.getString(3).trim();
				String id_4 = rs.getString(4).trim();
				String id_5 = rs.getString(5).trim();
				String id_6 = rs.getString(6).trim();
				txt_userview_1.setText(id_1);
				txt_userview_2.setText(id_2);
				txt_userview_3.setText(id_3);
				txt_userview_4.setText(id_4);
				txt_userview_5.setText(id_5);
				txt_userview_6.setText(id_6);
			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	public void fill_listExercise(String getquery) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = getquery;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id_1 = rs.getString(1).trim();
				String id_2 = rs.getString(2).trim();
				String id_3 = rs.getString(3).trim();
				String id_4 = rs.getString(4).trim();
				String id_5 = rs.getString(5).trim();
				txt_userview_1.setText(id_1);
				txt_userview_2.setText(id_2);
				txt_userview_3.setText(id_3);
				txt_userview_4.setText(id_4);
				txt_userview_5.setText(id_5);

			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	// ========Table code======//
	public ArrayList<userInfo> receiveorderinfo() {
		ArrayList<userInfo> usersList = new ArrayList<userInfo>();
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		String query = "SELECT * FROM  `user`";
		Statement st;
		ResultSet rs;

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			userInfo user;
			while (rs.next()) {
				user = new userInfo(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_gender"),
						rs.getInt("user_age"), rs.getString("uset_date"));
				usersList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

	// Display Data In JTable

	public void Show_userInfo() {
		ArrayList<userInfo> list = receiveorderinfo();
		DefaultTableModel model = (DefaultTableModel) tbl_userDetails.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getUser_id();
			row[1] = list.get(i).getUser_Name();
			row[2] = list.get(i).getUser_Gener();
			row[3] = list.get(i).getUser_Age();
			row[4] = list.get(i).getUser_date();
			model.addRow(row);
		}
	}

	// ===============================

	public ArrayList<userInfo> List_wa_Order1(String search) {// warehouse
		// emp
		ArrayList<userInfo> usersList = new ArrayList<userInfo>();
		Statement st;
		ResultSet rs;

		try {
			Connection con = null;
			try {
				con = DBConnection.getConnecttion();
			} catch (Exception e) {

			}
			st = con.createStatement();
			String searchQuery = "SELECT * FROM `user` WHERE CONCAT(`user_id`, `user_name`, `user_gender`, `user_age`,`uset_date`) LIKE '%"
					+ search + "%'";
			rs = st.executeQuery(searchQuery);
			userInfo user;
			while (rs.next()) {
				user = new userInfo(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_gender"),
						rs.getInt("user_age"), rs.getString("uset_date"));
				usersList.add(user);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return usersList;
	}

	public void find_customerorder1() {
		ArrayList<userInfo> list = List_wa_Order1(txt_pleasesearch.getText());// asdjflasjdf;lja;lkdsfjl;k
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] { "Id", "Name", "Gender", "Age", "Date" });
		Object[] row = new Object[5];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getUser_id();
			row[1] = list.get(i).getUser_Name();
			row[2] = list.get(i).getUser_Gener();
			row[3] = list.get(i).getUser_Age();
			row[4] = list.get(i).getUser_date();
			model.addRow(row);
		}
		tbl_userDetails.setModel(model);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String advancesearch = txt_pleasesearch.getText();
		find_customerorder1();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// count the day

	public void fill_totaldaycount(String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();

		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT COUNT(food_id)  from food WHERE username='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString("COUNT(food_id)");
				txt_getday.setText(count);
			}
			con.close();
		}

		catch (Exception ex) {

		}

	}

	// first

	public void fill_totaluser() {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();

		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT COUNT(user_id)  from user";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString("COUNT(user_id)");
				lbl_titlepanelbutton.setText(count);
			}
			con.close();
		}

		catch (Exception ex) {

		}

	}

	// second

	public void fill_totalman() {

		int count_male = 0;
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();

		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString(3);
				if (count.contentEquals("Male") == true) {
					count_male++;
				}

				lbl_titlepanelbutton_1.setText(Integer.toString(count_male));
			}
			con.close();
		}

		catch (Exception ex) {

		}

	}
	// third

	public void fill_totalwoman() {
		int count_female = 0;
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();

		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM  user";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString(3);
				if (count.contentEquals("Female") == true) {
					count_female++;

				}
				lbl_titlepanelbutton_2.setText(Integer.toString(count_female));

			}
			con.close();
		}

		catch (Exception ex) {

		}

	}

	class Action implements ActionListener, MouseListener {

		public Action() {

		}

		@Override
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source.equals(btn_exit)) {
				int i = JOptionPane.showConfirmDialog(null, "Do you want to Exit!!!" + JOptionPane.ERROR);
				if (i == 0) {
					dispose();
				}
			} else if (source.equals(btn_weightview_update)) {
				String str_1 = txt_userview_1.getText();
				String str_2 = txt_userview_2.getText();
				String str_3 = txt_userview_3.getText();
				String str_4 = txt_userview_4.getText();
				String str_5 = txt_userview_5.getText();
				String str_6 = txt_userview_6.getText();
				if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
						|| str_6.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						weigtdao.update_weight(
								new weightInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			} else if (source.equals(btn_weightview_delete)) {
				String str_1 = txt_userview_1.getText();
				if (str_1.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						weigtdao.delete_weight(Integer.parseInt(str_1));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (source.equals(btn_waistview_update)) {
				String str_1 = txt_userview_1.getText();
				String str_2 = txt_userview_2.getText();
				String str_3 = txt_userview_3.getText();
				String str_4 = txt_userview_4.getText();
				String str_5 = txt_userview_5.getText();
				String str_6 = txt_userview_6.getText();
				if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
						|| str_6.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						waistdio.update_waist(
								new waistInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			} else if (source.equals(btn_waistview_delete)) {
				String str_1 = txt_userview_1.getText();
				if (str_1.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						waistdio.delete_Waist(Integer.parseInt(str_1));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			} else if (source.equals(btn_exerciseview_update)) {

				String str_1 = txt_userview_1.getText();
				String str_2 = txt_userview_2.getText();
				String str_3 = txt_userview_3.getText();
				String str_4 = txt_userview_4.getText();
				String str_5 = txt_userview_5.getText();
				if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						exercisedio
								.update_Exercise(new exerciseInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5));

						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			} else if (source.equals(btn_exerciseview_delete)) {
				String str_1 = txt_userview_1.getText();
				if (str_1.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						exercisedio.delete_Exercise(Integer.parseInt(str_1));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (source.equals(btn_userview_update)) {
				String str_1 = txt_userview_1.getText();
				String str_2 = txt_userview_2.getText();
				String str_3 = txt_userview_3.getText();
				String str_4 = txt_userview_4.getText();
				String str_5 = txt_userview_5.getText();
				String str_6 = txt_userview_6.getText();
				if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
						|| str_6.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						foodinfo.update_food(new foodInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (source.equals(btn_userview_delete)) {
				String str_1 = txt_userview_1.getText();
				if (str_1.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					try {
						foodinfo.delete_food(Integer.parseInt(str_1));
						clear_allupdate();
						JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int j = tbl_userDetails.getSelectedRow();
			TableModel modelq = tbl_userDetails.getModel();
			txt_userid.setText(modelq.getValueAt(j, 1).toString());

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void clear_allupdate() {
		txt_userview_1.setText("");
		txt_userview_2.setText("");
		txt_userview_3.setText("");
		txt_userview_4.setText("");
		txt_userview_5.setText("");
		txt_userview_6.setText("");
	}

	public void active_buttonFood() {
		btn_userview_update.setVisible(true);
		btn_userview_delete.setVisible(true);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		txt_userview_6.setVisible(true);
	}

	public void active_buttonExercise() {
		btn_exerciseview_update.setVisible(true);
		btn_exerciseview_delete.setVisible(true);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		txt_userview_6.setVisible(false);
		lbl_userview_6.setVisible(false);
	}

	public void active_buttonWeight() {
		btn_weightview_update.setVisible(true);
		btn_weightview_delete.setVisible(true);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		txt_userview_6.setVisible(true);

	}

	public void active_buttonWaist() {
		btn_waistview_update.setVisible(true);
		btn_waistview_delete.setVisible(true);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		txt_userview_6.setVisible(true);

	}

	public void addtitle_activity1() {
		lbl_userview_1.setText(food_1);
		lbl_userview_2.setText(food_6);
		lbl_userview_3.setText(food_2);
		lbl_userview_4.setText(food_3);
		lbl_userview_5.setText(food_4);
		lbl_userview_6.setText(food_5);
	}

	public void addtitle_activity2() {
		lbl_userview_1.setText(ex_1);
		lbl_userview_2.setText(ex_2);
		lbl_userview_3.setText(ex_3);
		lbl_userview_4.setText(ex_4);
		lbl_userview_5.setText(ex_5);
	}

	public void addtitle_activity3() {
		lbl_userview_1.setText(waist_1);
		lbl_userview_2.setText(waist_2);
		lbl_userview_3.setText(waist_3);
		lbl_userview_4.setText(waist_4);
		lbl_userview_5.setText(waist_5);
		lbl_userview_6.setText(waist_6);
	}

	public void addtitle_activity4() {
		lbl_userview_1.setText(wei_1);
		lbl_userview_2.setText(wei_2);
		lbl_userview_3.setText(wei_3);
		lbl_userview_4.setText(wei_4);
		lbl_userview_5.setText(wei_5);
		lbl_userview_6.setText(wei_6);
	}

}
