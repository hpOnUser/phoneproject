package hust.phone.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.phone.mapper.mapper.PlanePathMapper;
import hust.phone.mapper.pojo.PlanePath;
import hust.phone.service.interFace.PlanePathService;

@Service
public class PlanePathServiceImpl implements PlanePathService {

	@Autowired
	private PlanePathMapper planePathMapper;


	// 插入一条飞行路径
	@Override
	public boolean insertPlanePath(PlanePath planePath) {

		planePath.setPlongda("LINESTRING" + planePath.getPlongda());
		Date date = new Date();
		planePath.setCreatetime(date);
		planePath.setUpdatetime(date);

		// 然后在下面进行插入数据
		planePathMapper.insertPlanePath(planePath);
		return true;
	}

	@Override
	public PlanePath selectByPlanepathId(PlanePath planePath) {
		PlanePath path = planePathMapper.selectByPlanepathId(planePath);
		return path;
	}

	
	@Override
	public List<PlanePath> findAllplanePath() {
		List<PlanePath> planePaths = planePathMapper.findAllplanePath();
		return planePaths;
	}

}
