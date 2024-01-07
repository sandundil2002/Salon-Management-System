package lk.ijse.salon.bo.custom;

import lk.ijse.salon.bo.SuperBO;
import lk.ijse.salon.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductsBO extends SuperBO{
    List<ProductDto> loadAllProducts() throws SQLException,ClassNotFoundException;

    boolean saveProduct(ProductDto dto) throws SQLException,ClassNotFoundException;

    boolean updateProduct(ProductDto dto) throws SQLException,ClassNotFoundException;

    ProductDto searchProduct(String id) throws SQLException,ClassNotFoundException;

    boolean deleteProduct(String id) throws SQLException,ClassNotFoundException;

    String generateProductId() throws SQLException,ClassNotFoundException;
}
