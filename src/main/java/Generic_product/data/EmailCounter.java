package Generic_product.data;

import java.util.concurrent.atomic.AtomicInteger;

public class EmailCounter {
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static int getNext() {
        return counter.getAndIncrement();
    }
}
