package Maman13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// import java.text.DecimalFormat;

// import javax.swing.text.AbstractDocument.LeafElement;

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

    /**
     * @param csvFileName
     * @throws FileNotFoundException
     */
    public MaxMinHeap(String csvFileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(csvFileName));

        sc.useDelimiter(",");
        ArrayList<Integer> csvInput = new ArrayList<Integer>();

        while (sc.hasNext()) {
            csvInput.add(Integer.parseInt(sc.next()));
        }

        Integer[] initArray = new Integer[csvInput.size()];
        initArray = csvInput.toArray(initArray);
        int[] initArrayForHeap = new int[initArray.length];
        for (int i = 0; i < initArray.length; ++i) {
            initArrayForHeap[i] = initArray[i];
        }
        initHeap(initArrayForHeap);
    }

    public MaxMinHeap(int[] initArray) {
        initHeap(initArray);
    }

    public MaxMinHeap(int[] initArray, boolean print) {
        if(print) {
            _heapArr = initArray;
            prinTree();
        }
        initHeap(initArray);
    }

    private void initHeap(int[] initArray) {
        _heapArr = initArray;

        for (int i = _heapArr.length/2; i >= 0; i--) {
            heapify(i);
        }
    }

    public void printHeap() {
        System.out.println("Heap contents:");
        for (int i = 0; i < _heapArr.length ; i++) {
            System.out.println(_heapArr[i]);
        }
    }

    /**
     * @param _heapArr
     * @param i
     */
    public void heapify(int i) {

        if(isEven(getIdDepth(i))) {
            maxHeapify(i);
        }
        else {
            minHeapify(i);
        }
    }

    /**
     * @param heap
     * @param i
     */
    public void maxHeapify(int i) {
        if(hasKids(i)) {
            int maxDescendantId = getMaxDecendentIndex(i);

            if(maxDescendantId != i) { //Check if inputed node isn't bigger than its 2 generation descendants
                swap(i, maxDescendantId);

                if(maxDescendantId > getRightChildId(i)) { //check if maxDescendantId is grandkid

                    if(_heapArr[maxDescendantId] < _heapArr[getParentId(maxDescendantId)]) {
                        swap(getParentId(maxDescendantId), maxDescendantId);
                    }
                    heapify(maxDescendantId);
                }
            }
        }
    }

    /**
     * @param heap
     * @param i
     */
    public void minHeapify(int i) {  
        if(hasKids(i)) {
            int minDescendantId = getMinDecendentIndex(i);

            if(minDescendantId != i) { //Check if inputed node isn't smaller than its 2 generation descendants
                swap(i, minDescendantId);

                if(minDescendantId > getRightChildId(i)) { //check if minDescendantId is grandkid

                    if(_heapArr[minDescendantId] > _heapArr[getParentId(minDescendantId)]) {
                        swap(getParentId(minDescendantId), minDescendantId);
                    }
                    heapify(minDescendantId);
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

    public void insert(int key) {
        pushElement(key);
        int keyIndex = _heapArr.length - 1;

        reverseHeapify(keyIndex);
    }
    private void reverseHeapify(int keyIndex) {
        if(hasGrandparent(keyIndex)) {
            if(isEven(getIdDepth(keyIndex))) {
                if(_heapArr[keyIndex] < _heapArr[getParentId(keyIndex)]) {
                    swap(keyIndex, getParentId(keyIndex));
                    reverseMinHeapify(getParentId(keyIndex));
                }
                else {
                    reverseMaxHeapify(keyIndex);
                }
            }
            else {
                if(_heapArr[keyIndex] > _heapArr[getParentId(keyIndex)]) {
                    swap(keyIndex, getParentId(keyIndex));
                    reverseMaxHeapify(getParentId(keyIndex));
                }
                else {
                    reverseMinHeapify(keyIndex);
                }
            }            
        }
        else { //keyIndex is the root or one of its  sons
            if(!(isEven(getIdDepth(keyIndex))) && _heapArr[keyIndex] > _heapArr[0]) {
                swap(keyIndex, 0);                
            }
        }
    }
    private void reverseMinHeapify(int i) {
        while (hasGrandparent(i) && _heapArr[i] < _heapArr[getParentId(getParentId(i))]) {
            swap(i, getParentId(getParentId(i)));
            i = getParentId(getParentId(i));
        }
    }
    private void reverseMaxHeapify(int i) {
        while (hasGrandparent(i) && _heapArr[i] > _heapArr[getParentId(getParentId(i))]) {
            swap(i, getParentId(getParentId(i)));
            i = getParentId(getParentId(i));
        }
    }

    public void heapDelete(int i) throws Exception {
        if (i >= _heapArr.length || i < 0) {
            throw new Exception("Invalid heap index input");
        }

        int lastNodeIndex = _heapArr.length - 1;

        if (i == lastNodeIndex) {
            popElement();
            return;
        }

        swap(lastNodeIndex, i);
        int element = popElement();
        if(_heapArr[i] != element) {
            reverseHeapify(i);
            heapify(i);
        }

        // if(isEven(getIdDepth(i))) {
        //     while(hasGrandkids(i)) {
        //         swap(i, getMaxGrandkidIndex(i));
        //         i = getMaxGrandkidIndex(i);
        //     }

        //     if(hasKids(i)) {
        //         int maxKid = getMaxDecendentIndex(i);
        //         if(_heapArr[lastNodeIndex] >= _heapArr[maxKid]) {
        //             swap(lastNodeIndex, i);
        //             reverseHeapify(i);
        //         }
        //         else {
        //             swap(lastNodeIndex, i);
        //             swap(i, maxKid);
        //         }
        //     }
        //     else {
        //         swap(lastNodeIndex, i);
        //         reverseHeapify(i);
        //     }
        // }

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

    private int getMaxDecendentIndex(int i) {
        int[] descendants = new int[6];

        descendants[0] = getLeftChildId(i);
        descendants[1] = getRightChildId(i);
        descendants[2] = getLeftChildId(descendants[0]);
        descendants[3] = getRightChildId(descendants[0]);
        descendants[4] = getLeftChildId(descendants[1]);
        descendants[5] = getRightChildId(descendants[1]);

        int max = _heapArr[i];
        int maxIndex = i;
        for(int k = 0; k < 6; k++) {

            if(descendants[k] >= _heapArr.length) {
                break;
            }

            if(_heapArr[descendants[k]] > max) {
                max = _heapArr[descendants[k]];
                maxIndex = descendants[k];
            }
        }
        return maxIndex;
    }
    // private int getMaxGrandkidIndex(int i) {
    //     int[] descendants = new int[4];

    //     descendants[0] = getLeftChildId(getLeftChildId(i));
    //     descendants[1] = descendants[0] + 1;
    //     descendants[2] = descendants[1] + 1;
    //     descendants[3] = descendants[2] + 1;

    //     int max = _heapArr[descendants[0]];
    //     int maxIndex = descendants[0];
    //     for(int k = 1; k < 4; k++) {

    //         if(descendants[k] >= _heapArr.length) {
    //             break;
    //         }

    //         if(_heapArr[descendants[k]] > max) {
    //             max = _heapArr[descendants[k]];
    //             maxIndex = descendants[k];
    //         }
    //     }
    //     return maxIndex;
    // }

    private int getMinDecendentIndex(int i) {
        int[] descendants = new int[6];

        descendants[0] = getLeftChildId(i);
        descendants[1] = getRightChildId(i);
        descendants[2] = getLeftChildId(descendants[0]);
        descendants[3] = getRightChildId(descendants[0]);
        descendants[4] = getLeftChildId(descendants[1]);
        descendants[5] = getRightChildId(descendants[1]);

        int min = _heapArr[i];
        int minIndex = i;
        for(int k = 0; k < 6; k++) {

            if(descendants[k] >= _heapArr.length) {
                break;
            }

            if(_heapArr[descendants[k]] < min) {
                min = _heapArr[descendants[k]];
                minIndex = descendants[k];
            }
        }
        return minIndex;
    }
    // private int getMinGrandkidIndex(int i) {
    //     int[] descendants = new int[4];

    //     descendants[0] = getLeftChildId(getLeftChildId(i));
    //     descendants[1] = descendants[0] + 1;
    //     descendants[2] = descendants[1] + 1;
    //     descendants[3] = descendants[2] + 1;

    //     int min = _heapArr[descendants[0]];
    //     int minIndex = descendants[0];
    //     for(int k = 1; k < 4; k++) {

    //         if(descendants[k] >= _heapArr.length) {
    //             break;
    //         }

    //         if(_heapArr[descendants[k]] < min) {
    //             min = _heapArr[descendants[k]];
    //             minIndex = descendants[k];
    //         }
    //     }
    //     return minIndex;
    // }

    /**
     * @param _heapArr
     * @param i
     * @param generations
     * @return
     */
    // private int getMaxDescendantId(int[] heap, int i, int generations) {
    //     if(generations == 0 || !(hasKids(heap, i))) {
    //         return i;
    //     }

    //     int maxLeftId = getMaxDescendantId(heap, getLeftChildId(i), generations--);
    //     int maxRightId;
    //     if(getRightChildId(i) < heap.length) { //checking if i has two kids

    //         maxRightId = getMaxDescendantId(heap, getRightChildId(i), generations--);
    //         if(heap[i] <= heap[maxLeftId]) {
    //             if(heap[i] <= heap[maxRightId]) {
    //                 return i;
    //             }
    //             return maxRightId;
    //         }
    //         if(heap[maxLeftId] <= heap[maxRightId]) {
    //             return maxLeftId;
    //         }
    //         return maxRightId;
    //     }

    //     if(heap[i] <= heap[maxLeftId]) {
    //         return i;
    //     }
    //     return maxLeftId;
    // }
    
    /**
     * @param heap
     * @param i
     * @param generations
     * @return
     */
    // private int getMinDescendantId(int[] heap, int i, int generations) {

    //     if(generations == 0 || !(hasKids(heap, i))) {
    //         return i;
    //     }

    //     int minLeftId = getMinDescendantId(heap, getLeftChildId(i), generations--);
    //     int minRightId;
    //     if(getRightChildId(i) < heap.length) { //checking if i has two kids

    //         minRightId = getMinDescendantId(heap, getRightChildId(i), generations--);
    //         if(heap[i] <= heap[minLeftId]) {
    //             if(heap[i] <= heap[minRightId]) {
    //                 return i;
    //             }
    //             return minRightId;
    //         }
    //         if(heap[minLeftId] <= heap[minRightId]) {
    //             return minLeftId;
    //         }
    //         return minRightId;
    //     }

    //     if(heap[i] <= heap[minLeftId]) {
    //         return i;
    //     }
    //     return minLeftId;
    // }

    /**
     * @param _heapArr
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        System.out.println("Swapping " + _heapArr[i] + " <-> " + _heapArr[j]);
        int temp = _heapArr[i];
        _heapArr[i] = _heapArr[j];
        _heapArr[j] = temp;
    }

    /**
     * @param i
     * @return
     */
    public int getIdDepth(int i) {
        if(i == 0) {
            return 0;
        }
        return (int) Math.floor(Math.log(i + 1) / Math.log(2));
    }

    private boolean hasKids(int i) {
        return (getLeftChildId(i) < _heapArr.length);
    }
    private boolean hasGrandkids(int i) {
        return (getLeftChildId(getLeftChildId(i)) < _heapArr.length);
    }

    private void pushElement(int key) {
        int[] newArr = new int[_heapArr.length + 1];

        for(int i = 0; i < _heapArr.length; i++) {
            newArr[i] = _heapArr[i];
        }
        newArr[_heapArr.length] = key;
        _heapArr = newArr;
    }

    private int popElement() {
        int newHeap[] = new int[_heapArr.length - 1];
        int element = _heapArr[_heapArr.length - 1];

        for(int k = 0; k < newHeap.length; k++) {
            newHeap[k] = _heapArr[k];
        }

        _heapArr = newHeap;

        return element;
    }

    private boolean hasGrandparent(int i) {
        return (i > 2);
    }

    public void prinTree() {
    int maxDepth = getIdDepth(_heapArr.length - 1);

        String[] strArr = new String[_heapArr.length];
        for(int i = 0; i < _heapArr.length; i++) {
            strArr[i] = String.valueOf(_heapArr[i]);
        }

        int initialSpaces, spacesAfterNode, spacesBetweenLinks, stopId;
        String currLine, nextLine;
        String slash = "/", backslash = "\\";

        for(int j = 0; j <= maxDepth + 1; j++) {
            
            initialSpaces = 0;
            for(int k = j + 1; k <= maxDepth; k++) {
                initialSpaces += strArr[getLevelStartId(k)].length() + 1;
            }

            currLine = String.format("%1$" + (initialSpaces + 1) + "s", "");     
            nextLine = String.format("%1$" + Math.max(initialSpaces, 1) + "s", "");

            stopId = Math.min(getLevelStartId(j + 1), strArr.length);

            for(int l = getLevelStartId(j); l < stopId; l++) {

                spacesBetweenLinks = calcSpacedLinks(strArr, l);

                if(isEven(l)) {
                    if(spacesBetweenLinks > strArr[l].length() + 1) {
                        currLine += String.format("%1$" + ((spacesBetweenLinks - strArr[l].length()) / 2) + "s", "");
                    }
                    currLine += strArr[l] + " ";
                    nextLine += slash + String.format("%1$" + spacesBetweenLinks + "s", "") + backslash;
                }
                else {
                    if(spacesBetweenLinks > strArr[l].length() + 1) {
                        currLine += String.format("%1$" + ((spacesBetweenLinks - strArr[l].length()) / 2) + "s", "");
                    }
                    spacesAfterNode = calcSpacesAfterOdd(strArr, l);

                    currLine += strArr[l] + String.format("%1$" + spacesAfterNode + "s", "");
                    nextLine += slash + String.format("%1$" + spacesBetweenLinks + "s", "") + backslash + 
                    String.format("%1$" + (spacesAfterNode - 2) + "s", "");
                }
            }

            System.out.println(currLine);
            if(j != maxDepth) {
                System.out.println(nextLine);
            }
        } 
    }

    private boolean isEven(int num) {
        return (num % 2 == 0);
    }

    public int getLevelStartId(int level) {
        return (int) Math.pow(2, level) - 1;
    }

    private int calcSpacedLinks(String[] heapArr, int i) {
        int grandKid1Length = 0, grandKid2Length = 0;

        int grandKid1Id = getLeftChildId(getRightChildId(i)), 
        grandKid2Id = getRightChildId(getLeftChildId(i));

        if(grandKid1Id < heapArr.length) {
            grandKid1Length = heapArr[grandKid1Id].length();
        }
        if(grandKid2Id < heapArr.length) {
            grandKid2Length = heapArr[grandKid2Id].length();
        }

        return Math.max(heapArr[i].length(), grandKid1Length + grandKid2Length + 1);
    }

    private int calcSpacesAfterOdd(String[] heapArr, int i) {
        int rightLength = 0, otherNodeLength = 0;

        int rightId = getRightChildId(i), otherNodeId = getLeftChildId(i + 1);

        if(rightId < heapArr.length) {
            rightLength = heapArr[rightId].length();
        }
        if(otherNodeId < heapArr.length) {
            otherNodeLength = heapArr[otherNodeId].length();
        }

        return Math.max(heapArr[getParentId(i)].length(), rightLength + otherNodeLength + 1) + 2;
    }

    public int[] getHeapArr() {
        return _heapArr;
    }
}