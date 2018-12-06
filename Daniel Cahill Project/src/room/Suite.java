package room;

public class Suite extends Room{
    private static double PRICE = 150.00;
    private static int MAX_GUEST = 3;

    public Suite() {
        super(PRICE, MAX_GUEST);
    }

}