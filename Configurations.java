public class Configurations{
    // Create variables
    private char[][] board;
    private int board_size;
    private int lengthToWin;

    // Constructor, intialize the board with a nested array of ' '
    public Configurations(int board_size,int lengthToWin, int max_levels){
        board = new char[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = ' '; 
            }
        }
        // Set the method variables to inputs
        this.lengthToWin = lengthToWin;
        this.board_size = board_size; 
    }
   
    // Private custom function that turns Board into the string configuration key
    private String turnBoardToString(){
        String boardString = "";
        // Loops through every space in the board
        for(int i=0;i < board_size;i++){
            // for every single row in the board
            for(int j=0;j<board_size;j++){
                // for every column in the board
                // add that to boardString
                boardString += board[i][j];
            }
        }
        // return boardString
        return boardString;
    }

    // Function to create an empty dictionary of a size 
    public HashDictionary createDictionary(){
        HashDictionary dictionary = new HashDictionary(6967);
        // returns the dictionary
        return dictionary;
    }

    // Function to check if the board is already in the hashtable
    public int repeatedConfiguration(HashDictionary hashTable){
        // Get the key to the hashTable
        String configKey = turnBoardToString();
        // Getting it from the hashTable will return a score using the get function for HashDictionary class
        // and if not, it will return -1
        return hashTable.get(configKey);
    }

    // Function to add configuration 
    public void addConfiguration(HashDictionary hashDictionary, int score){
        // Turn board to string and stores the config key
        String configKey = turnBoardToString();
        Data dataOfBoard = new Data(configKey,score);
        // Uses hashDictionary class method .put to insert a new Data record
        hashDictionary.put(dataOfBoard);
    }

    // Function to save the play
    public void savePlay(int row, int col, char symbol){
        // Save the symbol into a specific slot
        this.board[row][col] = symbol;
    }

    // Function to check if the square is empty
    public boolean squareIsEmpty(int row, int col){
        // If they are just a space, it is empty
        if(board[row][col]==' '){
            return true;
        }
        // else not empty, false
        return false;
    }

    // Function to check if the given symbol has won the board
    public boolean wins(char symbol){
        int tilesAround = 0;
    
       // Loop through the inner board (excluding the borders), center tile can't be on border
        for(int i=1; i<=board_size-2;i++){
            for(int j=1; j<=board_size-2;j++){
                //If the inner board tile is the symbol
                if(board[i][j]==symbol){
                    // Loop through each side to find the number of tiles in that side around what we persume is the center tile 
                    // Center tile is board[i][j]
    
                    // count variable
                    int k=1;
                 
                    // While loop to loop through a side
                    // left side, col - k 
                    while(board[i][j-k]==symbol){
                        // if the left tile is that symbol
                        if(board[i][j-k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the first one isn't, end the loop
                            break;
                        }
                        // Next check if you can increase the count k, if out of bounds.. break and set k to 1 
                        k++;
                        if((j-k)<0){
                            // If yes
                            break;
                        }
                    } 

                    // reset the count
                    k=1;
                    // Do the same thing but for the right side
                    // Right side, col j + k
                    while(board[i][j+k]==symbol){
                        if(board[i][j+k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile to the right isn't the symbol, end the loop 
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break 
                        k++;
                        if((j+k)>=board_size){
                            break;
                        }
                    } 

                    // top side, repeat
                    // Row i - count k 
                    k=1;
                    while(board[i-k][j]==symbol){
                        if(board[i-k][j]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile above ins't, end loop
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break
                        k++;
                        if((i-k)<0){
                            break;
                        }
                    } 
                    
                    //reset count
                    k=1;
                    // bottom side , repeat
                    // Row i +  count k to go down
                    while(board[i+k][j]==symbol){
                        if(board[i+k][j]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile isn't symbol end the loop 
                            k=1;
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break 
                        k++;
                        if((i+k)>=board_size){
                            break;
                        }
                    } 
                    
                    // Two conditions to win, tilesAround must be bigger than your length -1, exclude center tile in calculation
                    // And that there must be a cross to win..
                    // if a cross is made, then check if the tiles around is bigger, or equal to the length
                    if(board[i][j-1] ==symbol & board[i][j+1]==symbol){
                        if(board[i-1][j]==symbol & board[i+1][j]==symbol)
                        // if a cross is made 
                        {
                            // return true if tiles around is accurate
                            if(tilesAround >=lengthToWin-1){
                                return true;
                            }
                        }
                    }
                    // reset tiles around
                    tilesAround = 0;
    

                    // Repeat entire process for diagonal star


                    // reset k 
                    k=1;
                    // check top left corner
                    // Decrease row, adn decrease column 
                    while(board[i-k][j-k]==symbol){
                        // bottom side 
                        if(board[i-k][j-k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile isn't symbol, end the loop 
                            break;
                        }
              
                        // Next check if you can increase the loop, if either i or k is out of bounds.. break
                        k++;
                        if((j-k)<0 || (i-k)<0){
                            break;
                        }
                    } 
                 
                    // reset count
                    k=1;

                    // check top right
                    // repeat process
                    // decrease row, adn increase column 
                    while(board[i-k][j+k]==symbol){
                        if(board[i-k][j+k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile isn't symbol end the loop 
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break 
                        k++;
                        if((j+k)>=board_size || (i-k)<0){
                            break;
                        }
                    }
                    
                    k=1;

                    // check bottom right
                    // Repeat
                    // increase row, and increase column
                    while(board[i+k][j+k]==symbol){
                        if(board[i+k][j+k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if isnt symbol, end the loop 
                           
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break 
                        k++;
                        if((j+k)>=board_size || (i+k)>=board_size){
                            // If yes
                       
                            break;
                        }
                    } 
                     
                    // check bottom left
                    // increase row, decrease column 
                    k=1;
                    while(board[i+k][j-k]==symbol){
                        if(board[i+k][j-k]==symbol){
                            // Add to the length
                            tilesAround++;
                        }else{
                            // if the tile isnt symbol, end the loop 
                            break;
                        }
                        // Next check if you can increase the loop, if out of bounds.. break and set k to 1 
                        k++;
                        if((j-k)<0 ||(i+k)>=board_size){
                            break;
                        }
                    } 
  

                    //Check for the diagonal base case (star) using same methodology as before
                    if(board[i-1][j-1]==symbol & board[i-1][j+1] ==symbol){
                        // if the one to the left or the one to the right is equal to the symbol
                        // check if the  bottom 2 diagonal squares are the same as well
                        if(board[i+1][j-1]== symbol & board[i+1][j+1]==symbol){
                            // if - center tiles, tiles around is equal or greater than legnth to win
                            // return true
                            if(tilesAround >=lengthToWin-1){
                                return true;
                            }
                        }
                    }
                    tilesAround =0;
                }
            }
        }
        // if not returned true for every inner space, then return false
        return false;
    }


    // Checks if there is a draw! 
    public boolean isDraw(){
        // Iterate through every tile 
        for(int i=0; i<board_size;i++){
            for (int j=0; j<board_size;j++){
                // IF even one square is empty, that means it is a draw
                if(squareIsEmpty(i,j)){
                    //if it is true that the square is empty even once
                    return false;
               }
            }
        }
        // If none is empty, it is a draw
        return true;
    }
    
    // Function that returns the final score of the board
    public int evalBoard(){
        if(wins('O')==true){
            // If computer wins
            return 3;
        } if(wins('X')==true){
            // if Human wins
            return 0;
        } if(isDraw()){
            // If draw
            return 2;
        }else{
            // If Turn continues
            return 1;
        }
    }
}
