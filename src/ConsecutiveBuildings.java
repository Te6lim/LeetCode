import java.util.ArrayList;

public class ConsecutiveBuildings {

    static String string = "00111111011110011000100100001000110001011110010100011111101100101110100";

    public static void main(String[] args) {
        System.out.println(numberOfWays(string));
    }

    public static long numberOfWays(String s) {
        return totalOf101(s) + totalOf010(s);
    }

    public static long totalOf101(String s) {
        ArrayList<Integer> rightValues = new ArrayList<>();
        ArrayList<Integer> centerValues = new ArrayList<>();
        int centerCount = 0;
        int leftCount = 0;
        int rightCount = 0;
        long total = 0;

        int positionCounter = 0;

        // find first
        for  (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                leftCount = 1;
                positionCounter = i;
                break;
            }
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                leftCount++;
                positionCounter = i;
            } else {
                break;
            }
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                centerCount++;
                positionCounter = i;
            } else {
                centerValues.add(centerCount);
                centerCount = 0;
                break;
            }
        }

        if (centerCount > 0) {
            centerValues.add(centerCount);
            centerCount = 0;
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (centerCount > 0) {
                    centerValues.add(centerCount);
                    centerCount = 0;
                }
                rightCount++;
            } else {
                if (rightCount > 0) {
                    rightValues.add(rightCount);
                    rightCount = 0;
                }
                centerCount++;
            }
        }

        if (centerCount > 0) {
            centerValues.add(centerCount);
        }

        if (rightCount > 0) {
            rightValues.add(rightCount);
        }


        long currentRightTotal = 0;
        for (Integer rightValue : rightValues) {
            currentRightTotal += (long) rightValue;
        }
        while (!centerValues.isEmpty()) {
            if (!rightValues.isEmpty()) {
                total += ((long) leftCount * centerValues.get(0)) * (currentRightTotal);
                leftCount += rightValues.get(0);
                positionCounter += rightValues.get(0);
                currentRightTotal -= rightValues.get(0);
                rightValues.remove(0);
            }
            positionCounter += centerValues.get(0);
            centerValues.remove(0);
        }
        return total;
    }

    public static long totalOf010(String s) {
        ArrayList<Integer> rightValues = new ArrayList<>();
        ArrayList<Integer> centerValues = new ArrayList<>();
        int centerCount = 0;
        int leftCount = 0;
        int rightCount = 0;
        long total = 0;

        int positionCounter = 0;

        // find first
        for  (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                leftCount = 1;
                positionCounter = i;
                break;
            }
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                leftCount++;
                positionCounter = i;
            } else {
                break;
            }
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                centerCount++;
                positionCounter = i;
            } else {
                centerValues.add(centerCount);
                centerCount = 0;
                break;
            }
        }

        if (centerCount > 0) {
            centerValues.add(centerCount);
            centerCount = 0;
        }

        for (int i = positionCounter + 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (centerCount != 0) {
                    centerValues.add(centerCount);
                    centerCount = 0;
                }
                rightCount++;
            } else {
                if (rightCount != 0) {
                    rightValues.add(rightCount);
                    rightCount = 0;
                }
                centerCount++;
            }
        }

        if (centerCount > 0) {
            centerValues.add(centerCount);
        }

        if (rightCount > 0) {
            rightValues.add(rightCount);
        }

        long currentRightTotal = 0;
        for (Integer rightValue : rightValues) {
            currentRightTotal += (long) rightValue;
        }
        while (!centerValues.isEmpty()) {
            if (!rightValues.isEmpty()) {
                total += ((long) leftCount * centerValues.get(0)) * (currentRightTotal);
                leftCount += rightValues.get(0);
                positionCounter += rightValues.get(0);
                currentRightTotal -= rightValues.get(0);
                rightValues.remove(0);
            }
            positionCounter += centerValues.get(0);
            centerValues.remove(0);
        }
        return total;
    }
}
