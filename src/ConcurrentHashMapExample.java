import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    // Create a ConcurrentHashMap to store employee data
    private ConcurrentHashMap<String, Integer> employeeMap = new ConcurrentHashMap<>();

    // Add employee details
    public void addEmployee(String name, int salary) {
        employeeMap.put(name, salary);
        System.out.println(Thread.currentThread().getName() + " added " + name + " with salary: " + salary);
    }

    // Retrieve employee salary
    public void getSalary(String name) {
        Integer salary = employeeMap.get(name);
        if (salary != null) {
            System.out.println(Thread.currentThread().getName() + " retrieved salary of " + name + ": " + salary);
        } else {
            System.out.println(Thread.currentThread().getName() + ": " + name + " not found.");
        }
    }

    // Update salary only if the employee exists
    public void updateSalaryIfPresent(String name, int newSalary) {
        employeeMap.computeIfPresent(name, (k, v) -> newSalary);
        System.out.println(Thread.currentThread().getName() + " updated salary of " + name + " to: " + newSalary);
    }

    // Remove an employee if their salary matches the provided one
    public void removeEmployeeIfSalaryMatches(String name, int salary) {
        boolean removed = employeeMap.remove(name, salary);
        System.out.println(Thread.currentThread().getName() + " attempted to remove " + name + ": " + (removed ? "success" : "failed"));
    }

    public ConcurrentHashMap<String, Integer> getMap(){
        return employeeMap;
    }

}
