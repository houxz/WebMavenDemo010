package com.projectmanage.dao.projectmanager;

import java.util.List;
import java.util.Map;

public interface UserRoleModelDao {

	List<Map<String, Object>> getEpleRoles(int userid);
}
