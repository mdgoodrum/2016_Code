import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Michael Goodrum
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot put entry with "
                + "null value and/or null key.");
        }
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
        if ((size + 1.0) / table.length > 0.67) {
            resizeBackingTable((table.length * 2) + 1);
        }
        if (table[Math.abs(key.hashCode()) % table.length] == null) {
            table[Math.abs(key.hashCode()) % table.length] = mapEntry;
            size++;
            return null;
        } else {
            V temp = null;
            if (table[Math.abs(key.hashCode()) 
                % table.length].getKey().equals(key)) {
                temp = table[Math.abs(key.hashCode()) 
                    % table.length].getValue();
                table[Math.abs(key.hashCode()) % table.length].setValue(value);
                return temp;
            } else {
                boolean has = false;
                for (MapEntry<K, V> mapE = table[Math.abs(key.hashCode()) 
                    % table.length]; mapE != null; mapE = mapE.getNext()) {
                    if (mapE.getNext() != null 
                        && mapE.getNext().getKey().equals(key)) {
                        temp = mapE.getNext().getValue();
                        mapE.getNext().setValue(value);
                        has = true;
                    }
                } if (!has) {
                    mapEntry.setNext(table[Math.abs(key.hashCode()) 
                        % table.length]);
                    table[Math.abs(key.hashCode()) % table.length] = mapEntry;
                    size++;
                    return null;
                } else {
                    return temp;
                }
            }
        }
    }


    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot "
                + "remove element without key.");
        }
        MapEntry<K, V> temp = null;
        if (table[key.hashCode() % table.length] != null) {
            temp = table[key.hashCode() % table.length];
            if (temp.getKey().equals(key)) {
                table[key.hashCode() % table.length] = temp.getNext();
                size--;
                return temp.getValue();
            } else {
                for (MapEntry<K, V> mapE = table[key.hashCode() 
                        % table.length]; mapE != null; mapE = mapE.getNext()) {
                    if (mapE.getNext() != null) {
                        if (mapE.getNext().getKey().equals(key)) {
                            temp = mapE.getNext();
                            mapE.setNext(mapE.getNext().getNext());
                            size--;
                        }
                    }
                } return temp.getValue();
            }
        } else {
            throw new java.util.NoSuchElementException("Cannot remove element "
                + "not present in hash map.");
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot look for null key.");
        }
        if (table[key.hashCode() % table.length] == null) {
            throw new java.util.NoSuchElementException("Cannot get with key "
                + "not present in table. 1");
        } else {
            V temp = null;
            for (MapEntry<K, V> mapE = table[key.hashCode() % table.length];
                    mapE != null; mapE = mapE.getNext()) {
                if (mapE.getKey().equals(key)) {
                    temp = mapE.getValue();
                }
            }
            if (temp == null) {
                throw new java.util.NoSuchElementException("Cannot get with key"
                + " not present in table. 2");
            } else {
                return temp;
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot check "
                + "against null key.");
        }
        try {
            get(key);
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new java.util.HashSet();
        for (int x = 0; x < table.length; x++) {
            if (table[x] != null) {
                set.add(table[x].getKey());
                if (table[x].getNext() != null) {
                    for (MapEntry<K, V> mapE = table[x]; mapE 
                            != null; mapE = mapE.getNext()) {
                        if (mapE.getNext() != null) {
                            set.add(mapE.getNext().getKey());
                        }
                    }
                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new java.util.ArrayList();
        for (int x = 0; x < table.length; x++) {
            if (table[x] != null) {
                list.add(table[x].getValue());
                if (table[x].getNext() != null) {
                    for (MapEntry<K, V> mapE = table[x]; mapE 
                            != null; mapE = mapE.getNext()) {
                        if (mapE.getNext() != null) {
                            list.add(mapE.getNext().getValue());
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < 0 || length < size) {
            throw new IllegalArgumentException("Invalid value for length.");
        }
        MapEntry<K, V>[] oldTable = table;
        int oldsize = size;
        table = (MapEntry<K, V>[]) new MapEntry[length];
        for (int x = 0; x < oldTable.length; x++) {
            if (oldTable[x] != null) {
                put(oldTable[x].getKey(), oldTable[x].getValue());
                if (oldTable[x].getNext() != null) {
                    for (MapEntry<K, V> mapE = oldTable[x]; mapE 
                            != null; mapE = mapE.getNext()) {
                        if (mapE.getNext() != null) {
                            put(mapE.getNext().getKey(),
                                mapE.getNext().getValue());
                        }
                    }
                }
            }
            size = oldsize;
        }
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}