/* Alexander Siruno-Nebel
 * Collection of several sorting algorithms. Allows choice between bubble, selection, table, and quicksort of a file
 * Jan 5, 2022
 */


package Sorting;

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Sorting {
	
	//vars
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public Sorting() {
		//evaluates input
		System.out.println("Enter a number for the input file");
		System.out.println("1: input1.txt 2: input2.txt 3: input4.txt 4: input4.txt");
		input = consoleInput.nextLine();
		if(input.length()!= 1 && input.charAt(0) !='1' && input.charAt(0) !='2' && input.charAt(0) !='3' && input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, 3, or 4");
			while(input.length() != 1 && input.charAt(0) !='1' && input.charAt(0) !='2' && input.charAt(0) !='3' && input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}	
		}
		try {
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		String inFile = fileInput.nextLine();
		String[] inputStringArray = inFile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		System.out.println("Enter a number for the sort method");
		System.out.println("1: Bubble 2: Selection 3: Table 4: Quick");
		input = consoleInput.nextLine();
		if(input.length()!= 1 && input.charAt(0) !='1' && input.charAt(0) !='2' && input.charAt(0) !='3' && input.charAt(0) !='4') {
			System.out.println("Enter 1, 2, 3, or 4");
			while(input.length() != 1 && input.charAt(0) !='1' && input.charAt(0) !='2' && input.charAt(0) !='3' && input.charAt(0) !='4') {
				input = consoleInput.nextLine();
			}	
		}
		startTime = System.currentTimeMillis();
		if (input.equals("1")) {
			inputArray = bubbleSort(inputArray);
		}
		if(input.equals("2")){
			inputArray = selectionSort(inputArray);
		}
		if(input.equals("3")){
			inputArray = tableSort(inputArray);
		}
		if(input.equals("4")){
			inputArray = quickSort(inputArray, 0, inputArray.length - 1);
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total time: "+totalTime);
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			output += "Total time: " + totalTime + "\n";
			for (int i = 0; i < inputStringArray.length; i++) {
				output += inputArray[i] + ", ";
			}
			pw.write(output);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	
	int pivot(int[] array, int low, int high) {// finds pivot point of the function
        int sorter = array[high];
        int i = (low - 1);// i is starting value
        for (int j = low; j < high; j++) {
            if (array[j] < sorter) {
                i++;
                int ph = array[i];// swaps i and j
                array[i] = array[j];
                array[j] = ph;
            }
        }

        int ph = array[i + 1];// swaps
        array[i + 1] = array[high];
        array[high] = ph;

        return i + 1;
    }

    int[] quickSort(int[] array, int fromLeft, int fromRight) {// finds the pivot, compares from left and from right, looking for numbers > and < the pivot point
        /*
        https://medium.com/karuna-sehgal/a-quick-explanation-of-quick-sort-7d8e2563629b
        an explanation i was sent about how quicksort functions.
        */
        if (fromLeft < fromRight) {
            int sorter = pivot(array, fromLeft, fromRight);
            quickSort(array, fromLeft, sorter - 1);
            quickSort(array, sorter + 1, fromRight);
        } else {
        }
		return array;
    }
	
    
    
	//Finds the smallest, moves to front
	int[] selectionSort(int[]array) {
		for (int j = 0; j < array.length; j++) {
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) {
				if(array[i] < smallestNumber) {
					smallestNumber = array[i];
					smallestIndex = i;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
		}
		
		return array;
	}
	
	//Compares each pair of numbers, moves larger to right
	int[] bubbleSort(int[] array) {
		for(int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				//if left >
				if(array[i] > array[i+1]) {
					//swap
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
		}
		return array;
	}
	
	//Tallies how often you see each number, prints out that number of times
		int[] tableSort(int[] array) {
			int[] tally = new int[1001];
			for (int i = 0; i < array.length; i++) {
				tally[array[i]]++;
			}
				
			int count = 0;
			//i keeps track of actual number
			for (int i = 0; i < tally.length; i++) {
				//j keeps track of how many times number is seen
				for (int j = 0; j < tally[i]; j++) {
					array[count]=i;
					count++;
				}
			}
			
			return array;
		}
	
	public static void main(String[] args) {
		new Sorting();
	}
	
	
	
}
