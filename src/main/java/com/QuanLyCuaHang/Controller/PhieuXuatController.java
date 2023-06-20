package com.QuanLyCuaHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.CTPhieuXuat;
import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.entity.PhieuXuat;
import com.QuanLyCuaHang.service.CTPhieuNhapService;
import com.QuanLyCuaHang.service.CTPhieuXuatService;
import com.QuanLyCuaHang.service.KhachHangService;
import com.QuanLyCuaHang.service.PhieuNhapService;
import com.QuanLyCuaHang.service.PhieuXuatService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PhieuXuatController {

//	@Autowired
//	PhieuNhapService phieunhapservice;
//	@Autowired
//	CTPhieuNhapService ctphieunhapservice;
	@Autowired
	PhieuXuatService phieuxuatservice;
	@Autowired
	CTPhieuXuatService ctphieuxuatservice;
	@Autowired
	KhachHangService khachhangservice;
	
	@GetMapping("/phieuxuat/list")
	public String getALlPhieuXuat(Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		List<PhieuXuat>phieuxuat=phieuxuatservice.getAllPhieuXuat();
		model.addAttribute("phieuxuat",phieuxuat);
		return "phieuxuatListView";
	}
	@GetMapping("/phieuxuat/add")
	public String showaddPhieuXuat(HttpSession session, Model model) {
//		if(session.getAttribute("maphieunhap")!=null) {
//			Object maphieunhapStr=session.getAttribute("maphieunhap");
//			long maphieunhap=Long.parseLong((String) maphieunhapStr);
//			ctphieunhapservice.DeleteCTPhieuNhapBymapn(maphieunhap);
//			phieunhapservice.DeletePhieuNhap(maphieunhap);
//			
//			session.setAttribute("maphieunhap", null);
//		}
		model.addAttribute("username", session.getAttribute("username"));
		PhieuXuat phieuxuat=new PhieuXuat();
		long maphieuxuat=0;
		Object maphieuxuatStr=null;
		if(session.getAttribute("maphieuxuat")==null) {
			phieuxuat=phieuxuatservice.addPhieuXuat();
			maphieuxuatStr=Long.toString(phieuxuat.getMaphieuxuat());
			maphieuxuat= Long.parseLong((String) maphieuxuatStr);
			session.setAttribute("maphieuxuat", maphieuxuatStr);
		}
		else {
			maphieuxuatStr=session.getAttribute("maphieuxuat");
			maphieuxuat= Long.parseLong((String) maphieuxuatStr);
			phieuxuat=phieuxuatservice.getPhieuXuatByMaphieuxuat(maphieuxuat);
		}
		List<CTPhieuXuat>ctphieuxuat=ctphieuxuatservice.getCTPhieuXuatByMpx(maphieuxuat);
        session.setAttribute("maphieuxuat", maphieuxuatStr);
        int manv=(int) session.getAttribute("manv");
        List<KhachHang> khachhang= khachhangservice.getAllKhachHang();
		model.addAttribute("khachhang", khachhang);
        model.addAttribute("manv",manv);
        model.addAttribute("maphieuxuat",maphieuxuat);
        model.addAttribute("ngaylapphieu",phieuxuat.getNgaylapphieu());
        model.addAttribute("thanhtien",phieuxuat.getThanhtien());
        model.addAttribute("ctphieuxuat",ctphieuxuat);
		return "phieuxuatCreateView";
	}
	@PostMapping("/phieuxuat/add")
	public String addPhieuXuat(HttpSession session, Model model,@ModelAttribute PhieuXuat phieuxuat) {
		
		phieuxuatservice.XacNhanPhieuXuat(phieuxuat);
		session.setAttribute("maphieuxuat", null);
		return  "redirect:/hanghoa/list";
	}
	@GetMapping("/phieuxuat/huy")
	public String huyPhieuXuat(HttpSession session, Model model) {
		Object maphieuxuatStr=session.getAttribute("maphieuxuat");
		long maphieuxuat=Long.parseLong((String) maphieuxuatStr);
		ctphieuxuatservice.DeleteCTPhieuXuatBympx(maphieuxuat);
		phieuxuatservice.DeletePhieuXuat(maphieuxuat);
		
		session.setAttribute("maphieuxuat", null);
		return  "redirect:/phieuxuat/list";
	}
	@GetMapping("/phieuxuat/detail/{mpx}")
	public String detailPhieuXuat(@PathVariable("mpx") long mpx ,Model model,HttpSession session) {
		model.addAttribute("username", session.getAttribute("username"));
		PhieuXuat phieuxuat=phieuxuatservice.getPhieuXuatByMaphieuxuat(mpx);
		List<CTPhieuXuat>ctphieuxuat=ctphieuxuatservice.getCTPhieuXuatByMpx(mpx);
		model.addAttribute("ctphieuxuat",ctphieuxuat);
		model.addAttribute("phieuxuat",phieuxuat);
		return "ctphieuxuatListView";
	}
	
}
