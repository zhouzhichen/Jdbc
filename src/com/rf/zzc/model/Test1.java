package com.rf.zzc.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.rf.zzc.jdbc.DBUtil;

public class Test1 {
	
	public static void main(String[] args) {
		
		//jdbc里面存储过程里面有out出参的一般办法。
		
		/*Connection c = DBUtil.getConn();
		String pro = "{call Getout(?)}";
		try {
			CallableStatement cas = c.prepareCall(pro);
			cas.registerOutParameter(1, Types.INTEGER);//直接用jdbc是给？赋值，而储存过程是给？赋一个类型为integer的
			cas.execute();//执行操作
			int x = cas.getInt(1);//得到？锁代表的值
			System.out.println(x);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//有1个输入参数的存储过程
		
		/*Connection c=DBUtil.getConn();
		String str="{call search(?)}";
		try {
			CallableStatement cas=c.prepareCall(str);
			cas.setString(1, "j");  设置？的值
			cas.execute();//设置完成后要执行才能有结果，后面的set才能有元素。
			ResultSet s=cas.getResultSet();
			while(s.next()) {
				System.out.println(s.getTime("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		//有一个输入和一个输出参数的存储过程
		
		/*Connection c=DBUtil.getConn();
		String str="{call inandout(?,?)}";
		try {
			CallableStatement cas=c.prepareCall(str);
			cas.setInt(1, 10);
			cas.registerOutParameter(2, Types.VARCHAR);
			cas.execute();
			String str1=cas.getString(2);
			System.out.println(str1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//输入即输出参数的存储过程
		Connection c=DBUtil.getConn();
		String str="{call same(?)}";
		try {
			CallableStatement cas=c.prepareCall(str);
			cas.setString(1, "s");
			cas.registerOutParameter(1, Types.VARBINARY);
			cas.execute();
			String s=cas.getString(1);
			System.out.println(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBUtil.close(c, null, null);
		
	}
	
	

}
