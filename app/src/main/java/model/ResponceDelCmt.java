package model;

public class ResponceDelCmt {
    String mess;

    public ResponceDelCmt(String mess) {
        this.mess = mess;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "ResponceDelCmt{" +
                "mess='" + mess + '\'' +
                '}';
    }
}
