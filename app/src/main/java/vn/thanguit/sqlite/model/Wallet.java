package vn.thanguit.sqlite.model;

public class Wallet {
    private int id;
    private String address = "";
    private String name = "";
    private String balance = "";
    private String pendingBalance = "";
    private String totalBalance = "";

    public Wallet() {
    }

    public Wallet(String address, String balance, String pendingBalance) {
        this.address = address;
        this.balance = balance;
        this.pendingBalance = pendingBalance;
    }

    public Wallet(int id,
                  String address,
                  String name,
                  String balance,
                  String pendingBalance,
                  String totalBalance) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.balance = balance;
        this.pendingBalance = pendingBalance;
        this.totalBalance = totalBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPendingBalance() {
        return pendingBalance;
    }

    public void setPendingBalance(String pendingBalance) {
        this.pendingBalance = pendingBalance;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                ", pendingBalance='" + pendingBalance + '\'' +
                ", totalBalance='" + totalBalance + '\'' +
                '}';
    }
}

