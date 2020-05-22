
import java.util.ArrayList;
import java.util.Iterator;

public class ChainingHashTable {
    private static int SLOTS = 10;

    public static int HASH(int key) {
        return key % SLOTS;
    }

    static class Entry {
        private int roll; // key
        private float marks; // value

        public Entry() {
            roll = 0;
            marks = 0.0f;
        }

        public Entry(int r, float m) {
            roll = r;
            marks = m;
        }

        boolean is_empty() {
            return roll == 0;
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Entry>[] table = new ArrayList[SLOTS];

    ChainingHashTable() {
        for (int i = 0; i < table.length; i++)
            table[i] = new ArrayList<>();
    }

    public float get(int roll) {
        // find the slot for given roll (key)
        int slot = HASH(roll);
        // find the given roll into the bucket (list) in that slot
        Iterator<Entry> itr = table[slot].iterator();
		while (itr.hasNext()) {
			// if found, return marks (value)
            Entry e = itr.next();
            if (e.roll == roll)
				return e.marks;
		}
		// if not found, return -0.0
		return 0.0f;
	}
	void put(int roll, float marks) {
		Entry e = new Entry(roll, marks);
		// find the slot for given roll (key)
		int slot = HASH(roll);
		// if bucket is empty, add entry there & return.
		if (table[slot].isEmpty()) {
			table[slot].add(e);
			return;
		}
		// find entry for given roll (key) & if found, overwrite value.
		Iterator<Entry> itr = table[slot].iterator();
		while (itr.hasNext()) {
            Entry en = itr.next();
			if (en.roll == roll) {
				en.marks = marks;
				return;
			}
		}
		// if entry is not found, append to the bucket.
		table[slot].add(e);
	}
	void display() {
		int i;
		for (i = 0; i < SLOTS; i++) {
			Iterator<Entry> itr = table[i].iterator();
			while (itr.hasNext()) {
                Entry e = itr.next();
				System.out.println(e.roll + " = " + e.marks);
			}
		}
	}

    public static void main(String[] args) {
        ChainingHashTable ht = new ChainingHashTable();
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
        ht.put(69, 609); 
        ht.display();     
    }
}
