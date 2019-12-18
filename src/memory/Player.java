package memory;

import java.time.LocalDate;

public class Player {
    private String name;
    private LocalDate date;
    private int mouseclicks;
    private long timer;
    private long highscore;

    public Player(String name) {
        this.timer = 0;
        this.highscore = 0;
        this.mouseclicks = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public long getHighscore() {
        return highscore;
    }

    public void setHighscore(long highscore) {
        this.highscore = highscore;
    }

    public int getMouseclicks() {
        return mouseclicks;
    }

    public void setMouseclicks(int mouseclicks) {
        this.mouseclicks = mouseclicks;
    }
}
