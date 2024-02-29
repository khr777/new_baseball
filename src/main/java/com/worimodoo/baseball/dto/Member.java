package com.worimodoo.baseball.dto;

import java.util.Map;

import com.worimodoo.baseball.util.ReturnCodeInterface;
import com.worimodoo.baseball.util.Utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Data
@EqualsAndHashCode(callSuper=false)
public class Member extends ResultDto implements ReturnCodeInterface{
	
	
	private int id;
	private String nickName;
	private String passWord;
	private String passWordCheck;
	private String joinTime;
	private String writeTime;
	private String updateTime;
	private Map<String, Object> map;
	
	
	
	public String getJoinValidResult() {
		
		String validResult = RETURN_SUCCESS;
		
		boolean boolNickNameCheck = Utils.isEmptyOrNull("1", getNickName(), null);
		boolean boolPassWord = Utils.isEmptyOrNull("1", getPassWord(), null);
		boolean boolPassWordCheck = Utils.isEmptyOrNull("1", getPassWordCheck(), null);
		
		if ( boolNickNameCheck || boolPassWord || boolPassWordCheck ) {
			validResult = RETURN_EMPTY_OR_NULL;
		}
		
		
		return validResult; 
	}
}
