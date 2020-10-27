// Java program to implement solution of producer
// consumer problem.

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int vect_A[] = { 3, -5, 4 };
        final int vect_B[] = { 2, 6, 5 };
        //result -4

        final int lenA=vect_A.length;
        final int lenB=vect_B.length;

        final ProducerConsumer pc = new ProducerConsumer(vect_A,vect_B,lenA,lenB);

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();

    }


}
