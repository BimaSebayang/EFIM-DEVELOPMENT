package paging.slice;

import java.io.Serializable;
import java.util.List;


public class SlicePaging<T> implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -3160748813127795268L;
	
		public List<T> obj;
	    public Long totalData;
	    
	    public SlicePaging() {
	    	
	    }
		
		public SlicePaging(List<T> obj, Long totalData) {
			this.obj = obj;
			this.totalData = totalData;
		}
		
		public List<T> getObject() {
			return obj;
		}
		
		public Long getTotalData() {
			return totalData;
		}
		
		
	
}
