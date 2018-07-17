package hust.phone.service.interFace;

import java.util.List;

import hust.phone.mapper.pojo.PlanePath;



public interface PlanePathService {

    boolean insertPlanePath(PlanePath planePath);

    PlanePath selectByPlanepathId(PlanePath planePath);

	List<PlanePath> findAllplanePath();
}
