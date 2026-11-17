package ra12_1.business;

import ra12_1.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class ProductBusiness {
    private ArrayList<Product> productList = new ArrayList<>();
    private int nextId = 1;

    public boolean isNameExist(String name, int excludeId) {
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(name)
                    && product.getProductId() != excludeId) {
                return true;
            }
        }
        return false;
    }

    public void addProduct(Scanner scanner) {
        Product product = new Product();

        product.inputData(scanner);

        while (isNameExist(product.getProductName(), -1)) {
            System.out.println("Tên sản phẩm đã tồn tại!");

            while (true) {
                System.out.print("Nhập lại tên sản phẩm: ");
                String name = scanner.nextLine().trim();

                if (name.length() < 10 || name.length() > 50) {
                    System.out.println("Tên phải từ 10-50 ký tự!");
                } else {
                    product.setProductName(name);
                    break;
                }
            }
        }

        product.setProductId(nextId);
        productList.add(product);

        nextId++;

        System.out.println("Thêm sản phẩm thành công!");
        System.out.println("Mã sản phẩm: " + product.getProductId());
    }

    public void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public Product findProductById(int id) {
        for (Product product : productList) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(Scanner scanner) {
        try {
            System.out.print("Nhập mã sản phẩm cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());

            Product product = findProductById(id);

            if (product == null) {
                System.out.println("Mã sản phẩm không tồn tại!");
                return;
            }

            System.out.println("Nhập thông tin mới:");

            while (true) {
                System.out.print("Nhập tên sản phẩm: ");
                String name = scanner.nextLine().trim();

                if (name.length() < 10 || name.length() > 50) {
                    System.out.println("Tên phải từ 10-50 ký tự!");
                } else if (isNameExist(name, id)) {
                    System.out.println("Tên sản phẩm đã tồn tại!");
                } else {
                    product.setProductName(name);
                    break;
                }
            }

            while (true) {
                try {
                    System.out.print("Nhập giá sản phẩm: ");
                    float price = Float.parseFloat(scanner.nextLine());

                    if (price > 0 && Float.isFinite(price)) {
                        product.setPrice(price);
                        break;
                    }

                    System.out.println("Giá phải lớn hơn 0!");

                } catch (NumberFormatException e) {
                    System.out.println("Giá phải là số thực!");
                }
            }

            while (true) {
                System.out.print("Nhập loại sản phẩm: ");
                String category = scanner.nextLine().trim();

                if (!category.isEmpty() && category.length() <= 200) {
                    product.setCategory(category);
                    break;
                }

                System.out.println("Loại sản phẩm tối đa 200 ký tự!");
            }

            while (true) {
                try {
                    System.out.print("Nhập số lượng tồn kho: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (quantity >= 0) {
                        product.setQuantity(quantity);
                        break;
                    }

                    System.out.println("Số lượng phải >= 0!");

                } catch (NumberFormatException e) {
                    System.out.println("Số lượng phải là số nguyên!");
                }
            }

            System.out.println("Cập nhật sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
        }
    }

    public void deleteProduct(Scanner scanner) {
        try {
            System.out.print("Nhập mã sản phẩm cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());

            Product product = findProductById(id);

            if (product == null) {
                System.out.println("Mã sản phẩm không tồn tại!");
                return;
            }

            productList.remove(product);

            System.out.println("Xóa sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
        }
    }

    public void searchProduct(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine().trim();

        int count = 0;

        for (Product product : productList) {
            if (product.getProductName().toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(product);
                count++;
            }
        }

        System.out.println("Tổng số sản phẩm tìm thấy: " + count);
    }

    public void sortByPrice() {
        Collections.sort(productList,
                Comparator.comparing(Product::getPrice));

        System.out.println("Sắp xếp theo giá tăng dần thành công!");
        displayProducts();
    }

    public void sortByQuantity() {
        Collections.sort(productList,
                Comparator.comparingInt(Product::getQuantity).reversed());

        System.out.println("Sắp xếp theo số lượng giảm dần thành công!");
        displayProducts();
    }
}