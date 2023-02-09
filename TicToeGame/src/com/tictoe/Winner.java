
package com.tictoe;

public class Winner {
    
     public boolean whoWon(char grid[][]) {
        
        for(int i=0;i<3;i++) {
            int vptraceX = 0, vptraceO = 0;
            int hptraceX = 0, hptraceO = 0;
            for(int j=0;j<3;j++) {
                if(grid[i][j] == 'X') {
                    vptraceX++;
                } else if(grid[i][j] == 'O') {
                    vptraceO++;
                }
                
                if(grid[j][i] == 'X') {
                    hptraceX++;
                } else if(grid[j][i] == 'O') {
                    hptraceO++;
                }
            }
            if(vptraceX == 3 || hptraceX == 3) {
                Console.winner = 'X';
                return true;
            } 
            if(vptraceO == 3 || hptraceO == 3){
                Console.winner = 'O';
                return true;
            }
        }
        
        if(grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X'){
            Console.winner = 'X';
            return true;
        }
        else if(grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O'){
            Console.winner = 'O';
            return true;
        }
        
        if(grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X'){
            Console.winner = 'X';
            return true;
        }
        else if(grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            Console.winner = 'O';
            return true;
        }
        
        return false;
    }
}
