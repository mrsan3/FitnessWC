import java.util.*;

class FitnessClass {
    private String type;
    private String day;
    private int time;
    private int capacity;
    private int price;
    private List<String> customers;
    private double rating;
    private String status;

    public FitnessClass(String type, String day, int time, int price) {
        this.type = type;
        this.day = day;
        this.time = time;
        this.price = price;
        this.capacity = 5;
        this.customers = new ArrayList<>();
        this.rating = 0.0;
        this.status = "booked";
    }

    public String getType() {
        return type;
    }

    public String getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFull() {
        return customers.size() >= capacity;
    }

    public boolean addCustomer(String customer) {
        if (isFull()) return false;
        customers.add(customer);
        return true;
    }

    public boolean removeCustomer(String customer) {
        return customers.remove(customer);
    }

    public List<String> getCustomers() {
        return new ArrayList<>(customers);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addReview(String review) {
        // add the review to the lesson
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumCustomers() {
        return customers.size();
    }

    public int getCapacity() {
        return capacity;
    }
}


class Booking {
    private static int count = 0;
    private int id;
    private FitnessClass fitnessClass;
    private String customer;

    public Booking(FitnessClass fitnessClass, String customer) {
        this.id = ++count;
        this.fitnessClass = fitnessClass;
        this.customer = customer;
        this.fitnessClass.addCustomer(customer);
    }

    public int getId() {
        return id;
    }

    public FitnessClass getFitnessClass() {
        return fitnessClass;
    }

    public String getCustomer() {
        return customer;
    }

    public void setFitnessClass(FitnessClass fitnessClass) {
        this.fitnessClass.removeCustomer(customer);
        this.fitnessClass = fitnessClass;
        this.fitnessClass.addCustomer(customer);
    }

    public void cancel() {
        this.fitnessClass.removeCustomer(customer);
    }
}

class WeekendFitnessClub {
    private static Map<String, List<FitnessClass>> timetable = new HashMap<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeTimetable();
        while (true) {
            System.out.println("Welcome to the Weekend Fitness Club!");
            System.out.println("Please select an option:");
            System.out.println("1. Book a class");
            System.out.println("2. Change/cancel a booking");
            System.out.println("3. Attend a class");
            System.out.println("4. Monthly class report");
            System.out.println("5. Monthly champion fitness type report");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookClass();
                    break;
                case 2:
                    changeCancelBooking();
                    break;
                case 3:
                    attendClass();
                    break;
                case 4:
                    monthlyClassReport();
                    break;
                case 5:
                    monthlyChampionFitnessTypeReport();
                    break;
                case 6:
                    System.out.println("Thank you for using the Weekend Fitness Club app. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void initializeTimetable() {
        List<FitnessClass> mondayClasses = new ArrayList<>();
        mondayClasses.add(new FitnessClass("Yoga", "Monday", 9, 10));
        mondayClasses.add(new FitnessClass("Zumba", "Monday", 12, 15));
        mondayClasses.add(new FitnessClass("Pilates", "Monday", 15, 20));
        timetable.put("Monday", mondayClasses);

        List<FitnessClass> tuesdayClasses = new ArrayList<>();
        tuesdayClasses.add(new FitnessClass("Spinning", "Tuesday", 10, 15));
        tuesdayClasses.add(new FitnessClass("Yoga", "Tuesday", 13, 10));
        tuesdayClasses.add(new FitnessClass("Boxing", "Tuesday", 16, 25));
        timetable.put("Tuesday", tuesdayClasses);

        List<FitnessClass> wednesdayClasses = new ArrayList<>();
        wednesdayClasses.add(new FitnessClass("Pilates", "Wednesday", 11, 20));
        wednesdayClasses.add(new FitnessClass("Zumba", "Wednesday", 14, 15));
        wednesdayClasses.add(new FitnessClass("Yoga", "Wednesday", 17, 10));
        timetable.put("Wednesday", wednesdayClasses);

        List<FitnessClass> thursdayClasses = new ArrayList<>();
        thursdayClasses.add(new FitnessClass("Boxing", "Thursday", 9, 25));
        thursdayClasses.add(new FitnessClass("Pilates", "Thursday", 12, 20));
        thursdayClasses.add(new FitnessClass("Spinning", "Thursday", 15, 15));
        timetable.put("Thursday", thursdayClasses);

        List<FitnessClass> fridayClasses = new ArrayList<>();
        fridayClasses.add(new FitnessClass("Yoga", "Friday", 10, 10));
        fridayClasses.add(new FitnessClass("Zumba", "Friday", 13, 15));
        fridayClasses.add(new FitnessClass("Boxing", "Friday", 16, 25));
        timetable.put("Friday", fridayClasses);

        List<FitnessClass> saturdayClasses = new ArrayList<>();
        saturdayClasses.add(new FitnessClass("Pilates", "Saturday", 9, 20));
        saturdayClasses.add(new FitnessClass("Yoga", "Saturday", 12, 10));
        saturdayClasses.add(new FitnessClass("Spinning", "Saturday", 15, 15));
        timetable.put("Saturday", saturdayClasses);

        List<FitnessClass> sundayClasses = new ArrayList<>();
        sundayClasses.add(new FitnessClass("Zumba", "Sunday", 10, 15));
        sundayClasses.add(new FitnessClass("Boxing", "Sunday", 13, 25));
        sundayClasses.add(new FitnessClass("Pilates", "Sunday", 16, 20));
        timetable.put("Sunday", sundayClasses);
    }

    private static void bookClass() {
        System.out.println("Please enter your name:");
        String customerName = scanner.next();
        System.out.println("Please enter the day of the class (e.g. Monday):");
        String day = scanner.next();
        List<FitnessClass> classes = timetable.get(day);
        if (classes == null) {
            System.out.println("Invalid day.");
            return;
        }
        System.out.println("Please select a class:");
        for (int i = 0; i < classes.size(); i++) {
            FitnessClass fitnessClass = classes.get(i);
            System.out.println((i + 1) + ". " + fitnessClass.getType() + " - " + fitnessClass.getTime() + ":00 (" + fitnessClass.getPrice() + " credits)");
        }
        int classIndex = scanner.nextInt() - 1;
        FitnessClass fitnessClass = classes.get(classIndex);
        if (fitnessClass.isFull()) {
            System.out.println("This class is already full.");
            return;
        }
        Booking booking = new Booking(fitnessClass, customerName);
        bookings.add(booking);
        System.out.println("Your booking has been confirmed with ID " + booking.getId() + ".");
    }

    public static void changeCancelBooking() {
        System.out.println("Please enter your booking ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Booking booking = findBookingById(id);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.println("Please select an option:");
        System.out.println("1. Change class");
        System.out.println("2. Cancel booking");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (choice == 1) {
            changeClass(booking);
        } else if (choice == 2) {
            cancelBooking(booking);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void changeClass(Booking booking) {
        System.out.println("Please select a new class:");

        String day = booking.getFitnessClass().getDay();
        List<FitnessClass> classes = timetable.get(day);
        for (int i = 0; i < classes.size(); i++) {
            FitnessClass fitnessClass = classes.get(i);
            System.out.printf("%d. %s at %d:00 (%d/%d)%n", i + 1, fitnessClass.getType(),
                    fitnessClass.getTime(), fitnessClass.getNumCustomers(), fitnessClass.getCapacity());
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        FitnessClass newClass = classes.get(choice - 1);
        booking.setFitnessClass(newClass);

        System.out.println("Booking updated.");
    }

    public static void cancelBooking(Booking booking) {
        booking.cancel();
        bookings.remove(booking);
        System.out.println("Booking cancelled.");
    }

    public static Booking findBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }

    private static void attendClass() {
        System.out.println("Please enter your name:");
        String customer = scanner.next();

        System.out.println("Please enter the ID of the booking you want to attend:");
        int id = scanner.nextInt();

        Booking bookingToAttend = null;
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                bookingToAttend = booking;
                break;
            }
        }

        if (bookingToAttend == null) {
            System.out.println("Error: Booking not found.");
            return;
        }

        FitnessClass fitnessClass = bookingToAttend.getFitnessClass();
        String status = fitnessClass.getStatus();
        if (status.equals("attended")) {
            System.out.println("Error: Class has already been attended.");
            return;
        } else if (status.equals("cancelled")) {
            System.out.println("Error: Class has been cancelled.");
            return;
        } else if (status.equals("changed")) {
            System.out.println("The booking has been changed. Do you still want to attend the class? (yes/no)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                return;
            }
        }

        fitnessClass.setStatus("attended");

        System.out.println("Please provide a review of the class:");
        String review = scanner.next();
        fitnessClass.addReview(review);

        System.out.println("Please provide a numerical rating for the class (1-5):");
        int rating = scanner.nextInt();
        fitnessClass.setRating(rating);

        System.out.println("Thank you for attending the class and providing feedback!");
    }


    public static void monthlyClassReport() {
        System.out.println("Monthly class report:");
        for (String day : timetable.keySet()) {
            System.out.println(day + ":");
            List<FitnessClass> classes = timetable.get(day);
            for (FitnessClass fitnessClass : classes) {
                if (fitnessClass.getNumCustomers() > 0) {
                    System.out.printf("%s at %d: %d/%d customers booked%n",
                            fitnessClass.getType(), fitnessClass.getTime(),
                            fitnessClass.getNumCustomers(), fitnessClass.getCapacity());
                }
            }
        }
    }


    public static void monthlyChampionFitnessTypeReport() {
        Map<String, Integer> incomeByType = new HashMap<>();
        for (List<FitnessClass> classes : timetable.values()) {
            for (FitnessClass fitnessClass : classes) {
                int income = fitnessClass.getNumCustomers() * fitnessClass.getPrice();
                incomeByType.put(fitnessClass.getType(), incomeByType.getOrDefault(fitnessClass.getType(), 0) + income);
            }
        }

        // Find the champion fitness type
        String championType = "";
        int championIncome = 0;
        for (Map.Entry<String, Integer> entry : incomeByType.entrySet()) {
            String type = entry.getKey();
            int income = entry.getValue();
            if (income > championIncome) {
                championType = type;
                championIncome = income;
            }
            System.out.printf("%s: $%d%n", type, income);
        }

        System.out.printf("The champion fitness type is %s with a total income of $%d%n", championType, championIncome);
    }
}