package cn.yfc.aone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.service.AffairsService;
import cn.yfc.aone.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AffairsService affairsService;
	@RequestMapping("/employee")
	 public ModelAndView homePage(Model model,String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("username=-=-=-=-="+cname);
		employeeService.getUsername(username);
		model.addAttribute("username",cname);
	 return new ModelAndView("/Employee");
	 }
	@RequestMapping("/yzwygxx")
	 public ModelAndView yzwygxx() {
	 return new ModelAndView("/yzwygxx");
	 }
	/**
	 * 获取地图经纬度并先死到地图上
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
	 * @return
	 */
	@RequestMapping("/grzwxx")
	 public ModelAndView grzwxx() {
	 return new ModelAndView("/grzwxx");
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
		System.out.println(list);
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
		System.out.println(list);
		return list;
	}
}
