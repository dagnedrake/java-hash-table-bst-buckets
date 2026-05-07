import java.util.Random;

/**
 * Manual driver for HashTable.
 *
 * Runs simple insertion, removal, search, rehashing, and print checks.
 *
 * @author Dagne Drake
 */
public class HashTableTest
{
    public static void main(String[] args)
    {
        // Part 1: common hash table check
        HashTable hash = new HashTable(0);
        final int NUMS = 4000;
        final int GAP = 37;

        System.out.println("checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            hash.insert(i);

        for (int i = 1; i < NUMS; i += 2)
            hash.remove(i);

        if (NUMS < 40)
            System.out.println(hash.toString());

        // Part 2: hash table of integers
        HashTable newHash = new HashTable(0);

        // END = how many values to put in hash table.
        // Between runs, change to 100, 1000, 10000, etc.
        final int END = 100;

        Random r = new Random();
        Integer rand;

        for (int i = 0; i < END; i++)
        {
            rand = r.nextInt(END * 10);

            // Check if rand is already in the hash table.
            while (newHash.search(rand) != null)
            {
                rand = r.nextInt(END * 10);
            }

            newHash.insert(rand);

            // Check load factor.
            if (newHash.loadFactor() > 1.0)
                newHash.rehash();
        }

        System.out.println("INTEGER TEST: print hash table");
        System.out.println(newHash.toString());
    }
}