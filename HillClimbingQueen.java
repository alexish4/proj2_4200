public class HillClimbingQueen {
    private int pairsAttacking = 0;
    private int savedH = 0;
    NQueens getValues = new NQueens();
    int searchCost = 0;

    public int countPairs(int [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    //count horizontally
                    int countHorizontal = j;
                    
                    if (j > 0) //if on the edge
                        countHorizontal = j - 1;

                    for (countHorizontal = j; countHorizontal >= 0; countHorizontal--) {
                        if(matrix[i][countHorizontal] == 1 && countHorizontal != j) {
                            pairsAttacking++;
                        }
                    }
                    countHorizontal = j;
                    if(j < matrix.length - 1) //if on the edge
                        countHorizontal = j + 1;

                    for (countHorizontal = j; countHorizontal < matrix.length; countHorizontal++) {
                        if(matrix[i][countHorizontal] == 1 && countHorizontal != j) {
                            pairsAttacking++;
                        }
                    }
                    
                    //count vertically
                    int countVertical = i;
                    if (i > 0) //if on the edge
                        countVertical = i - 1;

                    for (; countVertical >= 0; countVertical--) {
                        if(matrix[countVertical][j] == 1 && countVertical != i) {
                            pairsAttacking++;
                        }
                    }
                    if (i < matrix.length - 1)
                        countVertical = i + 1;
                        
                    for (countVertical = i; countVertical < matrix.length; countVertical++) {
                        if(matrix[countVertical][j] == 1 && countVertical != i) {
                            pairsAttacking++;
                        }
                    }

                    //count diagonally northwest
                    int diagonalIndex = 0; //we stop when we hit either the side edge or top edge
                    countHorizontal = j;
                    countVertical = i;
                    if (i < j) {
                        diagonalIndex = i;
                        if (i > 0)
                            countVertical = i - 1;
                        if (j > 0)
                            countHorizontal = j - 1;
                        diagonalIndex = countVertical;
                    }
                    else if (i == j) {
                        diagonalIndex = i;
                        if (i > 0) {
                            countVertical = i - 1;
                            countHorizontal = j - 1;
                        }
                        diagonalIndex = countVertical;
                    }
                    else {
                        diagonalIndex = j;
                        if (j > 0)
                            countHorizontal = j - 1;
                        if (i > 0)
                            countVertical = i - 1;
                        diagonalIndex = countHorizontal;
                    }

                    for (; diagonalIndex >= 0; diagonalIndex--) {
                        if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                            pairsAttacking++;
                        }
                        countHorizontal--;
                        countVertical--;
                    }

                    //count diagonally southeast
                    diagonalIndex = 0; //we stop when we hit either the side edge or top edge
                    countHorizontal = j;
                    countVertical = i;
                    if (i > j) {
                        diagonalIndex = i;
                        if (i < matrix.length - 1) {
                            countVertical = i + 1;
                            countHorizontal = j + 1;
                        }
                        diagonalIndex = countVertical;
                    }
                    else if (i == j) {
                        diagonalIndex = i;
                        if (i < matrix.length - 1) {
                            countVertical = i + 1;
                            countHorizontal = j + 1;
                        }
                        diagonalIndex = countVertical;
                    }
                    else {
                        diagonalIndex = j;
                        if (j < matrix.length - 1) {
                            countHorizontal = j + 1;
                            countVertical = i + 1;
                        }
                        diagonalIndex = countHorizontal;
                    }
                    if (diagonalIndex == matrix.length)
                        diagonalIndex--;
                    for (; diagonalIndex < matrix.length; diagonalIndex++) {
                        if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                            pairsAttacking++;
                        }
                        countHorizontal++;
                        countVertical++;
                    }

                    //count diagonally Northeast
                    diagonalIndex = 0; //we stop when we hit either the side edge or top edge
                    countHorizontal = j; //horizontal increments vertical decrements
                    countVertical = i;
                    int testHorizontal = 7 - j; //j itself acts as testVertical since comparing with 0
                    if (testHorizontal < i) { //stop when we hit horizontal
                        diagonalIndex = j;
                        if (j < matrix.length - 1) {
                            countHorizontal = j + 1;
                            if(i > 0)
                                countVertical = i - 1;
                        }
                        diagonalIndex = countHorizontal;
                        if (diagonalIndex == matrix.length)
                            diagonalIndex--;
                        for (; diagonalIndex < matrix.length; diagonalIndex++) {
                            if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                                pairsAttacking++;
                            }
                            countHorizontal++;
                            countVertical--;
                        }      
                    }
                    else { //stop when we hit vertical
                        diagonalIndex = i;
                        if (i > 0) {
                            countVertical = i - 1;
                            if (j < matrix.length - 1)
                                countHorizontal = j + 1;
                        }
                        diagonalIndex = countVertical;

                        for (; diagonalIndex >= 0; diagonalIndex--) {
                            if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                                pairsAttacking++;
                            }
                            countHorizontal++;
                            countVertical--;
                        }      
                    }    
                    
                    //count diagonally SouthWest
                    diagonalIndex = 0; //we stop when we hit either the side edge or top edge
                    countHorizontal = j; //horizontal decrements vertical increments
                    countVertical = i;
                    int testVertical = 7 - i;
                    if (testVertical > j) { //stop when we hit horizontal
                        diagonalIndex = j;
                        if (j > 0) {
                            countHorizontal = j - 1;
                            if (i < matrix.length - 1)
                                countVertical = i + 1;
                        }
                        diagonalIndex = countHorizontal;

                        for (; diagonalIndex >= 0; diagonalIndex--) {
                            if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                                pairsAttacking++;
                            }
                            countHorizontal--;
                            countVertical++;
                        }      
                    }
                    else { //stop when we hit vertical
                        diagonalIndex = i;
                        if (i < matrix.length - 1) {
                            countVertical = i + 1;
                            if (j > 0)
                                countHorizontal = j - 1;
                        }
                        diagonalIndex = countVertical;
                        if (diagonalIndex == matrix.length)
                            diagonalIndex--;
                        for (; diagonalIndex < matrix.length; diagonalIndex++) {
                            if (matrix[countVertical][countHorizontal] == 1 && i != countVertical && j != countHorizontal) {
                                pairsAttacking++;
                            }
                            countHorizontal--;
                            countVertical++;
                        }      
                    }    
                }
            }
        }
        return pairsAttacking / 2; //don't count duplicates
    }
    public int [][] addEdges(int [][] matrix) { //finding the best configuration
        int [][] savedConfiguration = new int [8][8];
        int [][] compareConfiguration = new int [8][8];
        compareConfiguration = matrix.clone();
        pairsAttacking = 0;
        countPairs(matrix);
        savedH = pairsAttacking; //initialized to amount of pairs attacking
        pairsAttacking = 0;
        
        int compareCost = 0; //comparing to see if good configuration or not 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int row = i;
                int column = j;
                if(matrix[i][j] == 1 && savedH != 0) {
                    //moving Northwest
                    while(row >= 0 && column >= 0){
                        if(row != i && column != j && compareConfiguration[row][column] != 1){
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        row--;
                        column--;
                    }
                    //moving west
                    row = i;
                    column = j;
                    while (column >= 0){
                        if(column != j && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        column--;
                    }

                    //moving east
                    column = j;
                    while (column < matrix.length) {
                        if(column != j && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        column++;
                    }
                    
                    //moving southWest
                    column = j;
                    while(column >= 0 && row < matrix.length) {
                        if(column != j && row != i && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        column--;
                        row++;
                    }

                    //moving South
                    column = j;
                    row = i;
                    while (row < matrix.length) {
                        if(row != i && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        row++;
                    }

                    //moving North
                    row = i;
                    while (row >= 0) {
                        if(row != i && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        row--;
                    }
                    
                    //moving NorthEast
                    row = i;
                    while (row >= 0 && column < matrix.length) {
                        if(row != i && column != j && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        row--;
                        column++;
                    }

                    //moving SouthEast
                    row = i;
                    column = j;
                    while (row < matrix.length && column < matrix.length) {
                        if(row != i && column != j && compareConfiguration[row][column] != 1) {
                            compareConfiguration[row][column] = 1;
                            compareConfiguration[i][j] = 0;
                            compareCost = countPairs(compareConfiguration);
                            pairsAttacking = 0;
                            if (compareCost < savedH) {
                                for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                                    for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                        savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                                    }
                                }
                                savedH = compareCost;
                            }

                            compareConfiguration[row][column] = 0;
                            compareConfiguration[i][j] = 1;

                        }
                        row++;
                        column++;
                    }
                }
            }
        } //end of looping for this method

        return savedConfiguration;
    }
    public boolean hillClimbing(int [][] matrix) {
        int [][] compareMatrix = new int [8][8];
        boolean solved = true;
        for (int i = 0; i < compareMatrix.length; i++) { //copying matrix
            for (int j = 0; j < compareMatrix.length; j++) {
                compareMatrix[i][j] = matrix[i][j];
            }
        }
        int compare = 0;
        searchCost = 0;

        do { //hill climbing
            compare = countPairs(compareMatrix);
            pairsAttacking = 0;
            compareMatrix = addEdges(compareMatrix); //saved h gets updated
            searchCost++;
        } while(savedH < compare && savedH != 0);
        if (savedH != 0)
            solved = false;
        // System.out.println("compare is " + compare);
        // System.out.println("Saved h is " + savedH);
        System.out.println("Solve status is " + solved);
        System.out.println("Search Cost is " + searchCost);
        setSearchCost(searchCost);
        for (int i = 0; i < compareMatrix.length; i++) {
            for (int j = 0; j < compareMatrix.length; j++) {
                System.out.print(compareMatrix[i][j] + " ");
            }
            System.out.println();
        }
        return solved;
    }
    public int [][] addEdgesMinConflict(int [][] matrix) { //some light tweaking from addEdges method for min conflicts
        int [][] savedConfiguration = new int [8][8];
        int [][] compareConfiguration = new int [8][8];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                compareConfiguration [i][j] = matrix [i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                savedConfiguration [i][j] = matrix [i][j];
            }
        }
        pairsAttacking = 0;
        countPairs(matrix);
        savedH = pairsAttacking; //initialized to amount of pairs attacking
        pairsAttacking = 0;

        int savedRow = getValues.getSavedRow();
        int savedColumn = getValues.getSavedColumn();
        int compareCost = 0;
        int row = savedRow;
        int column = savedColumn;

        if(savedH != 0 && matrix[savedRow][savedColumn] == 1) {
            //moving Northwest
            while(row >= 0 && column >= 0){
                if(row != savedRow && column != savedColumn && compareConfiguration[row][column] != 1){
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }

                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;
                }
                row--;
                column--;
            }
            //moving west
            row = savedRow;
            column = savedColumn;
            while (column >= 0){
                if(column != savedColumn && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                column--;
            }

            //moving east
            column = savedColumn;
            while (column < matrix.length) {
                if(column != savedColumn && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                column++;
            }
            
            //moving southWest
            column = savedColumn;
            while(column >= 0 && row < matrix.length) {
                if(column != savedColumn && row != savedRow&& compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                column--;
                row++;
            }

            //moving South
            column = savedColumn;
            row = savedRow;
            while (row < matrix.length) {
                if(row != savedRow && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                row++;
            }

            //moving North
            row = savedRow;
            while (row >= 0) {
                if(row != savedRow && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                row--;
            }
            
            //moving NorthEast
            row = savedRow;
            while (row >= 0 && column < matrix.length) {
                if(row != savedRow && column != savedColumn && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                row--;
                column++;
            }

            //moving SouthEast
            row = savedRow;
            column = savedColumn;
            while (row < matrix.length && column < matrix.length) {
                if(row != savedRow && column != savedColumn && compareConfiguration[row][column] != 1) {
                    compareConfiguration[row][column] = 1;
                    compareConfiguration[savedRow][savedColumn] = 0;
                    compareCost = countPairs(compareConfiguration);
                    pairsAttacking = 0;
                    if (compareCost <= savedH) {
                        for (int cRow = 0; cRow < matrix.length; cRow++) { //saving the array
                            for (int cColumn = 0; cColumn < matrix[0].length; cColumn++) {
                                savedConfiguration[cRow][cColumn] = compareConfiguration[cRow][cColumn];
                            }
                        }
                        savedH = compareCost;
                    }


                    compareConfiguration[row][column] = 0;
                    compareConfiguration[savedRow][savedColumn] = 1;

                }
                row++;
                column++;
            }
        }

        return savedConfiguration;
    }
    public boolean minConflicts(int [][] matrix) {
        int [][] compareMatrix = new int [8][8];
        boolean solved = true;
        for (int i = 0; i < compareMatrix.length; i++) { //copying matrix
            for (int j = 0; j < compareMatrix.length; j++) {
                compareMatrix[i][j] = matrix[i][j];
            }
        }
        int compare = 0;
        int maxSteps = 0;

        do { //get random queen and get min conflict
            getValues.randomQueen(compareMatrix); //get a random queen and save their location
            compare = countPairs(compareMatrix);
            pairsAttacking = 0;
            compareMatrix = addEdgesMinConflict(compareMatrix); //saved h gets updated
            // System.out.println("Saved h is " + savedH);
            // System.out.println("Count is " + maxSteps);
            maxSteps++;
        } while(savedH != 0 && maxSteps < 2000); //max steps is 2000
        if(maxSteps >= 2000)
            solved = false;
        System.out.println("Solved is " + solved);
        System.out.println("Search Cost is " + maxSteps);
        setSearchCost(maxSteps);
        for (int i = 0; i < compareMatrix.length; i++) {
            for (int j = 0; j < compareMatrix.length; j++) {
                System.out.print(compareMatrix[i][j] + " ");
            }
            System.out.println();
        }
        return solved;
    }
    public void setSearchCost(int searchCost) {
        this.searchCost = searchCost;
    }
    public int getSearchCost() {
        return searchCost;
    }
}