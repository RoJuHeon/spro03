package com.keduit.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//JUnit4클래스를 통해서 test를 할거야
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//환경을 갖고 있음
@Log4j//log를 통해 출력해서 볼 수 있다


public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));//forEach는 board이름으로 한개씩 꺼내서 콘솔창에 보여줘
	}
	
	//게시글 수 가져오기 테스트
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(5);
		cri.setAmount(10);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));//하나씩 꺼내와서 board로 뿌려
		
	}
	
	//게시물 등록 테스트
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 테스트");
		board.setContent("내용 테스트임.");
		board.setWriter("tester01");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	//데이터 주입 테스트
	@Test
	public void testInsertKey() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 테스트 insert key");
		board.setContent("내용 테스트임. select key");
		board.setWriter("tester02");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	//존재하는 게시물 번호 테스트
	@Test
	public void testRead() {
		BoardVO board = new BoardVO();
		board = mapper.read(3L);
		
		log.info(board);
	}
	
	//게시물 삭제 테스트
	@Test
	public void delete() {
		int result = mapper.delete(2L);
		log.info("delete...");
		log.info("result => " + result);
	}
	
	//게시물 수정 테스트
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("tester00");
		board.setBno(4L);
		
		int result = mapper.update(board);
		log.info(".......");
		log.info("update result => " + result);
	}
	
	//개시물 검색 테스트
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("테스트");
		//읽어온만큼 갯수를 보여줘야 함 
		cri.setType("TC");//total count, 검색하는것만 보여줘야 되서
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
		int total = mapper.getTotalCount(cri);
		log.info("test total count..." + total);
	}
	
	
	@Test
	public void testRemove() {
		Criteria cri = new Criteria();
		
		
	}
	
}
