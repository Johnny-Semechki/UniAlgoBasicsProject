package Tests;

import java.io.FileNotFoundException;

import org.junit.Test;

import Maman13.MaxMinHeap;

public class MaxMinHeapTester {
    @Test
    public void buildByArrayTest() {
        int[] heap1 = {12, 5, 11, 3, 10, 2, 9, 4, 8, 15, 7, 6};
        int[] heap2 = {31, 51, 11, 13, 41, 46, 31, 16, 8, 71, 10, 21};
        int[] heap3 = {1};
        int[] heap4 = {15, 14, 80, 32, 18, 8, 13, 31, 17, 20, 12, 30, 16, 15, 10};
        int[] heap5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        testBuildHeap(heap1);
        testBuildHeap(heap2);
        testBuildHeap(heap3);
        testBuildHeap(heap4);
        testBuildHeap(heap5);
    }

    public void testBuildHeap(int[] arr) {
        System.out.println("Inputed array as heap before sorting:");
        MaxMinHeap m = new MaxMinHeap(arr, true);
        System.out.println("Array after sorting");
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
        int numToDelete = m.getHeapArr()[index];
        System.out.println(String.format("Before deleting heap[%d]=", index) + numToDelete);
        m.prinTree();
        m.heapDelete(index);
        System.out.println(String.format("After deleting heap[%d]=", index) + numToDelete);
        m.prinTree();
    }
}
