package lj.controllers;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by min on 17-1-1.
 */
@ContextConfiguration(locations = {"classpath:springmvc.xml"})
public class HelloControllerTest extends AbstractJUnit4SpringContextTests {
    @Test
    public void test() {
        assert 1 == 1;
    }
}
