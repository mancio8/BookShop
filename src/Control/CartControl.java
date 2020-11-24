package Control;

/**
 * Sample Skeleton for 'Cart.fxml' Controller Class
 */

import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.ResourceBundle;

import DAO.*;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CartControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="TotalL"
    private Label TotalL; // Value injected by FXMLLoader

    @FXML // fx:id="CartList"
    private ListView<String> CartList; // Value injected by FXMLLoader

    @FXML // fx:id="DeletButton"
    private Button DeletButton; // Value injected by FXMLLoader

    @FXML // fx:id="LabeMail"
    private Label LabeMail; // Value injected by FXMLLoader

    @FXML // fx:id="IdRichiesta"
    private TextField IdRichiesta; // Value injected by FXMLLoader

    @FXML // fx:id="Payement"
    private SplitMenuButton Payement; // Value injected by FXMLLoader



    @FXML // fx:id="DeleteId"
    private TextField DeleteId; // Value injected by FXMLLoader

    @FXML
    private Label LPay;

    @FXML // fx:id="PriceT"
    private Label PriceT; // Value injected by FXMLLoader

    @FXML // fx:id="CartadiCredito"
    private MenuItem CartadiCredito; // Value injected by FXMLLoader

    @FXML // fx:id="CarrelloB"
    private Button CarrelloB; // Value injected by FXMLLoader

    @FXML // fx:id="Contanti"
    private MenuItem Contanti; // Value injected by FXMLLoader

    @FXML // fx:id="LCartidP"
    private Label LCartidP; // Value injected by FXMLLoader

    @FXML // fx:id="lrichiesta"
    private Label lrichiesta; // Value injected by FXMLLoader

    @FXML // fx:id="CreateRequestB"
    private Button CreateRequestB; // Value injected by FXMLLoader

    @FXML
    private Label LidEsistente;

    @FXML
    private ImageView screenblue;

    @FXML
    private ImageView Icart;

    @FXML
    private ImageView Ieuro;

    @FXML
    private ImageView Ivisa;

    @FXML // fx:id="Tquantita"
    private TextField Tquantita; // Value injected by FXMLLoader

    @FXML // fx:id="Lquantita"
    private Label Lquantita; // Value injected by FXMLLoader

    @FXML // fx:id="LEdshop"
    private Label LEdshop; // Value injected by FXMLLoader

    @FXML // fx:id="Lindirizzo"
    private Label Lindirizzo; // Value injected by FXMLLoader

    @FXML // fx:id="Ldata"
    private Label Ldata; // Value injected by FXMLLoader

    @FXML // fx:id="LSconto"
    private Label LSconto; // Value injected by FXMLLoader

    @FXML // fx:id="LSconto1"
    private Label LSconto1; // Value injected by FXMLLoader

    @FXML // fx:id="ScontoL"
    private Label ScontoL; // Value injected by FXMLLoader

    @FXML // fx:id="ScontoL1"
    private Label ScontoL1; // Value injected by FXMLLoader

    String accesso=null;
    String idC=null;
    String prodotto=null;
    double prezzo_unitario=0;
    double prezzo_totale=0;
    int quantita=0;
    double sconto=00.00;
    int punti=0;



    public static Connection createConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(DBURL, USER, PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }


    @FXML
    public boolean DeleteP(ActionEvent event) {
        searchforC();

        if(Tquantita.getText().equals("")){
            Tquantita.setText("1");
        }
        if (Integer.parseInt(Tquantita.getText())>quantita){
            Tquantita.setText(String.valueOf(quantita));
        }
        if(quantita==1 || quantita==Integer.parseInt(Tquantita.getText())){

                if(DeleteId.getText().equalsIgnoreCase(prodotto)){
                    boolean p= updateP();
                    boolean ac= updateArtC();
                    boolean l= updateL();
                    Connection conn = null;
                    PreparedStatement preparedStatement = null;
                    try {
                        conn = createConnection();
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, DeleteId.getText());
                        preparedStatement.execute();
                        Prodotti_CarrelloDAO.getInstance().load();
                        String accesso=null;
                        singin = SingInDAO.getInstance().getSingIn();
                        for (SingIn s : singin){
                            accesso= s.getEmail();
                        }
                        LabeMail.setText(accesso);

                        carrelli = Prodotti_CarrelloDAO.getInstance().getP_Carrello();
                        ObservableList<String> pro = null;
                        ObservableList<Integer> quan = null;
                        ObservableList<Double> pre = null;
                        CartList.getItems().clear();

                        for ( Prodotti_Carrello cart : carrelli){
                            if(cart.getIdC().equalsIgnoreCase(idC)){
                                prezzo_totale=prezzo_totale;
                                pro = FXCollections.observableArrayList(cart.getProdotto().getId());
                                quan=  FXCollections.observableArrayList(cart.getQuantita());
                                pre=  FXCollections.observableArrayList(cart.getPrezzoTot());
                                CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
                            }



                        }


                        PriceT.setText(String.valueOf(prezzo_totale));
                        return true;

                    } catch(SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            preparedStatement.close();
                        } catch(Exception sse) {
                            sse.printStackTrace();
                        }
                        try {
                            conn.close();
                        } catch(Exception cse) {
                            cse.printStackTrace();
                        }
                    }

                    return false;
                }else{

                    DeleteId.setText("Id non trovato");
                }
            }

        else {
            boolean p= updateP();
            boolean ac= updateArtC();
            boolean l= updateL();
            Connection conn = null;
            PreparedStatement ps = null;
            double prezzoto=prezzo_totale-(prezzo_unitario*Integer.parseInt(Tquantita.getText()));
            double number2 = (int)(Math.round(prezzoto * 100))/100.0;
            try {
                conn = createConnection();
                ps = conn.prepareStatement(UPDATE_QUERY);
                ps.setInt(1, quantita-Integer.parseInt(Tquantita.getText()));
                ps.setDouble(2,number2 );
                ps.setString(3, DeleteId.getText());
                ps.execute();
                Prodotti_CarrelloDAO.getInstance().load();
                String accesso=null;
                singin = SingInDAO.getInstance().getSingIn();
                for (SingIn s : singin){
                    accesso= s.getEmail();
                }
                LabeMail.setText(accesso);
                double prezzotot=0;
                carrelli = Prodotti_CarrelloDAO.getInstance().getP_Carrello();
                ObservableList<String> pro = null;
                ObservableList<Integer> quan = null;
                ObservableList<Double> pre = null;
                CartList.getItems().clear();

                for ( Prodotti_Carrello cart : carrelli){
                    if(cart.getIdC().equalsIgnoreCase(idC)){
                        prezzotot=prezzotot+cart.getPrezzoTot();
                        pro = FXCollections.observableArrayList(cart.getProdotto().getId());
                        quan=  FXCollections.observableArrayList(cart.getQuantita());
                        pre=  FXCollections.observableArrayList(cart.getPrezzoTot());
                        CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
                    }



                }


                PriceT.setText(String.valueOf(prezzotot));
                return true;
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch(Exception sse) {
                    sse.printStackTrace();
                }
                try {
                    conn.close();
                } catch(Exception cse) {
                    cse.printStackTrace();
                }
            }
            return false;
        }


       return false;
    }

    public boolean addR() {
        searchforC();
        Connection conn = null;
        PreparedStatement ps = null;
        carrelli = Prodotti_CarrelloDAO.getInstance().getP_Carrello();
        Date data1=new Date (System.currentTimeMillis());
        double prezzotot=0;

        for ( Prodotti_Carrello c : carrelli) {
            if (c.getIdC().equalsIgnoreCase(idC)) {
                prezzotot = prezzotot + c.getPrezzoTot();
            }
        }

        if(prezzotot > 0){
            try {
                double number2 = (int)(Math.round((prezzotot-sconto) * 100))/100.0;

                conn = createConnection();
                ps = conn.prepareStatement(CREATE_QUERY_R);

                ps.setString(1, IdRichiesta.getText());
                ps.setDate(2, data1);
                ps.setDouble(3, number2);
                ps.setString(4, LPay.getText());

                ps.setString(5, null);
                ps.setString(6, LabeMail.getText());

                ps.execute();
                RichiestaDAO.getInstance().load();
                return true;

            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch(Exception sse) {
                    sse.printStackTrace();
                }
                try {
                    conn.close();
                } catch(Exception cse) {
                    cse.printStackTrace();
                }
            }
            return false;
        }
        LidEsistente.setText("Carrello vuoto");
      return false;
    }

    @FXML
    public boolean NewRequest(ActionEvent event) {
        show();
        viewScontrino();
        add_Pag();


        if(LPay.getText().equals("")){
            LPay.setText("Contanti");
        }
        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
        clienti = ClienteDAO.getInstance().getCliente();
        richieste = RichiestaDAO.getInstance().getRichiesta();
        for(RichiestaAcquisto r : richieste){
            if(r.getIdA().equalsIgnoreCase(IdRichiesta.getText())){
                LidEsistente.setText("Id già esistente");
                return false;
            }

        }
        for (Cliente c : clienti){
           if(IdRichiesta.getText().equals("")){
                LidEsistente.setText("Dati Errati");

                return false;
            }
            else{
               LidEsistente.setText("Richiesta inviata");

               boolean b = addR();
               boolean d= deleteCart();

                return b;



            }
        }


        return false;



    }




    @FXML
    public void ShowList(ActionEvent event) {
        searchforC();

        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
        double prezzotot=0;
        carrelli = Prodotti_CarrelloDAO.getInstance().getP_Carrello();
        ObservableList<String> pro = null;
        ObservableList<Integer> quan = null;
        ObservableList<Double> pre = null;
        CartList.getItems().clear();

        for ( Prodotti_Carrello c : carrelli){
            if(c.getIdC().equalsIgnoreCase(idC)){
                prezzotot=prezzotot+c.getPrezzoTot();
                pro = FXCollections.observableArrayList(c.getProdotto().getId());
                quan=  FXCollections.observableArrayList(c.getQuantita());
                pre=  FXCollections.observableArrayList(c.getPrezzoTot());
                CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
            }



        }


     PriceT.setText(String.valueOf(prezzotot));


    }

    public void show(){
        searchforC();

        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
        double prezzotot=0;
        carrelli = Prodotti_CarrelloDAO.getInstance().getP_Carrello();
        ObservableList<String> pro = null;
        ObservableList<Integer> quan = null;
        ObservableList<Double> pre = null;
        CartList.getItems().clear();

        for ( Prodotti_Carrello c : carrelli){
            if(c.getIdC().equalsIgnoreCase(idC)){
                prezzotot=prezzotot+c.getPrezzoTot();
                pro = FXCollections.observableArrayList(c.getProdotto().getId());
                quan=  FXCollections.observableArrayList(c.getQuantita());
                pre=  FXCollections.observableArrayList(c.getPrezzoTot());
                CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
            }



        }


        PriceT.setText(String.valueOf(prezzotot));
    }

    @FXML
    void Addmail(MouseEvent mouseEvent){
        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
           accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
    }
    @FXML
    void PayContanti(ActionEvent event) {
        LPay.setText("Contanti");
    }

    @FXML
    void Paycreditcard(ActionEvent event) {
        LPay.setText("CartadiCredito");
    }

    public boolean updateP() {
        Double prezzo=0.00;
        int quantita=0;
        int quant=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for(Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                prezzo=p.getPrezzo();
                quantita=p.getQuantita();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        carrelli =Prodotti_CarrelloDAO.getInstance().getP_Carrello();
        for (Prodotti_Carrello car : carrelli){
            if(car.getProdotto().getId().equalsIgnoreCase(DeleteId.getText())){
                quant= car.getQuantita();
            }
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_P);
            //ps.setString(1, TFQuantitaUp);
            ps.setInt(1,quantita+Integer.parseInt(Tquantita.getText()));
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());

            ps.execute();
            ProdottoDAO.getInstance().load();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateArtC() {


        Connection conn = null;
        PreparedStatement ps = null;
        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_AC);
            ps.setInt(1, Quantita);
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());
            ps.execute();
            ArticoliCDAO.getInstance().load();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateL() {
        Connection conn = null;
        PreparedStatement ps = null;
        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_L);
            ps.setInt(1, Quantita);
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());
            ps.execute();
            LibroDAO.getInstance().load();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }
    public boolean deleteCart() {
        clienti = ClienteDAO.getInstance().getCliente();
        for(Cliente c : clienti){
            if(c.getEmail().equals(LabeMail.getText())){
                Connection conn = null;
                PreparedStatement preparedStatement = null;
                try {
                    conn = createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY_CART);
                    preparedStatement.setString(1, LabeMail.getText());
                    preparedStatement.execute();
                    Prodotti_CarrelloDAO.getInstance().load();
                    return true;
                } catch(SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        preparedStatement.close();
                    } catch(Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn.close();
                    } catch(Exception cse) {
                        cse.printStackTrace();
                    }
                }
                return false;
            }
        }
        return false;

    }



    public void searchforC(){
        String product=null;
        double prezzo_u=0;
        double prezzo_t=0;
        int quant=0;

        singin=SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso=s.getEmail();
        }
        LabeMail.setText(accesso);
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String idcart=null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY);

            preparedStatement.setString(1, LabeMail.getText());
            preparedStatement.execute();
            CarrelloDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                idC = rs.getString("IdC");
                product= rs.getString("Prodotto");
                quant=rs.getInt("Quantità");
                prezzo_u= rs.getDouble("Prezzo_unitario");
                prezzo_t=rs.getDouble("Prezzo_Totale");

                if(product.equalsIgnoreCase(DeleteId.getText())){
                    prodotto=product;
                    prezzo_unitario=prezzo_u;
                    prezzo_totale=prezzo_t;
                    quantita=quant;
                }
            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    public boolean add_Pag() {
        sconto_P();
        double prezzotot=00.00;
        Connection conn = null;
        PreparedStatement ps = null;
        Date data1=new Date (System.currentTimeMillis());
        for ( Prodotti_Carrello c : carrelli) {
            if (c.getIdC().equalsIgnoreCase(idC)) {
                prezzotot = prezzotot + c.getPrezzoTot();
            }
        }
        try {
            double number2 = (int)(Math.round((prezzotot-sconto) * 100))/100.0;
            if(number2>50 && sconto>0){
                punti_up=-45;
                updatePunti();
            }else if(sconto >0){
                punti_up=-50;
                updatePunti();
            }else if(number2>50){
                punti_up=+5;
                updatePunti();
            }

            conn = createConnection();
            ps = conn.prepareStatement(CREATE_QUERY_P);

            ps.setString(1, accesso);
            ps.setDate(2, (java.sql.Date) data1);
            ps.setDouble(3, sconto);
            ps.setDouble(4, number2);



            ps.execute();
            PagamentoDAO.getInstance().load();
            return true;

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

    public void sconto_P(){
        searchfor_CF();
        if(punti>= 50){

            sconto=5;

        }else{
            sconto=0;
        }
    }

 int punti_up=0;

    public boolean updatePunti() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_Punti);
            ps.setInt(1, punti+punti_up );
            ps.setString(2, accesso);
            ps.execute();
            CartaFedDAO.getInstance().load();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }
    public void searchfor_CF(){



        Connection conn = null;
        PreparedStatement preparedStatement = null;


        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY_CF);

            preparedStatement.setString(1, accesso);
            preparedStatement.execute();
            CartaFedDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){

                punti=rs.getInt("Punti");



            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
    }

    public void viewScontrino(){
        double prezzotot=00.00;
        Date data1=new Date (System.currentTimeMillis());
        for ( Prodotti_Carrello c : carrelli) {
            if (c.getIdC().equalsIgnoreCase(idC)) {
                prezzotot = prezzotot + c.getPrezzoTot();
            }
        }
        double number2 = (int)(Math.round((prezzotot-sconto) * 100))/100.0;
        LEdshop.setText("EdOnlineShop");
        Lindirizzo.setText("Via Roma 1, Benevento (Bn)");
        LSconto.setText("-"+String.valueOf(sconto));
        LSconto1.setText(String.valueOf(number2));

        Ldata.setText(String.valueOf(data1));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screenblue != null : "fx:id=\"screenblue\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CartList != null : "fx:id=\"CartList\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LabeMail != null : "fx:id=\"LabeMail\" was not injected: check your FXML file 'Cart.fxml'.";
        assert DeleteId != null : "fx:id=\"DeleteId\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LCartidP != null : "fx:id=\"LCartidP\" was not injected: check your FXML file 'Cart.fxml'.";
        assert DeletButton != null : "fx:id=\"DeletButton\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CreateRequestB != null : "fx:id=\"CreateRequestB\" was not injected: check your FXML file 'Cart.fxml'.";
        assert TotalL != null : "fx:id=\"TotalL\" was not injected: check your FXML file 'Cart.fxml'.";
        assert PriceT != null : "fx:id=\"PriceT\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CarrelloB != null : "fx:id=\"CarrelloB\" was not injected: check your FXML file 'Cart.fxml'.";
        assert IdRichiesta != null : "fx:id=\"IdRichiesta\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Payement != null : "fx:id=\"Payement\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Contanti != null : "fx:id=\"Contanti\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CartadiCredito != null : "fx:id=\"CartadiCredito\" was not injected: check your FXML file 'Cart.fxml'.";
        assert lrichiesta != null : "fx:id=\"lrichiesta\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LPay != null : "fx:id=\"LPay\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LidEsistente != null : "fx:id=\"LidEsistente\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Icart != null : "fx:id=\"Icart\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Ieuro != null : "fx:id=\"Ieuro\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Ivisa != null : "fx:id=\"Ivisa\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Tquantita != null : "fx:id=\"Tquantita\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Lquantita != null : "fx:id=\"Lquantita\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LEdshop != null : "fx:id=\"LEdshop\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Lindirizzo != null : "fx:id=\"Lindirizzo\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Ldata != null : "fx:id=\"Ldata\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LSconto != null : "fx:id=\"LSconto\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LSconto1 != null : "fx:id=\"LSconto1\" was not injected: check your FXML file 'Cart.fxml'.";
        assert ScontoL != null : "fx:id=\"ScontoL\" was not injected: check your FXML file 'Cart.fxml'.";
        assert ScontoL1 != null : "fx:id=\"ScontoL1\" was not injected: check your FXML file 'Cart.fxml'.";



    }
    private LinkedList<Prodotti_Carrello> carrelli;
    private static final String READ_ALL_QUERY = "SELECT * FROM carrelli";
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";
    private static final String DELETE_QUERY= "DELETE FROM prodotti_carrello WHERE Prodotto =(?) ";
    private static final String READ_ALL_QUERY_R = "SELECT * FROM richieste";
    private static final String CREATE_QUERY_R ="INSERT INTO richieste VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_QUERY_P = "UPDATE prodotti SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private LinkedList<Prodotto> prodotti;
    private LinkedList<RichiestaAcquisto> richieste;
    private LinkedList<Cliente> clienti;
    private LinkedList<SingIn> singin;
    private static final String UPDATE_QUERY_L = "UPDATE libri SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_AC = "UPDATE articoli SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String DELETE_QUERY_CART= "DELETE FROM prodotti_carrello WHERE Cliente =(?) ";
    private static final String UPDATE_QUERY = "UPDATE prodotti_carrello SET Quantità=(?), Prezzo_totale=(?) WHERE Prodotto=(?) ";
    private static final String Search_ALL_QUERY = "SELECT IdC, Prodotto, quantità, prezzo_unitario, Prezzo_Totale FROM prodotti_carrello WHERE Cliente=(?) ";
    private static final String CREATE_QUERY_P ="INSERT INTO pagamenti VALUES(?,?,?,?)";
    private static final String Search_ALL_QUERY_CF = "SELECT Punti FROM carte WHERE Email=(?) ";
    private static final String UPDATE_QUERY_Punti = "UPDATE carte SET punti =(?) where Email=(?) ";

    public void Alphabetic(javafx.scene.input.KeyEvent keyEvent) {
        Tquantita.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                Tquantita.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}

