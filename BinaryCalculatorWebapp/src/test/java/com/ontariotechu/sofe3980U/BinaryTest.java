package com.ontariotechu.sofe3980U;

import static org.junit.Assert.*;
import org.junit.Test;

public class BinaryTest {

    // MULTIPLY

    @Test
    public void testMultiplySimple() {
        Binary a = new Binary("10");   // 2
        Binary b = new Binary("11");   // 3
        assertEquals("110", Binary.multiply(a, b).getValue()); // 6
    }

    @Test
    public void testMultiplyByZero() {
        Binary a = new Binary("1011");
        Binary b = new Binary("0");
        assertEquals("0", Binary.multiply(a, b).getValue());
    }

    @Test
    public void testMultiplyByOne() {
        Binary a = new Binary("1011");
        Binary b = new Binary("1");
        assertEquals("1011", Binary.multiply(a, b).getValue());
    }

    @Test
    public void testMultiplyWithLeadingZeros() {
        Binary a = new Binary("00101");
        Binary b = new Binary("00010");
        assertEquals("1010", Binary.multiply(a, b).getValue());
    }

    // AND

    @Test
    public void testAndOperation() {
        Binary a = new Binary("1101");
        Binary b = new Binary("1011");
        assertEquals("1001", Binary.and(a, b).getValue());
    }

    @Test
    public void testAndWithZero() {
        Binary a = new Binary("1111");
        Binary b = new Binary("0");
        assertEquals("0", Binary.and(a, b).getValue());
    }

    @Test
    public void testAndDifferentLengths() {
        Binary a = new Binary("101");
        Binary b = new Binary("11111");
        assertEquals("101", Binary.and(a, b).getValue());
    }

    // OR

    @Test
    public void testOrOperation() {
        Binary a = new Binary("1100");
        Binary b = new Binary("0011");
        assertEquals("1111", Binary.or(a, b).getValue());
    }

    @Test
    public void testOrWithZero() {
        Binary a = new Binary("10101");
        Binary b = new Binary("0");
        assertEquals("10101", Binary.or(a, b).getValue());
    }

    @Test
    public void testOrDifferentLengths() {
        Binary a = new Binary("101");
        Binary b = new Binary("10000");
        assertEquals("10101", Binary.or(a, b).getValue());
    }
}

