package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.QuanLyCuaHang.entity.PhieuNhap;

@Transactional
@Repository
public interface PhieuNhapRepository extends CrudRepository<PhieuNhap, Long> {
	Optional<PhieuNhap>  findByMaphieunhap(long maphieunhap);
	@Modifying
	@Query(value="update  phieunhap a set a.thanhtien= :thanhtien where a.maphieunhap= :maphieunhap", nativeQuery = true)
	void updateThanhtienPN(@Param("maphieunhap")long maphieunhap, @Param("thanhtien")float thanhtien);
	
}
