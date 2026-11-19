package mylab.bank.exception;

// 출금 한도 초과 시 발생
// InsufficientBalanceException 상속
public class WithdrawalLimitExceededException
        extends InsufficientBalanceException {

    private static final long serialVersionUID = 1L;

    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
