package unl.cse.lists;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

	private Node<T> head = null;
	private int size = 0;

	public MyLinkedList(MyLinkedList<T> that) {
		
	}
	
	public void addElementToHead(T item) {
		Node<T> newHead = new Node<T>(item);
		newHead.setNext(this.head);
		this.head = newHead;
		size++;
	}
	
	public void removeFirstInstanceOf(T item) {
		if(head == null) {			
			return;
		} else if(head.getItem().equals(item)) {
			head = head.getNext();
			size--;
		} else {
			Node<T> curr = head;
			Node<T> prev = null;
			while(curr.hasNext() && !curr.getItem().equals(item)) {
				prev = curr;
				curr = curr.getNext();
			}
			if(curr.getItem().equals(item)) {
				prev.setNext(curr.getNext());
				size--;
			}			
		}
	}
	
	public int getSize() {		
		return this.size;
//		int counter = 0;
//		Node<T> curr = head;
//		while(curr != null) {
//			counter++;
//			curr = curr.getNext();
//		}
//		return counter;
	}
	
	public void insertAtIndex(T element, int index) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("cannot do");
		}
		if(index == 0) {
			this.addElementToHead(element);
			return;
		}
		Node<T> newNode = new Node<T>(element);
		insertAtIndex(newNode,index);
	}
	
	private Node<T> getNodeAtIndex(int index) {
		int counter = 0;
		Node<T> curr = head;
		for(int i=0; i<index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}
	
	private void insertAtIndex(Node<T> node, int index) {
		Node<T> prev = getNodeAtIndex(index-1);
		Node<T> curr = prev.getNext();
		prev.setNext(node);
		node.setNext(curr);
		size++;		
	}

	public void addAllAtIndex(MyLinkedList<T> those, int index) {
		//Solution 1:
		//for(int i = 0; i<those.getSize(); i++) {
		//	T item = thjose.getNodeAtIndex(i);
		//	this.insertAtIndex(node.getItem(), index+i);
		//}
		
		//Solution 2:
		//Get the head and tail of the other list:
		//Node<T> thatHead = those.head;
		//Node<T> thatTail = those.getNodeAtIndex(those.getSize()-1);
		//Node<T> prev = this.getNodeAtIndex(index - 1);
		//Node<T> curr = prev.getNext();
		//prev.setNext(thatHead);
		//thatTail.setNext(curr);
		//size += those.size;
		
		//Solution 3:
		MyLinkedList<T> copy = new MyLinkedList<T>(those);
		Node<T> thatHead = copy.head;
		Node<T> thatTail = 
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> curr = head;
			
			@Override
			public boolean hasNext() {
				if(curr == null)
					return false;
				else
					return true;
			}
			@Override
			public T next() {
				T item = curr.getItem();
				curr = curr.getNext();
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("not implemented");
			}};
	}

	@Override
	public String toString() {
		if(this.head == null) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> curr = head;
		while(curr.hasNext()) {
			sb.append(curr.getItem());
			sb.append(", ");
			curr = curr.getNext();
		}
		sb.append(curr.getItem());
		sb.append("]");
		return sb.toString();
	}


}