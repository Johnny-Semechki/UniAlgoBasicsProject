package Maman13;

/*
 * The class repressents a max-min heap by its array form.
 * 
 * A max-min heap is a heap which property is that each node at an even level in the tree is greater than 
 * or equals to all of its descendants, while each node at an odd level in the tree is less than or 
 * equals to all of its descendants.
 * 
 * @author Johnathan Shilo id 212938476
 * date: 14/5/2023
 * ¯\_(ツ)_/¯
 */
public class MaxMinHeap {

    private int[] _heapArr;
    private int _heapDepth;

    public void BuildHeap() {
        
    }

    public void heapify(int[] heap, int i) {
        int leftChild = getLeftChild(heap, i);
        int rightChild = getRightChild(heap, i);

        
    }

    public void maxHeapify(int[] heap, int i) {

    }
    public void minHeapify(int[] heap, int i) {

    }


    public int getRightChildId(int i) {
        return i * 2 + 2;
    }
    public int getLeftChildId(int i) {
        return i * 2 + 1;
    }
    public int getRightChild(int[] heap, int i) {
        return heap[getRightChildId(i)];
    }
    public int getLeftChild(int[] heap, int i) {
        return heap[getLeftChildId(i)];
    }

    public static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int getIdDepth(int i) {
        return (int) Math.floor(Math.log(i + 1) / Math.log(2));
    }
}