package paging.request;


import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public  class RequestPaging implements Serializable, Pageable{

	private static final long serialVersionUID = 1L;
    public Object obj;
    public int totalData;
	
	
	public PageRequest createRequestPage(int page,String direction, String orderBy) {
		String[] splliterOrder = orderBy.split(",");
		Direction sort = null;
		if(direction.equals("ASC")) {
			sort = Sort.Direction.ASC;
		}
		else if(direction.equals("DESC")) {
			sort = Sort.Direction.DESC;
		}
		PageRequest pr = new PageRequest(page-1, 10,sort,splliterOrder);
		return pr;
	}
	
	public Object getObject() {
		return obj;
	}
	
	public int getTotalData() {
		return totalData;
	}
	
	
	
	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

}
