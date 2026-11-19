package mylab.bank.entity;

// Account 상속
public class SavingsAccount extends Account {

    private double interestRate;

    // 생성자
    public SavingsAccount(String accountNumber,
                          String ownerName,
                          double balance,
                          double interestRate) {

        super(accountNumber, ownerName, balance);	// 부모 클래스 Account 호출

        if (interestRate < 0) {
            throw new IllegalArgumentException(
                "이자율은 음수일 수 없습니다.");
        }

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException(
                "이자율은 음수일 수 없습니다.");
        }
        this.interestRate = interestRate;
    }

    // 이자 적용
    public void applyInterest() {

        double interest = getBalance() * interestRate;	// 이자 계산

        if (interest > 0) {
            deposit(interest);
        }

        System.out.println(
            "이자 " + interest
            + "원이 적용되었습니다. 현재 잔액: "
            + getBalance() + "원");
    }

    @Override
    public String toString() {
        return super.toString()
            + ", 이자율: " + (interestRate * 100) + "%";
    }
}
