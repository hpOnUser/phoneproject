package hust.phone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	
	@RequestMapping("/myindex")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping("/toPlane")
	public String toPlane()
	{
		return "fight";
	}
	
	@RequestMapping("/webtest")
	public String webtest(Model model)
	{
		return "webtest";
	}


}
