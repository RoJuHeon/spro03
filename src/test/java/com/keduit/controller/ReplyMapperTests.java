package com.keduit.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {5L,7L,9L,46L,47L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	//게시물 번호에 대해 댓글 추가코드
	@Test
	public void testCreate() {
		//IntStream의 범위값, rangeClosed = 1~1까지
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트입니다." + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);//10번 날림
		});
	}
	
	//한 건 읽어오기
	@Test
	public void testRead() {
		Long rno = 5L;
		
		ReplyVO vo = mapper.read(rno);
		log.info(vo);
	}
	
	@Test
	public void testdelete() {
		Long rno = 5L;
		int result = mapper.delete(rno);
		log.info("삭제결과 : " + result);
	}
	
	@Test
	public void testUpdate() {
		long rno = 11L;
		ReplyVO vo = mapper.read(rno);
		vo.setReply("Update 댓글 test...");
		int result = mapper.update(vo);
		log.info("update 결과 : "+result);
		
		vo = mapper.read(rno);
		log.info("update 후의 replyVO" + vo);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListwithPaging(cri, bnoArr[3]);
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListwithPaging(cri, 1L);
		replies.forEach(reply -> log.info(reply));
	}
}
