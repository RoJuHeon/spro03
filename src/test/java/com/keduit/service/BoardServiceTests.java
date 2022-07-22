package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//JUnit4클래스를 통해서 test를 할거야
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//환경을 갖고 있음
@Log4j//log를 통해 출력해서 볼 수 있다
public class BoardServiceTests {
	@Setter(onMethod_= @Autowired)
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("서비스 등록 글 제목");
		board.setContent("서비스 등록 글 내용");
		board.setWriter("작성자1");
		long bno = service.register(board);//service에 register에 board 호출
		
		log.info("insert 게시물 번호 : " + bno);
	}
	
	//여러개 읽어오기
//	@Test
//	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
//	}
	
	@Test
	public void testGetList() {
		service.getList(new Criteria(5,10)).forEach(board-> log.info(board));
	}
	
	//한 건 읽어오기
	@Test
	public void testGet() {
		log.info(service.get(4L));
	}
	
	//삭제하기
	@Test
	public void testDelete() {
		log.info("삭제 결과 : " + service.remove(18L) );
	}
	
	//수정하기 - 업데이트
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		if(board == null) {
			return;
		}
		board.setTitle("서비스에 수정한 제목");
		log.info("수정 결과 : " + service.modify(board));
	}
}	
