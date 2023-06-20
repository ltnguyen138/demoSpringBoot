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
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.servlet.ModelAndView;

import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.service.KhachHangService;

import jakarta.servlet.http.HttpSession;

@Controller
public class KhachHangController {
	@Autowired
	KhachHangService khachhangservice;
	
	@GetMapping("/khachhang/list")
	public ModelAndView getAllKhachHang(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<KhachHang> list= khachhangservice.getAllKhachHang();
		ModelAndView m =new ModelAndView();
		m.setViewName("khachhangListView");
		m.addObject("khachhang",list);
		return m;
	}
	@GetMapping("/khachhang/add")
	public String addKhachHang(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		return "khachhangCreateView";
	}
	@PostMapping("/khachhang/add")
	public String addKhachHang(@ModelAttribute KhachHang khachhang) {
		khachhangservice.addKhachHang(khachhang);
		return "redirect:/khachhang/list";
	}
	@GetMapping("/khachhang/edit/{makh}")
	public String editKhachHang(@PathVariable("makh") long makh, Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		KhachHang khachhang =khachhangservice.getKhachHangByMakh(makh);
		model.addAttribute("khachhang",khachhang);
		return "editKhachHangView";
	}
	@PostMapping("/khachhang/edit")
	public String editKhachHang(KhachHang khachhang) {
		khachhangservice.UpdateKhachHang(khachhang);
		return "redirect:/khachhang/list";
	}
	@GetMapping("/khachhang/delete/{makh}")
	public String deleteKhachHang(@PathVariable("makh") long makh) {
		khachhangservice.DeleteKhachHang(makh);
		return "redirect:/khachhang/list";
	}
	@GetMapping("khachhang/search")
	public ModelAndView searchKhachHang(@RequestParam("search") String search,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<KhachHang> list= khachhangservice.searchKhachHang(search);
		ModelAndView m =new ModelAndView();
		m.setViewName("khachhangListView");
		m.addObject("khachhang",list);
		return m;
	}
}
