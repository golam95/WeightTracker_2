package com.tracker.java.DAO;

import java.sql.SQLException;

import com.tracker.java.model.waistInfo;

public interface waistDAO {

	public void add_waist(waistInfo waistinfo) throws SQLException;

	public void delete_Waist(int waist_id) throws SQLException;

	public void update_waist(waistInfo waistinfo) throws SQLException;

	public boolean checkdate_waist(String check_date, String check_uesrname) throws SQLException;
}
