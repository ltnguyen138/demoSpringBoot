package com.QuanLyCuaHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.CTPhieuXuat;
import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.exception.MsgException;
import com.QuanLyCuaHang.service.CTPhieuXuatService;
import com.QuanLyCuaHang.service.HangHoaService;
import com.QuanLyCuaHang.service.PhieuXuatService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CTPhieuXuatController {
	@Autowired
	CTPhieuXuatService ctphieuxuatservice;
	@Autowired 
	HangHoaService hanghoaservice;
	
	@GetMapping("/ctphieuxuat/add")
	public String addCTPhieuXuat(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		Object maphieuxuatStr=null;
		long maphieuxuat =0;
		maphieuxuatStr=session.getAttribute("maphieuxuat");
		maphieuxuat= Long.parseLong((String) maphieuxuatStr);
		List<HangHoa> hanghoa=hanghoaservice.getAllHangHoa();
		
		model.addAttribute("hanghoa",hanghoa);
		model.addAttribute("maphieuxuat", maphieuxuat);
		return "ctphieuxuatCreateView";
	}
	@PostMapping("/ctphieuxuat/add")
	public String addCTPhieuXuat(HttpSession session, Model model,@ModelAttribute CTPhieuXuat ctphieuxuat) {
		System.out.println(ctphieuxuat);
		try {
			ctphieuxuatservice.addCTPhieuXuat(ctphieuxuat);
		}catch (MsgException e) {
			String msg=e.toString();
			model.addAttribute("msg",e.getMessage());
			return addCTPhieuXuat(session, model);
		}
		
		
		return "redirect:/phieuxuat/list";
	}
	@GetMapping("/ctphieuxuat/edit")
	public String editCTPhieuXuat(@RequestParam(value = "mpx", required = false) long mpx,@RequestParam(value = "mahh", required = false) long mahh,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		CTPhieuXuat ctPhieuXuat=ctphieuxuatservice.getCTPhieuXuatByMapxAndMahh(mpx, mahh);
		System.out.println(ctPhieuXuat);
		model.addAttribute("ctphieuxuat", ctPhieuXuat);
		return "editCtphieuxuatView";
	}
	@PostMapping("/ctphieuxuat/edit")
	public String editCTPhieuXuat(Model model,HttpSession session,@ModelAttribute CTPhieuXuat ctphieuxuat) {
		try {
			ctphieuxuatservice.UpdateCTPhieuXuat(ctphieuxuat);
		} catch (MsgException e) {
			String msg=e.toString();
			model.addAttribute("msg",e.getMessage());
			return editCTPhieuXuat(ctphieuxuat.getMaphieuxuat(), ctphieuxuat.getMahanghoa(), model, session);
		}
		
		return "redirect:/phieuxuat/add";
	}
	@GetMapping("/ctphieuxuat/delete")
	public String deleteCTPhieuXuat(@RequestParam(value = "mpx", required = false) long mpx,@RequestParam(value = "mahh", required = false) long mahh,Model model,HttpSession session) {
		ctphieuxuatservice.DeleteCTPhieuXuat(mpx, mahh);
		return "redirect:/phieuxuat/add";
	}
	

}
