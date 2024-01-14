// Nehoray Maimon - ID 317826071
// Saar Dannon - ID 318322716
import java.util.*;

public class HashAVLSpellTable {
    private LinkedList<AVLTree> buckets[];
    private int tableSize;
    private int numSpells;

    public HashAVLSpellTable(int size) {
        tableSize = size;
        buckets = new LinkedList[size];
    }

    private int hash(String category) {
        int hash = 0;
        for(char c: category.toCharArray()){
            hash = hash + c;
        }
        return hash % tableSize;
    }

    public void addSpell(Spell s) {
        int currHash = hash(s.getCategory());

        if(this.buckets[currHash] != null){
            LinkedList<AVLTree> currLL = this.buckets[currHash];
            int i = 0;
            while(i < currLL.size()){
                AVLTree avlTree = currLL.get(i);
                if(avlTree.getCategory().equals(s.getCategory())){
                    avlTree.insert(s);
                    numSpells += 1;
                    break;
                }
                i += 1;
            }
        } else{
            AVLTree avlTree = new AVLTree(s);
            LinkedList<AVLTree> avlTreeLinkedList = new LinkedList<>();
            avlTreeLinkedList.add(avlTree);
            this.buckets[currHash] = avlTreeLinkedList;
            numSpells += 1;
        }

    }

    public Spell searchSpell(String category, String spellName, int powerLevel) {
        int currHash = hash(category);
        LinkedList<AVLTree> avlTreeLinkedList = this.buckets[currHash];
        int i = 0;
        while(i < avlTreeLinkedList.size()){
            AVLTree avlTree = avlTreeLinkedList.get(i);
            if(avlTree.getCategory().equals(category)){
                Spell wantedSpell = avlTree.search(spellName,powerLevel);
                return wantedSpell;
            }
            i += 1;
        }
        // if nothing found
        return null;

    }

    public int getNumberSpells(){
        return numSpells;
    }

    public int getNumberSpells(String category){

        AVLTree avlTree = this.findTreeByCategory(category);
        if(avlTree == null)
            return -1;

        return avlTree.getSize();

    }

    public List<Spell> getTopK(String category, int k) {
        AVLTree avlTree = this.findTreeByCategory(category);
        if(avlTree == null)
            return null;

        return avlTree.getTopK(k);

    }
    private AVLTree findTreeByCategory(String category){
        int currHash = hash(category);
        LinkedList<AVLTree> avlTreeLinkedList = this.buckets[currHash];
        int i = 0;
        while(i < avlTreeLinkedList.size()){
            AVLTree avlTree = avlTreeLinkedList.get(i);
            if(avlTree.getCategory().equals(category)){
                return avlTree;
            }
            i += 1;
        }
        // if nothing found
        return null;
    }
}
