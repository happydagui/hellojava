/**
 * Created by min on 17-1-10.
 */
package hello.dao;

import java.util.Map;

public interface OrderSequenceDao {
    /**
     * key `key` must be set
     * @param map
     * @return
     */
    int nextval(Map<String, Object> map);
}
