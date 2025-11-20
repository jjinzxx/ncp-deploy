package com.lgdx.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgdx.web.entity.MsgMember;
import com.lgdx.web.mapper.MsgMemberMapper;

import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {
	// DB를 연결해서 데이터를 조회하거나 생성하는 기능을 처리하는 컨트롤러
	
	// 데이터를 확인하는 용도로 console 출력할 도구
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// Spring Container가 해당하는 인터페이스 구현체를 직접 생성해서 주입
	@Autowired
	private MsgMemberMapper mapper;
	
	@PostMapping("/join")
	public String join(MsgMember mem, Model model) {
		// 1. 요청데이터 수집하기
		// -> 수집해야 할 데이터가 4가지네? -> 낱개로 수집하면 불편
		// -> 내가 해당하는 데이터를 하나로 표현할 수 있는 자료형을 만들자
		// -> MsgMember생성 -> 요청 데이터를 매개변수로 수집
		logger.info("수집한 데이터 확인 >> " + mem);
		
		// 2. DB에 수집한 데이터를 저장하기
		mapper.join(mem);
		
		// model 영역에 회원가입 한 email값만 담아서 다음페이지로 넘겨주기
		model.addAttribute("email", mem.getEmail());
		
		return "JoinSuccess";
	}
	
	
	// 로그인에 대한 작업 작성하기
	@PostMapping("/login")
	public String login(MsgMember mem, HttpSession session) {
		// 1. 들어온 요청을 판단할 수 있는 작업 수행
		logger.info("로그인 요청 데이터 >> " + mem);

		// 2. 해당 요청에 따라 응답할 수 있는 작업 수행
		// - DB에 연결하여 회원의 정보가 있는지 없는지 확인 필요
		MsgMember member = mapper.login(mem);
		
		// 모든 페이지 기능에서 활용할 수 있는 session scope 영역 사용
		session.setAttribute("member", member);
		
		if(member != null) {
			// 회원 정보가 존재하는 경우
			System.out.println("로그인 성공한 회원 정보 >> " + member);
			return "Main";
		} else {
			// 회원 정보가 존재하지 않는 경우
			System.out.println("로그인 실패! 회원 정보가 존재하지 않음!");
			return "Main";
		}
		
	}
	
	// 로그아웃에 대한 작업 작성하기
	// GET 요청
	// 요청 키워드 : /logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// Session 영역에서 loginMem 속성 제거
		session.removeAttribute("member");
		
		// 결과화면 : Main
		return "Main";
	}
	
	// 회원 정보 수정에 대한 작업 작성하기
	@PostMapping("/update")
	public String update(MsgMember mem, HttpSession session) {
	    
	    mapper.update(mem);
	    
        session.setAttribute("member", mem);
        
        logger.info("회원 정보 수정 완료 >> " + mem);
	    
	    return "Main";
	}
	
	
	// 회원정보관리
	@GetMapping("/showMember")
	public String showMember(Model model) {
		
		List<MsgMember> list = mapper.showMember();
		model.addAttribute("list", list);
		
	    return "ShowMember";
	}

	// 회원정보삭제
	
	@GetMapping("/delete")
	public String delete1(@RequestParam ("email") String email) {
		
		mapper.delete(email);
		
		return "redirect:/showMember";
	}
}
