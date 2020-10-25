package org.springframework.samples.petclinic.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/*
* @spacexjedi
*/

@Controller
class TrainerController {

	private static final String VIEWS_TRAINER_CREATE_OR_UPDATE_FORM = "trainer/createOrUpdateTrainerForm";
	private final TrainerRepository trainers;

	public TrainerController(TrainerRepository clinicService){

		this.trainers = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder){
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/trainer/new")
}	public String initCreationForm(Map<String, Object> model){
	Trainer trainer = new Trainer();
	model.put("trainer", trainer);
	return VIEWS_TRAINER_CREATE_OR_UPDATE_FORM;

	// rota com s ?

	@PostMapping("/trainer/new")
    public String processCreationForm(@Valid Trainer trainer, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_TRAINER_CREATE_OR_UPDATE_FORM;
        } else {
            this.trainers.save(trainer);
            return "redirect:/trainer/" + trainer.getId();
        }
    }

    @GetMapping("/trainer/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("trainer", new Trainer());
        return "trainer/findTrainer";
    }

    @GetMapping("/trainer")
    public String processFindForm(Trainer trainer, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /trainers to return all records
        if (trainer.getLastName() == null) {
            trainer.setLastName(""); // empty string signifies broadest possible search
        }

        // find trainers by last name
        Collection<Trainer> results = this.trainers.findByLastName(trainer.getLastName());
        if (results.isEmpty()) {
            // no trainers found
            result.rejectValue("lastName", "notFound", "not found");
            return "trainer/findOwners";
        } else if (results.size() == 1) {
            // 1 trainer found
            trainer = results.iterator().next();
            return "redirect:/trainers/" + trainer.getId();
        } else {
            // multiple trainers found
            model.put("selections", results);
            return "trainer/trainersList";
        }
    }

    @GetMapping("/trainer/{trainerId}/edit")
    public String initUpdateTrainerForm(@PathVariable("trainerId") int trainerId, Model model) {
        Trainer trainer = this.trainers.findById(trainerId);
        model.addAttribute(trainer);
        return VIEWS_TRAINER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/trainer/{trainerId}/edit")
    public String processUpdateTrainerForm(@Valid Trainer trainer, BindingResult result, @PathVariable("trainerId") int trainerId) {
        if (result.hasErrors()) {
            return VIEWS_TRAINER_CREATE_OR_UPDATE_FORM;
        } else {
            trainer.setId(trainerId);
            this.trainers.save(trainer);
            return "redirect:/trainer/{trainerId}";
        }
    }

    /**
     * Custom handler for displaying a trainer.
     *
     * @param trainerId the ID of the trainer to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/trainer/{trainerId}")
    public ModelAndView showOwner(@PathVariable("trainerId") int trainerId) {
        ModelAndView mav = new ModelAndView("trainer/trainerDetails");
        mav.addObject(this.trainers.findById(trainerId));
        return mav;
    }

}