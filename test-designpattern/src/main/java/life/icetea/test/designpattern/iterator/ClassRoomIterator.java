package life.icetea.test.designpattern.iterator;

public class ClassRoomIterator implements Iterator {

    private ClassRoom classRoom;
    private int index;

    public ClassRoomIterator(ClassRoom classRoom) {
        this.classRoom = classRoom;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < classRoom.getSize()) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        Student student = classRoom.getStudent(index);
        index++;
        return student;
    }

}
