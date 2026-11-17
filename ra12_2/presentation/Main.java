package ra12_2.presentation;

import ra12_2.business.AppointmentBusiness;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentBusiness appointmentBusiness = new AppointmentBusiness();

        int choice;

        do {
            System.out.println("\n************* QUẢN LÝ LỊCH HẸN *************");
            System.out.println("1. Thêm lịch hẹn");
            System.out.println("2. Hiển thị danh sách lịch hẹn");
            System.out.println("3. Tìm kiếm lịch hẹn theo tên bệnh nhân");
            System.out.println("4. Cập nhật lịch hẹn theo mã lịch hẹn");
            System.out.println("5. Xóa lịch hẹn theo mã lịch hẹn");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        appointmentBusiness.addAppointment(scanner);
                        break;

                    case 2:
                        appointmentBusiness.displayAppointments();
                        break;

                    case 3:
                        appointmentBusiness.searchAppointment(scanner);
                        break;

                    case 4:
                        appointmentBusiness.updateAppointment(scanner);
                        break;

                    case 5:
                        appointmentBusiness.deleteAppointment(scanner);
                        break;

                    case 6:
                        appointmentBusiness.statistics();
                        break;

                    case 7:
                        System.out.println("Thoát chương trình!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Vui lòng nhập số nguyên!");
            }

        } while (choice != 7);

        scanner.close();
    }
}
