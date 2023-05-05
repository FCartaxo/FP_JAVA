import java.util.Random;

public class Exercicio2_2 {
    public static void main (String[] args){

            Random aleatorio = new Random();

            int lauch1 = aleatorio.nextInt(6) + 1;
            int lauch2 = aleatorio.nextInt(6) + 1;

            System.out.println("Lançamento de dois dados");

            //lauch1 = (int) (Math.random() * 6) + 1;
            //lauch2 = (int) (Math.random() * 6) + 1;

            System.out.println("O primeiro dado apresenta "+ lauch1);
            System.out.println("O primeiro dado apresenta "+ lauch2);
            System.out.println("O total é: "+ (lauch1+lauch2));
    }
}
