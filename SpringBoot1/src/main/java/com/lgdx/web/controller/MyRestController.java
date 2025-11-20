package com.lgdx.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgdx.web.entity.MsgMember;
import com.lgdx.web.mapper.MsgMemberMapper;

@RestController
public class MyRestController {
	// 동기 방식의 요청처리 -> 일반 Controller 작업
	// 비동기 방식의 요청처리 -> 비동기 전용 RestController 작업
	
	@Autowired
	private MsgMemberMapper mapper;

	@GetMapping("/checkEmail")
	public String checkEmail(@RequestParam("inputEmail") String email) {
		// 1. 클라이언트가 보내준 이메일을 수집
		// 2. DB에 해당 이메일이 존재하는지 확인
		// 3. 존재하면 "exist", 존재하지 않으면 "non_exist" 문자열 응답
		MsgMember result = mapper.selectByEmail(email);
		
		if (result != null) {
			// email 검색 시 확인되는 회원이 있다.
			// => 이미 사용중인 email이다.
			return "false";
		} else {
			// email 검색 시 확인되는 회원이 없다.
			// => 회원가입에 사용이 가능한 email이다.
			return "true";
		}
		
	}

}
