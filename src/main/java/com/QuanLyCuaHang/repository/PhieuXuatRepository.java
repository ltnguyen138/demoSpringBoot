package com.QuanLyCuaHang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.QuanLyCuaHang.entity.PhieuXuat;
@Transactional
@Repository
public interface PhieuXuatRepository extends CrudRepository<PhieuXuat, Long> {
	Optional<PhieuXuat> findByMaphieuxuat(long maphieuxuat);
	@Modifying
	@Query(value="update  phieuxuat a set a.thanhtien= :thanhtien where a.maphieuxuat= :maphieuxuat", nativeQuery = true)
	void updateThanhtienPX(@Param("maphieuxuat")long maphieuxuat, @Param("thanhtien")float thanhtien);
	void deleteByMaphieuxuat(long maphieuxuat);
}
