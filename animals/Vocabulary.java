package animals;

import java.time.LocalTime;
import java.util.*;
import java.util.function.UnaryOperator;


public abstract class Vocabulary {

    public static final ResourceBundle res = ResourceBundle.getBundle("Animals");
    private static final Random rnd = new Random();
    private static final Set<String> confirmations = Set.of(res.getStringArray("confirmations"));
    private static final Set<String> negations = Set.of(res.getStringArray("negations"));
    private static final List<String> clarifications = List.of(res.getStringArray("clarifications"));
    private static final List<String> farewells = List.of(res.getStringArray("farewells"));

    public static String getLogic(String key, String par) {
        UnaryOperator<String> func = (UnaryOperator<String>) res.getObject(key);
        return func.apply(par);
    }

    public static String getStr(String key) {
        return res.getString(key);
    }

    public static boolean isYes(Scanner scanner) {
        YesNo answer = Vocabulary.getYesNoUndefined(scanner.nextLine());
        while (answer == YesNo.UNDEFINED) {
            System.out.println(Vocabulary.getClarification());
            answer = Vocabulary.getYesNoUndefined(scanner.nextLine());
        }

        return answer == YesNo.YES;
    }

    private static YesNo getYesNoUndefined(String input) {
        input = input.strip().toLowerCase();
        if (input.endsWith("!") || input.endsWith(".")) {
            input = input.substring(0, input.length() - 1);
        }
        if (confirmations.contains(input)) {
            return YesNo.YES;
        } else if (negations.contains(input)) {
            return YesNo.NO;
        } else {
            return YesNo.UNDEFINED;
        }
    }

    public static String getClarification() {
        return clarifications.get(rnd.nextInt(clarifications.size()));
    }

    public static String getFarewell() {
        return farewells.get(rnd.nextInt(farewells.size()));
    }

    public static String getGreeting() {
        int hour = LocalTime.now().getHour();
        if (hour >= 5 && hour < 12) {
            return res.getString("Good morning!");
        } else if (hour >= 12 && hour < 18) {
            return res.getString("Good afternoon!");
        } else if (hour >= 18) {
            return res.getString("Good evening!");
        } else {
            return res.getString("Hi, Night Owl!");
        }
    }

    public static String capitalise(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    enum YesNo {
        YES,
        NO,
        UNDEFINED;

        @Override
        public String toString() {
            if (this == YES) {
                return res.getString("Yes");
            } else if (this == NO) {
                return res.getString("No");
            } else {
                return res.getString("Not sure");
            }
        }
    }
}
