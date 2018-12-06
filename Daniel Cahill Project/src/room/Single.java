package room;

public class Single extends Room{
    private static double PRICE = 75.00;
    private static int MAX_GUEST = 1;

    public Single() {
        super(PRICE, MAX_GUEST);
    }

}