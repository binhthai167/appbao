package model;

public class ResponeCmt {

   String mess;


    public ResponeCmt(String messs) {
        this.mess = mess;
    }

    public String getMess() {
        return mess;
    }

    public void setMesss(String messs) {
        this.mess = messs;
    }

    @Override
    public String toString() {
        return "ResponeCmt{" +
                "mess='" + mess + '\'' +
                '}';
    }
}
