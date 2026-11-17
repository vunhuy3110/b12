package ra12_3.entity;

import java.util.Scanner;
public class Order {
    private int orderId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private float orderAmount;
    private String status;

    public Order() {
    }

    public Order(int orderId, String customerName, String phoneNumber,
                 String address, float orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên khách hàng: ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 6 && name.length() <= 100) {
                customerName = name;
                break;
            }

            System.out.println("Tên khách hàng phải từ 6-100 ký tự!");
        }

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phone = scanner.nextLine().trim();

            if (phone.matches("0[35789][0-9]{8}")) {
                phoneNumber = phone;
                break;
            }

            System.out.println("Số điện thoại không hợp lệ!");
        }

        while (true) {
            System.out.print("Nhập địa chỉ giao hàng: ");
            String add = scanner.nextLine().trim();

            if (!add.isEmpty()) {
                address = add;
                break;
            }

            System.out.println("Địa chỉ không được để trống!");
        }

        while (true) {
            try {
                System.out.print("Nhập giá trị đơn hàng: ");
                float amount = Float.parseFloat(scanner.nextLine());

                if (amount > 0 && Float.isFinite(amount)) {
                    orderAmount = amount;
                    break;
                }

                System.out.println("Giá trị đơn hàng phải lớn hơn 0!");

            } catch (NumberFormatException e) {
                System.out.println("Giá trị đơn hàng phải là số thực!");
            }
        }
    }

    @Override
    public String toString() {
        return "Mã đơn hàng: " + orderId
                + " | Khách hàng: " + customerName
                + " | Số điện thoại: " + phoneNumber
                + " | Địa chỉ: " + address
                + " | Giá trị đơn hàng: " + orderAmount
                + " | Trạng thái: " + status;
    }
}
