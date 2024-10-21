import AbstractFactoryDesignPattern.AbstractVehicleFactory;
import AbstractFactoryDesignPattern.Car;
import AbstractFactoryDesignPattern.FordFactoryAbstract;
import AbstractFactoryDesignPattern.TeslaFactoryAbstract;
import AbstractFactoryDesignPattern.Truck;
import AdapterDesignPattern.AudioPlayer;
import AdapterDesignPattern.MediaPlayer;
import ChainOfResponsibilityDesignPattern.DebugLogger;
import ChainOfResponsibilityDesignPattern.ErrorLogger;
import ChainOfResponsibilityDesignPattern.InfoLogger;
import ChainOfResponsibilityDesignPattern.Logger;
import CommandDesignPattern.Command;
import CommandDesignPattern.Light;
import CommandDesignPattern.LightOffCommand;
import CommandDesignPattern.LightOnCommand;
import CommandDesignPattern.RemoteControl;
import CompositeDesignPattern.Circle;
import CompositeDesignPattern.CompositeGraphic;
import CompositeDesignPattern.Graphic;
import CompositeDesignPattern.Square;
import DecoratorDesignPattern.MilkDecorator;
import DecoratorDesignPattern.SimpleCoffee;
import DecoratorDesignPattern.SugarDecorator;
import FacadeDesignPattern.DvdPlayer;
import FacadeDesignPattern.HomeTheaterFacade;
import FacadeDesignPattern.Lights;
import FacadeDesignPattern.Projector;
import FacadeDesignPattern.SoundSystem;
import FactoryDesignPattern.CarFactory;
import FactoryDesignPattern.TruckFactory;
import FactoryDesignPattern.VehicleFactory;
import FlyWeightDesignPattern.Tree;
import FlyWeightDesignPattern.TreeFactory;
import FlyWeightDesignPattern.TreeType;
import InterpretDesignPattern.AdditionExpression;
import InterpretDesignPattern.Expression;
import InterpretDesignPattern.NumberExpression;
import InterpretDesignPattern.SubtractionExpression;
import IteratordesignPattern.Book;
import IteratordesignPattern.Iterator;
import IteratordesignPattern.Library;
import MediatorPattern.ChatMediator;
import MediatorPattern.ChatMediatorImpl;
import MediatorPattern.User;
import MediatorPattern.UserImpl;
import NullObjectDesignPattern.Customer;
import NullObjectDesignPattern.CustomerFactory;
import ObserverDesignPattern.CurrentConditionsDisplay;
import ObserverDesignPattern.ForecastDisplay;
import ObserverDesignPattern.WeatherStation;
import ProxyDesignPattern.Database;
import ProxyDesignPattern.SecureDatabaseProxy;
import StateDesignPattern.Coin;
import StateDesignPattern.State;
import StateDesignPattern.VendingMachine;
import StrategyPattern.CashPayment;
import StrategyPattern.CreditCardPayment;
import StrategyPattern.Item;
import StrategyPattern.PayPalPayment;
import StrategyPattern.ShoppingCart;
import TemplateDesignPattern.CaffeineBeverage;
import TemplateDesignPattern.Coffee;
import TemplateDesignPattern.Tea;
import MementoDesignPattern.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");


        //Mediater design pattern
        ChatMediator chatMediator = new ChatMediatorImpl();

        User user = new UserImpl(chatMediator, "1", "Joseph");
        User user2 = new UserImpl(chatMediator, "2", "Mighty");
        User user3 = new UserImpl(chatMediator, "3", "Raina");

        chatMediator.addUser(user);
        chatMediator.addUser(user2);
        chatMediator.addUser(user3);

        user.sendMessage("hello", "1");
        System.out.println("");

        //Stratergy design patern
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(10, "shoe"));
        shoppingCart.addItem(new Item(20, "lays"));

        shoppingCart.pay(new CashPayment("Joseph"));

        shoppingCart.pay(new CreditCardPayment("1232", "Joseph"));

        shoppingCart.pay(new PayPalPayment("spourgeon@gmail.com", "Mighty"));

        System.out.println("");
        //State Design pattern


        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.getInventory().addItem(new Item(10, "lays"));
        vendingMachine.getInventory().addItem(new Item(5, "biscuit"));

        List<Coin> coinList = new ArrayList<>();
        coinList.add(Coin.DIME);
        coinList.add(Coin.NICKEL);

        State vedingMachineState = vendingMachine.getVendingMachineState();
        vedingMachineState.clickOnInsertCoinButton(vendingMachine);

        vedingMachineState = vendingMachine.getVendingMachineState();
        vedingMachineState.insertCoin(vendingMachine, Coin.NICKEL);

        vedingMachineState.insertCoin(vendingMachine, Coin.NICKEL);

        vedingMachineState.clickOnStartProductSelectionButton(vendingMachine);

        vedingMachineState = vendingMachine.getVendingMachineState();
        vedingMachineState.chooseProduct(vendingMachine, "lays");
        System.out.println("");

        //template desingn pattern

        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();

        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();

        //observer design pattern
        WeatherStation weatherStation = new WeatherStation();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        weatherStation.addObserver(currentDisplay);
        weatherStation.addObserver(forecastDisplay);

        weatherStation.setMeasurements(25.5f, 65f, 1013f);


        System.out.println("removed observer");
        weatherStation.removeObserver(forecastDisplay);

        weatherStation.setMeasurements(27.0f, 70f, 1010f);

        System.out.println("");
        //Decorator Pattern

        DecoratorDesignPattern.Coffee coffee1 = new MilkDecorator(new SugarDecorator(new SimpleCoffee()));
        DecoratorDesignPattern.Coffee coffee2 = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee1.getDescription());
        System.out.println("cost is" + coffee1.getCost());
        System.out.println(coffee2.getDescription());
        System.out.println("cost 2 is" + coffee2.getCost());

        //Chain Of responsibility principle

        Logger logger = new InfoLogger();
        Logger errorLogger = new ErrorLogger();
        Logger debugLogger = new DebugLogger();
        logger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);

        logger.logMessage(1, "Hello");
        logger.logMessage(2, "Hello");
        logger.logMessage(3, "Hello");

        System.out.println("");
        //Factory Design Pattern

        VehicleFactory vehicleFactory = new CarFactory();
        vehicleFactory.driveVehicle();
        VehicleFactory vehicleFactory1 = new TruckFactory();
        vehicleFactory1.driveVehicle();


        System.out.println("");
        //Abstract Design Pattern

        AbstractVehicleFactory fordFactory = new FordFactoryAbstract();
        Car fordCar = fordFactory.createCar();
        Truck fordTruck = fordFactory.createTruck();
        fordCar.drive();
        fordTruck.drive();

        AbstractVehicleFactory teslaFactory = new TeslaFactoryAbstract();
        Car teslaCar = teslaFactory.createCar();
        Truck teslaTruck = teslaFactory.createTruck();
        teslaCar.drive();
        teslaTruck.drive();

        System.out.println("");


        //Proxy Design Pattern

        Database adminProxy = new SecureDatabaseProxy("ADMIN");
        System.out.println("Admin trying to access the database:");
        adminProxy.fetchData();  // Output: Fetching sensitive data from the database.

        // User with GUEST role
        Database guestProxy = new SecureDatabaseProxy("GUEST");
        System.out.println("\nGuest trying to access the database:");
        guestProxy.fetchData();

        System.out.println("");

        //Null Object Design Pattern
        Customer customer1 = CustomerFactory.getCustomer("John");
        Customer customer2 = CustomerFactory.getCustomer("Doe");

        System.out.println("Customers:");
        System.out.println(customer1.getName());  // John
        System.out.println(customer2.getName());  // Not Available

        System.out.println("");


        //Adapter Design Pattern
        MediaPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3"); // Playing MP3 file: song.mp3
        audioPlayer.play("vlc", "movie.vlc"); // Playing VLC file: movie.vlc
        audioPlayer.play("avi", "video.avi"); // Invalid media type: avi

        System.out.println("");

        //Composite Design Pattern

        Graphic circle = new Circle();
        Graphic square = new Square();


        CompositeGraphic compositeGraphic = new CompositeGraphic();
        compositeGraphic.add(circle);
        compositeGraphic.add(square);

        // Draw individual shapes
        circle.draw();  // Output: Drawing a Circle
        square.draw();  // Output: Drawing a Square

        // Draw composite shapes
        compositeGraphic.draw();
        System.out.println("");


        //Facade Design Pattern
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Lights lights = new Lights();


        // Create the facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(projector, soundSystem, dvdPlayer, lights);

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();

        System.out.println("");

        //FlyWeight Design Pattern

        List<Tree> trees = new ArrayList<>();

        // Create 1,000 trees with shared types
        for (int i = 0; i < 1000; i++) {
            TreeType type = TreeFactory.getTreeType("Oak", "Green", "Rough");
            trees.add(new Tree(i, i + 10, type));
        }


        // Display all trees
        for (Tree tree : trees) {
            tree.display();
        }

        System.out.println("");

        //Iterator Design Pattern

        Library library = new Library(5);

        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("Brave New World", "Aldous Huxley"));
        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury"));

        Iterator<Book> bookIterator = library.createIterator();

        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            System.out.println(book);
        }

        System.out.println("");

        //Memento Design Pattern
        TextEditor textEditor = new TextEditor();
        TextEditorHistory history = new TextEditorHistory();

        textEditor.setContent("First Version");
        history.save(textEditor);  // Save state

        textEditor.setContent("Second Version");
        history.save(textEditor);  // Save state

        textEditor.setContent("Third Version");

        System.out.println("Current Content: " + textEditor.getContent());

        // Undo to the last saved state
        history.undo(textEditor);
        System.out.println("After Undo: " + textEditor.getContent());

        // Undo to the first saved state
        history.undo(textEditor);
        System.out.println("After Second Undo: " + textEditor.getContent());


        //Interpreter design pattern

        Expression five = new NumberExpression(5);
        Expression three = new NumberExpression(3);
        Expression two = new NumberExpression(2);

        Expression addExpression = new AdditionExpression(five, three); // 5 + 3
        Expression subtractExpression = new SubtractionExpression(addExpression, two); // (5 + 3) - 2

        // Evaluate the expression
        int result = subtractExpression.interpret();
        System.out.println("Result: " + result); // Output: 6

        //Command design pattern

        Light livingRoomLight = new Light();

        // Create concrete commands
        Command lightOnCommand = new LightOnCommand(livingRoomLight);
        Command lightOffCommand = new LightOffCommand(livingRoomLight);

        // Create invoker (remote control)
        RemoteControl remote = new RemoteControl();

        // Turn the light on
        remote.setCommand(lightOnCommand);
        remote.pressButton(); // Output: The light is on

        // Turn the light off
        remote.setCommand(lightOffCommand);
        remote.pressButton();
    }
}