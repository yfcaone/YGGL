package cn.yfc.aone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.service.AffairsService;
import cn.yfc.aone.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private String ccname;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AffairsService affairsService;

	@RequestMapping("/employee")
	public ModelAndView homePage(Model model, String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		Map<String, Object> map = employeeService.getName(cname);
		String ccname = (String) map.get("UNAME");
		this.ccname = ccname;
		employeeService.getUsername(username);
		model.addAttribute("username", ccname);
		return new ModelAndView("/Employee");
	}

	@RequestMapping("/yzwygxx")
	public ModelAndView yzwygxx() {
		return new ModelAndView("/yzwygxx");
	}

	/**
	 * 获取地图经纬度并显示到地图上
	 * 
	 * @param model
	 * @param account
	 *            员工姓名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/map")
	public ModelAndView map(ModelMap model, String account) throws Exception {
		String cname = new String(account.getBytes("ISO8859-1"), "UTF-8");
		Map<String, Object> map = affairsService.getMapjw(cname);
		String j = (String) map.get("LONGITUDE");
		String w = (String) map.get("LATITUDE");
		model.addAttribute("j", j);
		model.addAttribute("w", w);
		return new ModelAndView("map");
	}

	/**
	 * 个人在外信息
	 * 
	 * @return
	 */
	@RequestMapping("/grzwxx")
	public ModelAndView grzwxx(Model model) {
		System.out.println("ccname++" + ccname);
		model.addAttribute("username", ccname);
		return new ModelAndView("/grzwxx");
	}

	/**
	 * 根据用户名和项目名称 获得日志内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logContent")
	public ModelAndView logContent(ModelMap model, String account, String affair) throws Exception {
		String lname = new String(account.getBytes("ISO8859-1"), "UTF-8");
		String laffair = new String(affair.getBytes("ISO8859-1"), "UTF-8");
		model.addAttribute("lname", lname);
		model.addAttribute("laffair", laffair);
		return new ModelAndView("logContent");
	}

	/**
	 * 获取未完成员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getZwygInfo")
	public List<Map<String, Object>> getZwygInfo() throws Exception {
		List<Map<String, Object>> list = employeeService.getZwygInfo();
		return list;
	}

	/**
	 * 获取个人在外信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getSelfInfo")
	public List<Map<String, Object>> getSelfInfo() throws Exception {
		List<Map<String, Object>> list = employeeService.getSelfInfo();
		return list;
	}

	/**
	 * 更新项目是否完成
	 * 
	 * @param ISCOMPLETE
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateInfo")
	public String updateInfo(String ID, String ACCOUNT, String AFFAIR, String ISCOMPLETE) throws Exception {
		System.out.println("士大夫首發式地方" + ISCOMPLETE);
		affairsService.updateInfo(ISCOMPLETE, ID, ACCOUNT, AFFAIR);
		return "true";
	}

	/**
	 * 添加日志
	 * 
	 * @param map
	 * @param account
	 *            用户名
	 * @param affair
	 *            项目名称
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addLogInfo")
	public String addLogInfo(@RequestBody Map<String, Object> map, String account, String affair) throws Exception {
		String eaccount = new String(account.getBytes("ISO8859-1"), "UTF-8");
		String eaffair = new String(affair.getBytes("ISO8859-1"), "UTF-8");
		System.out.println(map + eaccount + eaffair);
		employeeService.addLogInfo(map, eaccount, eaffair);
		return "true";
	}

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updatePwd")
	public Map<String, Object> updatePwd(HttpServletRequest request) throws Exception {
		String old_pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		String sure_new_pwd = request.getParameter("sure_new_pwd");
		System.out.println("修改密码" + old_pwd + new_pwd + sure_new_pwd);
		int i = employeeService.getJudgmentPwd(old_pwd);
		System.out.println("i的值为" + i);
		Map<String, Object> map2 = new HashMap<String, Object>();
		if (i == 1) {
			if (new_pwd.equals(sure_new_pwd)) {
				employeeService.updatePwd(new_pwd, old_pwd);
				map2.put("sure", "密码修改成功");
				return map2;
			} else {
				map2.put("sure", "确认密码不对");
				return map2;
			}
		} else {
			map2.put("sure", "原密码不正确");
			return map2;
		}

		// employeeService.updatePwd(map);
	}

	/**
	 * 获得日志内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getLogContent")
	public List<Map<String, Object>> getLogContent(String laffair, String lname) throws Exception {
		String name = new String(lname.getBytes("ISO8859-1"), "UTF-8");
		String affair = new String(laffair.getBytes("ISO8859-1"), "UTF-8");
		List<Map<String, Object>> list = affairsService.getLogContent(name, affair);
		return list;
	}

	/**
	 * 添加在外员工信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addData")
	public String addData(@RequestBody Map<String, Object> map) throws Exception {
		employeeService.addData(map);
		return "true";
	}
}
