package Lada303.lesson8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashTableImpl <K, V> implements HashTable<K, V>{
    private final List<Item<K, V>>[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("key: %s -> value: %s", key, value);
        }
    }

    public HashTableImpl(int initialCapacity) {
        this.data = new ArrayList[initialCapacity];
    }

    public HashTableImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) {
            return false;
        }

        int index = hashFunc(key);
        List<Item<K, V>> listItems = data[index];
        if (listItems == null) {
            data[index] = new ArrayList<>();
        } else {
            for (Item<K, V> listItem : listItems) {
                if (listItem.getKey().equals(key)) {
                    listItem.setValue(value);
                    return true;
                }
            }
        }
        data[index].add(new Item<>(key, value));
        size++;
        return true;
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        List<Item<K, V>> listItems = data[index];
     //   System.out.println(listItems);
        if (listItems == null) {
            return null;
        } else {
            for (Item<K, V> listItem : listItems) {
            //    System.out.println(listItem.getKey() + " <-> " + key);
                if (listItem.getKey().equals(key)) {
                    return listItem.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        List<Item<K, V>> listItems = data[index];
        if (listItems == null) {
            return null;
        } else {
            for (int i = 0; i < listItems.size(); i++) {
                Iterator<Item<K, V>> iter = listItems.iterator();
                while (iter.hasNext()) {
                    Item<K, V> item = iter.next();
                    if (item.getKey().equals(key)) {
                        V value = item.getValue();
                        iter.remove();
                        size--;
                        return value;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s = [%s]%n", i, data[i]));
        }
        return sb.toString();
    }
}
