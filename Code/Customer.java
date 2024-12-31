import java.util.Scanner;

public class Customer {
	private String name;
	private String phonenumber;
	private int district;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_MAGENTA = "\u001B[95m";

	public Customer() {
		super();
	}

	public Customer(String name, String phonenumber, int district) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public String toString() {
		return "Customer [name=" + name + ", phonenumber=" + phonenumber + ", district=" + district + "]";
	}

	public String toString2() {
		return "Customer [name=" + name + ", phonenumber=" + phonenumber + "]";
	}

	public Customer customerInfo(Scanner in) {
		System.out.println(ANSI_MAGENTA + "******** Customer's info ********" + ANSI_RESET);
		System.out.println("Name: ");
		String name = in.next();
		System.out.println("Phone number: ");
		String number = in.next();
		while (true) {
			try {
				long phoneNumber = Long.parseLong(number);
				if (phoneNumber >= 9999999) {
					break;
				} else {
					System.out.println(ANSI_RED + "Enter a valid Phone number: " + ANSI_RESET);
					number = in.next();
				}
			} catch (NumberFormatException e) {
				System.out.println(ANSI_RED + "Enter a valid Phone number: " + ANSI_RESET);
				number = in.next();
			}
		}
		System.out.println("Enter your district number: ");
		System.out.println(ANSI_BLUE + "1. Beirut \n2. Khalde \n3. Debbieh \n4. Zaarouriyeh" + ANSI_RESET);
		int district = in.nextInt();
		if (district != 1 && district != 2 && district != 3 && district != 4) {
			System.out.println(ANSI_BLUE + "1. Beirut \n2. Khalde \n3. Debbieh \n4. Zaarouriyeh" + ANSI_RESET);
			district = in.nextInt();
		}
		Customer c1 = new Customer(name, number, district);
		return c1;
	}
}
