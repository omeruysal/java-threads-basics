public class Main {
    public static void main(String[] args) {
        Data d = new Data();
        d.value = 0;
        Producer producer = new Producer(d);
        Consumer consumer = new Consumer(d);
        Thread thread = new Thread(consumer); // Our Consumer class do not have the Thread methods, that is why we have to put it into a thread

        producer.start(); // We can reach the start method because we extend it from Thread, as we know that we can reach the methods of parent class
        thread.start(); // Represent our consumer object
    }
}

class Producer extends Thread {

    Data data;

    public Producer(Data data) {
        this.data = data;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (data) {

                data.value++;
                System.out.println("Producer increased value, new value is " + data.value);
            }
        }
    }
}

class Consumer implements Runnable {

    Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override // We have to override run method if we implement Runnable interface
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (data) { // with synchronized, we save the data and allow only one thread can reach tha data

                data.value--;
                System.out.println("Consumer decreased value, new value is " + data.value);
            }

        }
    }
}

class Data {
    int value;
}

// There are two ways to create threads.
// Extending Thread class or implementing Runnable interface and mostly we should second way that is why we can extend something later if we need