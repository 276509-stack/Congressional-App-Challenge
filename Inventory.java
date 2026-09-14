import java.util.ArrayList;
import java.util.Comparator;

public class Inventory {

    private ArrayList<FoodItem> foods;

    public Inventory() {
        foods = new ArrayList<>();
    }

    public void addFood(FoodItem food) {
        foods.add(food);
    }

    public void removeFood(FoodItem food) {
        foods.remove(food);
    }

    public void removeFood(int index) {

        if (index >= 0 && index < foods.size()) {
            foods.remove(index);
        }
    }

    public ArrayList<FoodItem> getFoods() {
        return foods;
    }

    public FoodItem getFood(int index) {

        if (index >= 0 && index < foods.size()) {
            return foods.get(index);
        }

        return null;
    }

    public int getSize() {
        return foods.size();
    }

    public void sortByExpirationDate() {

        foods.sort(
                Comparator.comparing(
                        FoodItem::getExpirationDate
                )
        );
    }

    public FoodItem findFood(String name) {

        for (FoodItem food : foods) {

            if (
                    food.getName()
                            .equalsIgnoreCase(name)
            ) {

                return food;
            }
        }

        return null;
    }

    public void clearInventory() {
        foods.clear();
    }
}
