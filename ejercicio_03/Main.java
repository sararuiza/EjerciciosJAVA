public class Main {
    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();

        Course math = new Course("FULLCOURSE101","Integral Calculus");
        Course english = new Course("FULLCOURSE102","English b2: intercultural trip");

        Student student0101 = new Student(1,"Jaime Velasquez","jaime@gmail.com");
        Student student0102 = new Student(1,"Juan Carlos","juanito@gmail.com");

        math.inscribirEstudiante(student0101);
        english.inscribirEstudiante(student0102);

        courseManagement.addCourse(math);
        courseManagement.addCourse(english);

        courseManagement.showStudents("FULLCOURSE101");
        courseManagement.showStudents("FULLCOURSE102");
    }
}
