package hust.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.PlaneMapper;
import hust.phone.mapper.pojo.Plane;
import hust.phone.service.interFace.testService;
import hust.phone.utils.HttpClientUtil;
@Service
public class testServiceImpl implements testService{
	@Value("${PLANE_URL}")
	private String PLANE_URL;
	@Value("${PROJECT_NAME}")
	private String PROJECT_NAME;
	@Value("${PLANE_TEST}")
	private String PLANE_TEST;
	@Override
	public String testCon() {

//		String url=PLANE_URL+PLANE_PORT+"/test";
		String url=PLANE_URL+PROJECT_NAME+PLANE_TEST;
		String json=HttpClientUtil.doGet(url);
		return json;
	}
	
	@Autowired
	private PlaneMapper planeMapper;
	
	public void updateTest()
	{
		 Plane p = new Plane();
		 int lon1 =2222222;
		 int lat1 =3333333;
		 int alt1=2222;
		 int sysid=2;
		 double lon =lon1/(10000000.0);
		 double lat =lat1/(10000000.0);
		 double alt =alt1/1000.0;
		 String fl ="Point("+lon+" "+lat+")";
		 p.setPlaneId(sysid+"");
		 p.setHeight(alt+"");
		 p.setFlongda(fl);
		 planeMapper.updateByPlane(p);
	}
	

}
