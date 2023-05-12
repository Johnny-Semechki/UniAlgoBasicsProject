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

    /**
     * @param heap
     * @param i
     */
    public void heapify(int[] heap, int i) {

        if(getIdDepth(i) % 2 == 0) {
            maxHeapify(heap, i);
        }

        else {
            minHeapify(heap, i);
        }
    }

    public void maxHeapify(int[] heap, int i) {
        int maxDescendantId = getMaxDescendantId(heap, i, 2);

        if(hasKids(heap, i)) {
            if(maxDescendantId != i) {
                swap(heap, i, maxDescendantId);

                if(maxDescendantId > getRightChildId(i)) { //check if maxDescendantId is grandkid

                    if(heap[maxDescendantId] > heap[getParentId(maxDescendantId)]) {
                        swap(heap, getParentId(maxDescendantId), maxDescendantId);
                    }
                    heapify(heap, maxDescendantId);
                }
            }
        }
    }
    public void minHeapify(int[] heap, int i) {  
        int minDescendantId = getMinDescendantId(heap, i, 2);

        if(hasKids(heap, i)) {
            if(minDescendantId != i) {
                swap(heap, i, minDescendantId);

                if(minDescendantId > getRightChildId(i)) { //check if minDescendantId is grandkid

                    if(heap[minDescendantId] > heap[getParentId(minDescendantId)]) {
                        swap(heap, getParentId(minDescendantId), minDescendantId);
                    }
                    heapify(heap, minDescendantId);
                }
            }
        }
    }

    public int heapExtractMax() {
        int maxVal = _heapArr[0];
        System.out.println("Max value of this heap is: " + maxVal);
        return maxVal;
    }
    public int heapExtractMin() {
        int minVal = Math.min(_heapArr[1], _heapArr[2]);
        System.out.println("Min value of this heap is: " + minVal);
        return minVal;
    }

    public void heapDelete(int i) {
        int newHeap[] = new int[_heapArr.length - 1];

        int j;
        for(j = 0; j < i; j++) {
            newHeap[j] = _heapArr[j];
        }
        if(i == 0) {
            j = 0;
        }
        for(int k = i; k < newHeap.length; k++) {
            newHeap[k] = _heapArr[j];
            j++;
        }

        _heapArr = newHeap;
        heapify(_heapArr, i);
    }

    public static int getRightChildId(int i) {
        return i * 2 + 2;
    }
    public static int getLeftChildId(int i) {
        return i * 2 + 1;
    }
    public static int getParentId(int i) {
        if(i % 2 == 0) {
            return ((i - 2) / 2);
        }
        return ((i - 1) / 2);
    }

    /**
     * @param heap
     * @param i
     * @param generations
     * @return
     */
    public static int getMaxDescendantId(int[] heap, int i, int generations) {
        if(i > heap.length - 1) {
            return Integer.MIN_VALUE;
        }

        if(generations == 0 || !(hasKids(heap, i))) {
            return i;
        }

        return Math.max(i, Math.max(getMaxDescendantId(heap, getLeftChildId(i), generations--), getMaxDescendantId(heap, getRightChildId(i), generations--)));
    }
    
    /**
     * @param heap
     * @param i
     * @param generations
     * @return
     */
    public static int getMinDescendantId(int[] heap, int i, int generations) {
        if(i > heap.length - 1) {
            return Integer.MAX_VALUE;
        }

        if(generations == 0 || !(hasKids(heap, i))) {
            return i;
        }

        return Math.min(i, Math.min(getMaxDescendantId(heap, getLeftChildId(i), generations--), getMaxDescendantId(heap, getRightChildId(i), generations--)));
    }

    /**
     * @param heap
     * @param i
     * @param j
     */
    public static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * @param i
     * @return
     */
    public static int getIdDepth(int i) {
        return (int) Math.floor(Math.log(i + 1) / Math.log(2));
    }

    public static boolean hasKids(int[] heap, int i) {
        return (getLeftChildId(i) > heap.length - 1);
    }

    public void setHeapArr(int[] newHeapArr) {

    }
}