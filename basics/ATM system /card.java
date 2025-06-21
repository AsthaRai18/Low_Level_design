public class card
{
    String holderName;
    String cardNumber;
    String expiryDate;
    String cvv;
    cardType cardType;
    public card(String holderName, String cardNumber, String expiryDate, String cvv, cardType cardType)
    {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardType = cardType;
    }
    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    public void setCvv(String cvv)
    {
        this.cvv = cvv;
    }
    public void setCardType(cardType cardType)
    {
        this.cardType = cardType;
    }
}