package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Application {
    public static void main(String[] args) {
        System.out.println("숫자 야구를 시작합니다.");

        List<Integer> computerValue = outputComputer();

        while (true) {
            if (matchNumber(computerValue, inputNumber()) == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                Scanner scn = new Scanner(System.in);
                int gameRestart = scn.nextInt();
                switch (gameRestart){
                    case 1:
                        computerValue = outputComputer();
                        matchNumber(computerValue, inputNumber());
                        continue;
                    case 2:
                        break;
                }
                break;
            }
        }
    }

    private static List<Integer> outputComputer() {
        Computer computer = new Computer();

        while (computer.computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.computer.contains(randomNumber)) {
                computer.computer.add(randomNumber);
            }
        }

        return computer.computer;
    }

    public static List<Integer> inputNumber() {

        Scanner scn = new Scanner(System.in);
        System.out.print("숫자를 입력해주세요 : ");
        String userNumber = scn.nextLine();
        int[] userNumArray= new int[3];
        try {
            userNumArray = Arrays.stream(userNumber.split("")).mapToInt(Integer::parseInt).toArray();
        } catch (IllegalArgumentException e) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.print("숫자를 입력해주세요 : ");
            userNumber = scn.nextLine();
        } finally {
            return Arrays.stream(userNumArray).boxed().collect(Collectors.toList());

        }
    }

    public static int matchNumber(List<Integer> computer, List<Integer> user){
        int strikeCount = 0;
        int ballCount = 0;
        for (int i = 0; i < computer.size(); i++) {
            Integer computerValue = computer.get(i);
            Integer userValue = user.get(i);

            if (computerValue.equals(userValue)) {
                strikeCount++;
            } else {
                for (Integer value : user) {
                    if (computerValue.equals(value)) {
                        ballCount++;
                        break;
                    }
                }
            }
        }

        if (strikeCount >= 1 && ballCount >= 1)
            System.out.println(strikeCount + "스트라이크" + " " + ballCount + "볼");
        else if (strikeCount >= 1)
            System.out.println(strikeCount + "스트라이크");
        else if (ballCount >= 1)
            System.out.println(ballCount + "볼");
        else
            System.out.println("낫싱");

        return strikeCount;
    }
}
