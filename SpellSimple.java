
public class SpellSimple {
    String name; // stores the name of the spell
    String words; // stores the words required to cast the spell

    SpellSimple(String name, String words) {
        // class constructor
        this.name = name;
        this.words = words;

    }

    public String getName(){
        // – Returns the spell name
        return name;
    }

    public String getWords(){
        //Returns the words to cast the spell
        return words;
    }
}