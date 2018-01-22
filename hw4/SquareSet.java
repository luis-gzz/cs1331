import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom Set implementation to hold the Square object
 *
 * @author lgonzalez35
 * @version 1.1
 */
public class SquareSet implements Set<Square> {

    private Square[] backingArray;
    private int setSize;

    /**
     * Creates a SquareSet with initial capacity of 5
     */
    public SquareSet() {
        backingArray = new Square[5];
        setSize = 0;
    }

    /**
     * Creates a SquareSet with the elements passed in the constructor
     * @param  c A collection containing the elements to be added to new set
     */
    public SquareSet(Collection<Square> c) {
        backingArray = new Square[5];
        setSize = 0;

        for (Object elem: c) {
            if (elem instanceof Square) {
                Square sq = (Square) elem;
                this.add(sq);
            }
        }

    }

    @Override public boolean add(Square e) {
        if (e == null) {
            throw new NullPointerException("Null are not supported");
        } else if (!(e instanceof Square)) {
            throw new ClassCastException();
        } else if (!checkSquare(e)) {
            throw new InvalidSquareException(e.toString()
                + " is not a proper square");
        }

        if (this.contains(e)) {
            return false;
        }

        if (setSize == backingArray.length) {
            Square[] newArr = new Square[backingArray.length * 2];

            for (int i = 0; i < setSize; i++) {
                newArr[i] = backingArray[i];
            }

            newArr[setSize] = e;
            setSize++;
            backingArray = newArr;

        } else {
            backingArray[setSize] = e;
            setSize++;

        }
        return true;

    }

    @Override public boolean addAll(Collection<? extends Square> c) {
        if (c == null) {
            throw new NullPointerException("Null collections cannot be added");
        }

        for (Square elem: c) {
            if (!checkSquare(elem)) {
                throw new InvalidSquareException(elem.toString()
                    + " is not a proper square");
            }

        }

        if (this.equals(c)) {
            return false;
        }

        for (Square elem : c) {
            this.add(elem);
        }
        return true;
    }

    @Override public boolean contains(Object o) {
        if (!(o instanceof Square)) {
            throw new ClassCastException();
        } else if (o == null) {
            throw new NullPointerException();
        }

        for (Square elem : backingArray) {
            if (elem != null && o != null && elem.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }

        for (Object elemC : c) {
            Square sq = (Square) elemC;
            if (!this.contains(elemC)) {
                return false;
            }
        }

        return true;
    }

    @Override public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if (o instanceof Square) {
            Square obj = (Square) o;
            for (int i = 0; i < backingArray.length; i++) {
                if (obj.equals(backingArray[i])) {
                    backingArray[i] = null;

                    Square[] newArr = new Square[setSize * 2];

                    int counter = 0;
                    for (Square elem: backingArray) {
                        if (elem != null) {
                            newArr[counter] = elem;
                            counter++;
                        }
                    }

                    backingArray = newArr;
                    setSize--;
                    return true;
                }
            }
        } else {
            throw new ClassCastException();
        }
        return false;
    }

    @Override public Object[] toArray() {
        Object[] newArr = new Object[setSize];

        for (int i = 0; i < setSize; i++) {
            newArr[i] = backingArray[i];
        }

        return newArr;
    }

    @Override public <T> T[] toArray(T[] a) {
        Class c = a.getClass();
        Square[] sqArr = new Square[1];

        if (a == null) {
            throw new NullPointerException();
        } else if (!(c.isInstance(sqArr))) {
            throw new ArrayStoreException();
        }

        if (a.length < setSize) {
            T[] tempArr = (T[]) new Object[setSize];
            // T[] tempArr = (T[])
            //     java.lang.reflect.Array.newInstance(
            //     a.getClass().getComponentType(), setSize);
            a = tempArr;

        }

        for (int i = 0; i < a.length; i++) {
            if (i < setSize) {
                a[i] = (T) backingArray[i];
            } else {
                a[i] = null;
            }
        }

        return a;
    }

    @Override public boolean isEmpty() {
        if (setSize == 0) {
            return true;
        }

        return false;
    }

    @Override public void clear() {
        Square[] tempArray = new Square[5];
        backingArray = tempArray;

        setSize = 0;
    }

    @Override public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
        //return false;
    }

    @Override public Iterator<Square> iterator() {
        Iterator iterator = new SquareSetIterator();
        return iterator;
    }

    @Override public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Set)) {
            return false;
        }

        Set set = (Set) obj;

        if (this.containsAll(set) && set.containsAll(this)) {
            return true;
        }

        return false;
    }

    @Override public int hashCode() {
        int sum = 0;

        for (Square sq: backingArray) {
            if (sq != null) {
                sum = sum + sq.hashCode();
            }
        }

        return sum;
    }

    @Override public int size() {
        return setSize;
    }

    /**
     * A simple function to check if the added square is valid
     * @param  obj the Square object to be checked
     */
    private boolean checkSquare(Square obj) {
        char f;
        char r;

        if (obj.toString().length() != 2) {
            // throw new InvalidSquareException(obj.toString());
            return false;
        } else {
            f = obj.toString().charAt(0);
            r = obj.toString().charAt(1);
        }
        if (!(f >= 'a' && f <= 'h') || !(r >= '1' && r <= '8')) {
            //throw new InvalidSquareException(obj.toString());
            return false;
        }
        return true;
    }

    private class SquareSetIterator implements Iterator<Square> {
        private int cursor;

        /**
         * Sets up a SquareSetIterator with its cursor at zero
         */
        public SquareSetIterator() {
            cursor = 0;
        }

        @Override public boolean hasNext() {
            return cursor < setSize;
        }

        @Override public Square next() {
            if (hasNext()) {
                Square element = (Square) backingArray[cursor];
                cursor++;
                return element;

            }

            throw new NoSuchElementException();
        }

        @Override public void remove() {
            SquareSet.this.remove(cursor - 1);
        }

    }

}
