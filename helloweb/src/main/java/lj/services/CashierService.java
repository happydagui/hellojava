package lj.services;

import java.util.List;

/**
 * Created by min on 17-1-8.
 */
public interface CashierService {
    void checkout(String username, List<String> isbns);
}
