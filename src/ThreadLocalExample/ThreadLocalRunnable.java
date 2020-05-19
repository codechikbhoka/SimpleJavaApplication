package ThreadLocalExample;

/**
 * @author vbbharga on 05/05/20
 */
public class ThreadLocalRunnable implements Runnable {

    private ThreadLocal<Integer> threadLocalWhichStoresInt = new ThreadLocal<>();

    private Integer normalInt;

    /**
     * This code will get run. Variables above are shared among every instantiation of run() method
     * <p>
     * ### (on same instance of MyRunnable, if instances of MyRunnable are different, then invocation of run() method
     * on those instances will have their own copy of above variables) ###
     * <p>
     * So the threadLocal variable is shared, but the threadLocal variable will keep separate copy of Integer corresponding to every thread.
     * <p>
     * normalInt variable is also shared, its access is not thread-safe. If synchronised block is not used in threads and that block of code is reading+writing this variable,
     * then there is no control over how/when the value of this variable is getting read/written by different threads.
     */


    int generateRandomInt() {
        return (int) (Math.random() * 100D);
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        // Each thread has its own copy of int inside threadLocal, so non concurrency issues.
        threadLocalWhichStoresInt.set(generateRandomInt());

        // value generated for second thread will override the value generated for first thread.
        normalInt = generateRandomInt();

        System.out.println("normalInt generated for " + threadName + " is " + normalInt);

        try {
            Thread.sleep(2000);
            // In thread1, above 2 statements gets executed, then thread1 goes to sleep.
            // while thread1 is sleeping, above 2 statements gets executed for thread2. the value of
            // normalInt gets overridden. Then thread2 goes to sleep.
            // then below 2 statements for both threads gets executed.
        } catch (InterruptedException e) {
        }


        System.out.println(threadName + " :: threadLocalInt ::" + threadLocalWhichStoresInt.get());

        System.out.println(threadName + ":: normalInt :: " + normalInt);
    }
}