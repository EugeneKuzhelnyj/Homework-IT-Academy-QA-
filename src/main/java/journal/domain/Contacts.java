package journal.domain;

import java.util.Objects;

public class Contacts {

    private String address;
    private String telephone;
    private String email;
    private String url;

    public Contacts() {
    }

    public Contacts(String address, String telephone, String email, String url) {
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(address, contacts.address) && Objects.equals(telephone, contacts.telephone) && Objects.equals(email, contacts.email) && Objects.equals(url, contacts.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, telephone, email, url);
    }

    @Override
    public String toString() {
        return "Contacts: " + "address = " + address + ", telephone = " + telephone + ",email = " + email + ",url = " + url;
    }
}
