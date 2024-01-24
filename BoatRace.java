import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BoatRace {
	private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static Player winner = new Player();
    private static River river = new River(player1, player2);
    private static Placement[] riverList = river.getRiverList();
    private static FileHandler file_handler = new FileHandler();
    private static int numOfTurns = 0;
    
    

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;    
    }

    public static Player getWinner() {
        return winner;
    }

    public static River getRiver() {
        return river;
    }
    
    public static FileHandler getFileHandler() {
        return file_handler;
    }

    public static int getNumOfTurns(){
        return numOfTurns;
    }
    

    public static String promptDifficultyLevel() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Difficulty Level: ");
        System.out.println("1. Beginner");
        System.out.println("2. Intermediate");
        System.out.println("3. Expert");
        System.out.print("Please enter difficulty level [ 1 | 2 | 3 ]: ");
        // check if user input is valid
        String difficultyLevel = input.nextLine();
        while (!(difficultyLevel.equals("1") || difficultyLevel.equals("2") || difficultyLevel.equals("3"))) {
            System.out.println("Invalid input! Difficulty level must be 1, 2 or 3.\n");
            System.out.print("Please enter difficulty level [ 1 | 2 | 3 ]: ");
            difficultyLevel = input.nextLine();
        }

        return difficultyLevel;
    }

    public static void startGame(){
        int dice;

        out: while (player1.getLocation() != 100 && player2.getLocation() != 100) {

            // Update numOfTurns every round
            numOfTurns += 1;

            System.out.printf("═══════════════[ Round: %3d ]═══════════════", numOfTurns);

            System.out.println();
            dice = player1.throwDice();
            player1.setLocation(player1.getLocation() + dice);
            BoatRace.getRiver().printRiverList();

            if ((player1.getLocation() < 100)) {
                System.out.println(player1);
                while (!(riverList[player1.getLocation()] instanceof Blank )) {
                    encounterLocation(riverList[player1.getLocation()], player1);
                    if ((player1.getLocation() >= 100)) {
                        winner = player1;
                        break out;
                    } else if (player1.getLocation() < 0) {
                        player1.setLocation(0);
                        System.out.println("! Restart from 0");
                    }
                    System.out.println(player1);
                }
            } else {
                winner = player1;
                break;
            }
            
            river.printRiverList();

            // divider
            System.out.println();

            dice = player2.throwDice();
            player2.setLocation(player2.getLocation() + dice);
            BoatRace.getRiver().printRiverList();

            if ((player2.getLocation() < 100)) {
                System.out.println(player2);
                while (!(riverList[player2.getLocation()] instanceof Blank)) {
                    encounterLocation(riverList[player2.getLocation()], player2);
                    if ((player2.getLocation() >= 100)) {
                        winner = player2;
                        break out;
                    } else if (player2.getLocation() < 0) {
                        player2.setLocation(0);
                        System.out.println("! Restart from 0");
                    }
                    System.out.println(player2);
                }
            } else {
                winner = player2;
                break;
            }
            
            river.printRiverList();

        }
    }

    private static void encounterLocation(Placement placement, Player player) {
        if (placement instanceof Current) {
            ((Current) placement).generateMagnitude();
            System.out.print(placement);
            player.setLocation(player.getLocation() + placement.getMagnitude());

        } else if (placement instanceof Trap) {
            ((Trap) placement).generateMagnitude();
            System.out.print(placement);
            player.setLocation(player.getLocation() - placement.getMagnitude());

        }
    }

}

