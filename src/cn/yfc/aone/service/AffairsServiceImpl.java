package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.beans.Affairs;
import cn.yfc.aone.dao.AffairsDao;

@Service
public class AffairsServiceImpl implements AffairsService{

	@Autowired
	private AffairsDao affairsDao;
	
	@Override
	public List<Affairs> selectAll() {
		List<Affairs> list = affairsDao.selectAll();
		return list;
	}

}
