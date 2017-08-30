package com.rf.zzc.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rf.zzc.dao.StudentDao;
import com.rf.zzc.jdbc.DBUtil;
import com.rf.zzc.model.Student;

public class StudentImpl implements StudentDao {
	// 封装insert（添加）方法
	public  int insert(Student s) {
		Connection c = DBUtil.getConn();
		int i = 0;
		String sql = "insert into student (id,name,age,birthday) value(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) c.prepareStatement(sql);
			pstmt.setInt(1, s.getId());
			pstmt.setString(2, s.getName());
			pstmt.setInt(3, s.getAge());
			pstmt.setDate(4, (Date) s.getBirthday());
			i = pstmt.executeUpdate();
			pstmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	// update方法的封装
	public  int update(Student s) {
		Connection c = DBUtil.getConn();
		int i = 0;
		String sql = "update student set name='" + s.getName() + "',age='" + s.getAge() + "',birthday='"
				+ s.getBirthday() + "' where id='" + s.getId() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	// select封装方法
	public  void select() {
		Connection c = DBUtil.getConn();
		String sql = "select birthday from student";
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			/*此处为输出所有的内容，查询的也是*
			 * int col = r.getMetaData().getColumnCount();
			while (r.next()) {
				for (int i = 1; i < =col; i++) {
					System.out.println(r.getString(i) + "\t");
				}
				System.out.println();
			}*/
			while(r.next()) {
				System.out.println(r.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// delete的封装方法
	public  int delete(Student s) {
		Connection c = DBUtil.getConn();
		int i = 0;
		String sql = "delete from student where name='" + s.getName() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
}
