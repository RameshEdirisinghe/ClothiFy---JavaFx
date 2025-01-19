package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private int id;
    private String Name;
    private String Category;
    private String Size;
    private Double price;
    private int qty;
    private String imgPath;
    private String Suppler;
}
