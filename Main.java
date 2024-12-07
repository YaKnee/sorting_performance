import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import helpers.PerformanceTable;
import static helpers.Searching.linearSearch;
import static helpers.Searching.binarySearch;
import static helpers.Sorting.insertionSort;
import static helpers.Sorting.quickSort;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equalsIgnoreCase("q")) {
            displayMenu();
            input = scanner.next();
            switch (input) {
                case "1":
                    searchWith(scanner, "linear");
                    break;
                case "2":
                    searchWith(scanner, "binary");
                    break;
                case "3":
                    sortWith("insertion sorting");
                    break;
                case "4":
                    sortWith("quicksort");
                    break;
                case "5":
                    new PerformanceTable();
                    break;
                case "q":
                case "Q":
                    break;
                default:
                    System.out.println("\nInvalid input: " + input);
                    System.out.println("Please only input values from 1-5, or enter q to quit.\n");
                    break;
            }
        }
        scanner.close();
        System.out.println("Goodbye.");
    }

    private static void displayMenu() {
        System.out.println("Menu of Searching and Sorting Testbed.\n");
        System.out.println("1)\tLinear searching");
        System.out.println("2)\tBinary searching");
        System.out.println("3)\t0(n^2) type of sorting");
        System.out.println("4)\t0(n*log(n)) type of sorting");
        System.out.println("5)\tSorting performance\n");
        System.out.println("q/Q)\tQuit\n");
        System.out.print("Your choice: ");
    }

    private static Integer[] generateRandomList() {
        Integer[] array = new Integer[10];
        int max = 100;
        int min = -100;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int number = rand.nextInt(max - min + 1) + min;
            array[i] = number;
        }
        return array;
    }

    private static void printArray(Integer[] array) {
        for (int num: array) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }

    private static void searchWith(Scanner scanner, String searchingMethod) {
        Integer[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean found;
        System.out.print("In the list are values 0, ..., 9; which value would you like to search with " + searchingMethod + " search? ");
        try {
            int target = scanner.nextInt();
            if (searchingMethod.equals("linear")) {
                found = linearSearch(list, 0, 9, target);
            } else {
                found = binarySearch(list, 0, 9, target);
            }
            System.out.println();
            System.out.println(found ? "Found" : "Not found");
            System.out.println();
        } catch(InputMismatchException e) {
            System.out.println("\nInvalid input. Try again with an integer.\n");
            scanner.nextLine();
        }
    }

    private static void sortWith(String sortingMethod) {
        Integer[] randomList = generateRandomList();
        System.out.println("\nData set before " + sortingMethod + ":");
        printArray(randomList);
        if (sortingMethod.equals("quicksort")) {
            quickSort(randomList);
        } else {
            insertionSort(randomList);
        }
        System.out.println("Data set after " + sortingMethod + ":");
        printArray(randomList);
    }
}
