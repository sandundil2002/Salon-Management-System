package lk.ijse.salon.model;

import javafx.collections.FXCollections;
import lk.ijse.salon.dto.ProductDto;
import lk.ijse.salon.util.SQLUtil;
import lombok.SneakyThrows;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductModel {
    public boolean saveProduct(ProductDto dto) throws SQLException {
        return SQLUtil.execute("insert into product values (?,?,?,?,?,?,?)", dto.getProduct_id(), dto.getGetDate(), dto.getQty(), dto.getPrice(), dto.getType(), dto.getDescription(), dto.getProduct_name());
    }

    public ProductDto searchProduct(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("Select * from product where product_id = ?",id);
        ProductDto dto = null;

        if (resultSet.next()){
            dto = new ProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return dto;
    }

    public boolean updateProduct(ProductDto dto) throws SQLException {
        return SQLUtil.execute("update product set getDate=?, qty=?,price=?,itemType = ?, description = ?, product_name = ? where product_id = ?", dto.getProduct_id(), dto.getGetDate(), dto.getQty(), dto.getPrice(), dto.getType(), dto.getDescription(), dto.getProduct_name());
    }

    public boolean deleteProduct(String id) throws SQLException {
        return SQLUtil.execute("delete from product where product_id = ?"+id);
    }

    @SneakyThrows
    public List<ProductDto> getAllProduct() {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product");
        List<ProductDto> dto = FXCollections.observableArrayList();
        while (resultSet.next()){
            dto.add(new ProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7))
            );
        }
        return dto;
    }
}
