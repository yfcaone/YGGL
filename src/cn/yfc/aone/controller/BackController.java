package cn.yfc.aone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yfc.aone.beans.Affairs;
import cn.yfc.aone.service.AffairsService;

@Controller
@RequestMapping("/yggl")
public class BackController {

	@Autowired
	private AffairsService affairsService;
	
	
	@RequestMapping("/homepage")
	 public ModelAndView homePage() {
	 return new ModelAndView("/homepage");
	 }
	
	@RequestMapping("/worktable")
	 public ModelAndView worktable() {
	 return new ModelAndView("/WorkTable");
	 }
	
	
	

    /**
     * Ա����Ϣ
     *
     * @return
     */	
	@RequestMapping("/zwygxx")
	 public ModelAndView zwygxx() {
	 return new ModelAndView("/zwygxx");
	 }
	
	
	/**
     * ���Ա����Ϣ����
     *
     * @return
     */
	@RequestMapping("/addEmplInfo")
    public ModelAndView addEmplInfo() {
        return new ModelAndView("addEmplInfo");
    }
	
	/**
     * ���Ա����Ϣ����
     *
     * @return
     */
	@RequestMapping("/map")
    public ModelAndView map() {
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
}
