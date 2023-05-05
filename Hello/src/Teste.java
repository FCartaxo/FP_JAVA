import java.util.Scanner;

public class Teste {
    public static void main (String[] args){
        
        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Idade: ");
        int idade = scanner.nextInt();

        if (idade > 18){
            System.out.println("Es um adulto!");
        }
        else if(idade <= 0){
            System.out.println("Não existes");
        }
        else{
            System.out.println("Nao es um adulto");
        }*/

        System.out.println(3);

        int [] vals = {1, 2, 3, 4};
        //for (int a : vals){
        //    System.out.println(a);
        //}

        for(int i=0; i<vals.length; i++){
            System.out.print(vals[i]);
            System.out.println("\n");
            // Resultado: 25461234
        }

    }
}
