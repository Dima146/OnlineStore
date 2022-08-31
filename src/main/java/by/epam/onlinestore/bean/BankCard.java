package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class BankCard extends Bean implements Serializable {

    private long cardNumber;
    private int expirationYear;
    private int expirationMonth;
    private String cardOwnerName;
    private int cvvNumber;
    private double balance;
    private long userId;

    public BankCard() {
    }

    public BankCard(long id, int cardNumber,
                    int expirationYear, int expirationMonth,
                    String cardOwner, int cvvNumber,
                    double balance, long userInformationId) {

        super(id);
        this.cardNumber = cardNumber;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.cardOwnerName = cardOwner;
        this.cvvNumber = cvvNumber;
        this.balance = balance;
        this.userId = userInformationId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwner) {
        this.cardOwnerName = cardOwner;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return this.getId() == bankCard.getId() && cardNumber == bankCard.cardNumber
                && expirationYear == bankCard.expirationYear && expirationMonth == bankCard.expirationMonth
                && cvvNumber == bankCard.cvvNumber && balance == bankCard.balance
                && userId == bankCard.userId && cardOwnerName.equals(bankCard.cardOwnerName);
    }

    @Override

    public int hashCode() {
        return Objects.hash(this.getId(), cardNumber, expirationYear, expirationMonth,
                cardOwnerName, cvvNumber, balance, userId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", cardNumber=" + cardNumber +
                ", expirationYear=" + expirationYear +
                ", expirationMonth=" + expirationMonth +
                ", cardOwner='" + cardOwnerName + '\'' +
                ", cvvNumber=" + cvvNumber +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
