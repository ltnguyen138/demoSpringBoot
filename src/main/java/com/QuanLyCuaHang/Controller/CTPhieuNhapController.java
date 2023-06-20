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

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.exception.MsgException;
import com.QuanLyCuaHang.service.CTPhieuNhapService;
import com.QuanLyCuaHang.service.HangHoaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CTPhieuNhapController {

	@Autowired
	CTPhieuNhapService ctphieunhapservice;
	@Autowired
	HangHoaService hanghoaservice;
	
	@GetMapping("/ctphieunhap/add")
	public String addCTPhieuNhap(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		Object maphieunhapStr=null;
		long maphieunhap =0;
		maphieunhapStr=session.getAttribute("maphieunhap");
		maphieunhap= Long.parseLong((String) maphieunhapStr);
		List<HangHoa> hanghoa=hanghoaservice.getAllHangHoa();
		model.addAttribute("hanghoa",hanghoa);
		model.addAttribute("maphieunhap", maphieunhap);
		return "ctphieunhapCreateView";
	}
	@PostMapping("/ctphieunhap/add")
	public String addCTPhieuNhap(HttpSession session, Model model,@ModelAttribute CTPhieuNhap ctphieunhap) {
		System.out.println(ctphieunhap);
		ctphieunhapservice.addCTPhieuNhap(ctphieunhap);
		
		return "redirect:/phieunhap/add";
	}
	@GetMapping("/ctphieunhap/edit")
	public String editCTPhieuNhap(@RequestParam(value = "mpn", required = false) long mpn,@RequestParam(value = "mahh", required = false) long mahh,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		CTPhieuNhap ctPhieuNhap=ctphieunhapservice.getCTPhieuNhapByMapnAndMahh(mpn, mahh);
		System.out.println(ctPhieuNhap);
		model.addAttribute("ctphieunhap", ctPhieuNhap);
		return "editCtphieunhapView";
	}
	@PostMapping("/ctphieunhap/edit")
	public String editCTPhieuNhap(Model model,HttpSession session,@ModelAttribute CTPhieuNhap ctphieunhap) {
		try {
		ctphieunhapservice.UpdateCTPhieuNhap(ctphieunhap);
		}catch (MsgException e) {
			model.addAttribute("msg",e.getMessage());
			return editCTPhieuNhap(ctphieunhap.getMaphieu(), ctphieunhap.getMahanghoa(), model, session);
		}
		return "redirect:/phieunhap/add";
	}
	@GetMapping("/ctphieunhap/delete")
	public String deleteCTPhieuNhap(@RequestParam(value = "mpn", required = false) long mpn,@RequestParam(value = "mahh", required = false) long mahh,Model model,HttpSession session) {
		ctphieunhapservice.DeleteCTPhieuNhap(mpn, mahh);
		return "redirect:/phieunhap/add";
	}
}
