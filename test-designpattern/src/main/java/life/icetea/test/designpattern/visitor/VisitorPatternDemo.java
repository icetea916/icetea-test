package life.icetea.test.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icetea
 */
public class VisitorPatternDemo {

    public static void main(String[] args) {
        // 子部门1和他的叶子部门
        Department subDept1 = new Department("子部门1");
        Department leafDept1 = new Department("叶子部门1");
        subDept1.getChildren().add(leafDept1);
        Department leafDept2 = new Department("叶子部门2");
        subDept1.getChildren().add(leafDept2);

        // 子部门2和他的叶子部门
        Department subDept2 = new Department("子部门2");
        Department leafDept3 = new Department("叶子部门3");
        subDept1.getChildren().add(leafDept3);

        // 父部门
        Department parentDept = new Department("父部门");
        parentDept.getChildren().add(subDept1);
        parentDept.getChildren().add(subDept2);

        // 删除父部门
        parentDept.access(new RemoveVisitor());
    }


    public static class Department {

        private String name;
        private List<Department> children = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Department> getChildren() {
            return children;
        }

        public void setChildren(List<Department> children) {
            this.children = children;
        }

        public void access(Visitor visitor) {
            visitor.visit(this);
        }

    }

    /**
     * 访问者接口
     */
    public interface Visitor {
        void visit(Department department);
    }

    /**
     * remove访问者
     */
    public static class RemoveVisitor implements Visitor {

        @Override
        public void visit(Department department) {
            if (department.getChildren().size() > 0) {
                for (Department child : department.getChildren()) {
                    child.access(this);
                }
            }
            System.out.println("删除部门【" + department.getName() + "】");
        }

    }


}
