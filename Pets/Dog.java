package Pets;

public class Dog extends Pet {
    private String color, dogType;
    public Dog(
            String petName,
            String petGender,
            String color,
            String dogType
    ) {
        super(petName, petGender);
        this.color = color;
        this.dogType = dogType;
    }

//methods to do hold the behaviours of the Pets for example Dog
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getDogType() {
        return dogType;
    }
    public void setDogType(String dogSound) {
        this.dogType = dogSound;
    }

    //displayValues
    //output all the Cat details
    @Override
    public String toString() {
        return "Dog{" +
                "name ='" + getPetName() + '\'' +
                ", dogType='" + dogType + '\'' +
                "color='" + color + '\'' +
                ", gender ='" + getPetGender() + '\'' +
                '}';
    }
}
