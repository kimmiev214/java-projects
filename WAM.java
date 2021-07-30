import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WAM {

    //fields

    private int score;
    private int molesLeft;
    private int attemptsLeft;

    char moleGrid[][];

    // Constructor
    WAM(int numAttempts, int gridDimension) {
        attemptsLeft = numAttempts;
        moleGrid = new char[gridDimension][gridDimension];

        for (char[] row : moleGrid)
            Arrays.fill(row, '*');

    }

    public static void main (String[] args) {

        WAM game = new WAM(50, 10);

        Random random = new Random();

        while(game.molesLeft < 10) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            game.place(x,y);

            // System.out.println("Placed: " + x + "," + y); //cheat code

        }

        while (game.attemptsLeft > 0 && game.molesLeft > 0) {
            Scanner in = new Scanner(System.in);

            System.out.print("Enter the coordinates of where you would like to take a whack. \nYou have " + game.attemptsLeft + " attempts left.");
            System.out.print("\nInsert coordinates \"-1,-1\" to end the game at any time." );
            System.out.print("\n x coordinate:  \n");
            int x = in.nextInt();
            System.out.print("\n y coordinate:  \n");
            int y = in.nextInt();

            game.whack(x,y);
        }


        game.printGridToUser();
    }

    boolean place(int x, int y) {
        if (moleGrid[x][y] == '*') {
            moleGrid[x][y] = 'M';
            molesLeft ++;
            return true;
        } else  {
            return false;
        }
    }

    void whack(int x, int y) {
        if (x == -1 && y == -1) {
            attemptsLeft = 0;
            System.out.print("Game over. \n");
            printGrid();
        } else if (moleGrid[x][y] == 'M') {
            System.out.print("Hit! \n");
            moleGrid[x][y] = 'W';
            score++;
            attemptsLeft--;
            molesLeft--;
            printGridToUser();
        } else {
            attemptsLeft --;
            printGridToUser();
        }
    }

    void printGrid() {
        for(int i = 0; i < moleGrid.length; i++)
        {
            for(int j = 0; j < moleGrid.length; j++)
            {
                System.out.print(moleGrid[i][j] + " ");
            }
            System.out.println("");
        }

    }

    void printGridToUser() {
        for(int i = 0; i < moleGrid.length; i++)
        {
            for(int j = 0; j < moleGrid.length; j++)
            {
                if(moleGrid[i][j] == 'W')
                {
                    System.out.print("W" +" ");
                }
                else
                {
                    System.out.print("*" + " ");
                }
            }
            System.out.print("\n");
        }
    }
}

