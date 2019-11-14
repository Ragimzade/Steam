package model;

public interface IAccessibleElement {

    boolean isClickable(int timeout);

    void click();

    void sendKeys( );

}
