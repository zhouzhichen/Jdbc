package com.rf.zzc.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rf.zzc.model.Student;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/test1";
	private static final String user = "root";
	private static final String password = "1234";

	// ��װconnection�������������ݿ������
	public static Connection getConn()  {
		// 1.���d��
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 2.�������ӣ�����MySQL���ݿ⣩
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(URL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 3.ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ顣 
		 * Statement st = conn.createStatement(); 
		 * ResultSet rs =st.executeQuery("select * from phone"); 
		 * while (rs.next()) {
		 * System.out.println(rs); }
		 */
		return conn;
	}

	
}
