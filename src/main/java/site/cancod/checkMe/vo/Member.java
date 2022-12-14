package site.cancod.checkMe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	
	private int seq;
	private String loginId;
	private String loginPw;
	private String create_time;
	private String delete_time;
	private String update_time;
	private String email;
	private String name;
}
