package hust.phone.service.interFace;

import hust.phone.mapper.pojo.Plane;

public interface planeService {

	Plane getPlaneByPlane(Plane plane);

	void takeoff(String planeid);
	
	void showData(String planeid);
	
	void planeLand(String planeid);
	
	void planeReturn(String planeid);
	
	void checkConnect(String planeid);
}
