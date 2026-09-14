import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddFoodPage extends JDialog {

    private JTextField nameField;
    private JComboBox<String> categoryBox;
    private JTextField expirationField;

    private Inventory inventory;

    private Runnable refreshAction;

    public AddFoodPage(
            JFrame parent,
            Inventory inventory,
            Runnable refreshAction
    ) {

        super(
                parent,
                "Add Food",
                true
        );

        this.inventory = inventory;
        this.refreshAction = refreshAction;

        setupWindow();

        createPage();
    }

    private void setupWindow() {

        setSize(
                400,
                320
        );

        setLocationRelativeTo(
                getParent()
        );

        setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );
    }

    private void createPage() {

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        // FOOD NAME

        JLabel nameLabel =
                new JLabel(
                        "Food Name:"
                );

        nameField =
                new JTextField();

        nameField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );


        // CATEGORY

        JLabel categoryLabel =
                new JLabel(
                        "Category:"
                );

        String[] categories = {
                "Dairy",
                "Fruit",
                "Vegetable",
                "Meat",
                "Frozen",
                "Pantry",
                "Other"
        };

        categoryBox =
                new JComboBox<>(
                        categories
                );

        categoryBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );


        // EXPIRATION DATE

        JLabel expirationLabel =
                new JLabel(
                        "Expiration Date (YYYY-MM-DD):"
                );

        expirationField =
                new JTextField();

        expirationField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );


        // BUTTONS

        JButton saveButton =
                new JButton(
                        "Save Food"
                );

        JButton cancelButton =
                new JButton(
                        "Cancel"
                );


        saveButton.addActionListener(
                e -> saveFood()
        );

        cancelButton.addActionListener(
                e -> dispose()
        );


        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                saveButton
        );

        buttonPanel.add(
                cancelButton
        );


        // ADD EVERYTHING

        mainPanel.add(
                nameLabel
        );

        mainPanel.add(
                nameField
        );

        mainPanel.add(
                Box.createVerticalStrut(15)
        );


        mainPanel.add(
                categoryLabel
        );

        mainPanel.add(
                categoryBox
        );

        mainPanel.add(
                Box.createVerticalStrut(15)
        );


        mainPanel.add(
                expirationLabel
        );

        mainPanel.add(
                expirationField
        );

        mainPanel.add(
                Box.createVerticalStrut(20)
        );


        mainPanel.add(
                buttonPanel
        );


        add(mainPanel);
    }

    private void saveFood() {

        String name =
                nameField
                        .getText()
                        .trim();

        String category =
                categoryBox
                        .getSelectedItem()
                        .toString();

        String dateText =
                expirationField
                        .getText()
                        .trim();


        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a food name."
            );

            return;
        }


        if (dateText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an expiration date."
            );

            return;
        }


        try {

            LocalDate expirationDate =
                    LocalDate.parse(
                            dateText
                    );


            FoodItem food =
                    new FoodItem(
                            name,
                            category,
                            expirationDate
                    );


            inventory.addFood(
                    food
            );


            /*
             * Tell MainPage to redraw itself
             * after the food gets added.
             */
            if (refreshAction != null) {

                refreshAction.run();
            }


            dispose();

        }

        catch (
                DateTimeParseException exception
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter the date as YYYY-MM-DD.\n"
                            + "Example: 2026-09-25"
            );
        }
    }
}
