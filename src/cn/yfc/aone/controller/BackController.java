package cn.yfc.aone.controller;

import java.util.List;
import java.util.Map;
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

@Controller
@RequestMapping("/yggl")
public class BackController {

	Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private AffairsService affairsService;

	@RequestMapping("/homepage")
	public ModelAndView homePage(Model model, String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		affairsService.getUsername(cname);
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
	 * 员工信息
	 *
	 * @return
	 */
	@RequestMapping("/zwygxx")
	public ModelAndView zwygxx() {
		return new ModelAndView("/zwygxx");
	}

	/**
	 * 添加员工信息界面
	 *
	 * @return
	 */
	@RequestMapping("/addEmplInfo")
	public ModelAndView addEmplInfo() {
		return new ModelAndView("addEmplInfo");
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
	 * 获取未评测员工信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("emplinfor")
	public List<Map<String, Object>> emplInfor() {
		List<Map<String, Object>> list = affairsService.selectAll();
		System.out.println(list);
		return list;
	}

	/**
	 * 获取已评在外员工信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getYpzaygInfo")
	public List<Map<String, Object>> getYpzaygInfo() {
		List<Map<String, Object>> list = affairsService.getYpzaygInfo();
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
