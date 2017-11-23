public class Solver {
    private int[][] sudoku;
    private boolean[][] isInput;
    
    
    public Solver(int[][] board) {
        sudoku = board;
        for(int i =0 ; i < 9; i++ ){
        	for (int j= 0; j < 9; i++ ){
        		if(board[i][j] != 0){
        			isInput[i][j] = true;
        		}
        	}
        }
    }
    private 
    public boolean solve(){
    	if(){
    		
    	}
    }
    
    
    
    
    
    /*/
     * Checks all rules for a spot in a sudoku.
     */
    public boolean checkRules(int rad, int kolum){
        return checkColumn(rad, kolum) && checkRow(rad, kolum) && checkBox(rad, kolum);
    }
    
    public boolean checkRow(int rad, int kolum){
        for (int i = 0; i < 9 ; i++){
            if(rad != i){
                if(sudoku[rad][kolum] == sudoku[i][kolum] ){
                    return false;
                }                
            }
        }
            return true;
    }
    
    public boolean checkColumn(int rad, int kolum){
        for (int i = 0; i < 9 ; i++){
            if(kolum != i){
                if(sudoku[rad][kolum] == sudoku[rad][i]){
                    return false;
                }                
            }
        }
        return true;
    }
    
    public boolean checkBox(int rad, int kolum){
        //tex    rad = 1 > i börjar på (0/3)*3= 0
        //        rad = 4 > i börjar på (3/3)*3= 3 
        //         rad = 9 > i börjar på (8/3)*3= 6 
        for (int i = 3*(rad/3); i  <= 3*(rad/3)+2; i++){
            for (int j = 3*(kolum/3); j <= 3*(kolum/3); j ++){
                if(rad != i && kolum != j){
                    if(sudoku[i][j] == sudoku[rad][kolum]){
                        return false;
                    }                    
                }
            }
        }
        return true;
    }

}
