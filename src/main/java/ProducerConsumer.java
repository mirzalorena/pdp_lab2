import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity=1;

    int vect_A[];
    int vect_B[];
    int lenA;
    int lenB;

    ReentrantLock mutex=new ReentrantLock();

    public ProducerConsumer(int[] vect_A, int[] vect_B,int lenA,int lenB) {
        this.vect_A = vect_A;
        this.vect_B = vect_B;
        this.lenA=lenA;
        this.lenB=lenB;
    }

    public ProducerConsumer() {
    }

    public void produce() throws InterruptedException {
        int product=0;
        int count=0;
        while(count<lenA)
        {
            synchronized (this)
            {
                if(lenA==lenB)
                {
                    for(int i=0;i<lenA;i++) {
                        product = vect_A[i] * vect_B[i];

                        while (list.size() == capacity)
                            wait();

                        mutex.lock();
                        list.add(product);
                        mutex.unlock();
                        count++;

                        notify();
                        Thread.sleep(100);
                    }
                }
            }
        }
    }

    public void consume() throws InterruptedException {
        int sum=0;
        int count=0;
        while(count<lenA)
        {
            synchronized (this)
            {
                    while (list.size() == 0)
                        wait();

                    mutex.lock();
                    int product = list.removeFirst();
                    mutex.unlock();

                    sum = sum + product;
                    count++;

                    notify();
                    Thread.sleep(100);
            }
        }
        System.out.println("PRODUCT: "+sum);
    }
}
