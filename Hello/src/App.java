import java.util.Scanner;

public class App {
    public static void main(String[] args){
        double principal;
        double rate;
        double interest;

        Scanner stdin = new Scanner (System.in);

        System.out.println("Investimento após um ano");
        System.out.print("                  Investimento inicial: ");
        principal = stdin.nextDouble();
        System.out.print("    Taxa de juros anual (como decimal): ");
        rate = stdin.nextDouble();

        interest = principal * rate;
        principal += interest;

        System.out.printf("       O valor do juro ganho é %10.2f Eur%n", interest);
        System.out.format("O valor do investimento após é %10.2f Eur%n", principal);

    }
}
