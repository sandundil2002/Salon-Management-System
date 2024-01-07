package lk.ijse.salon.bo.custom.impl;

import lk.ijse.salon.bo.custom.ProductsBO;
import lk.ijse.salon.dao.DAOFactory;
import lk.ijse.salon.dao.custom.ProductsDAO;
import lk.ijse.salon.dto.ProductDto;
import lk.ijse.salon.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsBOImpl implements ProductsBO {
    ProductsDAO productsDAO = (ProductsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    @Override
    public List<ProductDto> loadAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = productsDAO.loadAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products){
            productDtos.add(new ProductDto(
                    product.getProduct_id(),
                    product.getGetDate(),
                    product.getQty(),
                    product.getPrice(),
                    product.getType(),
                    product.getDescription(),
                    product.getProduct_name()));
        }
        return productDtos;
    }

    @Override
    public boolean saveProduct(ProductDto dto) throws SQLException, ClassNotFoundException {
        return productsDAO.save(new Product(
                dto.getProduct_id(),
                dto.getGetDate(),
                dto.getQty(),
                dto.getPrice(),
                dto.getType(),
                dto.getDescription(),
                dto.getProduct_name()));
    }

    @Override
    public boolean updateProduct(ProductDto dto) throws SQLException, ClassNotFoundException {
        return productsDAO.update(new Product(
                dto.getProduct_id(),
                dto.getGetDate(),
                dto.getQty(),
                dto.getPrice(),
                dto.getType(),
                dto.getDescription(),
                dto.getProduct_name()));
    }

    @Override
    public ProductDto searchProduct(String id) throws SQLException, ClassNotFoundException {
        Product product = productsDAO.search(id);
        ProductDto productDto = new ProductDto(
                product.getProduct_id(),
                product.getGetDate(),
                product.getQty(),
                product.getPrice(),
                product.getType(),
                product.getDescription(),
                product.getProduct_name());
        return productDto;
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productsDAO.delete(id);
    }

    @Override
    public String generateProductId() throws SQLException, ClassNotFoundException {
        return productsDAO.generateId();
    }
}
