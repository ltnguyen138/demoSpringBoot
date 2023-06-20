package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.QuanLyCuaHang.entity.HangHoa;
@Transactional
@Repository
public interface HangHoaRepository extends CrudRepository<HangHoa, Long>{
	Optional<HangHoa>  findByMahh(long mahh);
	List<HangHoa> findByTenhhContaining(String tenhh);
	@Modifying
	@Query(value="update  hanghoa a set a.soluong= :soluong where a.mahh= :mahh", nativeQuery = true)
	void updateSoluongHH(@Param("mahh")long mahh, @Param("soluong")int soluong);
	
}
