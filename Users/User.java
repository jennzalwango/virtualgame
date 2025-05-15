package Users;

import Pets.Pet;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String username;
    private int userId;

    //list pets assigned to the user
    private ArrayList<Pet> assignedPets = new ArrayList<>();

    //create a param  constructor
    public User(String username) {
        this.username = username;

        // Generate random user id
        this.userId = new Random().nextInt();
    }

    //create setters for username & userId
    private void setUsername(String username){
        this.username = username;
    }
    private void setUserId(int userId){ this.userId = userId;
    }

    //create getters for username and id
    public String getUsername(){
        return username;
    };
    public int getUserId(){
        return userId;
    }

    //create the display function
    void  displayUsers(){
        System.out.println(getUsername() + getUserId());
    }

    public  void assignPet(Pet pet){
        assignedPets.add(pet);
    }

    //get the user assigned pets
    public ArrayList<Pet> getAssignedPets() {
        return assignedPets;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", numberOfAssignedPets=" + assignedPets.size() +
                '}';
    }
}
