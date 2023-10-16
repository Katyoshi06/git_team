package com.nexus.whc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nexus.whc.models.Filelink;
import com.nexus.whc.services.TopService;

@Controller
public class TopController {
	
	@Autowired
	private TopService topService;

	@GetMapping("/SCMCM001")
	public String topList(Model model) {
		
		List<Filelink> files = topService.getAllFiles();
		
		model.addAttribute("files", files);
		
		return "SCMCM001";
	}
}
