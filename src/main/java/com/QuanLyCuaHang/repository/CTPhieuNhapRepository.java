
package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.PhieuNhap;
@Transactional
@Repository
public interface CTPhieuNhapRepository extends CrudRepository<CTPhieuNhap, Long> {
	Optional<CTPhieuNhap>   findByMaphieunhapAndMahanghoa( long maphieu,long mahanghoa);
	  List<CTPhieuNhap> findByMaphieunhap(long maphieunhap);
	void deleteByMaphieunhap(long maphieunhap);  
	@Modifying
	@Query(value="delete from ctphieunhap a where a.maphieunhap= :maphieunhap and a.mahanghoa=:mahanghoa", nativeQuery = true)
	void deleteCTPhieuNhap(@Param("maphieunhap")long maphieunhap, @Param("mahanghoa")long mahanghoa);
	 
}
