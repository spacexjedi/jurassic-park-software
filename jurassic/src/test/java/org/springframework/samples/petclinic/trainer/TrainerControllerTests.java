package org.springframework.samples.petclinic.trainer;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
import org.springframework.samples.petclinic.trainer.Trainer;
import org.springframework.samples.petclinic.trainer.TrainerController;
import org.springframework.samples.petclinic.trainer.TrainerRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/* 
* @spacexjedi
*/

/**
* Test class { @link TrainerController }
*/

@Runtime(SpringRunner.class)
@WebMvcTest(TrainerController.class)
pulic class TrainerControllerTests {

	private static final int TEST_OWNER_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainerRepository trainers;

	private Trainer owen;

	@Before
	public void setup() {
		owen. new Trainer();
		owen.setId(TEST_TRAINER_ID);
		owen.SetFirstName("Owen");
		owen.setLastName("Grady");
		owen.setAddres("Area 51");
		owen.setCity("Texas");
		owen.setTelephone("33333333");
		given(this.trainers.findAll(TEST_TRAINER_ID)).willReturn(owen);

	}

	@Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/trainer/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("trainer"))
            .andExpect(view().name("trainer/createOrUpdateTrainerForm"));
    }

    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/trainer/new")
            .param("firstName", "Owen")
            .param("lastName", "Grady")
            .param("address", "Area 51")
            .param("city", "Texas")
            .param("telephone", "33333333")
        )
            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/trainer/new")
            .param("firstName", "Owen")
            .param("lastName", "Grady")
            .param("city", "Texas")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("trainer"))
            .andExpect(model().attributeHasFieldErrors("trainer", "address"))
            .andExpect(model().attributeHasFieldErrors("trainer", "telephone"))
            .andExpect(view().name("trainer/createOrUpdateTrainerForm"));
    }

    @Test
    public void testInitFindForm() throws Exception {
        mockMvc.perform(get("/trainer/find"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("trainer"))
            .andExpect(view().name("trainer/findTrainers"));
    }

    @Test
    public void testProcessFindFormSuccess() throws Exception {
        given(this.trainers.findByLastName("")).willReturn(Lists.newArrayList(owen, new Owner()));
        mockMvc.perform(get("/trainer"))
            .andExpect(status().isOk())
            .andExpect(view().name("trainer/trainersList"));
    }

    @Test
    public void testProcessFindFormByLastName() throws Exception {
        given(this.trainers.findByLastName(owen.getLastName())).willReturn(Lists.newArrayList(owen));
        mockMvc.perform(get("/trainer")
            .param("lastName", "Grady")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/trainer/" + TEST_OWNER_ID));
    }

    @Test
    public void testProcessFindFormNotrainersFound() throws Exception {
        mockMvc.perform(get("/trainer")
            .param("lastName", "Unknown Surname")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("trainer", "lastName"))
            .andExpect(model().attributeHasFieldErrorCode("trainer", "lastName", "notFound"))
            .andExpect(view().name("trainer/findTrainers"));
    }

    @Test
    public void testInitUpdateTrainerForm() throws Exception {
        mockMvc.perform(get("/trainers/{trainerId}/edit", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("trainer"))
            .andExpect(model().attribute("trainer", hasProperty("lastName", is("Grady"))))
            .andExpect(model().attribute("trainer", hasProperty("firstName", is("Owen"))))
            .andExpect(model().attribute("trainer", hasProperty("address", is("Area 51"))))
            .andExpect(model().attribute("trainer", hasProperty("city", is("Texas"))))
            .andExpect(model().attribute("trainer", hasProperty("telephone", is("33333333"))))
            .andExpect(view().name("trainer/createOrUpdateTrainerForm"));
    }

    @Test
    public void testProcessUpdateOwnerFormSuccess() throws Exception {
        mockMvc.perform(post("/trainer/{TrainerId}/edit", TEST_OWNER_ID)
            .param("firstName", "Owen")
            .param("lastName", "Grady")
            .param("address", "Area 51")
            .param("city", "Texas")
            .param("telephone", "33333333")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/trainer/{TrainerId}"));
    }

    @Test
    public void testProcessUpdateTrainerFormHasErrors() throws Exception {
        mockMvc.perform(post("/trainer/{trainerId}/edit", TEST_OWNER_ID)
            .param("firstName", "Owen")
            .param("lastName", "Grady")
            .param("city", "Texas")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("trainer"))
            .andExpect(model().attributeHasFieldErrors("trainer", "address"))
            .andExpect(model().attributeHasFieldErrors("trainer", "telephone"))
            .andExpect(view().name("trainer/createOrUpdateTrainerForm"));
    }

    @Test
    public void testShowTrainer() throws Exception {
        mockMvc.perform(get("/trainer/{TrainerId}", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(model().attribute("trainer", hasProperty("lastName", is("Grady"))))
            .andExpect(model().attribute("trainer", hasProperty("firstName", is("Owen"))))
            .andExpect(model().attribute("trainer", hasProperty("address", is("Area 51"))))
            .andExpect(model().attribute("trainer", hasProperty("city", is("Texas"))))
            .andExpect(model().attribute("trainer", hasProperty("telephone", is("33333333"))))
            .andExpect(view().name("trainer/trainerDetails"));
    }



}