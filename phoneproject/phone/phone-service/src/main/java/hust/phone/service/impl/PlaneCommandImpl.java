package hust.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.PlaneMapper;
import hust.phone.mapper.pojo.Plane;


@Service
public class PlaneCommandImpl {
	
	@Autowired
	private PlaneMapper planeMapper;

	public  void updateById(Plane p) {
		planeMapper.updateByPlane(p);
		
	}

}
