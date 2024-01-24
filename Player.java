import java.util.Scanner;
import java.util.Random;

public class Player {
	 	private String name;
	    private int score = 0;
	    private int location = 0;
	    private char symbol;
	    
	    Scanner input = new Scanner(System.in);

	    
	    public Player() {
	        name = "";
	    }

	    public Player(String name) {
	        this.name = name;
	    }

	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public int getLocation() {
	        return location;
	    }

	    public void setLocation(int location) {
	        this.location = location;
	    }
	    
	    public char getSymbol() {
	    	return symbol;
	    }
	    
	    public void setSymbol(char symbol) {
	    	this.symbol = symbol;
	    }

	    public void promptName(int num) {
	        System.out.printf("Please enter Player %d's name: ", num);
	        name = input.nextLine();
	        while (name.equals("") || name.length() > 18) {
	            if (name.equals("")) {
	                System.out.println("Please enter a valid name!");
	            } else if (name.length() > 18) {
	                System.out.println("Name cannot be more than 18 characters!");
	            }
	            System.out.printf("Please enter Player %d's name: ", num);
	            name = input.nextLine();
	            symbol = (num == 1) ? 'X' : 'O';
	        }
	       // System.out.printf("%s = X\n" , BoatRace.getPlayer1().getName());
           // System.out.printf("%s = O\n" , BoatRace.getPlayer2().getName());
	        
	    }

	    public int throwDice() {
	        Random randomNum = new Random();
	        System.out.printf("%s = X\n" , BoatRace.getPlayer1().getName());
	        System.out.printf("%s = O\n" , BoatRace.getPlayer2().getName());
	        System.out.printf("* %s, press ENTER to throw dice", name);
	        input.nextLine();
	        int dice = (1 + randomNum.nextInt(6));
	        System.out.println("- You rolled a " + dice);
	        return dice;
	    }

	    @Override
	    public String toString() {
	        return String.format("- You moved to location %d\n", location);
	    }


}
