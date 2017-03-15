package hello.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by min on 17-2-11.
 */
public class ViaPipeline {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Comunicator comunicator = new Comunicator();
        final PipedInputStream inputStream = new PipedInputStream();
        final PipedOutputStream outputStream = new PipedOutputStream();
        outputStream.connect(inputStream);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                comunicator.read(inputStream);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                comunicator.write(outputStream);
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}

class Comunicator {
    public void write(PipedOutputStream out) {
        try {
            System.out.println("Write starting");
            for (int i = 0; i < 300; i++) {
                String data = "" + (i + 1);
                out.write(data.getBytes());
            }
            System.out.println("Write ended");

            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(PipedInputStream input) {
        try {
            System.out.println("Read starting");
            byte[] bytes = new byte[20];
            int count = input.read(bytes);
            System.out.println("after read");
            while (count != -1) {
                String s = new String(bytes, 0, count);
                System.out.println(s);
                count = input.read(bytes);
            }
            System.out.println();
            System.out.println("Read ended");
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
