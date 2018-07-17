package hust.phone.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hust.phone.mapper.pojo.Plane;
import hust.phone.service.interFace.planeService;
import hust.phone.utils.JsonUtils;
import hust.phone.web.controller.vo.PlaneVo;

@Controller
public class PlaneController {
	
	@Autowired
	planeService planeServiceImpl;
	
	//获取无人机实时位置
	@RequestMapping(value="/getlocation",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getlocation(Plane plane) {
		
		Plane plane2 = planeServiceImpl.getPlaneByPlane(plane);
		PlaneVo planevo = new PlaneVo(plane2);
		
		return JsonUtils.objectToJson(planevo);	
	}
	
	
	

}
