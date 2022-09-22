import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.awt.FileDialog;
import java.awt.Frame;
import java.util.Arrays;

public class NQueens {
    private int savedRow = 0; //remember queens location for min conflicts
    private int savedColumn = 0;
    public int getSavedRow() {
        return savedRow;
    }
    public void setSavedRow(int savedRow) {
        this.savedRow = savedRow;
    }
    public int getSavedColumn() {
        return savedColumn;
    }
    public void setSavedColumn(int savedColumn) {
        this.savedColumn = savedColumn;
    }
    public static void main(String[] args) throws FileNotFoundException {
        NQueens demo = new NQueens();
        HillClimbingQueen hillDemo = new HillClimbingQueen();
        int [][] matrix = new int [8][8];
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which board choice will you use?\n"
        + "[1] Randomly Generated\n[2] From File");
        int boardChoice = keyboard.nextInt();
        if (boardChoice == 1)
            matrix = demo.createRandom();
        else
            matrix = demo.readFile(8, 8);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Which algorithm do you want to use?\n" +
        "[1] Hill Climbing\n[2] Min-Conflicts");
        int algInput = keyboard.nextInt();

        boolean algChoice = true; //if true do hill climbing
        if (algInput == 1) 
            hillDemo.hillClimbing(matrix);
        else {
            algChoice = false;
            hillDemo.minConflicts(matrix);
        }

        System.out.println("Would you like to do the 1000 case test?\n"
        + "[1] Yes\n[2] No");
        int hundChoice = keyboard.nextInt();
        if (hundChoice == 1)
            demo.do1000(algChoice);

    }
    public int[][] readFile(int ROWS, int COLUMNS) throws FileNotFoundException {
        int[][] numArray = new int[ROWS][COLUMNS];
        Scanner sc = new Scanner(choseTextFile());
        while (sc.hasNextLine()) {
            for (int i = 0; i < numArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    numArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return numArray;
    }

    private static File choseTextFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File To Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        File[] file = dialog.getFiles();
        return file[0];
    }
    public int [][] createRandom() { //creates a random board
        int [][] randomBoard = new int [8][8];
        
        for (int i = 0; i < randomBoard.length; i++) { //fill board with 0's
            for (int j = 0; j < randomBoard[0].length; j++) {
                randomBoard[i][j] = 0;
            }
        }
        int row = 0;
        int column = 0;
        int queenCount = 0;
        do {
            row = (int) (Math.random() * 8);
            column = (int) (Math.random() * 8);
            if (randomBoard[row][column] == 0) {
                randomBoard[row][column] = 1;
                queenCount++;
            }
        } while (queenCount < 8);
        
        return randomBoard;
    }
    public void randomQueen(int [][] matrix) { //chooses a random queen to work with for min conflict
        int row = 0;
        int column = 0;
        boolean assignedQueen = false;
        do {
            row = (int) (Math.random() * 8);
            column = (int) (Math.random() * 8);
            if (matrix[row][column] == 1) {
                matrix[row][column] = 1;
                assignedQueen = true;
                setSavedRow(row);
                setSavedColumn(column);
            }
        } while (!assignedQueen);
    }
    public void do1000(boolean alg) { //Does 100 random configurations depending on what algorithm you chose
        long startTime = System.currentTimeMillis(); //recording the time
        int hunIndex = 0;
        int failIndex = 0;
        NQueens hunDemo = new NQueens();
        HillClimbingQueen hillHunDemo = new HillClimbingQueen();
        int [][] randomBoard = new int [8][8];
        int searchCost = 0;
        if(alg == true) { //hill climbing
            while (hunIndex < 1000) {
                randomBoard = hunDemo.createRandom();
                if(hillHunDemo.hillClimbing(randomBoard) == false )
                    failIndex++;
                hunIndex++;
                searchCost += hillHunDemo.getSearchCost();
            }
        }
        else { //min conflicts
            while (hunIndex < 1000) {
                randomBoard = hunDemo.createRandom();
                if(hillHunDemo.minConflicts(randomBoard) == false )
                    failIndex++;
                hunIndex++;
                searchCost += hillHunDemo.getSearchCost();
            }
        }
        System.out.println("Average Search Cost is " + searchCost/1000);
        System.out.println(failIndex + " is number of fails");
        long endtime = System.currentTimeMillis();
        long totalTime = (endtime - startTime) / 1000; //dividing by 100 to get an average time for each test
        System.out.println("Average time for each puzzle is " + totalTime + " (milliseconds)");
    }
}
