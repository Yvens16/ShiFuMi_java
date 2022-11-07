import java.io.*;
import com.shifumi.player.Player;
import java.util.Scanner;
import org.w3c.dom.ls.LSOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  // Import the Scanner class


public class Main {
    int score = 0;

    // J1 ==> rows, j2 ==> columns
    int[][] zeroSumMatrix = {
            {0, -1, 1},
            {1, 0, -1},
            {-1, 1, 0}
    };

    Map<String, Integer> dico = new HashMap<String, Integer>(){{
        put("P",0); put("F",1); put("C",2);}};

    public static void  main(String[] args) {
        Player j1 = new Player();
        Player j2 = new Player();
        Main mc = new Main();
        int numberOfTour = 3;
        System.out.println("Le jeu va commencer");
        System.out.println("Alors pour commencer, voyons voir qui sont nos joueuers");
        Console console = System.console();
        if (console != null) {
            String j1Name =  console.readLine("J1 ajoute ton nom ici stp ==> ");
            j1.setName(j1Name);
            System.out.println(String.format("Bienvenue %s !", j1.getName()));
            String j2Name =  console.readLine("Maintenant J2 ajoute ton nom ici stp ==> ");
            j2.setName(j2Name);
            System.out.println(String.format("Bienvenue à toi %s !", j2.getName()));
            while(numberOfTour > 0) {
                String choice1 = console.readLine(String.format("%s Choisis entre P, F ou C stp ==> ", j1.getName()));
                String choice2 = console.readLine(String.format("%s Choisis entre P, F ou C stp ==> ", j2.getName()));
                mc.whichPLayerWonTheTour(choice1, choice2, j1, j2);
                numberOfTour--;
            }
            System.out.println(mc.whichPlayerWonTheGame(j1, j2));
        }
    }

    public void whichPLayerWonTheTour(String choice1, String choice2, Player j1, Player j2) {
        Integer res1 = this.dico.get(choice1);
        Integer res2 = this.dico.get(choice2);
        if (this.zeroSumMatrix[res1][res2] > 0) {
            System.out.println(String.format("%s won the tour", j1.getName()));
        }
        if (this.zeroSumMatrix[res1][res2] < 0) {
            System.out.println(String.format("%s won the tour", j2.getName()));
        }
        if (this.zeroSumMatrix[res1][res2] == 0) System.out.println(String.format("Nobody won", j2.getName()));
        this.score += this.zeroSumMatrix[res1][res2];
    }

    public String whichPlayerWonTheGame(Player j1, Player j2) {
        if (this.score > 0) return String.format("Congratulations %s, You won the game !", j1.getName());
        if (this.score < 0) return String.format("Congratulations %s, You won the game !", j2.getName());
        return String.format("Well done guys it's a draw !");
    }
}