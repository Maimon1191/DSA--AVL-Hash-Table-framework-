
public class MainPart1 {

    private static boolean testPassed = true;
    private static int testNum = 0;


    public static void main(String[] args) {
        System.out.println("Part 1 - Tester:");
        System.out.println();
        //SpellSimple Test
        SpellSimpleTest();
        //DoubleHashTableTest
        testDoubleHashTable();


        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }



    public static void SpellSimpleTest(){
        // fully checked
        System.out.println("SpellSimpleTest");
        SpellSimple spell = new SpellSimple("Expecto Patronum","I’m gonna stand here like a unicorn");
        test(spell.getName().equals("Expecto Patronum"), "Name doesnt match");
        test(spell.getWords().equals("I’m gonna stand here like a unicorn"), "word getter faulty");

    }
    private static void testDoubleHashTable() {
        System.out.println("SpellSimpleTest");
        DoubleHashTable DoubleHashtable = new DoubleHashTable(6);
        test(DoubleHashtable.getSize() == 0, "size of DoubleHashtable error");
        test(DoubleHashtable.getCastWords("Expecto Patronum") == null, "found incorrect spell");
        DoubleHashtable.put(new SpellSimple("Expecto Patronum", "I’m gonna stand here like a unicorn"));
        DoubleHashtable.put(new SpellSimple("Wingardium Leviosa", "Get up, stand up"));
        DoubleHashtable.put(new SpellSimple("Kamehamea", "woooooo"));
        test(DoubleHashtable.getCastWords("Expecto Patronum").equals("I’m gonna stand here like a unicorn"), "get cast words faulty");
        test(DoubleHashtable.getLastSteps() == 1, "steps not working properly");
        DoubleHashtable.put(new SpellSimple("Wind-Tornado", "whoosh"));
        test(DoubleHashtable.getLastSteps() == 0, "steps error");
        DoubleHashtable.put(new SpellSimple("Lumos", "WHAAAA"));
        DoubleHashtable.put(new SpellSimple("Im a real sleam shady! ", "please stand up!"));
        DoubleHashtable.put(new SpellSimple("candy shop", "tom tom tooom"));
        DoubleHashtable.put(new SpellSimple("its my life! ", "now or never!"));
        test(DoubleHashtable.getSize() == 6, "size of DHT faulty");

    }


    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }
}