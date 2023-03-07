import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class AVL {
	int counterr=0;
	public FileWriter fileWriter;
	
	public class Node{
		String key;
		Node left;
		Node right;
		int height;
		FileWriter fileWriter;
		
		public Node(Node left, String key,Node right) {
			this.left=left;
			this.key=key;
			this.right=right;
			 this.height=0;
		}
		public Node(String key) {
			this.key=key;
			left=right=null;
			height=0;
		}	
		public void setKey(String key) {
			this.key = key;
		}
}
	public Node  setKey(String key) {
		return rootNode=new Node(key);
	}
	ArrayList<String> finArrayList=new ArrayList<>();
	public ArrayList<String> print(String statement){
		
		
		finArrayList.add(statement);
		return finArrayList;
	}
	public AVL(FileWriter fileWriter) {
		this.fileWriter=fileWriter;
	}
	
	
	
	Node rootNode;
	public AVL() {
		rootNode=null;
	}
	
	
	public AVL(String key) {
		rootNode=new Node(key);
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
	public boolean ifempty() {
		return rootNode==null;
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
	private int height( Node currentNode )
    {
        if(currentNode==null) {
        	return -1;
        }
        return currentNode.height;
    }
	
	public void deletion(String key) throws IOException{
		rootNode=deletionMain(rootNode,key);
	}
	
	String parentString[]=new String[1];
	String edgeString[]=new String[1];
	String deletedString;
	boolean flag=true;
	boolean flag2=true;
	boolean flag3=true;
	
	public Node deletionMain(Node current,String key) throws IOException {
		
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
				
				
				return balance(current.left);
			}
			else if(current.left==null) {
				
				return balance(current.right);
			}
		
			
				
				deletedString=current.key;
				
				current.key=findMin(current.right).key;
				flag=false;
				if(rootNode.key!=key)
				fileWriter.write(parentString[0]+": " +"Non Leaf Node Deleted; removed: "+deletedString+" replaced: "+current.key+"\n");
				
				
				
				
				current.right=deletionMain(current.right, current.key);
				flag=true;
				
			

		
		}
		flag2=true;
		return balance(current);
	
	}
	public void insert(String key)throws IOException  {
		rootNode=insertMain(rootNode,key);
		
	}
	public Node insertMain(Node current,String key)throws IOException  {
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
		flag2=true;
		return balance(current);
	}
	  private static final int threshold = 1;
	private Node  balance( Node currentNode )throws IOException 
    {
        if( currentNode == null )
            return currentNode;

        if( height( currentNode.left ) - height( currentNode.right ) > threshold )
            if( height( currentNode.left.left ) >= height( currentNode.left.right ) )
                currentNode = rotateWithLeftChild( currentNode );
            else
                currentNode = doubleWithLeftChild( currentNode);
        else
        if( height( currentNode.right ) - height( currentNode.left ) > threshold )
            if( height( currentNode.right.right ) >= height( currentNode.right.left ) )
            	currentNode = rotateWithRightChild( currentNode);
            else
            	currentNode = doubleWithRightChild( currentNode );

        currentNode.height = Math.max( height( currentNode.left ), height( currentNode.right ) ) + 1;
        return currentNode;
    }
	
	String indett=null;
	
	 private Node rotateWithLeftChild( Node currentNode )throws IOException 
	    {
		 	if(flag2) {
		 		fileWriter.write("Rebalancing: right rotation"+"\n");
		 		
		 	flag2=true;
		 	}
		 	
	        Node newNode = currentNode.left;
	        currentNode.left = newNode.right;
	        newNode.right = currentNode;
	        currentNode.height = Math.max( height( currentNode.left ), height( currentNode.right ) ) + 1;
	        newNode.height = Math.max( height( newNode.left ), currentNode.height ) + 1;
	        return newNode;
	    }
	 private Node rotateWithRightChild( Node currentNode )throws IOException 
	    {	
		 if(flag2) {
			
			 fileWriter.write("Rebalancing: left rotation"+"\n");
		 	flag2=true;
		 }
		
	        Node newNode = currentNode.right;
	        currentNode.right = newNode.left;
	        newNode.left = currentNode;
	        currentNode.height = Math.max( height( currentNode.left ), height( currentNode.right ) ) + 1;
	        newNode.height = Math.max( height( newNode.right ), currentNode.height ) + 1;
	        return newNode;
	    }
	 private Node doubleWithLeftChild( Node currentNode )throws IOException 
	 {  
		 flag2=false;
	 	
		 fileWriter.write("Rebalancing: left-right rotation"+"\n");
		
		 currentNode.left = rotateWithRightChild( currentNode.left );
	        return rotateWithLeftChild( currentNode );
	    }
	 private Node doubleWithRightChild( Node currentNode )throws IOException 
	    { 
		 flag2=false;
		  
		 
		 fileWriter.write("Rebalancing: right-left rotation"+"\n");
		 
		 
		 
		 currentNode.right = rotateWithLeftChild(  currentNode.right );
	        return rotateWithRightChild(  currentNode );
	    }
	 public void checkBalance( )
	    {
	        checkBalance( rootNode );
	    }

	    private int checkBalance( Node currentNode )
	    {
	        if( currentNode == null )
	            return -1;

	        if( currentNode != null )
	        {
	            int hl = checkBalance( currentNode.left );
	            int hr = checkBalance( currentNode.right );
	            if( Math.abs( height( currentNode.left ) - height( currentNode.right ) ) > 1 ||
	                    height( currentNode.left ) != hl || height( currentNode.right ) != hr ) {
	            	
	            }
	                
	        }

	        return height( currentNode );
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
		public Node distanceRoot(Node currentNode ,String key)throws IOException {
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
					
					
					distanceRoot(currentNode.right, key);
				}
				
			}
			else if(ifNodePresent(initial.left, senderrString)&&ifNodePresent(initial.right, recieverString)) {
				if(key.equals(senderrString)) {
					if(compareval<0) {
						
						myArrayList.add(currentNode.left.key);
						myArrayList.add(currentNode.key);
						if(currentNode.left.key.equals(key)) {
							
							//Collections.reverse(myArrayList);
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
							
							//Collections.reverse(myArrayList);
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
							
							//Collections.reverse(myArrayList);
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
							
							//Collections.reverse(myArrayList);
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
