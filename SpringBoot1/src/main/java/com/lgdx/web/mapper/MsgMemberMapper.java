package com.lgdx.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lgdx.web.entity.MsgMember;

@Mapper
public interface MsgMemberMapper {

	@Insert("INSERT INTO MSG_MEMBER VALUES(#{email}, #{pw}, #{tel}, #{address})")
	void join(MsgMember mem);
	
	@Select("SELECT * FROM MSG_MEMBER WHERE EMAIL=#{email} AND PW=#{pw}")
	MsgMember login(MsgMember mem);
	
	@Update("UPDATE MSG_MEMBER SET PW=#{pw}, TEL=#{tel}, ADDRESS=#{address} WHERE EMAIL=#{email}")
	void update(MsgMember mem);

	@Select("SELECT * FROM MSG_MEMBER")
	// 한 개의 행을 담기 위한 타입으로 MsgMember가 필요하고
	// 해당 기능은 전체 회원에 대한 정보를 받아와야 하므로
	// List 자료구조를 활용한다
	List<MsgMember> showMember();

	@Delete("DELETE FROM MSG_MEMBER WHERE EMAIL=#{email}")
	void delete(String email);

	@Select("SELECT * FROM MSG_MEMBER WHERE EMAIL=#{email}")
	MsgMember selectByEmail(String email);

}
