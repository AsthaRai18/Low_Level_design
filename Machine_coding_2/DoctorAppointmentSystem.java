import java.util.*;

enum Speciality {
    Cardiologist, Dermatologist, Orthopedic, General_Physician
}

class Slot {
    String time;
    Map<Doctor, Boolean> doctorAvailable = new HashMap<>();
    Map<Doctor, Queue<Patient>> waitList = new HashMap<>();

    public Slot(String time) {
        this.time = time;
    }
}

class Doctor {
    String name;
    Speciality speciality;
    Set<String> bookedSlots = new HashSet<>();
    int totalAppointments = 0;

    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }
}

class Patient {
    String name;
    Set<String> bookedSlots = new HashSet<>();

    public Patient(String name) {
        this.name = name;
    }
}

class Booking {
    static int counter = 1;
    int bookingId;
    Doctor doctor;
    Patient patient;
    String slotTime;

    public Booking(Doctor doc, Patient pat, String slotTime) {
        this.doctor = doc;
        this.patient = pat;
        this.slotTime = slotTime;
        this.bookingId = counter++;
    }
}

class BookingSystem {
    Map<String, Slot> slots = new HashMap<>();
    Map<String, Doctor> doctors = new HashMap<>();
    Map<String, Patient> patients = new HashMap<>();
    Map<Integer, Booking> bookings = new HashMap<>();
    Doctor trendingDoctor = null;

    public BookingSystem() {
        initializeSlots();
    }

    void initializeSlots() {
    int hour = 9;
    int min = 0;
    while (hour < 21) {
        String slotStart = String.format("%02d:%02d", hour, min);
        slots.put(slotStart, new Slot(slotStart));
        min += 30;
        if (min == 60) {
            hour++;
            min = 0;
        }
    }
  }

    void registerDoctor(String name, Speciality sp) {
        if (!doctors.containsKey(name)) {
            doctors.put(name, new Doctor(name, sp));
            System.out.println("Welcome Dr. " + name + " !!");
        }
    }

    void markAvailability(String doctorName, List<String> slotStarts) {
        Doctor doc = doctors.get(doctorName);
        if (doc == null)
        {
            System.out.println("Doctor is not registered: " + doctorName);
            return;
        }
        for (String start : slotStarts) {
            if (!slots.containsKey(start)) {
                System.out.println("Invalid slot: " + start);
                continue;
            }
            Slot slot = slots.get(start);
            slot.doctorAvailable.put(doc, true);
            slot.waitList.putIfAbsent(doc, new LinkedList<>());
        }
        System.out.println("Done Doc!");
    }

    void registerPatient(String name) {
        patients.putIfAbsent(name, new Patient(name));
    }

    void showAvailableBySpeciality(Speciality sp) {
        for (Slot slot : slots.values()) {
            for (Doctor doc : slot.doctorAvailable.keySet()) {
                if (doc.speciality == sp && slot.doctorAvailable.get(doc)) {
                    System.out.println("Dr." + doc.name + ": (" + slot.time + ")");
                }
            }
        }
    }

    void bookAppointment(String patientName, String doctorName, String slotStart) {
        Patient p = patients.get(patientName);
        Doctor d = doctors.get(doctorName);
        Slot slot = slots.get(slotStart);

        if (p == null || d == null || slot == null) return;

        if (p.bookedSlots.contains(slot.time)) {
            System.out.println("Patient already booked in this slot.");
            return;
        }

        if (!slot.doctorAvailable.containsKey(d)) {
            System.out.println("Doctor not available in this slot.");
            return;
        }

        if (slot.doctorAvailable.get(d)) {
            slot.doctorAvailable.put(d, false);
            p.bookedSlots.add(slot.time);
            d.bookedSlots.add(slot.time);
            d.totalAppointments++;
            Booking booking = new Booking(d, p, slot.time);
            bookings.put(booking.bookingId, booking);
            updateTrendingDoctor(d);
            System.out.println("Booked. Booking id: " + booking.bookingId);
        } else {
            slot.waitList.get(d).add(p);
            System.out.println("Doctor is booked. Patient added to waitlist.");
        }
    }

    void cancelBooking(int bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking id");
            return;
        }

        Slot slot = slots.get(booking.slotTime);
        Doctor doc = booking.doctor;
        Patient pat = booking.patient;

        slot.doctorAvailable.put(doc, true);
        doc.bookedSlots.remove(slot.time);
        pat.bookedSlots.remove(slot.time);
        doc.totalAppointments--;

        System.out.println("Booking Cancelled");

        Queue<Patient> waitlist = slot.waitList.get(doc);
        if (waitlist != null && !waitlist.isEmpty()) {
            Patient next = waitlist.poll();
            slot.doctorAvailable.put(doc, false);
            doc.bookedSlots.add(slot.time);
            next.bookedSlots.add(slot.time);
            doc.totalAppointments++;
            Booking newBooking = new Booking(doc, next, slot.time);
            bookings.put(newBooking.bookingId, newBooking);
            updateTrendingDoctor(doc);
            System.out.println("Appointment auto-booked for waitlisted patient: " + next.name);
        }
    }
    
    // this method is used to view appointments for a doctor or patient -- depending on the isDoctor flag
    void viewAppointments(String name, boolean isDoctor) {
        if (isDoctor) {
            Doctor doc = doctors.get(name);
            if (doc == null) return;
            System.out.println("Appointments for Dr. " + name);
            for (String time : doc.bookedSlots) {
                System.out.println(time);
            }
        } else {
            Patient pat = patients.get(name);
            if (pat == null) return;
            System.out.println("Appointments for patient " + name);
            for (String time : pat.bookedSlots) {
                System.out.println(time);
            }
        }
    }

    void updateTrendingDoctor(Doctor d) {
        if (trendingDoctor == null || d.totalAppointments > trendingDoctor.totalAppointments) {
            trendingDoctor = d;
        }
    }

    void showTrendingDoctor() {
        if (trendingDoctor != null) {
            System.out.println("Trending Doctor: Dr. " + trendingDoctor.name + " with " + trendingDoctor.totalAppointments + " appointments");
        } else {
            System.out.println("No appointments yet.");
        }
    }
}
public class DoctorAppointmentSystem {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        system.registerDoctor("Curious", Speciality.Cardiologist);
        system.markAvailability("Curious", Arrays.asList("09:30", "12:30", "16:00"));

        system.registerDoctor("Dreadful", Speciality.Dermatologist);
        system.markAvailability("Dreadful", Arrays.asList("09:30", "12:30", "16:00"));

        system.showAvailableBySpeciality(Speciality.Cardiologist);

        system.registerPatient("PatientA");
        system.bookAppointment("PatientA", "Curious", "12:30");

        system.showAvailableBySpeciality(Speciality.Cardiologist);

      //   system.cancelBooking(1);

      //   system.showAvailableBySpeciality(Speciality.Cardiologist);

        
        system.registerPatient("PatientB");
        system.bookAppointment("PatientB", "Curious", "12:30");
         system.cancelBooking(1);
        system.registerDoctor("Daring", Speciality.Dermatologist);
        system.markAvailability("Daring", Arrays.asList("11:30", "14:00"));

        system.showAvailableBySpeciality(Speciality.Dermatologist);
    
        system.showTrendingDoctor();
    }
}