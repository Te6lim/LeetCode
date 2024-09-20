import java.util.ArrayList;
import java.util.LinkedList;

public class ConsecutiveBuildings {

    public static void main(String[] args) {
        System.out.println(numberOfWays("0001100100"));
    }

    public static long numberOfWays(String s) {
        return totalOf101(s) + totalOf010(s);
    }

    public static long totalOf101(String s) {
        LinkedList<Integer> rightValues = new LinkedList<>();
        int centerCount;
        int lastCenterPosition = s.length();
        int leftCount = 1;
        int step = 0;
        int rightCount;
        long total = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                step = i;
                break;
            }
        }

        while (step < s.length()) {
            for (int i = step + 1; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    leftCount++;
                    step = i;
                } else {
                    break;
                }
            }

            centerCount = 0;
            for (int i = step + 1; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    centerCount++;
                    step = i;
                    lastCenterPosition = i;
                } else {
                    break;
                }
            }

            if (rightValues.isEmpty()) {
                rightCount = 0;
                for (int i = step + 1; i < s.length(); i++) {
                    if (s.charAt(i) == '1') {
                        rightCount++;
                    } else {
                        if (rightCount != 0) {
                            rightValues.add(rightCount);
                            rightCount= 0;
                        }
                    }
                }

                if (rightCount != 0) {
                    rightValues.add(rightCount);
                }
            }

            if (rightValues.isEmpty()) {
                step = s.length();
            } else {
                for (Integer rightValue : rightValues) {
                    total += (long) leftCount * rightValue * centerCount;
                }
                rightValues.remove(0);
                step = lastCenterPosition;
                lastCenterPosition = s.length();
            }
        }
        return total;
    }

    public static long totalOf010(String s) {
        LinkedList<Integer> rightValues = new LinkedList<>();
        ArrayList<Integer> centerValues = new ArrayList<>();
        int centerCount;
        int lastCenterPosition = s.length();
        int leftCount = 1;
        int step = 0;
        int rightCount;
        long total = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                step = i;
                break;
            }
        }

        while (step < s.length()) {
            if (leftCount == 0) {
                for (int i = step + 1; i < s.length(); i++) {
                    if (s.charAt(i) == '0') {
                        leftCount++;
                        step = i;
                    } else {
                        break;
                    }
                }
            }

            if (centerValues.isEmpty()) {
                centerCount = 0;
                for (int i = step + 1; i < s.length(); i++) {
                    if (s.charAt(i) == '1') {
                        centerCount++;
                        step = i;
                        lastCenterPosition = i;
                    } else {
                        break;
                    }
                }
                centerValues.add(centerCount);
            }

            if (rightValues.isEmpty()) {
                rightCount = 0;
                centerCount = 0;
                for (int i = step + 1; i < s.length(); i++) {
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

                if (centerCount != 0) {
                    rightValues.add(centerCount);
                }

                if (rightCount != 0) {
                    rightValues.add(rightCount);
                }
            }

            if (rightValues.isEmpty()) {
                step = s.length();
            } else {
                for (Integer rightValue : rightValues) {
                    total += (long) leftCount * rightValue * centerValues.get(0);
                }

                leftCount += centerValues.get(0);
                rightValues.remove(0);
                centerValues.remove(0);
                step = lastCenterPosition;
                lastCenterPosition = s.length();
            }
        }
        return total;
    }
}
