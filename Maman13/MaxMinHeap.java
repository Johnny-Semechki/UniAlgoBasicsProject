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

    /**
     * @param initArray - Array to be turned into a max-min heap
     * Constructor for the MaxMinHeap class, takes an array as an input and uses @func initHeap
     * to sort the inputed array and initialize the _heapArr.
     */
    public MaxMinHeap(int[] initArray) {
        initHeap(initArray);
    }

    /**
     * @param initArray - Array to be turned into a max-min heap
     * @param print - Prints the heap before sorting if true
     * Another onstructor for the MaxMinHeap class that prints the heap before sorting it 
     * according to print's value
     */
    public MaxMinHeap(int[] initArray, boolean print) {
        if(print) {
            _heapArr = initArray;
            prinTree();
        }
        initHeap(initArray);
    }

    /**
     * @param initArray - Array to be sorted and used as heap
     * initialize the _heapArr attribute of the class to the inputed array and sorting it
     * to fit the definition of a max-min heap using heapify
     */
    private void initHeap(int[] initArray) {
        _heapArr = initArray;

        for (int i = _heapArr.length/2; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * @param i - index of a node in the heap
     * Takes an index of a node in the heap as input and uses maxHeapify and minHeapify
     * to push its stored value down the heap where it belongs according to max-min heap's
     * definition
     */
    public void heapify(int i) {
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }

        if(isEven(getIdDepth(i))) {
            maxHeapify(i);
        }
        else {
            minHeapify(i);
        }
    }

    /**
     * @param i - index of a node in a maximum level
     * Get an index of a maximum level node and check if it needs to be pushed down the heap
     * to meet the heap's criteria
     */
    private void maxHeapify(int i) {
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }

        if(hasKids(i)) {
            int maxDescendantId = getMaxDecendentIndex(i);

            if(maxDescendantId != i) { //Check if inputed node isn't bigger than its 2 generation descendants
                swap(i, maxDescendantId);

                if(maxDescendantId > getRightChildId(i)) { //check if maxDescendantId is grandkid

                    if(_heapArr[maxDescendantId] < _heapArr[getParentId(maxDescendantId)]) { // check if i's value (now maxDescendantId's value) is smaller than minDescendantId's parent
                        swap(getParentId(maxDescendantId), maxDescendantId);
                    }
                    heapify(maxDescendantId);
                }
            }
        }
    }

    /**
     * @param i - index of a node in a minimum level
     * Get an index of a minimum level node and check if it needs to be pushed down the heap
     * to meet the heap's criteria
     */
    private void minHeapify(int i) {  
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }

        if(hasKids(i)) {
            int minDescendantId = getMinDecendentIndex(i);

            if(minDescendantId != i) { //Check if inputed node isn't smaller than its 2 generation descendants
                swap(i, minDescendantId);

                if(minDescendantId > getRightChildId(i)) { //check if minDescendantId is grandkid

                    if(_heapArr[minDescendantId] > _heapArr[getParentId(minDescendantId)]) { // check if i's value (now minDescendantId's value) is bigger than minDescendantId's parent
                        swap(getParentId(minDescendantId), minDescendantId);
                    }
                    heapify(minDescendantId);
                }
            }
        }
    }

    /**
     * @return _heapArr's root
     * returns the maximum value of the heap
     */
    public int heapExtractMax() {
        int maxVal = _heapArr[0];
        System.out.println("Max value of this heap is: " + maxVal);
        return maxVal;
    }

    /**
     * @return the minimum between _heapArr's root's kids
     * returns the minimum value of the heap
     */
    public int heapExtractMin() {
        int minVal = Math.min(_heapArr[1], _heapArr[2]);
        System.out.println("Min value of this heap is: " + minVal);
        return minVal;
    }

    /**
     * @param key value to insert to the heap
     * gets a value as input and pushes it to the heap's array, than sorting it so it'll be a valid max-min heap
     */
    public void insert(int key) {
        pushElement(key);
        int keyIndex = _heapArr.length - 1;

        reverseHeapify(keyIndex);
    }

    /**
     * @param keyIndex - index of a node in the heap
     * Takes an index of a node in the heap as input and uses reverseMaxHeapify and reverseMinHeapify
     * to push its stored value up the heap where it belongs according to max-min heap's
     * definition
     */
    private void reverseHeapify(int keyIndex) {
        if(keyIndex >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }
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

    /**
     * @param i - index of a node in a minimum level
     * Get an index of a minimum level node and check if it needs to be pushed up the heap
     * to meet the heap's criteria
     */
    private void reverseMinHeapify(int i) {
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }

        while (hasGrandparent(i) && _heapArr[i] < _heapArr[getParentId(getParentId(i))]) {
            swap(i, getParentId(getParentId(i)));
            i = getParentId(getParentId(i));
        }
    }
    
    /**
     * @param i - index of a node in a maximum level
     * Get an index of a maximum level node and check if it needs to be pushed up the heap
     * to meet the heap's criteria
     */
    private void reverseMaxHeapify(int i) {
        while (hasGrandparent(i) && _heapArr[i] > _heapArr[getParentId(getParentId(i))]) {
            swap(i, getParentId(getParentId(i)));
            i = getParentId(getParentId(i));
        }
    }

    /**
     * @param i - index of a node to delete from the heap
     * @throws Exception if inputed index isn't within heap's range
     * Gets a node's index as an input and removes it's value from the heap's array while keeping the heap 
     * a valid max-min heap
     */
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
    }

    /**
     * @param i - index of a node
     * @return - the index of i's right child node
     */
    public int getRightChildId(int i) {
        return i * 2 + 2;
    }
    
    /**
     * @param i - index of a node
     * @return - the index of i's left child node
     */
    public int getLeftChildId(int i) {
        return i * 2 + 1;
    }

    /**
     * @param i - index of a node
     * @return - the index of i's parent node
     */
    public int getParentId(int i) {
        if(i % 2 == 0) {
            return ((i - 2) / 2);
        }
        return ((i - 1) / 2);
    }

    /**
     * @param i - index of a node
     * @return - the index of the node with the maximum value out of i and its children and grandchildren nodes
     */
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

    /**
     * @param i - index of a node
     * @return - the index of the node with the minimum value out of i and its children and grandchildren nodes
     */
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
    
    /**
     * @param i - node index
     * @param j - node index
     * gets two indexes and swap their nodes values
     */
    public void swap(int i, int j) {
        if(i >= _heapArr.length || j >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return;
        }

        System.out.println("Swapping " + _heapArr[i] + " <-> " + _heapArr[j]);
        int temp = _heapArr[i];
        _heapArr[i] = _heapArr[j];
        _heapArr[j] = temp;
    }

    /**
     * @param i - node index
     * @return the level/depth of the node with index i
     */
    public int getIdDepth(int i) {
        if(i == 0) {
            return 0;
        }
        return (int) Math.floor(Math.log(i + 1) / Math.log(2));
    }

    /**
     * @param i - node index
     * @return true if the node i has any kids
     */
    private boolean hasKids(int i) {
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return false;
        }

        return (getLeftChildId(i) < _heapArr.length);
    }

    /**
     * @param key - value to add to the heap's array
     * adds inputed value to the end of _heapArray (and changes its size accordingly)
     */
    private void pushElement(int key) {
        int[] newArr = new int[_heapArr.length + 1];

        for(int i = 0; i < _heapArr.length; i++) {
            newArr[i] = _heapArr[i];
        }
        newArr[_heapArr.length] = key;
        _heapArr = newArr;
    }

    /**
     * @return the last value of _heapArr
     * removes last value of _heapArray (and changes its size accordingly)
     */
    private int popElement() {
        int newHeap[] = new int[_heapArr.length - 1];
        int element = _heapArr[_heapArr.length - 1];

        for(int k = 0; k < newHeap.length; k++) {
            newHeap[k] = _heapArr[k];
        }

        _heapArr = newHeap;

        return element;
    }

    /**
     * @param i - node index
     * @return true if node i has a grandparent node
     */
    private boolean hasGrandparent(int i) {
        if(i >= _heapArr.length) {
            System.out.println("Invalid heap index input");
            return false;
        }

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

    /**
     * @param num - number to check
     * @return true if the number is even
     */
    private boolean isEven(int num) {
        return (num % 2 == 0);
    }

    /**
     * @param level - number of a level in a heap
     * @return the index of the first node in the inputed level
     */
    private int getLevelStartId(int level) {
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

    /**
     * @return the _heapArr attribute of a MaxMinHeap object
     */
    public int[] getHeapArr() {
        return _heapArr;
    }
}