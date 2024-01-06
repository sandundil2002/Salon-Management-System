package lk.ijse.salon.dao.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.salon.dao.custom.ProductsDAO;
import lk.ijse.salon.entity.Product;
import lk.ijse.salon.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductsDAOImpl implements ProductsDAO {
    @Override
    public List<Product> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM product");
        List<Product> proList = FXCollections.observableArrayList();
        while (resultSet.next()){
            Product productDTO = new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
            proList.add(productDTO);
        }
        return proList;
    }

    @Override
    public boolean save(Product dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into product values (?,?,?,?,?,?,?)",
                dto.getProduct_id(),
                dto.getGetDate(),
                dto.getQty(),
                dto.getPrice(),
                dto.getType(),
                dto.getDescription(),
                dto.getProduct_name());
    }

    @Override
    public boolean update(Product dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update product set getDate=?, qty=?,price=?,itemType = ?, description = ?, product_name = ? where product_id = ?",
                dto.getProduct_id(),
                dto.getGetDate(),
                dto.getQty(),
                dto.getPrice(),
                dto.getType(),
                dto.getDescription(),
                dto.getProduct_name());
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("Select * from product where product_id = ?",id);

        if (resultSet.next()){
            return new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from product where product_id = ?"+id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Product_id FROM product ORDER BY Product_id DESC LIMIT 1");
        if(resultSet.next()){
            String id = resultSet.getString("Product_id");
            String numericPart = id.replaceAll("\\D", "");
            int newProId = Integer.parseInt(numericPart) + 1 ;
            return String.format("P%03d", newProId);
        } else {
            return "P001";
        }
    }
}
