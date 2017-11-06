/**
 * 
 */
package jp.co.worksap.global;

import java.util.NoSuchElementException;

/**
 * @author bheem_000
 *
 */
public final class ImmutableQueue<E> {
private Object[] q_element;
	
	public ImmutableQueue(){
		
	}
	private ImmutableQueue(int q_size){
		q_element=new Object[q_size];
	}
	public void isQueueEmpty(){
		if(size()==0){
			throw new NoSuchElementException();
		}
		
	}
	public ImmutableQueue<E> enqueue(E e){
	    if(e==null){
	    	throw new IllegalArgumentException();
	    }
	    ImmutableQueue<E> queue = new ImmutableQueue<E>(size()+1);
	    if(size()>0){
	    	for(int i=0; i<size();i++){
	    		queue.q_element[i]=q_element[i];
	    	}
	    }
	    queue.q_element[size()]=e;
		return queue;
	}
	public ImmutableQueue<E> dequeue(){
		isQueueEmpty();
		ImmutableQueue<E> queue =new ImmutableQueue<E>(size()-1);
		for(int i=1; i<size(); i++){
			queue.q_element[i-1]=q_element[i];
		}
		return queue;
	}
	
	public E peek(){
		isQueueEmpty();
		return (E) q_element[0];
	}
	
	public int size(){
		return (q_element!=null?q_element.length:0);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
