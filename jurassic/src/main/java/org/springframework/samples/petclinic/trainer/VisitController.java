package org.springframework.samples.petclinic.trainer;

import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/*
* @spacexjedi
 */
@Controller
class VisitController {

    private final VisitRepository visits;
    private final AnimalRepository animals;


    public VisitController(VisitRepository visits, AnimalRepository animals) {
        this.visits = visits;
        this.animals = animals;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * @param animalId
     * @return Animal
     */
    @ModelAttribute("visit")
    public Visit loadAnimalWithVisit(@PathVariable("animalId") int animalId, Map<String, Object> model) {
        Animal animal = this.animals.findById(animalId);
        model.put("animal", animal);
        Visit visit = new Visit();
        animal.addVisit(visit);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/trainer/*/animal/{animalId}/visits/new")
    public String initNewVisitForm(@PathVariable("animalId") int animalId, Map<String, Object> model) {
        return "animal/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/trainer/{trainerId}/animal/{animalId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "animal/createOrUpdateVisitForm";
        } else {
            this.visits.save(visit);
            return "redirect:/trainer/{trainerId}";
        }
    }

}
