package ra12_2.business;

import ra12_2.entity.Appointment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentBusiness {
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public boolean isIdExist(String id) {
        return appointmentList.stream()
                .anyMatch(a -> a.getAppointmentId().equalsIgnoreCase(id));
    }

    public void addAppointment(Scanner scanner) {
        Appointment appointment = new Appointment();

        appointment.inputData(scanner);

        while (isIdExist(appointment.getAppointmentId())) {
            System.out.println("Mã lịch hẹn đã tồn tại!");

            System.out.print("Nhập lại mã lịch hẹn: ");
            String id = scanner.nextLine().trim();

            if (id.length() == 6 && !isIdExist(id)) {
                appointment.setAppointmentId(id);
                break;
            }

            System.out.println("Mã lịch hẹn phải gồm 6 ký tự và không trùng!");
        }

        appointmentList.add(appointment);
        System.out.println("Thêm lịch hẹn thành công!");
    }

    public void displayAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("Danh sách lịch hẹn trống!");
            return;
        }

        appointmentList.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate))
                .forEach(System.out::println);
    }

    public void searchAppointment(Scanner scanner) {
        System.out.print("Nhập tên bệnh nhân cần tìm: ");
        String name = scanner.nextLine().trim().toLowerCase();

        var result = appointmentList.stream()
                .filter(a -> a.getPatientName().toLowerCase().contains(name))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy lịch hẹn!");
        } else {
            result.forEach(System.out::println);
        }
    }

    public void updateAppointment(Scanner scanner) {
        System.out.print("Nhập mã lịch hẹn cần cập nhật: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optional = appointmentList.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst();

        optional.ifPresentOrElse(appointment -> {
            while (true) {
                System.out.print("Nhập tên bệnh nhân mới: ");
                String name = scanner.nextLine().trim();

                if (name.length() >= 10 && name.length() <= 50) {
                    appointment.setPatientName(name);
                    break;
                }

                System.out.println("Tên bệnh nhân phải từ 10-50 ký tự!");
            }

            while (true) {
                System.out.print("Nhập số điện thoại mới: ");
                String phone = scanner.nextLine().trim();

                if (phone.matches("0[35789][0-9]{8}")) {
                    appointment.setPhoneNumber(phone);
                    break;
                }

                System.out.println("Số điện thoại không hợp lệ!");
            }

            while (true) {
                try {
                    System.out.print("Nhập ngày hẹn mới (dd/MM/yyyy): ");
                    String date = scanner.nextLine().trim();

                    LocalDate appointmentDate = LocalDate.parse(date, FORMATTER);
                    appointment.setAppointmentDate(appointmentDate);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Ngày hẹn không hợp lệ!");
                }
            }

            while (true) {
                System.out.print("Nhập tên bác sĩ mới: ");
                String doctor = scanner.nextLine().trim();

                if (!doctor.isEmpty() && doctor.length() <= 200) {
                    appointment.setDoctor(doctor);
                    break;
                }

                System.out.println("Tên bác sĩ không được rỗng và tối đa 200 ký tự!");
            }

            System.out.println("Cập nhật lịch hẹn thành công!");

        }, () -> System.out.println("Không tìm thấy mã lịch hẹn!"));
    }

    public void deleteAppointment(Scanner scanner) {
        System.out.print("Nhập mã lịch hẹn cần xóa: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optional = appointmentList.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst();

        optional.ifPresentOrElse(appointment -> {
            System.out.print("Bạn có chắc chắn muốn xóa? (Y/N): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {
                appointmentList.remove(appointment);
                System.out.println("Xóa lịch hẹn thành công!");
            } else {
                System.out.println("Đã hủy thao tác xóa!");
            }

        }, () -> System.out.println("Không tìm thấy mã lịch hẹn!"));
    }

    public void statistics() {
        System.out.println("Tổng số lịch hẹn: " + appointmentList.size());

        if (appointmentList.isEmpty()) {
            System.out.println("Chưa có lịch hẹn để thống kê!");
            return;
        }

        Map<String, Long> result = appointmentList.stream()
                .collect(Collectors.groupingBy(
                        Appointment::getDoctor,
                        Collectors.counting()
                ));

        System.out.println("Số lịch hẹn theo từng bác sĩ:");

        result.forEach((doctor, count) ->
                System.out.println(doctor + ": " + count + " lịch hẹn"));
    }
}

