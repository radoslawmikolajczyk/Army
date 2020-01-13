package baza;

class Messege {         //Klasa, która służy do przekazywania wiadomości
    private String messege;

    Messege(){
        messege = "";
    }

    void kasa(int ile){
        messege = "Za mało pieniędzy, potrzebujesz: " + ile;
    }

    void szeregowy(int ile){
        messege = "Za mało szeregowych, potrzebujesz: " + ile;
    }

    void dowodca(String jaki){
        messege = "Nie posiadasz wymaganego dowódcy - " + jaki;
    }


    void setMessege(String messege) {
        this.messege = messege;
    }

    String getMessege() {
        return messege;
    }
}
