package edu.uoc.ds.adt;


import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;

public class PR1Queue {

    public final int CAPACITY = 15;

    private Queue<Integer> queue;

    public PR1Queue() {
        newQueue();
    }
    public void newQueue() {
        queue = new QueueArrayImpl<>(CAPACITY);
    }

    public Queue<Integer> getQueue() {
        return this.queue;
    }

    public void add(int c) {
        this.queue.add(Integer.valueOf(c));
    }

    public int poll() {
        int x = this.queue.poll().intValue();
        return (x % 4) * (x % 4);
    }
}
