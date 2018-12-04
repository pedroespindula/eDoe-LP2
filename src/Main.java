import easyaccept.EasyAccept;

public class Main {

  public static void main(String[] args) {
    String[] easyAccept = new String[]{"edoe.EDoeFacade",
      "acceptance_tests/use_case_1.txt",
      "acceptance_tests/use_case_2.txt",
      "acceptance_tests/use_case_3.txt",
      "acceptance_tests/use_case_4.txt",
      "acceptance_tests/use_case_5.txt",
      //"acceptance_tests/use_case_6.txt",
      //"acceptance_tests/use_case_7.txt",
    };
    EasyAccept.main(easyAccept);
  }

}
