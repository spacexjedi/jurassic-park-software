package org.springframework.samples.petclinic.trainer;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.trainer.Trainer;
import org.springframework.samples.petclinic.trainer.TrainerRepository;
import org.springframework.samples.petclinic.trainer.Animal;
import org.springframework.samples.petclinic.trainer.AnimalController;
import org.springframework.samples.petclinic.trainer.AnimalRepository;
import org.springframework.samples.petclinic.trainer.AnimalType;
import org.springframework.samples.petclinic.trainer.AnimalTypeFormatter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/* 
* @spacexjedi
*/

/*
* Test class {@link AnimalController}
*/

@RunWith(SpringRunner.class)
@WebMvcTest(value = AnimalController.class, 
	includeFilters = @ComponentScan.Filter(
						value = AnimalTypeFormatter.class,
						type = FilterType.ASSIGNABLE_TYPE))


public class AnimalControllerTests {

	private static final int TEST_TRAINER_ID = 1;
	private static final int TEST_ANIMAL_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnimalRepository animals;

	@MockBean
	private TrainerRepository trainers;

	@Before
	public void setup() {
		AnimalType chicken = new AnimalType();
		chicken.setId(3);
		chicken.setName("clotilde");
		given(this.animals.findAnimalsTypes()).willReturn(Lists.newArrayList(chicken));
		given(this.trainers.findById(TEST_TRAINER_ID)).willReturn(new Trainer());
		given(this.animals.findById(TEST_ANIMAL_ID)).willReturn(new Animal());

	}

	  @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/trainer/{trainerId}/animal/new", TEST_TRAINER_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("animal/createOrUpdateAnimalForm"))
            .andExpect(model().attributeExists("animal"));
    }

    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/trainer/{trainerId}/animal/new", TEST_TRAINER_ID)
            .param("name", "Clotilde")
            .param("type", "chicken")
            .param("birthDate", "2020-02-02")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/trainer/{trainerId}"));
    }

    @Test
    public void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/trainer/{trainerId}/animal/new", TEST_TRAINER_ID)
            .param("name", "Clotilde")
            .param("birthDate", "2020-02-02")
        )
            .andExpect(model().attributeHasNoErrors("trainer"))
            .andExpect(model().attributeHasErrors("animal"))
            .andExpect(model().attributeHasFieldErrors("animal", "type"))
            .andExpect(model().attributeHasFieldErrorCode("animal", "type", "required"))
            .andExpect(status().isOk())
            .andExpect(view().name("animal/createOrUpdateAnimalForm"));
    }

    @Test
    public void testInitUpdateForm() throws Exception {
        mockMvc.perform(get("/trainer/{trainrId}/animal/{animalId}/edit", TEST_TRAINER_ID, TEST_ANIMAL_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("animal"))
            .andExpect(view().name("animal/createOrUpdateAnimalForm"));
    }

    @Test
    public void testProcessUpdateFormSuccess() throws Exception {
        mockMvc.perform(post("/trainer/{trainerId}/animal/{animalId}/edit", TEST_TRAINER_ID, TEST_ANIMAL_ID)
            .param("name", "Clotilde")
            .param("type", "chicken")
            .param("birthDate", "2020-02-02")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/trainer/{trainerId}"));
    }

    @Test
    public void testProcessUpdateFormHasErrors() throws Exception {
        mockMvc.perform(post("/trainer/{trainerId}/animal/{animalId}/edit", TEST_TRAINER_ID, TEST_ANIMAL_ID)
            .param("name", "Clotilde")
            .param("birthDate", "2020/02/02")
        )
            .andExpect(model().attributeHasNoErrors("trainer"))
            .andExpect(model().attributeHasErrors("animal"))
            .andExpect(status().isOk())
            .andExpect(view().name("animal/createOrUpdateAnimalForm"));
    }



}