package com.QuanLyCuaHang;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.HangHoa;
import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.entity.NhanVien;
import com.QuanLyCuaHang.entity.PhieuNhap;
import com.QuanLyCuaHang.repository.KhachHangRepository;
import com.QuanLyCuaHang.service.CTPhieuNhapService;
import com.QuanLyCuaHang.service.HangHoaService;
import com.QuanLyCuaHang.service.KhachHangService;
import com.QuanLyCuaHang.service.NCCService;
import com.QuanLyCuaHang.service.NhanVienService;
import com.QuanLyCuaHang.service.PhieuNhapService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DemoQuanLyCuaHang2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoQuanLyCuaHang2Application.class, args);
	}

}
