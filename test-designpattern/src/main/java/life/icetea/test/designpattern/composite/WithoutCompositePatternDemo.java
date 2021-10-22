package life.icetea.test.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 不适用组合模式demo
 *
 * @author icetea
 */
public class WithoutCompositePatternDemo {

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

        // 删除父部门, 嵌套for循环太多,如果有多级的话就会很麻烦
        for (Department childDept : parentDept.getChildren()) {
            if (childDept.getChildren().size() > 0) {
                for (Department leafDept : childDept.getChildren()) {
                    leafDept.remove();
                }
            }
            childDept.remove();
        }
        parentDept.remove();
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

        public void remove() {
            System.out.println("删除部门【" + name + "】");
        }
    }

}
