package ThreadLocalExample;

/**
 * @author vbbharga on 05/05/20
 */

public class ThreadLocalExample {

    public static void main(String[] args) {

        ThreadLocalRunnable runnableCode = new ThreadLocalRunnable();

        // we create 2 threads which are going to run same runnable code
        Thread thread1 = new Thread(runnableCode);
        Thread thread2 = new Thread(runnableCode);

        // Ask both threads to start running that code
        thread1.start();
        thread2.start();

        try {
            thread1.join(); //wait for thread 1 to terminate
            thread2.join(); //wait for thread 2 to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}