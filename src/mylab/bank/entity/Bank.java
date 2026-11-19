package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {

    private List<Account> accounts;		// 다형성
    private int nextAccountNumber;

    // 생성자
    public Bank() {
        accounts = new ArrayList<Account>();	// ArrayList 객체 생성
        nextAccountNumber = 1000;				// 시작 계좌번호
    }

    // 계좌번호 자동 생성
    private String issueAccountNumber() {
        return "AC" + (nextAccountNumber++);
    }

    // 저축 계좌 생성
    public SavingsAccount createSavingsAccount(
            String ownerName, double balance,
            double interestRate) {

        SavingsAccount account = new SavingsAccount(
            issueAccountNumber(), ownerName,
            balance, interestRate);

        accounts.add(account);
        return account;
    }

    // 체킹 계좌 생성
    public CheckingAccount createCheckingAccount(
            String ownerName, double balance,
            double limit) {

        CheckingAccount account = new CheckingAccount(
            issueAccountNumber(), ownerName,
            balance, limit);

        accounts.add(account);
        return account;
    }

    // 계좌 검색
    public Account findAccount(String accountNumber)
            throws AccountNotFoundException {

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        throw new AccountNotFoundException(
            "계좌번호 " + accountNumber
            + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    // 입금
    public void deposit(String accountNumber, double amount)
            throws AccountNotFoundException {

        findAccount(accountNumber).deposit(amount);
    }

    // 출금
    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        findAccount(accountNumber).withdraw(amount);
    }

    // 계좌 이체
    public void transfer(String fromNumber,
                         String toNumber,
                         double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);

        from.withdraw(amount);
        to.deposit(amount);

        System.out.println(
            amount + "원이 " + fromNumber
            + "에서 " + toNumber + "로 송금되었습니다.");
    }

    // 모든 계좌 반환
    public List<Account> getAllAccounts() {
        return new ArrayList<Account>(accounts);
    }

    // 모든 계좌 출력
    public void printAllAccounts() {

        System.out.println("\n=== 모든 계좌 목록 ===");

        for (Account account : accounts) {
            System.out.println(account);
        }

        System.out.println("===================");
    }
}
