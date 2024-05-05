package LLD.TicTacToe.Constants;

public enum Move {
    ONE("00"),
    TWO("01"),
    THREE("02"),
    FOUR("10"),
    FIVE("11"),
    SIX("12"),
    SEVEN("20"),
    EIGHT("21"),
    NINE("22");

    private final String position;

    Move(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

}
