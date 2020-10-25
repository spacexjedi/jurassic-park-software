package org.springframework.samples.petclinic.researcher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/*
* @spacexjedi
*/

@Controller
Class ResearcherController {

	private final ResearcherRepository reseachers;

	public ResearcherController(ResearcherRepository clinicService) {

		this.reseachers = clinicService;
	}

	@GetMapping("/researcher.html")
	public String showReseacherList(Map<String, Object> model){
		Reseachers reseachers = new Reseachers();
		reseachers.getResearchList().addAll(this.reseachers.findAll());
		model.put("reseachers", reseachers);
		return "reseachers/ReseacherList";
	}

	@GetMapping({"/reseachers"})
	public @ResponseBody Reseachers showResourcesResearchList() {
		Reseachers reseachers = new Reseachers();
		reseachers.getResearchList().addAll(this.reseachers.findAll());
		return reseachers;
	}


}