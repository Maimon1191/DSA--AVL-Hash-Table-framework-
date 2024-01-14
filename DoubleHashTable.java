
public class DoubleHashTable{
    private SpellSimple[] table; // table of spell simple struct (array)
    private int capacity; // size of table - prime number > 2 (array size)
    private int size; // number of filled buckets(slots)
    private int steps = 0; // – number of steps performed on latest (counter)



    public DoubleHashTable(int capacity) {
        // constructor, size of the table is chosen by capacity.
        this.capacity = capacity;

        if (isPrime(capacity)) {

            table = new SpellSimple[capacity];
        } else{
            int primeCount = getNextPrime(capacity);
            table = new SpellSimple[primeCount];
        }
    }

    private int getNextPrime(int minNumber){
        for( int i = minNumber; true; i++){
            if(isPrime(i)){
                return i;
            }
        }
    }


    private boolean isPrime(int num){
        // function that check if the input is a prime number > 2
        for (int i = 2; i*i <= num; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public boolean put(SpellSimple spellSimple)
    {
        // checking the size of table and
        //  comparing it with users input value
        // inserting new spell struct to the
        //table (based on hash functions), returns True if succeeds or False if not.
        if (size == capacity) {
            return false;
        }

        int hashing1 = hash1(spellSimple.getName());
        int hashing2 = hash2(spellSimple.getName());
        int i = 0;
        int index = (hashing1+i*hashing2) % capacity;
        while (table[index] != null) {
            i += 1;
            index = (hashing1+i*hashing2) % capacity;
        }
        steps = i;
        table[index] = spellSimple;
        size++;
        return true;
    }

    public String getCastWords(String name) {
        int hash1 = hash1(name);
        int hash2 = hash2(name);
        // 4 0 1 2 3
        while (table[hash1] == null || !table[hash1].getName().equals(name)) {
            steps += 1;
            if(steps == capacity)
                return null;
            hash1 += hash2;
            hash1 %= capacity;
        }
        return table[hash1].words;
    }


    public int getSize() {
        // Returns the number of steps performed in the
        //last put or getCastWords action
        return size;
    }

    public int getLastSteps() {
        return this.steps;
    }

    private int hash1(String name){
        // returns the result of hash function 1.
        // formula 1.
        int hashVal = 0;
        for(char c: name.toCharArray()){
            hashVal = hashVal + c  * 31;
        }
        return hashVal % capacity;
    }

    private int hash2(String name){
        // - returns the result of hash function 2
        // formula 2.
        int hashVal = 0;
        for(char c: name.toCharArray()){
            hashVal = hashVal + c * 13;
        }
        return (1 + hashVal % (capacity -2));
    }

    public void printHashTable(){
        System.out.println("\n My double Hash Table");
        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                System.out.println("Spell name" + table[i].name + ":  "
                        + table[i].words);
    }


}