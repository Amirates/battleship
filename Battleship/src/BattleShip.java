import java.util.Random;
import java.util.Scanner;

public class BattleShip {

	/** Initially the variables are created 'board[7][7]' which will store store the game board.
	 * The variable 'ships[5][2]' store the position (row and column) of the 5 hidden ships on the board.
	 * The variable 'shoot[2]' will store the position (row and column) of the shot that the player will take each round.
	 * The variable 'attempts' store the number of attempts the player has to hit the 5 ships.
	 * Finally the variable 'shotHit'counts the number of ships the player hit.
	 */
	
    public static void main(String[] args) {
        int[][] board = new int[7][7];
        int[][] ships = new int[5][2];
        int[] shoot = new int[2];
        int attempts=0,
            shotHit=0;
        
        //method initBoard is triggered to create the board with the number '-1' in all positions
        initBoard(board);
        //method initShips is triggered to fill the position of the 5 ships (row and column)
        initShips(ships);
        
        System.out.println();
        
        //the game begins using a do...while loop, game goes on until the player hits the 5 ships
        do{
            showBoard(board);
            shoot(shoot);
            attempts++;
            
            if(hit(shoot,ships)){
                hint(shoot,ships,attempts);
                shotHit++;
            }                
            else
                hint(shoot,ships,attempts);
            
            changeboard(shoot,ships,board);
            
        //condition of the loop is "shotHit!=5'
        }while(shotHit!=5);
        
        System.out.println("\n\n\nWell done soldier! You've destroyed 5 enemy ships in "+attempts+" attempts");
        showBoard(board);
    }
    //sets the value -1 in all blocks of the board
    public static void initBoard(int[][] board){
        for(int row=0 ; row < 7 ; row++ )
            for(int column=0 ; column < 7 ; column++ )
                board[row][column]=-1;
    }
    //gets the int matrix and shows the board game
    public static void showBoard(int[][] board){
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7");
        System.out.println();
        
        for(int row=0 ; row < 7 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 7 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");
                }
                
            }
            System.out.println();
        }

    }

    //this method randomly select 5 pairs of integers numbers, which are the location of the 5 ships
    public static void initShips(int[][] ships){
        Random random = new Random();
        
        for(int ship=0 ; ship < 5 ; ship++){
            ships[ship][0]=random.nextInt(7);
            ships[ship][1]=random.nextInt(7);
            
            //let's check if that shot was already tried 
            //if it was, just finish the do...while when a new pair was randomly selected
            for(int last=0 ; last < ship ; last++){
                if( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do{
                        ships[ship][0]=random.nextInt(7);
                        ships[ship][1]=random.nextInt(7);
                    }while( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }
            
        }
    }
    
    //gets a shot (row and column) of the user, and stores in variable shot []
    public static void shoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Row: ");
        shoot[0] = input.nextInt();
        shoot[0]--;
        
        System.out.print("Column: ");
        shoot[1] = input.nextInt();
        shoot[1]--;
        
    }
    
    //checks if given shot hit a ship
    public static boolean hit(int[] shoot, int[][] ships){
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.printf("What a SOLDIER! You hit an enemy ship located in (%d,%d) with a hellstorm missle\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }

    //give a hint of how many ships are in that row and that column where the shot was given
    public static void hint(int[] shoot, int[][] ships, int attempt){
        int row=0,
            column=0;
        
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }
        
        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    //after the shot is given, the board is changed, showing that the shot was give (if hit or missed)
    public static void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
}