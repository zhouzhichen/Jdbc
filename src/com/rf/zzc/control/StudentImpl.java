package com.rf.zzc.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rf.zzc.dao.StudentDao;
import com.rf.zzc.jdbc.DBUtil;
import com.rf.zzc.model.Student;

public class StudentImpl implements StudentDao {
	// 封装insert（添加）方法
	public int insert(Student s) {
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
	public int update(Student s) {
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
	public void select() {
		Connection c = DBUtil.getConn();
		String sql = "select birthday from student";
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			/*
			 * 此处为输出所有的内容，查询的也是* int col = r.getMetaData().getColumnCount(); while
			 * (r.next()) { for (int i = 1; i < =col; i++) {
			 * System.out.println(r.getString(i) + "\t"); } System.out.println(); }
			 */
			while (r.next()) {
				System.out.println(r.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 数据的解析，用select方法
	public List<Student> select2() {
		Connection c = DBUtil.getConn();
		String sql = "select * from student";
		PreparedStatement pstmt = null;
		try {
			pstmt = c.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Student> list = new ArrayList();
		try {
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setBirthday((Date) rs.getDate("birthday"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// delete的封装方法
	public int delete(Student s) {
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

	// 多字段模糊查询
	public ArrayList<Student> fuzzyQueryByMultiPara(String... para) {
		ArrayList<Student> users = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstat = null;
		ResultSet set = null;
		String sql = "select * from student where";

		if (para.length != 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < para.length - 1; i += 2) {
				if (para[i + 1] == null || "".equals(para[i + 1])) {
					continue;
				}
				if (i != 0) {
					sb.append(" and ");
				}
				sb.append(para[i] + " like " + "'%" + para[i + 1] + "%'");
			}
			sql += sb.toString();
		}
		if (sql.equals("select * from student where")) {
			sql = "select * from student";
		}
		System.out.println("查询语句为:\n" + sql);

		try {
			pstat = conn.prepareStatement(sql);
			set = pstat.executeQuery();
			while (set.next()) {
				Student user = new Student(set.getInt("id"), set.getString("name"), set.getInt("age"),
						set.getDate("birthday"));
				users.add(user);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("输入字段有误,查询失败");
		} finally {
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

}
