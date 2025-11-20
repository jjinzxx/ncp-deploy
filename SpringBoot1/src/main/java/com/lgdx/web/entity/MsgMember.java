package com.lgdx.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgMember {
	// Oracle DB 에서 msg_member 테이블과 매핑되는 Entity 클래스
	
	// 필드의 명칭을 지어줄 때는 테이블의 컬럼명과 동일하게 맞춰주는 것이 좋다!
	private String email;
	private String pw;
	private String tel;
	private String address;
	
	
}
