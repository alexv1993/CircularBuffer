/**
 * Created by Alex on 01.10.2017.
 */
public class CircularBufferTest {
    public static void main(String[] args) {
        CircularBuffer sharedLocation = new CircularBuffer();
        System.err.println(sharedLocation.createStateOutput());

        Producer producer = new Producer(sharedLocation);
        Consumer consumer = new Consumer(sharedLocation);

        producer.start();
        consumer.start();
    }
}
