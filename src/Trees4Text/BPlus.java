package Trees4Text;


import java.util.ArrayList;
import java.util.Vector;

import TextStuff.SampleText;




public class BPlus {
	
	public Object root;
	private final int T;
	private int minGap;
	
	public BPlus(){
		this.root= null;
		this.T=5;
		this.minGap= Integer.MAX_VALUE;	
	}
	
	
	//insets the key 'x' into the tree
	public void insert(SampleText sample){
		int gap= Integer.MAX_VALUE;
		if (this.root == null){	//if this the first key, makes a leaf out of it
			Link newLink= new Link(sample);
			this.root= new Leaf(newLink, this.T, null, null,  null);
		}
		else {	//if this isen't the first key
			if (this.root instanceof Leaf){	//if the root is a leaf, adds it to that leaf
				((Leaf)this.root).insert(new Link(sample));
				if (((Leaf)this.root).overflow()){	//root is a Leaf, and also needs splitting. only happens once for each BPlus tree
					splitRoot();
				}
			}
			else {	//if root is a junction
				Object insertionPlace= ((Junction)this.root).find(sample.getWord(), true);	//finds where x should be inserted
				if (insertionPlace instanceof Leaf){	//the sons of the root are leafs
					Leaf insertPlace= (Leaf)insertionPlace;
					insertPlace.insert(new Link(sample));	//inserts the new key
					if (insertPlace.overflow()){	//if case the leaf is too big, splits it
						((Junction)this.root).splitSon(insertionPlace);
					}
					if (((Junction)this.root).overflow()){	//in case the root is now too big, splits it
						splitRoot();
					}
				}
				else {	//the sons of the root are junctions
					Leaf temp= searchHelper(sample.getWord(), insertionPlace, true);	//finds where x should be inserted
					temp.insert(new Link(sample));	//inserts the new key
					if (temp.overflow())	//if case the leaf is too big, splits it
						(temp.getParent()).splitSon(temp);
					Junction tempJunction= temp.getParent();
					boolean stop= false;
					while ((!stop) && (tempJunction != this.root)){
						if (tempJunction.overflow()){	//makes sure that all the junctionhs are of legal size
							(tempJunction.getParent()).splitSon(tempJunction);	//otherwise, splits them
							tempJunction= tempJunction.getParent();
						}
						else
							stop= true;
					}
					if ((tempJunction == this.root) && (((Junction)this.root).overflow())){	//if the root is now too big, splits it
						splitRoot();
					}
				}
			}
		}
		if (gap < this.minGap)
			this.minGap= gap;
	}//insert(int)
	
	
	//splits the roon into 2 junctions/leafs and makes a new root
	private void splitRoot(){
		if (this.root instanceof Leaf){
			Leaf newLeaf= ((Leaf)this.root).split(null);	//splits the root
			Vector<Link> newElement= new Vector<Link>();
			newElement.add(((Leaf)this.root).getLast());
			Vector<Object> newPointers= new Vector<Object>();
			newPointers.add(this.root);
			newPointers.add(newLeaf);
			Junction newRoot= new Junction(newElement, this.T, null, newPointers);	//updates all of the second half's fields
			((Leaf)this.root).setParent(newRoot);
			newLeaf.setParent(newRoot);
			this.root= newRoot;	//makes the newly creates root the only root for this tree
		}
		else {	//if the root is a junction
			Junction newJunction= ((Junction)this.root).split(null);	//splits the root
			Vector<Link> newElement= new Vector<Link>();
			newElement.add(((Junction)this.root).getLast());
			Vector<Object> newPointers= new Vector<Object>();
			newPointers.add(this.root);
			newPointers.add(newJunction);
			Junction newRoot= new Junction(newElement, this.T, null, newPointers);	//updates all of the second half's fields
			for (int i=0; i < newPointers.size(); i++){	//sets the parents for the split junctions
				Object temp= newPointers.elementAt(i);
				if (temp instanceof Leaf){
					Leaf tempLeaf= (Leaf)temp;
					tempLeaf.setParent(newRoot);
				}
				else {
					Junction tempJunction= (Junction)temp;
					tempJunction.setParent(newRoot);
				}
			}
			((Junction)this.root).setParent(newRoot);
			newJunction.setParent(newRoot);
			((Junction)this.root).removeLastElement();
			this.root= newRoot;	//makes the newly creates root the only root for this tree
		}
	}//splitRoot
	
	
	//finds 'x' whithin this tree. returns 'null' if x is not in this tree
	public Link search(String word){
			Leaf node= searchHelper(word, this.root, false);	//looks for the leaf where 'x' should be located
			Link temp=((Leaf)node).find(word);	//looks for 'x' in that leaf
			if (temp.getElement().getWord() == word) {	//if the link found is x, returns its location
				return temp;
			}else {
				return null;
			}
	}//search(int)
	
	
	//this method is used to lower the load off 'search', and also so it can be called recursively
	private Leaf searchHelper(String word, Object node, boolean isInserting){
		while (!(node instanceof Leaf)){	//while we have not found a leaf, keeps going deeper into the tree
			node= ((Junction)node).find(word, isInserting);
		}
		return (Leaf)node;
	}//searchHelper(int, Object, boolean)
	
	
	//retunrs the minimal gap between any 2 elements in this tree
	public int minGap(){
		return this.minGap;
	}//minGap()
	
	
	//finds the order of 'x' in this tree. assumes 'x' is present in this tree
	/*
	public int order(int x){		
		Object node= this.root;
		int ans= 0;
		while (!(node instanceof Leaf)){	//while we havent found the key's leaf
			Junction junctionNode= (Junction)node;
			Vector<Link> elements= junctionNode.getElements();
			Vector<Object> pointers= junctionNode.getPointers();
			int i;	//i will hold the location of the key
			for (i= 0; ((i != -1) && (i < elements.size())); i++){	//sums the keys we skipped over
				if (x > elements.elementAt(i).getElement()){
					if (pointers.elementAt(i) instanceof Leaf)
						ans+= ((Leaf)pointers.elementAt(i)).getSize();
					else
						ans+= ((Junction)pointers.elementAt(i)).numOfElements();
				}
				else {
					node= pointers.elementAt(i);
					i= -2;
				}
			}
			if (i != -1){
				node= pointers.lastElement();
			}
		}
		Leaf leafNode= (Leaf)node;	//this is the leaf where the key is located
		Vector<Link> data= leafNode.getData();
		for (int i=0; i < leafNode.getSize(); i++){	//adds the number of keys that are before 'x' in his leaf
			if (x >= data.elementAt(i).getElement()){
				ans++;
			}
			else
				i= leafNode.getSize();
		}
		return ans;
	}*/
	public ArrayList<ArrayList<String>> minePrintTree(){
		ArrayList<ArrayList<String>> samples = new ArrayList<ArrayList<String>>();
		Object node= this.root;
		while (!(node instanceof Leaf))
			node= (((Junction)node).getPointers()).elementAt(0);
		Leaf leafNode= (Leaf)node;
		while (leafNode != null){
			ArrayList<String> leaf = new ArrayList<String>();
			for(Link link:leafNode.getData()) {
				leaf.add(link.getElement().getBlock()+link.getElement().getWord()+link.getRepeatWordsCound());
			}
			samples.add(leaf);
			leafNode= leafNode.getNext();
		}
		return samples;
	}//printTree()
	public String printTree(){
		String ans= "";
		Object node= this.root;
		while (!(node instanceof Leaf))
			node= (((Junction)node).getPointers()).elementAt(0);
		Leaf leafNode= (Leaf)node;
		while (leafNode != null){
			ans= ans + (leafNode.toString()).substring(0, (leafNode.toString()).length()) + "#";
			leafNode= leafNode.getNext();
		}
		return ans.substring(0, ans.length()-1);
	}//printTree()

	
}

	