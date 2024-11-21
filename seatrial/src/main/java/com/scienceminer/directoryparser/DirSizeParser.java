package com.scienceminer.directoryparser;

import com.scienceminer.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

enum FileType {
	DIR, FILE;
}
class Node {
	
	String name;
	FileType type;
	List<Node> children;
	int fileSize;
	int totalFileSize;
	int totalDirectorySize;
	Node parent;


	Node(Node p, String name, FileType f, Integer size) {
		this.name = name;
		this.children = new ArrayList<>();
		this.fileSize = size;
		this.totalFileSize = 0;
		this.totalDirectorySize = 0;
		this.type = f;
		this.parent = p;
	}
	
	Node(String name, FileType f) {
		this(null,name,f,0);
	}	
	
	Node(String name, FileType f, Integer size) {
		this(null,name,f,size);
	}	
	
	Node(Node p, String name, FileType f ) {
		this(p,name,f,0);
	}
	
	public Node getChosenNode(String nodeName) {
		List<Node> children = this.children;
		
		for (Node n : children) {
			if (nodeName.equals(n.name)) {
				return n;
			}
			
		}
		
		return null;
	}
	
	public  Node addDirectory(Node parent, String directoryName) {
		Node directoryNode = new Node(parent,directoryName,FileType.DIR);
		System.out.println(" addDir::adding children " + parent.children.size() + " with parent " + parent.name);
		parent.children.add(directoryNode);
		return directoryNode;
	}

	public  void addFile(Node parent, String fileName, Integer fileSize) {
		Node fileNode = new Node(parent,fileName,FileType.FILE,fileSize);
		parent.totalFileSize = parent.totalFileSize + fileSize;
		System.out.println(" addFile::adding children " + parent.children.size() + " with parent " + parent.name );
		System.out.println(" addFile::fileSize " + fileSize + " totalFileSize " + parent.totalFileSize );
		parent.children.add(fileNode);
	}
	
	
	public Integer sumFileSizes() {
		System.out.println(" Summing for " + this.name);
		Integer totalFileSize = 0;
		for (Node n : this.children ) {
			if (n.type == FileType.FILE) {
				System.out.println("ADDING FILE: " + n.name + " with " + n.fileSize);				
				totalFileSize = totalFileSize + n.fileSize;
			}
			else if (n.type == FileType.DIR) {
				Integer dirSize = n.sumFileSizes();
				System.out.println(" ADDING DIR: " + n.name + " with " + dirSize );
				n.totalDirectorySize = dirSize;
				totalFileSize = totalFileSize + dirSize;							
			}
			
		}
		
		return totalFileSize;
	}
	
	public void printDirSizes() {
		for (Node n : this.children ) {
			if (n.type == FileType.DIR) {
				System.out.println(" DIR: " + n.name + " with " + n.totalDirectorySize);
			}
			n.printDirSizes();
		}
	}
	
	public Integer sumDirSizes(int total) {
		
		for (Node n : this.children ) {
			if (n.type == FileType.DIR) {
				System.out.println(" DIR: " + n.name + " with " + n.totalDirectorySize);
				if (n.totalDirectorySize <= 100000) {
					total = total + n.totalDirectorySize;
				}
			}
			total = n.sumDirSizes(total);
			
		}
		
		return total;
	}
	

	protected void printFiles( int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("  ");
		}
		System.out.println(this.name);
		for (Node child : this.children) {
			child.printFiles( level + 1);
		}
	}
	
	public String toString() {
		String resultString = null;
		if (this.type == FileType.FILE)
			resultString = " File: " + name + " SIZE: " + this.fileSize + "\n";
		if (this.type == FileType.DIR)
			resultString = " DIR: " + name + " \n" ;
		resultString = resultString +  " CHILDREN: " + this.children.size() + " " ;
		for (Node n : this.children) {
			resultString = resultString + " CHILD:\n" + n.toString() + "\n";
		}
		resultString = resultString + "\n TOTAL SIZE: " + this.totalFileSize ;
		return resultString;
	}
	
}

public class DirSizeParser {

	public static void main(String[] args) throws IOException {
		ArrayList<String> arrList  = FileUtils.readFileToArrayList("/dirParserInput.txt");
		// printTree(root, 0);
		int count = 0;
		Node root = new Node("root",FileType.DIR);
		Node crntNode = root;
		
		for (String s : arrList)  {
			System.out.println(count + " " + s);
			count++;
			String[] result = s.split("\\s+");
			
			if (s.startsWith("dir")) {
				// create dirNode for s1
				root.addDirectory( crntNode, result[1] );
			}
			
			if ( Character.isDigit(s.charAt(0))) {
				// add file after digits to current Node
				// update fileSum
				Integer number = 0;
				try{
		            number = Integer.valueOf(result[0]);
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
				root.addFile( crntNode, result[1], number );
			}
			if (s.contains("..")) {
				// set currentNode to parentNode
				crntNode = crntNode.parent;
			}
			else if (s.startsWith("$ cd ")) {
				// create childNode of CurrentNode with name of s[1]
				// set currentNode to s[1] node
				crntNode = crntNode.getChosenNode(result[2]);
				if (crntNode == null) {
					System.out.println("NODE is null for:\t "+ result[2] + " so create as root NODE ");
					
					crntNode = root.addDirectory( root, result[2] );
					
				}
			}

		}

		System.out.println(" NODES: " + root.toString());
		System.out.println(" File Size: " + root.sumFileSizes() );
		
		
		root.printFiles(0);
		
		int total = 0;
		int result = root.sumDirSizes(total);
		System.out.println(" Answer: " + result );
		
	}



}
