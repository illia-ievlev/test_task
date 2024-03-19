package com.ievlev.test_task3.processor;

import com.ievlev.test_task3.exceptions.ParsingInputException;
import com.ievlev.test_task3.model.Degree;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.service.DepartmentService;
import com.ievlev.test_task3.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class InputProcessor implements CommandLineRunner {
    private static final String CAN_T_PARSE_YOUR_MESSAGE = "can't parse your message";
    private final LectorService lectorService;
    private final DepartmentService departmentService;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("User Input: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try{
                System.out.println("Answer: " + processInputString(input));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        System.exit(12);
    }

    private String processInputString(String input) {
        if (input.contains("Who is head of department ")) {
            String regex = "Who is head of department (.*)";
            String departmentName = getUserInputArgument(regex, input);
            String headOfDepartmentName = departmentService.getNameOfHeadOfDepartment(departmentName); //this method will throw DepartmentWithSpecifiedNameNotFound if there is no department with specified name
            return "Head of " + departmentName + "department is " + headOfDepartmentName;
        }

        if (input.contains("Show ") && input.contains(" statistics")) {
            String regex = "Show (.*) statistics";
            String departmentName = getUserInputArgument(regex, input);
            List<Lector> lectorList = departmentService.getLectorsFromDepartment(departmentName);
            long numberOfAssistants = 0;
            long numberOfAssociateProfessors = 0;
            long numberOfProfessors = 0;

            for (Lector lector : lectorList) {
                if (lector.getDegree().equals(Degree.ASSISTANT)) {
                    numberOfAssistants++;
                }
                if (lector.getDegree().equals(Degree.PROFESSOR)) {
                    numberOfProfessors++;
                }
                if (lector.getDegree().equals(Degree.ASSOCIATE_PROFESSOR)) {
                    numberOfAssociateProfessors++;
                }
            }

            return "assistants - " + numberOfAssistants + "\n associate professors - " + numberOfAssociateProfessors + "\n professors - " + numberOfProfessors;
        }

        if (input.contains("Show the average salary for the department ")) {
            String regex = "Show the average salary for the department (.*)";
            String departmentName = getUserInputArgument(regex, input);
            BigDecimal averageSalary = lectorService.getAverageSalaryOfDepartment(departmentName);
            return "The average salary of " + departmentName + " is " + averageSalary.toString();
        }

        if (input.contains("Show count of employee for ")) {
            String regex = "Show count of employee for (.*)";
            String departmentName = getUserInputArgument(regex, input);
            return String.valueOf(lectorService.getNumberOfEmployeeForDepartment(departmentName));
        }

        if (input.contains("Global search by ")) {
            String regex = "Global search by (.*)";
            String template = getUserInputArgument(regex, input);
            return lectorService.globalSearchByNameTemplate(template).toString();
        }
        throw new ParsingInputException(CAN_T_PARSE_YOUR_MESSAGE);
    }


    private String getUserInputArgument(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else throw new ParsingInputException(CAN_T_PARSE_YOUR_MESSAGE);
    }


}
