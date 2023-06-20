package com.QuanLyCuaHang.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

import com.QuanLyCuaHang.entity.NhanVien;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
        	response.sendRedirect("/login");
            return false;
        }
        if (requestURI.startsWith("/nhanvien")) {
            if ( !(session.getAttribute("role").equals("QL") )){
                response.sendRedirect("/thongbao");
                return false;
            }
        }
        if (requestURI.startsWith("/phieunhap")) {
            if ( !(session.getAttribute("role").equals("QL")||!session.getAttribute("role").equals("NVK")) ){
                response.sendRedirect("/thongbao");
                return false;
            }
        }
        if (requestURI.startsWith("/phieuxuat")) {
            if ( !(session.getAttribute("role").equals("QL") ||session.getAttribute("role").equals("NVKD")) ){
                response.sendRedirect("/thongbao");
                return false;
            }
        }
        return true;
    }
}
	
        

