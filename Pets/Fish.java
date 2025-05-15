package Pets;

public class Fish extends Pet {
    private String fishSpecies, color;
    public Fish(String petName,
                String petGender,
                String color,
                String fishSpecies
    ){
        super(petName, petGender);
        this.fishSpecies = fishSpecies;
        this.color = color;
    }
    public String getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(String fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //displayValues
    // method to output the details of the fish sub class.
    @Override
    public String toString() {
        return "Fish{" +
                "petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                ", fishSpecies='" + fishSpecies + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
