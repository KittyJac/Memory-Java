package memory;

import java.util.Scanner;

public class Memory {
    private long timer; //in milliseconds
    private State state;
    private Player player;
    private Grid grid;
    private Scanner scanner;
    private boolean gameFinished;

    public Memory(Player player) {
        this(player, new Grid(2,2));
    }

    public Memory(Player player, int width, int height){
        this(player, new Grid(width, height));
    }

    public Memory(Player player, Grid grid) {
        this.timer = 0;
        this.state = State.ONGOING;
        this.player = player;
        this.grid = grid;
        this.scanner = new Scanner(System.in);
        this.gameFinished = false;
    }

    public long getTimer() {
        return timer;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void start() {
        while (!gameFinished) {
            checkForWin();
            switch (state) {
                case ONGOING:
                    handleOngoing();
                    break;
                case FINISHED:
                    handleFinished();
                    break;
                case HIGHSCORE:
                    handleHighscore();
                    break;
            }
        }
    }

    private void drawGameScreen() {
        System.out.println(grid.generateGrid());
    }

    private void drawEndScreen() {
        System.out.println("[CONGRATULATIONS! YOU WON!]");
        gameFinished = true;
    }

    private void checkForWin() {
        if (grid.cardsGone()) {
            state = State.FINISHED;
        }
    }

    private void handleOngoing() {
        drawGameScreen();
        if (grid.getAmountFlippedCards() == 2) {
            grid.compareFlipped();
        }
        int[] cardCoords = readCardCoords();
        if (cardCoords != null) {
            grid.flipCard(cardCoords);
        } else {
            System.out.println("PLEASE TYPE IN TWO COORDINATES");
        }
    }

    private void handleHighscore() {

    }

    private int[] readCardCoords() {
        System.out.println("Which card would you like to flip? Enter XY-coordinates. (Example: 32)");
        String cardCoordsString = scanner.nextLine();
        if (cardCoordsString.matches("^[0-9]{2}$")) {
            String[] cardCoordsStrings = cardCoordsString.split("");
            int[] cardCoordsInt = new int[2];
            cardCoordsInt[0] = Integer.parseInt(cardCoordsStrings[0]);
            cardCoordsInt[1] = Integer.parseInt(cardCoordsStrings[1]);
            if (cardCoordsInt[0] < grid.getWidth() && cardCoordsInt[1] < grid.getHeight()) {
                for (int i = 0; i < cardCoordsStrings.length; i++) {
                    cardCoordsInt[i] = Integer.parseInt(cardCoordsStrings[i]);
                }
                return cardCoordsInt;
            }
        }
        return null;
    }

    private void handleFinished() { drawEndScreen();}

    private void startTimer() { }

    private void stopTimer() {}

    private void resetTimer() {}

    public void resetGame() {}
}
