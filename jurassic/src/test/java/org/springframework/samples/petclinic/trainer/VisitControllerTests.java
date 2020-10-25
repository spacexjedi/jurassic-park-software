package org.springframework.samples.petclinic.trainer;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.owner.Animal;
import org.springframework.samples.petclinic.owner.AnimalRepository;
import org.springframework.samples.petclinic.owner.VisitController;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*
* @spacexjedi
*/

/* 
* Test class {@link VisitController}
*/

@RunWith(SpringRunner.class)
@WebMvcTest(VisitController.class)
public class VisitControllerTests {

	private static final int TEST_ANIMAL_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VisitRepository visits;

	@MockBean
	private AnimalRepository animals;

	@Before
	public void init() {

		given(this.animals.findById(TEST_ANIMAL_ID)).willReturn(new Animal());

	}

	@Test
    public void testInitNewVisitForm() throws Exception {
        mockMvc.perform(get("/trainer/*/animal/{animalId}/visits/new", TEST_ANIMAL_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("animal/createOrUpdateVisitForm"));
    }

    @Test
    public void testProcessNewVisitFormSuccess() throws Exception {
        mockMvc.perform(post("/trainer/*/animal/{animalId}/visits/new", TEST_ANIMAL_ID)
            .param("name", "Owen")
            .param("description", "Visit Description")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/trainer/{trainerId}"));
    }

    @Test
    public void testProcessNewVisitFormHasErrors() throws Exception {
        mockMvc.perform(post("/trainer/*/animal/{animalId}/visits/new", TEST_ANIMAL_ID)
            .param("name", "Owen")
        )
            .andExpect(model().attributeHasErrors("visit"))
            .andExpect(status().isOk())
            .andExpect(view().name("animal/createOrUpdateVisitForm"));
    }

}