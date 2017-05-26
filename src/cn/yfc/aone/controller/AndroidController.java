package cn.yfc.aone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yfc.aone.service.AndroidService;
import cn.yfc.aone.service.EmployeeService;
import cn.yfc.aone.service.LoginService;

@Controller
@RequestMapping(value = "/android")
public class AndroidController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AndroidService androidService;
	
	@RequestMapping(value ="/android", method=RequestMethod.POST)
	 @ResponseBody
	public Map<String, Object> model(String username,String password, String role) {
		
		System.out.println("texts======"+username+"edits==========="+password+"role=========="+role);
		Map<String, Object> map2 = loginService.getLogContent(username, password, role);
		Map<String, Object> map = new HashMap<>();
		if(map2!=null){
			map.put("result_code", 0 );
			return map;
		}else {
			map.put("result_code", 1);
			return map;
		}
			
		
	}
	/**
	 * 获取未完成员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getZwygInfo")
	public List<Map<String, Object>> getZwygInfo(String username) throws Exception {
		System.out.println("usernam ======="+username);
		List<Map<String, Object>> list = androidService.getZwygInfo(username);
		return list;
	}
	
	/**
	 * 获取个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getGrzwxxInfo")
	public List<Map<String, Object>> getGrzwxxInfo(String username) throws Exception {
		System.out.println("usernam ======="+username);
		List<Map<String, Object>> list = androidService.getGrzwxxInfo(username);
		System.out.println("list==="+list);
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
	public Map<String , Object> addData(String account,String affair,String locale,String starttime,String endtime,String manager,String username) throws Exception {
		System.out.println("数据"+account+affair+locale+starttime+endtime+manager);
		int i = androidService.addData(account,affair,locale,starttime,endtime,manager,username);
		Map<String, Object> map = new HashMap<>();
		if(i==1){
			map.put("result_code", 0 );
			return map;
		}else {
			map.put("result_code", 1);
			return map;
		}
	}
	
	/**
	 * 添加日志信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addRzInfo")
	public Map<String , Object> addRzInfo(String log,String log_account,String log_affair,String username) throws Exception {
		System.out.println("数据"+log+log_account+log_affair+username);
		int i = androidService.addRzInfo(log,log_account,log_affair,username);
		Map<String, Object> map = new HashMap<>();
		if(i==1){
			map.put("result_code", 0 );
			return map;
		}else {
			map.put("result_code", 1);
			return map;
		}
	}
	/**
	 * 添加经纬度
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addMapInfo")
	public Map<String , Object> addMapInfo(String lontitude,String latitude,String time,String username) throws Exception {
		System.out.println("数据"+lontitude+"++++++++++"+latitude+"+++++++++"+time+"++++++"+username);
		androidService.addMapInfo(lontitude,latitude,time,username);
		Map<String , Object> map = new HashMap<>();
		map.put("sss", "sss");
		return map;
	}

}
