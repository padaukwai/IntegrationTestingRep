package imc.com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DisplayEmpListTest {
    static App app;
    static ArrayList<Employee> employees;
    @BeforeAll
    static void init() {
        app = new App();
        employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;
        employees.add(emp);
        Employee emp2 = new Employee();
        emp2.emp_no = 2;
        emp2.first_name = "Wai";
        emp2.last_name = "Mar";
        emp2.title = "Teacher";
        emp2.salary = 55000;
        employees.add(emp2);
    }
    @Test
    void displayEmplist() {
        int i=employees.size();
        int index=0;
        while(i>0)
        {
            System.out.print("Test Result is "+employees.get(index).first_name);
            System.out.println(" "+employees.get(index).last_name);
            index++;
            i--;
        }
    }
}
