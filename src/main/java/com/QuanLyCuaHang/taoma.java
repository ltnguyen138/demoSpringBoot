package com.QuanLyCuaHang;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class taoma {

	public static String taoma() {
		LocalDateTime date=LocalDateTime.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		String month = date.format(formatter1);
		String day=date.format(formatter2);
		String year=Integer.toString(date.getYear());
		year = year.substring(2);
		String time=date.format(formatter3);
		
		String hour=time.substring(0,2);
		String minute=time.substring(3,5);
		String second=time.substring(6,8);
		String ma=year+month+day+hour+minute+second;
		
		return ma;
	}

}