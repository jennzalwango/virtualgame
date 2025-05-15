package Pets;


import Users.User;

import java.util.List;
import java.util.Scanner;

public class PetMain {
    public static void main(String[] args) {
        //create pet game menu
        PetGame game = new PetGame();

        //call method to create user and selected user.
        selectUser(game);

        //call method to display list of Pets
        listAndAssignPets(game);

        // call method to show users and their assigned pets
        showUserAssignedPets(game);

        // call method to interact with pet
        selectPet(game);


        String action = "";
        do {
            //creating the game options
            System.out.println("Choose an action to do with the chosen pet from the menu:");
            System.out.println("Play");
            System.out.println("Feed");
            System.out.println("Rest");
            System.out.println("Update");
            System.out.println("Insert");
            System.out.println("Delete");
            System.out.println("Details");
            System.out.println("MyPets");
            System.out.println("PetHealth");
            System.out.println("Swap");
            System.out.println("ShowUsers");
            System.out.println("Exit");

            //create scanner object
            Scanner scanner = new Scanner(System.in);
            action = scanner.nextLine();

            // use switch to call different methods that interact with pet.
            switch (action.trim().toLowerCase()) {
                case "play":
                    game.playWithPet(game.getCurrentPet());
                    break;

                case "feed":
                    game.getCurrentPet().feed();
                    break;
                case "pethealth":
                    PetHealth health = game.getCurrentPet().getPetHealthStatus();
                    System.out.println("The pet " + game.getCurrentPet().getPetName() + " health status is: " + health);
                    break;
                case "rest":
                    game.getCurrentPet().rest();
                    break;
                case "update":
                    updatePetDetails(game, game.getCurrentPet());
                    break;
                case "delete":
                    game.deletePet(game.getCurrentPet());
                    System.out.println("The pet with name: " + game.getCurrentPet().getPetName() + " has been deleted");
                    break;
                case "insert":
                    InsertNewPet(game.getCurrentPet());
                    System.out.println("This is the inserted pet: " + game.getCurrentPet());
                    break;
                case "details":
                    Pet pet = game.getCurrentPet();
                    if (pet != null) {
                        System.out.println(pet);
                        game.printPetDetails(pet);
                    } else {
                        System.out.println("No current pet is selected.");
                    }
                    break;
                case "mypets":
                    showUserAssignedPets(game);
                    System.out.println("here are your current pet: " + game.getCurrentPet());
                    break;
                case "swap":
                    showUserAssignedPets(game);
                    selectPet(game);
                    System.out.println("You have successfully swapped to interact with: " + game.getCurrentPet());
                    break;
                case "showusers":
                    showUsersWithPets(game);
                    break;
                default:
                    System.out.println("Invalid Option, Try again");
                    break;
            }
        } while (!action.equalsIgnoreCase("exit"));
    }


    // helper method to insert new pet
    private static void InsertNewPet(Pet pet) {
        Scanner userscanner = new Scanner(System.in);
        System.out.println("Enter pet name:");
        String name = userscanner.nextLine().trim();
        if (pet instanceof Dog) {
            ((Dog) pet).setPetName(name.trim());
        } else if (pet instanceof Cat) {
            ((Cat) pet).setPetName(name.trim());
        }else if (pet instanceof Fish) {
            ((Fish) pet).setPetName(name.trim());
        }

        //insert pet gender
        System.out.println("Enter pet gender:");
        Scanner scanner = new Scanner(System.in);
        String gender = scanner.nextLine().trim();
        if (pet instanceof Dog) {
            ((Dog) pet).setPetGender(gender.trim());
        } else if (pet instanceof Cat) {
            ((Cat) pet).setPetGender(gender.trim());
        }else if (pet instanceof Fish) {
            ((Fish) pet).setPetGender(gender.trim());
        }
    }

    //helper method to update the Pet Details
    private static void updatePetDetails(PetGame game, Pet pet) {
        // ask the user what attributes they want to update
        System.out.println("Choose attributes to update");
        // name, color, save
        String action = "";
        do {
            System.out.println("Name");
            System.out.println("Color");
            System.out.println("Health");
            System.out.println("Save");
            System.out.println("DONE");

            Scanner scanner = new Scanner(System.in);
            action = scanner.nextLine();
            switch (action.trim().toLowerCase()) {
                case "name":
                    System.out.println("Enter new pet name: ");
                    String newPetName = scanner.nextLine();
                    pet.setPetName(newPetName.trim());
                    break;
                case "color":
                    System.out.println("Enter new Pet color:");
                    String newPetColor = scanner.nextLine();
                    if (pet instanceof Dog) {
                        ((Dog) pet).setColor(newPetColor.trim());
                    } else if (pet instanceof Cat) {
                        ((Cat) pet).setColor(newPetColor.trim());
                    } else if (pet instanceof Fish) {
                        ((Fish) pet).setColor(newPetColor.trim());
                    }
                    break;

                case "health":
                    System.out.println("Enter new Pet color:");
                    int newPetHealth = scanner.nextInt();
                    game.setPetHealth(pet, newPetHealth);
                    break;

                case "save":
                    System.out.println("Saving updated pet details: ");
                    game.updatePet(pet);
                    System.out.println("This is the updated pet: " + game.getCurrentPet());
                    break;
                case "done":
                    System.out.println("Exiting update menu");
                    break;
                default:
                    System.out.println("Invalid option, Try again");
                    break;
            }
        } while (!action.equalsIgnoreCase("done"));
    }

    // helper method to create user and get user input
    private static void selectUser(PetGame game) {
        // create user
        String userNameInput = "";
        do {
            System.out.println("Enter your username");
            Scanner myscanner = new Scanner(System.in);
            userNameInput = myscanner.nextLine();
            if (userNameInput == null || userNameInput.isEmpty() || userNameInput.isBlank()) {
                System.out.println("Provide username");
            } else {
                game.setCurrentUser(userNameInput);
            }
        } while (userNameInput.isEmpty() || userNameInput.isBlank());
    }

    //helper method to list and assign pets
    private static void listAndAssignPets(PetGame game) {
        System.out.println("Please select 3 pets of your choice");
        displayAvailablePets(game);

        // define the option to add more pets

        boolean shouldAddMorePets = true;
        do {
            if (game.getCurrentUser().getAssignedPets().size() >= 3) {
                System.out.println("User has been assigned the maximum number of pets");
                break;
            }

            if (shouldAddMorePets) {
                System.out.println("Enter pet number, e.g 1");
                Scanner petUserInput = new Scanner(System.in);
                int petNumberByUser = petUserInput.nextInt();
                if (petNumberByUser < 0) {
                    System.out.println("Provide Pet Number!");
                } else {
                    try {
                        Pet pet = game.getUnassignedPets().get(petNumberByUser);
                        game.getCurrentUser().assignPet(pet);
                    } catch (Exception exception) {
                        System.out.println("Invalid pet number");
                    }
                    if (game.getCurrentUser().getAssignedPets().size() < 3) {
                        System.out.println("Do you want to add another Pet: Yes or No");
                        Scanner userInputOption = new Scanner(System.in);
                        String getInput = userInputOption.nextLine();
                        if (getInput.equalsIgnoreCase("Yes")) {
                            displayAvailablePets(game);
                        } else {
                            shouldAddMorePets = false;
                        }
                    }
                }
            } else {
                break;
            }
        } while (shouldAddMorePets == true);
    }

    private static void displayAvailablePets(PetGame game) {
        int petIndex = 0;
        List<Pet> pets = game.getUnassignedPets();
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(petIndex);
            System.out.println(petIndex + ". " + pet.toString());
            petIndex++;
        }
    }

    // helper method to handle logic to show user and their assigned Pets
    private static void showUserAssignedPets(PetGame game) {
        System.out.println("This is (are) your Pets to interact with:");
        int assignedPetIndex = 0;
        for (int i = 0; i < game.getCurrentUser().getAssignedPets().size(); i++) {
            Pet pet = game.getCurrentUser().getAssignedPets().get(assignedPetIndex);
            System.out.println(assignedPetIndex + ". " + pet.toString() + pet.getCurrentActivity());
            assignedPetIndex++;
        }
    }

    // helper method to choose pets to interact with
    private static Pet selectPet(PetGame game) {
        Pet pet = null;
        do {
            System.out.println("Choose a pet to interact with");
            Scanner chosenPet = new Scanner(System.in);
            int petToInteractWith = chosenPet.nextInt();

            try {
                pet = game
                        .getCurrentUser()
                        .getAssignedPets()
                        .get(petToInteractWith);
                game.setCurrentPet(pet);
                System.out.println("The pet with ID " + pet.getPetId() + " and name " + pet.getPetName() + " was chosen");
            } catch (Exception e) {
                System.out.println("Invalid pet number");
            }
        } while (pet == null);
        return pet;
    }

    private static void showUsersWithPets(PetGame game) {
        for (User user : game.getAllUsers()) {
            System.out.println(user);
            System.out.println(user.getUsername() + "'s pets are:");
            for (Pet pet : user.getAssignedPets()) {
                game.printPetDetails(pet);
            }
            System.out.println("-------------------------");
        }
    }
}


