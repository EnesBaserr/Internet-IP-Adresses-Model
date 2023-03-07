import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
	public static void main(String[] args)throws IOException {	
		
	
		
		
		
			File inputsFile = new File(args[0]);
			Scanner reader = new Scanner(inputsFile);
			File bstOut = new File(args[1]+"_bst.txt");
			File avlOut = new File(args[1]+"_avl.txt");
			
			FileWriter fileWriter=new FileWriter(avlOut);
			FileWriter fileWriter2=new FileWriter(bstOut);
			
			AVL AVL2=new AVL(fileWriter);
			BST BST2=new BST(fileWriter2);
			
			int counter=0;
			
			while(reader.hasNextLine()) {
				String inputInfo = reader.nextLine();
				String[] inputArray = inputInfo.split(" ");
				if(counter==0) {
					BST2.setKey(inputArray[0]);
					AVL2.setKey(inputArray[0]);
					counter++;
					continue;
					
					
					
				}
				
				else  {
					if(inputArray[0].equals("ADDNODE")) {
						
						BST2.insert(inputArray[1]);
						AVL2.insert(inputArray[1]);
						
						
						
						
						
						
					}
					if(inputArray[0].equals("DELETE")) {
						
						BST2.deletion(inputArray[1]);
						AVL2.deletion(inputArray[1]);
					
						
						
					}
					if(inputArray[0].equals("SEND")) {
						
						BST2.send(inputArray[1], inputArray[2]);
						AVL2.send(inputArray[1], inputArray[2]);
						
					}
					
					
					
				}
				
				
			}
			
			
			
			fileWriter.close();
			fileWriter2.close();
			reader.close();
			
			
			
		
		
	}
}
			
			
		 
	


