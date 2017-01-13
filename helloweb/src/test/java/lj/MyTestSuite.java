package lj;

import lj.entities.HelloDbTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by min on 17-1-9.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SpringTransitionTest.class, HelloDbTest.class})
public class MyTestSuite {
}
