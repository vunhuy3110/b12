package ra12_2.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Appointment {
        private String appointmentId;
        private String patientName;
        private String phoneNumber;
        private LocalDate appointmentDate;
        private String doctor;

        private static final DateTimeFormatter FORMATTER =
                DateTimeFormatter.ofPattern("dd/MM/uuuu");

        public Appointment() {
        }

        public Appointment(String appointmentId, String patientName,
                           String phoneNumber, LocalDate appointmentDate,
                           String doctor) {
            this.appointmentId = appointmentId;
            this.patientName = patientName;
            this.phoneNumber = phoneNumber;
            this.appointmentDate = appointmentDate;
            this.doctor = doctor;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public LocalDate getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

        public String getDoctor() {
            return doctor;
        }

        public void setDoctor(String doctor) {
            this.doctor = doctor;
        }

        public void inputData(Scanner scanner) {
            while (true) {
                System.out.print("Nhập mã lịch hẹn (6 ký tự): ");
                String id = scanner.nextLine().trim();

                if (id.length() == 6) {
                    appointmentId = id;
                    break;
                }

                System.out.println("Mã lịch hẹn phải gồm 6 ký tự!");
            }

            while (true) {
                System.out.print("Nhập tên bệnh nhân: ");
                String name = scanner.nextLine().trim();

                if (name.length() >= 10 && name.length() <= 50) {
                    patientName = name;
                    break;
                }

                System.out.println("Tên bệnh nhân phải từ 10-50 ký tự!");
            }

            while (true) {
                System.out.print("Nhập số điện thoại: ");
                String phone = scanner.nextLine().trim();

                if (Pattern.matches("0[35789][0-9]{8}", phone)) {
                    phoneNumber = phone;
                    break;
                }

                System.out.println("Số điện thoại không hợp lệ!");
            }

            while (true) {
                try {
                    System.out.print("Nhập ngày hẹn (dd/MM/yyyy): ");
                    String date = scanner.nextLine().trim();

                    appointmentDate = LocalDate.parse(date, FORMATTER);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Ngày hẹn không hợp lệ! Vui lòng nhập dd/MM/yyyy.");
                }
            }

            while (true) {
                System.out.print("Nhập tên bác sĩ phụ trách: ");
                String doctorName = scanner.nextLine().trim();

                if (!doctorName.isEmpty() && doctorName.length() <= 200) {
                    doctor = doctorName;
                    break;
                }

                System.out.println("Tên bác sĩ không được rỗng và tối đa 200 ký tự!");
            }
        }

        @Override
        public String toString() {
            return "Mã lịch hẹn: " + appointmentId
                    + " | Bệnh nhân: " + patientName
                    + " | Số điện thoại: " + phoneNumber
                    + " | Ngày hẹn: " + appointmentDate.format(FORMATTER)
                    + " | Bác sĩ: " + doctor;
        }
    }

