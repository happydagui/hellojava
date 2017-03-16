package lj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by min on 17-1-9.
 */
public class TempTest {
    @Test
    public void testCollectionCopy() {
        List<String> src = new ArrayList<String>(2);
        src.add("foo");
        src.add("bar");
        src.toArray();
        System.out.println(src);
//        for (Iterator<String> it = src.iterator(); it.hasNext();) {
//            it.remove();
//        }
        Iterator<String> it = src.iterator();

        while (it.hasNext()) {
            it.remove();
        }
        System.out.println(src);
    }
}
