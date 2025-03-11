package Task3;

import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        Random random = new Random();
        
        // Генерация случайного трёхзначного числа
        int number = 100 + random.nextInt(900); // 100 до 999 включительно
        
        // Вычисление суммы цифр числа
        int sumOfDigits = sumDigits(number);
        
        // Вывод числа и суммы его цифр
        System.out.println("Случайное трёхзначное число: " + number);
        System.out.println("Сумма его цифр: " + sumOfDigits);
    }
    
    // Метод для вычисления суммы цифр числа
    public static int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
