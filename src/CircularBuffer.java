/**
 * Created by Alex on 01.10.2017.
 */
public class CircularBuffer implements Buffer {
    private int buffers[] = {-1, -1, -1};

    private int occupiedBuffers;

    private int readLocation = 0;
    private int writeLocation = 0;

    public synchronized void set(int value) {
        String name = Thread.currentThread().getName();

        while (occupiedBuffers == buffers.length) {
            try {
                System.err.println("\nAll elements is full. " + name + " is waiting");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        buffers[writeLocation] = value;

        System.err.println("\n" + name + " is writing " + buffers[writeLocation] + " ");

        ++occupiedBuffers;

        writeLocation = (writeLocation + 1) % buffers.length;

        System.err.println(createStateOutput());
        notify();
    }

    public synchronized int get() {
        String name = Thread.currentThread().getName();

        while (occupiedBuffers == 0) {
            try {
                System.err.println("\nAll elements is clear. " + name + " is waiting.");
                wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        int readValue = buffers[readLocation];
        System.err.println("\n" + name + " is reading " + readValue + " ");
        --occupiedBuffers;
        readLocation = (readLocation + 1) % buffers.length;

        System.err.println(createStateOutput());
        notify();
        return readValue;
    }

    public String createStateOutput() {
        String output = "(works elements: " + occupiedBuffers + ")\ninside: ";
        for (int i = 0; i < buffers.length; ++i) {
            output += " " + buffers[i] + " ";
        }

        output = "\n";
        for (int i = 0; i < buffers.length; ++i) {
            output += "---- ";
        }

        output += "\n";

        for (int i = 0; i < buffers.length; ++i) {
            if (i == writeLocation && writeLocation == readLocation) {
                output += " WR ";
            } else if (i == writeLocation) {
                output += " W  ";
            } else if (i == readLocation) {
                output += " R  ";
            } else {
                output += "    ";
            }
        }
        output += "\n";
        return output;
    }
}
