package com.academia.appForum.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academia.appForum.model.UsersEntity;
import com.academia.appForum.service.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ServletContext servletContext;

	private static final String UPLOAD_DIRECTORY = "appacademy";

	@RequestMapping(value = { "/login", "index**", "/" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		final ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("index");

		return model;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView errorPage() {

		final ModelAndView model = new ModelAndView();
		model.setViewName("403");

		return model;

	}

	@RequestMapping(value = "/users**", method = RequestMethod.GET)
	public ModelAndView usersPage() {

		final ModelAndView model = new ModelAndView();
		model.setViewName("users");
		model.addObject("user", new UsersEntity());

		return model;

	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UsersEntity user, BindingResult bindingResult, HttpServletRequest request,
			ModelAndView model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addObject("user", user);
			model.setViewName("users");
			return model;
		}

		// >Upload file<
		servletContext = request.getSession().getServletContext();
		final String path = servletContext.getRealPath("");
		final String uploadPath = path + "\\" + UPLOAD_DIRECTORY;

		System.out.println("> Upoload Path > " + uploadPath);

		final File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		// file processing
		final String fileName = new File(user.getFileData().getOriginalFilename()).getName();

		// Upload file
		if (!user.getFileData().isEmpty() || isAllowedExtension(fileName, new String[] { "png", "jpg" })) {
			final String filePath = uploadPath + "/" + fileName;
			final File storeFile = new File(filePath);

			if (!filePath.contains("\\test-classes")) {
				user.getFileData().transferTo(storeFile);
			}

			user.setImageUrl(filePath);
		} else {
			user.setImageUrl(
					"C:\\Users\\ruben.valderrabano\\git\\FinalAcademia\\src\\main\\webapp\\WEB-INF\\resources\\img\\user.png");
		}

		usersService.addNewUser(user);
		usersService.addNewUserRole(user);

		final String successMsg = "User " + user.getUsername() + " was created successfully!";

		model.setViewName("index");
		model.addObject("registerSuccessMsg", successMsg);

		return model;
	}

	private boolean isAllowedExtension(String fileName, String[] extensions) {

		for (final String extension : extensions) {
			if (FilenameUtils.isExtension(fileName.toLowerCase(), extension)) {
				return true;
			}
		}
		return false;

	}
}
