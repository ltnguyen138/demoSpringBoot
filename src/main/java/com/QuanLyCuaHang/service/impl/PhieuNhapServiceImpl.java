package com.QuanLyCuaHang.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.taoma;
import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.exception.NotFoundException;

import com.QuanLyCuaHang.repository.PhieuNhapRepository;
import com.QuanLyCuaHang.service.CTPhieuNhapService;
import com.QuanLyCuaHang.service.PhieuNhapService;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class PhieuNhapServiceImpl implements PhieuNhapService {

	@Autowired
	PhieuNhapRepository phieunhaprepository;
	@Autowired
	NhanVienServiceImpl nhanvienserviceimpl;
	@Autowired
	NCCServiceImpl nccserviceimpl;
	
	
	@Override
	public PhieuNhap getPhieuNhapByMaphieunhap(long maphieunhap) {
		PhieuNhap phieunhap = phieunhaprepository.findByMaphieunhap(maphieunhap).orElse(null);
		return phieunhap;
	}

	@Override
	public PhieuNhap addPhieuNhap() {
//		if (!IsEmptyPhieuNhap(phieunhap.getMaphieunhap())) 
//			return null;
//		else if(nccserviceimpl.IsEmptyNCC(phieunhap.getMancc()))
//			throw new NotFoundException("Khong tim thay nha cung cap");
//		else if (nhanvienserviceimpl.IsEmptyNhanVien(phieunhap.getManv())) 
//			throw new NotFoundException("Khong tim thay nhan vien");
		long maphieunhap=Long.parseLong(taoma.taoma());
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngaylapphieu = currentDate.format(formatter);
        long mancc = 0;
        List<NCC> list =nccserviceimpl.getAllNCC();
        for (NCC ncc : list) {
			mancc=ncc.getMancc();
			break;
		}
		PhieuNhap phieunhap = new PhieuNhap( maphieunhap,mancc,1,ngaylapphieu,0,0);
		return phieunhaprepository.save(phieunhap);
	}

	@Override
	public PhieuNhap XacNhanPhieuNhap(PhieuNhap phieunhap) {
//		if (IsEmptyPhieuNhap(maphieunhap)) 
//			throw new NotFoundException("Khong tim thay phieu nhap");
//		else if(nccserviceimpl.IsEmptyNCC(mancc))
//			throw new NotFoundException("Khong tim thay nha cung cap");
//		else if (nhanvienserviceimpl.IsEmptyNhanVien(manv)) 
//			throw new NotFoundException("Khong tim thay nhan vien");
		
		phieunhap.setTrangthai(1);
		return phieunhaprepository.save(phieunhap);
	}

	@Override
	public void DeletePhieuNhap(long maphieunhap) {
		if (IsEmptyPhieuNhap(maphieunhap)) 
			throw new NotFoundException("Khong tim thay phieu nhap");
		
		phieunhaprepository.deleteById(maphieunhap);
	}

	@Override
	public List<PhieuNhap> getAllPhieuNhap() {
		return (List<PhieuNhap>) phieunhaprepository.findAll();
	}

	@Override
	public boolean IsEmptyPhieuNhap(long maphieunhap) {
		PhieuNhap phieunhap = getPhieuNhapByMaphieunhap(maphieunhap);
		if(phieunhap!=null)
			return false;
		else
			return true;
	}

	@Override
	public void UpdateThanhtienPN(long maphieunhap, float thanhtien) {
		if (IsEmptyPhieuNhap(maphieunhap)) 
			throw new NotFoundException("Khong tim thay phieu nhap");
		phieunhaprepository.updateThanhtienPN(maphieunhap, thanhtien);
	}

	@Override
	public PhieuNhap UpdatePhieuNhap(PhieuNhap phieunhap) {
		return phieunhaprepository.save(phieunhap);
	}	
}
