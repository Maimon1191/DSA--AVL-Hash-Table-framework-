/**
 * This is a testing framework. Use it extensively to verify that my code is working
 * properly.
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;


    public static void main(String[] args) {
        //SpellSimple Test
        SpellSimpleTest();
        //DoubleHashTableTest
        testDoubleHashTable();
        //SpellTest
        SpellTest();
        //AVLTreeTest
        testAVLTreeTest();
        //HashAVLSpellTableTest
        HashAVLSpellTableTest();


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

    public static void SpellTest(){

        System.out.println("SpellTest");
        Spell spell = new Spell("Kamehamea", "fire", 10, "Kamehamea!!!!!!");
        test(spell.getName().equals("Kamehamea"), "Name getter error");
        test(spell.getCategory().equals("fire"), "Category getter error");
        test(spell.getPowerLevel() == 10, "Power level getter error");
        test(spell.getWords().equals("Kamehamea!!!!!!"), "word getter faulty");
        test(spell.toString().equals("Kamehamea (fire) - Power Level: 10, to cast say: Kamehamea!!!!!!"), "spell to string faulty");
    }


    public static void testAVLTreeTest(){
        // RR rotations tests
        System.out.println("AVLTreeTest");
        AVLTree treeRighRight = new AVLTree(new Spell("Kamehmea", "fire", 1, "k1!"));
        test(treeRighRight.getTreeHeight() == 0, "Tree height error");
        treeRighRight.insert(new Spell("Kamehmea", "fire", 2, "k2!"));
        test(treeRighRight.getTreeHeight() == 1, "Tree height error");
        treeRighRight.insert(new Spell("Kamehmea", "fire", 3, "k3!"));
        test(treeRighRight.getTreeHeight() == 1,  "Tree height error");
        test(treeRighRight.getSize() == 3, "Tree size error");

        //LL rotations tests
        AVLTree treeLL = new AVLTree(new Spell("AVATAR-ANG-ICE","ice",8,"SPSHHH"));
        treeLL.insert(new Spell("AVATAR-ANG-ICE","ice",7,"SPSHHH"));
        treeLL.insert(new Spell("AVATAR-ANG-ICE","ice",6,"SPSHHH"));
        test(treeLL.getTreeHeight() == 1, "Tree height error");


        //LR rotations tests
        AVLTree treeLR = new AVLTree(new Spell("storm","wind",6,"woooooo"));
        treeLR.insert(new Spell("storm","wind",4,"woooooo"));
        treeLR.insert(new Spell("storm","wind",5,"woooooo"));
        test(treeLR.getTreeHeight() == 1, "Tree height error");

        //RL rotations tests
        AVLTree treeRL = new AVLTree(new Spell("Earthquake","earth",4,"qlackk"));
        treeRL.insert(new Spell("Earthquake","earth",6,"qlackk"));
        treeRL.insert(new Spell("Earthquake","earth",5,"qlackk"));
        test(treeRL.getTreeHeight() == 1, "Tree height error");

    }


    public static void HashAVLSpellTableTest(){
        System.out.println("HashAVLSpellTableTest");
        HashAVLSpellTable myHashTable = new HashAVLSpellTable(11);
        myHashTable.addSpell(new Spell("fireball","fire",1,"foo"));
        myHashTable.addSpell(new Spell("fireball2","fire",2,"foo2"));
        myHashTable.addSpell(new Spell("fireball3","fire",3,"test"));
        myHashTable.addSpell(new Spell("fireball4","fire",4,"test"));
        myHashTable.addSpell(new Spell("fireball5","fire",5,"test"));
        myHashTable.addSpell(new Spell("iceblast","ice",1,"brr"));
        test(myHashTable.getNumberSpells() == 6,"Hash Table Size faulty");
        test(myHashTable.getNumberSpells("fire") == 5, "Hash Table By category size faulty");
        test(myHashTable.searchSpell("fire","fireball",1).getWords().equals("foo"),"Hash table search faulty");
        test(myHashTable.searchSpell("fire","fireball6",5) == null,"spell search faulty");
        boolean flag = true;
        for (Spell spell:myHashTable.getTopK("fire",3))
            if (!spell.getWords().equals("test")) {
                flag = false;
                break;
            }
        test(flag,"get top k faulty");
    }

    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }
}