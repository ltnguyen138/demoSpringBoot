package com.QuanLyCuaHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.QuanLyCuaHang.entity.NhanVien;
import com.QuanLyCuaHang.service.NhanVienService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NhanVienController {

		@Autowired
		NhanVienService nhanvienservice;
		
		@GetMapping("/nhanvien/list")
		public ModelAndView getAllNhanVien(Model model,HttpSession session) {
			model.addAttribute("username", session.getAttribute("username"));
			List<NhanVien> list=nhanvienservice.getAllNhanVien();
			NhanVien ql=new NhanVien();
			for (NhanVien nhanVien : list) {
				if (nhanVien.getVaitro().equals("QL")) {
					ql=nhanVien;
					
					list.remove(nhanVien);
					break;
				}
			}
			model.addAttribute("ql", ql);
			ModelAndView m=new ModelAndView();
			m.setViewName("nhanvienListView");
			m.addObject("nhanvien",list);
			return m;
		}
		@GetMapping("/nhanvien/add")
		public String addNhanVien(Model model,HttpSession session) {
			model.addAttribute("username", session.getAttribute("username"));
			return "nhanvienCreateView";
		}
		@PostMapping("/nhanvien/add")
		public String addNhanVien(@ModelAttribute NhanVien nhanvien) {
			nhanvienservice.addNhanVien(nhanvien);
			return "redirect:/nhanvien/list";
		}
		@GetMapping("/nhanvien/edit/{manv}")
		public String editNhanVien(@PathVariable("manv") int manv,Model model,HttpSession session) {
			model.addAttribute("username", session.getAttribute("username"));
			NhanVien nhanvien = nhanvienservice.getNhanVienByManv(manv);
			model.addAttribute("nhanvien",nhanvien);
			return "editNhanVienView";
		}
		@PostMapping("/nhanvien/edit")
		public String editNhanVien(@ModelAttribute NhanVien nhanvien) {
			nhanvienservice.UpdateNhanVien(nhanvien);
			return "redirect:/nhanvien/list";
		}
		@GetMapping("/nhanvien/delete/{manv}")
		public String deleteNhanVien(@PathVariable("manv") int manv) {
			nhanvienservice.DeleteNhanVien(manv);
			return "redirect:/nhanvien/list";
		}
		@GetMapping("/ttcn")
		public String ttcn(Model model,HttpSession session) {
			int manv=(int) session.getAttribute("manv");
			NhanVien nhanvien=nhanvienservice.getNhanVienByManv(manv);
			model.addAttribute("nhanvien", nhanvien);
			return "ttcnView";
		}
		
		@PostMapping("/ttcn")
		public String ttcn(Model model,HttpSession session,@ModelAttribute NhanVien nhanvien){
			nhanvienservice.UpdateNhanVien(nhanvien);
			
			return "redirect:/ttcn";
			
		}
		@GetMapping("nhanvien/search")
		public ModelAndView searchNhanVien(@RequestParam("search") String search,Model model,HttpSession session) {
			model.addAttribute("username", session.getAttribute("username"));
			List<NhanVien> list=nhanvienservice.searchNhanVien(search);
			NhanVien ql=new NhanVien();
			for (NhanVien nhanVien : list) {
				if (nhanVien.getVaitro().equals("QL")) {
					ql=nhanVien;
					
					list.remove(nhanVien);
				}
			}
			model.addAttribute("ql", ql);
			ModelAndView m=new ModelAndView();
			m.setViewName("nhanvienListView");
			m.addObject("nhanvien",list);
			return m;
		}
		
}
