import java.util.List;
import java.util.LinkedList;

public Stack <T> {
	//Design 1: use an array to hold the elements, keep track of the "top" with an index
	
	//Design 2: use a linked list to hold elements
	
	List <T> list = new LinkedList<T>();
	
	public void push(T element) {
		this.list.add(0, element);
	}
	
	public T pop() {
		return this.list.remove(0);
	}
	
	public static void main(String args[]) {
		
		Stack<Integer> s = new Stack();
		s.push(10);
		s.push(20);
		s.push(30);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}
