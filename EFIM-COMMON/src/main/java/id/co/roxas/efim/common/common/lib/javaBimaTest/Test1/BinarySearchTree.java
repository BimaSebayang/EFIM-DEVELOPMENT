package id.co.roxas.efim.common.common.lib.javaBimaTest.Test1;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

	static List<Integer> listR(Node root){
		 List<Integer> listValueR = new ArrayList<>();
		 boolean rIsnull = true;
		 Node tempNode = root; 
			while(rIsnull) {
				if(tempNode.right!=null) {
					tempNode = tempNode.right;
					listValueR.add(tempNode.value);
				} 
				else {
					rIsnull = false;
				}
			}
		return listValueR;	
	}
	
	
    static List<Integer> listL(Node root){
    	List<Integer> listValueL = new ArrayList<>();
  		boolean lIsnull = true;
		 Node tempNode = root; 
			while(lIsnull) {
				if(tempNode.left!=null) {
					tempNode = tempNode.left;
					listValueL.add(tempNode.value);
				} 
				else {
					lIsnull = false;
				}
			}
		return listValueL;
	}
	
	public static boolean contains(Node root, int value) {	
		if(listL(root).contains(value)||listR(root).contains(value)) {
		return true;
		}
		else {
			return false;
		}
		
    }
    
    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, null, null);
        Node n4 = new Node(4, null, null);
        
        System.out.println(contains(n4, 3));
    }
    
}
