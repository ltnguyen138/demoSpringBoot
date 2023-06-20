package com.QuanLyCuaHang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.exception.SoLuongHHException;
import com.QuanLyCuaHang.repository.HangHoaRepository;
import com.QuanLyCuaHang.service.HangHoaService;

@Component
public class HangHoaServiceImpl implements HangHoaService {

	@Autowired
	HangHoaRepository hanghoarepository;
	@Autowired
	NCCServiceImpl nccserviceimpl;
	
	@Override
	public HangHoa getHangHoaByMahh(long mahh) {
		HangHoa hanghoa = hanghoarepository.findByMahh(mahh).orElse(null);
		return hanghoa;
	}

	@Override
	public HangHoa addHangHoa(HangHoa hanghoa) {
		if(!IsEmptyHangHoa(hanghoa.getMahh()))
			return null;
		else if(nccserviceimpl.IsEmptyNCC(hanghoa.getMancc()))
			throw new NotFoundException("Khong tim thay nha cung cap");
		return hanghoarepository.save(hanghoa);
	}

	@Override
	public HangHoa UpdateHangHoa(HangHoa hanghoa) {
		if(IsEmptyHangHoa(hanghoa.getMahh()))
			throw new NotFoundException("Khong tim thay hang hoa");
		else if(nccserviceimpl.IsEmptyNCC(hanghoa.getMancc()))
			throw new NotFoundException("Khong tim thay nha cung cap");
		int soluong=getHangHoaByMahh(hanghoa.getMahh()).getSoluong();
		hanghoa.setSoluong(soluong);
		return hanghoarepository.save(hanghoa);
	}

	@Override
	public void DeleteHangHoa(long mahh) {
		if(IsEmptyHangHoa(mahh))
			throw new NotFoundException("Khong tim thay hang hoa");
		int soluong=getHangHoaByMahh(mahh).getSoluong();
		if(soluong>0)
			throw new SoLuongHHException("So luong hang hoa khong hop le");
		hanghoarepository.deleteById(mahh);
		
	}

	@Override
	public List<HangHoa> getAllHangHoa() {
		return (List<HangHoa>) hanghoarepository.findAll();
	}

	@Override
	public boolean IsEmptyHangHoa(long mahh) {
		HangHoa hanghoa= getHangHoaByMahh(mahh);
		if(hanghoa!=null)
			return false;
		else
			return true;
	}

	@Override
	public void UpdateSoluongHH(long mahh, int soluong) {
		if(IsEmptyHangHoa(mahh))
			throw new NotFoundException("Khong tim thay hang hoa");
		hanghoarepository.updateSoluongHH(mahh, soluong);
	}

	@Override
	public int getSoLuongHH(long mahh) {
		return getHangHoaByMahh(mahh).getSoluong();
	}

	@Override
	public List<HangHoa> searchHH(String search) {
		return hanghoarepository.findByTenhhContaining(search);
	}

	

}
