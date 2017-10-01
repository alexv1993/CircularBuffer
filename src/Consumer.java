/**
 * Created by Alex on 30.09.2017.
 */
public class Consumer extends Thread {
    private Buffer sharedLocation;

    public Consumer(Buffer shared) {
        super("consumer");
        sharedLocation = shared;
    }

    public void run() {
        int sum = 0;
        for (int count = 1; count < 10; ++count) {
            try {
                Thread.sleep((int) (Math.random() * 3001));
                sum += sharedLocation.get();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.err.println(getName() + " sum of read values: " +
                sum + ".\n Finishing thread " + getName() + ".");
    }

}
