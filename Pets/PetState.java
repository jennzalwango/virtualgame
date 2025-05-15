package Pets;

abstract class PetState {

    //attributes for state behaviours
    public int health, happiness, energy, hunger;
    protected int maxHungerLevel = 100;
    protected int maxEnergyLevel = 70;
    protected int maxHealthLevel = 100;
    private PetHealth petHealth;

    // create default constructor
    public PetState() {}

    abstract String getPetName();
    abstract void play();
    abstract void feed();
    abstract void rest();

    // methods for range
    public void decreaseHunger() {
        //hunger range (0 - 100)
        if (hunger >= 70) {
            System.out.println("The pet is not hungry");
        } else {
            hunger -= 10;
            System.out.println("Hunger pet level decreased to " + hunger);
        }
    }
    
    public void increaseHunger() {
        //if pet is not feed  hunger or has played
        if (hunger >= maxHungerLevel) {
            System.out.println("Pet is not hungry");
        } else {
            // increase level by 10
            hunger += 10;
            System.out.println("Pet hunger level increased to " + hunger);

        }
    }
    
    public void increaseEnergy() {
        if (energy >= maxEnergyLevel) {
            // if pet is resting
            System.out.println("The pet is at its max energy level");
        } else {
            //increase energy level by 10
            energy += 10;
            System.out.println("Pet energy level increased to " + energy);
        }
    }

    public void decreaseEnergy() {
        if (energy <= 10) {
            System.out.println("Pet needs to feed for more energy");
            // low energy can impact health
            decreaseHealth();
        } else {
            energy -= 10;
            System.out.println("Pet energy level decreased to " + energy);
        }
    }
    
    public void increaseHappiness() {
        //check pet is not hungry before playing
        if (hunger >= 80) {
            System.out.println(getPetName() + " is too hungry to be happy. Try feeding first.");
            return;
        }

        // check pet energy before playing
        if (energy <= 10) {
            System.out.println(getPetName() + " is too tired to enjoy playing. Let them rest.");
            return;
        }

        //increase happiness by 10%
        int happinessGain = (int) (happiness * 0.10);
        if (happinessGain == 0) {
            happinessGain = 10; // set a min gain 10
        }
        happiness += happinessGain;

        // max happiness level 70
        if (happiness > 70) happiness = 70;
        System.out.println(getPetName() + "Happiness level increased to " + happiness);
    }

    // decrease Pet Happiness
    public void decreaseHappiness() {
        if (happiness <= 10) {
            System.out.println("Pet needs some playing");
        } else {
            happiness -= (int) (happiness * 0.1);

            System.out.println("The pet happiness level reduced to " + happiness);
        }
    }

    //increase health
    public void increaseHealth() {
        if (health >= maxHealthLevel) {
            // if pet has  rest
            System.out.println("The pet is at its max health level");
        } else {
            //increase health level by 10
            health += 10;
            System.out.println("Pet health level increased to " + health);
        }
    }
    
    public void decreaseHealth() {
        health -= 10;
        System.out.println("Pet health level decreased to " + health);
    }
    // get the health status of the pet
    public PetHealth getPetHealthStatus() {
        if (health >= 20) {
            return PetHealth.HEALTHY;
        } else if (health > 0) {
            return PetHealth.SICK;
        } else {
            return PetHealth.DEAD;
        }
    }

    public boolean isSick(){
        return getPetHealthStatus() == PetHealth.SICK;
    }


    // create parametrized constructor
    public PetState(int health, int happiness, int energy, int hunger) {
        this.health = health;
        this.happiness = happiness;
        this.energy = energy;
        this.hunger = hunger;
    }

    // setters and getters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    //getters
    public int getHealth() {
        return this.health;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getHunger() {
        return this.hunger;
    }

    //display pet state details
    public void displayPetStateDetails() {
        System.out.println(
                getHappiness()
                        + getHealth()
                        + getHunger()
                        + getEnergy());
    }
}
