
public class Spell {
    private String name; //stores the name of the spell
    private String category; // spell’s category
    private int powerLevel; //  spell’s category
    private String words; //  stores the words required to cast the spell

    public Spell(String name, String category, int powerLevel, String words) {
        this.name = name;
        this.category = category;
        this.powerLevel = powerLevel;
        this.words = words;

    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public int getPowerLevel() {
        return powerLevel;
    }

    public String getWords(){
        return words;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Power Level: " + powerLevel + ", to cast say: " + words;
    }
}
