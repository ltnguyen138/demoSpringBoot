package com.QuanLyCuaHang.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.taoma;
import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.entity.PhieuXuat;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.PhieuXuatRepository;
import com.QuanLyCuaHang.service.PhieuNhapService;
import com.QuanLyCuaHang.service.PhieuXuatService;

@Component
public class PhieuXuatServiceImpl implements PhieuXuatService {

	@Autowired
	PhieuXuatRepository phieuxuatrepository;
	@Autowired
	NhanVienServiceImpl nhanvienserviceimpl;
	@Autowired
	KhachHangServiceImpl khachhangserviceimpl;
	
	@Override
	public PhieuXuat getPhieuXuatByMaphieuxuat(long maphieuxuat) {
		PhieuXuat phieuxuat=phieuxuatrepository.findByMaphieuxuat(maphieuxuat).orElse(null);
		return phieuxuat;
	}

	@Override
	public PhieuXuat addPhieuXuat() {
//		if (!IsEmptyPhieuXuat(phieuxuat.getMaphieuxuat())) 
//			return null;
//		else if(khachhangserviceimpl.IsEmptyKhachHang(phieuxuat.getMakh()))
//			throw new NotFoundException("Khong tim thay khach hang");
//		else if (nhanvienserviceimpl.IsEmptyNhanVien(phieuxuat.getManv())) 
//			throw new NotFoundException("Khong tim thay nhan vien");
		long maphieuxuat=Long.parseLong(taoma.taoma());
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngaylapphieu = currentDate.format(formatter);
        List<KhachHang> list=khachhangserviceimpl.getAllKhachHang();
        long makh=0;
        for (KhachHang khachHang : list) {
			makh=khachHang.getMakh();
			break;
		}
        PhieuXuat phieuxuat=new PhieuXuat(maphieuxuat, makh, 1, ngaylapphieu, 0, 0);
		return phieuxuatrepository.save(phieuxuat);
	}

	@Override
	public PhieuXuat XacNhanPhieuXuat(PhieuXuat phieuxuat) {
//		if (IsEmptyPhieuXuat(maphieuxuat)) 
//			throw new NotFoundException("Khong tim thay phieu xuat");
//		else if(khachhangserviceimpl.IsEmptyKhachHang(makh))
//			throw new NotFoundException("Khong tim thay khach hang");
//		else if (nhanvienserviceimpl.IsEmptyNhanVien(manv)) 
//			throw new NotFoundException("Khong tim thay nhan vien");
		phieuxuat.setTrangthai(1);
		return phieuxuatrepository.save(phieuxuat);
	}

	@Override
	public void DeletePhieuXuat(long maphieuxuat) {
		if (IsEmptyPhieuXuat(maphieuxuat)) 
			throw new NotFoundException("Khong tim thay phieu xuat");
		phieuxuatrepository.deleteById(maphieuxuat);
		
	}

	@Override
	public List<PhieuXuat> getAllPhieuXuat() {
		return (List<PhieuXuat>) phieuxuatrepository.findAll();
	}

	@Override
	public boolean IsEmptyPhieuXuat(long maphieuxuat) {
		PhieuXuat phieuxuat=getPhieuXuatByMaphieuxuat(maphieuxuat);
		if(phieuxuat!=null)
			return false;
		else
			return true;		
	}

	@Override
	public void UpdateThanhtienPX(long maphieuxuat, float thanhtien) {
		if (IsEmptyPhieuXuat(maphieuxuat)) 
			throw new NotFoundException("Khong tim thay phieu xuat");
		phieuxuatrepository.updateThanhtienPX(maphieuxuat, thanhtien);
	}

	

	
}
