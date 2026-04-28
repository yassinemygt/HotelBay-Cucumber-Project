package hellocucumber;

public class IsItFriday {
    public static String check(String day) {
        return "Friday".equals(day) ? "TGIF" : "Nope";
    }
}