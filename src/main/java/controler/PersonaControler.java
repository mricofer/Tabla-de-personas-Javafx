package controler;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Persona;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonaControler implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colEdad;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;

    private ObservableList<Persona> personas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personas = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));

        tblPersonas.setItems(personas);
    }

    @FXML
    private void agregarPersona(ActionEvent event){

        try{
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);

            if(!this.personas.contains(p)){
                this.personas.add(p);
                this.tblPersonas.setItems(personas);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La edad debe ser un número entero");
            alert.showAndWait();
        }

    }

    @FXML
    private void seleccionar(MouseEvent event){
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p != null){
            this.txtNombre.setText(p.getNombre());
            this.txtApellidos.setText(p.getApellidos());
            this.txtEdad.setText(String.valueOf(p.getEdad()));
        }
    }
    @FXML
    private void eliminarPersona(ActionEvent event){
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ninguna persona");
            alert.showAndWait();
        }else{
            this.personas.remove(p);
            this.tblPersonas.refresh();
        }
    }
    @FXML
    private void modificarPersona(ActionEvent event){

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ninguna persona");
            alert.showAndWait();
        }else{
            try{
                String nombre = this.txtNombre.getText();
                String apellidos = this.txtApellidos.getText();
                int edad = Integer.parseInt(this.txtEdad.getText());

                Persona aux = new Persona(nombre, apellidos, edad);

                if(!this.personas.contains(aux)){
                    p.setNombre(aux.getNombre());
                    p.setApellidos(aux.getApellidos());
                    p.setEdad(aux.getEdad());

                    this.tblPersonas.refresh();

                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona ya existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La edad debe ser un número entero");
                alert.showAndWait();
            }
        }

    }
}
