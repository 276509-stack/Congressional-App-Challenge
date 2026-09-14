import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainPage extends JFrame {

    private Inventory inventory;

    private JPanel expiredPanel;
    private JPanel todayPanel;
    private JPanel soonPanel;
    private JPanel laterPanel;

    private JLabel expiredCountLabel;
    private JLabel todayCountLabel;
    private JLabel soonCountLabel;
    private JLabel totalCountLabel;


    public MainPage() {

        // Create one shared inventory for the whole app
        inventory = new Inventory();

        setupWindow();

        createPage();

        refreshInventory();
    }


    /*
     * Sets up the main app window.
     */
    private void setupWindow() {

        setTitle("Pantry Panic");

        setSize(900, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
    }


    /*
     * Creates the entire main page.
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
                        25,
                        30,
                        25,
                        30
                )
        );


        /*
         * HEADER
         */
        JPanel header =
                createHeader();

        mainContainer.add(header);

        mainContainer.add(
                Box.createVerticalStrut(25)
        );


        /*
         * SUMMARY CARDS
         */
        JPanel summaryPanel =
                createSummaryPanel();

        mainContainer.add(summaryPanel);

        mainContainer.add(
                Box.createVerticalStrut(30)
        );


        /*
         * FOOD SECTIONS
         */
        expiredPanel =
                createFoodListPanel();

        todayPanel =
                createFoodListPanel();

        soonPanel =
                createFoodListPanel();

        laterPanel =
                createFoodListPanel();


        mainContainer.add(
                createSection(
                        "Expired",
                        expiredPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(25)
        );


        mainContainer.add(
                createSection(
                        "Expires Today",
                        todayPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(25)
        );


        mainContainer.add(
                createSection(
                        "Use Soon",
                        soonPanel
                )
        );

        mainContainer.add(
                Box.createVerticalStrut(25)
        );


        mainContainer.add(
                createSection(
                        "Good for Later",
                        laterPanel
                )
        );


        /*
         * Allows scrolling if there are many foods.
         */
        JScrollPane scrollPane =
                new JScrollPane(
                        mainContainer
                );

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);


        add(scrollPane);
    }


    /*
     * Creates the header at the top.
     */
    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
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
                        30
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Never forget what's about to expire."
                );

        subtitle.setForeground(
                Color.GRAY
        );


        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);


        /*
         * ADD FOOD BUTTON
         */
        JButton addButton =
                new JButton(
                        "+ Add Food"
                );


        /*
         * This opens AddFoodPage.java.
         */
        addButton.addActionListener(
                e -> {

                    AddFoodPage addFoodPage =
                            new AddFoodPage(
                                    this,
                                    inventory,
                                    this::refreshInventory
                            );

                    addFoodPage.setVisible(true);
                }
        );


        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                addButton,
                BorderLayout.EAST
        );


        header.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );


        return header;
    }


    /*
     * Creates the number cards:
     *
     * Expired
     * Today
     * Expiring Soon
     * Total
     */
    private JPanel createSummaryPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );


        expiredCountLabel =
                createNumberLabel();

        todayCountLabel =
                createNumberLabel();

        soonCountLabel =
                createNumberLabel();

        totalCountLabel =
                createNumberLabel();


        panel.add(
                createSummaryCard(
                        "Expired",
                        expiredCountLabel
                )
        );

        panel.add(
                createSummaryCard(
                        "Today",
                        todayCountLabel
                )
        );

        panel.add(
                createSummaryCard(
                        "Expiring Soon",
                        soonCountLabel
                )
        );

        panel.add(
                createSummaryCard(
                        "Total Foods",
                        totalCountLabel
                )
        );


        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        110
                )
        );


        return panel;
    }


    /*
     * Creates the large number inside
     * each summary card.
     */
    private JLabel createNumberLabel() {

        JLabel label =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return label;
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


        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(numberLabel);


        return card;
    }


    /*
     * Creates one major category section.
     */
    private JPanel createSection(
            String title,
            JPanel listPanel
    ) {

        JPanel section =
                new JPanel();

        section.setLayout(
                new BoxLayout(
                        section,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel label =
                new JLabel(title);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );


        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        listPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        section.add(label);

        section.add(
                Box.createVerticalStrut(10)
        );

        section.add(listPanel);


        section.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        section.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                )
        );


        return section;
    }


    /*
     * Panel where FoodItems will appear.
     */
    private JPanel createFoodListPanel() {

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
     * Refreshes all food data displayed
     * on the main page.
     *
     * This runs after AddFoodPage adds food.
     */
    public void refreshInventory() {

        /*
         * Clear all old food cards.
         */
        expiredPanel.removeAll();

        todayPanel.removeAll();

        soonPanel.removeAll();

        laterPanel.removeAll();


        /*
         * Sort inventory so earliest
         * expiration dates appear first.
         */
        inventory.sortByExpirationDate();


        /*
         * Get the different food groups
         * using ReminderManager.java.
         */
        ArrayList<FoodItem> expiredFoods =
                ReminderManager.getExpiredFoods(
                        inventory
                );


        ArrayList<FoodItem> todayFoods =
                ReminderManager.getFoodsExpiringToday(
                        inventory
                );


        ArrayList<FoodItem> soonFoods =
                ReminderManager.getFoodsExpiringSoon(
                        inventory
                );


        /*
         * Display expired food.
         */
        for (
                FoodItem food :
                expiredFoods
        ) {

            expiredPanel.add(
                    createFoodCard(food)
            );

            expiredPanel.add(
                    Box.createVerticalStrut(8)
            );
        }


        /*
         * Display food expiring today.
         */
        for (
                FoodItem food :
                todayFoods
        ) {

            todayPanel.add(
                    createFoodCard(food)
            );

            todayPanel.add(
                    Box.createVerticalStrut(8)
            );
        }


        /*
         * Display food expiring soon.
         */
        for (
                FoodItem food :
                soonFoods
        ) {

            soonPanel.add(
                    createFoodCard(food)
            );

            soonPanel.add(
                    Box.createVerticalStrut(8)
            );
        }


        /*
         * Everything that is not expired,
         * today, or soon goes under
         * "Good for Later."
         */
        for (
                FoodItem food :
                inventory.getFoods()
        ) {

            if (
                    !ReminderManager.isExpired(food)
                    && !ReminderManager.expiresToday(food)
                    && !ReminderManager.expiresSoon(food)
            ) {

                laterPanel.add(
                        createFoodCard(food)
                );

                laterPanel.add(
                        Box.createVerticalStrut(8)
                );
            }
        }


        /*
         * Empty messages.
         */
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
                "Nothing expires within the next 5 days."
        );

        addEmptyMessage(
                laterPanel,
                "No food stored for later."
        );


        /*
         * Update summary numbers.
         */
        expiredCountLabel.setText(
                String.valueOf(
                        expiredFoods.size()
                )
        );


        todayCountLabel.setText(
                String.valueOf(
                        todayFoods.size()
                )
        );


        soonCountLabel.setText(
                String.valueOf(
                        soonFoods.size()
                )
        );


        totalCountLabel.setText(
                String.valueOf(
                        inventory.getSize()
                )
        );


        /*
         * Redraw screen.
         */
        revalidate();

        repaint();
    }


    /*
     * Creates one individual food card.
     */
    private JPanel createFoodCard(
            FoodItem food
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
                                15,
                                12,
                                15
                        )
                )
        );


        /*
         * LEFT SIDE
         */
        JPanel informationPanel =
                new JPanel();

        informationPanel.setLayout(
                new BoxLayout(
                        informationPanel,
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
                        17
                )
        );


        JLabel detailsLabel =
                new JLabel(

                        food.getCategory()
                        + " • Expires: "
                        + food.getExpirationDate()

                );


        detailsLabel.setForeground(
                Color.GRAY
        );


        informationPanel.add(
                nameLabel
        );

        informationPanel.add(
                Box.createVerticalStrut(4)
        );

        informationPanel.add(
                detailsLabel
        );


        /*
         * RIGHT SIDE
         */
        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        JLabel reminderLabel =
                new JLabel(

                        ReminderManager
                                .getExpirationMessage(
                                        food
                                )

                );


        reminderLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        /*
         * Change reminder color depending
         * on urgency.
         */
        if (
                ReminderManager.isExpired(food)
        ) {

            reminderLabel.setForeground(
                    new Color(
                            180,
                            30,
                            30
                    )
            );

        }

        else if (
                ReminderManager.expiresToday(food)
        ) {

            reminderLabel.setForeground(
                    new Color(
                            200,
                            100,
                            0
                    )
            );

        }

        else if (
                ReminderManager.expiresSoon(food)
        ) {

            reminderLabel.setForeground(
                    new Color(
                            170,
                            140,
                            0
                    )
            );

        }

        else {

            reminderLabel.setForeground(
                    new Color(
                            40,
                            130,
                            40
                    )
            );

        }


        /*
         * DELETE BUTTON
         */
        JButton deleteButton =
                new JButton(
                        "Delete"
                );


        deleteButton.addActionListener(
                e -> deleteFood(food)
        );


        actionPanel.add(
                reminderLabel
        );

        actionPanel.add(
                deleteButton
        );


        /*
         * Put the card together.
         */
        card.add(
                informationPanel,
                BorderLayout.WEST
        );

        card.add(
                actionPanel,
                BorderLayout.EAST
        );


        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );


        return card;
    }


    /*
     * Deletes a food item.
     */
    private void deleteFood(
            FoodItem food
    ) {

        int answer =
                JOptionPane.showConfirmDialog(

                        this,

                        "Remove "
                                + food.getName()
                                + " from your inventory?",

                        "Delete Food",

                        JOptionPane.YES_NO_OPTION

                );


        if (
                answer
                == JOptionPane.YES_OPTION
        ) {

            inventory.removeFood(food);

            refreshInventory();
        }
    }


    /*
     * Displays a message if a section
     * contains no foods.
     */
    private void addEmptyMessage(
            JPanel panel,
            String message
    ) {

        if (
                panel.getComponentCount()
                == 0
        ) {

            JLabel label =
                    new JLabel(message);

            label.setForeground(
                    Color.GRAY
            );

            panel.add(label);
        }
    }


    /*
     * Gives other parts of the app
     * access to the inventory if needed.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
