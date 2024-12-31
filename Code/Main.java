import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[91m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_MAGENTA = "\u001B[95m";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		FoodItem f1 = new FoodItem("Baguette", 2, "French baguette", 12);
		FoodItem f2 = new FoodItem("Cookie", 1, "chocolate chips cookies", 36);
		FoodItem f3 = new FoodItem("Croissant", 3, "Classic croissant", 24);
		FoodItem f4 = new FoodItem("Cake", 9, "Moist chocolate Cake", 10);
		FoodItem f5 = new FoodItem("Eclaire", 5, "French choux pastry", 20);
		FoodItem f6 = new FoodItem("cinnamonrolls", 4, "with butter,sugar and cinnamon", 18);

		LinkedList menu = new LinkedList();
		menu.addItem(f1);
		menu.addItem(f2);
		menu.addItem(f3);
		menu.addItem(f4);
		menu.addItem(f5);
		menu.addItem(f6);

		Customer c1 = new Customer();
		LinkedList foodorders = new LinkedList();
		PQofOrders orders = new PQofOrders();
		Queue completed_orders = new Queue();
		Order customerorder = null;
		Queue shipped = new Queue();
		Vans vans = new Vans();
		vans.insertVan(1234);
		vans.insertVan(9876);
		vans.insertVan(4897);
		vans.insertVan(5236);
		double pending_bill = 0.0;
		double served_bill = 0.0;
		int quantity = 0;
		System.out.println(ANSI_MAGENTA + "******** WELCOME ********" + ANSI_RESET);
		System.out.println(ANSI_BLUE + "1. Re-Show list of options\n" + "2. View the Menu\n" + "3. Make An Order\n"
				+ "4. Search for a menu item by name\n" + "5. Search for your order \n" + "6. Delete your order \n"
				+ "7. Track order \n" + "8. Staff only section \n" + "0. Exit the program" + ANSI_RESET);
		int option = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
				option = in.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
				in.next();
			}
		}
		while (option != 0) {
			switch (option) {
			case 1:
				System.out.println(ANSI_MAGENTA + "******** WELCOME ********" + ANSI_RESET);
				System.out.println(ANSI_BLUE + "1. Re-Show list of options\n" + "2. View the Menu\n"
						+ "3. Make An Order\n" + "4. Search for a menu item by name\n" + "5. Search for your order \n"
						+ "6. Delete your order \n" + "7. Track order \n" + "8. Staff only section \n"
						+ "0. Exit the program" + ANSI_RESET);
				break;
			case 2:
				menu.displayMenu();
				break;
			case 3:
				c1 = new Customer();
				c1 = c1.customerInfo(in);

				foodorders = new LinkedList();

				System.out.println("How many items you want to order: ");
				int num = in.nextInt();
				double total_price = 0;
				while (num-- > 0) {
					System.out.println("Please enter the id of the item you want to order: ");
					int chosenid = in.nextInt();
					while (chosenid != 1 && chosenid != 2 && chosenid != 3 && chosenid != 4 && chosenid != 5
							&& chosenid != 6) {
						System.out.println(ANSI_RED + "Re-enter the id" + ANSI_RESET);
						chosenid = in.nextInt();
					}
					System.out.println("Enter the quantity of item: ");
					quantity = in.nextInt();

					total_price = orders.placeorder(chosenid, menu, foodorders, quantity, total_price);
				}
				customerorder = new Order(foodorders, c1);
				customerorder.price = total_price;
				foodorders.display3(customerorder.id);
				System.out.println(c1.toString());

				orders.insert_order(customerorder, c1.getDistrict());
				Van v = (Van) vans.top();
				v.setPriorityQueue(orders);
				System.out.println(ANSI_YELLOW + "Total price: " + total_price + "$ + 3$ delivery fee = "
						+ (total_price + 3) + "$ " + ANSI_RESET);
				pending_bill += total_price;
				System.out.println(ANSI_YELLOW + "Thank you for visiting our bakery! ;)" + ANSI_RESET);
				break;
			case 4:
				System.out.println("Enter the menu item you want to search for: ");
				String name = in.next();
				menu.searchForMenuItemByName(name);
				break;
			case 5:
				System.out.println("Enter your order's ID: ");
				int idd = in.nextInt();
				orders.search_by_ID(idd);
				break;
			case 6:
				System.out.println("Enter the ID of the order you want to delete: ");
				int id = in.nextInt();
				Order o = orders.searchorder_by_ID(id);
				if (orders.delete_by_ID(id)) {
					System.out.println(ANSI_GREEN + "Your order has been successfully deleted." + ANSI_RESET);
					pending_bill -= o.price;
					break;
				} else
					break;
			case 7:
				System.out.println("Enter the id of the item you want to track: ");
				int orderid = in.nextInt();
				if (shipped.isEmpty()) {
					System.out.println(ANSI_RED + "The order is still in the proccess!" + ANSI_RESET);
					break;
				}
				Order orderr = shipped.searchorder_by_ID(orderid);
				if (orderr.orderShipped(orderid, shipped))
					System.out.println(ANSI_GREEN + "The order is in his way to your door step!" + ANSI_RESET);
				else
					System.out.println(ANSI_RED + "The order is still in the proccess!" + ANSI_RESET);
				break;

			case 8:
				System.out.println("Please enter password for staff operation: ");
				String pass = in.next();
				while (!pass.equals("1234") && !pass.equals("madandan")) {
					System.out.println(ANSI_RED
							+ "Incorrect Password! please reenter the correct password.\nForget Password? daber rasak"
							+ ANSI_RESET);
					pass = in.next();
				}
				//
				System.out.println(ANSI_BLUE + "1. Admin \n" + "2. Driver \n" + "0. Exit program" + ANSI_RESET);
				int opt = 0;
				boolean valid = false;
				while (!valid) {
					try {
						System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
						opt = in.nextInt();
						valid = true;
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
						in.next();
					}
				}
				while (opt != 0) {
					switch (opt) {
					case 1:
						System.out.println(ANSI_BLUE + "1. Re-show Admin list\n" + "2. View Pending Orders \n"
								+ "3. Ship all orders \n" + "4. View completed orders \n"
								+ "5. Display total bill of Pending Orders \n"
								+ "6. Display total Earnings of Served Orders \n" + "7. Add a new item to the menu \n"
								+ "8. Delete an item from the menu \n" + "9. Display orders of a specific region \n"
								+ "0. Exit Admin list" + ANSI_RESET);
						int op = 0;
						boolean validd = false;
						while (!validd) {
							try {
								System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
								op = in.nextInt();
								validd = true;
							} catch (InputMismatchException e) {
								System.out.println(
										ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
								in.next();
							}
						}
						while (op != 0) {
							switch (op) {
							case 1:
								System.out.println(ANSI_BLUE + "1. Re-show Admin list\n" + "2. View Pending Orders \n"
										+ "3. Ship all orders \n" + "4. View completed orders \n"
										+ "5. Display total bill of Pending Orders \n"
										+ "6. Display total Earnings of Served Orders \n"
										+ "7. Add a new item to the menu \n" + "8. Delete an item from the menu \n"
										+ "9. Display orders of a specific region \n" + "0. Exit Admin list"
										+ ANSI_RESET);
								break;
							case 2:
								System.out.println("Pending Orders Display: ");
								orders.pqueuedisplay();
								break;
							case 3:
								shipped = vans.shipOrder(orders);
								break;
							case 4:
								completed_orders.Qdisplay();
								break;
							case 5:
								System.out.println(
										ANSI_GREEN + "Total bill of pending orders: " + pending_bill + ANSI_RESET);
								break;
							case 6:
								System.out.println(
										ANSI_GREEN + "Total earnings of served orders: " + served_bill + ANSI_RESET);
								break;
							case 7:
								FoodItem fooditem = new FoodItem();
								fooditem = fooditem.iteminfo(in);
								menu.addItem(fooditem);
								System.out.println(ANSI_GREEN + "The item " + fooditem.name
										+ " has been added successfully." + ANSI_RESET);
								break;
							case 8:
								System.out.println("Enter the id of the menu item you want to delete: ");
								int menuid = in.nextInt();
								if (menu.removeItem(menuid))
									System.out.println(
											ANSI_GREEN + "The item has been deleted successfully." + ANSI_RESET);
								else
									System.out.println(ANSI_RED + "The item was not found." + ANSI_RESET);
								break;
							case 9:
								System.out.println("Enter your district number: ");
								System.out.println(
										ANSI_BLUE + "1. Beirut \n2. Khalde \n3. Debbieh \n4. Zaarouriyeh" + ANSI_RESET);
								int dist_search = in.nextInt();
								if (dist_search == 1) {
									System.out.println("All orders of Beirut: ");
								} else if (dist_search == 2) {
									System.out.println("All orders of Khalde: ");
								} else if (dist_search == 3) {
									System.out.println("All orders of Debbieh: ");
								} else if (dist_search == 4) {
									System.out.println("All orders of Zaarouriyeh: ");
								}
								orders.orderdisplay(dist_search);
								break;
							default:
								System.out.println(ANSI_RED + "Invalid character" + ANSI_RESET);
								break;
							}
							op = 0;
							validd = false;
							while (!validd) {
								try {
									System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
									op = in.nextInt();
									validd = true;
								} catch (InputMismatchException e) {
									System.out.println(
											ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
									in.next();
								}
							}
						}

						break;
					case 2:
						System.out.println(ANSI_BLUE + "1. Re-show Driver list\n" + "2. View Shipped Orders \n"
								+ "3. Add a new van \n" + "4. Delete an existing van \n"
								+ "5. Search for a specific van \n" + "6. Display all vans \n"
								+ "7. Complete an Order \n" + "0. Exit driver list" + ANSI_RESET);

						int opp = 0;
						boolean validdd = false;
						while (!validdd) {
							try {
								System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
								opp = in.nextInt();
								validdd = true;
							} catch (InputMismatchException e) {
								System.out.println(
										ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
								in.next();
							}
						}
						while (opp != 0) {
							switch (opp) {
							case 1:
								System.out.println(ANSI_BLUE + "1. Re-show Driver list\n" + "2. View Shipped Orders \n"
										+ "3. Add a new van \n" + "4. Delete an existing van \n"
										+ "5. Search for a specific van \n" + "6. Display all vans \n"
										+ "7. Complete an Order \n" + "0. Exit driver list" + ANSI_RESET);
								break;
							case 2:
								System.out.println("Shipped orders Display:");
								shipped.Qdisplay();
								break;
							case 3:
								System.out.println("Enter the van plate number you want to insert:");
								int plate_number = in.nextInt();
								vans.insertVan(plate_number);
								System.out.println(ANSI_GREEN + "The van " + plate_number
										+ " has been added successfully." + ANSI_RESET);
								break;
							case 4:
								System.out.println("Enter the van plate number you want to delete:");
								int platenumber = in.nextInt();
								int vanidd = vans.searchVan(platenumber);
								if (vanidd != -1)
									System.out.println(ANSI_GREEN + "Van " + vanidd + " was deleted!" + ANSI_RESET);
								else
									System.out.println(ANSI_RED + "Van " + vanidd + " is not found!" + ANSI_RESET);
								vans.deleteVan(platenumber);

								break;
							case 5:
								System.out.println("Enter the van plate number you want to search for:");
								int platenum = in.nextInt();
								int vanid = vans.searchVan(platenum);
								if (vanid != -1)
									System.out.println(ANSI_GREEN + "Van " + vanid + " was found!" + ANSI_RESET);
								else
									System.out.println(ANSI_RED + "Van of plate number " + platenum + " is not found!"
											+ ANSI_RESET);
								break;
							case 6:
								vans.display();
								break;
							case 7:
								System.out.println("Enter the ID of the order you want to mark as completed");
								int orderID = in.nextInt();
								Order orderrr = shipped.searchorder_by_ID(orderID);

								if (orderrr != null) {
									Order completedOrder = customerorder.completeOrder(orderID, shipped);
									served_bill += orderrr.price;
									pending_bill -= orderrr.price;
									if (completedOrder != null) {
										completed_orders.enqueue(completedOrder);
										System.out.println(ANSI_GREEN + "The order " + orderID
												+ " has been marked as completed." + ANSI_RESET);
									} else {
										System.out.println(
												ANSI_RED + "Unable to mark the order as completed." + ANSI_RESET);
									}
								} else {
									System.out.println(ANSI_RED + "Order with ID " + orderID
											+ " not found in the shipped queue." + ANSI_RESET);
								}
								break;
							default:
								System.out.println(ANSI_RED + "Invalid character" + ANSI_RESET);
								break;
							}
							opp = 0;
							validdd = false;
							while (!validdd) {
								try {
									System.out.println(ANSI_MAGENTA + "choose an integer: " + ANSI_RESET);
									opp = in.nextInt();
									validdd = true;
								} catch (InputMismatchException e) {
									System.out.println(
											ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
									in.next();
								}
							}
						} // while driver
						break;
					default: // default admin driver
						System.out.println(ANSI_RED + "Invalid character" + ANSI_RESET);
						break;
					}
					opt = 0;
					valid = false;
					while (!valid) {
						try {
							System.out.println(
									ANSI_BLUE + "1. Admin \n" + "2. Driver \n" + "0. Exit program" + ANSI_RESET);
							opt = in.nextInt();
							valid = true;
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
							in.next();
						}
					}
				}
				break;
			default:
				System.out.println(ANSI_RED + "Invalid character" + ANSI_RESET);
				break;
			} // user switch
			option = 0;
			validInput = false;
			while (!validInput) {
				try {
					System.out.println(ANSI_MAGENTA + "choose a number from the list above: " + ANSI_RESET);
					option = in.nextInt();
					validInput = true;
				} catch (InputMismatchException e) {
					System.out.println(ANSI_RED + "Invalid input! Please enter a valid integer." + ANSI_RESET);
					in.next();
				}
			}
		} // user while

	}

}
