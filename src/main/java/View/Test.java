package View;

import javafx.beans.property.SimpleStringProperty;

public class Test {
    private SimpleStringProperty name;

    Test(String name){this.name = new SimpleStringProperty(name);}
    public String getName(){return name.get();}
}
