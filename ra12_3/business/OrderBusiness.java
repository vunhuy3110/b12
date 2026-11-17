package ra12_3.business;

import ra12_3.entity.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness {
    private ArrayList<Order> orderList = new ArrayList<>();
    private int nextId = 1;

    public void addOrder(Scanner scanner) {
        Order order = new Order();

        order.inputData(scanner);
        order.setOrderId(nextId);
        order.setStatus("Pending");

        orderList.add(order);
        nextId++;

        System.out.println("Thêm đơn hàng thành công!");
        System.out.println("Mã đơn hàng: " + order.getOrderId());
    }

    public void displayOrders() {
        if (orderList.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống!");
            return;
        }

        orderList.stream()
                .sorted(Comparator.comparing(Order::getOrderAmount).reversed())
                .forEach(System.out::println);
    }

    public Order findOrderById(int id) {
        for (Order order : orderList) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(Scanner scanner) {
        try {
            System.out.print("Nhập mã đơn hàng cần cập nhật trạng thái: ");
            int id = Integer.parseInt(scanner.nextLine());

            Order order = findOrderById(id);

            if (order == null) {
                System.out.println("Mã đơn hàng không tồn tại!");
                return;
            }

            if (order.getStatus().equals("Pending")) {
                order.setStatus("Shipped");
                System.out.println("Cập nhật trạng thái thành Shipped thành công!");
            } else if (order.getStatus().equals("Shipped")) {
                order.setStatus("Delivered");
                System.out.println("Cập nhật trạng thái thành Delivered thành công!");
            } else {
                System.out.println("Đơn hàng đã Delivered, không thể cập nhật tiếp!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Mã đơn hàng phải là số nguyên!");
        }
    }

    public void deleteOrder(Scanner scanner) {
        try {
            System.out.print("Nhập mã đơn hàng cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());

            Order order = findOrderById(id);

            if (order == null) {
                System.out.println("Mã đơn hàng không tồn tại!");
                return;
            }

            if (order.getStatus().equals("Pending")) {
                orderList.remove(order);
                System.out.println("Xóa đơn hàng thành công!");
            } else {
                System.out.println("Chỉ được xóa đơn hàng có trạng thái Pending!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Mã đơn hàng phải là số nguyên!");
        }
    }

    public void searchOrder(Scanner scanner) {
        System.out.print("Nhập tên khách hàng cần tìm: ");
        String name = scanner.nextLine().trim().toLowerCase();

        var result = orderList.stream()
                .filter(order -> order.getCustomerName()
                        .toLowerCase().contains(name))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy đơn hàng!");
        } else {
            result.forEach(System.out::println);
        }
    }

    public void statisticsTotalOrders() {
        System.out.println("Tổng số đơn hàng: " + orderList.size());
    }

    public void statisticsRevenue() {
        float revenue = (float) orderList.stream()
                .filter(order -> order.getStatus().equals("Delivered"))
                .mapToDouble(Order::getOrderAmount)
                .sum();

        System.out.println("Tổng doanh thu đơn hàng Delivered: " + revenue);
    }

    public void statisticsByStatus() {
        Map<String, Long> result = orderList.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.counting()
                ));

        System.out.println("Số lượng đơn hàng theo trạng thái:");

        System.out.println("Pending: " + result.getOrDefault("Pending", 0L));
        System.out.println("Shipped: " + result.getOrDefault("Shipped", 0L));
        System.out.println("Delivered: " + result.getOrDefault("Delivered", 0L));
    }

    public void findLargestOrder() {
        orderList.stream()
                .max(Comparator.comparing(Order::getOrderAmount))
                .ifPresentOrElse(
                        order -> {
                            System.out.println("Đơn hàng có giá trị lớn nhất:");
                            System.out.println(order);
                        },
                        () -> System.out.println("Danh sách đơn hàng trống!")
                );
    }
}