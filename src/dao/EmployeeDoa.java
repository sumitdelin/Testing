package dao;

import bean.Employee;

public interface EmployeeDoa {
    
    int saveEmployee(Employee e);
    int deleteEmployee(Employee e);
    int updateEmployee(Employee e);
    Employee getEmployee(int id);
    int getMaxEmpSalary();
    java.util.List<Employee> getEmployees1();
    java.util.List<Employee> getEmployees2();
    java.util.List<String> getEmpNames();
    
}
