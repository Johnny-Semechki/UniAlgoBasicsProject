package Tests;

import java.io.FileNotFoundException;

import org.junit.Test;

import Maman13.MaxMinHeap;

public class MaxMinHeapTester {
    @Test
    public void buildByArrayTest() {
        int[] heap = {12, 5, 11, 3, 10, 2, 9, 4, 8, 15, 7, 6};
        MaxMinHeap m = new MaxMinHeap(heap);
        m.prinTree();
    }

    @Test
    public void buildByCsvTest() throws FileNotFoundException {
        MaxMinHeap m = new MaxMinHeap("/Users/yehonatanshilo/AlgoBasicsCourse/UniAlgoBasicsProject/Tests/heap-input.csv");
        m.prinTree();
    }

    @Test
    public void testsForInsert() {
        testInsert(0);
        testInsert(20);
        testInsert(13);
        testInsert(1);
        testInsert(8);
        testInsert(9);
    }

    public void testInsert(int element) {
        int[] heap = {12, 5, 11, 3, 10, 2, 9, 4, 8, 15, 7, 6};

        MaxMinHeap m = new MaxMinHeap(heap);
        System.out.println("Before insert " + element);
        m.prinTree();
        m.insert(element);
        System.out.println("After insert " + element);
        m.prinTree();
    }

    @Test
    public void testsForDelete() throws Exception {
        testDelete(2);
        testDelete(4);
        testDelete(11);
        testDelete(8);
        testDelete(0);
    }

    public void testDelete(int index) throws Exception {
        int[] heap = {12, 5, 11, 3, 10, 2, 9, 4, 8, 15, 7, 6};

        MaxMinHeap m = new MaxMinHeap(heap);
        System.out.println("Before delete " + index);
        m.prinTree();
        m.heapDelete(index);
        System.out.println("After delete " + index);
        m.prinTree();
    }
}
