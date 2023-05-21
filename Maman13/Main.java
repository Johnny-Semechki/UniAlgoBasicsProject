package Maman13;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] startMenu = {"1 - build max-min heap",
                              "2 - Exit"

        };
        boolean keepMenu = true;
        while(keepMenu) {
            Scanner scanner = new Scanner(System.in);
            printMenu(startMenu);
            int startMenuOption;
            try {
                startMenuOption = scanner.nextInt();
                switch (startMenuOption){
                    case 1: buildHeapOption(); break;
                    case 2: keepMenu = false; break;
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + startMenu.length);
                scanner.next();
            }
        }
    }

// Options
    private static void buildHeapOption() throws FileNotFoundException {
        System.out.println("input relative path to csv file with numbers seperated by commas and no spaces: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        MaxMinHeap heap = new MaxMinHeap(path);
        heap.prinTree();

        String[] options = {"1 - HEAP-EXTRACT-MAX",
                            "2 - HEAP-EXTRACT-Min",
                            "3 - HEAP-INSERT",
                            "4 - HEAP-DELETE",
                            "5 - Exit",
        };
        int option = 1;
            while(option != options.length) {
                printMenu(options);
                try {
                    option = scanner.nextInt();
                    switch (option){
                        case 1: option1(heap); break;
                        case 2: option2(heap); break;
                        case 3: option3(heap); break; 
                        case 4: option4(heap); break;
                        case 5: option = options.length; break;
                    }
                }
                catch (Exception ex){
                    System.out.println("Please enter an integer value between 1 and " + options.length);
                    scanner.next();
                }
            }
    }
    private static void option1(MaxMinHeap heap) {
        heap.heapExtractMax();
    }
    private static void option2(MaxMinHeap heap) {
        heap.heapExtractMin();
    }
    private static void option3(MaxMinHeap heap) {
        System.out.println("Please enter value to insert: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int input = scanner.nextInt();
            heap.insert(input);
            heap.prinTree();
        }
        catch (Exception ex){
            System.out.println("Please enter an integer value");
            scanner.next();
        }
        
    }
    private static void option4(MaxMinHeap heap) throws Exception {
        System.out.println("Please enter index to delete: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int input = scanner.nextInt();
            heap.heapDelete(input);
            heap.prinTree();
        }
        catch (Exception ex){
            System.out.println("Please enter an integer value");
            scanner.next();
        }
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
}
