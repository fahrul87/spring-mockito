package com.fahrul.springmockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fahrul.springmockito.model.Employee;
import com.fahrul.springmockito.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringMockitoApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om=new ObjectMapper();
	
	@Before
	public void Setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void  addEmployeeTest() throws Exception  {
		Employee employee = new Employee();
		employee.setName("fahrul");
		employee.setDept("IT");
		String jsonRequest = om.writeValueAsString(employee);
		MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee").content(jsonRequest).
				contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContext, Response.class);
		Assert.assertTrue(response.isStatus() == Boolean.TRUE);
		

	}
	
	

	@Test
	public void getEmployeesTest() throws Exception {
		MvcResult result=mockMvc.perform(get("/EmployeeService/getEmployees").content(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContext, Response.class);
		Assert.assertTrue(response.isStatus()==Boolean.TRUE);
		
	}

}
