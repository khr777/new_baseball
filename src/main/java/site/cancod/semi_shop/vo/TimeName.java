package site.cancod.semi_shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeName {
	
	private int seq;
	private String timeName;
	private String userId;
	private String create_time;
	private String delete_time;
	private String update_time;
}
