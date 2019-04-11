package com.tracker.java.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.tracker.java.DAOImp.DBConnection;
import com.tracker.java.DAOImp.calorieDAOImp;
import com.tracker.java.DAOImp.exerciseDAOImp;
import com.tracker.java.DAOImp.foodInfoDAOImp;
import com.tracker.java.DAOImp.waistDAOImp;
import com.tracker.java.DAOImp.weightDAOImp;
import com.tracker.java.model.calorieburnInfo;
import com.tracker.java.model.exerciseInfo;
import com.tracker.java.model.foodInfo;
import com.tracker.java.model.storefoodInfo;
import com.tracker.java.model.waistInfo;
import com.tracker.java.model.weightInfo;
import com.tracker.java.model.weight_changeInfo;

public class userMealDetails extends JDialog implements ActionListener, KeyListener {
	waistDAOImp waistdio = new waistDAOImp();
	weightDAOImp weightdio = new weightDAOImp();
	foodInfoDAOImp fooddio = new foodInfoDAOImp();
	calorieDAOImp caloriedio = new calorieDAOImp();
	exerciseDAOImp exercisedio = new exerciseDAOImp();
	SimpleDateFormat f3 = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat f4 = new SimpleDateFormat("-MM-yyyy");
	String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
	Font textsize = new Font("Arial", Font.BOLD, 14);
	Font textsize1 = new Font("Arial", Font.BOLD, 15);
	private JButton btn_mealsave, btn_help, btn_mealsave_1;
	private JComboBox<String> select_Activity = new JComboBox<String>();
	private String str_activity[] = { "Active", "InActive", "Moderately Active" };
	private JTabbedPane tab_mealdetails;
	private JPanel pan_tab1, pan_tab2, pan_tab3, pan_tab4;
	private String[] user_Mealtype = { "Fat", "Alcohol", "Protein", "Carbohydrate" };
	private String[] user_Exercise = { "Runnig", "Push Up", "Bicycling", "Cooking", "Dancing", "Jumping", "Basketball",
			"Slow Walk", "swimming", "Jogging" };
	// for food txt_exercisename_1
	private JButton btn_addfood;
	// for waist
	private JTextField txt_morningwaist, txt_eveningwaist, txt_averagewaist, txt_retrive_waistvalue, txt_waistAverage;
	private JLabel cmb_kg1, cmb_kg2;
	private JButton btn_savewaist, btn_averagewaist;
	// for weight
	private JTextField txt_morningweight, txt_eveningweight, txt_averageweight, txt_retrive_weightvalue,
			txt_weightAverage;
	private JButton btn_saveweight, btn_averageweight;
	private JComboBox<String> cmb_selectmealtype;
	private static String getuserName = null;
	private static String getuserGender = null;
	// for food
	private JTextField txt_addfooddetails, txt_addfoodecal, txt_mealName, txt_calories, txt_exercise, txt_weight,
			txt_waist, txt_storevaluewithcalorie;
	// for Exercise
	private JComboBox<String> txt_exercisename_1;
	private JTextField txt_exercisevalue_1, txt_totalexercise_Name, txt_totalexercise_minutes;
	private JButton btn_addexercise;
	// date chooser
	private JDateChooser date_chooser;
	private JButton btn_cancel;
	private JTextField txt_retrive_Exercise, txt_retrive_Food;
	JTextField everyday_Activity = new JTextField();
	private static String average_weight = null;
	private static final long serialVersionUID = 1L;

	public userMealDetails(String getName, String getGender, JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "Generate Report", true);
		getuserName = getName;
		getuserGender = getGender;
		this.createuserDetails();
	}

	public void createuserDetails() {
		try {
			this.setSize(980, 360);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			this.setResizable(false);

			// panel decleration
			tab_mealdetails = new JTabbedPane();
			tab_mealdetails.setFont(textsize1);
			tab_mealdetails.setBackground(Color.BLACK);
			tab_mealdetails.setForeground(Color.white);
			tab_mealdetails.setBounds(0, 0, 750, 360);
			this.setBackground(Color.decode("#273746"));

			pan_tab1 = new JPanel();
			pan_tab2 = new JPanel();
			pan_tab3 = new JPanel();
			pan_tab4 = new JPanel();
			pan_tab3.setBackground(Color.decode("#0D620F"));
			pan_tab4.setBackground(Color.decode("#0D620F"));
			pan_tab1.setLayout(null);
			pan_tab2.setLayout(null);
			pan_tab3.setLayout(null);
			pan_tab4.setLayout(null);

			tab_mealdetails.add("Food", pan_tab1);
			tab_mealdetails.add("Exercise", pan_tab2);
			tab_mealdetails.add("Weight", pan_tab3);
			tab_mealdetails.add("Waist", pan_tab4);
			// panel decleration

			//
			// for morning

			JLabel lbl_mealName = new JLabel("Meal Name");
			JLabel lbl_calories = new JLabel("Amount(cal)");
			JLabel lbl_totalfood = new JLabel("FoodName");
			JLabel lbl_totalfoodkacal = new JLabel("Total(gram)");
			txt_addfooddetails = new JTextField();
			txt_addfoodecal = new JTextField("0");
			btn_addfood = new JButton("Add");
			txt_mealName = new JTextField();
			txt_calories = new JTextField();
			txt_exercise = new JTextField();
			txt_weight = new JTextField();
			txt_waist = new JTextField();
			txt_storevaluewithcalorie = new JTextField("0");

			txt_mealName.addKeyListener(this);

			lbl_mealName.setBounds(20, 20, 100, 30);
			lbl_calories.setBounds(20, 70, 100, 30);

			JLabel lbl_selectmealtype = new JLabel("Food Type");
			cmb_selectmealtype = new JComboBox<String>();

			lbl_selectmealtype.setBounds(400, 20, 100, 30);
			cmb_selectmealtype.setBounds(520, 20, 200, 30);

			for (int j = 0; j < user_Mealtype.length; j++) {
				cmb_selectmealtype.addItem(user_Mealtype[j]);
			}

			lbl_totalfood.setBounds(20, 190, 140, 30);
			lbl_totalfoodkacal.setBounds(400, 190, 140, 30);
			txt_addfooddetails.setBounds(150, 190, 200, 30);
			txt_addfoodecal.setBounds(520, 190, 200, 30);

			lbl_selectmealtype.setFont(textsize);
			cmb_selectmealtype.setFont(textsize);

			txt_mealName.setBounds(150, 20, 200, 30);
			txt_calories.setBounds(150, 70, 200, 30);
			txt_addfooddetails.setFont(textsize);
			txt_addfoodecal.setFont(textsize);
			lbl_totalfood.setFont(textsize);
			lbl_totalfoodkacal.setFont(textsize);
			txt_addfooddetails.setFont(textsize);
			txt_addfoodecal.setFont(textsize);

			btn_mealsave = new JButton("Save");
			btn_help = new JButton("Help");
			btn_addfood.setBounds(340, 135, 180, 30);
			JLabel lbl_storevaluewithcalorie = new JLabel("Total(cal)");
			lbl_storevaluewithcalorie.setFont(textsize);
			lbl_storevaluewithcalorie.setBounds(400, 70, 150, 30);
			txt_storevaluewithcalorie.setBounds(520, 70, 200, 30);

			btn_addfood.setFont(textsize);
			btn_addfood.setForeground(Color.white);
			btn_mealsave.setFont(textsize);
			btn_help.setFont(textsize);
			btn_mealsave.setBounds(340, 250, 180, 30);

			// ADD THE BUTTON FOR THAT OOOOOOOOOOOOOOOOOOOOOOO

			date_chooser = new JDateChooser();
			btn_cancel = new JButton("Cancel");

			for (int k = 0; k < str_activity.length; k++) {
				select_Activity.addItem(str_activity[k]);
			}
			date_chooser.setBounds(20, 30, 180, 30);
			select_Activity.setBounds(20, 80, 180, 30);
			btn_help.setBounds(20, 180, 180, 30);
			btn_cancel.setBounds(20, 230, 180, 30);

			btn_cancel.setBackground(Color.decode("#1A3A2D"));
			date_chooser.setBackground(Color.decode("#1A3A2D"));
			btn_cancel.setForeground(Color.WHITE);
			btn_help.setForeground(Color.WHITE);
			select_Activity.setFont(textsize);
			date_chooser.setFont(textsize);
			btn_cancel.setFont(textsize);
			btn_mealsave.addActionListener(this);
			btn_help.addActionListener(this);
			btn_addfood.addActionListener(this);
			btn_cancel.addActionListener(this);

			lbl_mealName.setFont(textsize);

			lbl_calories.setFont(textsize);
			txt_mealName.setFont(textsize);
			txt_exercise.setFont(textsize);
			txt_weight.setFont(textsize);
			txt_waist.setFont(textsize);
			txt_calories.setFont(textsize);
			txt_storevaluewithcalorie.setFont(textsize);
			//

			btn_mealsave.setBackground(Color.decode("#1A3A2D"));
			btn_addfood.setBackground(Color.decode("#1A3A2D"));
			btn_help.setBackground(Color.decode("#1A3A2D"));
			btn_mealsave.setForeground(Color.white);

			// ===================================== last text
			// ===============================================

			txt_retrive_Exercise = new JTextField();
			txt_retrive_Food = new JTextField();
			txt_retrive_Food.setBounds(570, 135, 150, 30);
			txt_retrive_Exercise.setBounds(170, 135, 150, 30);

			// =======================================================================================
			txt_retrive_Exercise.setVisible(false);
			txt_storevaluewithcalorie.setEditable(false);
			txt_addfoodecal.setEditable(false);

			pan_tab1.add(lbl_mealName);
			pan_tab1.add(lbl_calories);
			pan_tab1.add(txt_mealName);
			pan_tab1.add(txt_calories);
			pan_tab1.add(txt_addfooddetails);
			pan_tab1.add(txt_addfoodecal);
			pan_tab1.add(btn_addfood);
			pan_tab1.add(lbl_totalfood);
			pan_tab1.add(lbl_totalfoodkacal);
			pan_tab1.setBackground(Color.decode("#0D620F"));
			pan_tab1.add(btn_mealsave);
			pan_tab1.add(txt_storevaluewithcalorie);
			pan_tab1.add(lbl_selectmealtype);
			pan_tab1.add(lbl_storevaluewithcalorie);
			pan_tab1.add(cmb_selectmealtype);
			pan_tab1.add(txt_retrive_Exercise);

			//// for morning

			// for Exercise

			JLabel lbl_exercisename_1 = new JLabel("Exercise Name");
			JLabel lbl_exercisevalue_1 = new JLabel("Exercise(mints)");
			JLabel lbl_totalexercise_Name = new JLabel("Exercise Name");
			JLabel lbl_totalexercise_minutes = new JLabel("Exercise(cal)");
			txt_exercisename_1 = new JComboBox<String>();
			txt_exercisevalue_1 = new JTextField();
			txt_exercisevalue_1.addKeyListener(this);
		
			btn_addexercise = new JButton("Add");
			btn_mealsave_1 = new JButton("Save");
			btn_addexercise.setBackground(Color.decode("#1A3A2D"));
			btn_addexercise.setForeground(Color.white);
			txt_totalexercise_Name = new JTextField();
			txt_totalexercise_minutes = new JTextField("0");
			txt_totalexercise_minutes.setEditable(false);
			btn_addexercise.addActionListener(this);
			btn_mealsave_1.addActionListener(this);
			txt_retrive_Food.setVisible(false);
			for (int i = 0; i < user_Exercise.length; i++) {
				txt_exercisename_1.addItem(user_Exercise[i]);
			}
			lbl_exercisename_1.setBounds(20, 20, 140, 30);
			txt_exercisename_1.setBounds(150, 20, 200, 30);
			lbl_exercisevalue_1.setBounds(400, 20, 200, 30);
			txt_exercisevalue_1.setBounds(520, 20, 200, 30);
			lbl_totalexercise_Name.setBounds(20, 190, 140, 30);
			txt_totalexercise_Name.setBounds(150, 190, 200, 30);
			lbl_totalexercise_minutes.setBounds(400, 190, 140, 30);
			txt_totalexercise_minutes.setBounds(520, 190, 200, 30);

			btn_addexercise.setBounds(340, 135, 180, 30);
			btn_mealsave_1.setBounds(340, 250, 180, 30);
			btn_mealsave_1.setBackground(Color.decode("#1A3A2D"));
			btn_mealsave_1.setFont(textsize);
			lbl_exercisename_1.setFont(textsize);
			lbl_exercisevalue_1.setFont(textsize);
			txt_exercisename_1.setFont(textsize);
			txt_exercisevalue_1.setFont(textsize);
			//
			btn_mealsave_1.setBackground(Color.decode("#1A3A2D"));
			btn_mealsave_1.setForeground(Color.white);
			lbl_totalexercise_Name.setFont(textsize);
			txt_totalexercise_Name.setFont(textsize);
			lbl_totalexercise_minutes.setFont(textsize);
			txt_totalexercise_minutes.setFont(textsize);
			btn_mealsave_1.setFont(textsize);
			btn_addexercise.setFont(textsize);

			everyday_Activity.setBounds(10, 10, 50, 30);

			pan_tab2.add(lbl_exercisename_1);
			pan_tab2.add(lbl_exercisevalue_1);
			pan_tab2.add(txt_exercisename_1);
			pan_tab2.add(txt_exercisevalue_1);
			pan_tab2.setBackground(Color.decode("#0D620F"));
			pan_tab2.add(btn_mealsave_1);
			pan_tab2.add(lbl_totalexercise_Name);
			pan_tab2.add(txt_totalexercise_Name);
			pan_tab2.add(lbl_totalexercise_minutes);
			pan_tab2.add(txt_totalexercise_minutes);
			pan_tab2.add(btn_addexercise);
			pan_tab2.add(txt_retrive_Food);

			// for weight

			// 250

			JLabel lbl_morningweight = new JLabel("Weight(Morning)");
			JLabel lbl_eveningweight = new JLabel("Weight(Evening)");
			JLabel lbl_averageweight = new JLabel("Average");
			txt_morningweight = new JTextField();
			txt_eveningweight = new JTextField();
			txt_averageweight = new JTextField();
			txt_retrive_weightvalue = new JTextField();
			txt_retrive_weightvalue.setBounds(50, 200, 200, 30);
			txt_retrive_weightvalue.setFont(textsize);
			txt_weightAverage = new JTextField();
			txt_weightAverage.setBounds(50, 250, 200, 30);
			txt_weightAverage.setFont(textsize);

			btn_saveweight = new JButton("Save");

			btn_averageweight = new JButton("Average");
			btn_averageweight.setBounds(290, 195, 200, 30);
			btn_averageweight.setForeground(Color.white);
			btn_averageweight.setBackground(Color.decode("#1A3A2D"));
			btn_averageweight.setFont(textsize);
			btn_averageweight.addActionListener(this);
			JLabel lbl_kg1 = new JLabel("Kg");
			JLabel lbl_kg2 = new JLabel("Kg");
			JLabel lbl_kg3 = new JLabel("Kg");
			lbl_morningweight.setBounds(120, 30, 200, 30);
			lbl_eveningweight.setBounds(120, 85, 200, 30);
			lbl_averageweight.setBounds(120, 140, 200, 30);
			txt_morningweight.setBounds(290, 30, 200, 30);
			txt_eveningweight.setBounds(290, 85, 200, 30);
			txt_averageweight.setBounds(290, 140, 200, 30);
			lbl_kg1.setBounds(500, 30, 80, 30);
			lbl_kg2.setBounds(500, 85, 80, 30);
			lbl_kg3.setBounds(500, 140, 80, 30);
			btn_saveweight.setBounds(290, 245, 200, 30);
			btn_saveweight.setForeground(Color.white);
			btn_saveweight.setBackground(Color.decode("#1A3A2D"));
			btn_saveweight.addActionListener(this);
			lbl_morningweight.setFont(textsize);
			lbl_eveningweight.setFont(textsize);
			lbl_averageweight.setFont(textsize);
			txt_morningweight.setFont(textsize);
			txt_eveningweight.setFont(textsize);
			txt_averageweight.setFont(textsize);
			lbl_kg1.setFont(textsize);
			lbl_kg2.setFont(textsize);
			lbl_kg3.setFont(textsize);
			btn_saveweight.setFont(textsize);

			txt_retrive_weightvalue.setVisible(false);
			txt_weightAverage.setVisible(false);
			everyday_Activity.setVisible(false);
			txt_averageweight.setEditable(false);

			pan_tab3.add(lbl_morningweight);
			pan_tab3.add(lbl_eveningweight);
			pan_tab3.add(lbl_averageweight);
			pan_tab3.add(txt_morningweight);
			pan_tab3.add(txt_eveningweight);
			pan_tab3.add(txt_averageweight);
			pan_tab3.add(lbl_kg1);
			pan_tab3.add(lbl_kg2);
			pan_tab3.add(lbl_kg3);
			pan_tab3.add(btn_saveweight);
			pan_tab3.add(btn_averageweight);
			pan_tab3.add(txt_retrive_weightvalue);
			pan_tab3.add(txt_weightAverage);
			pan_tab3.add(everyday_Activity);
			
			
			
			// for waist =============================================================
			JLabel lbl_morningwaist = new JLabel("Waist(Morning)");
			JLabel lbl_eveningwaist = new JLabel("Waist(Evening)");
			JLabel lbl_averagewaist = new JLabel("Average(Inch)");

			lbl_morningwaist.setBounds(120, 30, 200, 30);
			lbl_eveningwaist.setBounds(120, 85, 200, 30);
			lbl_averagewaist.setBounds(120, 140, 200, 30);
			//
			// 250

			lbl_averagewaist.setFont(textsize);
			lbl_eveningwaist.setFont(textsize);
			lbl_morningwaist.setFont(textsize);
			txt_morningwaist = new JTextField();
			txt_morningwaist.setBounds(290, 30, 200, 30);
			txt_morningwaist.setFont(textsize);
			txt_eveningwaist = new JTextField();
			txt_eveningwaist.setBounds(290, 85, 200, 30);
			txt_eveningwaist.setFont(textsize);

			txt_averagewaist = new JTextField();
			txt_averagewaist.setBounds(290, 140, 200, 30);
			txt_averagewaist.setFont(textsize);
			// new textfield =======================================
			txt_retrive_waistvalue = new JTextField();
			txt_retrive_waistvalue.setBounds(50, 200, 200, 30);
			txt_retrive_waistvalue.setFont(textsize);

			txt_waistAverage = new JTextField();
			txt_waistAverage.setBounds(50, 250, 200, 30);
			txt_waistAverage.setFont(textsize);

			// new textfield for waist================================

			cmb_kg1 = new JLabel("Inch");
			cmb_kg1.setBounds(500, 30, 80, 30);
			cmb_kg1.setFont(textsize);
			cmb_kg2 = new JLabel("Inch");
			cmb_kg2.setBounds(500, 85, 80, 30);
			cmb_kg2.setFont(textsize);

			btn_averagewaist = new JButton("Average");
			btn_averagewaist.setBounds(290, 195, 200, 30);
			btn_averagewaist.setForeground(Color.white);
			btn_averagewaist.setBackground(Color.decode("#1A3A2D"));
			btn_averagewaist.setFont(textsize);
			btn_averagewaist.addActionListener(this);

			btn_savewaist = new JButton("Save");
			btn_savewaist.setBounds(290, 245, 200, 30);
			btn_savewaist.setForeground(Color.white);
			btn_savewaist.setBackground(Color.decode("#1A3A2D"));
			btn_savewaist.setFont(textsize);
			btn_savewaist.addActionListener(this);
			// 250
		

			txt_retrive_waistvalue.setVisible(false);
			txt_waistAverage.setVisible(false);
			txt_waistAverage.setEditable(false);

			pan_tab4.add(btn_averagewaist);
			pan_tab4.add(txt_morningwaist);
			pan_tab4.add(txt_eveningwaist);
			pan_tab4.add(txt_averagewaist);
			pan_tab4.add(txt_retrive_waistvalue);
			pan_tab4.add(txt_waistAverage);
			pan_tab4.add(cmb_kg1);
			pan_tab4.add(cmb_kg2);
			pan_tab4.add(btn_savewaist);
			pan_tab4.add(lbl_morningwaist);
			pan_tab4.add(lbl_eveningwaist);
			pan_tab4.add(lbl_averagewaist);
			
			
			
			this.add(tab_mealdetails);
			this.getContentPane().setBackground(Color.decode("#0D620F"));
			// this.setContentPane(tab_mealdetails);
			////
			//
			JPanel pan_layutright = new JPanel();
			pan_layutright.setLayout(null);
			pan_layutright.setBounds(750, 0, 225, 380);
			pan_layutright.setBackground(Color.decode("#1A3A2D"));
			pan_layutright.add(date_chooser);
			pan_layutright.add(btn_cancel);
			pan_layutright.add(btn_help);
			pan_layutright.add(select_Activity);
			this.add(pan_layutright);
			this.show();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error CreateuserDetils GUi" + ex.toString());

		}
	}

	@Override
	public void actionPerformed(ActionEvent object) {
		Object source = object.getSource();
		if (source.equals(btn_savewaist)) {
			String morning_waist = txt_morningwaist.getText();
			String evening_waist = txt_eveningwaist.getText();
			String average_waist = txt_averagewaist.getText();
			String date_name = null;
			String date_name_spilt = null;

			if (morning_waist.equals("") || evening_waist.equals("") || average_waist.equals("")
					|| date_chooser.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
			} else {
				date_name = f3.format(date_chooser.getDate());
				String getsubstring_Date = date_name.substring(0, 2);
				int dicrement_date = (Integer.parseInt(getsubstring_Date) - 1);
				date_name_spilt = Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
				try {
					if (waistdio.checkdate_waist(date_name_spilt, getuserName) == true) {
						if (waistdio.checkdate_waist(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null,
									"Sorry, Today Information is already taken by golam!!!");
							setclearwaist();
						} else {
							waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist, average_waist, date_name,
									getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							setclearwaist();
						}

					} else {
						int final_decrement = (dicrement_date - 1);
						String add_retrivedate = (Integer.toString(final_decrement)
								+ f4.format(date_chooser.getDate()));
						if ((waistdio.checkdate_waist(add_retrivedate, getuserName) == true)) {
							Retrive_waistDetails(add_retrivedate, getuserName);
							double total_average = ((Double.parseDouble(txt_retrive_waistvalue.getText())
									+ Double.parseDouble(average_waist)) / 2);
							txt_waistAverage.setText(Double.toString(total_average));
							waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist,
									txt_waistAverage.getText(), date_name_spilt, getuserName));
							waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist, average_waist, date_name,
									getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Postation!!!");
							setclearwaist();
						} else {
							if (waistdio.checkdate_waist(date_name, getuserName) == true) {
								JOptionPane.showMessageDialog(null,
										"Sorry, Today Information is already taken y last -------!!!");
								setclearwaist();
							} else {
								waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist, average_waist,
										date_name, getuserName));
								JOptionPane.showMessageDialog(null, "Add a New Position!!");
								setclearwaist();
							}
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} else if (source.equals(btn_saveweight)) {

			String morning_weight = txt_morningweight.getText();
			String evening_weight = txt_eveningweight.getText();
			average_weight = txt_averageweight.getText();
			String date_name = null;
			String date_name_spilt = null;

			if (morning_weight.equals("") || evening_weight.equals("") || average_weight.equals("")
					|| date_chooser.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
			} else {
				date_name = f3.format(date_chooser.getDate());
				String getsubstring_Date = date_name.substring(0, 2);
				int dicrement_date = (Integer.parseInt(getsubstring_Date) - 1);
				String check_length = Integer.toString(dicrement_date);

				if (check_length.length() == 1) {
					date_name_spilt = "0" + Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
				} else {
					date_name_spilt = Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
				}
				// need to write the code
				// check the date substring
				try {
					// check spilt date Name
					if (weightdio.checkdate_weight(date_name_spilt, getuserName) == true) {
						if (weightdio.checkdate_weight(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
							setclearweight();
						} else {

							Retrive_dailyActivity(date_name_spilt, getuserName);
							//
							double current_weight = Double.parseDouble(txt_averageweight.getText())
									- Double.parseDouble(everyday_Activity.getText());
							String convertcurrent_weight = Double.toString(current_weight);
							weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
									date_name, getuserName));
							weightdio.add_dailychange(
									new weight_changeInfo(0, convertcurrent_weight, date_name, getuserName));
							showEstimateInfo();
							setclearweight();
							

						}

					} else {
						int final_decrement = (dicrement_date - 1);
						String add_retrivedate = (Integer.toString(final_decrement)
								+ f4.format(date_chooser.getDate()));

						if ((weightdio.checkdate_weight(add_retrivedate, getuserName) == true)) {
							Retrive_weightDetails(add_retrivedate, getuserName);

							double total_average = ((Double.parseDouble(txt_retrive_weightvalue.getText())
									+ Double.parseDouble(average_weight)) / 2);
							txt_weightAverage.setText(Double.toString(total_average));
							// new code
							// Retrive_dailyActivity(date_name_spilt, getuserName);
							// match previous
							double current_weight = Double.parseDouble(txt_averageweight.getText())
									- Double.parseDouble(txt_weightAverage.getText());

							// not match previous
							double current_weight_1 = Double.parseDouble(txt_averageweight.getText())
									- Double.parseDouble(txt_retrive_weightvalue.getText());

							String convertcurrent_weight = Double.toString(current_weight);
							// new code
							weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight,
									txt_weightAverage.getText(), date_name_spilt, getuserName));
							weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
									date_name, getuserName));
							// currentlychange here
							weightdio.add_dailychange(new weight_changeInfo(0, Double.toString(current_weight_1),
									date_name_spilt, getuserName));

							weightdio.add_dailychange(new weight_changeInfo(0, Double.toString(current_weight_1),
									date_name, getuserName));
							showEstimateInfo();
							setclearweight();
							
						} else {
							if (weightdio.checkdate_weight(date_name, getuserName) == true) {
								JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken.!!!");
								setclearweight();
							} else {
								weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
										date_name, getuserName));
								weightdio.add_dailychange(new weight_changeInfo(0, "0.0", date_name, getuserName));
								showEstimateInfo();
								setclearweight();
								
							}
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} else if (source.equals(btn_mealsave)) {
			String get_foodetails = txt_addfooddetails.getText().toString();
			String get_caloriedetails = txt_addfoodecal.getText().toString();
			String date_name = null;
			if (get_foodetails.equals("") || get_caloriedetails.equals("") || date_chooser.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Field MustNot Empty!!!");
			} else {
				date_name = f3.format(date_chooser.getDate());
				if (txt_retrive_Exercise.getText().equals("")) {
					try {
						if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
							System.out.println(date_name + " " + getuserName);
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken ggggg!!!");
							set_totalfoodclear();
						} else {
							fooddio.add_food(new foodInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName,
									txt_storevaluewithcalorie.getText()));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							set_totalfoodclear();
						}
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					try {
						if (caloriedio.checkdate_calorie(date_name, getuserName) == true) {
							// nothing todo
						} else {
							date_name = f3.format(date_chooser.getDate());
							if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
								System.out.println(date_name + " " + getuserName);
								JOptionPane.showMessageDialog(null,
										"Sorry, Today Information is already taken kkkkk!!!");
								set_totalfoodclear();
							} else {
								double getfirtid = Double.parseDouble(txt_storevaluewithcalorie.getText());
								double getsecondid = Double.parseDouble(txt_retrive_Exercise.getText());
								double difference = (getfirtid - getsecondid);

								fooddio.add_food(new foodInfo(0, get_foodetails, get_caloriedetails, date_name,
										getuserName, txt_storevaluewithcalorie.getText()));
								caloriedio.add_calorie(
										new calorieburnInfo(0, Double.toString(getsecondid), Double.toString(getfirtid),
												Double.toString(difference), date_name, getuserName));
								JOptionPane.showMessageDialog(null, "Add a New Position!!");
								set_totalfoodclear();
							}

						}
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}

			}

		} else if (source.equals(btn_help)) {
			try {
				String help_url = "http://allabouthealthwellness.blogspot.com/2011/01/understanding-different-types-of.html";
				Desktop.getDesktop().browse(URI.create(help_url));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (source.equals(btn_addfood)) {
			String getmeal_Name = txt_mealName.getText();
			String getmeal_Calories = txt_calories.getText();
			if (!getmeal_Name.equals("") || !getmeal_Calories.equals("")) {
				try {
					if (fooddio.checkstorefood_username(getuserName) && fooddio.checkstorefood_foodname(getmeal_Name)) {

					} else {
						fooddio.add_storefood(new storefoodInfo(0, getmeal_Name, getmeal_Calories, getuserName));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
			}

			mealtypeadd();

		} else if (source.equals(btn_mealsave_1)) {// saveExercise button done
			String get_foodetails = txt_totalexercise_Name.getText().toString();
			String get_caloriedetails = txt_totalexercise_minutes.getText().toString();
			String date_name = null;
			if (get_foodetails.equals("") || get_caloriedetails.equals("") || date_chooser.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Field MustNot Empty!!!");
			} else {
				if (txt_retrive_Food.getText().equals("")) {
					try {
						date_name = f3.format(date_chooser.getDate());
						if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
							set_exerciseClear();
						} else {
							date_name = f3.format(date_chooser.getDate());
							exercisedio.addExercise(
									new exerciseInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							set_exerciseClear();
						}
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					try {
						if (caloriedio.checkdate_calorie(date_name, getuserName) == true) {
							// nothing todo
						} else {
							date_name = f3.format(date_chooser.getDate());
							if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
								JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
								set_exerciseClear();
							} else {
								double getfirtid = Double.parseDouble(txt_totalexercise_minutes.getText());
								double getsecondid = Double.parseDouble(txt_retrive_Food.getText());

								double difference = (getfirtid - getsecondid);
								exercisedio.addExercise(new exerciseInfo(0, get_foodetails, get_caloriedetails,
										date_name, getuserName));
								caloriedio.add_calorie(
										new calorieburnInfo(0, Double.toString(getsecondid), Double.toString(getfirtid),
												Double.toString(difference), date_name, getuserName));
								JOptionPane.showMessageDialog(null, "Add a New Position!!");
								set_exerciseClear();
							}

						}
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}

			}

		} else if (source.equals(btn_averagewaist)) {
			int first_waist = Integer.parseInt(txt_morningwaist.getText());
			int second_waist = Integer.parseInt(txt_eveningwaist.getText());
			if ((txt_morningwaist.getText().equals("") || txt_eveningwaist.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
			} else {
				int total_waist = (first_waist + second_waist) / 2;
				txt_averagewaist.setText("" + total_waist);
			}
		} else if (source.equals(btn_averageweight)) {
			int first_weight = Integer.parseInt(txt_morningweight.getText());
			int second_weight = Integer.parseInt(txt_eveningweight.getText());
			if ((txt_morningweight.getText().equals("") || txt_eveningweight.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
			} else {
				int total_weight = (first_weight + second_weight) / 2;
				txt_averageweight.setText("" + total_weight);
			}

		} else if (source.equals(btn_addexercise)) {
			add_ExerciseType();
		} else if (source.equals(btn_cancel)) {
			dispose();
		}

	}

	public void mealtypeadd() {
		try {
			String getmealtype = cmb_selectmealtype.getSelectedItem().toString();
			String first = txt_mealName.getText();
			double check_fat = Double.parseDouble(txt_calories.getText());
			if (getmealtype.equals("Fat")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 9;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			} else if (getmealtype.equals("Alcohol")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 7;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			} else if (getmealtype.equals("Protein") || getmealtype.equals("Carbohydrate")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 4;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty");
		}
	}

	// some basic calculation for my application (Exercise) =---------(EXXX)
	private void add_ExerciseType() {
		try {
			String getExerciseSelect = txt_exercisename_1.getSelectedItem().toString();
			String getExerciseValue = txt_exercisevalue_1.getText();
			if (getExerciseSelect.equals("Runnig")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 10.53;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Push Up")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 9.10;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Bicycling")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 6.06;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Cooking")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 0.002;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Dancing")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 0.023;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Jumping")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Basketball")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 24.26;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Slow Walk")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 8.5;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("swimming")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Jogging")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			}

		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty");
		}
	}

	private void setclearwaist() {
		txt_morningwaist.setText("");
		txt_eveningwaist.setText("");
		txt_averagewaist.setText("");
		txt_retrive_waistvalue.setText("");
		txt_waistAverage.setText("");

	}

	private void setclearweight() {
		txt_morningweight.setText("");
		txt_eveningweight.setText("");
		txt_averageweight.setText("");
		txt_retrive_weightvalue.setText("");
		txt_weightAverage.setText("");
	}

	private void setclearfood() {
		txt_mealName.setText("");
		txt_calories.setText("");
	}

	private void set_totalfoodclear() {
		txt_addfooddetails.setText("");
		txt_addfoodecal.setText("0");
		txt_storevaluewithcalorie.setText("0");
	}

	public void set_exerciseClear() {
		txt_totalexercise_Name.setText("");
		txt_totalexercise_minutes.setText("0");
		txt_retrive_Food.setText("");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String getmealname = txt_mealName.getText().toString();
		try {
			if (fooddio.checkstorefood_username(getuserName)
					&& fooddio.checkstorefood_foodname(txt_mealName.getText())) {
				Retrive_FoodCalorie(getmealname, getuserName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (txt_mealName.getText().equals("")) {
			txt_calories.setText("");
		}
		String date_name = null;
		try {
			date_name = f3.format(date_chooser.getDate());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Date Field Must Not Empty!!!");
		}
		try {
			if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
				check_FoodvalueExit(date_name, getuserName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//
		try {
			if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
				check_ExercisevalueExit(date_name, getuserName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent getaction) {
	}

	@SuppressWarnings("resource")
	public void Retrive_FoodCalorie(String foodname, String usernmecheck) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from storefood where foodvirtual_name='" + foodname
					+ "' And foodvirtual_username='" + usernmecheck + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(3).trim();
				txt_calories.setText(get_gram);
				rs = null;
			}
			con.close();
		} catch (Exception ex) {

		}

	}

	@SuppressWarnings("resource")
	public void check_ExercisevalueExit(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from exercise where exercise_date='" + date + "' And exercise_username='"
					+ username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(3).trim();
				txt_retrive_Exercise.setText(get_gram);
			}
			rs = null;
			con.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	public void check_FoodvalueExit(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from food where food_date='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(6).trim();
				txt_retrive_Food.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Retrive_waistDetails(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from waist where waist_date='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				txt_retrive_waistvalue.setText(get_gram);

			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Retrive_weightDetails(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from weight where weight_date ='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				txt_retrive_weightvalue.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// what up new

	public void Retrive_dailyActivity(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from weight where weight_date ='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				everyday_Activity.setText(get_gram);
			}
			rs = null;
			con.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showEstimateInfo() {
		String get_averageValue = average_weight;
		if (get_averageValue.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			if (getuserGender.equalsIgnoreCase("Male")) {

				if (select_Activity.getSelectedItem().equals("Active") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 7.5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else if (select_Activity.getSelectedItem().equals("InActive") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else {
					double result_Active = (Double.parseDouble(get_averageValue) * 6);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				}

			} else {

				if (select_Activity.getSelectedItem().equals("Active") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 6);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else if (select_Activity.getSelectedItem().equals("InActive") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 4);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else {
					double result_Active = (Double.parseDouble(get_averageValue) * 5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				}
			}
		}

	}

}
