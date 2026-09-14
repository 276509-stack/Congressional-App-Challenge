import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class MainPage extends JFrame {

    private ArrayList<FoodItem> inventory;

    private JPanel expiredPanel;
    private JPanel todayPanel;
    private JPanel soonPanel;
    private JPanel laterPanel;

    private JLabel expiredCountLabel;
    private JLabel todayCountLabel;
    private JLabel soonCountLabel;
    private JLabel totalCountLabel;

    public MainPage() {

        inventory = new ArrayList<>();

        // Sample foods so the page is not empty at first
        inventory.add(
                new FoodItem(
                        "Milk",
                        "Dairy",
                        LocalDate.now()
                )
        );

        inventory.add(
                new FoodItem(
                        "Spinach",
                        "Vegetable",
                        LocalDate.now().plusDays(1)
                )
        );

        inventory.add(
                new FoodItem(
                        "Chicken",
                        "Meat",
                        LocalDate.now().plusDays(3)
                )
        );

        inventory.add(
                new FoodItem(
                        "Eggs",
                        "Dairy",
                        LocalDate.now().plusDays(9)
                )
        );

        inventory.add(
                new FoodItem(
                        "Yogurt",
                        "Dairy",
                        LocalDate.now().minusDays(1)
                )
        );

        setupWindow();

        createPage();

        refreshInventory();
    }


    /*
     * Sets up the main window.
     */
    private void setupWindow() {

        setTitle("Pantry Panic");

        setSize(900, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );
    }


    /*
     * Creates everything visible on the page.
     */
    private void createPage() {

        JPanel mainContainer =
                new JPanel();

        mainContainer.setLayout(
                new BoxLayout(
                        mainContainer,
                        BoxLayout.Y_AXIS
                )
        );

        mainContainer.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        addHeader(mainContainer);

        mainContainer.add(
                Box.createVerticalStrut(20)
        );

        addSummarySection(mainContainer);

        mainContainer.add(
                Box.createVerticalStrut(25)
        );

        expiredPanel =
                createFoodSection(
                        "Expired"
                );

        todayPanel =
                createFoodSection(
                        "Expires Today"
                );

        soonPanel =
                createFoodSection(
                        "Use Soon"
                );

        laterPanel =
                createFoodSection(
                        "Good for Later"
                );


        mainContainer.add(
                createSectionWrapper(
                        "Expired",
                        expiredPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(20)
        );

        mainContainer.add(
                createSectionWrapper(
                        "Expires Today",
                        todayPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(20)
        );

        mainContainer.add(
                createSectionWrapper(
                        "Use Soon",
                        soonPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(20)
        );

        mainContainer.add(
                createSectionWrapper(
                        "Good for Later",
                        laterPanel
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        mainContainer
                );

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scrollPane,
                BorderLayout.CENTER
        );
    }


    /*
     * Header with title and Add Food button.
     */
    private void addHeader(
            JPanel container
    ) {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );


        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Pantry Panic"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Keep track of food before it expires."
                );

        subtitle.setForeground(
                Color.GRAY
        );


        textPanel.add(title);

        textPanel.add(
                Box.createVerticalStrut(5)
        );

        textPanel.add(subtitle);


        JButton addFoodButton =
                new JButton(
                        "+ Add Food"
                );

        addFoodButton.addActionListener(
                e -> showAddFoodDialog()
        );


        header.add(
                textPanel,
                BorderLayout.WEST
        );

        header.add(
                addFoodButton,
                BorderLayout.EAST
        );


        container.add(header);
    }


    /*
     * Creates the four number cards at the top.
     */
    private void addSummarySection(
            JPanel container
    ) {

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );


        expiredCountLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        todayCountLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        soonCountLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        totalCountLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );


        summaryPanel.add(
                createSummaryCard(
                        "Expired",
                        expiredCountLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "Today",
                        todayCountLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "Expiring Soon",
                        soonCountLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "Total Items",
                        totalCountLabel
                )
        );


        summaryPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100
                )
        );


        container.add(
                summaryPanel
        );
    }


    /*
     * Creates one summary card.
     */
    private JPanel createSummaryCard(
            String title,
            JLabel numberLabel
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.LIGHT_GRAY
                        ),

                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        numberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        numberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(numberLabel);


        return card;
    }


    /*
     * Creates a wrapper around each food category.
     */
    private JPanel createSectionWrapper(
            String title,
            JPanel listPanel
    ) {

        JPanel wrapper =
                new JPanel();

        wrapper.setLayout(
                new BoxLayout(
                        wrapper,
                        BoxLayout.Y_AXIS
                )
        );

        wrapper.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        listPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        wrapper.add(
                titleLabel
        );

        wrapper.add(
                Box.createVerticalStrut(8)
        );

        wrapper.add(
                listPanel
        );


        wrapper.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                )
        );


        return wrapper;
    }


    /*
     * Creates a panel that will hold food cards.
     */
    private JPanel createFoodSection(
            String name
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        return panel;
    }


    /*
     * Rebuilds the page based on the current inventory.
     */
    private void refreshInventory() {

        expiredPanel.removeAll();

        todayPanel.removeAll();

        soonPanel.removeAll();

        laterPanel.removeAll();


        inventory.sort(
                Comparator.comparing(
                        FoodItem::getExpirationDate
                )
        );


        int expiredCount = 0;
        int todayCount = 0;
        int soonCount = 0;


        for (
                FoodItem food :
                inventory
        ) {

            long daysLeft =
                    getDaysUntilExpiration(
                            food
                    );


            if (daysLeft < 0) {

                expiredPanel.add(
                        createFoodCard(
                                food,
                                daysLeft
                        )
                );

                expiredCount++;
            }

            else if (daysLeft == 0) {

                todayPanel.add(
                        createFoodCard(
                                food,
                                daysLeft
                        )
                );

                todayCount++;
            }

            else if (daysLeft <= 5) {

                soonPanel.add(
                        createFoodCard(
                                food,
                                daysLeft
                        )
                );

                soonCount++;
            }

            else {

                laterPanel.add(
                        createFoodCard(
                                food,
                                daysLeft
                        )
                );
            }

        }


        addEmptyMessage(
                expiredPanel,
                "No expired food."
        );

        addEmptyMessage(
                todayPanel,
                "Nothing expires today."
        );

        addEmptyMessage(
                soonPanel,
                "Nothing expires in the next 5 days."
        );

        addEmptyMessage(
                laterPanel,
                "No food in this category."
        );


        expiredCountLabel.setText(
                String.valueOf(
                        expiredCount
                )
        );

        todayCountLabel.setText(
                String.valueOf(
                        todayCount
                )
        );

        soonCountLabel.setText(
                String.valueOf(
                        soonCount
                )
        );

        totalCountLabel.setText(
                String.valueOf(
                        inventory.size()
                )
        );


        revalidate();

        repaint();
    }


    /*
     * Creates one food item display.
     */
    private JPanel createFoodCard(
            FoodItem food,
            long daysLeft
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),

                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );


        JPanel information =
                new JPanel();

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel nameLabel =
                new JLabel(
                        food.getName()
                );

        nameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        JLabel categoryLabel =
                new JLabel(
                        food.getCategory()
                        + " • "
                        + food.getExpirationDate()
                );

        categoryLabel.setForeground(
                Color.GRAY
        );


        information.add(
                nameLabel
        );

        information.add(
                categoryLabel
        );


        JLabel expirationLabel =
                new JLabel(
                        getExpirationMessage(
                                daysLeft
                        )
                );

        expirationLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        JButton deleteButton =
                new JButton(
                        "Delete"
                );

        deleteButton.addActionListener(
                e -> {

                    inventory.remove(
                            food
                    );

                    refreshInventory();

                }
        );


        JPanel rightPanel =
                new JPanel();

        rightPanel.add(
                expirationLabel
        );

        rightPanel.add(
                deleteButton
        );


        card.add(
                information,
                BorderLayout.WEST
        );

        card.add(
                rightPanel,
                BorderLayout.EAST
        );


        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        70
                )
        );


        return card;
    }


    /*
     * Calculates how many days remain.
     */
    private long getDaysUntilExpiration(
            FoodItem food
    ) {

        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                food.getExpirationDate()
        );
    }


    /*
     * Turns the number of days into readable text.
     */
    private String getExpirationMessage(
            long daysLeft
    ) {

        if (daysLeft < 0) {

            return "Expired "
                    + Math.abs(daysLeft)
                    + " day(s) ago";
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


    /*
     * Adds an empty message if a section
     * has no foods.
     */
    private void addEmptyMessage(
            JPanel panel,
            String text
    ) {

        if (
                panel.getComponentCount()
                == 0
        ) {

            JLabel label =
                    new JLabel(text);

            label.setForeground(
                    Color.GRAY
            );

            panel.add(label);
        }
    }


    /*
     * Popup for adding food.
     */
    private void showAddFoodDialog() {

        JTextField nameField =
                new JTextField();

        String[] categories = {
                "Dairy",
                "Fruit",
                "Vegetable",
                "Meat",
                "Frozen",
                "Pantry",
                "Other"
        };

        JComboBox<String> categoryBox =
                new JComboBox<>(
                        categories
                );

        JTextField dateField =
                new JTextField(
                        "2026-09-20"
                );


        JPanel dialogPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                5,
                                5
                        )
                );


        dialogPanel.add(
                new JLabel(
                        "Food name:"
                )
        );

        dialogPanel.add(
                nameField
        );


        dialogPanel.add(
                new JLabel(
                        "Category:"
                )
        );

        dialogPanel.add(
                categoryBox
        );


        dialogPanel.add(
                new JLabel(
                        "Expiration date (YYYY-MM-DD):"
                )
        );

        dialogPanel.add(
                dateField
        );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        dialogPanel,
                        "Add Food",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                result
                == JOptionPane.OK_OPTION
        ) {

            try {

                String name =
                        nameField
                                .getText()
                                .trim();

                String category =
                        categoryBox
                                .getSelectedItem()
                                .toString();

                LocalDate date =
                        LocalDate.parse(
                                dateField
                                        .getText()
                                        .trim()
                        );


                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a food name."
                    );

                    return;
                }


                FoodItem newFood =
                        new FoodItem(
                                name,
                                category,
                                date
                        );


                inventory.add(
                        newFood
                );


                refreshInventory();

            }

            catch (
                    Exception exception
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the date as YYYY-MM-DD."
                );
            }
        }
    }
}
