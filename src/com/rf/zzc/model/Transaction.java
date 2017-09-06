package com.rf.zzc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.rf.zzc.jdbc.DBUtil;

public class Transaction {

	public static User choose(int id) {
		Connection c = DBUtil.getConn();
		String sql = "select * from User where id = " + id;
		System.out.println(sql);
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.executeQuery();
			ResultSet rt = pstmt.getResultSet();
			User u = new User();
			while (rt.next()) {
				u.setId(rt.getInt("id"));
				u.setName(rt.getString("name"));
				u.setMoney(rt.getInt("money"));
			}
			return u;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void change(User u1, User u2, int money) {
		Connection c = DBUtil.getConn();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sqla = "update User set money =" + (u1.getMoney() - money) + " where id =" + u1.getId();
		String sqlb = "update User set money =" + (u2.getMoney() + money) + " where id =" + u2.getId();
		System.out.println(sqla);
		try {
			PreparedStatement pstmta = c.prepareStatement(sqla);
			PreparedStatement pstmtb = c.prepareStatement(sqlb);
			pstmta.executeUpdate();
			pstmtb.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		change(choose(1), choose(2), 1000);
	}
}
