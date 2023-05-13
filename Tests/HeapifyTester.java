package Tests;

import Maman13.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapifyTester {
    
    @Test
    public void testMaxHeapify() {
        int[] heap = {9, 7, 6, 5, 3, 2, 1};
        int[] expected = {9, 7, 6, 5, 3, 2, 1};
        MaxMinHeap maxHeap = new MaxMinHeap();
        maxHeap.maxHeapify(heap, 0);
        assertArrayEquals(expected, heap);

        heap = new int[] {7, 5, 6, 1, 3, 2, 4};
        expected = new int[] {7, 5, 6, 1, 3, 2, 4};
        maxHeap.maxHeapify(heap, 1);
        assertArrayEquals(expected, heap);

        heap = new int[] {3, 2, 5, 1, 7, 8, 6};
        expected = new int[] {5, 2, 8, 1, 7, 3, 6};
        maxHeap.maxHeapify(heap, 0);
        assertArrayEquals(expected, heap);

        heap = new int[] {9, 5, 8, 3, 2, 7, 1, 6, 4};
        expected = new int[] {9, 6, 8, 3, 2, 7, 1, 5, 4};
        maxHeap.maxHeapify(heap, 1);
        assertArrayEquals(expected, heap);
    }

    @Test
    public void testMinHeapify() {
        int[] heap = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MaxMinHeap minHeap = new MaxMinHeap();
        minHeap.minHeapify(heap, 0);
        assertArrayEquals(expected, heap);

        heap = new int[] {7, 5, 6, 1, 3, 2, 4};
        expected = new int[] {1, 5, 6, 7, 3, 2, 4};
        minHeap.minHeapify(heap, 1);
        assertArrayEquals(expected, heap);

        heap = new int[] {3, 2, 5, 1, 7, 8, 6};
        expected = new int[] {3, 2, 5, 1, 7, 8, 6};
        minHeap.minHeapify(heap, 0);
        assertArrayEquals(expected, heap);

        heap = new int[] {9, 5, 8, 3, 2, 7, 1, 6, 4};
        expected = new int[] {1, 5, 8, 3, 2, 7, 9, 6, 4};
        minHeap.minHeapify(heap, 1);
        assertArrayEquals(expected, heap);
    }

    @Test
    public void testHeapify() {
        MaxMinHeap maxMinHeap = new MaxMinHeap();

        int[] heap1 = new int[]{3, 5, 7, 8, 10, 13, 15, 19, 21, 22, 24, 26, 28, 29, 30};
        maxMinHeap.heapify(heap1, 2);
        assertArrayEquals(new int[]{3, 5, 29, 8, 10, 13, 15, 19, 21, 22, 24, 26, 28, 7, 30}, heap1);

        int[] heap2 = new int[]{30, 28, 26, 24, 22, 21, 19, 15, 13, 10, 8, 7, 5, 3};
        maxMinHeap.heapify(heap2, 3);
        assertArrayEquals(new int[]{30, 28, 26, 24, 22, 21, 19, 15, 13, 3, 7, 5, 10, 8}, heap2);

        int[] heap3 = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        maxMinHeap.heapify(heap3, 1);
        assertArrayEquals(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, heap3);

        int[] heap4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        maxMinHeap.heapify(heap4, 2);
        assertArrayEquals(new int[]{1, 2, 7, 4, 5, 6, 3, 8}, heap4);

        int[] heap5 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        maxMinHeap.heapify(heap5, 1);
        assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, heap5);

        int[] heap6 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        maxMinHeap.heapify(heap6, 2);
        assertArrayEquals(new int[]{1, 2, 9, 4, 5, 6, 7, 8, 3}, heap6);
    }

}
