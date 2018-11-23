package TextStuff;


public class LinkBP {

	//element- holds the numaric value of this given link
	private SampleText element;
	//prev- points to the previous link in the list of numbers (spans over all the leafs in the tree)
	private LinkBP prev;
	//next- points to the next link in the list (spans over all the leafs in the tree)
	private LinkBP next;
	//When the word is already in the tree and have the same section then this count increment
	private int RepeatWordsCound = 1;
	
	public LinkBP(SampleText newSample){
		this.element= newSample;
		this.prev= null;
		this.next= null;
	}
	
	protected SampleText getElement() {
		return this.element;
	}
	protected LinkBP getNext() {
		return next;
	}
	protected void setNext(LinkBP next) {
		this.next = next;
	}
	protected LinkBP getPrev() {
		return prev;
	}//getPrev()
	

	//prev setter
	protected void setPrev(LinkBP prev) {
		this.prev = prev;
	}//setPrev(Link)

	
	//overrides the toString from Object class
	//returns a string representation of this link
	public String toString(){
			return "" + this.element;
	}//toString()


	public int getRepeatWordsCound() {
		return RepeatWordsCound;
	}


	public void setRepeatWordsCound(int repeatWordsCound) {
		RepeatWordsCound = repeatWordsCound;
	}



}//Link
