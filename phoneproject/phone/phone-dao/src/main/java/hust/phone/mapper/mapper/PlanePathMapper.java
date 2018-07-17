package hust.phone.mapper.mapper;


import java.util.List;

import hust.phone.mapper.pojo.PlanePath;

public interface PlanePathMapper {
	
	 PlanePath selectByPlanePathVo(PlanePath planepath);

	 void insertPlanePath();

	 void insertPlanePath(PlanePath planePath);

	 PlanePath selectByPlanepathId(PlanePath planepath);

	int planePathCount(PlanePath planePath);

	List<PlanePath> findAllplanePath(); 
}
