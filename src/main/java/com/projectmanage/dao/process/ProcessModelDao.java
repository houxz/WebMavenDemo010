package com.projectmanage.dao.process;

import java.util.List;

import com.projectmanage.pojo.ProcessModel;
import com.projectmanage.pojo.ProcessModelExample;

public interface ProcessModelDao {
	int countByExample(ProcessModelExample example);
	
	List<ProcessModel> selectByExample(ProcessModelExample example);
}
