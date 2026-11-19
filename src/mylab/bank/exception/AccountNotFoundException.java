package mylab.bank.exception;

// 존재하지 않는 계좌번호 입력 시 발생
public class AccountNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);
    }
}
