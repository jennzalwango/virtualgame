package Pets;

import java.util.Random;

public class Pet extends PetState {
    public String petName, petGender;
    private int petId;

    //create the constructor
    public Pet(
            String petName,
            String petGender
    ) {
        this.petName = petName;
        this.petGender = petGender;
        //Generate random id for Pets
        this.petId = new Random().nextInt();

        // pet attributes defined starting ranges
        this.hunger = 50;
        this.energy = 50;
        this.happiness = 50;
        this.health = 50;
    }

    // override functions for pet behaviours from Pet state
    @Override
    public void play() {
        // hunger pet will not play, has decreased energy. An assumption low energy impacts health
        if (hunger < 20) {
            // do not play
            System.out.println("Pet cannot play because its hungry");
            // hunger levels can impact health
            decreaseHealth();
            // also decrease the pets happiness since its hungry
            decreaseHappiness();
            setCurrentActivity("Hungry (can't play)");
        } else {
            // increase pet happiness, its increase hunger and decrease energy
            increaseHappiness();
            increaseHunger();
            decreaseEnergy();
            setCurrentActivity("Playing");
            System.out.println("The pet is playing");
        }
    }

    @Override
    public void feed() {
        //feeding increase energy and decrease Hunger
        increaseEnergy();
        decreaseHunger();
        setCurrentActivity("Feeding");
        System.out.println("The pet has been fed");
    }
    
    @Override
    public void rest() {
        // resting increase health and energy recover
        increaseHealth();
        increaseEnergy();
        setCurrentActivity("Resting");
        System.out.println("The pet is resting");
    }


    private String currentActivity = "Pet is Idle"; // Default state

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    //create setters for Pet
    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    //create getters for pet
    @Override
    public String getPetName() {
        return this.petName;
    }

    public String getPetGender() {
        return this.petGender;
    }


    public int getPetId() {
        return this.petId;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHunger() {
        return hunger;
    }

    public int getEnergy() {
        return energy;
    }


    //display pet details
    public void displayPetDetails() {
        System.out.println(getPetName() + getPetId() + getPetGender());
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petId=" + petId +
                ", currentActivity='" + getCurrentActivity() + '\'' +
                '}';
    }
}
