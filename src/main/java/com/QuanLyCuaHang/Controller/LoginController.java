package com.QuanLyCuaHang.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuanLyCuaHang.entity.NhanVien;
import com.QuanLyCuaHang.service.NhanVienService;

@Controller
public class LoginController {
	
	@Autowired
	NhanVienService nhanvienservice;
	
	@GetMapping("/login")
	public String Login() {
		return "Login";
	}
	@PostMapping("/login")
	public String Login(@RequestParam("username") String username,@RequestParam("password") String password, HttpSession session, Model model) {
		
		if (nhanvienservice.CheckUsernamePasswork(username, password)) {
			NhanVien nhanvien = nhanvienservice.getUsernamePasswork(username, password);
			String errorString="";
			model.addAttribute("errorString",errorString);
			session.setAttribute("nhanvien", nhanvien);
			session.setAttribute("manv", nhanvien.getManv());
			session.setAttribute("role", nhanvien.getVaitro());
			session.setAttribute("username", nhanvien.getUsername());
			
			return "redirect:/hanghoa/list";
		}
		String errorString="username hoặc mặt khẩu không đúng";
		model.addAttribute("errorString",errorString);
		return "login";
	}
	@GetMapping("/thongbao")
	public String thongbao() {
		return "/thongbao";
	}
}
