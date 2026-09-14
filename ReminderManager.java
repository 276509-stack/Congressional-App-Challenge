import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReminderManager {

    public static long getDaysUntilExpiration(
            FoodItem food
    ) {

        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                food.getExpirationDate()
        );
    }

    public static String getExpirationStatus(
            FoodItem food
    ) {

        long daysLeft =
                getDaysUntilExpiration(food);

        if (daysLeft < 0) {
            return "Expired";
        }

        if (daysLeft == 0) {
            return "Expires Today";
        }

        if (daysLeft <= 5) {
            return "Use Soon";
        }

        return "Fresh";
    }

    public static String getExpirationMessage(
            FoodItem food
    ) {

        long daysLeft =
                getDaysUntilExpiration(food);

        if (daysLeft < 0) {

            long daysExpired =
                    Math.abs(daysLeft);

            if (daysExpired == 1) {
                return "Expired yesterday";
            }

            return "Expired "
                    + daysExpired
                    + " days ago";
        }

        if (daysLeft == 0) {
            return "Expires today";
        }

        if (daysLeft == 1) {
            return "Expires tomorrow";
        }

        return "Expires in "
                + daysLeft
                + " days";
    }

    public static boolean isExpired(
            FoodItem food
    ) {

        return getDaysUntilExpiration(food) < 0;
    }

    public static boolean expiresToday(
            FoodItem food
    ) {

        return getDaysUntilExpiration(food) == 0;
    }

    public static boolean expiresSoon(
            FoodItem food
    ) {

        long daysLeft =
                getDaysUntilExpiration(food);

        return daysLeft > 0
                && daysLeft <= 5;
    }

    public static ArrayList<FoodItem>
    getExpiredFoods(Inventory inventory) {

        ArrayList<FoodItem> result =
                new ArrayList<>();

        for (
                FoodItem food :
                inventory.getFoods()
        ) {

            if (isExpired(food)) {
                result.add(food);
            }
        }

        return result;
    }

    public static ArrayList<FoodItem>
    getFoodsExpiringToday(
            Inventory inventory
    ) {

        ArrayList<FoodItem> result =
                new ArrayList<>();

        for (
                FoodItem food :
                inventory.getFoods()
        ) {

            if (expiresToday(food)) {
                result.add(food);
            }
        }

        return result;
    }

    public static ArrayList<FoodItem>
    getFoodsExpiringSoon(
            Inventory inventory
    ) {

        ArrayList<FoodItem> result =
                new ArrayList<>();

        for (
                FoodItem food :
                inventory.getFoods()
        ) {

            if (expiresSoon(food)) {
                result.add(food);
            }
        }

        return result;
    }
}
