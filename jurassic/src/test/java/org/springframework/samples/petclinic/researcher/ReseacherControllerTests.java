package springframework.samples.petclinic.reseacher;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


/*
* @spacexjedi
*/

/**
* Test for {@link ReseacherController}
*/

@RunWith(SpringRunner.class)
@WebMvcTest(ReseacherController.class)
public class ReseacherControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ResearcherRepository res;

	@Before
	public void setup() {

		Reseacher alan = new Reseacher();
		alan.setFirstName("Alan");
		alan.setLastName("Grant");
		alan.setId(1);
		Reseacher ellie = new Reseacher();
		ellie.setFirstName("Ellie");
		ellie.setLastName("Sattler");
		ellie.setId(2);
		Specialty paleontology = new Specialty();
		paleontology.setId(1);
		paleontology.setName("paleontology");
		ellie.addSpecialty(paleontology);
		given(this.res.findAll()).willReturn(Lists.newArrayList(alan, ellie))

	}

	@Test
    public void testShowReseacherListHtml() throws Exception {
        mockMvc.perform(get("/reseacher.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("reseacher"))
            .andExpect(view().name("reseacher/reseacherList"));
    }

    @Test
    public void testShowResourcesReseacherList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/reseacher")
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.reseacherList[0].id").value(1));
    }


}