package com.syscho.grpc.main;


import com.syscho.grpc.stub.AddressOuterClass;
import com.syscho.grpc.stub.EmployeeOuterClass;

public class ListDemo {

    public static void main(String[] args) throws Exception {

        EmployeeOuterClass.Employee employee1 = buildEmployee(101, "Peter", 23, EmployeeOuterClass.Designation.DEVELOPER);
        EmployeeOuterClass.Employee employee2 = buildEmployee(102, "Mark", 33, EmployeeOuterClass.Designation.LEAD);
        EmployeeOuterClass.Employee employee3 = buildEmployee(103, "Jessica", 25, EmployeeOuterClass.Designation.TESTER);
        EmployeeOuterClass.Employee employee4 = buildEmployee(104, "Jacob", 27, EmployeeOuterClass.Designation.DEVELOPER);

        EmployeeOuterClass.Employee.Builder empWithAddres = employee1.toBuilder().setAddress(AddressOuterClass.Address.newBuilder().setAddress1("104 Galli no")
                .setAddress2(" Beach rock ").setPinCode(20150).setCity("Delhi").setState("Delhi").build());

        EmployeeOuterClass.Employees.Builder employees = buildEmployeeList(employee1, employee2, employee3, employee4, empWithAddres.build());
        listOperation(employee4, employees);


    }


    private static void listOperation(EmployeeOuterClass.Employee employee4, EmployeeOuterClass.Employees.Builder employees) throws Exception {
        System.out.println("--------------Employee LIST---------------------------");
        System.out.println(employees);

        System.out.println("Employee at index  2 : " + employees.getEmployees(2));
        System.out.println("--------------Update Employee Details---------------------------");
        EmployeeOuterClass.Employee updateEmp = employee4.toBuilder().setId(152).setName("Risky").setDesignation(EmployeeOuterClass.Designation.UNKNOWN).build();
        employees.setEmployees(2, updateEmp);
        System.out.println(employees);
        System.out.println("Remove Employee at index  2 : " + employees.removeEmployees(2));

        System.out.println("--------------Serialization & DeSerialization---------------------------");
        byte[] bytes = employees.getEmployees(0).toByteArray();
        EmployeeOuterClass.Employee employeeDeserialized = EmployeeOuterClass.Employee.parseFrom(bytes);
        System.out.println(" Serialized Object : " + bytes);
        System.out.println(" DeSerialized Object : " + employeeDeserialized);
    }

    private static EmployeeOuterClass.Employee buildEmployee(int id, String name, int age, EmployeeOuterClass.Designation designation) {
        return EmployeeOuterClass.Employee.newBuilder().setId(id).setName(name).setAge(age).setDesignation(designation).build();
    }


    private static EmployeeOuterClass.Employees.Builder buildEmployeeList(EmployeeOuterClass.Employee... employees) {
        EmployeeOuterClass.Employees.Builder empData = EmployeeOuterClass.Employees.newBuilder();
        for (EmployeeOuterClass.Employee emp : employees) {
            empData.addEmployees(emp);
        }
        return empData;
    }

}

