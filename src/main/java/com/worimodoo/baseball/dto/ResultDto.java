package com.worimodoo.baseball.dto;

import com.worimodoo.baseball.util.ReturnCodeInterface;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ResultDto implements ReturnCodeInterface {
	
	private String result;
	private String resultMsg;
	private String view;
	
}
