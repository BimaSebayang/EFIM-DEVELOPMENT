package webservice.global;

import java.io.Serializable;

import com.google.gson.Gson;

public class WsResponse implements Serializable{
	private static final long serialVersionUID = 5631412733795499376L;
	private String  wsContent;
	private String  message = "Success";
    private Integer totalContent;
    private Boolean isPaging;
    private Integer page;
    private Integer size;
    private Boolean isErrorSvc;
    private String  errorCmd;
    private String  orderBy;
    private String  direction;
	
    public WsResponse() {
	
    }
    
    public WsResponse(String wsStringContent,Integer totalContent) {
    	this.wsContent = wsStringContent;
    	if(wsStringContent == null) {
            this.totalContent = 0;
      	}
      	else {
      		try {
        		this.totalContent = totalContent.intValue();
        	}catch (NullPointerException e) {
        		 this.message = "total content can't contain null";
       		     this.wsContent = "Access Denied";
    			 this.totalContent = 0;
    		}
      	}
    }
    
    public WsResponse(Object wsContent,Integer totalContent) {
    	this.wsContent = new Gson().toJson(wsContent);
    	if(wsContent == null) {
            this.totalContent = 0;
      	}
      	else {
      		try {
        		this.totalContent = totalContent.intValue();
        	}catch (NullPointerException e) {
        		 this.message = "total content can't contain null";
       		     this.wsContent = "Access Denied";
    			 this.totalContent = 0;
    		}
      	}
    }
    
	public WsResponse(Object wsContent,Integer totalContent,Boolean isPaging,Integer page,Integer size) 
	{
    	this.wsContent = new Gson().toJson(wsContent);
    	
    	if(wsContent == null) {
          this.totalContent = 0;
    	}
    	else {
    		try {
        		this.totalContent = totalContent.intValue();
        	}catch (NullPointerException e) {
        		 this.message = "total content can't contain null";
       		     this.wsContent = "Access Denied";
    			 this.totalContent = 0;
    		}
    	}
    	
    	
    	
    	try {
    	this.isPaging = isPaging.booleanValue();
    	}
    	catch(NullPointerException e) {
    		 this.message = "paging identifier contain null";
   		     this.wsContent = "Access Denied";
			 this.totalContent = 0;
    	}
    	
    	if(isPaging !=null) {
    	if(isPaging.booleanValue()) {
    	try {	
    	     this.page = page.intValue();
    	     this.size = size.intValue();
    	    }
    	catch(NullPointerException e) {
    		 this.message = "Page or Size Contain null";
    		 this.wsContent = "Access Denied";
			 this.totalContent = 0;
    	   }
    	}
        else {
    		this.page = 0;
        	this.size = 0;
    	 }
    	}
	
	}

	public String getWsContent() {
		return wsContent;
	}

	public void setWsContent(String wsContent) {
		this.wsContent = wsContent;
	}

	public Integer getTotalContent() {
		return totalContent;
	}

	public void setTotalContent(Integer totalContent) {
		this.totalContent = totalContent;
	}

	public Boolean getIsPaging() {
		return isPaging;
	}

	public void setIsPaging(Boolean isPaging) {
		this.isPaging = isPaging;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsErrorSvc() {
		return isErrorSvc;
	}

	public void setIsErrorSvc(Boolean isErrorSvc) {
		this.isErrorSvc = isErrorSvc;
	}

	public String getErrorCmd() {
		return errorCmd;
	}

	public void setErrorCmd(String errorCmd) {
		this.errorCmd = errorCmd;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	


}
