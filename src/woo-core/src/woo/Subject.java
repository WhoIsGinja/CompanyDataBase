package woo;

public interface Subject {
    public void attachObserver(Observer observer);
    public boolean dettachObserver(Observer observer);

    public void notifyNew();
    public void notifyBargain();
}