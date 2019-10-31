// Nguyen Tien Vuong
//
// DYUQFJ
//
// Assignment2
//
// 2018/11/21 08:07:14
//
// This solution was submitted and prepared by Nguyen Tien Vuong, DYUQFJ for the
// Assignment2 assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

package tictactoe;

import java.util.Random;
import java.util.ArrayList;

public class Model {

    private int size;

    private Player actualPlayer;
    
    private Player[][] table;
    
    private ArrayList<PlayerXO> playerX;
    
    private ArrayList<PlayerXO> playerO;

    private ArrayList<PlayerXO> playerToDelete;
    
    public Model(int size) {
        this.size = size;
        actualPlayer = Player.X;
        //previousPlayer = Player.O;
        playerX = new ArrayList<>();
        playerO = new ArrayList<>();
        playerToDelete = new ArrayList<>();

        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }

    public Player step(int row, int column) {
        PlayerXO player = new PlayerXO(row, column);
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }

        table[row][column] = actualPlayer;

        if (actualPlayer == Player.X) {
            player.setRow(row);
            player.setColumn(column);
            playerX.add(player);
            for (int i = 0; i < playerX.size(); i ++){
                System.out.print(playerX.get(i).getRow() + " ");
                System.out.println(playerX.get(i).getColumn());
            }
            actualPlayer = Player.O;
        } else {
            player.setRow(row);
            player.setColumn(column);
            playerO.add(player);
            for (int i = 0; i < playerO.size(); i ++){
                System.out.print(playerO.get(i).getRow() + " ");
                System.out.println(playerO.get(i).getColumn());
            }
            actualPlayer = Player.X;
        }

        return table[row][column];
    }

    public Player findWinner() {
        int lengthToWin = 5;
        
        for (int i = 0; i < size - lengthToWin + 1; i++) {
            int m = i + lengthToWin - 1;
            for (int j = 0; j < size - lengthToWin + 1; j++) {
                int n = j + lengthToWin - 1; 
                
                //check row
                nextRow: for (int row = i; row <= m; row++){
                    if (table[row][j] == Player.NOBODY){
                        continue;
                    }
                    for (int col = j; col <= n; col++){
                        if (table[row][col] != table[row][j]){
                            continue nextRow;
                        }
                    }
                    return table[row][j];
                }
                
                //check column
                nextCol: for (int col = j; col <= n; col++){
                    if (table[i][col] == Player.NOBODY){
                        continue;
                    }
                    for (int row = i; row <= m; row++){
                        if (table[row][col] != table[i][col]){
                            continue nextCol;
                        }
                    }
                    return table[i][col];
                }
                
                //check top-left to bottom right diagonal
                diag1:if (table[i][j] != Player.NOBODY){
                    for (int p = 1; p < lengthToWin; p++){
                        if (table[i+p][j+p] != table[i][j]){
                            break diag1;
                        }
                    }
                    return table[i][j];
                }
                
                //check top-right to bottom-left diagonal
                diag2: if (table[i][n] != Player.NOBODY){
                    for (int p = 1; p < lengthToWin; p++){
                        if (table[i+p][n-p] != table[i][n]){
                            break diag2;
                        }
                    }
                    return table[i][n];
                }
            }           
        }
        return Player.NOBODY;
    }
    
    //check for full Board
    public int findDraw(){
        boolean isFull;
        int cnt = 0;
        full: 
        for (int row = 0; row < size; row ++){
            for (int col = 0; col < size; col++){
                if (table[row][col] == Player.NOBODY){
                    isFull = false;
                    break full;
                }
                else{
                    cnt++;
                }
            } 
        }
        return cnt;
    }
    
    public ArrayList<PlayerXO> playTrick(int i, int j){
        Random rand = new Random();
        playerToDelete = new ArrayList<>();
        
        //check row j(right, left)
        int jr = j;
        while (jr + 1 < size && table[i][j] == table[i][jr+1]){
            jr++;
        }
        int jl = j;
        while (jl - 1 >= 0 && table[i][j] == table[i][jl-1]){
            jl--;
        }
        if (jr - jl + 1 == 3){
            if (table[i][j] ==  Player.X){
                int r = rand.nextInt(playerX.size());
                PlayerXO tmp = new PlayerXO(playerX.get(r).getRow(), playerX.get(r).getColumn());
                table[playerX.get(r).getRow()][playerX.get(r).getColumn()] = Player.NOBODY;
                playerX.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
            else if (table[i][j] == Player.O){
                int r = rand.nextInt(playerO.size());
                PlayerXO tmp = new PlayerXO(playerO.get(r).getRow(), playerO.get(r).getColumn());
                table[playerO.get(r).getRow()][playerO.get(r).getColumn()] = Player.NOBODY;
                playerO.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
        }
        if (jr - jl + 1 == 4){
            if (table[i][j] ==  Player.X){
                int r1 = rand.nextInt(playerX.size());
                PlayerXO tmp1 = new PlayerXO(playerX.get(r1).getRow(), playerX.get(r1).getColumn());
                table[playerX.get(r1).getRow()][playerX.get(r1).getColumn()] = Player.NOBODY;
                playerX.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerX.size());
                PlayerXO tmp2 = new PlayerXO(playerX.get(r2).getRow(), playerX.get(r2).getColumn());
                table[playerX.get(r2).getRow()][playerX.get(r2).getColumn()] = Player.NOBODY;
                playerX.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;    
            }
            else if (table[i][j] == Player.O){
                int r1 = rand.nextInt(playerO.size());
                PlayerXO tmp1 = new PlayerXO(playerO.get(r1).getRow(), playerO.get(r1).getColumn());
                table[playerO.get(r1).getRow()][playerO.get(r1).getColumn()] = Player.NOBODY;
                playerO.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerO.size());
                PlayerXO tmp2 = new PlayerXO(playerO.get(r2).getRow(), playerO.get(r2).getColumn());
                table[playerO.get(r2).getRow()][playerO.get(r2).getColumn()] = Player.NOBODY;
                playerO.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;    
            }
        }
        
        
        //check column i(down, up)
        int id = i;
        while (id + 1 < size && table[i][j] == table[id+1][j]){
            id++;
        }
        int iu = i;
        while (iu - 1 >= 0 && table[i][j] == table[iu-1][j]){
            iu--;
        }
        if (id - iu + 1 == 3){
            if (table[i][j] ==  Player.X){
                int r = rand.nextInt(playerX.size());
                PlayerXO tmp = new PlayerXO(playerX.get(r).getRow(), playerX.get(r).getColumn());
                table[playerX.get(r).getRow()][playerX.get(r).getColumn()] = Player.NOBODY;
                playerX.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
            else if (table[i][j] == Player.O){
                int r = rand.nextInt(playerO.size());
                PlayerXO tmp = new PlayerXO(playerO.get(r).getRow(), playerO.get(r).getColumn());
                table[playerO.get(r).getRow()][playerO.get(r).getColumn()] = Player.NOBODY;
                playerO.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
        }
        if (id - iu + 1 == 4){
            if (table[i][j] ==  Player.X){
                int r1 = rand.nextInt(playerX.size());
                PlayerXO tmp1 = new PlayerXO(playerX.get(r1).getRow(), playerX.get(r1).getColumn());
                table[playerX.get(r1).getRow()][playerX.get(r1).getColumn()] = Player.NOBODY;
                playerX.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerX.size());
                PlayerXO tmp2 = new PlayerXO(playerX.get(r2).getRow(), playerX.get(r2).getColumn());
                table[playerX.get(r2).getRow()][playerX.get(r2).getColumn()] = Player.NOBODY;
                playerX.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;   
            }
            else if (table[i][j] == Player.O){
                int r1 = rand.nextInt(playerO.size());
                PlayerXO tmp1 = new PlayerXO(playerO.get(r1).getRow(), playerO.get(r1).getColumn());
                table[playerO.get(r1).getRow()][playerO.get(r1).getColumn()] = Player.NOBODY;
                playerO.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerO.size());
                PlayerXO tmp2 = new PlayerXO(playerO.get(r2).getRow(), playerO.get(r2).getColumn());
                table[playerO.get(r2).getRow()][playerO.get(r2).getColumn()] = Player.NOBODY;
                playerO.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;   
            }
        }
        
        //check top-left to bottom-right diagonal d(left, right)
        int dlr1 = i;
        int dlr2 = j;
        while((dlr1 + 1 < size && dlr2 + 1 < size) && table[i][j] == table[dlr1 + 1][dlr2 + 1]){
            dlr1++;
            dlr2++;
        }
        int dlr3 = i;
        int dlr4 = j;
        while((dlr3 - 1 >= 0 && dlr4 - 1 >= 0) && table[i][j] == table[dlr3-1][dlr4-1]){
            dlr3--;
            dlr4--;
        }
        if (dlr1 - dlr3 + 1 == 3){
            if (table[i][j] ==  Player.X){
                int r = rand.nextInt(playerX.size());
                PlayerXO tmp = new PlayerXO(playerX.get(r).getRow(), playerX.get(r).getColumn());
                table[playerX.get(r).getRow()][playerX.get(r).getColumn()] = Player.NOBODY;
                playerX.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
            else if (table[i][j] == Player.O){
                int r = rand.nextInt(playerO.size());
                PlayerXO tmp = new PlayerXO(playerO.get(r).getRow(), playerO.get(r).getColumn());
                table[playerO.get(r).getRow()][playerO.get(r).getColumn()] = Player.NOBODY;
                playerO.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
        }
        if (dlr1 - dlr3 + 1 == 4){
            if (table[i][j] ==  Player.X){
                int r1 = rand.nextInt(playerX.size());
                PlayerXO tmp1 = new PlayerXO(playerX.get(r1).getRow(), playerX.get(r1).getColumn());
                table[playerX.get(r1).getRow()][playerX.get(r1).getColumn()] = Player.NOBODY;
                playerX.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerX.size());
                PlayerXO tmp2 = new PlayerXO(playerX.get(r2).getRow(), playerX.get(r2).getColumn());
                table[playerX.get(r2).getRow()][playerX.get(r2).getColumn()] = Player.NOBODY;
                playerX.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;   
            }
            else if (table[i][j] == Player.O){
                int r1 = rand.nextInt(playerO.size());
                PlayerXO tmp1 = new PlayerXO(playerO.get(r1).getRow(), playerO.get(r1).getColumn());
                table[playerO.get(r1).getRow()][playerO.get(r1).getColumn()] = Player.NOBODY;
                playerO.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerO.size());
                PlayerXO tmp2 = new PlayerXO(playerO.get(r2).getRow(), playerO.get(r2).getColumn());
                table[playerO.get(r2).getRow()][playerO.get(r2).getColumn()] = Player.NOBODY;
                playerO.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;    
            }
        }
        
        //check top-right to bottom-left diagonal d(left, right)
        int drl1 = i;
        int drl2 = j;
        while((drl1 + 1 < size && drl2 - 1 >= 0) && table[i][j] == table[drl1+1][drl2-1]){
            drl1++;
            drl2--;
        }
        int drl3 = i;
        int drl4 = j;
        while((drl3 - 1 >= 0 && drl4 + 1 <  size) && table[i][j] == table[drl3-1][drl4+1]){
            drl3--;
            drl4++;
        }
        if (drl1 - drl3 + 1 == 3){
            if (table[i][j] ==  Player.X){
                int r = rand.nextInt(playerX.size());
                PlayerXO tmp = new PlayerXO(playerX.get(r).getRow(), playerX.get(r).getColumn());
                table[playerX.get(r).getRow()][playerX.get(r).getColumn()] = Player.NOBODY;
                playerX.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
            else if (table[i][j] == Player.O){
                int r = rand.nextInt(playerO.size());
                PlayerXO tmp = new PlayerXO(playerO.get(r).getRow(), playerO.get(r).getColumn());
                table[playerO.get(r).getRow()][playerO.get(r).getColumn()] = Player.NOBODY;
                playerO.remove(r);
                playerToDelete.add(tmp);
                return playerToDelete;
            }
        }
        if (drl1 - drl3 + 1 == 4){
            if (table[i][j] ==  Player.X){
                int r1 = rand.nextInt(playerX.size());
                PlayerXO tmp1 = new PlayerXO(playerX.get(r1).getRow(), playerX.get(r1).getColumn());
                table[playerX.get(r1).getRow()][playerX.get(r1).getColumn()] = Player.NOBODY;
                playerX.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerX.size());
                PlayerXO tmp2 = new PlayerXO(playerX.get(r2).getRow(), playerX.get(r2).getColumn());
                table[playerX.get(r2).getRow()][playerX.get(r2).getColumn()] = Player.NOBODY;
                playerX.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;   
            }
            else if (table[i][j] == Player.O){
                int r1 = rand.nextInt(playerO.size());
                PlayerXO tmp1 = new PlayerXO(playerO.get(r1).getRow(), playerO.get(r1).getColumn());
                table[playerO.get(r1).getRow()][playerO.get(r1).getColumn()] = Player.NOBODY;
                playerO.remove(r1);
                playerToDelete.add(tmp1);
                int r2 = rand.nextInt(playerO.size());
                PlayerXO tmp2 = new PlayerXO(playerO.get(r2).getRow(), playerO.get(r2).getColumn());
                table[playerO.get(r2).getRow()][playerO.get(r2).getColumn()] = Player.NOBODY;
                playerO.remove(r2);
                playerToDelete.add(tmp2);
                return playerToDelete;   
            }
        }
                
                
        return playerToDelete;
    }

    public Player getActualPlayer() {
        return actualPlayer;
    }
}
