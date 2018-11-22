package TextStuff;
import java.util.ArrayList;
import java.util.Vector;

public class BPlusTree {
	
	public Object root;
	private final int T;
	public String letter;
	
	public BPlusTree(String letter){
		this.letter = letter;
		this.root= null;
		this.T=5;
	}
	
	public void insert(SampleText newSample){
		if (this.search(newSample.getWord(), newSample.getBlock()) != null) {
			this.search(newSample.getWord(), newSample.getBlock()).setRepeatWordsCound(this.search(newSample.getWord(),newSample.getBlock()).getRepeatWordsCound()+1);
			return;
		}
		if (this.root == null){ //if is the first key.
			LinkBP newLink= new LinkBP(newSample);
			this.root= new LeafBP(newLink, this.T, null, null,  null);
		}
		else {	//if this isn't the first key
			if (this.root instanceof LeafBP){	//if the root is a leaf, adds it to that leaf
				((LeafBP)this.root).insert(new LinkBP(newSample));
				if (((LeafBP)this.root).overflow()){	//root is a Leaf, and also needs splitting. only happens once for each BPlus tree
					splitRoot();
				}
			}
			else {	//if root is a junction
				Object insertionPlace= ((JunctionBP)this.root).find(newSample.getWord(),newSample.getBlock(), true);	//finds where x should be inserted
				if (insertionPlace instanceof LeafBP){	//the sons of the root are leafs
					LeafBP insertPlace= (LeafBP)insertionPlace;
					insertPlace.insert(new LinkBP(newSample));	//inserts the new key
					if (insertPlace.overflow()){	//if case the leaf is too big, splits it
						((JunctionBP)this.root).splitSon(insertionPlace);
					}
					if (((JunctionBP)this.root).overflow()){	//in case the root is now too big, splits it
						splitRoot();
					}
				}
				else {	//the sons of the root are junctions
					LeafBP temp= searchHelper(newSample.getWord(),newSample.getBlock(), insertionPlace, true);	//finds where x should be inserted
					temp.insert(new LinkBP(newSample));	//inserts the new key
					if (temp.overflow())	//if case the leaf is too big, splits it
						(temp.getParent()).splitSon(temp);
					JunctionBP tempJunction= temp.getParent();
					boolean stop= false;
					while ((!stop) && (tempJunction != this.root)){
						if (tempJunction.overflow()){	//makes sure that all the junctions are of legal size
							(tempJunction.getParent()).splitSon(tempJunction);	//otherwise, splits them
							tempJunction= tempJunction.getParent();
						}
						else
							stop= true;
					}
					if ((tempJunction == this.root) && (((JunctionBP)this.root).overflow())){	//if the root is now too big, splits it
						splitRoot();
					}
				}
			}
		}
		
	}//insert(int)
	
	//splits the root into 2 junctions/leafs and makes a new root
	private void splitRoot(){
		if (this.root instanceof LeafBP){
			LeafBP newLeaf= ((LeafBP)this.root).split(null);	//splits the root
			Vector<LinkBP> newElement= new Vector<LinkBP>();
			newElement.add(((LeafBP)this.root).getLast());
			Vector<Object> newPointers= new Vector<Object>();
			newPointers.add(this.root);
			newPointers.add(newLeaf);
			JunctionBP newRoot= new JunctionBP(newElement, this.T, null, newPointers);	//updates all of the second half's fields
			((LeafBP)this.root).setParent(newRoot);
			newLeaf.setParent(newRoot);
			this.root= newRoot;	//makes the newly creates root the only root for this tree
		}
		else {	//if the root is a junction
			JunctionBP newJunction= ((JunctionBP)this.root).split(null);	//splits the root
			Vector<LinkBP> newElement= new Vector<LinkBP>();
			newElement.add(((JunctionBP)this.root).getLast());
			Vector<Object> newPointers= new Vector<Object>();
			newPointers.add(this.root);
			newPointers.add(newJunction);
			JunctionBP newRoot= new JunctionBP(newElement, this.T, null, newPointers);	//updates all of the second half's fields
			for (int i=0; i < newPointers.size(); i++){	//sets the parents for the split junctions
				Object temp= newPointers.elementAt(i);
				if (temp instanceof LeafBP){
					LeafBP tempLeaf= (LeafBP)temp;
					tempLeaf.setParent(newRoot);
				}
				else {
					JunctionBP tempJunction= (JunctionBP)temp;
					tempJunction.setParent(newRoot);
				}
			}
			((JunctionBP)this.root).setParent(newRoot);
			newJunction.setParent(newRoot);
			((JunctionBP)this.root).removeLastElement();
			this.root= newRoot;	//makes the newly creates root the only root for this tree
		}
	}//splitRoot
	
	//finds 'x' whithin this tree. returns 'null' if x is not in this tree
	public LinkBP search(String word, int block){
		if(this.root == null) {
			return null;
		}
			LeafBP node= searchHelper(word,block, this.root, false);	//looks for the leaf where 'x' should be located
			LinkBP temp=((LeafBP)node).find(word, block);	//looks for 'x' in that leaf
			if(temp == null) {
				return null;
			}
			if ((temp.getElement().getWord()).equals(word) ) {	//if the link found is x, returns its location
				return temp;
			}else {
				return null;
			}
	}//search(int)
	
	//this method is used to lower the load off 'search', and also so it can be called recursively
	private LeafBP searchHelper(String word,int block, Object node, boolean isInserting){

		while (!(node instanceof LeafBP)){	//while we have not found a leaf, keeps going deeper into the tree
			node= ((JunctionBP)node).find(word, block, isInserting);
		}
		return (LeafBP)node;
	}
	
	public ArrayList<ArrayList<String>> myPrintTree(){
		ArrayList<ArrayList<String>> samples = new ArrayList<ArrayList<String>>();
		Object node= this.root;
		while (!(node instanceof LeafBP))
			node = (((JunctionBP)node).getPointers()).elementAt(0);
		LeafBP leafNode= (LeafBP)node;
		while (leafNode != null){
			ArrayList<String> leaf = new ArrayList<String>();
			for(LinkBP link:leafNode.getData()) {
				leaf.add(String.valueOf(link.getRepeatWordsCound()));
			}
			samples.add(leaf);
			leafNode= leafNode.getNext();
		}
		return samples;
	}
	public String printTree(){
		String ans= "";
		Object node= this.root;
		while (!(node instanceof LeafBP))
			node= (((JunctionBP)node).getPointers()).elementAt(0);
		LeafBP leafNode= (LeafBP)node;
		while (leafNode != null){
			for(LinkBP sample:leafNode.getData()) {
				ans = ans + sample.getElement().getBlock()+sample.getElement().getWord()+sample.getRepeatWordsCound()+", ";
			}
			ans = ans+" # ";
			//ans= ans + (leafNode.toString()).substring(0, (leafNode.toString()).length()) + "#";
			leafNode= leafNode.getNext();
		}
		return ans.substring(0, ans.length()-1);
	}
}