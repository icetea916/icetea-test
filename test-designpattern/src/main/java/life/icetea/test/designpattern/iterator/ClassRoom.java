package life.icetea.test.designpattern.iterator;

public class ClassRoom implements Aggregate {

    private Student[] students;
    private int last = 0;

    public ClassRoom(int size) {
        this.students = new Student[size];
    }

    public Student getStudent(int index) {
        return students[index];
    }

    public void addStudent(Student student) {
        students[last] = student;
        last++;
    }

    public int getSize() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new ClassRoomIterator(this);
    }

}
