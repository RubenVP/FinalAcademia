package com.academia.appForum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.academia.appForum.service.MessageService;

@ControllerAdvice
public class MessageControllerAdvice {

	@Autowired
	private MessageService messageService;

	@ModelAttribute
	public void addBugetToModel(Model model) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String username = auth.getName();

		System.out.println("---***--***--***--**--**--**---***--***--***--**--**--**");
		System.out.println(username);
		System.out.println("---***--***--***--**--**--**---***--***--***--**--**--**");

		if (!username.equals("anonymousUser")) {
			final Integer numberMessages = messageService.getNumberOfNewMessagesByUser();

			model.addAttribute("numberMessages", numberMessages);

			System.out.println("---***--***--***--**--**--**---***--***--***--**--**--**");
			System.out.println("---***--***--***--**--**--**" + numberMessages);
			System.out.println("---***--***--***--**--**--**---***--***--***--**--**--**");
		}
	}
}
