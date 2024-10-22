import java.util.*;

public class Demo {

	static String userName = "vidura";
	static String password = "Vidura999@";
	static String[][] details = new String[0][2];
	static String[][] itemDetails = new String[0][6];
	static String[] category = new String[0]; 
	
	
	public static void rankUnitPrice() {
        Scanner input = new Scanner(System.in);
        System.out.println();

        if (itemDetails.length == 0) {
            System.out.println("No items to display.");
            return;
        }

        String[][] sortedItems = new String[itemDetails.length][6];

        for (int i = 0; i < itemDetails.length; i++) {
            for (int j = 0; j < itemDetails[i].length; j++) {
                sortedItems[i][j] = itemDetails[i][j];
            }
        }

        for (int i = 0; i < sortedItems.length - 1; i++) {
            for (int j = 0; j < sortedItems.length - i - 1; j++) {
                if (Double.parseDouble(sortedItems[j][3]) > Double.parseDouble(sortedItems[j + 1][3])) {
                    String[] temp = sortedItems[j];
                    sortedItems[j] = sortedItems[j + 1];
                    sortedItems[j + 1] = temp;
                }
            }
        }

		System.out.printf("+-------------+-------------+----------------------+------------+-------------+-------------+%n");
		System.out.printf("| %-11s | %-11s | %-20s | %-10s | %-10s | %-11s |%n",
			"SUPPLIER ID",
			"ITEM CODE",
			"DESCRIPTION",
			"UNIT PRICE",
			"QTY ON HAND",
			"CATEGORY");
		System.out.printf("+-------------+-------------+----------------------+------------+-------------+-------------+%n");

		for (String[] item : sortedItems) {
			System.out.printf("| %-11s | %-11s | %-20s | %-10s | %-11s | %-11s |%n",
				item[1],
				item[0],
				item[2],
				item[3],
				item[4],
				item[5]);
			System.out.printf("+-------------+-------------+----------------------+------------+-------------+-------------+%n");
		}


        System.out.print("Do you want to go to the stock manage page? (Y/N): ");
        boolean validInput = false;

        while (!validInput) {
            char c = input.next().charAt(0);
            switch (c) {
                case 'Y', 'y' -> {
                    System.out.print("\033c");
                    stockManage();
                    validInput = true;
                }
                case 'N', 'n' -> {
                    System.out.print("\033c");
                    supplierManage();
                    validInput = true;
                }
                default -> {
                    System.out.print("Invalid option! Please enter Y or N: ");
                }
            }
        }
    }
	
	public static void viewItems() {
		Scanner input = new Scanner(System.in);
    
		if (itemDetails.length == 0) {
			System.out.println("No items to display.");
			return;
		}

		for (String cat : category) {
			boolean hasItems = false;

			System.out.println("\n" +cat + ": ");
			System.out.println("+-------------+-------------+----------------------+------------+-------------+-------------+");
			System.out.printf("| %-11s | %-11s | %-20s | %-10s | %-11s | %-11s |%n",
				"SUPPLIER ID",
				"ITEM CODE",
				"DESCRIPTION",
				"UNIT PRICE",
				"QTY ON HAND",
				"CATEGORY");
			System.out.println("+-------------+-------------+----------------------+------------+-------------+-------------+");
			for (String[] item : itemDetails) {
				if (item[5].equals(cat)) {
					hasItems = true;
					System.out.printf("| %-11s | %-11s | %-20s | %-10s | %-11s | %-11s |%n",
						item[1],
						item[0],
						item[2],
						item[3],
						item[4],
						item[5]);
					System.out.println("+-------------+-------------+----------------------+------------+-------------+-------------+");	
				}
			}


        if (!hasItems) {
            System.out.println("No items in this category");	
        }

		System.out.println();
		}

		System.out.print("Do you want to go stock manage page? (Y/N): ");
		boolean validInput = false;

		while (!validInput) {
			char c = input.next().charAt(0);
			switch (c) {
				case 'Y', 'y' -> {
					System.out.print("\033c");
					stockManage();
					validInput = true;
				}
				case 'N', 'n' -> {
					System.out.print("\033c");
					stockManageSystem();
					validInput = true;
				}
				default -> {
					System.out.print("Invalid option! Please enter Y or N: ");
				}
			}
		}
	}

    public static String tableCenter(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
	
	public static void displayItemsForSupplier(String supplierId) {
		 Scanner input = new Scanner(System.in);

        boolean itemsFound = false;

        for (int i = 0; i < itemDetails.length; i++) {
            if (supplierId.equals(itemDetails[i][1])) {
                itemsFound = true;
                break;
            }
        }

        if (itemsFound) {
            System.out.println("+-------------+-------------+--------------------+-----------+------------------+");
            System.out.printf("| %-11s | %-11s | %-16s | %-11s | %-16s |%n",
                    "ITEM CODE",
                    "DESCRIPTION",
                    "UNIT PRICE",
                    "QTY ON HAND",
                    "CATEGORY");
            System.out.println("+-------------+-------------+--------------------+-----------+------------------+");

            for (int i = 0; i < itemDetails.length; i++) {
                if (supplierId.equals(itemDetails[i][1])) {
                    String itemCategory = itemDetails[i][5];

                    System.out.printf("| %-11s | %-11s | %-16s | %-11s | %-16s |%n",
                            itemDetails[i][0],
                            itemDetails[i][2],
                            itemDetails[i][3],
                            itemDetails[i][4],
                            itemCategory);
                    System.out.println("+-------------+-------------+--------------------+-----------+------------------+");
                }
            }
        } else {
            System.out.println("No items found for the given supplier ID.");
        }

        System.out.print("Search Successfully! Do you want another search (Y/N) ");
        boolean validInput = false;

        while (!validInput) {
            char c = input.next().charAt(0);
            switch (c) {
                case 'Y', 'y' -> {
                    searchSupplierWise();
                    validInput = true;
                }
                case 'N', 'n' -> {
                    System.out.print("\033c");
                    stockManage();
                    validInput = true;
                }
                default -> {
                    System.out.print("Invalid option! Please enter Y or N: ");
                }
            }
        }
        
    }

    public static void inputSupplierName(String supplierId) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter supplier Name: ");
        String supplierName = input.nextLine();
        
        System.out.println();

        for (int i = 0; i < details.length; i++) {
            if (supplierId.equals(details[i][0]) && supplierName.equals(details[i][1])) {
                displayItemsForSupplier(supplierId);
                return;
            }
        }

        System.out.print("Can't find supplier Name. Try again!\n");
        inputSupplierName(supplierId);
    }

    public static void searchSupplierWise() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter supplier Id: ");
        String supplierId = input.nextLine();

        boolean supplierFound = false;

        for (int i = 0; i < details.length; i++) {
            if (supplierId.equals(details[i][0])) {
                supplierFound = true;
                inputSupplierName(supplierId);
                return;
            }
        }

        if (!supplierFound) {
            System.out.print("Can't find supplier Id. Try again!\n");
            searchSupplierWise();
        }
    }
	
	public static String[][] addItemDetailsArr(String[][] itemsDetails, String supplierId, String itemCode, String desc, String price, String qty, String category) {
        String[][] newArr = new String[itemsDetails.length + 1][6];

        for (int i = 0; i < itemsDetails.length; i++) {
            for (int j = 0; j < 6; j++) {
                newArr[i][j] = itemsDetails[i][j];
            }
        }

        newArr[itemsDetails.length][0] = itemCode;
        newArr[itemsDetails.length][1] = supplierId;
        newArr[itemsDetails.length][2] = desc;
        newArr[itemsDetails.length][3] = price;
        newArr[itemsDetails.length][4] = qty;
        newArr[itemsDetails.length][5] = category;

        return newArr;
    }

    public static void showCategoryList() {
		
		System.out.printf("%n+----------------------+--------------------+%n");
		System.out.printf("| %-20s | %-18s |%n", tableCenter(20, "#"), tableCenter(18, "CATEGORY NAME"));
		System.out.printf("+----------------------+--------------------+%n");
		
        for (int i = 0; i < category.length; i++) {
            int number = i + 1;
            String name = category[i];

            int namePadding = (18 - name.length()) / 2;
            String namePadded = String.format("%" + (namePadding + name.length()) + "s", name);
            namePadded = String.format("%-" + 18 + "s", namePadded);

            System.out.printf("| %-20d | %-18s |%n", number, namePadded);
        }

        System.out.printf("+----------------------+--------------------+%n");
    }

    public static void showSupplierList() {
		
		System.out.printf("%n+-------+----------------------+--------------------+%n");
        System.out.printf("| %-7s | %-20s | %-16s |%n", tableCenter(7, "#"), tableCenter(20, "SUPPLIER ID"), tableCenter(16, "SUPPLIER NAME"));
        System.out.printf("+-------+----------------------+--------------------+%n");

        for (int i = 0; i < details.length; i++) {
            int number = i + 1;
            String id = details[i][0];
            String name = details[i][1];

            System.out.printf("| %-7d | %-20s | %-16s |%n", number, id, name);
        }

        System.out.printf("+-------+----------------------+--------------------+%n");
    }

    public static String[][] itemGrow(String[][] oldArr) {
        String[][] newArr = new String[oldArr.length + 1][1];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i][0] = oldArr[i][0];
        }
        return newArr;
    }

    public static String[][] itemAr(String[][] ar, String id) {
        ar = itemGrow(ar);
        ar[ar.length - 1][0] = id;
        return ar;
    }

    public static void addItem() {
        Scanner input = new Scanner(System.in);

        boolean hasCategories = category.length > 0;
        boolean hasSupplierList = details.length > 0;

        if (!hasCategories || !hasSupplierList) {
            while (true) {
                if (!hasCategories) {
                    System.out.println("OOPS! It seems that you don't have any categories in the system.");
                    System.out.print("Do you want to add a new category? (Y/N) : ");
                    char option = input.next().charAt(0);
                    input.nextLine();

                    System.out.println();

                    if (option == 'Y' || option == 'y') {
						System.out.print("\033c");
                        addCategory();
                        hasCategories = true;
                    } else if (option == 'N' || option == 'n') {
						System.out.print("\033c");
                        stockManage();
                        return;
                    } else {
                        System.out.println("Invalid Input " + option + "! Please enter (Y/N) ");
                        continue;
                    }
                }
                if (!hasSupplierList) {
                    System.out.println("OOPS! It seems that you don't have any suppliers in the system.");
                    System.out.print("Do you want to add a new supplier? (Y/N) : \n");
                    char option = input.next().charAt(0);
                    input.nextLine();

                    System.out.println();

                    if (option == 'Y' || option == 'y') {
						System.out.print("\033c");
                        addSupplier();
                        hasSupplierList = true;
                    } else if (option == 'N' || option == 'n') {
						System.out.print("\033c");
                        stockManage();
                        return;
                    } else {
                        System.out.println("Invalid Input " + option + "! Please enter (Y/N) ");
                        continue;
                    }
                }
                if (hasCategories && hasSupplierList) {
                    break;
                }
            }
        }

        System.out.print("\nItem Code: ");
        String itemCode = input.nextLine();

        for (String[] item : itemDetails) {
            if (itemCode.equals(item[0])) {
                System.out.println("Item code already exists. Please try another item code.");
                addItem();
                return;
            }
        }

        showSupplierList();

        System.out.print("Enter the supplier number > ");
        int supplierNumIndex = input.nextInt() - 1;
        String supplierId = details[supplierNumIndex][0];

        showCategoryList();

        System.out.print("Enter the category number > ");
        int categoryNumIndex = input.nextInt() - 1;
        input.nextLine();
        String categoryName = category[categoryNumIndex];
        
        String desc = getInput(input, "\nDescription > ");
        String unitPrice = getInput(input, "Unit price > ");
        String qty = getInput(input, "Qty on hand > ");

        itemDetails = addItemDetailsArr(itemDetails, supplierId, itemCode, desc, unitPrice, qty, categoryName);
 
        System.out.print("added successfully! Do you want to add another Item (Y/N) ? ");
		boolean validInput = false;

		while (!validInput) {
			char c = input.next().charAt(0);
			switch (c) {
				case 'Y', 'y' -> {
					addItem();
					validInput = true;
				}
				case 'N', 'n' -> {
					System.out.print("\033c");
					stockManage();
					validInput = true;
				}
				default -> {
					System.out.print("Invalid option! Please enter Y or N: ");
				}
			}
		}
    }
    
	public static String getInput(Scanner input, String prompt) {
		String value = "";
		while (value.length() == 0) {
			System.out.print(prompt);
			value = input.nextLine();
			if (value.length() == 0) {
				System.out.println("Error: Input cannot be empty. Please enter a value.");
			}
		}
		return value;
	}


    
	public static void updateCategory() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nCategory name: ");
        String name = input.nextLine();
        boolean found = false;

        for (int i = 0; i < category.length; i++) {
            if (name.equals(category[i])) {
                found = true;

                System.out.print("\nEnter the new category name: ");
                category[i] = input.nextLine();

                System.out.print("Updated successfully! Do you want to update another category? (Y/N): ");
                boolean validInput = false;

				while (!validInput) {
					char c = input.next().charAt(0);
					switch (c) {
						case 'Y', 'y' -> {
							updateCategory();
							validInput = true;
						}
						case 'N', 'n' -> {
							System.out.print("\033c");
							manageItemCategories();
							validInput = true;
						}
						default -> {
							System.out.print("Invalid option! Please enter Y or N: ");
						}
					}
				}
            }
        }

        if (!found) {
            System.out.println("Can't find category. Try again!");
            updateCategory();
        }
    }

    public static String[] removeCategory(int index) {
        String[] newArr = new String[category.length - 1];
        for (int i = 0, j = 0; i < category.length; i++) {
            if (i != index) {
                newArr[j++] = category[i];
            }
        }
        return newArr;
    }
    
	public static void deleteCategory() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nCategory name: ");
        String name = input.nextLine();

        boolean found = false;

        for (int i = 0; i < category.length; i++) {
            if (name.equals(category[i])) {
                found = true;
                category = removeCategory(i);
                System.out.print("Deleted successfully! Do you want to delete another category? (Y/N): ");
                boolean validInput = false;

				while (!validInput) {
					char c = input.next().charAt(0);
					switch (c) {
						case 'Y', 'y' -> {
							deleteCategory();
							validInput = true;
						}
						case 'N', 'n' -> {
							System.out.print("\033c");
							manageItemCategories();
							validInput = true;
						}
						default -> {
							System.out.print("Invalid option! Please enter Y or N: ");
						}
					}
				}
           }
       }

        if (!found) {
            System.out.println("Can't find category. Try again!");
            deleteCategory();
        }
    }
    
	public static String[] growNew(String[] oldArr) {
        String[] newArr = new String[oldArr.length + 1];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }

    public static String[] addGrow(String[] ar, String v) {
        ar = growNew(ar);
        ar[ar.length - 1] = v;
        return ar;
    }

    public static void addCategory() {
        Scanner input = new Scanner(System.in);
        boolean addingCategories = true;

        while (addingCategories) {
            System.out.print("\nEnter the new item category: ");
            String inputCategory = input.nextLine();

            boolean exists = false;
            for (String name : category) {
                if (name.equals(inputCategory)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Category already exists! Try another category.");
                addCategory();
            } else {
                category = addGrow(category, inputCategory);
                System.out.println("Added successfully!");
            }

            System.out.print("\nDo you want to add another category? (Y/N): ");
            boolean validInput = false;

			while (!validInput) {
				char c = input.next().charAt(0);
				switch (c) {
					case 'Y', 'y' -> {
						addCategory();
						validInput = true;
					}
					case 'N', 'n' -> {
						System.out.print("\033c");
						manageItemCategories();
						validInput = true;
					}
					default -> {
						System.out.print("Invalid option! Please enter Y or N: ");
					}
				}
			}
        }
    }
    
	public static void manageItemCategories(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|                      MANAGE ITEM CATEGORIES                           |");
		System.out.println("+-----------------------------------------------------------------------+");
		
		System.out.println("\n[1] Add New Item Category\t\t[2] Delete Item Category\n[3] Update Item Category\t\t[4] Stock Management");
		
		System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();
        
        switch(num){
			case 1 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
				System.out.println("|                        ADD ITEM CATEGORY                              |");
				System.out.println("+-----------------------------------------------------------------------+");
				addCategory();
			}
			case 2 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                       DELETE ITEM CATEGORY                            |");
                System.out.println("+-----------------------------------------------------------------------+");
                deleteCategory();
			}
			case 3 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                      UPDATE ITEM CATEGORY                             |");
                System.out.println("+-----------------------------------------------------------------------+");
                updateCategory();
			}
			case 4 -> {
				System.out.print("\033c");
				stockManageSystem();
			}
		}
	}
	
	public static void stockManage(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|                         STOCK MANAGEMENT                              |");
		System.out.println("+-----------------------------------------------------------------------+");
		
		System.out.println("\n[1] Manage Item Categories\t\t[2] Add Item\n[3] Get Items Supplier Wise\t\t[4] View Items\n[5] Rank Items Per Unit Price\t\t[6] Home Page");
		
		System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();
        
        switch(num){
			case 1 -> {
				System.out.print("\033c");
				manageItemCategories();
			}
			case 2 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
				System.out.println("|                              ADD ITEM                                 |");
				System.out.println("+-----------------------------------------------------------------------+");
				addItem();
			}
			case 3 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
				System.out.println("|                           SEARCH SUPPLIER                             |");
				System.out.println("+-----------------------------------------------------------------------+");
				searchSupplierWise();
			}
			case 4 ->{
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
				System.out.println("|                             VIEW ITEMS                                |");
				System.out.println("+-----------------------------------------------------------------------+");
				viewItems();
			}
			case 5 ->{
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
				System.out.println("|                          RANKED UNIT PRICE                            |");
				System.out.println("+-----------------------------------------------------------------------+");
				rankUnitPrice();
			}
		}
		
	}
	
	public static void searchSupplier(){
		Scanner input = new Scanner(System.in);

        System.out.print("\nSupplier ID: ");
        String id = input.nextLine();
        boolean found = false;

        for (int i = 0; i < details.length; i++) {
            if (id.equals(details[i][0])) {
                found = true;
                System.out.println("Supplier name: " + details[i][1]);

                System.out.print("Searched successfully! Do you want to search for another supplier? (Y/N): ");
                boolean validInput = false;

				while (!validInput) {
					char c = input.next().charAt(0);
					switch (c) {
						case 'Y', 'y' -> {
							searchSupplier();
							validInput = true;
						}
						case 'N', 'n' -> {
							System.out.print("\033c");
							supplierManage();
							validInput = true;
						}
						default -> {
							System.out.print("Invalid option! Please enter Y or N: ");
						}
					}
				}
            }
        }

        if (!found) {
            System.out.println("Can't find supplier id. Try again!");
            searchSupplier();
        }
	}
	
	public static void viewSupplier(){
		Scanner input = new Scanner(System.in);
		
		System.out.printf("%n+----------------------+--------------------+%n");
		System.out.printf("| %-20s | %-18s |%n", tableCenter(20, "SUPPLIER ID"), tableCenter(18, "SUPPLIER NAME"));
		System.out.printf("+----------------------+--------------------+%n");

        for (int i = 0; i < details.length; i++) {
            String id = details[i][0];
            String name = details[i][1];

            System.out.printf("| %-20s | %-18s |%n", id, name);
        }

        System.out.printf("+----------------------+--------------------+%n");
        
        System.out.print("Do you want to go supplier manage page? (Y/N): ");
		boolean validInput = false;

		while (!validInput) {
		char c = input.next().charAt(0);
			switch (c) {
				case 'Y', 'y' -> {
					System.out.print("\033c");
					supplierManage();
					validInput = true;
				}
				case 'N', 'n' -> {
					System.out.print("\033c");
					System.exit(0);
					validInput = true;
				}
				default -> {
					System.out.print("Invalid option! Please enter Y or N: ");
				}
			}
		}
	}
	
	public static void deleteSupplier(String[][] details) {
        Scanner input = new Scanner(System.in);

        System.out.print("\nSupplier ID: ");
        String id = input.nextLine();
        boolean found = false;

        for (int i = 0; i < details.length; i++) {
            if (id.equals(details[i][0])) {
                found = true;
                removeSupplier(i);
                System.out.print("Deleted successfully! Do you want to delete another supplier? (Y/N): ");
                boolean validInput = false;

				while (!validInput) {
				char c = input.next().charAt(0);
					switch (c) {
						case 'Y', 'y' -> {
							deleteSupplier(details);
							validInput = true;
						}
						case 'N', 'n' -> {
							System.out.print("\033c");
							supplierManage();
							validInput = true;
						}
						default -> {
							System.out.print("Invalid option! Please enter Y or N: ");
						}
					}
				}
            }
        }

        if (!found) {
            System.out.println("Can't find supplier id. Try again!");
			deleteSupplier(details);
        }
    }
    
	public static void removeSupplier(int index) {
        String[][] newArr = new String[details.length - 1][2];
        for (int i = 0, j = 0; i < details.length; i++) {
            if (i != index) {
                newArr[j++] = details[i];
            }
        }
        details = newArr;
    }
    
	public static void updateSupplier() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nSupplier ID: ");
        String id = input.nextLine();
        boolean found = false;

        for (int i = 0; i < details.length; i++) {
            if (id.equals(details[i][0])) {
                found = true;
                System.out.println("Supplier name: " + details[i][1]);

                System.out.print("\nEnter the new supplier name: ");
                details[i][1] = input.nextLine();

                System.out.print("Updated successfully! Do you want to update another supplier? (Y/N): ");
                boolean validInput = false;

				while (!validInput) {
				char c = input.next().charAt(0);
					switch (c) {
						case 'Y', 'y' -> {
							updateSupplier();
							validInput = true;
						}
						case 'N', 'n' -> {
							System.out.print("\033c");
							supplierManage();
							validInput = true;
						}
						default -> {
							System.out.print("Invalid option! Please enter Y or N: ");
						}
					}
				}
            }
        }

        if (!found) {
            System.out.println("Can't find supplier id. Try again!");
            updateSupplier();
        }
    }
    
	public static String[][] grow(String[][] oldArr) {
        String[][] newArr = new String[oldArr.length + 1][2];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i][0] = oldArr[i][0];
            newArr[i][1] = oldArr[i][1];
        }
        return newArr;
    }

    public static String[][] add(String[][] ar, String id) {
        ar = grow(ar);
        ar[ar.length - 1][0] = id;
        inputName(ar, ar.length - 1);
        return ar;
    }

    public static void inputName(String[][] ar, int index) {
        Scanner input = new Scanner(System.in);

        System.out.print("Supplier Name: ");
        String name = input.nextLine();

        ar[index][1] = name;
    }

    public static String[][] inputId() {
        Scanner input = new Scanner(System.in);

        System.out.print("\nSupplier ID: ");
        String id = input.nextLine();

        for (String[] supplier : details) {
            if (id.equals(supplier[0])) {
                System.out.println("ID already exists: try another supplier id!");
                return inputId();
            }
        }
        return add(details, id);
    }
    
	public static void addSupplier() {
        Scanner input = new Scanner(System.in);
        
        details = inputId();

        System.out.print("Supplier added successfully! Do you want to add another supplier? (Y/N): ");
        boolean validInput = false;

		while (!validInput) {
		char c = input.next().charAt(0);
			switch (c) {
				case 'Y', 'y' -> {
					addSupplier();
					validInput = true;
				}
				case 'N', 'n' -> {
					System.out.print("\033c");
					supplierManage();
					validInput = true;
				}
				default -> {
					System.out.print("Invalid option! Please enter Y or N: ");
				}
			}
		}
    }
    
	public static void supplierManage() {
        Scanner input = new Scanner(System.in);

        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                        SUPPLIER MANAGE                                |");
        System.out.println("+-----------------------------------------------------------------------+");

        System.out.println("\n[1] Add Supplier\t[2] Update Supplier\n[3] Delete Supplier\t[4] View Suppliers\n[5] Search Supplier\t[6] Home Page");

        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();

        switch (num) {
            case 1 -> {
                System.out.print("\033c");
                System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                        ADD SUPPLIER                                   |");
                System.out.println("+-----------------------------------------------------------------------+");
                addSupplier();
            }
            case 2 -> {
                System.out.print("\033c");
                System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                        UPDATE SUPPLIER                                |");
                System.out.println("+-----------------------------------------------------------------------+");
                updateSupplier();
            }
            case 3 -> {
                System.out.print("\033c");
                System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                        DELETE SUPPLIER                                |");
                System.out.println("+-----------------------------------------------------------------------+");
                deleteSupplier(details);

            }
            case 4 -> {
				System.out.print("\033c");
                System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                          VIEW SUPPLIER                                |");
                System.out.println("+-----------------------------------------------------------------------+");
                viewSupplier();
			}
			case 5 -> {
				System.out.print("\033c");
				System.out.println("+-----------------------------------------------------------------------+");
                System.out.println("|                         SEARCH SUPPLIER                               |");
                System.out.println("+-----------------------------------------------------------------------+");
                searchSupplier();
			}
            case 6 -> {
                System.out.print("\033c");
                stockManageSystem();
            }
            default -> {
                System.out.println("Invalid option! Returning to main menu.");
                supplierManage();
            }
        }
    }
    
	public static void verify() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\nPlease enter the user name to verify it's you: ");
            String userInput = input.nextLine();

            if (userName.equals(userInput)) {
                System.out.println("Hey " + userInput);
                break;
            } else {
                System.out.println("User name is invalid, please try again!");
            }
        }

        while (true) {
            System.out.print("\nEnter your current password: ");
            String userInput = input.nextLine();
            if (password.equals(userInput)) {
                break;
            } else {
                System.out.println("Password is incorrect, please try again!");
            }
        }
    }
    
	public static void changePass() {
        Scanner input = new Scanner(System.in);

        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                        CHANGE THE CREDENTIALS                         |");
        System.out.println("+-----------------------------------------------------------------------+");

        verify();

        System.out.print("\nEnter your new password: ");
        password = input.nextLine();

        System.out.print("Password changed successfully! Do you want to go home page (Y/N): ");
        boolean validInput = false;

		while (!validInput) {
		char c = input.next().charAt(0);
			switch (c) {
				case 'Y', 'y' -> {
					System.out.print("\033c");
					stockManageSystem();
					validInput = true;
				}
				case 'N', 'n' -> {
					System.out.print("\033c");
					logInPage();
					stockManageSystem();
					validInput = true;
				}
				default -> {
					System.out.print("Invalid option! Please enter Y or N: ");
				}
			}
		}
    }
    
	public static void stockManageSystem() {
        Scanner input = new Scanner(System.in);

        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|              WELCOME TO IJSE STOCK MANAGEMENT SYSTEM                  |");
        System.out.println("+-----------------------------------------------------------------------+");

        System.out.println("\n[1] Change the Credentials\t[2] Supplier Manage\n[3] Stock Manage\t\t[4] Log Out\n[5] Exit the system");

        System.out.print("\nEnter an option to continue > ");
        int num = input.nextInt();

        switch (num) {
            case 1 -> {
                System.out.print("\033c");
                changePass();
            }
            case 2 -> {
                System.out.print("\033c");
                supplierManage();
            }
            case 3 -> {
				System.out.print("\033c");
				stockManage();
			}
            case 4 -> {
                System.out.print("\033c");
                logInPage();
                stockManageSystem();
            }
            case 5 -> {
                System.out.print("\033c");
                System.out.println("Exiting......");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid option! Returning to main menu.");
                stockManageSystem();
            }
        }
    }
    
	public static void inputPass() {		//1
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.print("\nPassword: ");
            String userInput = input.nextLine();
            if (password.equals(userInput)) {
                break;
            } else {
                System.out.println("Password is incorrect, please try again!");
            }
        }
    }
    
	public static void inputUserName() { 	//1
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.print("\nUser Name: ");
            String userInput = input.nextLine();
            if (userName.equals(userInput)) {
                break;
            } else {
                System.out.println("User name is invalid, please try again!");
            }
        }
    }
    
	public static void logInPage() { 	//1
		
       System.out.println("+-----------------------------------------------------------------------+");
       System.out.println("|                            LOGIN PAGE                                 |");
       System.out.println("+-----------------------------------------------------------------------+");
        
        inputUserName();
        inputPass();
        System.out.print("\033c");
    }
    
	public static void main(String args[]){
		logInPage(); 	//1
		stockManageSystem();	//2
	} 
}
