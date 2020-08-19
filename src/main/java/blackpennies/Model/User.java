package blackpennies.Model;

import javax.validation.constraints.*;
import java.net.URL;
import java.text.DecimalFormat;

public class User {

    public static int globalId;

    private int id;

    private URL picture;


    @NotNull(message = "Nickname is mandatory")
    @NotBlank(message = "Nickname is mandatory")
    @Size(min = 3, max = 64)
    private String nickname;

    @NotNull(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, max = 64)
    private String password;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;


    private double balance;


    public User(){
        id = setID();
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public double getBalance() {
        return (double) Math.round(balance * 100d) /100d;
    }

    public boolean canCredit(double amount) {
        return amount <= balance;
    }

    public void addBalance(double amount){
        balance += amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean removeBalance(double amount) {
        if(!canCredit(amount)){
            return false;
        }
        balance -= amount;
        return true;
    }

    public int setID() {
        return ++globalId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public int getId() {
        return id;
    }
}
