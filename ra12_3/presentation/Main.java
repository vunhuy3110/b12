package ra12_3.presentation;

import ra12_3.business.OrderBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderBusiness orderBusiness = new OrderBusiness();

        int choice;

        do {
            System.out.println("\n************* QUẢN LÝ ĐƠN HÀNG *************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Hiển thị danh sách đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng theo mã đơn hàng");
            System.out.println("4. Xóa đơn hàng theo mã đơn hàng");
            System.out.println("5. Tìm kiếm đơn hàng theo tên khách hàng");
            System.out.println("6. Thống kê tổng số đơn hàng");
            System.out.println("7. Thống kê tổng doanh thu các đơn hàng Delivered");
            System.out.println("8. Thống kê số lượng đơn hàng theo từng trạng thái");
            System.out.println("9. Tìm kiếm đơn hàng có giá trị lớn nhất");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        orderBusiness.addOrder(scanner);
                        break;

                    case 2:
                        orderBusiness.displayOrders();
                        break;

                    case 3:
                        orderBusiness.updateOrderStatus(scanner);
                        break;

                    case 4:
                        orderBusiness.deleteOrder(scanner);
                        break;

                    case 5:
                        orderBusiness.searchOrder(scanner);
                        break;

                    case 6:
                        orderBusiness.statisticsTotalOrders();
                        break;

                    case 7:
                        orderBusiness.statisticsRevenue();
                        break;

                    case 8:
                        orderBusiness.statisticsByStatus();
                        break;

                    case 9:
                        orderBusiness.findLargestOrder();
                        break;

                    case 10:
                        System.out.println("Thoát chương trình!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Vui lòng nhập số nguyên!");
            }

        } while (choice != 10);

        scanner.close();
    }
}

