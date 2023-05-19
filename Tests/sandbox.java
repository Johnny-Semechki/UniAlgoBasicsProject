package Tests;

import Maman13.*;

public class sandbox {
/*              1
             /    \
          346      48
         /   \    /  \
      4245    43 5    567
     /    \           /   \
45455      56      768


*/

public static void main(String[] args) {

    MaxMinHeap heap = new MaxMinHeap();

    int[] arr = {78, 34, 8, 425, 143, 53, 536, 455, 1256, 32};
    //int[] arr = {1, 346, 48, 4245, 43, 5, 567, 45455, 56};
    //System.out.println(heap.getIdDepth(arr.length - 1));

    heap.prinTree(arr);
}

}


