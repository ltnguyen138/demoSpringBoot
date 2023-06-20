package com.QuanLyCuaHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.service.HangHoaService;
import com.QuanLyCuaHang.service.NCCService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HangHoaController {

	@Autowired
	HangHoaService hanghoaservice;
	@Autowired
	NCCService nccservice;
	
	@GetMapping("/hanghoa/list")
	public ModelAndView getAllHangHoa(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<HangHoa> list=hanghoaservice.getAllHangHoa();
		ModelAndView m=new ModelAndView();
		m.setViewName("hanghoaListView");
		m.addObject("hanghoa",list);
		return m;
	}
	@GetMapping("/hanghoa/add")
	public String addHangHoa(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<NCC> ncc= nccservice.getAllNCC();
		model.addAttribute("ncc", ncc);
		return "hanghoaCreateView";
	}
	@PostMapping("hanghoa/add")
	public String addHangHoa(@ModelAttribute HangHoa hanghoa) {

		hanghoaservice.addHangHoa(hanghoa);
		return "redirect:/hanghoa/list";
	}
	@GetMapping("/hanghoa/edit/{mahh}")
	public String editHangHoa(@PathVariable("mahh") long mahh, Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<NCC> ncc= nccservice.getAllNCC();
		model.addAttribute("ncc", ncc);
		HangHoa hanghoa=hanghoaservice.getHangHoaByMahh(mahh);
		model.addAttribute("hanghoa", hanghoa);
		return "editHangHoaView";
	}
	@PostMapping("/hanghoa/edit")
	public String editHangHoa(@ModelAttribute HangHoa hanghoa) {
		hanghoaservice.UpdateHangHoa(hanghoa);
		return "redirect:/hanghoa/list";
	}
	@GetMapping("/hanghoa/delete/{mahh}")
	public String deleteHangHoa(@PathVariable("mahh") long mahh) {
		hanghoaservice.DeleteHangHoa(mahh);
		return "redirect:/hanghoa/list";
	}
	@GetMapping("hanghoa/search")
	public ModelAndView searchHangHoa(@RequestParam("search") String search, Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<HangHoa> list=hanghoaservice.searchHH(search);
		ModelAndView m=new ModelAndView();
		m.setViewName("hanghoaListView");
		m.addObject("hanghoa",list);
		return m;
	}
}
