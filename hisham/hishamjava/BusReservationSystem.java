package hisham.hishamjava;

import java.util.Scanner;
import java.util.Random;

class BinarySearchTree {
    int PassnNo; // busNo0SeatNo.
    String name;
    BinarySearchTree left;
    BinarySearchTree right;

    public BinarySearchTree(int PassnNo, String name) {
        this.PassnNo = PassnNo;
        this.name = name;
        this.left = this.right = null;
    }
}

public class BusReservationSystem {
    static BinarySearchTree root = null;
    static int[][] busSeat = new int[32][33]; // busSeat[32][33] represents the bus seats, initially all set to 0
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        int randomNum = random.nextInt(10000);
        login();
        mainMenu(randomNum);
    }

    static void mainMenu(int randomNum) {
        int num;
        do {
            System.out.println("\n\n====================================================================");
            System.out.println("\t\t\tBUS RESERVATION");
            System.out.println("\n\n=====================================================================");
            System.out.println("====================  MAIN MENU =====================");
            System.out.println("   [1] VIEW BUS LIST");
            System.out.println("   [2] BOOK TICKETS");
            System.out.println("   [3] CANCEL BOOKING");
            System.out.println("   [4] BUSES SEATS INFO");
            System.out.println("   [5] RESERVATION INFO");
            System.out.println("   [6] EXIT");
            System.out.println("=====================================================");
            System.out.print("   ENTER YOUR CHOICE: ");
            num = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (num) {
                case 1:
                    busLists();
                    break;
                case 2:
                    bookTickets(randomNum);
                    break;
                case 3:
                    cancel(randomNum);
                    break;
                case 4:
                    status();
                    break;
                case 5:
                    reservationInfoMenu(randomNum);
                    break;
                case 6:
                    System.out.println("\n\n=====================================================================");
                    System.out.println("THANK YOU FOR USING THIS BUS RESERVATION SYSTEM");
                    System.out.println("PRESS ANY KEY TO EXIT THE END PROGRAM !!");
                    scanner.nextLine(); // consume any leftover input
                    break;
                default:
                    System.out.println("\n\nINVALID INPUT CHOOSE CORRECT OPTION");
                    break;
            }
        } while (num != 6);
    }

    static void login() {
        String userName = "tj";
        String passWord = "hisham";
        String matchPass;
        String matchUser;
        boolean loggedIn = false;

        System.out.println("\n\n=========================================================================================");
        System.out.println("\t\t\tWELCOME TO ONLINE BUS RESERVATION");
        System.out.println("\n\n=========================================================================================\n\n");

        while (!loggedIn) {
            System.out.print("\n\nUserName: ");
            matchUser = scanner.nextLine();

            System.out.print("\nPassWord: ");
            matchPass = scanner.nextLine();

            if (passWord.equals(matchPass)) {
                System.out.println("\nLOGGED IN SUCCESSFULLY...");
                loggedIn = true;
            } else {
                System.out.println("\nINVALID DETAILS TRY AGAIN...");
            }
        }
    }

    static void busLists() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Bus.No\tName\t\t\tDestinations  \t\tCharges  \t\tTime");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1\tGreenLine            \tDhaka to Chittagong   \tBDT 70   \t\t07:00  AM");
        System.out.println("2\tShohag               \tChittagong To Dhaka   \tBDT 55   \t\t01:30  PM");
        System.out.println("3\tDesh Travels         \tRajshahi To Dhaka     \tBDT 40   \t\t03:50  PM");
        System.out.println("4\tShyamoli Paribahan   \tSylhet To Dhaka       \tBDT 70   \t\t01:00  AM");
        System.out.println("5\tHanif Enterprise     \tDhaka To Khulna       \tBDT 55   \t\t12:05  AM");
        System.out.println("6\tEna Transport        \tBarisal to Dhaka      \tBDT 40   \t\t09:30  AM");
        System.out.println("7\tNabil Paribahan      \tDhaka To Rangpur      \tBDT 70   \t\t11:00  PM");
        System.out.println("8\tS Alam Service       \tCox's Bazar To Dhaka  \tBDT 55   \t\t08:15  AM");
        System.out.println("9\tShyamoli NR Travels  \tDhaka To Jessore      \tBDT 40   \t\t04:00  PM");
        System.out.println("\nPRESS 'ENTER' KEY TO CONTINUE");
        scanner.nextLine(); // wait for user to press enter
    }

    static void bookTickets(int randomNum) {
        busLists();
        int choice;
        int seats;

        System.out.print("\n\nCHOOSE YOUR BUS  : ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (choice <= 0 || choice > 9) {
            System.out.println("\nENTER VALID BUS NUMBER !! ");
            return;
        }

        DisplaySeat(busSeat[choice]);

        System.out.print("\n\nNO. OF SEATS YOU NEED TO BOOK : ");
        seats = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (seats <= 0 || seats > 32) {
            System.out.println("\nENTER VALID SEAT NUMBER WE HAVE ONLY 32 SEATS IN A BUS !!");
            return;
        }

        for (int i = 1; i <= seats; i++) {
            int seatNumber;
            System.out.println("\n\n==================================================================================");
            do {
                System.out.print("   ENTER THE SEAT NUMBER: ");
                seatNumber = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (seatNumber <= 0 || seatNumber > 32) {
                    System.out.println("\nENTER VALID SEAT NUMBER WE HAVE ONLY 32 SEATS IN A BUS !!");
                } else {
                    break;
                }
            } while (true);

            int CustId = choice * 1000 + seatNumber; // CustumerId
            busSeat[choice][seatNumber] = 1;
            root = insert(root, CustId);
            System.out.println("\n   YOUR CUSTOMER ID IS : " + CustId);
            System.out.println("\n\n==================================================================================");
        }
        System.out.println("\nYOUR RESERVATION NUMBER IS : " + randomNum);
        System.out.println("\nPLEASE NOTE DOWN YOUR RESERVATION NUMBER FOR CANCEL BOOKING TICKETS!!");
        System.out.println("PRESS 'ENTER' KEY TO CONTINUE ");
        scanner.nextLine(); // wait for user to press enter
    }

    static void cancel(int randomNum) {
        int reservationNo;
        int seatNumber;
        int choice;
        char c;
        int seatCancel;

        System.out.print("\nENTER YOUR RESERVATION NUMBER : ");
        reservationNo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (reservationNo == randomNum) {
            System.out.println("\nRESERVATION NUMBER IS IT CORRECT ? " + reservationNo + " \nENTER (Y/N) : ");
            c = scanner.nextLine().charAt(0);
            if (c == 'y' || c == 'Y') {
                System.out.println("\n\n============================================");
                System.out.print("   ENTER THE BUS NUMBER: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                System.out.print("\n HOW MANY SEATS DO WANT TO CANCEL : ");
                seatCancel = scanner.nextInt();
                scanner.nextLine(); // consume newline
                for (int i = 0; i < seatCancel; i++) {
                    System.out.print("   \nENTER THE SEAT NUMBER: ");
                    seatNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    busSeat[choice][seatNumber] = 0;
                }
                System.out.println("\n\nYOUR RESERVATION HAS BEEN CANCELLED !!");
                System.out.println("\n  PRESS 'ENTER' KEY TO CONTINUE ");
                scanner.nextLine(); // wait for user to press enter
                DisplaySeat(busSeat[choice]);
            } else if (c == 'n' || c == 'N') {
                System.out.println("\nYOUR RESERVATION CANCELLATION HAS BEEN DENIED");
            }
        } else {
            System.out.println("\nNOT FOUND!! ENTER THE CORRECT RESERVATION NUMBER");
        }
    }

    static void status() {
        int busNum;
        busLists();
        System.out.print("\n\nENTER YOUR BUS NUMBER : ");
        busNum = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (busNum <= 0 || busNum >= 10) {
            System.out.println("\n  PLEASE ENTER CORRECT BUS NUMBER !!");
            return;
        }
        DisplaySeat(busSeat[busNum]);
        System.out.println("PRESS 'ENTER' KEY TO CONTINUE ");
        scanner.nextLine(); // wait for user to press enter
    }

    static void DisplaySeat(int[] bus) {
        for (int i = 1; i <= 32; i++) {
            System.out.printf("%02d .", i);
            if (bus[i] == 0)
                System.out.print("EMPTY ");
            else
                System.out.print("BOOKED ");
            System.out.print("         ");
            if (i % 4 == 0)
                System.out.println();
        }
    }

    static int cost(BinarySearchTree r) {
        int buscost = r.PassnNo / 1000;
        switch (buscost % 3) {
            case 1:
                return 70;
            case 2:
                return 55;
            case 0:
                return 40;
            default:
                return 0;
        }
    }

    static BinarySearchTree insert(BinarySearchTree r, int custId) {
        if (r == null) {
            System.out.print("\n   ENTER THE PERSON NAME: ");
            String name = scanner.nextLine();
            return new BinarySearchTree(custId, name);
        } else {
            if (r.PassnNo > custId) {
                r.left = insert(r.left, custId);
            } else if (r.PassnNo < custId) {
                r.right = insert(r.right, custId);
            }
        }
        return r;
    }

    static void reservationInfoMenu(int randomNum) {
        int reservationNo;
        System.out.print("\n   ENTER YOUR RESERVATION NUMBER :");
        reservationNo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (randomNum == reservationNo) {
            int custID;
            System.out.print("\n   ENTER YOUR CUSTOMER ID :");
            custID = scanner.nextInt();
            scanner.nextLine(); // consume newline

            boolean custIDmatched = false;
            root = reservationInfo(root, custID, custIDmatched);
            if (!custIDmatched) {
                System.out.println("\n   ENTER CORRECT CUSTOMER ID!!");
            }
        } else {
            System.out.println("\n INVALID RESERVATION NUMBER PLEASE ENTER CORRECT RESERVATION NUMBER !!");
        }
    }

    static BinarySearchTree reservationInfo(BinarySearchTree r, int s, boolean custIDmatched) {
        if (r == null)
            return null;

        BinarySearchTree presentnode = r;
        while (presentnode != null) {
            if (presentnode.PassnNo == s) {
                custIDmatched = true;
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println("||              NAME: " + String.format("%10s", presentnode.name) + "                               ||");
                System.out.println("||              CUSTOMER ID: " + presentnode.PassnNo + "                              ||");
                System.out.println("||              BUS NUMBER: " + (presentnode.PassnNo / 1000) + "                                  ||");
                System.out.println("||              SEAT NUMBER: " + (presentnode.PassnNo % 100) + "                                 ||");
                System.out.println("||              TICKET COST: BDT." + cost(presentnode) + "                             ||");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("PRESS 'ENTER' KEY TO CONTINUE ");
                scanner.nextLine(); // wait for user to press enter
                return r;
            } else if (presentnode.PassnNo > s) {
                presentnode = presentnode.left;
            } else {
                presentnode = presentnode.right;
            }
        }
        return null;
    }
}
