package hust.plane.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hust.plane.mapper.mapper.RouteMapper;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;
import hust.plane.utils.LineUtil;
import hust.plane.utils.pojo.RouteExcel;

public class FileServiceImpl implements FileService {
	
	@Autowired
	private RouteMapper routeMapper;
	//插入路由数据
	@Override
	public void insertRoute(String path,Route route) {
		List<RouteExcel> readExcel = ExcelUtil.readExcel(path);
		Route r=new Route();
		//读取编号
		
		//构成经纬度序列
		String s=LineUtil.ListToString(readExcel);
		
		//读取描述
		r.setDescripte(route.getDescripte());
		//设置创建时间
		Date date=new Date();
		r.setCreateTime(date);
		
		
		
	}

}
