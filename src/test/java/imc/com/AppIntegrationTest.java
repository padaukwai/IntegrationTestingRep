package imc.com;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
   // static App app;
    static  App app;
   @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("127.0.0.1:33060", 30000);
       // app.connect("db:33060", 30000);

    }
 /* @BeforeEach
  void setUp() {
      app = new App();
      app.connect("localhost:33060", 10000);
  }*/

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertEquals(emp.emp_no, 255530);
        assertEquals(emp.first_name, "Ronghao");
        assertEquals(emp.last_name, "Garigliano");
    }
}
