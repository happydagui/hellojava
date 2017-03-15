package suggestions.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by min on 17-3-14.
 */
public class TaxCaculator implements Callable<Integer> {
    private int seedMoney;

    public TaxCaculator(int seedMoney) {
        this.seedMoney = seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return seedMoney / 10;
    }
}
