package cn.yfc.aone.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public ModelAndView homePage() {
		return new ModelAndView("/login");
	}

	/**
	 * 登录判断
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getLogin")
	public String getLogContent(Model model,String username, String password, String role) throws Exception {
		Map<String, Object> map2 = loginService.getLogContent(username, password, role);
		System.out.println("username   " + username);
		System.out.println("password    " + password);
		System.out.println("role   " + role);
		if (map2.size() != 0 && role.equals("管理员")) {
			return "true";
		} else if (map2.size() != 0 && role.equals("员工")) {
			return "false";
		}
		return "no";

	}
}
