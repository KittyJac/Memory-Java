package memory;

import java.util.Random;

public class Grid {
    private int width;
    private int height;
    private Card[][] cards;
    private Random random;
    private int amountOfCardsFlipped;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cards = new Card[height][width];
        this.random = new Random();
        this.amountOfCardsFlipped = 0;
        fillCardsArray();
    }

    public int getAmountFlippedCards() {
        return amountOfCardsFlipped;
    }

    public String generateGrid() {
        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int i = 0; i < width; i++) {
            builder.append(" ");
            builder.append(i);
            builder.append(" ");
        }
        builder.append("\n");
        for (int i = 0; i < height; i++) {
            builder.append(i).append(" ");
            for (int j = 0; j < width; j++) {
                builder.append(cards[i][j].drawCard());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void flipCard(int[] cardCoords) {
        if (cards[cardCoords[1]][cardCoords[0]].isVisible()) {
            cards[cardCoords[1]][cardCoords[0]].flip();
            amountOfCardsFlipped++;
        }
    }

    public void compareFlipped() {
        int coord1 = 0;
        int coord2 = 0;
        int cardsFound = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                if (cards[i][j].isFlipped() && cardsFound == 0) {
                    coord1 = i;
                    coord2 = j;
                    cardsFound++;
                } else if (cards[i][j].isFlipped() && cardsFound == 1) {
                    if (cards[coord1][coord2].getValue() == cards[i][j].getValue()) {
                        removeCards(coord1, coord2, i, j);
                    } else {
                        cards[coord1][coord2].flip();
                        cards[i][j].flip();
                    }
                }
            }
        }
        amountOfCardsFlipped = 0;
    }

    private void removeCards(int card1Coord1, int card1Coord2, int card2Coord1, int card2Coord2) {
        cards[card1Coord1][card1Coord2].setVisible(false);
        cards[card2Coord1][card2Coord2].setVisible(false);
    }

    private void fillCardsArray() {
        int[] values = generateValues();
        values = shuffleValues(values);
        int valueCounter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.cards[i][j] = new Card(values[valueCounter]);
                valueCounter++;
            }
        }
    }

    private int[] generateValues() {
        int[] values = new int[width*height];
        for (int i = 0; i < (width*height)/2; i++) {
            values[2*i] = i;
            values[2*i+1] = i;
        }
        return values;
    }

    private int[] shuffleValues(int[] values) {
        for (int i=0; i< (width*height); i++) {
            int r = random.nextInt(width*height);
            int t = values[i];
            values[i] = values[r];
            values[r] = t;
        }

        return values;
    }

    public boolean cardsGone() {
        for (Card[] card : cards) {
            for (Card card1 : card) {
                if (card1.isVisible()) {
                    return false;
                }
            }
        }
        return true;
    }
}
