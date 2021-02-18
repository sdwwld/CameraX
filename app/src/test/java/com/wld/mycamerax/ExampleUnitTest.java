package com.wld.mycamerax;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void validTest() {
        valid("5:3");
        valid(1);
        valid(null);
    }

    public void valid(Object object) {
        object.getClass().isAssignableFrom(int.class);
        System.out.println(object);
    }
}