package Pets;

public class Cat extends Pet {
    private String character, color;
    public Cat(
            String petName,
            String petGender,
            String color,
            String character
    ) {
        super(petName, petGender);
        this.character = character;
        this.color = color;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //displayValues
    //output all the Cat details
    @Override
    public String toString() {
        return "Cat{" +
                "petName='" + petName + '\'' +
                ", petGender='" + petGender + '\'' +
                ", character='" + character + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
