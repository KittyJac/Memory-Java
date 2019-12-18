package memory;

public class Card {
    private int value;
    private boolean flipped;
    private boolean visible;

    public Card(int value) {
        this.flipped = false;
        this.value = value;
        this.visible = true;
    }

    public int getValue() {
        return value;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void flip() {
        flipped = !flipped;
    }

    public String drawCard() {
        if (visible) {
            if (flipped) {
                return String.format("[%s]", value);
            } else {
                return "[#]";
            }
        } else {
            return "   ";
        }
    }
}
