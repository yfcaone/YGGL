package cn.yfc.aone.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.yfc.aone.service.AffairsService;
import cn.yfc.aone.service.TravelService;

@Controller
@RequestMapping("/yggl")
public class ManagerController {

	Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private AffairsService affairsService;

	@Autowired
	private TravelService travelService;
	
	@RequestMapping("/homepage")
	public ModelAndView homePage(Model model, String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		affairsService.getUsername(cname);
		travelService.getUsername(cname);
		model.addAttribute("username", cname);
		return new ModelAndView("/homepage");
	}

	@RequestMapping("/model")
	public ModelAndView model() {
		return new ModelAndView("/model");
	}

	@RequestMapping("/worktable")
	public ModelAndView worktable() {
		return new ModelAndView("/WorkTable");
	}

	@RequestMapping("/dynamic")
	public ModelAndView dynamic() {
		return new ModelAndView("/dynamic");
	}

	/**
	 * 员工信息界面
	 *
	 * @return
	 */
	@RequestMapping("/zwygxx")
	public ModelAndView zwygxx() {
		return new ModelAndView("/zwygxx");
	}

	/**
	 * 已评在外员工信息界面
	 *
	 * @return
	 */
	@RequestMapping("/ypzwygxx")
	public ModelAndView ypzwygxx() {
		return new ModelAndView("ypzwygxx");
	}

	/**
	 * 出差信息录入界面
	 * @return
	 */
	@RequestMapping("/ccxxlr")
	public ModelAndView ccxxlr() {
		return new ModelAndView("ccxxlr");
	}
	
	/**
	 * 请假信息录入界面
	 * @return
	 */
	@RequestMapping("/qjxxlr")
	public ModelAndView qjxxlr() {
		return new ModelAndView("qjxxlr");
	}
	
	/**
	 * 报销信息录入界面
	 * @return
	 */
	@RequestMapping("/bxxxlr")
	public ModelAndView bxxxlr() {
		return new ModelAndView("bxxxlr");
	}
	
	/**
	 * 出差费用结算界面
	 * @return
	 */
	@RequestMapping("/ccfyjs")
	public ModelAndView ccfyjs() {
		return new ModelAndView("ccfyjs");
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
	 * 根据用户名和项目名称 获得日志内容界面
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
	
	

	/** ------------------------------------------------------------------------------------------------------------------------------------------- */
	/**
	 * 获取未评测员工信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("emplinfor")
	public List<Map<String, Object>> emplInfor(String name) throws Exception {
		String lname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("name============="+lname);
		List<Map<String, Object>> list = affairsService.selectAll(lname);
		System.out.println(list);
		return list;
	}

	/**
	 * 获取已评在外员工信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("getYpzaygInfo")
	public List<Map<String, Object>> getYpzaygInfo(String name) throws Exception {
		String lname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("name============="+lname);
		List<Map<String, Object>> list = affairsService.getYpzaygInfo(lname);
		return list;
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
		System.out.println(name + affair);
		List<Map<String, Object>> list = affairsService.getLogContent(name, affair);
		return list;
	}

	/**
	 * 给员工评分
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addPcInfo")
	public String addPcInfo(@RequestBody Map<String, Object> map, String account, String affair) throws Exception {
		String eaccount = new String(account.getBytes("ISO8859-1"), "UTF-8");
		String eaffair = new String(affair.getBytes("ISO8859-1"), "UTF-8");
		affairsService.addPcInfo(map, eaccount, eaffair);
		return "true";
	}

	/**
	 * 创建用户
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("createUser")
	public String createUser(@RequestBody Map<String, Object> map) throws Exception {
		System.out.println("map=-=-=-=-=" + map);
		affairsService.createUser(map);
		return "true";
	}

	/**
	 * 获得用户并查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getUserInfo")
	public List<Map<String, Object>> getUserInfo(String date, String name) throws Exception {
		System.out.println("date==============" + date + name);
		String vname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		List<Map<String, Object>> list = affairsService.getUserInfo(date, vname);
		return list;
	}

	/**
	 * 获得城市等级
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCityGrade")
	public List<Map<String,Object>> getCityGrade()  {
		List<Map<String,Object>> list = travelService.getCityGrade();
		return list;
	}
	
	/**
	 * 获得城市
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCity")
	public List<Map<String,Object>> getCity(String city)  {
		System.out.println(city);
		List<Map<String,Object>> list = travelService.getCity(city);
		return list;
	}
	
	/**
	 * 获得部门名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getDepartment")
	public List<Map<String,Object>> getDepartment()  {
		List<Map<String,Object>> list = travelService.getDepartment();
		return list;
	}
	
	/**
	 * 获得职务
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPost")
	public List<Map<String,Object>> getPost(String department)  {
		System.out.println(department);
		List<Map<String,Object>> list = travelService.getPost(department);
		return list;
	}
	
	/**
	 * 录入出差信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTravelInfo")
	public String  addTravelInfo(HttpServletRequest request)  {
		String city = request.getParameter("city");
		String affair_name = request.getParameter("affair_name");
		String go_date = request.getParameter("go_date");
		String post_name = request.getParameter("post_name");
		String job_number = request.getParameter("job_number");
		System.out.println("------"+city+"------"+affair_name+"------"+go_date+"------"+post_name+"------"+job_number);
		travelService.addTravelInfo(city,affair_name,go_date,post_name,job_number);
		return "true";
	}
	
	/**
	 * 获得出差信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTravelInfo")
	public List<Map<String,Object>> getTravelInfo()  {
		List<Map<String,Object>> list = travelService.getTravelInfo();
		return list;
	}
	
	/**
	 * 添加请假信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addLeaveInfo")
	public String  addLeaveInfo(HttpServletRequest request)  {
		String log_number = request.getParameter("log_number");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String leave_cause = request.getParameter("leave_cause");
		System.out.println("------"+log_number+"------"+start_date+"------"+end_date+"------"+leave_cause);
		travelService.addLeaveInfo(log_number,start_date,end_date,leave_cause);
		return "true";
	}
	
	/**
	 * 获得请假信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getLeaveInfo")
	public List<Map<String,Object>> getLeaveInfo()  {
		List<Map<String,Object>> list = travelService.getLeaveInfo();
		System.out.println("请假信息=========="+list);
		return list;
	}
	
	/**
	 * 添加报销信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addReimbursementInfo")
	public String  addReimbursementInfo(HttpServletRequest request)  {
		String bx_log_number = request.getParameter("bx_log_number");
		String bx_invoice = request.getParameter("bx_invoice");
		String bx_maney = request.getParameter("bx_maney");
		System.out.println("------"+bx_log_number+"------"+bx_invoice+"------"+bx_maney);
		travelService.addReimbursementInfo(bx_log_number,bx_invoice,bx_maney);
		return "true";
	}
	
	/**
	 * 获得报销信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getReimbursementInfo")
	public List<Map<String,Object>> getReimbursementInfo()  {
		List<Map<String,Object>> list = travelService.getReimbursementInfo();
		System.out.println("报销信息=========="+list);
		return list;
	}
	
	/**
	 * 添加借款信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addLoanInfo")
	public String  addLoanInfo(HttpServletRequest request)  {
		String jk_log_numbers = request.getParameter("jk_log_numbers");
		String jk_loan = request.getParameter("jk_loan");
		String jk_money = request.getParameter("jk_money");
		System.out.println("------"+jk_log_numbers+"------"+jk_loan);
		travelService.addLoanInfo(jk_log_numbers,jk_loan,jk_money);
		return "true";
	}
	
	/**
	 * 获得借款信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getloanInfo")
	public List<Map<String,Object>> getloanInfo()  {
		List<Map<String,Object>> list = travelService.getloanInfo();
		System.out.println("借款信息=========="+list);
		return list;
	}
	/** -------=++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++================================= */

	/**
	 * 添加员工信息界面
	 *
	 * @return
	 */
	@RequestMapping("/yuangong")
	public ModelAndView yuangong() {
		return new ModelAndView("yuangong");
	}

}
