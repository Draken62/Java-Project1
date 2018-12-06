package guest;

@SuppressWarnings("serial")
public class Student extends Guest {
    private final static double DISCOUNT = 0;

    public Student(String name) {
        super(name, DISCOUNT);
    }

}