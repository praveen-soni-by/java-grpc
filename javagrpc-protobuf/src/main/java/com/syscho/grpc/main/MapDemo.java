package com.syscho.grpc.main;


import com.syscho.grpc.stub.EmployeeOuterClass;

public class MapDemo {

    public static void main(String[] args) throws Exception {

        EmployeeOuterClass.Employee employee1 = buildEmployee(101, "Peter", 23, EmployeeOuterClass.Designation.DEVELOPER);
        EmployeeOuterClass.Employee employee2 = buildEmployee(102, "Mark", 33, EmployeeOuterClass.Designation.LEAD);
        EmployeeOuterClass.Employee employee3 = buildEmployee(103, "Jessica", 25, EmployeeOuterClass.Designation.TESTER);
        EmployeeOuterClass.Employee employee4 = buildEmployee(104, "Jacob", 27, EmployeeOuterClass.Designation.DEVELOPER);


        EmployeeOuterClass.EmployeeMap employeeMap = buildEmployeeMap(employee1, employee2);
        mapOperations(employeeMap);
    }



    private static void mapOperations(EmployeeOuterClass.EmployeeMap employeeMap) throws Exception {
        System.out.println("--------------Employee MAP---------------------------");
        System.out.println(employeeMap);


        System.out.println("-----------------------------------------");
        System.out.println("Employee with ID 102 Exist ? " + employeeMap.containsEmployees(102));
        System.out.println("Employee with ID 012 Exist ? " + employeeMap.containsEmployees(012));

        System.out.println("-----------------------------------------");
        System.out.println(" Count number of employee " + employeeMap.getEmployeesCount());
        System.out.println("-----------------------------------------");

        EmployeeOuterClass.Employee employeesOrDefault = employeeMap.getEmployeesOrDefault(1011, null);
        System.out.println(" employeesOrDefault : " + employeesOrDefault);
        System.out.println("-----------------------------------------");

        byte[] bytes = employeeMap.toByteArray();

        EmployeeOuterClass.EmployeeMap employeeMap1 = EmployeeOuterClass.EmployeeMap.parseFrom(bytes);
        System.out.println(" Serialized Object : " + bytes);
        System.out.println(" DeSerialized Object : " + employeeMap1);
        EmployeeOuterClass.Employee employeesOrThrow = employeeMap.getEmployeesOrThrow(1021);
        System.out.println(" employeesOrThrow : " + employeesOrThrow);


    }



    private static EmployeeOuterClass.Employee buildEmployee(int id, String name, int age, EmployeeOuterClass.Designation designation) {
        return EmployeeOuterClass.Employee.newBuilder().setId(id).setName(name).setAge(age).setDesignation(designation).build();
    }

    private static EmployeeOuterClass.EmployeeMap buildEmployeeMap(EmployeeOuterClass.Employee employee1, EmployeeOuterClass.Employee employee2) {
        return EmployeeOuterClass.EmployeeMap.newBuilder().putEmployees(employee1.getId(), employee1)
                .putEmployees(employee2.getId(), employee2).build();
    }

}

