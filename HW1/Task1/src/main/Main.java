package main;
import decorator.Decorator;
import math.Math;

public class Main {
    public static void main(String[] args) {
        int result = Math.mul(5,5);
        System.out.println(Decorator.decorator1(result));
    }
}