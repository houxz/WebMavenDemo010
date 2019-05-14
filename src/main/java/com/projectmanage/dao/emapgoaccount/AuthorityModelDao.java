package com.projectmanage.dao.emapgoaccount;

import com.projectmanage.pojo.AuthorityModel;

public interface AuthorityModelDao {

	AuthorityModel getAuthorityByUsername(String username);
}
