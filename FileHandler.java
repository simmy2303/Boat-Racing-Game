import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.SecurityException;
import java.util.NoSuchElementException;

public class FileHandler {
    private ArrayList<ArrayList<Player>> players = new ArrayList<ArrayList<Player>>(
            Arrays.asList(new ArrayList<Player>(), new ArrayList<Player>(), new ArrayList<Player>()));
    private Formatter fileWriter;
    private Scanner fileReader;

    public FileHandler() {
        File file = new File("TopScore.txt");
        try {
            file.createNewFile();
            if (file.createNewFile()) {
                System.out.println("File has been created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        extractData();
    }

    private void updateFile() {
        try {
            fileWriter = new Formatter("TopScore.txt");

            for (int i = 0; i < players.size(); i++) {
                for (int j = 0; j < players.get(i).size(); j++) {
                    if (j < 5) {
                        fileWriter.format("%s\n", players.get(i).get(j).getName());
                        fileWriter.format("%d\n", players.get(i).get(j).getScore());
                    }
                }

                fileWriter.format("%s\n", ".");
            }

            fileWriter.close();

        } catch (SecurityException e) {
            System.out.println("You do not have write access");
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening/creating file.");
        }
    }
    
    public void printPlayers(int i) {
        System.out.println();
        System.out.println("╔════════════════════════════╗");
        String[] levelName = new String[] { "Beginner", "Intermediate", "Expert" };

        System.out.print("║");
        for (int k = 0; k < ((15 - levelName[i - 1].length()) / 2); k++) {
            System.out.print(" ");
        }

        System.out.print(" Difficulty: " + levelName[i - 1] + " ");
        for (int k = 0; k < ((15 - levelName[i - 1].length()) / 2); k++) {
            System.out.print(" ");
        }
        System.out.print("║");
        System.out.println();
        System.out.println("╠════════════════════╦═══════╣");
        System.out.println("║        Name        ║ Score ║");
        System.out.println("╠════════════════════╬═══════╣");
        for (int j = 0; j < players.get(i - 1).size(); j++) {
            if (j < 5) {
                System.out.printf("║ %-18s ║  %-4d ║\n", players.get(i - 1).get(j).getName(),
                        players.get(i - 1).get(j).getScore());
            }
        }
        System.out.println("╚════════════════════╩═══════╝");
    }

    private void extractData() {
        try {
            fileReader = new Scanner(new File("TopScore.txt"));

            int index = 0;
            while (fileReader.hasNext()) {
                String tempName = fileReader.nextLine();

                if (!tempName.equals(".")) {
                    String tempScore = fileReader.nextLine();
                    Player temp = new Player(tempName);

                    temp.setScore(Integer.parseInt(tempScore));
                    players.get(index).add(temp);
                } else {
                    index++;
                }
            }

            if (fileReader != null) {
                fileReader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error opening file.");
        } catch (NoSuchElementException e) {
            System.out.println("File improperly formed.");
        }

    }

    public void updateList(Player player, int difficultyLevel) {
        players.get(difficultyLevel - 1).add(player);

        for (int j = 0; j < players.get(difficultyLevel - 1).size(); j++) {
            for (int ind = 0; ind < players.get(difficultyLevel - 1).size() - 1; ind++) {
                Player temp1 = players.get(difficultyLevel - 1).get(ind);
                Player temp2 = players.get(difficultyLevel - 1).get(ind + 1);

                if (temp1.getScore() > temp2.getScore()) {
                    players.get(difficultyLevel - 1).set(ind, temp2);
                    players.get(difficultyLevel - 1).set(ind + 1, temp1);
                }
            }
        }
        updateFile();
    }

//    public void printPlayers(int i) {
//        System.out.println();
//        System.out.println("╔════════════════════════════╗");
//        String[] levelName = new String[] { "Beginner", "Intermediate", "Expert" };
//
//        System.out.print("║");
//        for (int k = 0; k < ((15 - levelName[i - 1].length()) / 2); k++) {
//            System.out.print(" ");
//        }
//
//        System.out.print(" Difficulty: " + levelName[i - 1] + " ");
//        for (int k = 0; k < ((15 - levelName[i - 1].length()) / 2); k++) {
//            System.out.print(" ");
//        }
//        System.out.print("║");
//        System.out.println();
//        System.out.println("╠════════════════════╦═══════╣");
//        System.out.println("║        Name        ║ Score ║");
//        System.out.println("╠════════════════════╬═══════╣");
//        for (int j = 0; j < players.get(i - 1).size(); j++) {
//            if (j < 5) {
//                System.out.printf("║ %-18s ║  %-4d ║\n", players.get(i - 1).get(j).getName(),
//                        players.get(i - 1).get(j).getScore());
//            }
//        }
//        System.out.println("╚════════════════════╩═══════╝");
//    }
}


