import java.util.ArrayList;

/**
 * Hash table implementation using binary search trees as buckets.
 *
 * This class is currently unfinished and preserved as an early draft.
 *
 * @author Dagne Drake
 */
public class HashTable
{
    private Integer arraySize;
    private BinarySearchTree[] hashBST;

    /**
     * Constructor sets initial length of hash table.
     *
     * @param sz initial length of hash table
     */
    public HashTable(Integer sz)
    {
        hashBST = new BinarySearchTree[findNextPrime(sz)];
        arraySize = 0;
    }

    /**
     * Method to take an Integer parameter and store it in hash table.
     *
     * @param key Integer to store in hash table
     */
    public void insert(Integer key)
    {
        Integer pos = makeHashCode(key);
        BinarySearchTree root = hashBST[pos];

        if (root == null)
            root = new BinarySearchTree();

        root.add(key);
        hashBST[pos] = root;
        arraySize++;
    }

    /**
     * Method to find an item in the table and remove it.
     *
     * @param key item to find and remove
     */
    public void remove(Integer key)
    {
        Integer pos = makeHashCode(key);
        BinarySearchTree root = hashBST[pos];

        if (root == null)
        {
            System.out.println("\nItem to be removed is not there!\n");
            return;
        }

        if (root.remove(key))
        {
            arraySize--;
        }
        else
        {
            System.out.println("\nItem to be removed is not there!\n");
        }

        hashBST[pos] = root;
    }

    /**
     * Method to search for an item in the hash table.
     * If found, return the item. Otherwise, return null.
     *
     * @param key item for which to search
     * @return item if found, or null
     */
    public Integer search(Integer key)
    {
        Integer pos = makeHashCode(key);
        BinarySearchTree root = hashBST[pos];

        if (root == null)
            return null;

        if (root.contains(key))
            return key;

        return null;
    }

    /**
     * Method outputs the contents of the hash table.
     * The BST for each bucket is printed using an in-order traversal.
     *
     * @return contents of the hash table as a string
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        for (Integer i = 0; i < hashBST.length; i++)
        {
            if (hashBST[i] != null)
                hashBST[i].stringBuilderTraversal(str);
        }

        return str.toString();
    }

    /**
     * Method to return the number of keys in the hash table.
     *
     * @return number of keys
     */
    public Integer size()
    {
        return arraySize;
    }

    /**
     * Method to return the number of elements in the bucket
     * specified by the parameter index.
     *
     * @param index hash bucket identifier
     * @return number of elements in bucket at index
     */
    public Integer size(Integer index)
    {
        if (index < 0 || index >= hashBST.length)
            return 0;

        if (hashBST[index] == null)
            return 0;

        return hashBST[index].countTraversal();
    }

    /**
     * Method to return the loading factor of the hash table.
     * Loading factor = count of all elements / number of buckets.
     *
     * @return loading factor
     */
    public double loadFactor()
    {
        if (hashBST.length == 0)
            return 0.0;

        return (double) arraySize / hashBST.length;
    }

    /**
     * Method to resize the bucket list to the next prime
     * number greater than twice the current bucket count.
     * Then rehash each element into the larger hash table.
     */
    public void rehash()
    {
        ArrayList<Integer> temp = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        for (Integer i = 0; i < hashBST.length; i++)
        {
            if (hashBST[i] != null)
                hashBST[i].stringBuilderTraversal(str);
        }

        String hashString = str.toString().trim();

        if (!hashString.isEmpty())
        {
            String[] strings = hashString.split("\\s+");

            for (String s : strings)
                temp.add(Integer.parseInt(s));
        }

        Integer nextPrime = findNextPrime(2 * hashBST.length);
        hashBST = new BinarySearchTree[nextPrime];
        arraySize = 0;

        for (Integer value : temp)
            insert(value);
    }

    /**
     * Method to find the next prime number at or after n.
     *
     * @param n integer at which to begin prime check
     * @return next prime number
     */
    private static Integer findNextPrime(Integer n)
    {
        if (n <= 2)
            return 2;

        if (n % 2 == 0)
            n++;

        while (!checkIfPrime(n))
            n += 2;

        return n;
    }

    /**
     * Method to check if a given number is prime.
     *
     * @param n integer to check for prime
     * @return true if prime, false if not
     */
    private static boolean checkIfPrime(Integer n)
    {
        if (n == 2 || n == 3)
            return true;

        if (n <= 1 || n % 2 == 0)
            return false;

        for (Integer i = 3; i * i <= n; i += 2)
        {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    /**
     * Method to calculate hash code.
     *
     * @param h item number used to calculate hash
     * @return calculated hash value
     */
    private Integer makeHashCode(Integer h)
    {
        Integer hashValue = h % hashBST.length;

        if (hashValue < 0)
            hashValue += hashBST.length;

        return hashValue;
    }
}