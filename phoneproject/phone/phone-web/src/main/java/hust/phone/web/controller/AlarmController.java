package hust.phone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlarmController {

	@RequestMapping("/alermList")
	public String toTaskList() {
		return "alermList";
	}
	

}
