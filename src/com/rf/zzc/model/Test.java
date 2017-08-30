package com.rf.zzc.model;

import java.sql.Date;

import com.rf.zzc.control.StudentImpl;

public class Test {

	public static void main(String[] args) {
		StudentImpl sm=new StudentImpl();
		Date d=new Date(System.currentTimeMillis());
		Student s=new Student(10,"ку╤Ч╫Ц╫Ц",18,d);
		//sm.insert(s);
		//sm.select();
		//sm.delete(s);
		//sm.update(s);
	}

}
