package ru.mail.polis.collections.iterator.todo;

import ru.mail.polis.collections.iterator.IIncreasingSequenceIterator;
import ru.mail.polis.collections.iterator.IPeekingIterator;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 */
public class IntegerIncreasingSequencePeekingIterator implements IIncreasingSequenceIterator<Integer> {

    /**
     * minStep = 1
     *
     * first must be less or equal last
     *
     * maxStep must be positive
     *
     * @param first — min value in iterator
     * @param last - max value in iterator included
     * @param maxStep — max diff between adjacent values
     * @throws IllegalArgumentException if arguments is invalid
     */
    private int value;
    private int lastNext;
    private int last;
    private boolean isFirstIteration;
    private int maxStep;
    private Random random;
    private int minStep=1;

    public IntegerIncreasingSequencePeekingIterator(int first, int last, int maxStep) {
        if (maxStep<=0||first>last) {
            throw new IllegalArgumentException();
        }
        this.value=first;
        this.lastNext=first;
        this.isFirstIteration=true;
        this.last=last;
        this.maxStep=maxStep;
        random=new Random();
    }


    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * In other words, returns {@code false} if lastNextElement + minStep > last.
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return isFirstIteration||lastNext<=last-minStep;
}

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        lastNext = value;
        int step=minStep+random.nextInt(maxStep-minStep+1);
        if(value>last-step){
            value=last;
        }else{
            value+=step;
        }
        isFirstIteration=false;
        return lastNext;
    }

    /**
     * Retrieves the next element in the iteration, but does not iterate.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer peek() {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * Compares this object with the specified object for order.
     * Returns a negative integer, zero, or a positive integer as this peeked value is less
     * than, equal to, or greater than the peeked value specified object.
     *
     * <p> If iterator has no elements so it is less.
     *
     * @param other the {@link IPeekingIterator} to be compared.
     * @return a negative integer, zero, or a positive integer as this peeked value
     * is less than, equal to, or greater than the peeked value specified iterator.
     */
    @Override
    public int compareTo(IPeekingIterator<Integer> other) {
        if(hasNext()&&other.hasNext()){
            return peek().compareTo(other.peek());
        }
        if (hasNext()){
            return 1;
        }else{
            return -1;
        }
    }
}
