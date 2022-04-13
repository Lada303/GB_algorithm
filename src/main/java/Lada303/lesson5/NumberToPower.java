package Lada303.lesson5;
/*
Возводит число в степень
Степень м.б. положитльным или отрицательным числом; м.б. целым или дробным(учитывается не более 2-х цифр после запятой)
*/

public class NumberToPower {
    private int counterDegree;
    private int counterGSD;
    private int counterRoot;

    public double raisingNumberToPower (double value, double degree) {
        counterDegree = 0;
        counterGSD = 0;
        counterRoot = 0;
        double result;
        if (degree == 0 || Double.isNaN(degree)) {
            return 1;
        }
        //в случае отрицательной степени
        boolean isPositiveDegree = true;
        if (degree < 0) {
            isPositiveDegree = false;
            degree *= -1;
        }
        //в случае целого числа степени
        if (degree % 1 == 0) {
            result = raisingToPositiveIntegerPower(value, degree);
        } else { // в случае дробного числа в степени
            //переводим десятичнцю дробь в числитель(degree) и знаменатель(denominator - будет степенью корня)
            int denominator = 1;
            while (degree % 1 != 0) {
                degree *= 10;
                denominator *= 10;
                if (denominator == 100) {
                    degree = Math.floor(degree);
                    break;
                }
            }
            //находим НОД для сокращения дроби (числителя и знаменателя) для уменьшения последующих итераций
            double gsd = euclidGCD(degree, denominator);
            if (gsd != 1) {
                degree /= gsd;
                denominator /= gsd;
            }
            //возводим в степень
            result = raisingToPositiveIntegerPower(value, degree);
            //вычисляем корень
            result = findingRootOfNumber(result, denominator);
        }
        System.out.println("Количество итераций при возведении в степень - " + counterDegree);
        System.out.println("Количество итераций при поиске НОД - " + counterGSD);
        System.out.println("Количество итераций при вычислении корня - " + counterRoot);

        return isPositiveDegree ? result : 1 / result;
    }

    //Возведение числа в целую положительную степень - рекурсивный алгоритм
    private double raisingToPositiveIntegerPower(double value, double degree) {
        counterDegree++;
        if (degree == 1) {
            return value;
        }
        return value * raisingToPositiveIntegerPower(value, --degree);
    }

    //Наибольший общий делитель - рекурсивный алгоритм Евклида
    private double euclidGCD(double a, double b) {
        counterGSD++;
        if (a == 0) return b;
        if (b == 0) return a;
        return (a >= b) ? euclidGCD(a % b, b) : euclidGCD(a,b % a);
    }

    //Нахождение корня n-ой (rootDegree) степени из числа (number)
    //тут от погрешности зависит скорость вычисления, иногда оооочень долго
    private double findingRootOfNumber(double number, double rootDegree) {
        double eps = 0.001;                  //допустимая погрешность
        double root = number / rootDegree;   //начальное приближение корня
        double rn = number;                  //значение корня последовательным делением
        while(Math.abs(root - rn) >= eps){
            rn = number;
            for(int i = 1; i < rootDegree; i++){
                rn = rn / root;
                counterRoot++;
            }
            root = 0.5 * ( rn + root);
        }
        return root;
    }

}
