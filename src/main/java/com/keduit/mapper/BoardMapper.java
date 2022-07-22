package com.keduit.mapper;

import java.util.List;
import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from t_board where bno > 0") -> xml로 돌리겠다
	public List<BoardVO> getList();//BoardVOd타입의 List 리턴
	//BoardMapper.xml과 연결되어 있음
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
}
