package com.rf.zzc.dao;

import com.rf.zzc.model.Student;

public interface StudentDao {
	public abstract int insert(Student s);

	public int update(Student s);

	public void select();

	public int delete(Student s);
}
