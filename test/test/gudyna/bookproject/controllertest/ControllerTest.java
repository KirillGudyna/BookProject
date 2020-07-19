package test.gudyna.bookproject.controllertest;

import com.gudyna.bookproject.controller.WarehouseController;
import org.testng.annotations.BeforeTest;

public class ControllerTest {
    WarehouseController controller;
    @BeforeTest
    public void setUp(){
        controller.getInstance();
    }
}
