public class Deque<T> {

    private Array<T> array;
    private int front;
    private int back;
    private int size;
    
    public Deque() {
        array = new Array<T>(16);
        front = 0;
        back = 0;
        size = 0;
    }

    public Deque(int cap) {
        array = new Array<T>(cap);
        front = 0;
        back = 0;
        size = 0;
    }

    public void resize(int newCap){
        Array<T> newArray = new Array<T>(newCap);
        // Use % to tell us when we are adding from the front;
        for (int i=0; i<size; i++){
            newArray.setVal(i, array.getVal((front+i) % array.length()));
        }
        array = newArray;
        // front points to idx 0 again.
        front = 0;
        // back points to idx after last element.
        back = size;
    }

    public void addLast(T item){
        // Check array is not full.
        if (size==array.length()){
            resize(array.length()*2);
        }
        // if idx after last element is out of bounds, back wraps to front.
        array.setVal(back, item);
        back = (back+1)%array.length();
        size++;
    }

    public void addFirst(T item){
        if (size==array.length()){
            resize(array.length()*2);
        }
        // Double check front is not out of bounds at the end of array.
        front = (front-1+array.length())%array.length();
        array.setVal(front, item);
        size++;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public T removeFirst() throws EmptyDequeException{
        if (isEmpty()){
            throw new EmptyDequeException();
        }
        // Store the item that front points too.
        T item = array.getVal(front);
        // Move front pointer, wrapping if out of bounds.
        front = (front+1)%array.length();
        size--;
        if (size<array.length()/4){
            resize(array.length()/2);
        }
        return item;
    }

    public T removeLast() throws EmptyDequeException{
        if (isEmpty()){
            throw new EmptyDequeException();
        }
        // Store the item one idx before back pointer.
        T item = array.getVal((back-1)%array.length());
        // Move back pointer to newest null idx.
        back = (back-1+array.length())%array.length();
        size--;
        if (size<array.length()/4){
            resize(array.length()/2);
        }
        return item;
    }

    public T peekFirst() throws EmptyDequeException{
        if (isEmpty()){
            throw new EmptyDequeException();
        }
        return array.getVal(front);
    }

    public T peekLast() throws EmptyDequeException{
        if (isEmpty()){
            throw new EmptyDequeException();
        }
        return array.getVal((back-1)%array.length());
    }



    //used only for testing!!!
    public int getAccessCount() {
        return array.getAccessCount();
    }
    
    //used only for testing!!!
    public void resetAccessCount() {
    this.array.resetAccessCount();
    }
}
