package ra12_1.presentation;

import ra12_1.business.ProductBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductBusiness productBusiness = new ProductBusiness();

        int choice;

        do {
            System.out.println("\n******** QUẢN LÝ SẢN PHẨM ********");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Sắp xếp sản phẩm theo số lượng giảm dần");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        productBusiness.addProduct(scanner);
                        break;

                    case 2:
                        productBusiness.displayProducts();
                        break;

                    case 3:
                        productBusiness.updateProduct(scanner);
                        break;

                    case 4:
                        productBusiness.deleteProduct(scanner);
                        break;

                    case 5:
                        productBusiness.searchProduct(scanner);
                        break;

                    case 6:
                        productBusiness.sortByPrice();
                        break;

                    case 7:
                        productBusiness.sortByQuantity();
                        break;

                    case 8:
                        System.out.println("Thoát chương trình!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Vui lòng nhập số nguyên!");
            }

        } while (choice != 8);

        scanner.close();
    }
}
