package org.springframework.samples.animalclinic.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/*
* @spacexjedi
*/

@Controller
@RequestMappinh("/trainer/{trainerId}")
class AnimalController {

	private static final String VIEWS_ANIMALS_CREATE_OR_UPDATE_FORM = "animal/createOrUpdateAnimalForm";
    private final AnimalRepository animals;
    private final TrainerRepository trainers;

    public AnimalController(AnimalRepository animals, TrainerRepository trainers) {
        this.animals = animals;
        this.trainers = trainers;
    }

    @ModelAttribute("types")
    public Collection<AnimalType> populateAnimalTypes() {
        return this.animals.findAnimalTypes();
    }

    @ModelAttribute("trainer")
    public Trainer findTrainer(@PathVariable("trainerId") int trainerId) {
        return this.trainers.findById(trainerId);
    }

    @InitBinder("trainer")
    public void initTrainerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("animal")
    public void initAnimalBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new AnimalValidator());
    }

    @GetMapping("/animal/new")
    public String initCreationForm(Trainer trainer, ModelMap model) {
        Animal animal = new Animal();
        trainer.addAnimal(animal);
        model.put("animal", animal);
        return VIEWS_ANIMALS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/animal/new")
    public String processCreationForm(Trainer trainer, @Valid Animal animal, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(animal.getName()) && animal.isNew() && trainer.getAnimal(animal.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        trainer.addAnimal(animal);
        if (result.hasErrors()) {
            model.put("animal", animal);
            return VIEWS_ANIMALS_CREATE_OR_UPDATE_FORM;
        } else {
            this.animals.save(animal);
            return "redirect:/trainer/{trainerId}";
        }
    }

    @GetMapping("/animals/{animalId}/edit")
    public String initUpdateForm(@PathVariable("animalId") int animalId, ModelMap model) {
        Animal animal = this.animals.findById(animalId);
        model.put("animal", animal);
        return VIEWS_ANIMALS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/animals/{animalId}/edit")
    public String processUpdateForm(@Valid Animal animal, BindingResult result, Trainer trainer, ModelMap model) {
        if (result.hasErrors()) {
            animal.setTrainer(trainer);
            model.put("animal", animal);
            return VIEWS_ANIMALS_CREATE_OR_UPDATE_FORM;
        } else {
            trainer.addAnimal(animal);
            this.animals.save(animal);
            return "redirect:/trainer/{trainerId}";
        }
    }



}