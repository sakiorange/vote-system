import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class VoteSystem {

    static class Vote {
        private List<String> options;
        private List<Integer> votes;

        public Vote() {
            this.options = new ArrayList<>();
            this.votes = new ArrayList<>();
        }
        public void addOption(String option) {
            options.add(option);
            votes.add(0);
        }

        public boolean removeOption(int index) {
            if (index >= 1 && index <= options.size()) {
                options.remove(index - 1);
                votes.remove(index - 1);
                return true;
            }
            return false;
        }

        public void displayOptions() {
            if (options.isEmpty()) {
                System.out.println("目前沒有投票選項。");
            } else {
                System.out.println("可投票選項：");
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ". " + options.get(i)); 
                }
            }
        }

        public void vote(int optionIndex) {
            if (optionIndex >= 1 && optionIndex <= options.size()) {
                votes.set(optionIndex - 1, votes.get(optionIndex - 1) + 1);
                System.out.println("投票成功！您選擇了: " + options.get(optionIndex - 1));
            } else {
                System.out.println("無效選項，請重新選擇！");
            }
        }

        public void displayResults() {
            System.out.println("\n投票結果：");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(options.get(i) + ": " + votes.get(i) + "票");
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.err, true, "UTF-8"));
        
        Scanner scanner = new Scanner(System.in);
        Vote vote = new Vote();

        vote.addOption("鍵盤");
        vote.addOption("滑鼠");

        while (true) {
            System.out.println("歡迎來到投票系統\n");
            System.out.println("請選擇登錄身份：\n");
            System.out.println("1. 遊客\n");
            System.out.println("2. 管理員\n");
            System.out.print("請輸入選擇（1 或 2）：\n");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.println("\n歡迎，遊客！\n");
                while (true) {
                    vote.displayOptions();
                    System.out.print("請選擇投票選項（輸入0結束投票）：");
                    int voteChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (voteChoice == 0) {
                        vote.displayResults();
                        System.out.println("\n投票結束！\n");
                        break;
                    }

                    vote.vote(voteChoice);
                }

            } else if (choice == 2) {
                System.out.println("\n歡迎，管理員！");
                while (true) {
                    System.out.println("\n1. 顯示投票選項  2. 增加選項  3. 刪除選項  4. 顯示投票結果  0. 返回主菜單");

                    System.out.print("\n請選擇操作：\n");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (adminChoice == 0) {
                        break;
                    }

                    switch (adminChoice) {
                        case 1:
                            vote.displayOptions();
                            break;
                        case 2:
                            System.out.print("\n請輸入新選項：");
                            String newOption = scanner.nextLine();
                            vote.addOption(newOption);
                            System.out.println("\n選項已新增！\n");
                            break;
                        case 3:
                            vote.displayOptions();
                            System.out.print("請輸入要刪除的選項序號：");
                            int optionToRemove = scanner.nextInt();
                            scanner.nextLine();
                            if (vote.removeOption(optionToRemove)) {
                                System.out.println("\n選項已刪除！\n");
                            } else {
                                System.out.println("\n無效選項序號，請重新選擇！\n");
                            }
                            break;
                        case 4:
                            vote.displayResults();
                            break;
                        default:
                            System.out.println("\n無效選擇，請重新選擇！\n");
                            break;
                    }
                }
            } else {
                System.out.println("\n無效選擇！\n");
            }
        }
    }
}
