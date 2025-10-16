package imc.com;


import java.sql.*;
import java.util.ArrayList;

public class App
{
    private Connection con = null;

    /*public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }*/
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned

            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }
    public void displayEmployee(Employee emp)
    {
        System.out.println("display function");


        try {
            if (emp != null && emp.emp_no!=0)
                {
                    System.out.println(
                            emp.emp_no + " "
                                    + emp.first_name + " "
                                    + emp.last_name + "\n"
                                    + emp.title + "\n"
                                    + "Salary:" + emp.salary + "\n"
                                    + emp.dept_name + "\n"
                                    + "Manager: " + emp.manager + "\n");
                    System.out.println("Data Exists");
                }

        } catch (Exception e) {
            System.out.println("Exception Occurs");
            //throw new RuntimeException(e);
        }
    }
       // else
           // System.out.println("Information is null, This Employee doesn't exist");
  //  }
    public  ArrayList<Employee> getEmployeeList(int count) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        String query = "SELECT emp_no, first_name, last_name FROM employees";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
           // int i=3;
            while (rs.next() && count>0) {//3//2//1
                Employee emp = new Employee();
                emp.emp_no = rs.getInt("emp_no");
                emp.first_name = rs.getString("first_name");
                emp.last_name = rs.getString("last_name");

                System.out.println("Employee No: " + emp.emp_no);
                employeeArrayList.add(emp);
                count--;//2//1//0
                if(count==0)
                    break;
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving employee list: " + e.getMessage());
        }

        return employeeArrayList;
    }
    public void displayEmplist(ArrayList<Employee> employeeArrayList)
    {
        int index=0;
        System.out.println("size is "+employeeArrayList.size());
        int size=employeeArrayList.size();
       while(size>0)
        {
           size--;
            System.out.print("Data is"+employeeArrayList.get(index).first_name);
            System.out.println("Data is"+employeeArrayList.get(index).last_name);
            index++;
        }
    }

    public static void main(String[] args)
    {
//        try
//        {
//            // Load Database driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e)
//        {
//            System.out.println("Could not load SQL driver");
//            System.exit(-1);
//        }
//
//        // Connection to the database
//        Connection con = null;
//        int retries = 5;
//        for (int i = 0; i < retries; ++i)
//        {
//            System.out.println("Connecting to database...");
//            try
//            {
//                // Wait a bit for db to start
//                Thread.sleep(50000);
//                // Connect to database
//                //if you run java on windows
//              //  con = DriverManager.getConnection("jdbc:mysql://localhost:33060/employees?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
//                //if you run java on docker compose
//                //db:3306
//                 con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
//                System.out.println("Successfully connected");
//                // Wait a bit
//                Thread.sleep(10000);
//                // Exit for loop
//                break;
//            }
//            catch (SQLException sqle)
//            {
//                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
//                System.out.println(sqle.getMessage());
//            }
//            catch (InterruptedException ie)
//            {
//                System.out.println("Thread interrupted? Should not happen.");
//            }
//        }
//
//        if (con != null)
//        {
//            try
//            {
//                // Close connection
//                con.close();
//            }
//            catch (Exception e)
//            {
//                System.out.println("Error closing connection to database");
//            }
//        }
        // Create new Application
        /*App a = new App();

        // Connect to database
        a.connect();
        System.out.println("After connecting");
        Employee emp = a.getEmployee(255530);
        // Display results
        a.displayEmployee(emp);
        // Disconnect from database
        a.disconnect();*/
        try {
          /*  App a = new App();
            a.connect();*/
            App a = new App();
            if (args.length < 1) {
               // a.connect("localhost:33060", 30000);//db:3306
                a.connect("db:3306", 30000);
            } else {
                a.connect(args[0], Integer.parseInt(args[1]));
            }
            System.out.println("After connecting");
            Employee emp = a.getEmployee(255530);
            a.displayEmployee(emp);
            System.out.println("----");

          ArrayList<Employee>employeeArrayList= a.getEmployeeList(2);
          System.out.println(employeeArrayList.size()+"ssssssssssssssssssssss");
           a.displayEmplist(employeeArrayList);
            a.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
