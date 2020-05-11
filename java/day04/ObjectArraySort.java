
class Student {
    private int roll;
    private String name;
    private float marks;
    public Student() {
        this.roll = 0;
        this.name = "";
        this.marks = 0.0f;
    }
    public Student(int roll, String name, float marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }
    // getter/setter
    public float getMarks() {
        return this.marks;
    }
    @Override
    public String toString() {
        return this.roll + ", " + this.name + ", " + this.marks;
    }
}

public class ObjectArraySort {
    public static void sortStudents(Student[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[i].getMarks() < a[j].getMarks()) {
                    Student t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }
    public static void main(String[] args) {
        Student[] arr = {
            new Student(1, "A", 76),
            new Student(2, "B", 67),
            new Student(3, "C", 87),
            new Student(4, "D", 45),
            new Student(5, "E", 65),
        };
        sortStudents(arr);
        for (Student s : arr) {
            System.out.println(s);
        }
    }
}
