package com.worimodoo.baseball.dto;

import com.worimodoo.baseball.util.ReturnCodeInterface;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Data
@EqualsAndHashCode(callSuper=false)
public class GameDto extends Member implements ReturnCodeInterface {
	
	private int gameId;
	private int ball;
	private int round;
	private String playball;
	private String gameWriteTime;
	private String gameFinishTime;
	
}
