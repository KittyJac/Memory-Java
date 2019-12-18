package memory;

import java.util.Scanner;

public class Memory {
    private long timer; //in milliseconds
    private State state;
    private Player player;
    private Grid grid;
    private Scanner scanner;

    public Memory(Player player) {
        this(player, new Grid(4,5));
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
        checkForWin();
        switch (state) {
            case ONGOING:
                handleOngoing();
            case FINISHED:
                handleFinished();
            case HIGHSCORE:
                handleHighscore();
        }
    }

    private void drawGameScreen() {
        System.out.println(grid.generateGrid());
    }

    private void drawEndScreen() {
        System.out.println("[CONGRATULATIONS! YOU WON!]");
    }

    private void checkForWin() {
        if (grid.cardsGone()) {
            setState(State.FINISHED);
        }
    }

    private void handleOngoing() {
        while (state == State.ONGOING) {
            drawGameScreen();
            if (grid.getAmountFlippedCards() == 2) {
                grid.compareFlipped();

            }
            grid.flipCard(readCardCoords());
        }
    }

    private void handleHighscore() {

    }

    private int[] readCardCoords() {
        System.out.println("Which card would you like to flip? Enter XY-coordinates. (Example: 32)");
        //TODO: check if array contains two ints
        String[] cardCoordsString = scanner.nextLine().split("");
        int[] cardCoordsInt = new int[2];
        for (int i = 0; i < cardCoordsString.length; i++) {
            cardCoordsInt[i] = Integer.parseInt(cardCoordsString[i]);
        }
        return cardCoordsInt;
    }

    private void handleFinished() { drawEndScreen();}

    private void startTimer() { }

    private void stopTimer() {}

    private void resetTimer() {}

    public void resetGame() {}
}
