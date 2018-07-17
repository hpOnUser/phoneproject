package hust.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.PlaneMapper;
import hust.phone.mapper.pojo.Plane;
import hust.phone.service.interFace.planeService;

@Service
public class planeServiceImpl implements planeService {

	@Autowired 
	PlaneMapper planemapper;
	
	@Override
	public Plane getPlaneByPlane(Plane plane) {
		
		
		return planemapper.selectPlaneByPlane(plane);
	}

}
