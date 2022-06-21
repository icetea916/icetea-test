package life.icetea.test.lambada.domain;

import java.awt.*;

public class Test {

    public static void main(String[] args) {
        Button button = new Button();
        button.addActionListener((event) -> {
            System.out.println("button clicked icetea");
        });
    }

}
