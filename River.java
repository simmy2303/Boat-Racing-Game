import java.util.ArrayList;

public class River {
	private String difficultyLevel;
    private Placement[] riverList = new Placement[100];
    private ArrayList<Placement> riverPlacement;
    private Player player1;
    private Player player2;
    private char[] playerPositions;

    
    public River(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        playerPositions = new char[riverList.length];
        for (int i = 0; i < riverList.length; i++) {
            playerPositions[i] = ' ';
        }
        generateTrack();
    }
    
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        generateTrack();
    }
    
    public Placement[] getRiverList() {
        return riverList;
    }
    
 
    public void makeMove(Player player, int steps) {
        int currentPosition = player.getLocation();
//        player.(steps);
        int newPosition = player.getLocation();
        playerPositions[currentPosition] = ' ';
        playerPositions[newPosition] = player.getSymbol();
    }

    public void printRiverList() {
        System.out.print("    ");

        for (int i = 0; i < riverList.length / 10; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();

        for (int i = 0; i < riverList.length / 10; i++) {
            System.out.print(" " + i + "  ");
            for (int j = 0; j < riverList.length / 10; j++) {
                int index = i * 10 + j;
                if (player1.getLocation() == index) {
                    System.out.print("X  ");
                } else if (player2.getLocation() == index) {
                    System.out.print("O  ");
                } else if (riverList[index] instanceof Blank) {
                    System.out.print(".  ");
                } else if (riverList[index] instanceof Current) {
                    riverList[index].generateMagnitude();
                    System.out.print("C  ");
                } else if (riverList[index] instanceof Trap) {
                    riverList[index].generateMagnitude();
                    System.out.print("T  ");
                }
            }
            if (i == riverList.length / 10 - 1) {
                System.out.print("END");
            } else {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printRiverLocation() {
        for (Placement riverplacement : riverPlacement) {
            if (riverplacement instanceof Blank) {
                System.out.print(".");
            } else if (riverplacement instanceof Current) {
                System.out.printf("C[%d]", riverplacement.getPosition());
            } else if (riverplacement instanceof Trap) {
                System.out.printf("T[%d]", riverplacement.getPosition());
            }
        }
        System.out.print(".");
    }

    private void generateRiverPlacement(int blankNum, int currentNum, int trapNum) {
        riverPlacement = new ArrayList<Placement>();
        int total_ratio = blankNum + currentNum + trapNum;

        for (int i = 0; i < (currentNum * 100) / total_ratio; i++) {
            Placement temp = new Current();
            boolean positionValid = false;

            while (!positionValid) {
                int counter = 0;
                for (Placement riverplacement : riverPlacement) {
                    if (temp.getPosition() != riverplacement.getPosition()) {
                        counter++;
                    } else {
                        temp = new Current();
                        break;
                    }
                }

                if (counter == riverPlacement.size()) {
                    riverPlacement.add(temp);
                    positionValid = true;
                }
            }
        }

        for (int i = 0; i < (trapNum * 100) / total_ratio; i++) {
            Placement temp = new Trap();
            boolean positionValid = false;
            while (!positionValid) {
                int counter = 0;
                for (Placement riverplacement : riverPlacement) {
                    if (temp.getPosition() != riverplacement.getPosition()) {
                        counter++;
                    } else {
                        temp = new Trap();
                        break;
                    }
                }

                if (counter == riverPlacement.size()) {
                    riverPlacement.add(temp);
                    positionValid = true;
                }
            }
        }
    }

    private void generateTrack() {
        if (difficultyLevel == null) {
            generateRiverPlacement(1, 0, 0);
        } else if (difficultyLevel.equals("1")) {
            generateRiverPlacement(5, 3, 1);
        } else if (difficultyLevel.equals("2")) {
            generateRiverPlacement(4, 2, 1);
        } else if (difficultyLevel.equals("3")) {
            generateRiverPlacement(3, 1, 1);
        }

        
        for (int i = 0; i < riverList.length; i++) {
        	
            riverList[i] = new Blank();
        }
       
        for (Placement riverplacement : riverPlacement) {
            riverList[riverplacement.getPosition()] = riverplacement;
        }

        while (!checkRiver()) {
            generateTrack();
        }
    }

    public boolean checkRiver() {
        int count = 0;
        for (int i = 0; i < riverList.length - 1; i++) {
            if (riverList[i] instanceof Trap && riverList[i + 1] instanceof Trap) {
                count++;
            } else {
                count = 0;
            }
        }
        return (count < 5) ? true : false;
    }

}