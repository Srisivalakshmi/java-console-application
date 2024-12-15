import java.util.Scanner;
    public class Calculator {
        public static double calc() {
            Scanner scan = new Scanner(System.in);
            double result = 0;
            while (true) {
                System.out.println("Enter the operation(+,-,*,/,^,exit):");
                String op = scan.next();
                if (op.equals("exit")) {
                    break;
                } else {
                    System.out.println("Enter the 1st number:");
                    Double num1 = scan.nextDouble();
                    System.out.println("Enter the 2nd number:");
                    Double num2 = scan.nextDouble();


                    switch (op) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            }
                            break;

                    }
                    System.out.println("Result=" + result);

                }


            }
            return result;
        }

            double add ( double num1, double num2){
                return num1 + num2;
            }

            double add ( double num1, double num2, double c){
                return num1 + num2 + c;
            }
            double sub ( double num1, double num2){
                return num1 - num2;
            }
            double sub ( double num1, double num2, double c){
                return num1 - num2 - c;
            }
            double multi ( double num1, double num2){
                return num1 * num2;
            }
            double multi ( double num1, double num2, double c){
                return num1 * num2 * c;
            }

            double divide ( double num1, double num2){
                return num1 / num2;
            }
            double divide ( double num1, double num2, double c){
                return num1 / num2 / c;
            }

        }

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Calculator calc = new Calculator();
        calc.calc();


    }
}


