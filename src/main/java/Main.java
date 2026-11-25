private static final int TOTAL_ORDERS = 15;
private static final int CONSUMERS = 3;
private static final int ORDERS_PER_CONSUMER = 5;

void main() throws InterruptedException {
    startEx1();
    startEx2();
    startEx3();
}

static void startEx1() throws InterruptedException {
    Thread evenThread = new EvenThread();
    Thread oddThread = new Thread(new OddRunnable());

    evenThread.start();
    oddThread.start();

    evenThread.join();
    oddThread.join();
    IO.println("Потоки завершили работу\n");
}

static void startEx2() throws InterruptedException {
    ShoeWarehouse warehouse = new ShoeWarehouse();

    Thread producer = new Thread(new OrderProducer(warehouse, TOTAL_ORDERS), "Producer");
    List<Thread> consumers = new ArrayList<>();
    for (int c = 1; c <= CONSUMERS; c++) {
        consumers.add(new Thread(new OrderConsumer(warehouse, ORDERS_PER_CONSUMER), "Consumer-" + c));
    }

    producer.start();
    consumers.forEach(Thread::start);

    producer.join();
    for (Thread t : consumers) t.join();
    IO.println("Все заказы обработаны, программа завершена\n");
}

static void startEx3() {
    try (ShoeWarehouse warehouse = new ShoeWarehouse()) {
        warehouse.startFulfilling(CONSUMERS, ORDERS_PER_CONSUMER);

        try (ExecutorService producer = Executors.newSingleThreadExecutor()) {
            for (int i = 1; i <= TOTAL_ORDERS; i++) {
                Order order = new Order(
                        i,
                        ShoeWarehouse.PRODUCT_LIST.get(i % ShoeWarehouse.PRODUCT_LIST.size()),
                        1 + i % 5);
                producer.submit(() -> warehouse.receiveOrder(order));
            }
        }

        IO.println("Все заказы обработаны, пулы завершены");
    }
}