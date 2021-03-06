package cn.yfc.aone.controller;

import java.awt.Color;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.beans.Money;
import cn.yfc.aone.beans.Travel;
import cn.yfc.aone.service.AffairsService;
import cn.yfc.aone.service.TravelService;

@Controller
@RequestMapping("/yggl")
public class ManagerController {

	List<Map<String, Object>> list123;
	List<Map<String, Object>> moneyList;
	String P_NUMBERS;
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
	 * 
	 * @return
	 */
	@RequestMapping("/ccxxlr")
	public ModelAndView ccxxlr() {
		return new ModelAndView("ccxxlr");
	}

	/**
	 * 请假信息录入界面
	 * 
	 * @return
	 */
	@RequestMapping("/qjxxlr")
	public ModelAndView qjxxlr() {
		return new ModelAndView("qjxxlr");
	}

	/**
	 * 报销信息录入界面
	 * 
	 * @return
	 */
	@RequestMapping("/bxxxlr")
	public ModelAndView bxxxlr() {
		return new ModelAndView("bxxxlr");
	}

	/**
	 * 出差费用结算界面
	 * 
	 * @return
	 */
	@RequestMapping("/ccfyjs")
	public ModelAndView ccfyjs() {
		return new ModelAndView("ccfyjs");
	}

	/**
	 * 项目员工信息管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/xmygxxgl")
	public ModelAndView xmygxxgl() {
		return new ModelAndView("xmygxxgl");
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
		System.out.println("地图的名字是什麽" + cname);
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
	public Map<String, Object> emplInfor(String name, HttpServletRequest request) throws Exception {
		String lname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		List<Map<String, Object>> list1 = affairsService.selectAllInfo(lname);
		List<Map<String, Object>> list = affairsService.selectAll(lname, page, start, limit);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalCount", list1.size());

		return map;
	}

	/**
	 * 获取已评在外员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getYpzaygInfo")
	public Map<String, Object> getYpzaygInfo(HttpServletRequest request, String name) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String lname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		List<Map<String, Object>> list1 = affairsService.getYpzaygInfo(lname);
		List<Map<String, Object>> list = affairsService.getYpzaygAllInfo(lname, page, start, limit);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalCount", list1.size());
		return map;
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
		System.out.println("map-古典风格" + map);
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
		String vname = new String(name.getBytes("ISO8859-1"), "UTF-8");
		List<Map<String, Object>> list = affairsService.getUserInfo(date, vname);
		return list;
	}

	/**
	 * 获得城市等级
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCityGrade")
	public List<Map<String, Object>> getCityGrade() {
		List<Map<String, Object>> list = travelService.getCityGrade();
		return list;
	}

	/**
	 * 获得城市
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCity")
	public List<Map<String, Object>> getCity(String city) {
		System.out.println(city);
		List<Map<String, Object>> list = travelService.getCity(city);
		return list;
	}

	/**
	 * 获得部门名称
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getDepartment")
	public List<Map<String, Object>> getDepartment() {
		List<Map<String, Object>> list = travelService.getDepartment();
		return list;
	}

	/**
	 * 获得职务
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPost")
	public List<Map<String, Object>> getPost(String department) {
		List<Map<String, Object>> list = travelService.getPost(department);
		return list;
	}

	/**
	 * 获得项目编号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getXmbh")
	public List<Map<String, Object>> getXmbh() {
		List<Map<String, Object>> list = travelService.getXmbh();
		return list;
	}

	/**
	 * 获得工号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGhData")
	public List<Map<String, Object>> getGhData(String p_number) {
		List<Map<String, Object>> list = travelService.getGhData(p_number);
		return list;
	}

	/**
	 * 获得工号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getNumber")
	public List<Map<String, Object>> getNumber(String post_name) {
		List<Map<String, Object>> list = travelService.getNumber(post_name);
		return list;
	}

	/**
	 * 录入出差信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTravelInfo")
	public String addTravelInfo(HttpServletRequest request) {
		String city = request.getParameter("city");
		String affair_name = request.getParameter("affair_name");
		String go_date = request.getParameter("go_date");
		String p_number = request.getParameter("p_number");
		String post_name = request.getParameter("post_name");
		String job_number = request.getParameter("job_number");
		travelService.addTravelInfo(city, affair_name, go_date, post_name, job_number, p_number);
		return "true";
	}

	/**
	 * 添加新在外员工信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTravelInfos")
	public String addTravelInfos(HttpServletRequest request) {
		String p_number = request.getParameter("P_NUMBER");
		String p_city = request.getParameter("P_CITY");
		String go_date = request.getParameter("go_date");
		String p_name = request.getParameter("P_NAME");
		String post_name = request.getParameter("post_name");
		String job_number = request.getParameter("job_number");
		System.out.println("p_number" + p_number + "p_city" + p_city + "go_date" + go_date + "p_name" + p_name
				+ "post_name" + post_name + "job_number" + job_number);
		travelService.addTravelInfos(p_number, p_city, go_date, post_name, job_number, p_name);
		return "true";
	}

	/**
	 * 重置用户名密码
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("resetPwd")
	public String resetPwd(HttpServletRequest request) {
		String VACCOUNT = request.getParameter("VACCOUNT");
		System.out.println("resetPwd" + VACCOUNT);
		travelService.resetPwd(VACCOUNT);
		return "true";
	}

	/**
	 * 创建项目编号
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCreateTravelInfo")
	public List<Map<String, Object>> addCreateTravelInfo(HttpServletRequest request) {
		String add_city = request.getParameter("add_city");
		String add_affair_name = request.getParameter("add_affair_name");
		String add_go_date = request.getParameter("add_go_date");
		List<Map<String, Object>> list = travelService.addCreateTravelInfo(add_city, add_affair_name, add_go_date);
		return list;
	}

	/**
	 * 获得出差信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTravelInfo")
	public Map<String, Object> getTravelInfo(HttpServletRequest request, String P_NUMBER) {
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		list123 = travelService.getTravelInfo(P_NUMBER, page, start, limit);
		List<Map<String, Object>> list1 = travelService.getAllTravelInfo(P_NUMBER);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list123);
		map.put("totalCount", list1.size());

		return map;
	}

	/**
	 * 根据项目获得在外员工信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTravelInfos")
	public List<Map<String, Object>> getTravelInfos() {
		List<Map<String, Object>> list = travelService.getTravelInfos(P_NUMBERS);
		return list;
	}

	/**
	 * 获得所点击项目编号信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getP_NUMBER")
	public String getP_NUMBER(String P_NUMBER) {
		this.P_NUMBERS = P_NUMBER;
		System.out.println("P_NUMBER" + P_NUMBER);
		return "true";
	}

	/**
	 * 获得项目员工信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getXmygxxInfo")
	public List<Map<String, Object>> getXmygxxInfo() {
		List<Map<String, Object>> list = travelService.getXmygxxInfo();
		System.out.println("项目信息得到了吗" + list);
		return list;
	}

	/**
	 * 添加请假信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addLeaveInfo")
	public String addLeaveInfo(HttpServletRequest request) {
		String log_number = request.getParameter("log_number");
		String p_number = request.getParameter("p_number");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String leave_cause = request.getParameter("leave_cause");
		System.out.println("log_number=" + log_number + "start_date=" + start_date + "end_date=" + end_date
				+ "leave_cause=" + leave_cause + "p_number" + p_number);
		travelService.addLeaveInfo(log_number, start_date, end_date, leave_cause, p_number);
		return "true";
	}

	/**
	 * 获得请假信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getLeaveInfo")
	public Map<String, Object> getLeaveInfo(HttpServletRequest request, String P_NUMBER) {
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<Map<String, Object>> list = travelService.getLeaveInfo(page, start, limit, P_NUMBER);
		List<Map<String, Object>> list1 = travelService.getAllLeave(P_NUMBER);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalCount", list1.size());
		return map;
	}

	/**
	 * 添加报销信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addReimbursementInfo")
	public String addReimbursementInfo(HttpServletRequest request) {
		String bx_log_number = request.getParameter("bx_log_number");
		String bx_invoice = request.getParameter("bx_invoice");
		String bx_maney = request.getParameter("bx_maney");
		travelService.addReimbursementInfo(bx_log_number, bx_invoice, bx_maney);
		return "true";
	}

	/**
	 * 获得报销信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getReimbursementInfo")
	public Map<String, Object> getReimbursementInfo(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<Map<String, Object>> list = travelService.getReimbursementInfo(page, start, limit);
		List<Map<String, Object>> list1 = travelService.getReimbursementCount();
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalCount", list1.size());
		return map;
	}

	/**
	 * 添加借款信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addLoanInfo")
	public String addLoanInfo(HttpServletRequest request) {
		String jk_log_numbers = request.getParameter("jk_log_numbers");
		String jk_loan = request.getParameter("jk_loan");
		String jk_money = request.getParameter("jk_money");
		travelService.addLoanInfo(jk_log_numbers, jk_loan, jk_money);
		return "true";
	}

	/**
	 * 获得借款信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getloanInfo")
	public Map<String, Object> getloanInfo(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		System.out.println("page====" + page + "start===" + start + "limit+++" + limit);
		List<Map<String, Object>> list = travelService.getloanInfo(page, start, limit);
		List<Map<String, Object>> list1 = travelService.getloanCount();
		System.out.println("list1==" + list1.size());
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("totalCount", list1.size());
		return map;
	}

	/**
	 * 获得所有需要信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllInfo")
	public List<Map<String, Object>> getAllInfo(HttpServletRequest request) {
		String project_number = request.getParameter("project_number");
		List<Map<String, Object>> list = travelService.getAllInfo(project_number);
		return list;
	}

	/**
	 * 删除在员工信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delTravelInfo")
	public String delTravelInfo(String TID) {
		travelService.delTravelInfo(TID);
		return "true";
	}

	/**
	 * 添加所有工资信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getWageInfo")
	public List<Map<String, Object>> getWageInfo(HttpServletRequest request) {
		String affair_name = request.getParameter("affair_name");
		String city_name = request.getParameter("city_name");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String stay_subsidy = request.getParameter("stay_subsidy");
		String food_subsidy = request.getParameter("food_subsidy");
		String p_number = request.getParameter("p_number");
		String traffic_subsidy = request.getParameter("traffic_subsidy");
		travelService.getWageInfo(affair_name, city_name, start_date, end_date, stay_subsidy, food_subsidy,
				traffic_subsidy, p_number);
		return null;
	}

	/**
	 * 添加在外员工撤离时间
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addTravelYgclInfo")
	public List<Map<String, Object>> addTravelYgclInfo(HttpServletRequest request) {
		String P_NUMBER = request.getParameter("P_NUMBER");
		String JOB_NUMBER = request.getParameter("JOB_NUMBER");
		String P_EVACUATE_DATE = request.getParameter("P_EVACUATE_DATE");
		System.out.println("P_EVACUATE_DATE" + P_EVACUATE_DATE + "P_NUMBER" + P_NUMBER + "JOB_NUMBER" + JOB_NUMBER);
		travelService.addTravelYgclInfo(P_NUMBER, JOB_NUMBER, P_EVACUATE_DATE);
		return null;
	}

	/**
	 * 获得所有工资信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllMoneyInfo")
	public List<Map<String, Object>> getAllMoneyInfo() {
		moneyList = travelService.getAllMoneyInfo();
		return moneyList;
	}

	/**
	 * 导出出差信息
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("DaoChu")
	public String DaoChu() {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("出差信息表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 声明一个画图的顶级管理器
		// HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("项目编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("项目名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("工号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("职务");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("出发时间");
		cell.setCellStyle(style);

		List<Travel> travels = new ArrayList<>();
		for (Map<String, Object> map : list123) {

			BigDecimal bd = (BigDecimal) map.get("TID");
			String id = bd.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			BigDecimal jn = (BigDecimal) map.get("JOB_NUMBER");
			String JOB_NUMBER = jn.setScale(0, BigDecimal.ROUND_HALF_UP).toString();

			/*
			 * Date date = (Date) map.get("TDATE"); String TDATE = new
			 * SimpleDateFormat("yyyy-MM-dd").format(date);
			 */
			Travel user = new Travel(id, (String) map.get("P_NUMBER"), (String) map.get("TAFFAIR"), JOB_NUMBER,
					(String) map.get("TNAME"), (String) map.get("DPOST"), (String) map.get("TDATE"));
			travels.add(user);
		}

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < travels.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Travel stu = (Travel) travels.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getTID());
			row.createCell((short) 1).setCellValue(stu.getP_NUMBER());
			row.createCell((short) 2).setCellValue(stu.getTAFFAIR());
			row.createCell((short) 3).setCellValue(stu.getJOB_NUMBER());
			row.createCell((short) 4).setCellValue(stu.getTNAME());
			row.createCell((short) 5).setCellValue(stu.getDPOST());
			row.createCell((short) 6).setCellValue(stu.getTDATE());

		}
		// 创建表格之后设置行高与列宽
		for (int i = 0; i < travels.size() + 1; i++) {
			row = sheet.getRow(i);
			row.setHeightInPoints(30);// 设置行高
		}
		for (int j = 0; j < 7; j++) {
			sheet.setColumnWidth(j, 3766);
		}

		short colorIndex = 10;
		HSSFPalette palette = wb.getCustomPalette();// 自定义颜色
		Color rgb = Color.GREEN;
		short bgIndex = colorIndex++; // 背景颜色下标值
		palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		short bdIndex = colorIndex++; // 边框颜色下标值
		rgb = Color.BLACK;
		palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		// 第六步，将文件存到指定位置
		try {
			Travel stu = (Travel) travels.get(0);
			FileOutputStream fout = new FileOutputStream("C:/Users/aone/Desktop/" + stu.getTAFFAIR() + "项目出差人员信息.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "true";
	}

	/**
	 * 导出出差费用结算信息
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("ExportInfo")
	public String ExportInfo() {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
		// style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("出差费用结算表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("工号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("职务");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("天数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("结算时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("发放时间");
		cell.setCellStyle(style);

		List<Money> moneys = new ArrayList<>();
		for (Map<String, Object> map : moneyList) {
			BigDecimal rid = (BigDecimal) map.get("RID");
			String RID = rid.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			BigDecimal jn = (BigDecimal) map.get("R_JOB_NUMBER");
			String R_JOB_NUMBER = jn.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			/*
			 * BigDecimal rd = (BigDecimal) map.get("R_DAYS"); String R_DAYS =
			 * rd.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			 */
			/*
			 * Date date = (Date) map.get("TDATE"); String TDATE = new
			 * SimpleDateFormat("yyyy-MM-dd").format(date);
			 */
			Money money = new Money(RID, R_JOB_NUMBER, (String) map.get("R_JOB_NAME"), (String) map.get("R_POST"),
					(String) map.get("R_DAYS"), (String) map.get("MONEYS"), (String) map.get("R_ENDTIME"),
					(String) map.get("R_RELEASETIME"));
			moneys.add(money);
		}
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < moneys.size(); i++) {
			row = sheet.createRow((int) i + 1);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 创建一个居中格式
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			Money money = (Money) moneys.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(money.getRID());
			row.createCell((short) 1).setCellValue(money.getR_JOB_NUMBER());
			row.createCell((short) 2).setCellValue(money.getR_JOB_NAME());
			row.createCell((short) 3).setCellValue(money.getR_POST());
			row.createCell((short) 4).setCellValue(money.getR_DAYS());
			row.createCell((short) 5).setCellValue(money.getMONEYS());
			row.createCell((short) 6).setCellValue(money.getR_ENDTIME());
			row.createCell((short) 7).setCellValue(money.getR_RELEASETIME());

		}

		// 创建表格之后设置行高与列宽
		for (int i = 0; i < moneys.size() + 1; i++) {
			row = sheet.getRow(i);
			row.setHeightInPoints(30);// 设置行高
		}
		for (int j = 0; j < 8; j++) {
			sheet.setColumnWidth(j, 3766);
		}

		short colorIndex = 10;
		HSSFPalette palette = wb.getCustomPalette();// 自定义颜色
		Color rgb = Color.GREEN;
		short bgIndex = colorIndex++; // 背景颜色下标值
		palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		short bdIndex = colorIndex++; // 边框颜色下标值
		rgb = Color.BLACK;
		palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:/Users/aone/Desktop/出差费用结算信息.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "true";
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
