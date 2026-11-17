package ra12_1.entity;

import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    public Product() {
    }

    public Product(int productId, String productName, float price,
                   String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên sản phẩm phải từ 10-50 ký tự!");
            } else {
                productName = name;
                break;
            }
        }

        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                price = Float.parseFloat(scanner.nextLine());

                if (price > 0 && Float.isFinite(price)) {
                    break;
                }

                System.out.println("Giá sản phẩm phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Giá phải là số thực!");
            }
        }

        while (true) {
            System.out.print("Nhập loại sản phẩm: ");
            String cate = scanner.nextLine().trim();

            if (!cate.isEmpty() && cate.length() <= 200) {
                category = cate;
                break;
            }

            System.out.println("Loại sản phẩm không được rỗng và tối đa 200 ký tự!");
        }

        while (true) {
            try {
                System.out.print("Nhập số lượng tồn kho: ");
                quantity = Integer.parseInt(scanner.nextLine());

                if (quantity >= 0) {
                    break;
                }

                System.out.println("Số lượng phải lớn hơn hoặc bằng 0!");
            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }
    }

    @Override
    public String toString() {
        return "Mã sản phẩm: " + productId +
                " | Tên sản phẩm: " + productName +
                " | Giá: " + price +
                " | Loại: " + category +
                " | Số lượng: " + quantity;
    }
}