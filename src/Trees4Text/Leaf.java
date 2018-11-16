package Trees4Text;


import java.util.Vector;

import TextStuff.SampleText;





public class Leaf {
	
	/*Fields*/
	//size- holds the number of elements in this leaf
	private int size;
	//T- holds the T value of the tree. needed to ensure the leaf's size is legal
	private final int T;
	//next- points to the next leaf in the tree
	private Leaf next;
	//parent- points to the father of this leaf- a junction
	private Junction parent;
	//data- holds the numbers stored in this leaf
	private Vector<Link> data;
	
	
	/*Behavior*/
	/*Constructors*/
	//creates a new leaf from the given arguments
	public Leaf(Link key, int t, Junction parent, Leaf next, Leaf previous){
		this.T= t;
		this.size= 1;
		this.data= new Vector<Link>();
		this.next= next;
		this.parent= parent;
		if ((previous != null) && (next != null)){	//sets the new Link's 'next' and 'prev' values
			this.data.add(key);
			key.setNext(next.getFirst());
			key.setPrev(previous.getLast());
			previous.setNext(this);
		} 
		else if ((previous == null) && (next != null)){
			this.data.add(key);
			key.setNext(next.getFirst());
			key.setPrev(null);
		}
		else if ((previous != null) && (next == null)){
			this.data.add(key);
			key.setNext(null);
			key.setPrev(previous.getLast());
			previous.setNext(this);
		}
		else {
			this.data.add(key);
			key.setNext(null);
			key.setPrev(null);
		}
	}//Leaf(Link, int, Junction, Leaf, Leaf)
	
	
	//returns the last Link in this leaf
	protected Link getLast(){
		return this.data.lastElement();
	}//getLast()

	
	//gets the first Link in this leaf
	protected Link getFirst(){
		return this.data.firstElement();
	}//getFirst()
	
	
	//inserts a new key into the leaf. returns the minimal gap between this link and the adjacent ones
	protected void insert(Link key){
		boolean stop= false;
		int location;	//will point to the location of the new key in this leaf
		for(location=0; ((location < this.data.size()) && !stop); location++){	//finds where to place the new key
			
			if(this.data.elementAt(location).getElement().getWord().compareTo(key.getElement().getWord()) == 0  && //Check if have the same word
					this.data.elementAt(location).getElement().getBlock() == key.getElement().getBlock()){//Check if have the same block
				this.data.elementAt(location).setRepeatWordsCound(this.data.elementAt(location).getRepeatWordsCound()+1);//then increment the count
				return;
			}else if (((this.data.elementAt(location)).getElement().getWord()).compareTo(key.getElement().getWord()) > 0) {
				stop= true;	
			}
		}
		if (stop)	//due to the way 'for' loops works, this is needed
			location= location-1;
		if((location != 0) && (location != this.data.size())){	//if the new link should anywhere but first or last
			key.setNext(this.data.elementAt(location));	//sets all of the link's pointers
			key.setPrev(data.elementAt(location-1));
			this.data.elementAt(location-1).setNext(key);
			this.data.elementAt(location).setPrev(key);
			this.data.add(location, key);
		}
		else if((location == 0) && (location != this.data.size())){	//if the new link should be the first in this leaf
			key.setNext(this.data.elementAt(location));
			key.setPrev(this.data.elementAt(location).getPrev());
			if (this.data.elementAt(location).getPrev() != null)
				this.data.elementAt(location).getPrev().setNext(key);
			this.data.elementAt(location).setPrev(key);
			this.data.add(location, key);
		}
		else if((location != 0) && (location == this.data.size())){	//if the new link should be last in this leaf
			key.setNext(this.data.elementAt(location-1).getNext());
			key.setPrev(this.data.elementAt(location-1));
			this.data.elementAt(location-1).setNext(key);
			if (key.getNext() != null)
				(key.getNext()).setPrev(key);
			this.data.add(key);
		}	
		this.size= this.data.size();	//updates the leaf's size
	}//insert(Link)
	
	
	//returns 'true' if this leaf is too big
	protected boolean overflow(){
		return (this.size > this.T-1);
	}//overflow()
	

	//splits this leaf into 2- updates this leaf to be the left half, and returns the right half
	protected Leaf split(Junction parent){
		int half= this.size - this.size/2;
		Leaf newLeaf= new Leaf(this.data.remove(half), this.T, parent, this.next, this);	//creates a new leaf with the middle link of this leaf
		this.size= this.data.size();
		newLeaf.getFirst().setPrev(this.data.elementAt(half-1));	//lets the pointers for the new leaf's only key
		while (half < this.size){	//removes the rest of the right half elements in this leaf
			newLeaf.insert(this.data.remove(half));	//insertion will take care of the new link's pointers
			this.size= this.data.size();	//update the size of this leaf
		}
		this.next= newLeaf;	//sets this leaf's pointers right
		return newLeaf;
	}//split(Junction)
	
	
	//finds the location of x in this leaf- if not found, returns 'null'
	public Link find(String word) {
		for (int i=0; i < this.data.size(); i++){	//searches the leaf
			if (word.compareTo(this.data.elementAt(i).getElement().getWord())==0 || 
					word.compareTo(this.data.elementAt(i).getElement().getWord())<0) {
				return this.data.elementAt(i);
			}
		}
		return null;	//in case x was not found- null tells us to insert x at the end of the Leaf
	}//find(int)
	
	
	//returns the links of this leaf
	protected Vector<Link> getData(){
		return this.data;
	}//getData()
	
	
	//returns the next leaf in the list
	protected Leaf getNext(){
		return this.next;
	}//getNext()
	
	
	//sets the next leaf in the list
	protected void setNext(Leaf next){
		this.next= next;
	}//setNext()
	
	
	//returns the parent of this leaf
	protected Junction getParent(){
		return this.parent;
	}//getParent()
	
	
	//sets the parent of this leaf
	protected void setParent(Junction parent){
		this.parent= parent;
	}//setParent
	
	
	//returns the size of this link
	protected int getSize(){
		return this.size;
	}//getSize()
	
	
	//overrides the toString from Object class
	//returns a string representation of this link
	public String toString(){
		String ans= "";
		for (int i= 0; i < this.data.size(); i++){
			ans+= this.data.elementAt(i) + ",";
		}
		return ans.substring(0, ans.length()-1);
	}//toString()
	
	

}//Leaf
