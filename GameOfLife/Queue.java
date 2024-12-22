package GameOfLife;

public class Queue<T> {

	private Node<T> first;
	private Node<T> back;
	private int count;

	public Queue() {
		first = back = null;
		count = 0;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public boolean isFull() {
		return false;    //the queue is never full
	}

	public void enqueue(T newItem) throws QueueException {

		Node<T> newNode = new Node<T>(newItem);

		if (count == 0) {   //if the queue is currently empty
			first = back = newNode;    //set both first and back to this newNode
			back.setNext(first);    //connect back to first, keeping it circular
		} else {
			back.setNext(newNode);     //connect the newNode to the last node
			back = newNode;
			back.setNext(first);    //keep it circular

		}
		count++;   //increment count
	}

	public T dequeue() throws QueueException {
		//if the queue is empty, throw an exception
		if (count == 0) {
			throw new QueueException("Queue empty!");
		} else {
			T saveData = first.getInfo();

			//if there's only node, set both first and back to null
			if (first == back) {
				first = back = null;
			} else {    
				//delete the first node by moving first to the next node in the queue
				first = first.getNext();
				//keep it circular
				back.setNext(first);
			}

			count--;   //decrement count
			return saveData;

		}
	}

	public void dequeueAll() {
		first = back = null;
		count = 0;
	}

	public T peek() throws QueueException {
		//if the queue is empty throw an exception
		if (count == 0) {
			throw new QueueException("Queue empty!");
		}
		return first.getInfo();
	}

	public int size() {
		return count;
	}

}
