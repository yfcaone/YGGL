package cn.yfc.aone.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	@Override
	public Map<String, Object> getLogContent(String username,String password,String role) {
		
		Map<String, Object> map1 = loginDao.getLogContent(username,password,role);
		return map1;
	}

}
