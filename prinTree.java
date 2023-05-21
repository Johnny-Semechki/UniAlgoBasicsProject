import Maman13.*;
public class prinTree {
    public void prinTree(MaxMinHeap m) {
        int[] _heapArr = m.getHeapArr(); 
        int maxDepth = m.getIdDepth(_heapArr.length - 1);
    
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

        public int getRightChildId(int i) {
            return i * 2 + 2;
        }
        public int getLeftChildId(int i) {
            return i * 2 + 1;
        }
        public int getParentId(int i) {
            if(i % 2 == 0) {
                return ((i - 2) / 2);
            }
            return ((i - 1) / 2);
        }
    }

