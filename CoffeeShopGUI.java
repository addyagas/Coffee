import java.awt.*;
import java.io.File;
import javax.swing.*;

public class CoffeeShopGUI {
    private static JFrame frame;
    private static JLabel coffeeLabel;
    private static JLabel coffeeImageLabel;
    private static JTextArea orderLog;
    private static CoffeeShop coffeeShop;

    public static void main(String[] args) {
        coffeeShop = new CoffeeShop(10); // sets the maximum queue capacity at 10
        setupGUI();

        // Sample orders
        Customer customer1 = new Customer("Addy");
        Customer customer2 = new Customer("Sarah");
        Customer customer3 = new Customer("Thara");
        Customer customer4 = new Customer("Julia");
        Customer customer5 = new Customer("Anthony");

        Beverage beverage1 = new Cappuccino();
        Beverage beverage2 = new LatteMacchiato("Plain", true);
        Beverage beverage3 = new DripCoffee(true);
        Beverage beverage4 = new Tea(true);
        Beverage beverage5 = new LatteMacchiato("Vanilla", false);
        Beverage beverage6 = new Matcha();
        coffeeShop.placeOrder(beverage1, customer1);
        coffeeShop.placeOrder(beverage4, customer4);
        coffeeShop.placeOrder(beverage2, customer2);
        coffeeShop.placeOrder(beverage5, customer3);
        coffeeShop.placeOrder(beverage6, customer4);
        coffeeShop.placeOrder(beverage3, customer1);
        coffeeShop.placeOrder(beverage2, customer5);
    }

    private static void setupGUI() {
        frame = new JFrame("Coffee Shop Orders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);
        frame.setLayout(new BorderLayout());

        ImageIcon originalBackgroundIcon = new ImageIcon("src/images/shop.jpg");
        Image originalBackgroundImage = originalBackgroundIcon.getImage();
        Image scaledBackgroundImage = scaleImageWithAspectRatio(originalBackgroundImage, frame.getWidth(), frame.getHeight());
        ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImage);

        JLabel background = new JLabel(scaledBackgroundIcon);
        frame.setContentPane(background);
        background.setLayout(new BorderLayout()); //this is what gets the background to show up 

        coffeeLabel = new JLabel("COFFEE SHOP", SwingConstants.CENTER);
        coffeeLabel.setFont(new Font("Happy Monkey", Font.BOLD, 20));
        coffeeLabel.setForeground(Color.WHITE);
        coffeeLabel.setOpaque(true); //highlight color is now visible
        coffeeLabel.setBackground(new Color(210, 180, 140)); // tan color using RGB values because java doesn't have a preset value for 'tan'
        background.add(coffeeLabel, BorderLayout.NORTH);

        // Image display
        coffeeImageLabel = new JLabel();
        coffeeImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(coffeeImageLabel, BorderLayout.CENTER);

        //creates an order log
        orderLog = new JTextArea(10, 30); 
        orderLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderLog);
        background.add(scrollPane, BorderLayout.SOUTH);

        //JButton created to process orders
        JButton processOrdersButton = new JButton("Process Orders");
        background.add(processOrdersButton, BorderLayout.SOUTH);
        processOrdersButton.addActionListener(e -> processNextOrder());
        frame.setVisible(true);
    }

    private static void processNextOrder() {
        //gets rid of the first order from the queue then gets the next order
        Order nextOrder = coffeeShop.getOrderQueue().dequeue();
        if (nextOrder == null) {
            coffeeLabel.setText("No more orders to process.");
            coffeeImageLabel.setIcon(null); 
            return;
        }

        Beverage beverage = nextOrder.getBeverage();
        String customerName = nextOrder.getCustomerName();
        String message = beverage.getDrinkType() + " for " + customerName;
        coffeeLabel.setText(message);

        //this displays the correct coffee/tea/matcha image 
        String imagePath = getImagePath(beverage.getDrinkType());
        if (new File(imagePath).exists()) {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage()
                    .getScaledInstance(frame.getWidth() - 50, frame.getHeight() - 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            coffeeImageLabel.setIcon(scaledIcon);
        } else {
            coffeeImageLabel.setIcon(new ImageIcon("src/images/default.jpg")); //default image
        }
        orderLog.append(message + "\n");
    }

    private static String getImagePath(String coffeeType) {
        //makes sure that each coffee type has a corresponding image file using switch case
        return switch (coffeeType) {
            case "Cappuccino" -> "src/images/cappuchino.jpg";
            case "Latte Macchiato - Vanilla" -> "src/images/Latte Macchiato - Plain.jpg";
            case "Latte Macchiato - Plain" -> "src/images/Latte Macchiato - Plain.jpg";
            case "Drip Coffee - Iced" -> "src/images/Drip Coffee - Iced.jpg";
            case "Tea - Green" -> "src/images/green tea.jpg";
            case "Matcha" -> "src/images/matcha.jpg";
            default -> "src/images/default.jpg"; // default image for unknown coffee types
        };
    }

    //i couldnt get the image to work without zooming in a lot so I scaled the image to fit the exact ratio
    private static Image scaleImageWithAspectRatio(Image originalImage, int frameWidth, int frameHeight) {
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);
        double aspectRatio = (double) originalWidth / originalHeight;
        int newWidth = frameWidth;
        int newHeight = (int) (frameWidth / aspectRatio);
        if (newHeight > frameHeight) {
            newHeight = frameHeight;
            newWidth = (int) (frameHeight * aspectRatio);
        }

        return originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
