package FinalP;

class MessageS {
  String type;
  String input;
  int number;

  protected MessageS() {
    this.type = "";
    this.input = "";
    this.number = 0;
    System.out.println("client-side message created");
  }

  protected MessageS(String type, String input, int number) {
    this.type = type;
    this.input = input;
    this.number = number;
    System.out.println("client-side message created");
  }
}