package com.QuanLyCuaHang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.CTPhieuXuat;
import com.QuanLyCuaHang.exception.MsgException;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.CTPhieuXuatRepository;
import com.QuanLyCuaHang.service.CTPhieuXuatService;

@Component
public class CTPhieuXuatServiceImpl implements CTPhieuXuatService {

	@Autowired
	CTPhieuXuatRepository ctphieuxuatrepository;
	@Autowired
	HangHoaServiceImpl hanghoaservice;
	@Autowired
	PhieuXuatServiceImpl phieuxuatservice;
	
	@Override
	public CTPhieuXuat getCTPhieuXuatByMapxAndMahh(long maphieuxuat, long mahh) {
		CTPhieuXuat ctphieuxuat=ctphieuxuatrepository.findByMaphieuxuatAndMahanghoa(maphieuxuat, mahh).orElse(null);
		return ctphieuxuat;
	}

	@Override
	public CTPhieuXuat addCTPhieuXuat(CTPhieuXuat ctphieuxuat) {
		if(ctphieuxuat.getSoluong()<1)
			throw new MsgException("Số lượng hàng hóa phải lớn hơn 0");
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(ctphieuxuat.getMahanghoa());
		if(soluonghhtonkho-ctphieuxuat.getSoluong()<0)
			throw new MsgException("Số lượng hàng hóa trong kho không đủ");
		float dongia=hanghoaservice.getHangHoaByMahh(ctphieuxuat.getMahanghoa()).getDongiaxuat();
		//Cap nhat so tien phieu xuat
		float tongsotienpx= phieuxuatservice.getPhieuXuatByMaphieuxuat(ctphieuxuat.getMaphieuxuat()).getThanhtien()+ ctphieuxuat.getSoluong()*dongia;
		phieuxuatservice.UpdateThanhtienPX(ctphieuxuat.getMaphieuxuat(), tongsotienpx);
		//Cap nhat so luong hang hoa ton kho
		hanghoaservice.UpdateSoluongHH(ctphieuxuat.getMahanghoa(), soluonghhtonkho-ctphieuxuat.getSoluong());
		String tenhh=hanghoaservice.getHangHoaByMahh(ctphieuxuat.getMahanghoa()).getTenhh();
		ctphieuxuat.setTenhh(tenhh);
		ctphieuxuat.setDongia(dongia);
		return ctphieuxuatrepository.save(ctphieuxuat);
	}

	@Override
	public CTPhieuXuat UpdateCTPhieuXuat(CTPhieuXuat ctphieuxuat) {

		if(ctphieuxuat.getSoluong()<1)
			throw new MsgException("Số lượng hàng hóa phải lớn hơn 0");
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(ctphieuxuat.getMahanghoa());
		CTPhieuXuat ctphiepxuatcu =getCTPhieuXuatByMapxAndMahh(ctphieuxuat.getMaphieuxuat(),ctphieuxuat.getMahanghoa());
		if((soluonghhtonkho+ctphiepxuatcu.getSoluong()-ctphieuxuat.getSoluong())<0)
			throw new MsgException("Số lượng hàng hóa trong kho không đủ");
		float dongia=hanghoaservice.getHangHoaByMahh(ctphieuxuat.getMahanghoa()).getDongiaxuat();
		//Cap nhat so tien phieu xuat
		float soTienCTPXCu=ctphiepxuatcu.getSoluong()*ctphiepxuatcu.getDongia();
		float tongsotienpx= phieuxuatservice.getPhieuXuatByMaphieuxuat(ctphieuxuat.getMaphieuxuat()).getThanhtien();
		tongsotienpx =tongsotienpx-soTienCTPXCu+ctphieuxuat.getSoluong()*dongia;
		phieuxuatservice.UpdateThanhtienPX(ctphieuxuat.getMaphieuxuat(), tongsotienpx);
		//Cap nhat so luong hang hoa ton kho
		hanghoaservice.UpdateSoluongHH(ctphieuxuat.getMahanghoa(), soluonghhtonkho+ctphiepxuatcu.getSoluong()-ctphieuxuat.getSoluong());
		//Cap nhat chi tiet phieu xuat
		ctphieuxuat.setId(ctphiepxuatcu.getId());
		ctphieuxuat.setDongia(dongia);
		return ctphieuxuatrepository.save(ctphieuxuat);
	}

	@Override
	public void DeleteCTPhieuXuat(long maphieuxuat, long mahh) {
		if(IsEmptyCTPhieuXuat(maphieuxuat, mahh))
			throw new NotFoundException("Khong tim thay phieu xuat hang");
		//Cap nhat so tien phieu xuat
		float tongSoTienPX=phieuxuatservice.getPhieuXuatByMaphieuxuat(maphieuxuat).getThanhtien();
		tongSoTienPX=tongSoTienPX-getCTPhieuXuatByMapxAndMahh(maphieuxuat, mahh).getDongia()*getCTPhieuXuatByMapxAndMahh(maphieuxuat, mahh).getSoluong();
		phieuxuatservice.UpdateThanhtienPX(maphieuxuat, tongSoTienPX);
		//Cap nhat so luong hang hoa ton kho
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(mahh);
		hanghoaservice.UpdateSoluongHH(mahh,soluonghhtonkho+getCTPhieuXuatByMapxAndMahh(maphieuxuat, mahh).getSoluong());
		//Xoa chi tiet phieu xuat
		ctphieuxuatrepository.deleteCTPhieuXuat(maphieuxuat, mahh);
	}

	@Override
	public List<CTPhieuXuat> getAllCTPhieuXuat() {
		return (List<CTPhieuXuat>) ctphieuxuatrepository.findAll()	;
	}

	@Override
	public boolean IsEmptyCTPhieuXuat(long maphieunhap, long mahh) {
		CTPhieuXuat ctphieuxuat = getCTPhieuXuatByMapxAndMahh(maphieunhap, mahh);
		if(ctphieuxuat!=null)
			return false;
		else
			return true;
	}

	@Override
	public List<CTPhieuXuat> getCTPhieuXuatByMpx(long maphieuxuat) {
		return ctphieuxuatrepository.findByMaphieuxuat(maphieuxuat);
	}

	@Override
	public void DeleteCTPhieuXuatBympx(long maphieunhap) {
		List<CTPhieuXuat>ctphieuxuat=getCTPhieuXuatByMpx(maphieunhap);
		for (CTPhieuXuat ctPhieuXuat2 : ctphieuxuat) {
			DeleteCTPhieuXuat(ctPhieuXuat2.getMaphieuxuat(),ctPhieuXuat2.getMahanghoa());
		}
		
	}

	
}
