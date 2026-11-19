package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {

    private double withdrawalLimit;

    // 생성자
    public CheckingAccount(String accountNumber,
                           String ownerName,
                           double balance,
                           double withdrawalLimit) {

        super(accountNumber, ownerName, balance);

        if (withdrawalLimit < 0) {
            throw new IllegalArgumentException(
                "출금 한도는 음수일 수 없습니다.");
        }

        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        if (withdrawalLimit < 0) {
            throw new IllegalArgumentException(
                "출금 한도는 음수일 수 없습니다.");
        }

        this.withdrawalLimit = withdrawalLimit;
    }

    // 출금 기능 오버라이딩
    @Override
    public void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                "출금 한도를 초과했습니다. 한도: "
                + withdrawalLimit + "원");
        }

        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return super.toString()
            + ", 출금 한도: " + withdrawalLimit + "원";
    }
}
