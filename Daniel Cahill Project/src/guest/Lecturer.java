package guest;

@SuppressWarnings("serial")
public class Lecturer extends Guest {

    private final static double DISCOUNT = 0.10;

    public Lecturer(String name) {
        super(name, DISCOUNT);
    }
}