package memory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("What is your name?");
        Scanner scanner = new Scanner(System.in);
        //String name = scanner.nextLine();
        String name = "a";
        Player player = new Player(name);
        Memory memory = new Memory(player);

        memory.start();

    }
}
