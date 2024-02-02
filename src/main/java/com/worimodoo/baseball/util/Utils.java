package com.worimodoo.baseball.util;

public class Utils {
	
	/**
	 * 
	 * @param type 1: 문자열, 2: 객체 
	 * @param str 문자열 
	 * @param obj 객체 
	 * @return
	 */
	public static boolean isEmptyOrNull(String type, String str, Object obj) {
		
		if ( type.equals("1") ) {
			if ( str == null || str.equals("") ) {
				return true;
			} else {
				return false;
			}
		} else {
			if ( obj == null || obj.equals("") ) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	
}
