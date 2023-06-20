package com.QuanLyCuaHang.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.QuanLyCuaHang.taoma;
import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.service.CTPhieuNhapService;
import com.QuanLyCuaHang.service.CTPhieuXuatService;
import com.QuanLyCuaHang.service.NCCService;
import com.QuanLyCuaHang.service.PhieuNhapService;
import com.QuanLyCuaHang.service.PhieuXuatService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PhieuNhapController {

	@Autowired
	PhieuNhapService phieunhapservice;
	@Autowired
	CTPhieuNhapService ctphieunhapservice;
	@Autowired
	NCCService nccservice;
	
	
	@GetMapping("/phieunhap/list")
	public String getALlPhieuNhap(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<PhieuNhap>phieunhap=phieunhapservice.getAllPhieuNhap()	;
		model.addAttribute("phieunhap",phieunhap);
		return "phieunhapListView";
	}
	@GetMapping("/phieunhap/add")
	public String showaddPhieuNhap(HttpSession session, Model model) {
//		if(session.getAttribute("maphieuxuat")!=null) {
//			Object maphieuxuatStr=session.getAttribute("maphieuxuat");
//			long maphieuxuat=Long.parseLong((String) maphieuxuatStr);
//			ctphieuxuatservice.DeleteCTPhieuXuatBympx(maphieuxuat);
//			phieuxuatservice.DeletePhieuXuat(maphieuxuat);
//			
//			session.setAttribute("maphieuxuat", null);
//		}
		model.addAttribute("username", session.getAttribute("username"));
		PhieuNhap phieunhap=new PhieuNhap();
		long maphieunhap=0;
		Object maphieunhapStr=null;
		if(session.getAttribute("maphieunhap")==null) {
			phieunhap=phieunhapservice.addPhieuNhap();
			maphieunhapStr=Long.toString(phieunhap.getMaphieunhap());
			maphieunhap= Long.parseLong((String) maphieunhapStr);
			session.setAttribute("maphieunhap", maphieunhapStr);
		}
		else {
			maphieunhapStr=session.getAttribute("maphieunhap");
			maphieunhap= Long.parseLong((String) maphieunhapStr);
			phieunhap=phieunhapservice.getPhieuNhapByMaphieunhap(maphieunhap);
		}
		List<CTPhieuNhap>ctphieunhap=ctphieunhapservice.getCTPhieuNhapByMpn(maphieunhap);
        session.setAttribute("maphieunhap", maphieunhapStr);
        int manv=(int) session.getAttribute("manv");
        List<NCC> ncc= nccservice.getAllNCC();
		model.addAttribute("ncc", ncc);
        model.addAttribute("manv",manv);
        model.addAttribute("maphieunhap",maphieunhap);
        model.addAttribute("ngaylapphieu",phieunhap.getNgaylapphieu());
        model.addAttribute("thanhtien",phieunhap.getThanhtien());
        model.addAttribute("ctphieunhap",ctphieunhap);
		return "phieunhapCreateView";
	}
	@PostMapping("/phieunhap/add")
	public String addPhieuNhap(HttpSession session, Model model,@ModelAttribute PhieuNhap phieunhap) {
		
		phieunhapservice.XacNhanPhieuNhap(phieunhap);
		session.setAttribute("maphieunhap", null);
		return  "redirect:/hanghoa/list";
	}
	@GetMapping("/phieunhap/huy")
	public String huyPhieuNhap(HttpSession session, Model model) {
		Object maphieunhapStr=session.getAttribute("maphieunhap");
		long maphieunhap=Long.parseLong((String) maphieunhapStr);
		ctphieunhapservice.DeleteCTPhieuNhapBymapn(maphieunhap);
		phieunhapservice.DeletePhieuNhap(maphieunhap);
		
		session.setAttribute("maphieunhap", null);
		return  "redirect:/hanghoa/list";
	}
	@GetMapping("/phieunhap/detail/{mpn}")
	public String detailPhieuNhap(@PathVariable("mpn") long mpn ,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		PhieuNhap phieunhap=phieunhapservice.getPhieuNhapByMaphieunhap(mpn);
		List<CTPhieuNhap>ctphieunhap=ctphieunhapservice.getCTPhieuNhapByMpn(mpn);
		model.addAttribute("ctphieunhap",ctphieunhap);
		model.addAttribute("phieunhap",phieunhap);
		return "ctphieunhapListView";
	}
}
