package model;

public class ResponeSignup {
    String mess;

    public String   getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public ResponeSignup(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "ResponeSignup{" +
                "mess='" + mess + '\'' +
                '}';
    }
}
