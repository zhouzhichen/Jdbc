package com.rf.zzc.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.rf.zzc.jdbc.DBUtil;

public class Test1 {
	
	public static void main(String[] args) {
		
		//jdbc����洢����������out���ε�һ��취��
		
		/*Connection c = DBUtil.getConn();
		String pro = "{call Getout(?)}";
		try {
			CallableStatement cas = c.prepareCall(pro);
			cas.registerOutParameter(1, Types.INTEGER);//ֱ����jdbc�Ǹ�����ֵ������������Ǹ�����һ������Ϊinteger��
			cas.execute();//ִ�в���
			int x = cas.getInt(1);//�õ����������ֵ
			System.out.println(x);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//��1����������Ĵ洢����
		
		/*Connection c=DBUtil.getConn();
		String str="{call search(?)}";
		try {
			CallableStatement cas=c.prepareCall(str);
			cas.setString(1, "j");  ���ã���ֵ
			cas.execute();//������ɺ�Ҫִ�в����н���������set������Ԫ�ء�
			ResultSet s=cas.getResultSet();
			while(s.next()) {
				System.out.println(s.getTime("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		//��һ�������һ����������Ĵ洢����
		
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
		
		
		//���뼴��������Ĵ洢����
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
