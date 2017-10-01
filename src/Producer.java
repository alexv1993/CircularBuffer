/**
 * Created by Alex on 30.09.2017.
 */
public class Producer extends Thread {
    private Buffer sharedLocation;

    public Producer(Buffer shared) {
        super("producer");
        this.sharedLocation = shared;
    }

    @Override
    public void run() {
        for (int count = 11; count < 20; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3001));
                sharedLocation.set(count);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.err.println(getName() + " is finishing generation." +
                "\nFinishing thread " + getName() + ".");
    }


}
