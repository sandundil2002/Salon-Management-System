package lk.ijse.salon.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.salon.bo.custom.ProductsBO;
import lk.ijse.salon.bo.custom.impl.ProductsBOImpl;
import lk.ijse.salon.dto.ProductDto;
import lk.ijse.salon.dto.tm.productTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ProductFormController {
        @FXML
        private TableColumn<?, ?> date;

        @FXML
        private TableColumn<?, ?> discription;

        @FXML
        private TableColumn<?, ?> item_type;

        @FXML
        private TableColumn<?, ?> name;

        @FXML
        private TableColumn<?, ?> p_id;

        @FXML
        private TableColumn<?, ?> price;

        @FXML
        private TableColumn<?, ?> qty;

        @FXML
        private TableView<productTm> table;

        ProductsBO productsBO = new ProductsBOImpl();

        public void initialize(){
            try {
                loadAllProduct();
                setCellValueFactory();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private void setCellValueFactory() {
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                discription.setCellValueFactory(new PropertyValueFactory<>("dis"));
                item_type.setCellValueFactory(new PropertyValueFactory<>("item_type"));
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                p_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
                qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                price.setCellValueFactory(new PropertyValueFactory<>("price"));
                }

        private void loadAllProduct() throws SQLException, ClassNotFoundException {
                List<ProductDto> allProduct = productsBO.loadAllProducts();
                ObservableList<productTm> list=FXCollections.observableArrayList();
                for (ProductDto dto:allProduct){
                        list.add(new productTm(
                                dto.getProduct_id(),
                                dto.getProduct_name(),
                                dto.getGetDate(),
                                dto.getQty(),
                                dto.getPrice(),
                                dto.getType(),
                                dto.getDescription()

                        ));
                }
                table.setItems(list);
        }

        @FXML
        void btnManageProductOnAction(ActionEvent event) {
                try {
                        URL resource = ProductManageFormController.class.getResource("/View/ProductManageForm.fxml");
                        Parent parent = FXMLLoader.load(resource);
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Mona Beauty Salon");
                        stage.setScene(scene);
                        stage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
