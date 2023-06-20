package com.QuanLyCuaHang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.exception.MsgException;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.CTPhieuNhapRepository;
import com.QuanLyCuaHang.service.CTPhieuNhapService;

import jakarta.transaction.Transactional;


@Component
@Transactional
public class CTPhieuNhapServiceImpl implements CTPhieuNhapService {

	@Autowired
	CTPhieuNhapRepository ctphieunhaprepository;
	@Autowired
	HangHoaServiceImpl hanghoaservice;
	@Autowired
	PhieuNhapServiceImpl phieunhapservice;

	@Override
	public CTPhieuNhap getCTPhieuNhapByMapnAndMahh(long maphieunhap, long mahh) {
		CTPhieuNhap ctphieunhap = ctphieunhaprepository.findByMaphieunhapAndMahanghoa(maphieunhap, mahh).orElse(null);
		return ctphieunhap;
	}
		
	@Override
	public CTPhieuNhap addCTPhieuNhap(CTPhieuNhap ctphieunhap) {
//		if(phieunhapservice.IsEmptyPhieuNhap(maphieunhap))
//			throw new NotFoundException("Khong tim thay phieu nhap hang");
//		else if (hanghoaservice.IsEmptyHangHoa(mahanghoa)) 
//			throw new NotFoundException("Khong tim thay hang hoa");
		if(ctphieunhap.getSoluong()<1)
			throw new MsgException("Số lượng hàng hóa phải lớn hơn 0");
		Long maphieunhap=ctphieunhap.getMaphieu();
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(ctphieunhap.getMahanghoa());
		
		float dongia= hanghoaservice.getHangHoaByMahh(ctphieunhap.getMahanghoa()).getDongianhap();
		//Cap nhat thanh tien phieu nhap
		PhieuNhap phieunhap=phieunhapservice.getPhieuNhapByMaphieunhap(maphieunhap);
		float newthanhtien=phieunhap.getThanhtien()+dongia*ctphieunhap.getSoluong();
		
		phieunhapservice.UpdateThanhtienPN(phieunhap.getMaphieunhap(), newthanhtien);
		//Cap nhat so luong hang hoa		
		hanghoaservice.UpdateSoluongHH(ctphieunhap.getMahanghoa(), soluonghhtonkho+ctphieunhap.getSoluong());
		
		String tenhh=hanghoaservice.getHangHoaByMahh(ctphieunhap.getMahanghoa()).getTenhh();
		ctphieunhap.setTenhh(tenhh);
		ctphieunhap.setDongia(dongia);
		return ctphieunhaprepository.save(ctphieunhap);
	}

	@Override
	public CTPhieuNhap UpdateCTPhieuNhap(CTPhieuNhap ctphieunhap) {
//		if(IsEmptyCTPhieuNhap(maphieunhap, mahanghoa))
//			throw new NotFoundException("Khong tim thay ct phieu nhap hang");
		if(ctphieunhap.getSoluong()<1)
			throw new MsgException("Số lượng hàng hóa phải lớn hơn 0");
		CTPhieuNhap ctPhieuNhapCu=getCTPhieuNhapByMapnAndMahh(ctphieunhap.getMaphieu(), ctphieunhap.getMahanghoa());
		System.out.println(ctPhieuNhapCu+"cu");
		System.out.println(ctphieunhap+"moi");
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(ctphieunhap.getMahanghoa());
		if((soluonghhtonkho-ctPhieuNhapCu.getSoluong()+ctphieunhap.getSoluong())<0)
			throw new MsgException("Số lượng hang hóa trong kho không đủ");
		
		//Cap nhat thanh tien phieu nhap
		float dongia=hanghoaservice.getHangHoaByMahh(ctphieunhap.getMahanghoa()).getDongianhap();
		float tongsotienpn= phieunhapservice.getPhieuNhapByMaphieunhap(ctphieunhap.getMaphieu()).getThanhtien();
		float soTienCTPNCu= ctPhieuNhapCu.getDongia()*ctPhieuNhapCu.getSoluong();
		tongsotienpn=tongsotienpn-soTienCTPNCu+dongia*ctphieunhap.getSoluong();
		phieunhapservice.UpdateThanhtienPN(ctphieunhap.getMaphieu(), tongsotienpn);
		//Cap nhat so luong hang hoa ton kho
		soluonghhtonkho=soluonghhtonkho-ctPhieuNhapCu.getSoluong()+ctphieunhap.getSoluong();
		hanghoaservice.UpdateSoluongHH(ctphieunhap.getMahanghoa(), soluonghhtonkho);
		//Cap nhat ct phieu nhap
		String tenhh=hanghoaservice.getHangHoaByMahh(ctphieunhap.getMahanghoa()).getTenhh();
		ctphieunhap.setId(ctPhieuNhapCu.getId());
		ctphieunhap.setTenhh(tenhh);
		ctphieunhap.setDongia(dongia);
		return ctphieunhaprepository.save(ctphieunhap);
	}

	@Override
	public void DeleteCTPhieuNhap(long maphieunhap, long mahh) {
		if(IsEmptyCTPhieuNhap(maphieunhap, mahh))
			throw new NotFoundException("Khong tim thay ct phieu nhap hang");
		int soluonghhtonkho = hanghoaservice.getSoLuongHH(mahh);
		if((soluonghhtonkho-getCTPhieuNhapByMapnAndMahh(maphieunhap, mahh).getSoluong())<0)
			throw new MsgException("So luong hang hoa trong kho khong du");
		//Cap nhat thanh tien phieu nhap
		float tongsotienpn= phieunhapservice.getPhieuNhapByMaphieunhap(maphieunhap).getThanhtien();
		tongsotienpn=tongsotienpn-getCTPhieuNhapByMapnAndMahh(maphieunhap, mahh).getSoluong()*getCTPhieuNhapByMapnAndMahh(maphieunhap, mahh).getDongia();
		phieunhapservice.UpdateThanhtienPN(maphieunhap, tongsotienpn);
		//Cap nhat so luong hang hoa ton kho
		soluonghhtonkho=soluonghhtonkho-getCTPhieuNhapByMapnAndMahh(maphieunhap, mahh).getSoluong();
		hanghoaservice.UpdateSoluongHH(mahh, soluonghhtonkho);
		//Xoa ct phieu nhap
		ctphieunhaprepository.deleteCTPhieuNhap(maphieunhap, mahh);
	}

	@Override
	public List<CTPhieuNhap> getAllCTPhieuNhap() {
		return (List<CTPhieuNhap>) ctphieunhaprepository.findAll();
	}

	@Override
	public boolean IsEmptyCTPhieuNhap(long maphieunhap, long mahh) {
		CTPhieuNhap ctphieunhap=getCTPhieuNhapByMapnAndMahh(maphieunhap, mahh);
		if(ctphieunhap!=null)
			return false;
		else
			return true;
	}

	@Override
	public List<CTPhieuNhap> getCTPhieuNhapByMpn(long maphieunhap) {
		return ctphieunhaprepository.findByMaphieunhap(maphieunhap);
	}

	@Override
	public void DeleteCTPhieuNhapBymapn(long maphieunhap) {
		List<CTPhieuNhap>ctphieunhap=getCTPhieuNhapByMpn(maphieunhap);
		for (CTPhieuNhap ctPhieuNhap2 : ctphieunhap) {
			DeleteCTPhieuNhap(ctPhieuNhap2.getMaphieu(), ctPhieuNhap2.getMaphieu());
		}
		
	}
	


}
