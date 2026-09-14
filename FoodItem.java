import java.time.LocalDate;

public class FoodItem {

    private String name;
    private String category;
    private LocalDate expirationDate;

    public FoodItem(
            String name,
            String category,
            LocalDate expirationDate
    ) {

        this.name = name;
        this.category = category;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setExpirationDate(
            LocalDate expirationDate
    ) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {

        return name
                + " | "
                + category
                + " | Expires: "
                + expirationDate;
    }
}
