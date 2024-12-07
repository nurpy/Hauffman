import java.util.*;




class Huffman{
	static class Node implements Comparable<Node>{
		Node left;
		Node right;
		int frequency;
		char Character;
		boolean charNode;
		public Node(char Character, int frequency){
			left=null;
			right=null;
			charNode= true;
			this.Character=Character;
			this.frequency = frequency;
		}
		public Node(int frequency){
			left=null;
			right=null;
			charNode= false;
			this.frequency = frequency;
		}
		public boolean getIsChar(){
			return charNode;
		}
		public int compareTo(Node compared){
			if(this.frequency > compared.frequency){return 1;}
			else if(this.frequency < compared.frequency){return -1;}
			else{return 0;}
			

		}
		public String toString(){
			return " [" + this.frequency + this.Character + " ] " + left + right;

		}
	}
	static HashMap<Character, String> encodings;
	public static String decodeStrem(String str,Node tree){
		Node head=tree;
		String script="";
		for(char i : str.toCharArray())
		{
			if(i == '0'){tree=tree.left;}
			if(i == '1'){tree=tree.right;}
			if(tree.right == null && tree.left == null)
			{
				script+=tree.Character;
				tree=head;
			}
		}
		return script;


	}
	public static void buildEncoding(String curBits, Node tree){
		if(tree.left == null && tree.right == null){
			//add to map
			//is char
			encodings.put(tree.Character,curBits);
			
		}
		if(tree.left != null){buildEncoding(curBits+"0",tree.left);}
		if(tree.right != null){buildEncoding(curBits+"1",tree.right);}


	}
	public static void encodeStream(String str,Node tree){
		encodings = new HashMap<Character,String>();
		buildEncoding("",tree);

	}
	public static void InOrderHelper(Node cur){
		if(cur == null)
		{
			return;
		}
		

		if (cur.getIsChar()) {System.out.println(cur.frequency + " = " + cur.Character);}
		if(cur.left != null){InOrderHelper(cur.left);}
		if(cur.right != null){InOrderHelper(cur.right);}
		return;
	
	}
	public static void InOrderTraverse(PriorityQueue<Node> pq){
		Node cur =  pq.remove();
		InOrderHelper(cur);
	
	
	}
	public static PriorityQueue<Node> huffmanCode(PriorityQueue<Node> pq){
		while(pq.size() != 1)
		{
			Node smallest = pq.remove();
			Node secondSmallest = pq.remove();
			Node rep = new Node('s',smallest.frequency+ secondSmallest.frequency );
			rep.left=smallest;
			rep.right=secondSmallest;
			pq.add(rep);
		}

		System.out.println(pq.peek().right.Character);
		return pq;

	}
	public static void main(String[] args){
		String text = "Hello THis is the starter Scirpt in a file";
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		for(char i : text.toCharArray())
		{
			if(map.containsKey(i))
			{
				int occurences = map.get(i);
				map.put(i,occurences+1);
			}
			else
			{
				map.put(i,1);
			}
		}

		//priority Queue
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		//iterate through hashmap

		for (Map.Entry<Character, Integer> entry : map.entrySet() )
		{
			
			Node newEntry = new Node(entry.getKey() , entry.getValue() );
			pq.add(newEntry);
		}
		System.out.println(map);
		pq = huffmanCode(pq);

		encodeStream(text,pq.peek());
		System.out.println(decodeStrem("00011010",pq.peek()));
		System.out.println(encodings);


//		InOrderTraverse(pq);

	}






}
