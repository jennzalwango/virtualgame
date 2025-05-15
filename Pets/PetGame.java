package Pets;

import Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetGame {

    //defining the User List
    private ArrayList<User> usersArrayList = new ArrayList<>();
    //defining the Pet List

    public ArrayList<Pet> petArrayList = new ArrayList<>();

    private ArrayList<Pet> userAssignedPets = new ArrayList<>();

    private User currentUser = null;
    private Pet currentPet;

    public PetGame() {
        //create dummy pets list
        Dog dog1 = new Dog("Vicky", "F", "Red", "German Shepherd");
        Pet dog2 = new Dog("Max", "M", "Black", "Labrador");
        Pet dog3 = new Dog("Bella", "F", "White", "Poodle");
        Pet dog4 = new Dog("Rocky", "M", "Brown", "Boxer");
        Pet dog5 = new Dog("Lucy", "F", "Golden", "Golden Retriever");
        Pet dog6 = new Dog("Charlie", "M", "Gray", "Husky");
        Pet dog7 = new Dog("Daisy", "F", "Cream", "Beagle");

        Pet fish1 = new Fish("Vicky", "F", "Tilapia", "blue green");
        Pet fish2 = new Fish("Nemo", "M", "Clownfish", "orange white");
        Pet fish3 = new Fish("Bubbles", "F", "Goldfish", "golden");
        Pet fish4 = new Fish("Finn", "M", "Betta", "red blue");
        Pet fish5 = new Fish("Coral", "F", "Angelfish", "silver black");
        Pet fish6 = new Fish("Gill", "M", "Catfish", "gray");

        Pet cat1 = new Cat("Roe", "M", "Red", "curious" );
        Pet cat2 = new Cat("Whiskers", "F", "White", "lazy");
        Pet cat3 = new Cat("Shadow", "M", "Black", "playful");
        Pet cat4 = new Cat("Luna", "F", "Gray", "independent");
        Pet cat5 = new Cat("Simba", "M", "Golden", "brave");
        Pet cat6 = new Cat("Cleo", "F", "Brown", "quiet");
        Pet cat7 = new Cat("Milo", "M", "Orange", "curious");

        // add them to pet array list
        petArrayList.add(dog1);
        petArrayList.add(dog2);
        petArrayList.add(dog3);
        petArrayList.add(dog4);
        petArrayList.add(dog5);
        petArrayList.add(dog6);
        petArrayList.add(dog7);

        petArrayList.add(fish1);
        petArrayList.add(fish2);
        petArrayList.add(fish3);
        petArrayList.add(fish4);
        petArrayList.add(fish5);
        petArrayList.add(fish6);

        petArrayList.add(cat1);
        petArrayList.add(cat2);
        petArrayList.add(cat3);
        petArrayList.add(cat4);
        petArrayList.add(cat5);
        petArrayList.add(cat6);
        petArrayList.add(cat7);

        // create dummy user list
        User user1 = new User("Ninah");
        User user2 = new User("Joan");
        User user3 = new User("Robert");
        User user4 = new User("Kisenti");
        User user5 = new User("Caroline");

        // assign dummy pets to users
        user1.assignPet(dog1);
        user1.assignPet(cat2);
        user1.assignPet(fish2);
        //user 2
        user2.assignPet(cat1);
        user2.assignPet(fish3);
        //user3
        user3.assignPet(fish1);
        user3.assignPet(fish5);
        //user 4
        user4.assignPet(dog2);
        user4.assignPet(cat4);

        usersArrayList.add(user1);
        usersArrayList.add(user2);
        usersArrayList.add(user3);
        usersArrayList.add(user4);
        usersArrayList.add(user5);
    }


    //set user
    public void setCurrentUser(String userName) {
        // check username matches user in user array list
        User user = findUserByName(userName);
        // if yes return user else create user
        if(user == null){
            currentUser = new User(userName);
            usersArrayList.add(currentUser);
        } else {
            currentUser = user;
        }
    }
    // get the user
    public User getCurrentUser() {
        return currentUser;
    }

    // set current pet,
    public void setCurrentPet(Pet pet) {
        currentPet = pet;
    }

    //get current pet
    public Pet getCurrentPet(){
        return currentPet;
    }

    // update pet name
    void updatePetName(String newName) {
        if (currentPet != null) {
            currentPet.setPetName(newName);
            System.out.println("Pet name updated to: " + newName);
        } else {
            System.out.println("No pet selected to update.");
        }

    }

    public List<Pet> getUnassignedPets() {
        userAssignedPets.clear();
        for (User user : getAllUsers()) {
            userAssignedPets.addAll(user.getAssignedPets());
        }
        ArrayList<Pet> unAssignedPets = new ArrayList<>(petArrayList);
        unAssignedPets.removeAll(userAssignedPets);
        return unAssignedPets;
    }
    // method for playing with pet.
    public void playWithPet(Pet pet) {
        pet.play();
    }

    // method set the health of the pet
    void setPetHealth(Pet pet, int health) {
        //set health
        pet.setHealth(health);
        // remove the pet
        removePetFromUserListIfDead(pet);
    }

    // method to implement logic for dead pet if it is sick, remove from users list
    private void removePetFromUserListIfDead(Pet pet) {
        //get the current health of the pet
        PetHealth petHealth = getCurrentPet().getPetHealthStatus();
        if (petHealth == PetHealth.DEAD){
            // inform user pet is dead
            System.out.println("Unfortunately, your pet has died! Removed from your list of pets");
            // remove the pet from users assigned pet list
            currentUser.getAssignedPets().remove(pet);
        }
    }

    void deletePet(Pet pet){
        petArrayList.remove(pet);
        // remove pet from current user list.
        currentUser.getAssignedPets()
                .remove(pet);
    }

    void updatePet(Pet pet) {
        // Update in petArrayList
        for (int i = 0; i < petArrayList.size(); i++) {
            if (petArrayList.get(i).getPetId() == pet.getPetId()) {
                petArrayList.set(i, pet);
                break;
            }
        }

        // Update in currentUser's pet list
        for (int i = 0; i < currentUser.getAssignedPets().size(); i++) {
            if (currentUser.getAssignedPets().get(i).getPetId() == pet.getPetId()) {
                currentUser.getAssignedPets().set(i, pet);
                break;
            }
        }

        //  update currentPet if it matches
        if (currentPet != null && currentPet.getPetId() == pet.getPetId()) {
            currentPet = pet;
        }
    }


    private Pet findPetById(int petId) {
        Pet currentPet = null;
        for (Pet p : petArrayList) {
            if(p.getPetId() == petId){
                currentPet = p;
                break;
            }
        }
        return currentPet;
    }

    private User findUserByName(String username){
        User tempUser = null;
        for(User user : usersArrayList){
            if(user.getUsername() == username){
                tempUser = user;
                break;
            }
        }
        return tempUser;
    }

    //helper method to encapsulate pet data details.
    public void printPetDetails(Pet currentPet) {
        //print current pet details
        System.out.println("Pet Info:");
        System.out.println("ID: " + currentPet.getPetId());
        System.out.println("Name: " + currentPet.getPetName());
        System.out.println("Gender: " + currentPet.getPetGender());
        System.out.println("Health: " + currentPet.getHealth());
        System.out.println("Happiness: " + currentPet.getHappiness());
        System.out.println("Hunger: " + currentPet.getHunger());
        System.out.println("Energy: " + currentPet.getEnergy());
        System.out.println("Status: " + currentPet.getPetHealthStatus());
    }

    // method to get all users
    public ArrayList<User> getAllUsers() {
        return usersArrayList;
    }
}
