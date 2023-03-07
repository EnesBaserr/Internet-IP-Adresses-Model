import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



import java.io.FileWriter;
public class BST {
	public FileWriter fileWriter;
	public class Node{
		String key;
		Node left;
		Node right;
		FileWriter fileWriter;
		public Node(Node left, String key,Node right) {
			this.left=left;
			this.key=key;
			this.right=right;
		}
		public Node(String key) {
			this.key=key;
			left=right=null;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		
		
	}
	ArrayList<String> finArrayList=new ArrayList<>();
	Node rootNode;
	public BST(){
		rootNode=null;
	}
	public BST(FileWriter fileWriter) {
		this.fileWriter=fileWriter;
	}
	
	public BST(String key){
		rootNode=new Node(key);
	}
	public Node  setKey(String key) {
		return rootNode=new Node(key);
	}
	public boolean ifNodePresent(Node node, String val) {
	    if(node == null) {
	      return false;
	    }
	    
	    boolean isPresent = false;
	    
	    while(node != null) {
	    int compareval=val.compareTo(node.key);
	      if(compareval<0) {
	        node = node.left;
	      } else if(compareval>0) {
	        node = node.right;
	      } else {
	        isPresent = true;
	        break;
	      }
	    }
	    
	    return isPresent;
	  }

	public Node findMin(Node currentNode) {
		if(currentNode==null) {
			return currentNode;
			
		}
		else if(currentNode.left==null){
			return currentNode;
			
		}
		else {
			return findMin(currentNode.left);
		}
		
		
	}
	public Node findMax(Node currentNode) {
		if(currentNode==null) {
			return currentNode;
			
		}
		else if(currentNode.right==null){
			return currentNode;
			
		}
		else {
			return findMin(currentNode.right);
		}
		
		
	}
	public void insert(String key)throws IOException {
		
		rootNode=insertMain(rootNode,key);
		
	}
	public Node insertMain(Node current,String key)throws IOException {
		if(current==null) {
			current=new Node(key);
			return current;
		}
		int compareVal = key.compareTo(current.key);
		if(compareVal<0) {
			
			fileWriter.write(current.key+": New node being added with IP:"+key+"\n");
			current.left=insertMain(current.left, key);
			
		}			
		
		
		else if(compareVal>0) {
			fileWriter.write(current.key+": New node being added with IP:"+key+"\n");
		
			current.right=insertMain(current.right, key);
		}
		else {
			
		}

		return current;
	}
	public void deletion(String key)throws IOException {
		rootNode=deletionMain(rootNode,key);
	}
	String parentString[]=new String[1];
	String edgeString[]=new String[1];
	String deletedString;
	boolean flag=true;
	public Node deletionMain(Node current,String key) throws IOException{
		
		if(current==null) {
			return current;
		}
		int compareVal = key.compareTo(current.key);
		
		if(compareVal<0) {
			
			if(current.left.key.equals(key)&&flag) {
				
				if(current.left.left==null&&current.left.right==null){
					
					fileWriter.write(current.key+": Leaf Node Deleted: "+key+"\n");
				}
				
			
		
				else if(current.left.left==null||current.left.right==null){
					
					
					
					fileWriter.write(current.key+": Node with single child Deleted: "+key+"\n");
					
					}
					
					
				else if(current.left.left!=null&&current.left.right!=null) {
					 parentString[0]=current.key;
					
				}
				
				
			}
			
			current.left=deletionMain(current.left, key);
			
		}			
		
		else if(compareVal>0) {
			if(current.right.key.equals(key)&&flag) {
				
				
				if(current.right.left==null&&current.right.right==null){
					
					fileWriter.write(current.key+": Leaf Node Deleted: "+key+"\n");
				}
				
			
			
				else if(current.right.left==null||current.right.right==null){
					
					
					
					fileWriter.write(current.key+": Node with single child Deleted: "+key+"\n");
					
					}
				else if(current.right.left!=null&&current.right.right!=null) {
					 parentString[0]=current.key;
					 
					
				}
			}
				
			
		
			
			current.right=deletionMain(current.right, key);
			
	}
		
		
		if(compareVal==0) {
			
			if(current==rootNode) {
				
				return current;
			}
			
			
			
			if(current.right==null) {
				
				
				return current.left;
			}
			else if(current.left==null) {
				
				return current.right;
			}
			
			
				
				deletedString=current.key;
				
				current.key=findMin(current.right).key;
				flag=false;
				if(rootNode.key!=key)
				fileWriter.write(parentString[0]+": " +"Non Leaf Node Deleted; removed: "+deletedString+" replaced: "+current.key+"\n");
				
				
				
				
				current.right=deletionMain(current.right, current.key);
				flag=true;
				
			

		}
		return current;
	
	}
	static boolean NodeExists( Node node, String key)
	{
	    if (node == null)
	        return false;
	 
	    if (node.key.equals(key))
	        return true;
	 
	    // then recur on left subtree /
	    boolean res1 = NodeExists(node.left, key);
	   
	    // node found, no need to look further
	    if(res1) return true;
	 
	    // node is not found in left,
	    // so recur on right subtree /
	    boolean res2 = NodeExists(node.right, key);
	 
	    return res2;
	}
	 
	String senderrString;
	String recieverString;
	int counter=0;
	Node initial=null;
	
	ArrayList<String> myArrayList2=new ArrayList<>();
	boolean flagg=false;
	
	public void send(String key1,String key2)throws IOException {
		
		distancetwoNode(rootNode, key1, key2);
	}
	ArrayList<String> myArrayList=new ArrayList<>();
	public Node distanceRoot(Node currentNode ,String key) throws IOException{
		
		int compareval=key.compareTo(currentNode.key);
		if(initial.key.equals(senderrString)&&key==senderrString){
			
			
			return currentNode;
		}
		
		else if(initial.key.equals(senderrString)&&key==recieverString) {
			
			if(compareval<0) {
				if(currentNode.left.key.equals(key)) {
				
					fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
					return currentNode;
				}
				
				fileWriter.write(currentNode.left.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
				distanceRoot(currentNode.left, key);
			}
			else if(compareval>0) {
				if(currentNode.right.key.equals(key)) {
					
					fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
					return currentNode;
				}
				
				fileWriter.write(currentNode.right.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
				distanceRoot(currentNode.right, key);
				
			}
		}
		else if(initial.key.equals(recieverString)&&key==recieverString) {
			return currentNode;
		}
		else if(initial.key.equals(recieverString)&&key==senderrString) {
	
			if(compareval<0) {
				
				myArrayList.add(currentNode.left.key);
				myArrayList.add(currentNode.key);
				if(currentNode.left.key.equals(key)) {
					
					//Collections.reverse(myArrayList);
					for(int i = 0;i<myArrayList.size()&&i<myArrayList.size()-2;i++) {
						//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
						
						fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					i++;
					}
			
					fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
					myArrayList.clear();
					return currentNode;
				}	
				
				
				distanceRoot(currentNode.left, key);
			}
			else if (compareval>0) {
				myArrayList.add(currentNode.right.key);
				myArrayList.add(currentNode.key);
				
				if(currentNode.right.key.equals(key)) {
					
					for(int i = 0;i<myArrayList.size()&&i<myArrayList.size()-2;i++) {
						//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
						
						fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					i++;
					}
					
					fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
					myArrayList.clear();
					return currentNode;
				}	
				
				
				distanceRoot(currentNode.right, key);
			}
			
		}
		else if(ifNodePresent(initial.left, senderrString)&&ifNodePresent(initial.right, recieverString)) {
			if(key.equals(senderrString)) {
				if(compareval<0) {
					
					myArrayList.add(currentNode.left.key);
					myArrayList.add(currentNode.key);
					if(currentNode.left.key.equals(key)) {
						
						for(int i = 0;i<myArrayList.size();i++) {
							//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
							fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
						i++;
						}
						myArrayList.clear();
				
						return currentNode;
					}	
					
					
					distanceRoot(currentNode.left, key);
				}
				else if (compareval>0) {
					myArrayList.add(currentNode.right.key);
					myArrayList.add(currentNode.key);
					
					if(currentNode.right.key.equals(key)) {
						
						for(int i = 0;i<myArrayList.size();i++) {
							//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
							fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
							i++;
						}
						myArrayList.clear();
						return currentNode;
					}	
					
					
					distanceRoot(currentNode.right, key);
				}
				
			}
			else if(key.equals(recieverString)) {
				if(compareval<0) {
					if(currentNode.left.key.equals(key)) {
						
						fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
						return currentNode;
					}
					
					fileWriter.write(currentNode.left.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					distanceRoot(currentNode.left, key);
				}
				else if(compareval>0) {
					if(currentNode.right.key.equals(key)) {
						fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
						return currentNode;
					}
					
					fileWriter.write(currentNode.right.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					distanceRoot(currentNode.right, key);
					
				}
				
			}
			
		}
		else if(ifNodePresent(initial.right, senderrString)&&ifNodePresent(initial.left, recieverString)) {
			if(key.equals(senderrString)) {
				if(compareval<0) {
					
					myArrayList.add(currentNode.left.key);
					myArrayList.add(currentNode.key);
					if(currentNode.left.key.equals(key)) {
						
						for(int i = 0;i<myArrayList.size();i++) {
							//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
									fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
									i++;
								}
						myArrayList.clear();
				
						return currentNode;
					}	
					
					
					distanceRoot(currentNode.left, key);
				}
				else if (compareval>0) {
					myArrayList.add(currentNode.right.key);
					myArrayList.add(currentNode.key);
					
					if(currentNode.right.key.equals(key)) {
						
						for(int i = 0;i<myArrayList.size();i++) {
							//fileWriter.write(myArrayList.get(i)+": Transmission from: "+myArrayList.get(i+1)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
							fileWriter.write(myArrayList.get(myArrayList.size()-1-i)+": Transmission from: "+myArrayList.get(myArrayList.size()-2-i)+" receiver: "+recieverString+" sender:"+senderrString+"\n");
						i++;
						}
						myArrayList.clear();
						
						return currentNode;
					}	
					
					
					distanceRoot(currentNode.right, key);
				}
				
				
				
			}
			else if(key.equals(recieverString)) {
				if(compareval<0) {
					if(currentNode.left.key.equals(key)) {
					
						fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
						return currentNode;
					}
					
					fileWriter.write(currentNode.left.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					distanceRoot(currentNode.left, key);
				}
				else if(compareval>0) {
					if(currentNode.right.key.equals(key)) {
						fileWriter.write(recieverString+": Received message from: "+senderrString+"\n");
						return currentNode;
					}
					
					
					fileWriter.write(currentNode.right.key+": Transmission from: "+currentNode.key+" receiver: "+recieverString+" sender:"+senderrString+"\n");
					distanceRoot(currentNode.right, key);
					
				}
				
			}
		}
	return currentNode;
		
	}
		
		

		
	
	public void distancetwoNode(Node currentNode,String sender,String reciever)throws IOException{
		
		
		int compareResult1 = sender.compareTo(currentNode.key);
		int compareResult2 = reciever.compareTo(currentNode.key);
		
		if(compareResult1<0&&compareResult2<0) {
			
			 distancetwoNode(currentNode.left, sender, reciever);
		}
		if(compareResult1>0&&compareResult2>0) {
			
			 distancetwoNode(currentNode.right, sender, reciever);
		}
		
		
		if(compareResult1>=0&&compareResult2<=0) {
			
			initial=currentNode;
			senderrString=sender;
			recieverString=reciever;
			
			fileWriter.write(sender+": Sending message to: "+reciever+"\n");
			
			distanceRoot(currentNode,sender);
			
			distanceRoot(currentNode,reciever);
			
			
		}
		if(compareResult1<=0&&compareResult2>=0) {
			initial=currentNode;
			senderrString=sender;
			recieverString=reciever;
			
			
			fileWriter.write(sender+": Sending message to: "+reciever+"\n");
			
			distanceRoot(currentNode,sender);
			
			distanceRoot(currentNode,reciever);
			
		}		
	}
		

	
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

