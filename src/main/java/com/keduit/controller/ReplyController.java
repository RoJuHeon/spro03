package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping(value="/new",
			consumes = "application/json",//입력받는곳은 json로 받아서
			produces = {MediaType.TEXT_PLAIN_VALUE})//text로 출력
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		
		log.info("ReplyVO : " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("댓글 입력 count : " + insertCount);
		
		return insertCount == 1
				? new ResponseEntity<>("success", HttpStatus.OK)//데이터만 날림
				: new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}",//{}쓸려면 PathVariable 써줘야함
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(//t_reply 댓글목록
			@PathVariable("page") int page,//해당 페이지
			@PathVariable("bno") Long bno){//게시글 하나에 달리는 댓글들 bno가 중요
		log.info("reply getList..." + page + "," + bno);
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		
		return new ResponseEntity<>(
				service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("reply get : " + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("reply remove..." + rno);
		
		return service.remove(rno) == 1
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})//어떤 형식으로 처리할 형식
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo,//modify는 TEXT_PLAIN_VALUE형식으로 리턴
			@PathVariable("rno") Long rno){//vo를 json형식으로 받음
		
		vo.setRno(rno);//rno - 댓글번호
		log.info("modify reply rno --> " + rno);
		log.info("modify reply vo --> " + vo);
		
		return service.modify(vo) == 1//결과가 1이면
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
