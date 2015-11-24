package com.academia.appForum.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.academia.appForum.model.UsersEntity;
import com.academia.appForum.service.MessageService;
import com.academia.appForum.service.UsersService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

	@InjectMocks
	private UsersController usersController;

	@Mock
	private UsersService usersService;

	@Mock
	private ServletContext servletContext;

	@Mock
	private MessageService messageService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
	}

	@Test
	public void testLoginWithParamError() {
		final ModelAndView modelAndView = usersController.login("error", null);

		Assert.assertNotNull(modelAndView);
		Assert.assertEquals("index", modelAndView.getViewName());
		Assert.assertTrue(modelAndView.getModelMap().containsAttribute("error"));
		Assert.assertFalse(modelAndView.getModelMap().containsAttribute("msg"));
	}

	@Test
	public void testLoginWithParamLogout() {
		final ModelAndView modelAndView = usersController.login(null, "login");

		Assert.assertNotNull(modelAndView);
		Assert.assertEquals("index", modelAndView.getViewName());
		Assert.assertFalse(modelAndView.getModelMap().containsAttribute("error"));
		Assert.assertTrue(modelAndView.getModelMap().containsAttribute("msg"));
	}

	@Test
	public void testLoginWithParamsLogoutAndError() {
		final ModelAndView modelAndView = usersController.login("error", "login");

		Assert.assertNotNull(modelAndView);
		Assert.assertEquals("index", modelAndView.getViewName());
		Assert.assertTrue(modelAndView.getModelMap().containsAttribute("error"));
		Assert.assertTrue(modelAndView.getModelMap().containsAttribute("msg"));
	}

	/**
	 * create new user success giving a valid userEntity
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateNewUserSuccessGivingValidUserEntity() throws Exception {
		Mockito.doNothing().when(usersService).addNewUser(Matchers.any(UsersEntity.class));
		Mockito.doNothing().when(usersService).addNewUserRole(Matchers.any(UsersEntity.class));

		final String successMsg = "User username was created successfully!";

		final File file = new File(
				"C:\\Users\\ruben.valderrabano\\git\\FinalAcademia\\src\\main\\webapp\\WEB-INF\\resources\\img\\user.png");

		final FileInputStream inputStream = new FileInputStream(file);

		final MockMultipartFile mockMultipartFile = new MockMultipartFile("fileData", inputStream);

		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/registerUser").file(mockMultipartFile)
				.param("name", "MyName").param("lastName", "lastName").param("username", "username")
				.param("password", "pass132").param("email", "email@mail.com"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(view().name("index")).andExpect(model().attribute("registerSuccessMsg", successMsg))
				.andExpect(model().attributeDoesNotExist("user"));
	}

	/**
	 * create new user success giving a Invalid userEntity
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateNewUserSuccessGivingInvalidUserEntity() throws Exception {
		Mockito.doNothing().when(usersService).addNewUser(Matchers.any(UsersEntity.class));
		Mockito.doNothing().when(usersService).addNewUserRole(Matchers.any(UsersEntity.class));

		final File file = new File(
				"C:\\Users\\ruben.valderrabano\\git\\FinalAcademia\\src\\main\\webapp\\WEB-INF\\resources\\img\\user.png");

		final FileInputStream inputStream = new FileInputStream(file);

		final MockMultipartFile mockMultipartFile = new MockMultipartFile("fileData", inputStream);

		final UsersEntity usersEntity = new UsersEntity();
		usersEntity.setEmail("email@mail.com");
		usersEntity.setName("MyName");
		usersEntity.setPassword("pa$ss132");
		usersEntity.setUsername("user$name");

		mockMvc.perform(
				MockMvcRequestBuilders.fileUpload("/registerUser").file(mockMultipartFile).param("name", "MyName")
						.param("username", "user$name").param("password", "pa$ss132").param("email", "email@mail.com"))
				.andDo(MockMvcResultHandlers.print()).andExpect(view().name("users"))
				.andExpect(model().attribute("user", usersEntity))
				.andExpect(model().attributeDoesNotExist("registerSuccessMsg")).andReturn();

	}

}
