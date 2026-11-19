package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.entity.CheckingAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class BankDemo {

    public static void main(String[] args) {

        Bank bank = new Bank();

        // 계좌 생성
        System.out.println("=== 계좌 생성 ===");

        SavingsAccount savings1 =
            bank.createSavingsAccount("홍길동", 10000, 0.03);

        CheckingAccount checking =
            bank.createCheckingAccount("김철수", 20000, 5000);

        SavingsAccount savings2 =
            bank.createSavingsAccount("이영희", 30000, 0.02);

        System.out.println(
            "Saving(저축) 계좌가 생성되었습니다: " + savings1);

        System.out.println(
            "체킹 계좌가 생성되었습니다: " + checking);

        System.out.println(
            "저축 계좌가 생성되었습니다: " + savings2);

        bank.printAllAccounts();

        // 정상 동작 테스트
        try {
            System.out.println("\n=== 입금/출금 테스트 ===");

            bank.deposit("AC1000", 5000);
            bank.withdraw("AC1001", 3000);

            System.out.println("\n=== 이자 적용 테스트 ===");

            savings1.applyInterest();

            System.out.println("\n=== 계좌 이체 테스트 ===");

            bank.transfer("AC1002", "AC1001", 5000);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        bank.printAllAccounts();

        // 출금 한도 초과 테스트
        try {
            bank.withdraw("AC1001", 6000);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // 이체 시 출금 한도 초과 테스트
        try {
            bank.transfer("AC1001", "AC1000", 6000);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // 잔액 부족 테스트
        try {
            bank.withdraw("AC1002", 40000);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // 존재하지 않는 계좌 테스트
        try {
            bank.withdraw("AC9999", 1000);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
