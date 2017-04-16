package cn.yfc.aone.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.ImportExcelUtil;
import cn.yfc.aone.beans.Affairs;
import cn.yfc.aone.beans.InfoVo;
import cn.yfc.aone.beans.NoticeBean;
import cn.yfc.aone.service.AffairsService;

@Controller
@RequestMapping("/yggl")
public class BackController {

	
	Log log = LogFactory.getLog( this .getClass());
	
	@Autowired
	private AffairsService affairsService;
	
	
	@RequestMapping("/homepage")
	 public ModelAndView homePage() {
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
	 * 获取地图经纬度并先死到地图上
	 * @param model
	 * @param account 员工姓名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/map")
    public ModelAndView map(ModelMap model,String account) throws Exception {
		String cname = new String(account.getBytes("ISO8859-1"), "UTF-8");
		Map<String, Object > map = affairsService.getMapjw(cname);
		String j = (String) map.get("LONGITUDE");
		String w = (String) map.get("LATITUDE");
		model.addAttribute("j",j );
		model.addAttribute("w",w );
        return new ModelAndView("map");
    }
	
	@ResponseBody
	@RequestMapping("emplinfor")
	public List<Affairs> emplInfor(){
		List<Affairs> list = affairsService.selectAll();
		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		System.out.println("s====================="+list);
		return list;
	}
	
/**-------=++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=================================*/

   
    
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
