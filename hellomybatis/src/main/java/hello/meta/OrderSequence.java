package hello.meta;

import java.sql.Date;

/**
 * Created by min on 17-1-10.
 */
public class OrderSequence {
    String name;
    int currentValue;
    int increment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
