package com.QuanLyCuaHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.service.NCCService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NCCController {
	@Autowired
	NCCService nccservice;
	
	@GetMapping("/ncc/list")
	public ModelAndView getAllNcc(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<NCC> list=nccservice.getAllNCC();
		ModelAndView m =new ModelAndView();
		m.setViewName("nccListView");
		m.addObject("ncc",list);
		return m;
	}
	
	@GetMapping("/ncc/add")
	public String addNcc(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		return "nccCreateView";
	}
	
	@PostMapping("/ncc/add")
	public String addNcc(@ModelAttribute NCC ncc) {
		nccservice.addNCC(ncc);
		return "redirect:/ncc/list";
	}
	
	@GetMapping("/ncc/edit/{mancc}")
	public String editNcc(@PathVariable("mancc") long mancc,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		NCC ncc = nccservice.getNCCByMancc(mancc);
		model.addAttribute("ncc",ncc);
		return "editNCCView";
	}
	@PostMapping("/ncc/edit")
	public String editNcc(@ModelAttribute NCC ncc) {
		nccservice.UpdateNCC(ncc);
		return "redirect:/ncc/list";
	}
	@GetMapping("/ncc/delete/{mancc}")
	public String deleteNcc(@PathVariable("mancc") long mancc) {
		nccservice.DeleteNCC(mancc);
		
		return "redirect:/ncc/list";
	}
	@GetMapping("ncc/search")
	public ModelAndView searchNCC(@RequestParam("search") String search, Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<NCC> list=nccservice.searchNCC(search);
		ModelAndView m =new ModelAndView();
		m.setViewName("nccListView");
		m.addObject("ncc",list);
		return m;
	}
}
