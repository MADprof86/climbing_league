package exam;

import java.util.Arrays;

public class stream {
    public static void main(String[] args) {

            int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            int result = Arrays.stream(numbers)
                    .filter(n-> n%2 == 0 )
                    .map(x-> x *2)
                    .sum();
        System.out.println(result);
    }
}
