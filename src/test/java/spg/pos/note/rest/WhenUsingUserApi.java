package spg.pos.note.rest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import spg.pos.note.rest.RestApplicationConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = RestApplicationConfig.class)
@WebAppConfiguration
public class WhenUsingUserApi {
	@Resource
	private WebApplicationContext webApplicationContext;

	@Test
	public void ensureThatAtTheBeginningAnEmptyListIsRetrieved() throws Exception {
		MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();

		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}

	@Test
	public void ensureThatInsertingANewUserWorks() throws Exception {

		MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();

		mockMvc.perform(
				post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(
								"{\"username\":\"Corey\", \"emailAddress\":\"corey.taylor@slpnt.com\"," +
						"\"firstname\":\"Corey\",\"lastname\",\"birthday\":\"1973-08-12\",\"password\":\"slipknot\"}"))
				.andExpect(status().isCreated());
	}
}
