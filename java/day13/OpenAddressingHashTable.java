

import java.util.Scanner;

class OpenAddressingHashTable {
    class Entry {
        private int roll;
        private float marks;

        public Entry() {
            roll = 0;
            marks = 0.0f;
        }

        public Entry(int r, float m) {
            roll = r;
            marks = m;
        }

        public boolean is_empty() {
            return roll == 0;
        }
    }

    private static final int SLOTS = 10;

    private static int HASH(int k) {
        return k % SLOTS;
    }

    private static int REHASH(int i) {
        return (i + 1) % SLOTS;
    }

    private Entry table[] = new Entry[SLOTS];

    public OpenAddressingHashTable() {
        for(int i=0; i<SLOTS; i++)
            table[i] = new Entry();
    }

    public float get(int roll) {
        int slot = HASH(roll);
        if (table[slot].is_empty())
            return 0.0f; // not found
        if (table[slot].roll == roll) // found
            return table[slot].marks;
        for (int i = 0; i < SLOTS && !table[slot].is_empty(); i++) {
            slot = REHASH(slot);
            if (!table[slot].is_empty() && table[slot].roll == roll) // found
                return table[slot].marks;
        }
        return 0.0f; // not found
    }

    public void put(int roll, float marks) {
        Entry ne = new Entry(roll, marks);
        int i, slot = HASH(roll);
        if (table[slot].is_empty()) {
            table[slot] = ne; // empty slot found
            return;
        }
        if (table[slot].roll == roll) { // found
            table[slot] = ne; // overwrite the slot (value)
            return;
        }
        for (i = 0; i < SLOTS && !table[slot].is_empty(); i++) {
            slot = REHASH(slot);
            if (table[slot].is_empty()) {
                table[slot] = ne; // empty slot found
                return;
            }
            if (table[slot].roll == roll) { // found
                table[slot] = ne; // overwrite the slot (value)
                return;
            }
        }
        throw new RuntimeException("all slots full");
    }

    public void display() {
        int i;
        for (i = 0; i < SLOTS; i++) {
            if (!table[i].is_empty())
                System.out.println("roll=" + table[i].roll + ", marks=" + table[i].marks);
        }
    }

    public static void main(String[] args) {
        OpenAddressingHashTable ht = new OpenAddressingHashTable();
        try (Scanner sc = new Scanner(System.in)) {
            ht.put(11, 101);
            ht.put(22, 202);
            ht.put(33, 303);
            ht.put(21, 201);
            ht.put(41, 401);
            ht.put(52, 502);
            ht.put(61, 601);
            ht.put(93, 903);
            ht.put(11, 111); // overwrite
            ht.put(22, 222); // overwrite
            ht.put(46, 406);
            ht.put(75, 705);
            ht.put(61, 611); // overwrite
            // ht.put(69, 609); // full
            ht.display();

            int roll;
            System.out.print("enter roll: ");
            roll = sc.nextInt();
            float marks = ht.get(roll);
            if (marks == 0.0f)
                System.out.println("marks not found.");
            else
                System.out.println("marks: " + marks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
